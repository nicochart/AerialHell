package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class StellarDirtPathBlock extends DirtPathBlock
{
	public StellarDirtPathBlock(Properties properties) {super(properties);}

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState(), context.getLevel(), context.getClickedPos()) : super.getStateForPlacement(context);
	}

	@Override public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource rand) {StellarFarmBlock.turnToStellarDirt(null, state, serverLevel, pos);}

	@Override @Nullable public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (ItemAbilities.HOE_TILL == itemAbility) {return AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState();}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}