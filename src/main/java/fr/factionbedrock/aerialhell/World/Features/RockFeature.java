package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record RockFeature(Holder<BlockStateProvider> stateProvider) implements Feature, DungeonSensitiveFeatureCheck
{
	public static final MapCodec<RockFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(RockFeature::stateProvider)
			)
			.apply(i, RockFeature::new)
	);

	@Override public MapCodec<RockFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.MOSSY_STELLAR_COBBLESTONE_ROCK_LIST;}

	protected BlockState randomState(WorldGenLevel level, RandomSource rand, BlockPos pos) {return this.stateProvider.value().getState(level, rand, pos);}
	protected void placeBlocks(WorldGenLevel level, BlockPos pos, BlockState state, int number, Direction direction) {for (int d=0;d<number;d++) {level.setBlock(pos.relative(direction, d), state, 2);}}
	protected boolean canGenerateAtPos(WorldGenLevel level, BlockPos origin, BlockPos placementPos) {return hasSupportToGenerate(level, placementPos) && this.isDungeonSensitiveValid(level, origin);}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		BlockPos placementPos = findPosForPlacement(level, origin);
		if (!canGenerateAtPos(level, origin, placementPos)) {return false;}

		placeBlocks(level, placementPos, randomState(level, random, placementPos), 3, Direction.UP); placeBlocks(level, placementPos.relative(Direction.DOWN), randomState(level, random, placementPos), 1 + random.nextInt(2), Direction.DOWN);

		placeBlocks(level, placementPos.relative(Direction.NORTH), randomState(level, random, placementPos), 2, Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(2), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.EAST), randomState(level, random, placementPos), 2, Direction.UP); placeBlocks(level, placementPos.relative(Direction.EAST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH), randomState(level, random, placementPos), random.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.WEST), randomState(level, random, placementPos), random.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(level, placementPos.relative(Direction.WEST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(2), Direction.DOWN);

		placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.EAST), randomState(level, random, placementPos), random.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.WEST), randomState(level, random, placementPos), random.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.EAST), randomState(level, random, placementPos), random.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.WEST), randomState(level, random, placementPos), random.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(level, random, placementPos), random.nextInt(3), Direction.DOWN);

		return true;
	}

	protected BlockPos findPosForPlacement(WorldGenLevel level, BlockPos originalPos)
	{
		int x = originalPos.getX(), y=250, z=originalPos.getZ();
		int ymin = 50;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		mutablePos.set(new BlockPos(x, y, z));
		while (y > ymin)
		{
			if (hasSupportToGenerate(level, mutablePos)) {return mutablePos;}
			else {y--; mutablePos.set(new BlockPos(x, y, z));}
		}
		return originalPos;
	}

	private boolean hasSupportToGenerate(WorldGenLevel level, BlockPos pos)
	{
		if (level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
}
