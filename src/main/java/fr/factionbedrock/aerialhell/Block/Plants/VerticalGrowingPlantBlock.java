package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalGrowingPlantBlock extends Block
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private final int MAX_GROWTH_HEIGHT;

    public VerticalGrowingPlantBlock(BlockBehaviour.Properties settings, int maxHeight)
    {
        super(settings);
        this.MAX_GROWTH_HEIGHT = maxHeight;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(TOP, true));
    }

    @Override protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {return SHAPE;}

    @Override protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (!state.canSurvive(world, pos)) {world.destroyBlock(pos, true);}
    }

    @Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
    {
        boolean state_is_top = this.updateTopProperty(state, world, pos);
        int state_age = state.getValue(AGE);

        if (state_is_top && world.isEmptyBlock(pos.above()))
        {
            int height;
            for (height = 1; world.getBlockState(pos.below(height)).is(this); ++height) {}

            if (height < this.MAX_GROWTH_HEIGHT)
            {
                if (state_age == 15) {this.growUp(state, world, pos);}
                else {world.setBlock(pos, state.setValue(AGE, Integer.valueOf(state_age + 1)), 4);}
            }
        }
    }

    private boolean updateTopProperty(BlockState state, ServerLevel world, BlockPos pos)
    {
        boolean state_is_top = state.getValue(TOP);
        boolean state_is_actually_top = !world.getBlockState(pos.above()).is(this);
        if (state_is_top && !state_is_actually_top)
        {
            world.setBlockAndUpdate(pos, state.setValue(TOP, false));
            return false;
        }
        else if (!state_is_top && state_is_actually_top)
        {
            world.setBlockAndUpdate(pos, state.setValue(TOP, true));
            return true;
        }
        return state_is_top;
    }

    private void growUp(BlockState state, ServerLevel world, BlockPos pos)
    {
        world.setBlockAndUpdate(pos.above(), this.defaultBlockState());
        world.setBlockAndUpdate(pos, state.setValue(AGE, 0).setValue(TOP, false));
    }

    @Override protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (!state.canSurvive(world, pos)) {tickView.scheduleTick(pos, this, 1);}
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockState blockState = world.getBlockState(pos.below());
        return blockState.is(this) || blockState.is(BlockTags.DIRT);
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {stateDefinitionBuilder.add(AGE); stateDefinitionBuilder.add(TOP);}
}