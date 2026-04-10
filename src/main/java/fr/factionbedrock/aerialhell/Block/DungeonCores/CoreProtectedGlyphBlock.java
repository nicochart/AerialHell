package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.GlyphBlock;
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

public class CoreProtectedGlyphBlock extends GlyphBlock implements CoreProtectedPropertyUseableBlock
{
	public CoreProtectedGlyphBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
		super.createBlockStateDefinition(builder);
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level world, net.minecraft.core.BlockPos pos, Player player, BlockHitResult hit)
	{
		return this.canUse(state, player) ? super.useWithoutItem(state, world, pos, player, hit) : InteractionResult.PASS;
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}
}
