package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.ToIntFunction;

public class GlowingStellarTallGrass extends AerialHellTallGrassBlock
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	public GlowingStellarTallGrass(Properties properties) {super(properties); this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));}

	@Override public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean intersects)
	{
		if (!level.isClientSide()) {interact(state, level, pos);}
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
	{
		if (!level.isClientSide()) {interact(state, level, pos);}
		return InteractionResult.PASS;
	}

	private static void interact(BlockState state, Level level, BlockPos pos)
	{
		if (!state.getValue(LIT)) {level.setBlock(pos, state.setValue(LIT, true), 3);}
	}

	@Override public boolean isRandomlyTicking(BlockState state) {return state.getValue(LIT);}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (state.getValue(LIT)) {level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);}
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {state.add(LIT);}

	private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {return (p_50763_) -> {return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;};}
}
