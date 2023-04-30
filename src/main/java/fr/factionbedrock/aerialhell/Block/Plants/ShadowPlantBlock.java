package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class ShadowPlantBlock extends DeadBushBlock
{
	public ShadowPlantBlock(Properties builder)
	{
		super(builder);
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		Block block = state.getBlock();
	 	return block == AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get();
	}
}
