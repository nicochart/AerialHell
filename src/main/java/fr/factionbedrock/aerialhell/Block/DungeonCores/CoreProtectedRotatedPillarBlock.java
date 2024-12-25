package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.world.BlockView;

public class CoreProtectedRotatedPillarBlock extends PillarBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.of("core_protected");
	
	public CoreProtectedRotatedPillarBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.get(CORE_PROTECTED);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, net.minecraft.util.math.BlockPos pos)
	{
		float f = state.getHardness(world, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = player.canHarvest(state) ? 30 : 100;
			return player.getBlockBreakingSpeed(state) / f / (float)i;
		}
	}
}