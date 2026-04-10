package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AerialHellFungusBlock extends FungusBlock
{
	public AerialHellFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> fungusFeature, Block floorBlock, BlockBehaviour.Properties settings) {super(fungusFeature, floorBlock, settings);}

	@Override protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
	{
		return floor.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) || super.mayPlaceOn(floor, world, pos);
	}
}
