package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import javax.annotation.Nullable;
import java.util.List;

public record SingleBlockNeedingSupportFeature(int maxTries, SimpleStateProvider support, Holder<BlockStateProvider> block) implements Feature, DungeonSensitiveFeatureCheck
{
	private static final int MAX_TRIES = 25;
	public static final MapCodec<SingleBlockNeedingSupportFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				ExtraCodecs.POSITIVE_INT.fieldOf("max_tries").forGetter(SingleBlockNeedingSupportFeature::maxTries),
				SimpleStateProvider.CODEC.fieldOf("support").forGetter(SingleBlockNeedingSupportFeature::support),
				BlockStateProvider.CODEC.fieldOf("block").forGetter(SingleBlockNeedingSupportFeature::block)
			)
			.apply(i, SingleBlockNeedingSupportFeature::new)
	);

	@Override public MapCodec<SingleBlockNeedingSupportFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.SINGLE_BLOCK_NEEDING_SUPPORT_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		BlockPos pos = findPosForPlacement(level, random, origin);
		if (pos == null || !this.isDungeonSensitiveValid(level, origin)) {return false;}
		else
		{
			level.setBlock(pos, this.block.value().getState(level, random, pos), 0);
			return true;
		}
	}

	@Nullable protected BlockPos findPosForPlacement(WorldGenLevel level, RandomSource random, BlockPos origin)
	{
		Block support = this.support.getState(level, random, origin).getBlock();
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(origin);

		for (int i=0; i<this.maxTries; i++)
		{
			testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, random, 23, 30);
			while (level.getBlockState(testedPos).is(support)) {testedPos = testedPos.above();}
			if (hasSupportToGenerate(support, level, testedPos)) {return testedPos;}
		}
		return null;
	}

	private boolean hasSupportToGenerate(Block support, WorldGenLevel level, BlockPos pos)
	{
		return level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(support);
	}
}
