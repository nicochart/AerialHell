package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class DoubleShroomBlock extends TallPlantBlock
{
    public DoubleShroomBlock(Settings settings) {super(settings);}

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3);
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {return solidSurfaceAbove && super.canPlaceAt(state, world, pos);}
        else
        {
            if (state.getBlock() != this) {return solidSurfaceAbove && super.canPlaceAt(state, world, pos);}
            else
            {
                boolean brightnessFlag = world.getLightLevel(pos, 0) < 13;
                return solidSurfaceAbove && brightnessFlag && super.canPlaceAt(state, world, pos);
            }
        }
    }

    @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
    {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(AerialHellBlocks.STELLAR_COARSE_DIRT) || floor.isIn(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || floor.isIn(BlockTags.MUSHROOM_GROW_BLOCK);
    }
}