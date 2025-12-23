package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Item.StructureVoidPlacerItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixin //ServerPlayerInteractionManagerMixin
{
    @Shadow @Final private ServerPlayer player;
    @Shadow @Final private ServerLevel level;

    @Inject(method = "destroyBlock", at = @At(value = "HEAD"))
    private void afterBlockBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        if (!level.isClientSide())
        {
            BlockState state = level.getBlockState(pos);
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof StructureVoidPlacerItem && EntityHelper.isCreativePlayer(player))
            {
                stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
                int dx,dy,dz,radius=5;
                for (dx = -radius; dx <= radius; dx++)
                {
                    for (dy = -radius; dy <= radius; dy++)
                    {
                        for (dz = -radius; dz <= radius; dz++)
                        {
                            if (dx * dx + dy * dy + dz * dz > radius * radius) continue;

                            mutablePos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);

                            if (!level.getBlockState(mutablePos).is(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)) continue;
                            if (!(level.getBlockEntity(mutablePos) instanceof IntangibleTemporaryBlockEntity intangibleTemporaryBlockEntity)) continue;
                            if (intangibleTemporaryBlockEntity.getBeforeState() == null || !intangibleTemporaryBlockEntity.getBeforeState().is(StructureVoidPlacerItem.PLACED_BLOCKSTATE.getBlock())) continue;

                            level.removeBlockEntity(mutablePos);
                            level.setBlock(mutablePos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                        }
                    }
                }
            }
        }
    }
}
