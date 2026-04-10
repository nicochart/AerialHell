package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class VoluciteStoneBlock extends CoreProtectedBlock
{
    public VoluciteStoneBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false).setValue(AerialHellBooleanProperties.SELF_LUMINESCENT, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(AerialHellBooleanProperties.SELF_LUMINESCENT);
    }

    @Override protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        if (state.is(this) && random.nextFloat() > 0.95F && world.isLoaded(pos))
        {
            world.setBlockAndUpdate(pos, state.setValue(AerialHellBooleanProperties.SELF_LUMINESCENT, true).setValue(CORE_PROTECTED, wasCoreProtected));

            world.scheduleTick(pos, this, 1200);
        }
    }

    @Override protected boolean isRandomlyTicking(BlockState state)
    {
        return !state.getValue(AerialHellBooleanProperties.SELF_LUMINESCENT);
    }

    @Override public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        world.setBlockAndUpdate(pos, state.setValue(AerialHellBooleanProperties.SELF_LUMINESCENT, false).setValue(CORE_PROTECTED, wasCoreProtected));
    }
}
