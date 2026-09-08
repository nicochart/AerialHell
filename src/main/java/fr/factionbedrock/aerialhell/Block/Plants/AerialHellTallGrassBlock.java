package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
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
	
	@Override public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
	    DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocks.STELLAR_FERN.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_FERN.get();
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.BLUISH_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_BLUISH_FERN.get();
			placePlant(level, pos, tall_plant);
		}
		else if (this == AerialHellBlocks.POLYCHROME_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_POLYCHROME_FERN.get();
			placePlant(level, pos, tall_plant);
		}
	    else if (this == AerialHellBlocks.STELLAR_GRASS.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_GRASS.get();
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.STELLAR_CLOVERS.get())
		{
			BonemealableBlock.findSpreadableNeighbourPos(level, pos, state).ifPresent((blockPos) -> level.setBlockAndUpdate(blockPos, this.defaultBlockState()));
		}
	}

	@Override public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		if (this == AerialHellBlocks.STELLAR_CLOVERS.get())
		{
			return BonemealableBlock.hasSpreadableNeighbourPos(level, pos, state);
		}
		return super.isValidBonemealTarget(level, pos, state);
	}

	protected void placePlant(ServerLevel worldIn, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(worldIn, plantIn.defaultBlockState(), pos, 2);
	    }
	}
}
