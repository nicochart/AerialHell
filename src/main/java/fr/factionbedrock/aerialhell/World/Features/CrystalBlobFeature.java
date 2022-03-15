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

public class CrystalBlobFeature extends Feature<NoFeatureConfig>
{
	public CrystalBlobFeature(Codec<NoFeatureConfig> p_i231956_1_)
	{
		super(p_i231956_1_);
	}

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

		        for(int i = 0; i < 1500; ++i)
		        {
		        	BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		            if (reader.getBlockState(blockpos).isAir(reader, blockpos))
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
	
			            if (j == 1)
			            {
			            	reader.setBlockState(blockpos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState(), 2);
			            }
		            }
		        }

		    return true;
		    }
		}
	}
}
