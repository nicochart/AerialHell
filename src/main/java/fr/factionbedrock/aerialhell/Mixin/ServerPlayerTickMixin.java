package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Item.StructureVoidPlacerItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.LevelHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerPlayer.class)
public class ServerPlayerTickMixin
{
    private static final List<StructureBlockEntity> REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST = new ArrayList<>();
    private static final BlockPos.MutableBlockPos MUTABLE_POS = new BlockPos.MutableBlockPos();

    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayer player = (ServerPlayer) (Object) this;
        if (!player.getMainHandItem().is(AerialHellItems.STRUCTURE_VOID_PLACER)) {return;}
        int radius = player.getMainHandItem().getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        if (radius == 0) {return;}

        BlockPos playerPos = player.blockPosition();
        ServerLevel level = player.level();

        //structure block detection every 4 seconds
        if (player.tickCount % 80 == 0)
        {
            REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST.clear();
            LevelHelper.listStructureBlockEntitiesInZone(REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST, level, playerPos, 128);
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
                        if (!level.isEmptyBlock(MUTABLE_POS)) continue;

                        if (BlockHelper.isPosInsideStructureBlockZone(MUTABLE_POS, REUSABLE_NEARBY_STRUCTURE_BLOCK_ENTITIES_LIST))
                        {
                            level.setBlock(MUTABLE_POS, AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                            BlockHelper.setIntangibleTemporaryBlockEntityBeforeState(level, MUTABLE_POS, StructureVoidPlacerItem.PLACED_BLOCKSTATE);
                        }
                    }
                }
            }
        }
    }
}
