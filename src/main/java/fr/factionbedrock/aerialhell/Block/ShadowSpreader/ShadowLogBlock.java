package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ShadowLogBlock extends ShiftableLogBlock implements ShadowSpreaderBlock
{
	public ShadowLogBlock(Settings settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
	{
		super(settings, shiftedVariant, shiftType);
		this.setDefaultState(this.getDefaultState().with(CAN_SPREAD, true));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {super.appendProperties(builder); builder.add(CAN_SPREAD);}

	@Override protected boolean hasRandomTicks(BlockState state) {return state.get(CAN_SPREAD);}

	@Override protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
	{
		super.neighborUpdate(state, world, pos, neighborBlock, neighborPos, movedByPiston);
		if (BlockHelper.canBeCorrupted(world, neighborPos, BlockHelper.CorruptionType.ANY))
		{
			world.setBlockState(pos, state.with(CAN_SPREAD, true), 2);
		}
	}

	@Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		ShadowSpreaderBlock.trySpreading(state, world, pos, rand);
	}
}