package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.function.Supplier;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class CoreProtectedChestBlock extends ChestBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedChestBlock(Properties builder, Supplier<BlockEntityType<? extends ChestBlockEntity>> tileEntityTypeIn)
	{
		super(builder, tileEntityTypeIn);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}
	
	public void setProtected(boolean protect)
	{
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, protect));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		return (isProtected(state) && !player.isCreative()) ? InteractionResult.SUCCESS : super.use(state, worldIn, pos, player, handIn, hit);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter worldIn, BlockPos pos)
	{
		float f = state.getDestroySpeed(worldIn, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops(state, player) ? 30 : 100;
			return player.getDigSpeed(state, pos) / f / (float)i;
		}
	}
}
