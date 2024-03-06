package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ChorusPlantLikeBlock extends ChorusPlantBlock
{
    public ChorusPlantLikeBlock(Properties prop) {super(prop);}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return getStateForPlacement(context.getLevel(), context.getClickedPos());
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
                .trySetValue(DOWN, Boolean.valueOf(state_below.is(state.getBlock()) || state_below.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get()) || state_below.is(AerialHellTags.Blocks.STELLAR_DIRT)))
                .trySetValue(UP, Boolean.valueOf(state_above.is(state.getBlock()) || state_above.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .trySetValue(NORTH, Boolean.valueOf(state_north.is(state.getBlock()) || state_north.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .trySetValue(EAST, Boolean.valueOf(state_east.is(state.getBlock()) || state_east.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .trySetValue(SOUTH, Boolean.valueOf(state_south.is(state.getBlock()) || state_south.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .trySetValue(WEST, Boolean.valueOf(state_west.is(state.getBlock()) || state_west.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())));
    }

    @Override public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor level, BlockPos pos1, BlockPos pos2)
    {
        if (!state1.canSurvive(level, pos1))
        {
            level.scheduleTick(pos1, this, 1);
            return super.updateShape(state1, direction, state2, level, pos1, pos2);
        }
        else
        {
            boolean flag = state2.is(this) || state2.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get()) || direction == Direction.DOWN && state2.is(AerialHellTags.Blocks.STELLAR_DIRT);
            return state1.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
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
