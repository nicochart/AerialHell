package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class DoubleShroomBlock extends DoublePlantBlock
{
    public DoubleShroomBlock(Properties prop) {super(prop);}

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos)
    {
        boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(reader, pos, 3);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {return solidSurfaceAbove && super.canSurvive(state, reader, pos);}
        else
        {
            if (state.getBlock() != this) {return solidSurfaceAbove && super.canSurvive(state, reader, pos);}
            else
            {
                boolean brightnessFlag = reader.getRawBrightness(pos, 0) < 13;
                return solidSurfaceAbove && brightnessFlag && super.canSurvive(state, reader, pos);
            }
        }
    }

    @Override protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos)
    {
        return state.is(BlockTags.DIRT) || state.is(AerialHellBlocks.STELLAR_COARSE_DIRT.get()) || state.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }
}