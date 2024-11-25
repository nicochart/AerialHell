package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class StellarWheatBlock extends CropBlock
{
    public StellarWheatBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {return floor.isOf(AerialHellBlocks.STELLAR_FARMLAND);}

    @Override protected ItemConvertible getSeedsItem() {return AerialHellItems.STELLAR_WHEAT_SEEDS;}
}
