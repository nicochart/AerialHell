package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CrystallizedFireFeature extends Feature<NoFeatureConfig>
{
	public CrystallizedFireFeature(Codec<NoFeatureConfig> codec) {super(codec);}
	
	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if (!reader.isAirBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlockState(pos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState(), 2);
		    	
		    	int neighbor_number = 4 + rand.nextInt(3);
		    	for(int i = 0; i < neighbor_number; ++i)
		        {
		    		BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
		    		int j = 0;
		    		if (reader.getBlockState(blockpos).isIn(Blocks.AIR))
		    		{
		    			while (reader.getBlockState(blockpos.down()).isIn(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.down();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlockState(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState(), 2);
			    		}
		    		}
		    		else
		    		{
		    			while (!reader.getBlockState(blockpos).isIn(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.up();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos).isIn(Blocks.AIR) && reader.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlockState(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState(), 2);
			    		}
		    		}
		        }

		    return true;
		    }
		}
	}
}
