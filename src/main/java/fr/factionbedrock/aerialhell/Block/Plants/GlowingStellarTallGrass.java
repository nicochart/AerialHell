package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;

import java.util.function.ToIntFunction;

public class GlowingStellarTallGrass extends AerialHellTallGrassBlock
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	public GlowingStellarTallGrass(AbstractBlock.Settings settings) {super(settings); this.setDefaultState(this.getDefaultState().with(LIT, false));}

	@Override public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if (!level.isClientSide) {interact(state, level, pos);}
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, PlayerEntity player, BlockHitResult hitResult)
	{
		if (!level.isClientSide) {interact(state, level, pos);}
		return InteractionResult.PASS;
	}

	private static void interact(BlockState state, Level level, BlockPos pos)
	{
		if (!state.get(LIT)) {level.setBlockState(pos, state.with(LIT, true), 3);}
	}

	@Override public boolean hasRandomTicks(BlockState state) {return state.get(LIT);}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (state.get(LIT)) {level.setBlockState(pos, state.with(LIT, Boolean.valueOf(false)), 3);}
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> state) {state.add(LIT);}

	private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {return (p_50763_) -> {return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;};}
}
