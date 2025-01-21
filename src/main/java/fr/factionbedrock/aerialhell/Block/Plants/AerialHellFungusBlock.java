package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AerialHellFungusBlock extends FungusBlock
{
	public AerialHellFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> fungusFeature, Block floorBlock, Properties properties) {super(fungusFeature, floorBlock, properties);}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) || super.mayPlaceOn(state, worldIn, pos);
	}
}
