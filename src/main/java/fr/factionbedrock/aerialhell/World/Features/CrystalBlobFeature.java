package fr.factionbedrock.aerialhell.World.Features;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalBlobFeature(Holder<BlockStateProvider> crystalStateProvider) implements Feature, DungeonSensitiveFeatureCheck
{
	public static final MapCodec<CrystalBlobFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				BlockStateProvider.CODEC.fieldOf("crystal_state_provider").forGetter(CrystalBlobFeature::crystalStateProvider)
			)
			.apply(i, CrystalBlobFeature::new)
	);

	@Override public MapCodec<CrystalBlobFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTAL_BLOB_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		if (!level.isEmptyBlock(origin)) {return false;}
		else
		{
			BlockState blockstate = level.getBlockState(origin.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
		    else
		    {
				this.place(origin, random, level, this.crystalStateProvider.value());
				return true;
		    }
		}
	}

	private void place(BlockPos pos, RandomSource rand, WorldGenLevel level, BlockStateProvider blockProvider)
	{
		level.setBlock(pos, blockProvider.getState(level, rand, pos), 2);

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

				if (j == 1) {level.setBlock(blockpos, blockProvider.getState(level, rand, blockpos), 2);}
			}
		}
	}
}
