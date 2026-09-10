package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class StellarDirtBlock extends Block
{
	public StellarDirtBlock(Properties properties)
	{
		super(properties);
	}

	@Override @Nullable public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(toolAction)) {return null;}
		if (ToolActions.HOE_TILL == toolAction)
		{
			if (state.is(AerialHellBlocksAndItems.STELLAR_DIRT.get()) || state.is(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get()))
			{
				return AerialHellBlocksAndItems.STELLAR_FARMLAND.get().defaultBlockState();
			}
			else if (state.is(AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get()))
			{
				return AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState();
			}
		}
		else if (ToolActions.SHOVEL_FLATTEN == toolAction)
		{
			return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}