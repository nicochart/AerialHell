package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;

public class CoreProtectedChestBlock extends ChestBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedChestBlock(Properties builder, Supplier<TileEntityType<? extends ChestTileEntity>> tileEntityTypeIn)
	{
		super(builder, tileEntityTypeIn);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	public void setProtected(boolean protect)
	{
		this.setDefaultState(this.stateContainer.getBaseState().with(CORE_PROTECTED, protect));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.get(CORE_PROTECTED);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getExplosionResistance(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.getBlock().getExplosionResistance();
    }
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(CORE_PROTECTED);
	}
	
	@Override
	public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos)
	{
		float f = state.getBlockHardness(worldIn, pos);
	    if (f == -1.0F || isProtected(state))
	    {
	         return 0.0F;
	    }
	    else
	    {
	         int i = net.minecraftforge.common.ForgeHooks.canHarvestBlock(state, player, worldIn, pos) ? 30 : 100;
	         return player.getDigSpeed(state, pos) / f / (float)i;
	    }
	}
}
