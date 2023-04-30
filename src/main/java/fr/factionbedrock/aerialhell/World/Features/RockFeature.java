package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.Random;

public class RockFeature extends Feature<NoneFeatureConfiguration>
{
	WeightedStateProvider blockStateProvider;
	public RockFeature(Codec<NoneFeatureConfiguration> codec, WeightedStateProvider bsProvider) {super(codec); this.blockStateProvider = bsProvider;}
	
	protected BlockState randomState(Random rand, BlockPos pos) {return blockStateProvider.getState(rand, pos);}
	protected void placeBlocks(WorldGenLevel reader, BlockPos pos, BlockState state, int number, Direction direction) {for (int d=0;d<number;d++) {reader.setBlock(pos.relative(direction, d), state, 2);}}
	protected boolean canGenerateAtPos(WorldGenLevel reader, BlockPos pos) {return hasSupportToGenerate(reader, pos) && !(FeatureHelper.generatesInAnyDungeon(reader, pos));}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		BlockPos placementPos = findPosForPlacement(reader, pos);
		if (!canGenerateAtPos(reader, placementPos)) {return false;}
		
		placeBlocks(reader, placementPos, randomState(rand, placementPos), 3, Direction.UP); placeBlocks(reader, placementPos.relative(Direction.DOWN), randomState(rand, placementPos), 1 + rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(reader, placementPos.relative(Direction.NORTH), randomState(rand, placementPos), 2, Direction.UP); placeBlocks(reader, placementPos.relative(Direction.NORTH).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(2), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.EAST), randomState(rand, placementPos), 2, Direction.UP); placeBlocks(reader, placementPos.relative(Direction.EAST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.SOUTH), randomState(rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(reader, placementPos.relative(Direction.SOUTH).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.WEST), randomState(rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(reader, placementPos.relative(Direction.WEST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(reader, placementPos.relative(Direction.NORTH).relative(Direction.EAST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.relative(Direction.NORTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.NORTH).relative(Direction.WEST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.relative(Direction.NORTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.SOUTH).relative(Direction.EAST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.relative(Direction.SOUTH).relative(Direction.EAST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.relative(Direction.SOUTH).relative(Direction.WEST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.relative(Direction.SOUTH).relative(Direction.WEST).relative(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		
		return true;
	}
	
	protected BlockPos findPosForPlacement(WorldGenLevel reader, BlockPos originalPos)
	{
		int x = originalPos.getX(), y=250, z=originalPos.getZ();
		int ymin = 50;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		mutablePos.set(new BlockPos(x, y, z));
		while (y > ymin)
		{
			if (hasSupportToGenerate(reader, mutablePos)) {return mutablePos;}
			else {y--; mutablePos.set(new BlockPos(x, y, z));}
		}
		return originalPos;
	}
	
	private boolean hasSupportToGenerate(WorldGenLevel reader, BlockPos pos)
	{
		if (reader.isEmptyBlock(pos) && reader.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
}