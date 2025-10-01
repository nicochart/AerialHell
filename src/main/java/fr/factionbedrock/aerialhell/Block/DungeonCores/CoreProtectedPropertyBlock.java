package fr.factionbedrock.aerialhell.Block.DungeonCores;



import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;

import java.util.Optional;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public interface CoreProtectedPropertyBlock
{
    //used in ExplosionBehaviorMixin
    default Optional<Float> getModifiedBlastResistance(BlockState state, BlockView world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? Optional.of(1200.0F) : Optional.empty();
    }

    default float getModifiedDestroyProgress(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
    {
        float f = state.getHardness(world, pos);
        if (f == -1.0F || isProtected(state))
        {
            return 0.0F;
        }
        else
        {
            int i = player.canHarvest(state) ? 30 : 100;
            return player.getBlockBreakingSpeed(state) / f / (float)i;
        }
    }

    default  boolean isProtected(BlockState state) {return state.get(CORE_PROTECTED);}
}
