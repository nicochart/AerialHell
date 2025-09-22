package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class CrystallizedFireFeature extends AerialHellFeature<NoneFeatureConfiguration>
{
	public CrystallizedFireFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTALLIZED_FIRE_LIST;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		if (!super.place(context)) {return false;}
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random();
		if (!reader.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlock(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
		    	
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
		    				reader.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
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
		    				reader.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
			    		}
		    		}
		        }

		    return true;
		    }
		}
	}
}
