package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.function.ToIntFunction;

public class GlowingStellarTallGrass extends AerialHellTallGrassBlock
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	public GlowingStellarTallGrass(AbstractBlock.Settings settings) {super(settings); this.setDefaultState(this.getDefaultState().with(LIT, false));}

	@Override public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler)
	{
		if (!world.isClient) {interact(state, world, pos);}
	}

	@Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hitResult)
	{
		if (!world.isClient) {interact(state, world, pos);}
		return ActionResult.PASS;
	}

	private static void interact(BlockState state, World world, BlockPos pos)
	{
		if (!state.get(LIT)) {world.setBlockState(pos, state.with(LIT, true), 3);}
	}

	@Override public boolean hasRandomTicks(BlockState state) {return state.get(LIT);}

	@Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		if (state.get(LIT)) {world.setBlockState(pos, state.with(LIT, Boolean.valueOf(false)), 3);}
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> state) {state.add(LIT);}

	private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {return (state) -> {return state.get(RedstoneTorchBlock.LIT) ? p_50760_ : 0;};}
}
