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
import net.minecraft.world.level.block.state.BlockState;

public class ChorusPlantLikeBlock extends ChorusPlantBlock
{
    public ChorusPlantLikeBlock(Properties prop) {super(prop);}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return getStateForPlacement(context.getLevel(), context.getClickedPos(), this.defaultBlockState());
    }
    public static BlockState getStateForPlacement(BlockGetter blockGetter, BlockPos pos, BlockState state)
    {
        BlockState state_below = blockGetter.getBlockState(pos.below());
        BlockState state_above = blockGetter.getBlockState(pos.above());
        BlockState state_north = blockGetter.getBlockState(pos.north());
        BlockState state_east = blockGetter.getBlockState(pos.east());
        BlockState state_south = blockGetter.getBlockState(pos.south());
        BlockState state_west = blockGetter.getBlockState(pos.west());
        return state
                .trySetValue(DOWN, Boolean.valueOf(state_below.is(state.getBlock()) || state_below.is(AerialHellBlocks.FULL_MOON_FLOWER.get()) || state_below.is(AerialHellTags.Blocks.STELLAR_DIRT)))
                .trySetValue(UP, Boolean.valueOf(state_above.is(state.getBlock()) || state_above.is(AerialHellBlocks.FULL_MOON_FLOWER.get())))
                .trySetValue(NORTH, Boolean.valueOf(state_north.is(state.getBlock()) || state_north.is(AerialHellBlocks.FULL_MOON_FLOWER.get())))
                .trySetValue(EAST, Boolean.valueOf(state_east.is(state.getBlock()) || state_east.is(AerialHellBlocks.FULL_MOON_FLOWER.get())))
                .trySetValue(SOUTH, Boolean.valueOf(state_south.is(state.getBlock()) || state_south.is(AerialHellBlocks.FULL_MOON_FLOWER.get())))
                .trySetValue(WEST, Boolean.valueOf(state_west.is(state.getBlock()) || state_west.is(AerialHellBlocks.FULL_MOON_FLOWER.get())));
    }

    @Override protected BlockState updateShape(BlockState previousState, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
    {
        if (!previousState.canSurvive(level, pos))
        {
            scheduledTickAccess.scheduleTick(pos, this, 1);
            return super.updateShape(previousState, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        }
        else
        {
            boolean flag = neighborState.is(this) || neighborState.is(AerialHellBlocks.FULL_MOON_FLOWER.get()) || direction == Direction.DOWN && neighborState.is(AerialHellTags.Blocks.STELLAR_DIRT);
            return previousState.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
        }
    }

    @Override public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos)
    {
        BlockState blockstate = reader.getBlockState(pos.below());
        boolean noAir = !reader.getBlockState(pos.above()).isAir() && !blockstate.isAir();

        for(Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate1 = reader.getBlockState(blockpos);
            if (blockstate1.is(this))
            {
                if (noAir) {return false;}
                BlockState blockstate2 = reader.getBlockState(blockpos.below());
                if (blockstate2.is(this) || blockstate2.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
            }
        }
        return blockstate.is(this) || blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT);
    }
}
