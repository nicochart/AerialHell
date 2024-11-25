package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ShadowStoneBlock extends BasicShadowSpreaderBlock
{
	public ShadowStoneBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		ShadowSpreaderBlock.trySpreading(state, world, pos, rand, true);
	}
}