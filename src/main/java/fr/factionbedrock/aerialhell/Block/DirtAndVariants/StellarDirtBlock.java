package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class StellarDirtBlock extends Block
{
	public StellarDirtBlock(Properties properties)
	{
		super(properties);
	}

	//make it tillable
	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(toolAction)) {return null;}
		boolean isStellarDirt =  state.getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get(), isStellarCoarseDirt = state.getBlock() == AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get(), isChiseledStellarDirt = state.getBlock() == AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get();
		if (isStellarDirt || isStellarCoarseDirt || isChiseledStellarDirt)
		{
			if (ToolActions.HOE_TILL == toolAction) {return isStellarCoarseDirt ? AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState() : AerialHellBlocksAndItems.STELLAR_FARMLAND.get().defaultBlockState();}
			if (ToolActions.SHOVEL_FLATTEN == toolAction) {return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().defaultBlockState();}
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}