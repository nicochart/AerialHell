package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class AerialHellDeadBushBlock extends DeadBushBlock
{
	public AerialHellDeadBushBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
	{
		Block block = floor.getBlock();
		return block == AerialHellBlocks.SLIPPERY_SAND;
	}
}
