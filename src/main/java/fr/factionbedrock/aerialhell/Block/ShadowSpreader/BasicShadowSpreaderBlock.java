package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class BasicShadowSpreaderBlock extends Block implements ShadowSpreaderBlock
{
	public BasicShadowSpreaderBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(CAN_SPREAD, true).with(AerialHellBooleanProperties.SHIFTED_RENDER, false));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(CAN_SPREAD, AerialHellBooleanProperties.SHIFTED_RENDER);}

	@Override protected boolean hasRandomTicks(BlockState state) {return state.get(CAN_SPREAD);}

	@Override protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify)
	{
		super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
		if (BlockHelper.canAnyNeighborBeCorrupted(world, pos, BlockHelper.CorruptionType.ANY))
		{
			world.setBlockState(pos, state.with(CAN_SPREAD, true), 2);
		}
	}

	@Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		ShadowSpreaderBlock.trySpreading(state, world, pos, rand);
	}
}