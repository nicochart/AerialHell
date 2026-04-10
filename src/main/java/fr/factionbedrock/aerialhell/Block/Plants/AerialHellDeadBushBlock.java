package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellDeadBushBlock extends DryVegetationBlock
{
	public AerialHellDeadBushBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override
	protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
	{
		Block block = floor.getBlock();
		return block == AerialHellBlocks.SLIPPERY_SAND;
	}
}
