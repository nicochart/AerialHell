package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ChorusPlantLikeBlock extends ChorusPlantBlock
{
    public ChorusPlantLikeBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return getStateForPlacement(context.getLevel(), context.getClickedPos(), this.defaultBlockState());
    }

    public static BlockState getStateForPlacement(BlockGetter world, BlockPos pos, BlockState state)
    {
        BlockState state_below = world.getBlockState(pos.below());
        BlockState state_above = world.getBlockState(pos.above());
        BlockState state_north = world.getBlockState(pos.north());
        BlockState state_east = world.getBlockState(pos.east());
        BlockState state_south = world.getBlockState(pos.south());
        BlockState state_west = world.getBlockState(pos.west());
        return state
                .trySetValue(DOWN, Boolean.valueOf(state_below.is(state.getBlock()) || state_below.is(AerialHellBlocks.FULL_MOON_FLOWER) || state_below.is(AerialHellTags.Blocks.STELLAR_DIRT)))
                .trySetValue(UP, Boolean.valueOf(state_above.is(state.getBlock()) || state_above.is(AerialHellBlocks.FULL_MOON_FLOWER)))
                .trySetValue(NORTH, Boolean.valueOf(state_north.is(state.getBlock()) || state_north.is(AerialHellBlocks.FULL_MOON_FLOWER)))
                .trySetValue(EAST, Boolean.valueOf(state_east.is(state.getBlock()) || state_east.is(AerialHellBlocks.FULL_MOON_FLOWER)))
                .trySetValue(SOUTH, Boolean.valueOf(state_south.is(state.getBlock()) || state_south.is(AerialHellBlocks.FULL_MOON_FLOWER)))
                .trySetValue(WEST, Boolean.valueOf(state_west.is(state.getBlock()) || state_west.is(AerialHellBlocks.FULL_MOON_FLOWER)));
    }

    @Override protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (!state.canSurvive(world, pos))
        {
            tickView.scheduleTick(pos, this, 1);
            return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
        else
        {
            boolean flag = neighborState.is(this) || neighborState.is(AerialHellBlocks.FULL_MOON_FLOWER) || direction == Direction.DOWN && neighborState.is(AerialHellTags.Blocks.STELLAR_DIRT);
            return state.setValue(PROPERTY_BY_DIRECTION.get(direction), flag);
        }
    }

    @Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockState blockstate = world.getBlockState(pos.below());
        boolean noAir = !world.getBlockState(pos.above()).isAir() && !blockstate.isAir();

        for(Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate1 = world.getBlockState(blockpos);
            if (blockstate1.is(this))
            {
                if (noAir) {return false;}
                BlockState blockstate2 = world.getBlockState(blockpos.below());
                if (blockstate2.is(this) || blockstate2.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
            }
        }
        return blockstate.is(this) || blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT);
    }
}
