package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public interface CoreProtectedPropertyBlock
{
    default float getModifiedExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : state.getBlock().getExplosionResistance();
    }

    default float getModifiedDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
    {
        float f = state.getDestroySpeed(level, pos);
        if (f == -1.0F || isProtected(state)) {return 0.0F;}
        else
        {
            int i = net.neoforged.neoforge.event.EventHooks.doPlayerHarvestCheck(player, state, level, pos) ? 30 : 100;
            return player.getDestroySpeed(state, pos) / f / (float)i;
        }
    }

    default boolean isProtected(BlockState state) {return state.getValue(CORE_PROTECTED);}
}
