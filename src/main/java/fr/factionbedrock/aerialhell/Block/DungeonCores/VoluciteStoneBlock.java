package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class VoluciteStoneBlock extends CoreProtectedBlock
{
    public VoluciteStoneBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false).setValue(AerialHellStateProperties.SELF_LUMINESCENT, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(AerialHellStateProperties.SELF_LUMINESCENT);
    }

    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        if (state.is(this) && random.nextFloat() > 0.95F && level.isLoaded(pos))
        {
            level.setBlockAndUpdate(pos, state.setValue(AerialHellStateProperties.SELF_LUMINESCENT, true).setValue(CORE_PROTECTED, wasCoreProtected));

            level.scheduleTick(pos, this, 1200);
        }
    }

    @Override protected boolean isRandomlyTicking(BlockState state)
    {
        return !state.getValue(AerialHellStateProperties.SELF_LUMINESCENT);
    }

    @Override protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        level.setBlockAndUpdate(pos, state.setValue(AerialHellStateProperties.SELF_LUMINESCENT, false).setValue(CORE_PROTECTED, wasCoreProtected));
    }
}
