package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CoreProtectedRotatedPillarBlock extends RotatedPillarBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter world, net.minecraft.core.BlockPos pos)
	{
		float f = state.getDestroySpeed(world, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = player.hasCorrectToolForDrops(state) ? 30 : 100;
			return player.getDestroySpeed(state) / f / (float)i;
		}
	}
}