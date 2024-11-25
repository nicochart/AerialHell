package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ShadowPlantBlock extends DeadBushBlock
{
	public ShadowPlantBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, false));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
	{
		Block block = floor.getBlock();
	 	return block == AerialHellBlocks.SHADOW_GRASS_BLOCK;
	}
}
