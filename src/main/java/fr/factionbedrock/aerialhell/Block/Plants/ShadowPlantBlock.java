package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class ShadowPlantBlock extends DryVegetationBlock
{
	public ShadowPlantBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}

	@Override
	protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
	{
		Block block = floor.getBlock();
	 	return block == AerialHellBlocks.SHADOW_GRASS_BLOCK;
	}
}
