package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StellarStoneCrystalBlobFeature extends Feature<NoFeatureConfig>
{
	public StellarStoneCrystalBlobFeature(Codec<NoFeatureConfig> p_i231956_1_)
	{
		super(p_i231956_1_);
	}

	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if (!reader.isAirBlock(pos)) {return false;}
		int x,z;
		BlockPos blockpos;
		for (x=-4;x<5;x++)
		{
			for (z=-4;z<5;z++)
			{
				blockpos = pos.add(x, 0, z);
				if (!hasAnyBlockAbove(blockpos, reader)) {return false;}
			}
		}
		BlockState blockstate = reader.getBlockState(pos.down());
		
	    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
	    else
	    {
	    	reader.setBlockState(pos, AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get().getDefaultState(), 2);

	        for(int i = 0; i < 300; ++i)
	        {
	        	blockpos = pos.add(rand.nextInt(2) - rand.nextInt(2), rand.nextInt(5), rand.nextInt(2) - rand.nextInt(2)); //55855

	            if (reader.getBlockState(blockpos).isAir(reader, blockpos))
	            {
	            	int j = 0;

		            for(Direction direction : Direction.values())
		            {
			            if (reader.getBlockState(blockpos.offset(direction)).isIn(AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get()))
			            {
			            	++j;
			            }
	
			            if (j > 1) {break;}
		            }

		            if (j == 1)
		            {
		            	reader.setBlockState(blockpos, AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get().getDefaultState(), 2);
		            }
	            }
	        }

	    return true;
	    }
	}
	
	private boolean hasAnyBlockAbove(BlockPos pos, ISeedReader reader)
	{
		for (BlockPos blockpos1 = pos.up(); blockpos1.getY() < 250; blockpos1 = blockpos1.up())
		{
			if (!reader.isAirBlock(blockpos1)) {return true;}
		}
		return false;
	}
}
