package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class GlowingStellarTallGrass extends AerialHellTallGrassBlock
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	public GlowingStellarTallGrass(BlockBehaviour.Properties settings) {super(settings); this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));}

	@Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
	{
		if (!world.isClientSide) {interact(state, world, pos);}
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hitResult)
	{
		if (!world.isClientSide) {interact(state, world, pos);}
		return InteractionResult.PASS;
	}

	private static void interact(BlockState state, Level world, BlockPos pos)
	{
		if (!state.getValue(LIT)) {world.setBlock(pos, state.setValue(LIT, true), 3);}
	}

	@Override public boolean isRandomlyTicking(BlockState state) {return state.getValue(LIT);}

	@Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		if (state.getValue(LIT)) {world.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);}
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {state.add(LIT);}

	@Override public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {return hasSpreadableNeighbourPos(level, pos, state);}

	@Override public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		findSpreadableNeighbourPos(level, pos, state).ifPresent((blockPos) -> level.setBlockAndUpdate(blockPos, this.defaultBlockState()));
	}
}
