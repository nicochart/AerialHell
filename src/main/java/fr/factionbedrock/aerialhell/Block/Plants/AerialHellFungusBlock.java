package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherFungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AerialHellFungusBlock extends NetherFungusBlock
{
	public AerialHellFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> fungusFeature, Block requiredBlock, TagKey<Block> supportBlocks, Properties properties) {super(fungusFeature, requiredBlock, supportBlocks, properties);}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) || super.mayPlaceOn(state, worldIn, pos);
	}
}
