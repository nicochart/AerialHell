package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class StellarDirtBlock extends Block
{
	public StellarDirtBlock(Properties properties)
	{
		super(properties);
	}

	@Override @Nullable public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (ItemAbilities.HOE_TILL == itemAbility)
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
		else if (ItemAbilities.SHOVEL_FLATTEN == itemAbility)
		{
			return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}