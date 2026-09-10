package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class StellarDirtPathBlock extends DirtPathBlock
{
	public StellarDirtPathBlock(Properties properties) {super(properties);}

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState(), context.getLevel(), context.getClickedPos()) : super.getStateForPlacement(context);
	}

	@Override public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource rand) {StellarFarmBlock.turnToStellarDirt(null, state, serverLevel, pos);}

	@Override @Nullable public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(toolAction)) {return null;}
		if (ToolActions.HOE_TILL == toolAction) {return AerialHellBlocksAndItems.STELLAR_FARMLAND.get().defaultBlockState();}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}