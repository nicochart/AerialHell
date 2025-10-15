package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Item.StructureVoidPlacerItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin
{
    @Shadow @Final private ServerPlayerEntity player;
    @Shadow @Final private ServerWorld world;

    @Inject(method = "tryBreakBlock", at = @At(value = "HEAD"))
    private void afterBlockBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        if (!world.isClient)
        {
            //world.breakBlock(pos.up(), false);
            BlockState state = world.getBlockState(pos);
            ItemStack stack = player.getMainHandStack();
            if (stack.getItem() instanceof StructureVoidPlacerItem && EntityHelper.isCreativePlayer(player))
            {
                //if (!state.isOf(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)) {return;}

                stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
                BlockPos.Mutable mutablePos = new BlockPos.Mutable();
                int dx,dy,dz,radius=5;
                for (dx = -radius; dx <= radius; dx++)
                {
                    for (dy = -radius; dy <= radius; dy++)
                    {
                        for (dz = -radius; dz <= radius; dz++)
                        {
                            if (dx * dx + dy * dy + dz * dz > radius * radius) continue;

                            mutablePos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);

                            if (!world.getBlockState(mutablePos).isOf(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)) continue;
                            if (!(world.getBlockEntity(mutablePos) instanceof IntangibleTemporaryBlockEntity intangibleTemporaryBlockEntity)) continue;
                            if (intangibleTemporaryBlockEntity.getBeforeState() == null || !intangibleTemporaryBlockEntity.getBeforeState().isOf(StructureVoidPlacerItem.PLACED_BLOCKSTATE.getBlock())) continue;

                            world.removeBlockEntity(mutablePos);
                            world.setBlockState(mutablePos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        }
                    }
                }
            }
        }


        /*
        BlockState state = world.getBlockState(pos);
        if (world.isClient) {return;}
        ItemStack stack = player.getMainHandStack();
        if (stack.getItem() instanceof StructureVoidPlacerItem && EntityHelper.isCreativePlayer(player))
        {
            //if (!state.isOf(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)) {return;}

            stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
            BlockPos.Mutable mutablePos = new BlockPos.Mutable();
            int dx,dy,dz,radius=5;
            System.out.println("Execution du for");
            for (dx = -radius; dx <= radius; dx++)
            {
                for (dy = -radius; dy <= radius; dy++)
                {
                    for (dz = -radius; dz <= radius; dz++)
                    {
                        if (dx * dx + dy * dy + dz * dz > radius * radius) continue;

                        mutablePos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);

                        if (!world.getBlockState(mutablePos).isOf(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)) continue;
                        if (!(world.getBlockEntity(mutablePos) instanceof IntangibleTemporaryBlockEntity intangibleTemporaryBlockEntity)) continue;
                        if (intangibleTemporaryBlockEntity.getBeforeState() != null && intangibleTemporaryBlockEntity.getBeforeState().isOf(StructureVoidPlacerItem.PLACED_BLOCKSTATE.getBlock())) continue;

                        world.removeBlockEntity(mutablePos);
                        world.setBlockState(mutablePos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                    }
                }
            }
        }*/
    }
}
