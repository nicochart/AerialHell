package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class StellarDirtBlock extends Block
{
	public StellarDirtBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	/*@Override @Nullable TODO : use mixins to intercept onItemUse (Axe, Shovel, Hoe..)
	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		boolean isStellarDirt =  state.getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get(), isStellarCoarseDirt = state.getBlock() == AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get(), isChiseledStellarDirt = state.getBlock() == AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get();
		if (isStellarDirt || isStellarCoarseDirt || isChiseledStellarDirt)
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return isStellarCoarseDirt ? AerialHellBlocksAndItems.STELLAR_DIRT.get().getDefaultState() : AerialHellBlocksAndItems.STELLAR_FARMLAND.get().getDefaultState();}
			if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().getDefaultState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}*/
}