package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BasicShadowSpreaderBlock extends Block implements ShadowSpreaderBlock
{
	public BasicShadowSpreaderBlock(Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CAN_SPREAD, true).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(CAN_SPREAD, AerialHellGrassBlock.SHIFTED_RENDER);}

	@Override protected boolean isRandomlyTicking(BlockState state) {return state.getValue(CAN_SPREAD);}

	@Override protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
	{
		super.neighborChanged(state, world, pos, neighborBlock, neighborPos, movedByPiston);
		if (BlockHelper.canBeCorrupted(world, neighborPos, BlockHelper.CorruptionType.ANY))
		{
			world.setBlock(pos, state.setValue(CAN_SPREAD, true), 2);
		}
	}

	@Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		ShadowSpreaderBlock.trySpreading(state, world, pos, rand);
	}
}