package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
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

    @Override public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor world, BlockPos pos1, BlockPos pos2)
    {
        if (!state1.canSurvive(world, pos1))
        {
            world.scheduleTick(pos1, this, 1);
            return super.updateShape(state1, direction, state2, world, pos1, pos2);
        }
        else
        {
            boolean flag = state2.is(this) || state2.is(AerialHellBlocks.FULL_MOON_FLOWER) || direction == Direction.DOWN && state2.is(AerialHellTags.Blocks.STELLAR_DIRT);
            return state1.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
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
