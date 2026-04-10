package fr.factionbedrock.aerialhell.Block.DungeonCores;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CoreProtectedRotatedPillarBlock extends RotatedPillarBlock implements CoreProtectedPropertyBlock
{
	public CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, false));
	}
	
	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}
}