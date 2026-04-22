package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.List;

public class RockFeature extends Feature<NoneFeatureConfiguration> implements DungeonSensitiveFeatureCheck
{
	WeightedStateProvider blockStateProvider;
	public RockFeature(Codec<NoneFeatureConfiguration> codec, WeightedStateProvider bsProvider) {super(codec); this.blockStateProvider = bsProvider;}

	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.MOSSY_STELLAR_COBBLESTONE_ROCK_LIST;}

	protected BlockState randomState(WorldGenLevel level, RandomSource rand, BlockPos pos) {return blockStateProvider.getState(level, rand, pos);}
	protected void placeBlocks(WorldGenLevel level, BlockPos pos, BlockState state, int number, Direction direction) {for (int d=0;d<number;d++) {level.setBlock(pos.relative(direction, d), state, 2);}}
	protected boolean canGenerateAtPos(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos placementPos) {return hasSupportToGenerate(context.level(), placementPos) && this.isDungeonSensitiveValid(context);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel level = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		BlockPos placementPos = findPosForPlacement(level, pos);
		if (!canGenerateAtPos(context, placementPos)) {return false;}
		
		placeBlocks(level, placementPos, randomState(level, rand, placementPos), 3, Direction.UP); placeBlocks(level, placementPos.relative(Direction.DOWN), randomState(level, rand, placementPos), 1 + rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(level, placementPos.relative(Direction.NORTH), randomState(level, rand, placementPos), 2, Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(2), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.EAST), randomState(level, rand, placementPos), 2, Direction.UP); placeBlocks(level, placementPos.relative(Direction.EAST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH), randomState(level, rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.WEST), randomState(level, rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(level, placementPos.relative(Direction.WEST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.EAST), randomState(level, rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.WEST), randomState(level, rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.NORTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.EAST), randomState(level, rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.WEST), randomState(level, rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(level, placementPos.relative(Direction.SOUTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(level, rand, placementPos), rand.nextInt(3), Direction.DOWN);
		
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