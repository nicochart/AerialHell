package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class StellarDirtPathBlock extends DirtPathBlock
{
	public StellarDirtPathBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), AerialHellBlocks.STELLAR_DIRT.defaultBlockState(), context.getLevel(), context.getClickedPos()) : super.getStateForPlacement(context);
	}

	@Override protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
	{
		StellarFarmBlock.turnToStellarDirt(null, state, world, pos);
	}
}