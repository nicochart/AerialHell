package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Block.LargeDeadLogBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public record LargeDeadLogFeature(LargeDeadLogBlock log) implements Feature, DungeonSensitiveFeatureCheck
{
	private static final Codec<LargeDeadLogBlock> LARGE_DEAD_LOG_BLOCK_CODEC = BuiltInRegistries.BLOCK.byNameCodec().comapFlatMap(
		block -> block instanceof LargeDeadLogBlock log ? DataResult.success(log) : DataResult.error(() -> "Block " + BuiltInRegistries.BLOCK.getKey(block) + " is not a large dead log block"),
		log -> log
	);
	public static final MapCodec<LargeDeadLogFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				LARGE_DEAD_LOG_BLOCK_CODEC.fieldOf("log").forGetter(LargeDeadLogFeature::log)
			)
			.apply(i, LargeDeadLogFeature::new)
	);

	@Override public MapCodec<LargeDeadLogFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		BlockPos pos = findPosForPlacement(level, random, origin);
		if (pos == null || !this.isDungeonSensitiveValid(level, origin)) {return false;}
		else
		{
			generate(level, random, pos);
			return true;
		}
	}

	private void generate(WorldGenLevel level, RandomSource random, BlockPos pos)
	{
		Direction generationDirection = random.nextInt(2) == 0 ? Direction.WEST : Direction.NORTH;
		int lenght = 3 + random.nextInt(5);

		BlockPos.MutableBlockPos placementPos = pos.mutable();
		int percentageSupported = percentageOfLogThatHaveSupport(level, pos, generationDirection, lenght);
		if (percentageSupported < 50 && percentageOfLogThatHaveSupport(level, pos.below(), generationDirection, lenght) > percentageSupported) {placementPos.move(Direction.DOWN, 1);}

		for (int i=0; i<lenght; i++)
		{
			place4blocks(level, random, placementPos, generationDirection);
			placementPos.move(generationDirection, 1);
		}
	}

	protected void place4blocks(WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos pos, Direction generationDirection)
	{
		BlockState aboveLeft = this.log.getStateForPlacement(generationDirection.getCounterClockWise(), Half.TOP);
		BlockState aboveRight = this.log.getStateForPlacement(generationDirection.getClockWise(), Half.TOP);
		BlockState belowLeft = this.log.getStateForPlacement(generationDirection.getCounterClockWise(), Half.BOTTOM);
		BlockState belowRight = this.log.getStateForPlacement(generationDirection.getClockWise(), Half.BOTTOM);

		tryPlacingBlock(level, pos, belowRight, random);
		pos.move(generationDirection.getCounterClockWise(), 1);
		tryPlacingBlock(level, pos, belowLeft, random);
		pos.move(Direction.UP, 1);
		tryPlacingBlock(level, pos, aboveLeft, random);
		pos.move(generationDirection.getClockWise(), 1);
		tryPlacingBlock(level, pos, aboveRight, random);
		pos.move(Direction.DOWN, 1);
	}

	@Nullable protected BlockPos findPosForPlacement(WorldGenLevel level, RandomSource random, BlockPos origin)
	{
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(origin);
		if (hasSupportToGenerate(level, origin)) {return origin;}
		int t, i;
		for (t=0; t<10; t++)
		{
			i=0; testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, random, 12, 20);
			while (level.getBlockState(testedPos.below()).isAir() && i++ < 10 && testedPos.getY() > 10) {testedPos = testedPos.below();}
			if (hasSupportToGenerate(level, testedPos)) {return testedPos;}
		}
		return null;
	}

	private int percentageOfLogThatHaveSupport(WorldGenLevel level, BlockPos pos, Direction generationDirection, int lenght)
	{
		int i, count = 0, reachableTotal = lenght*2;
		for (i=0; i<lenght; i++)
		{
			if (hasSupportToGenerate(level, pos.relative(generationDirection, i))) {count++;}
			if (hasSupportToGenerate(level, pos.relative(generationDirection, i).relative(generationDirection.getCounterClockWise(), 1))) {count++;}
		}
		return (int) (100.0 * count / reachableTotal);
	}

	private boolean hasSupportToGenerate(WorldGenLevel level, BlockPos pos)
	{
		return level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
	}

	private void tryPlacingBlock(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockState state, RandomSource rand) {if (isReplaceable(level, pos) || isPossiblyReplaceable(level, pos, rand)) {level.setBlock(pos, state, 0);}}

	private boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
	{
		BlockState previousBlock = level.getBlockState(blockPos);
		return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE);
	}

	private boolean isPossiblyReplaceable(WorldGenLevel level, BlockPos blockPos, RandomSource rand)
	{
		BlockState previousBlock = level.getBlockState(blockPos);
		return (previousBlock.is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && (rand.nextInt(2) == 0)) || ((previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT)) && (rand.nextInt(3) == 0));
	}
}
