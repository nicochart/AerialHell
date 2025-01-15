package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;

public class VerticalGrowingPlantBlock extends Block
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private final int MAX_GROWTH_HEIGHT;

    public VerticalGrowingPlantBlock(Properties prop, int maxHeight)
    {
        super(prop);
        this.MAX_GROWTH_HEIGHT = maxHeight;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(TOP, true));
    }

    @Override public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {return SHAPE;}

    @Override public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (!state.canSurvive(level, pos)) {level.destroyBlock(pos, true);}
    }

    @Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        boolean state_is_top = this.updateTopProperty(state, level, pos);
        int state_age = state.getValue(AGE);

        if (state_is_top && level.isEmptyBlock(pos.above()))
        {
            int height;
            for (height = 1; level.getBlockState(pos.below(height)).is(this); ++height) {}

            if (height < this.MAX_GROWTH_HEIGHT)
            {
                if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, true))
                {
                    if (state_age == 15) {this.growUp(state, level, pos);}
                    else {level.setBlock(pos, state.setValue(AGE, Integer.valueOf(state_age + 1)), 4);}
                }
            }
        }
    }

    private boolean updateTopProperty(BlockState state, ServerLevel level, BlockPos pos)
    {
        boolean state_is_top = state.getValue(TOP);
        boolean state_is_actually_top = !level.getBlockState(pos.above()).is(this);
        if (state_is_top && !state_is_actually_top)
        {
            level.setBlockAndUpdate(pos, state.setValue(TOP, false));
            return false;
        }
        else if (!state_is_top && state_is_actually_top)
        {
            level.setBlockAndUpdate(pos, state.setValue(TOP, true));
            return true;
        }
        return state_is_top;
    }

    private void growUp(BlockState state, ServerLevel level, BlockPos pos)
    {
        level.setBlockAndUpdate(pos.above(), this.defaultBlockState());
        net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos.above(), this.defaultBlockState());
        level.setBlockAndUpdate(pos, state.setValue(AGE, 0).setValue(TOP, false));
    }

    @Override protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (!state.canSurvive(level, pos)) {scheduledTickAccess.scheduleTick(pos, this, 1);}
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.is(this) && !belowState.getValue(TOP)) {return true;}
        else
        {
            TriState soilDecision = belowState.canSustainPlant(level, pos.below(), Direction.UP, state);
            if (!soilDecision.isDefault()) {return soilDecision.isTrue();}
            if (belowState.is(BlockTags.DIRT)) {return true;}
            return false;
        }
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {stateDefinitionBuilder.add(AGE); stateDefinitionBuilder.add(TOP);}
    //@Override public net.neoforged.neoforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {return PlantType.PLAINS;} TODO
    //@Override public BlockState getPlant(BlockGetter world, BlockPos pos) {return defaultBlockState();}
}