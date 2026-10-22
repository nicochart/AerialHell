package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class VoluciteStoneBlock extends CoreProtectedBlock
{
    public static final BooleanProperty SELF_LUMINESCENT = BooleanProperty.create("self_luminescent");

    public VoluciteStoneBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false).setValue(SELF_LUMINESCENT, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(SELF_LUMINESCENT);
    }

    //in 1.20.1, StairBlock delegates isRandomlyTicking, randomTick and tick to its base block with the stairs state,
    //so these methods also receive volucite stone stairs states (without SELF_LUMINESCENT property)
    @Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (!state.is(this)) {return;}
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        if (random.nextFloat() > 0.95F && level.isLoaded(pos))
        {
            level.setBlockAndUpdate(pos, state.setValue(SELF_LUMINESCENT, true).setValue(CORE_PROTECTED, wasCoreProtected));

            level.scheduleTick(pos, this, 1200);
        }
    }

    @Override public boolean isRandomlyTicking(BlockState state)
    {
        return state.is(this) && !state.getValue(SELF_LUMINESCENT);
    }

    @Override public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (!state.is(this)) {return;}
        boolean wasCoreProtected = state.getValue(CORE_PROTECTED);
        level.setBlockAndUpdate(pos, state.setValue(SELF_LUMINESCENT, false).setValue(CORE_PROTECTED, wasCoreProtected));
    }
}
