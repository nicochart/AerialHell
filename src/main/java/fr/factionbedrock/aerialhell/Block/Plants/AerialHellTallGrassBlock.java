package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class AerialHellTallGrassBlock extends ShortPlantBlock
{
	public AerialHellTallGrassBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		TallPlantBlock tall_plant;
	    if (this == AerialHellBlocks.STELLAR_FERN)
	    {
	    	tall_plant = (TallPlantBlock) AerialHellBlocks.STELLAR_TALL_FERN;
	    	placePlant(world, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.BLUISH_FERN)
		{
			tall_plant = (TallPlantBlock) AerialHellBlocks.TALL_BLUISH_FERN;
			placePlant(world, pos, tall_plant);
		}
		else if (this == AerialHellBlocks.POLYCHROME_FERN)
		{
			tall_plant = (TallPlantBlock) AerialHellBlocks.TALL_POLYCHROME_FERN;
			placePlant(world, pos, tall_plant);
		}
	    else if (this == AerialHellBlocks.BRAMBLES) {}
	    else if (this == AerialHellBlocks.STELLAR_GRASS)
	    {
	    	tall_plant = (TallPlantBlock) AerialHellBlocks.STELLAR_TALL_GRASS;
	    	placePlant(world, pos, tall_plant);
	    }
	}

	protected void placePlant(ServerWorld world, BlockPos pos, TallPlantBlock plantIn)
	{
		if (plantIn.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up()))
	    {
	         plantIn.placeAt(world, plantIn.getDefaultState(), pos, 2);
	    }
	}
}
