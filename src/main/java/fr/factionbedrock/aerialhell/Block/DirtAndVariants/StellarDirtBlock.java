package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
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
			if (state.is(AerialHellBlocks.STELLAR_DIRT.get()) || state.is(AerialHellBlocks.CHISELED_STELLAR_DIRT.get()))
			{
				return AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState();
			}
			else if (state.is(AerialHellBlocks.STELLAR_COARSE_DIRT.get()))
			{
				return AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState();
			}
		}
		else if (ItemAbilities.SHOVEL_FLATTEN == itemAbility)
		{
			return AerialHellBlocks.STELLAR_DIRT_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}