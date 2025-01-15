package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ShadowLeavesBlock extends ShiftableLeavesBlock implements ShadowSpreaderBlock
{
	public ShadowLeavesBlock(Properties properties, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
	{
		super(properties, shiftedVariant, shiftType);
		this.registerDefaultState(this.defaultBlockState().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(CAN_SPREAD, true));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {super.createBlockStateDefinition(builder); builder.add(CAN_SPREAD);}

	@Override protected boolean isRandomlyTicking(BlockState state) {return state.getValue(CAN_SPREAD);}

	@Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston)
	{
		super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
		if (BlockHelper.canAnyNeighborBeCorrupted(level, pos, BlockHelper.CorruptionType.ANY))
		{
			level.setBlock(pos, state.setValue(CAN_SPREAD, true), 2);
		}
	}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		ShadowSpreaderBlock.trySpreading(state, level, pos, rand);
	}
}