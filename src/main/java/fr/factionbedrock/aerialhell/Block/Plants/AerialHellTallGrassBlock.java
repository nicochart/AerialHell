package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellTallGrassBlock extends TallGrassBlock
{
	public AerialHellTallGrassBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state)
	{
		DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocks.STELLAR_FERN)
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_FERN;
	    	placePlant(world, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.BLUISH_FERN)
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_BLUISH_FERN;
			placePlant(world, pos, tall_plant);
		}
		else if (this == AerialHellBlocks.POLYCHROME_FERN)
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_POLYCHROME_FERN;
			placePlant(world, pos, tall_plant);
		}
	    else if (this == AerialHellBlocks.BRAMBLES) {}
	    else if (this == AerialHellBlocks.STELLAR_GRASS)
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_GRASS;
	    	placePlant(world, pos, tall_plant);
	    }
	}

	protected void placePlant(ServerLevel world, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(world, plantIn.defaultBlockState(), pos, 2);
	    }
	}
}
