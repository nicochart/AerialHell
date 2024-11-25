package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirtPathBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class StellarDirtPathBlock extends DirtPathBlock
{
	public StellarDirtPathBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override public BlockState getPlacementState(ItemPlacementContext context)
	{
		return !this.getDefaultState().canPlaceAt(context.getWorld(), context.getBlockPos()) ? Block.pushEntitiesUpBeforeBlockChange(this.getDefaultState(), AerialHellBlocks.STELLAR_DIRT.getDefaultState(), context.getWorld(), context.getBlockPos()) : super.getPlacementState(context);
	}

	@Override protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		StellarFarmBlock.turnToStellarDirt(null, state, world, pos);
	}
}