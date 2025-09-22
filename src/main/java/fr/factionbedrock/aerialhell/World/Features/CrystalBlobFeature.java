package fr.factionbedrock.aerialhell.World.Features;

import java.util.List;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.World.Features.Config.CrystalBlobConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CrystalBlobFeature extends AerialHellFeature<CrystalBlobConfig>
{
	public CrystalBlobFeature(Codec<CrystalBlobConfig> codec) {super(codec);}

	@Override protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTAL_BLOB_LIST;}

	@Override public boolean place(FeaturePlaceContext<CrystalBlobConfig> context)
	{
		if (!super.place(context)) {return false;}
		BlockStateProvider blockProvider = context.config().crystalStateProvider();
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random();
		if (!reader.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlock(pos, blockProvider.getState(rand, pos), 2);

		        for(int i = 0; i < 1700; ++i)
		        {
		        	BlockPos blockpos;
		        	if (i < 1400)
		        	{
		        		blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else
		        	{
		        		blockpos = pos.offset(rand.nextInt(9) - rand.nextInt(9), - rand.nextInt(3), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).isAir())
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.relative(direction)).is(AerialHellTags.Blocks.NATURAL_CRYSTAL_BLOCK)) {++j;}
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1) {reader.setBlock(blockpos, blockProvider.getState(rand, blockpos), 2);}
		            }
		        }

		    return true;
		    }
		}
	}
}
