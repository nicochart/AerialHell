package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RockFeature extends Feature<NoFeatureConfig>
{
	WeightedBlockStateProvider blockStateProvider;
	public RockFeature(Codec<NoFeatureConfig> codec, WeightedBlockStateProvider bsProvider) {super(codec); this.blockStateProvider = bsProvider;}
	
	protected BlockState randomState(Random rand, BlockPos pos) {return blockStateProvider.getBlockState(rand, pos);}
	protected void placeBlocks(ISeedReader reader, BlockPos pos, BlockState state, int number, Direction direction) {for (int d=0;d<number;d++) {reader.setBlockState(pos.offset(direction, d), state, 2);}}
	protected boolean canGenerateAtPos(ISeedReader reader, BlockPos pos) {return hasSupportToGenerate(reader, pos) && !generatesInAnyDungeon(reader, pos);}
	
	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		BlockPos placementPos = findPosForPlacement(reader, pos);
		if (!canGenerateAtPos(reader, placementPos)) {return false;}
		
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
	
	protected BlockPos findPosForPlacement(ISeedReader reader, BlockPos originalPos)
	{
		int x = originalPos.getX(), y=250, z=originalPos.getZ();
		int ymin = 50;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		mutablePos.setPos(new BlockPos(x, y, z));
		while (y > ymin)
		{
			if (hasSupportToGenerate(reader, mutablePos)) {return mutablePos;}
			else {y--; mutablePos.setPos(new BlockPos(x, y, z));}
		}
		return originalPos;
	}
	
	private boolean hasSupportToGenerate(ISeedReader reader, BlockPos pos)
	{
		if (reader.isAirBlock(pos) && reader.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
	
	protected boolean generatesInAnyDungeon(ISeedReader reader, BlockPos pos)
	{
		return(reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()).findAny().isPresent() ||
    		   reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()).findAny().isPresent() ||
    		   reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()).findAny().isPresent());
	}
}