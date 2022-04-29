package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class AerialHellTallGrassBlock extends TallGrassBlock
{
	public AerialHellTallGrassBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
	{
	    DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocksAndItems.STELLAR_FERN.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.STELLAR_TALL_FERN.get();
	    	placePlant(worldIn, pos, tall_plant);
	    }
	    else if (this == AerialHellBlocksAndItems.BRAMBLES.get()) {}
	    else
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get();
	    	placePlant(worldIn, pos, tall_plant);
	    }
	}
	
	protected void placePlant(ServerWorld worldIn, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up()))
	    {
	         plantIn.placeAt(worldIn, pos, 2);
	    }
	}
}
