package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

public class VerticalGrowingPlantBlock extends Block implements net.minecraftforge.common.IPlantable
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
                if (ForgeHooks.onCropsGrowPre(level, pos, state, true))
                {
                    if (state_age == 15) {this.growUp(state, level, pos);}
                    else {level.setBlock(pos, state.setValue(AGE, state_age + 1).setValue(TOP, true), 4);}
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
        level.setBlockAndUpdate(pos, state.setValue(AGE, 0).setValue(TOP, false)); //this is no longer top
        level.setBlockAndUpdate(pos.above(), this.defaultBlockState()); //above is now top
        ForgeHooks.onCropsGrowPost(level, pos.above(), this.defaultBlockState());
    }

    @Override public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2)
    {
        if (!state1.canSurvive(levelAccessor, pos1)) {levelAccessor.scheduleTick(pos1, this, 1);}
        return super.updateShape(state1, direction, state2, levelAccessor, pos1, pos2);
    }

    @Override public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos)
    {
        BlockState soil = levelReader.getBlockState(pos.below());
        if (soil.canSustainPlant(levelReader, pos.below(), Direction.UP, this)) {return true;}
        BlockState belowstate = levelReader.getBlockState(pos.below());
        if (belowstate.is(this) && !belowstate.getValue(TOP)) {return true;}
        return false;
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinitionBuilder) {stateDefinitionBuilder.add(AGE); stateDefinitionBuilder.add(TOP);}
    @Override public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {return PlantType.PLAINS;}
    @Override public BlockState getPlant(BlockGetter world, BlockPos pos) {return defaultBlockState();}
}