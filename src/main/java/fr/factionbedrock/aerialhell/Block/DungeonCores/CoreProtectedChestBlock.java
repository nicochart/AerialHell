package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.AerialHellChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.ChestType;
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

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockState state = super.getStateForPlacement(context);

		if (state.hasProperty(ChestBlock.TYPE) && state.getValue(ChestBlock.TYPE) != ChestType.SINGLE)
		{
			Direction connectedDirection = ChestBlock.getConnectedDirection(state);
			BlockPos neighborPos = context.getClickedPos().relative(connectedDirection);
			BlockState neighborState = context.getLevel().getBlockState(neighborPos);

			if (neighborState.hasProperty(CORE_PROTECTED) && neighborState.getValue(CORE_PROTECTED))
			{
				return state.setValue(ChestBlock.TYPE, ChestType.SINGLE);
			}
		}
		return state;
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit)
	{
		return this.canUse(state, player) ? super.useWithoutItem(state, level, pos, player, hit) : InteractionResult.SUCCESS;
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}
}
