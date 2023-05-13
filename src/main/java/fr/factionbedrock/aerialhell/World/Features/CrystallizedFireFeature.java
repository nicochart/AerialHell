package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CrystallizedFireFeature extends Feature<NoneFeatureConfiguration>
{
	public CrystallizedFireFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random();
		if (!reader.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlock(pos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
		    	
		    	int neighbor_number = 4 + rand.nextInt(3);
		    	for(int i = 0; i < neighbor_number; ++i)
		        {
		    		BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
		    		int j = 0;
		    		if (reader.getBlockState(blockpos).is(Blocks.AIR))
		    		{
		    			while (reader.getBlockState(blockpos.below()).is(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.below();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlock(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
			    		}
		    		}
		    		else
		    		{
		    			while (!reader.getBlockState(blockpos).is(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.above();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos).is(Blocks.AIR) && reader.getBlockState(blockpos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlock(blockpos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
			    		}
		    		}
		        }

		    return true;
		    }
		}
	}
}
