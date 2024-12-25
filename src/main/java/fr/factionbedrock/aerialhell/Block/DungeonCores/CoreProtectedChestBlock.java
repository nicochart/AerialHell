package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Block.AerialHellChestBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CoreProtectedChestBlock extends AerialHellChestBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.of("core_protected");
	
	public CoreProtectedChestBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.get(CORE_PROTECTED);
	}

	@Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		return (isProtected(state) && !player.isCreative()) ? ActionResult.SUCCESS : super.onUse(state, world, pos, player, hit);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
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
