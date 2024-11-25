package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AerialHellFungusBlock extends FungusBlock
{
	public AerialHellFungusBlock(RegistryKey<ConfiguredFeature<?, ?>> fungusFeature, Block floorBlock, AbstractBlock.Settings settings) {super(fungusFeature, floorBlock, settings);}

	@Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
	{
		return floor.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) || super.canPlantOnTop(floor, world, pos);
	}
}
