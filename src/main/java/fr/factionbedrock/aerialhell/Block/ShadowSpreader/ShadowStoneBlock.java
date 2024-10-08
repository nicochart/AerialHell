package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowStoneBlock extends BasicShadowSpreaderBlock
{
	public ShadowStoneBlock(Properties properties) {super(properties);}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		ShadowSpreaderBlock.trySpreading(state, level, pos, rand, true);
	}
}