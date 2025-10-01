package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.AerialHellChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class CoreProtectedChestBlock extends AerialHellChestBlock implements CoreProtectedPropertyUseableBlock
{
	public CoreProtectedChestBlock(Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit)
	{
		return this.canUse(state, player) ? super.useWithoutItem(state, level, pos, player, hit) : InteractionResult.SUCCESS ;
	}

	@Override public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
	{
		return this.getModifiedExplosionResistance(state, world, pos, explosion);
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, level, pos);
	}
}
