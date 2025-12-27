package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Item.StructureVoidPlacerItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerTickMixin
{
    private static final List<StructureBlockBlockEntity> REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST = new ArrayList<>();
    private static final BlockPos.Mutable MUTABLE_POS = new BlockPos.Mutable();

    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        if (!player.getMainHandStack().isOf(AerialHellItems.STRUCTURE_VOID_PLACER)) {return;}
        int radius = player.getMainHandStack().getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        if (radius == 0) {return;}

        BlockPos playerPos = player.getBlockPos();
        ServerWorld world = player.getEntityWorld();

        //structure block detection every 4 seconds
        if (player.age % 80 == 0)
        {
            REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST.clear();
            WorldHelper.listStructureBlockEntitiesInZone(REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST, world, playerPos, 128);
        }
        //block transformation around player if block is air in a structure block bounding box
        if (!REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST.isEmpty())
        {
            int dx,dy,dz;
            for (dx = -radius; dx <= radius; dx++)
            {
                for (dy = -radius; dy <= radius; dy++)
                {
                    for (dz = -radius; dz <= radius; dz++)
                    {
                        if (dx * dx + dy * dy + dz * dz > radius * radius) continue;

                        MUTABLE_POS.set(playerPos.getX() + dx, playerPos.getY() + dy, playerPos.getZ() + dz);

                        //ignore current iteration if block is not air
                        if (!world.isAir(MUTABLE_POS)) continue;

                        if (BlockHelper.isPosInsideStructureBlockZone(MUTABLE_POS, REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST))
                        {
                            world.setBlockState(MUTABLE_POS, AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK.getDefaultState(), Block.NOTIFY_ALL);
                            BlockHelper.setIntangibleTemporaryBlockEntityBeforeState(world, MUTABLE_POS, StructureVoidPlacerItem.PLACED_BLOCKSTATE);
                        }
                    }
                }
            }
        }
    }
}
