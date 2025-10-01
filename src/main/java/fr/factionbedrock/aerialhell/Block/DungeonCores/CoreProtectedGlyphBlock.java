package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.GlyphBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class CoreProtectedGlyphBlock extends GlyphBlock implements CoreProtectedPropertyUseableBlock
{
	public CoreProtectedGlyphBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
	{
		return this.canUse(state, player) ? super.useWithoutItem(state, level, pos, player, hitResult) : InteractionResult.PASS;
	}

	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
		super.createBlockStateDefinition(builder);
	}
	
	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		float f = state.getDestroySpeed(level, pos);
	    if (f == -1.0F || isProtected(state))
	    {
	         return 0.0F;
	    }
	    else
	    {
	         int i = net.neoforged.neoforge.event.EventHooks.doPlayerHarvestCheck(player, state, level, pos) ? 30 : 100;
	         return player.getDestroySpeed(state, pos) / f / (float)i;
	    }
	}
}
