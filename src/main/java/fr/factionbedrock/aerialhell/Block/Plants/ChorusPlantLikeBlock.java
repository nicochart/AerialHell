package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class ChorusPlantLikeBlock extends ChorusPlantBlock
{
    public ChorusPlantLikeBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override public BlockState getPlacementState(ItemPlacementContext context)
    {
        return getStateForPlacement(context.getWorld(), context.getBlockPos(), this.getDefaultState());
    }

    public static BlockState getStateForPlacement(BlockView world, BlockPos pos, BlockState state)
    {
        BlockState state_below = world.getBlockState(pos.down());
        BlockState state_above = world.getBlockState(pos.up());
        BlockState state_north = world.getBlockState(pos.north());
        BlockState state_east = world.getBlockState(pos.east());
        BlockState state_south = world.getBlockState(pos.south());
        BlockState state_west = world.getBlockState(pos.west());
        return state
                .withIfExists(DOWN, Boolean.valueOf(state_below.isOf(state.getBlock()) || state_below.isOf(AerialHellBlocks.FULL_MOON_FLOWER) || state_below.isIn(AerialHellTags.Blocks.STELLAR_DIRT)))
                .withIfExists(UP, Boolean.valueOf(state_above.isOf(state.getBlock()) || state_above.isOf(AerialHellBlocks.FULL_MOON_FLOWER)))
                .withIfExists(NORTH, Boolean.valueOf(state_north.isOf(state.getBlock()) || state_north.isOf(AerialHellBlocks.FULL_MOON_FLOWER)))
                .withIfExists(EAST, Boolean.valueOf(state_east.isOf(state.getBlock()) || state_east.isOf(AerialHellBlocks.FULL_MOON_FLOWER)))
                .withIfExists(SOUTH, Boolean.valueOf(state_south.isOf(state.getBlock()) || state_south.isOf(AerialHellBlocks.FULL_MOON_FLOWER)))
                .withIfExists(WEST, Boolean.valueOf(state_west.isOf(state.getBlock()) || state_west.isOf(AerialHellBlocks.FULL_MOON_FLOWER)));
    }

    @Override public BlockState getStateForNeighborUpdate(BlockState state1, Direction direction, BlockState state2, WorldAccess world, BlockPos pos1, BlockPos pos2)
    {
        if (!state1.canPlaceAt(world, pos1))
        {
            world.scheduleBlockTick(pos1, this, 1);
            return super.getStateForNeighborUpdate(state1, direction, state2, world, pos1, pos2);
        }
        else
        {
            boolean flag = state2.isOf(this) || state2.isOf(AerialHellBlocks.FULL_MOON_FLOWER) || direction == Direction.DOWN && state2.isIn(AerialHellTags.Blocks.STELLAR_DIRT);
            return state1.with(FACING_PROPERTIES.get(direction), Boolean.valueOf(flag));
        }
    }

    @Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        BlockState blockstate = world.getBlockState(pos.down());
        boolean noAir = !world.getBlockState(pos.up()).isAir() && !blockstate.isAir();

        for(Direction direction : Direction.Type.HORIZONTAL)
        {
            BlockPos blockpos = pos.offset(direction);
            BlockState blockstate1 = world.getBlockState(blockpos);
            if (blockstate1.isOf(this))
            {
                if (noAir) {return false;}
                BlockState blockstate2 = world.getBlockState(blockpos.down());
                if (blockstate2.isOf(this) || blockstate2.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
            }
        }
        return blockstate.isOf(this) || blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT);
    }
}
