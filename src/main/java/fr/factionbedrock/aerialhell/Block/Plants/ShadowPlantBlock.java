package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.StateDefinition;

public class ShadowPlantBlock extends DryVegetationBlock
{
	public ShadowPlantBlock(Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.defaultBlockState().setValue(AerialHellStateProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellStateProperties.SHIFTED_RENDER);}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		Block block = state.getBlock();
	 	return block == AerialHellBlocks.SHADOW_GRASS_BLOCK.get();
	}
}
