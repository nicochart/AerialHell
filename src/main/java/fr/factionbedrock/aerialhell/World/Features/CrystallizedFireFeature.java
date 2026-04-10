package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CrystallizedFireFeature extends Feature<NoneFeatureConfiguration> implements DungeonSensitiveFeatureCheck
{
	public CrystallizedFireFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTALLIZED_FIRE_LIST;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel world = context.level(); RandomSource rand = context.random();
		if (!world.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = world.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(context)) {return false;}
		    else
		    {
				place(pos, rand, world);

		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, RandomSource rand, WorldGenLevel world)
	{
		world.setBlock(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.defaultBlockState(), 2);

		int neighbor_number = 4 + rand.nextInt(3);
		for(int i = 0; i < neighbor_number; ++i)
		{
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
			int j = 0;
			if (world.getBlockState(blockpos).is(Blocks.AIR))
			{
				while (world.getBlockState(blockpos.below()).is(Blocks.AIR) && j < 10)
				{
					blockpos = blockpos.below();
					j++;
				}
				if (world.getBlockState(blockpos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
				{
					world.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.defaultBlockState(), 2);
				}
			}
			else
			{
				while (!world.getBlockState(blockpos).is(Blocks.AIR) && j < 10)
				{
					blockpos = blockpos.above();
					j++;
				}
				if (world.getBlockState(blockpos).is(Blocks.AIR) && world.getBlockState(blockpos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
				{
					world.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.defaultBlockState(), 2);
				}
			}
		}
	}
}
