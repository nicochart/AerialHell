package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowStoneBlock extends BasicShadowSpreaderBlock
{
	public ShadowStoneBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		ShadowSpreaderBlock.trySpreading(state, world, pos, rand, true);
	}
}