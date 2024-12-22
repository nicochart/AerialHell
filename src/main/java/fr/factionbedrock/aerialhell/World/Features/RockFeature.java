package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class RockFeature extends Feature<DefaultFeatureConfig>
{
	WeightedBlockStateProvider blockStateProvider;
	public RockFeature(Codec<DefaultFeatureConfig> codec, WeightedBlockStateProvider bsProvider) {super(codec); this.blockStateProvider = bsProvider;}
	
	protected BlockState randomState(Random rand, BlockPos pos) {return blockStateProvider.get(rand, pos);}
	protected void placeBlocks(StructureWorldAccess reader, BlockPos pos, BlockState state, int number, Direction direction) {for (int d=0;d<number;d++) {reader.setBlockState(pos.offset(direction, d), state, 2);}}
	protected boolean canGenerateAtPos(FeatureContext<DefaultFeatureConfig> context, BlockPos placementPos) {return hasSupportToGenerate(context.getWorld(), placementPos) && !(FeatureHelper.isFeatureGeneratingNextToDungeon(context));}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom(); ChunkGenerator generator = context.getGenerator();
		BlockPos placementPos = findPosForPlacement(reader, pos);
		if (!canGenerateAtPos(context, placementPos)) {return false;}
		
		placeBlocks(reader, placementPos, randomState(rand, placementPos), 3, Direction.UP); placeBlocks(reader, placementPos.offset(Direction.DOWN), randomState(rand, placementPos), 1 + rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(reader, placementPos.offset(Direction.NORTH), randomState(rand, placementPos), 2, Direction.UP); placeBlocks(reader, placementPos.offset(Direction.NORTH).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(2), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.EAST), randomState(rand, placementPos), 2, Direction.UP); placeBlocks(reader, placementPos.offset(Direction.EAST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.SOUTH), randomState(rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(reader, placementPos.offset(Direction.SOUTH).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.WEST), randomState(rand, placementPos), rand.nextInt(4) < 3 ? 2 : 1, Direction.UP); placeBlocks(reader, placementPos.offset(Direction.WEST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(2), Direction.DOWN);
		
		placeBlocks(reader, placementPos.offset(Direction.NORTH).offset(Direction.EAST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.offset(Direction.NORTH).offset(Direction.EAST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.NORTH).offset(Direction.WEST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.offset(Direction.NORTH).offset(Direction.WEST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.SOUTH).offset(Direction.EAST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.offset(Direction.SOUTH).offset(Direction.EAST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		placeBlocks(reader, placementPos.offset(Direction.SOUTH).offset(Direction.WEST), randomState(rand, placementPos), rand.nextInt(2), Direction.UP); placeBlocks(reader, placementPos.offset(Direction.SOUTH).offset(Direction.WEST).offset(Direction.DOWN), randomState(rand, placementPos), rand.nextInt(3), Direction.DOWN);
		
		return true;
	}
	
	protected BlockPos findPosForPlacement(StructureWorldAccess reader, BlockPos originalPos)
	{
		int x = originalPos.getX(), y=250, z=originalPos.getZ();
		int ymin = 50;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		mutablePos.set(new BlockPos(x, y, z));
		while (y > ymin)
		{
			if (hasSupportToGenerate(reader, mutablePos)) {return mutablePos;}
			else {y--; mutablePos.set(new BlockPos(x, y, z));}
		}
		return originalPos;
	}
	
	private boolean hasSupportToGenerate(StructureWorldAccess reader, BlockPos pos)
	{
		if (reader.isAir(pos) && reader.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
}