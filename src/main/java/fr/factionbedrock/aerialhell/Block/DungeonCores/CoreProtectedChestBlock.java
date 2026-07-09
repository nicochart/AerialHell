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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CoreProtectedChestBlock extends AerialHellChestBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedChestBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		return (isProtected(state) && !player.isCreative()) ? InteractionResult.SUCCESS : super.useWithoutItem(state, world, pos, player, hit);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
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
