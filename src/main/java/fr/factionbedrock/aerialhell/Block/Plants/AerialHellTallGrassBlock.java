package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class AerialHellTallGrassBlock extends TallGrassBlock
{
	public AerialHellTallGrassBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state)
	{
	    DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocks.STELLAR_FERN.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_FERN.get();
	    	placePlant(worldIn, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.BLUISH_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_BLUISH_FERN.get();
			placePlant(worldIn, pos, tall_plant);
		}
		else if (this == AerialHellBlocks.POLYCHROME_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_POLYCHROME_FERN.get();
			placePlant(worldIn, pos, tall_plant);
		}
	    else if (this == AerialHellBlocks.BRAMBLES.get()) {}
	    else if (this == AerialHellBlocks.STELLAR_GRASS.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_GRASS.get();
	    	placePlant(worldIn, pos, tall_plant);
	    }
	}

	protected void placePlant(ServerLevel worldIn, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(worldIn, plantIn.defaultBlockState(), pos, 2);
	    }
	}
}
