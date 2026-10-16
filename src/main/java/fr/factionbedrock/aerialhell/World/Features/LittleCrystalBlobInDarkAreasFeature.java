package fr.factionbedrock.aerialhell.World.Features;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
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

public record LittleCrystalBlobInDarkAreasFeature(Holder<BlockStateProvider> crystalStateProvider) implements Feature, DungeonSensitiveFeatureCheck
{
	public static final MapCodec<LittleCrystalBlobInDarkAreasFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				BlockStateProvider.CODEC.fieldOf("crystal_state_provider").forGetter(LittleCrystalBlobInDarkAreasFeature::crystalStateProvider)
			)
			.apply(i, LittleCrystalBlobInDarkAreasFeature::new)
	);

	@Override public MapCodec<LittleCrystalBlobInDarkAreasFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.LITTLE_CRYSTAL_BLOB_IN_DARK_AREAS_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		BlockStateProvider blockProvider = this.crystalStateProvider.value();
		int x = origin.getX(), y=10, z=origin.getZ();
		int ymax = 160;
		BlockPos blockpos;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		mutablePos.set(new BlockPos(x, y, z));
		boolean hasSupport = false;
		while (!hasSupport && y < ymax)
		{
			if (hasSupportToGenerate(mutablePos, level)) {hasSupport = true;}
			else {y++; mutablePos.set(new BlockPos(x, y, z));}
		}
		if (y==ymax) {return false;}
		if (!squareHasRoof(mutablePos, level)) {return false;}
		origin = mutablePos;
		
		if (random.nextInt(160) < y) {return false;}
		if (!this.isDungeonSensitiveValid(level, origin)) {return false;}

		level.setBlock(origin, blockProvider.getState(level, random, origin), 2);
        for(int i = 0; i < 300; ++i)
        {
        	blockpos = origin.offset(random.nextInt(2) - random.nextInt(2), random.nextInt(5), random.nextInt(2) - random.nextInt(2)); //55855

            if (level.isEmptyBlock(blockpos))
            {
            	int j = 0;

	            for(Direction direction : Direction.values())
	            {
		            if (level.getBlockState(blockpos.relative(direction)).is(AerialHellTags.Blocks.NATURAL_CRYSTAL_BLOCK)) {++j;}

		            if (j > 1) {break;}
	            }

	            if (j == 1) {level.setBlock(blockpos, blockProvider.getState(level, random, blockpos), 2);}
            }
        }
	    return true;
	}
	
	private boolean hasSupportToGenerate(BlockPos pos, WorldGenLevel reader)
	{
		BlockState blockstateDown = reader.getBlockState(pos.below());
		if (isValidFloorState(blockstateDown) && hasAirColumnAbove(pos, reader, 4)) {return true;}
		else {return false;}
	}
		
	private boolean isValidFloorState(BlockState state)
	{
		return state.is(AerialHellTags.Blocks.STELLAR_DIRT) || state.is(AerialHellBlocks.SLIPPERY_SAND.get());
	}
	
	private boolean squareHasRoof(BlockPos pos, WorldGenLevel reader)
	{
		int x,z;
		BlockPos blockpos;
		for (x=-4;x<5;x++)
		{
			for (z=-4;z<5;z++)
			{
				blockpos = pos.offset(x, 0, z);
				if (!hasAnyBlockAbove(blockpos, reader)) {return false;}
			}
		}
		return true;
	}
	
	private boolean hasAnyBlockAbove(BlockPos pos, WorldGenLevel reader)
	{
		for (BlockPos blockpos = pos.above(); blockpos.getY() < 250; blockpos = blockpos.above())
		{
			if (!reader.isEmptyBlock(blockpos)) {return true;}
		}
		return false;
	}
	
	private boolean hasAirColumnAbove(BlockPos pos, WorldGenLevel reader, int dy)
	{
		for (BlockPos blockpos = pos.above(); blockpos.getY() < pos.getY()+dy; blockpos = blockpos.above())
		{
			if (!reader.isEmptyBlock(blockpos)) {return false;}
		}
		return true;
	}
}
