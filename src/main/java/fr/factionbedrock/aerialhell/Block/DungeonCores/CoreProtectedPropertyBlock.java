package fr.factionbedrock.aerialhell.Block.DungeonCores;



import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public interface CoreProtectedPropertyBlock
{
    //used in ExplosionBehaviorMixin
    default Optional<Float> getModifiedBlastResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? Optional.of(1200.0F) : Optional.empty();
    }

    default float getModifiedDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
    {
        float f = state.getDestroySpeed(world, pos);
        if (f == -1.0F || isProtected(state))
        {
            return 0.0F;
        }
        else
        {
            int i = player.hasCorrectToolForDrops(state) ? 30 : 100;
            return player.getDestroySpeed(state) / f / (float)i;
        }
    }

    default  boolean isProtected(BlockState state) {return state.getValue(CORE_PROTECTED);}
}
