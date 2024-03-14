package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StellarDirtPathBlock extends DirtPathBlock
{
	public StellarDirtPathBlock(Properties properties) {super(properties);}

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState(), context.getLevel(), context.getClickedPos()) : super.getStateForPlacement(context);
	}

	@Override public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource rand) {StellarFarmBlock.turnToStellarDirt(null, state, serverLevel, pos);}
}