package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StellarWheatBlock extends FarmlandBlock
{
    public StellarWheatBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {return state.is(AerialHellBlocksAndItems.STELLAR_FARMLAND.get());}

    @Override protected ItemLike getBaseSeedId() {return AerialHellBlocksAndItems.STELLAR_WHEAT_SEEDS.get();}
}
