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
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CrystalBlobFeature extends Feature<CrystalBlobConfig> implements DungeonSensitiveFeatureCheck
{
	public CrystalBlobFeature(Codec<CrystalBlobConfig> codec) {super(codec);}

	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTAL_BLOB_LIST;}

	@Override public boolean place(FeaturePlaceContext<CrystalBlobConfig> context)
	{
		BlockStateProvider blockProvider = context.config().crystalStateProvider();
		BlockPos pos = context.origin(); WorldGenLevel level = context.level(); RandomSource rand = context.random();
		if (!level.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = level.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(context)) {return false;}
		    else
		    {
				this.place(pos, rand, level, blockProvider);
				return true;
		    }
		}
	}

	private void place(BlockPos pos, RandomSource rand, WorldGenLevel level, BlockStateProvider blockProvider)
	{
		level.setBlock(pos, blockProvider.getState(rand, pos), 2);

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
			if (level.getBlockState(blockpos).isAir())
			{
				int j = 0;

				for(Direction direction : Direction.values())
				{
					if (level.getBlockState(blockpos.relative(direction)).is(AerialHellTags.Blocks.NATURAL_CRYSTAL_BLOCK)) {++j;}

					if (j > 1) {break;}
				}

				if (j == 1) {level.setBlock(blockpos, blockProvider.getState(rand, blockpos), 2);}
			}
		}
	}
}
