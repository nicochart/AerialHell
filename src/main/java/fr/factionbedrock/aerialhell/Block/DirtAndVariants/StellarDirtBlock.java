package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class StellarDirtBlock extends Block
{
	public StellarDirtBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		boolean isStellarDirt =  state.getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get(), isStellarCoarseDirt = state.getBlock() == AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get(), isChiseledStellarDirt = state.getBlock() == AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get();
		if (isStellarDirt || isStellarCoarseDirt || isChiseledStellarDirt)
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return isStellarCoarseDirt ? AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState() : AerialHellBlocksAndItems.STELLAR_FARMLAND.get().defaultBlockState();}
			if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().defaultBlockState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}