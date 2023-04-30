package fr.factionbedrock.aerialhell.Block.Plants;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
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
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state)
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
	
	protected void placePlant(ServerLevel worldIn, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(worldIn, plantIn.defaultBlockState(), pos, 2); //TODO : sûrement pas defaultBlockState(), avant il n'y avait pas ce 2ème paramètre
	    }
	}
}
