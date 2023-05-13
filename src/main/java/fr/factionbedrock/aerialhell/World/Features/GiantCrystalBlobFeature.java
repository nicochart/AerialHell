package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantCrystalBlobFeature extends Feature<NoneFeatureConfiguration>
{
	public GiantCrystalBlobFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random();
		if (!reader.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT))
		    {
		    	return false;
		    }
		    else
		    {
		    	reader.setBlock(pos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().defaultBlockState(), 2);
		    	
		    	BlockPos blockpos;
		        for(int i = 0; i < 3000; ++i)
		        {
		        	
		        	if (i < 1000)
		        	{
		        		blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else if (i < 1500)
		        	{
		        		blockpos = pos.offset(rand.nextInt(7) - rand.nextInt(7), 6 + rand.nextInt(6), rand.nextInt(7) - rand.nextInt(7));
		        	}
		        	else
		        	{
		        		blockpos = pos.offset(rand.nextInt(9) - rand.nextInt(9), rand.nextInt(4) - rand.nextInt(8), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).is(Blocks.AIR) || reader.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT) || reader.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_STONE))
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.relative(direction)).is(AerialHellBlocksAndItems.CRYSTAL_BLOCK.get()))
				            {
				            	++j;
				            }
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1 || j == 2 && rand.nextInt(25) == 0)
			            {
			            	reader.setBlock(blockpos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().defaultBlockState(), 2);
			            }
		            }
		        }
		        for(int i = 0; i < 100; ++i)
		        {
		        	blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	if (reader.getBlockState(blockpos).is(Blocks.AIR) && (reader.getBlockState(blockpos.below()).is(AerialHellBlocksAndItems.CRYSTAL_BLOCK.get())))
		        	{
		        		reader.setBlock(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
		        	}
		        }

		    return true;
		    }
		}
	}
}
