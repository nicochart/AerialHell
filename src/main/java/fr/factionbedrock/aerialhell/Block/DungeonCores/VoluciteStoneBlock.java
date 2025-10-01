package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class VoluciteStoneBlock extends CoreProtectedBlock
{
    public VoluciteStoneBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false).with(AerialHellBooleanProperties.SELF_LUMINESCENT, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(AerialHellBooleanProperties.SELF_LUMINESCENT);
    }

    @Override protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        boolean wasCoreProtected = state.get(CORE_PROTECTED);
        if (state.isOf(this) && random.nextFloat() > 0.95F && world.isPosLoaded(pos))
        {
            world.setBlockState(pos, state.with(AerialHellBooleanProperties.SELF_LUMINESCENT, true).with(CORE_PROTECTED, wasCoreProtected));

            world.scheduleBlockTick(pos, this, 1200);
        }
    }

    @Override protected boolean hasRandomTicks(BlockState state)
    {
        return !state.get(AerialHellBooleanProperties.SELF_LUMINESCENT);
    }

    @Override public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        boolean wasCoreProtected = state.get(CORE_PROTECTED);
        world.setBlockState(pos, state.with(AerialHellBooleanProperties.SELF_LUMINESCENT, false).with(CORE_PROTECTED, wasCoreProtected));
    }
}
