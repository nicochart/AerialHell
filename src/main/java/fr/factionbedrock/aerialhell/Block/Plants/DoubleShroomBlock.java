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
    public DoubleShroomBlock(Properties settings) {super(settings);}

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {return solidSurfaceAbove && super.canSurvive(state, world, pos);}
        else
        {
            if (state.getBlock() != this) {return solidSurfaceAbove && super.canSurvive(state, world, pos);}
            else
            {
                boolean brightnessFlag = world.getMaxLocalRawBrightness(pos, 0) < 13;
                return solidSurfaceAbove && brightnessFlag && super.canSurvive(state, world, pos);
            }
        }
    }

    @Override protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
    {
        return floor.is(BlockTags.DIRT) || floor.is(AerialHellBlocks.STELLAR_COARSE_DIRT) || floor.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || floor.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }
}