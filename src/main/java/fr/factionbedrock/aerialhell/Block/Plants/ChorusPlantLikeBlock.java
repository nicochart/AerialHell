package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ChorusPlantLikeBlock extends ChorusPlantBlock
{
    public ChorusPlantLikeBlock(Properties prop) {super(prop);}

    @Override public BlockState getStateForPlacement(BlockGetter p_51711_, BlockPos p_51712_)
    {
        BlockState state_below = p_51711_.getBlockState(p_51712_.below());
        BlockState state_above = p_51711_.getBlockState(p_51712_.above());
        BlockState state_north = p_51711_.getBlockState(p_51712_.north());
        BlockState state_east = p_51711_.getBlockState(p_51712_.east());
        BlockState state_south = p_51711_.getBlockState(p_51712_.south());
        BlockState state_west = p_51711_.getBlockState(p_51712_.west());
        return this.defaultBlockState()
                .setValue(DOWN, Boolean.valueOf(state_below.is(this) || state_below.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get()) || state_below.is(AerialHellTags.Blocks.STELLAR_DIRT)))
                .setValue(UP, Boolean.valueOf(state_above.is(this) || state_above.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .setValue(NORTH, Boolean.valueOf(state_north.is(this) || state_north.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .setValue(EAST, Boolean.valueOf(state_east.is(this) || state_east.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .setValue(SOUTH, Boolean.valueOf(state_south.is(this) || state_south.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())))
                .setValue(WEST, Boolean.valueOf(state_west.is(this) || state_west.is(AerialHellBlocksAndItems.FULL_MOON_FLOWER.get())));
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
