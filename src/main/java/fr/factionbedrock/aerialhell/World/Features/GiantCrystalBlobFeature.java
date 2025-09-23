package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class GiantCrystalBlobFeature extends Feature<NoneFeatureConfiguration> implements DungeonSensitiveFeatureCheck
{
	public GiantCrystalBlobFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GIANT_CRYSTAL_BLOB_LIST;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel level = context.level(); RandomSource rand = context.random();
		if (!level.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = level.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(context)) {return false;}
		    else
		    {
				place(pos, level, rand);
		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, WorldGenLevel level, RandomSource rand)
	{
		level.setBlock(pos, AerialHellBlocks.CRYSTAL_BLOCK.get().defaultBlockState(), 2);

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
			if (level.getBlockState(blockpos).is(Blocks.AIR) || level.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT) || level.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_STONE))
			{
				int j = 0;

				for(Direction direction : Direction.values())
				{
					if (level.getBlockState(blockpos.relative(direction)).is(AerialHellBlocks.CRYSTAL_BLOCK.get()))
					{
						++j;
					}

					if (j > 1) {break;}
				}

				if (j == 1 || j == 2 && rand.nextInt(25) == 0)
				{
					level.setBlock(blockpos, AerialHellBlocks.CRYSTAL_BLOCK.get().defaultBlockState(), 2);
				}
			}
		}
		for(int i = 0; i < 100; ++i)
		{
			blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
			if (level.getBlockState(blockpos).is(Blocks.AIR) && (level.getBlockState(blockpos.below()).is(AerialHellBlocks.CRYSTAL_BLOCK.get())))
			{
				level.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState(), 2);
			}
		}
	}
}
