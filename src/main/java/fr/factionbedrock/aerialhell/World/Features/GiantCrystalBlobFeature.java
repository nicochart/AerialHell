package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GiantCrystalBlobFeature extends Feature<NoFeatureConfig>
{
	public GiantCrystalBlobFeature(Codec<NoFeatureConfig> codec) {super(codec);}
	
	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if (!reader.isAirBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT))
		    {
		    	return false;
		    }
		    else
		    {
		    	reader.setBlockState(pos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState(), 2);
		    	
		    	BlockPos blockpos;
		        for(int i = 0; i < 3000; ++i)
		        {
		        	
		        	if (i < 1000)
		        	{
		        		blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else if (i < 1500)
		        	{
		        		blockpos = pos.add(rand.nextInt(7) - rand.nextInt(7), 6 + rand.nextInt(6), rand.nextInt(7) - rand.nextInt(7));
		        	}
		        	else
		        	{
		        		blockpos = pos.add(rand.nextInt(9) - rand.nextInt(9), rand.nextInt(4) - rand.nextInt(8), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).isIn(Blocks.AIR) || reader.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_DIRT) || reader.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_STONE))
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.offset(direction)).isIn(AerialHellBlocksAndItems.CRYSTAL_BLOCK.get()))
				            {
				            	++j;
				            }
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1 || j == 2 && rand.nextInt(25) == 0)
			            {
			            	reader.setBlockState(blockpos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState(), 2);
			            }
		            }
		        }
		        for(int i = 0; i < 100; ++i)
		        {
		        	blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	if (reader.getBlockState(blockpos).isIn(Blocks.AIR) && (reader.getBlockState(blockpos.down()).isIn(AerialHellBlocksAndItems.CRYSTAL_BLOCK.get())))
		        	{
		        		reader.setBlockState(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState(), 2);
		        	}
		        }

		    return true;
		    }
		}
	}
}
