package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class VerticalGrowingPlantBlock extends Block
{
    public static final IntProperty AGE = Properties.AGE_15;
    public static final BooleanProperty TOP = BooleanProperty.of("top");
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private final int MAX_GROWTH_HEIGHT;

    public VerticalGrowingPlantBlock(AbstractBlock.Settings settings, int maxHeight)
    {
        super(settings);
        this.MAX_GROWTH_HEIGHT = maxHeight;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(TOP, true));
    }

    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return SHAPE;}

    @Override protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (!state.canPlaceAt(world, pos)) {world.breakBlock(pos, true);}
    }

    @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        boolean state_is_top = this.updateTopProperty(state, world, pos);
        int state_age = state.get(AGE);

        if (state_is_top && world.isAir(pos.up()))
        {
            int height;
            for (height = 1; world.getBlockState(pos.down(height)).isOf(this); ++height) {}

            if (height < this.MAX_GROWTH_HEIGHT)
            {
                if (state_age == 15) {this.growUp(state, world, pos);}
                else {world.setBlockState(pos, state.with(AGE, Integer.valueOf(state_age + 1)), 4);}
            }
        }
    }

    private boolean updateTopProperty(BlockState state, ServerWorld world, BlockPos pos)
    {
        boolean state_is_top = state.get(TOP);
        boolean state_is_actually_top = !world.getBlockState(pos.up()).isOf(this);
        if (state_is_top && !state_is_actually_top)
        {
            world.setBlockState(pos, state.with(TOP, false));
            return false;
        }
        else if (!state_is_top && state_is_actually_top)
        {
            world.setBlockState(pos, state.with(TOP, true));
            return true;
        }
        return state_is_top;
    }

    private void growUp(BlockState state, ServerWorld world, BlockPos pos)
    {
        world.setBlockState(pos.up(), this.getDefaultState());
        world.setBlockState(pos, state.with(AGE, 0).with(TOP, false));
    }

    @Override public BlockState getStateForNeighborUpdate(BlockState state1, Direction direction, BlockState state2, WorldAccess worldAccess, BlockPos pos1, BlockPos pos2)
    {
        if (!state1.canPlaceAt(worldAccess, pos1)) {worldAccess.scheduleBlockTick(pos1, this, 1);}
        return super.getStateForNeighborUpdate(state1, direction, state2, worldAccess, pos1, pos2);
    }

    @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        BlockState blockState = world.getBlockState(pos.down());
        return blockState.isOf(this) || blockState.isIn(BlockTags.DIRT);
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> stateDefinitionBuilder) {stateDefinitionBuilder.add(AGE); stateDefinitionBuilder.add(TOP);}
}