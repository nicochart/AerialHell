package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ShadowPlantBlock extends DeadBushBlock
{
	public ShadowPlantBlock(Properties builder)
	{
		super(builder);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		Block block = state.getBlock();
	 	return block == AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get();
	}
}
