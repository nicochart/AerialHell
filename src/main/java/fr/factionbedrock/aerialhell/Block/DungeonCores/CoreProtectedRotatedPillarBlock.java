package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedRotatedPillarBlock extends PillarBlock implements CoreProtectedPropertyBlock
{
	public CoreProtectedRotatedPillarBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}
}