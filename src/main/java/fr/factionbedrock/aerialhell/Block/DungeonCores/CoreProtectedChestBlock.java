package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.AerialHellChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedChestBlock extends AerialHellChestBlock implements CoreProtectedPropertyUseableBlock
{
	public CoreProtectedChestBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		return this.canUse(state, player) ? super.useWithoutItem(state, world, pos, player, hit) : InteractionResult.SUCCESS;
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}
}
