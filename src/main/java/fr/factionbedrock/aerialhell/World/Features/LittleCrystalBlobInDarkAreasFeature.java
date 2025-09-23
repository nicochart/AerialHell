package fr.factionbedrock.aerialhell.World.Features;

import java.util.List;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.World.Features.Config.CrystalBlobConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class LittleCrystalBlobInDarkAreasFeature extends Feature<CrystalBlobConfig> implements DungeonSensitiveFeatureCheck
{
	public LittleCrystalBlobInDarkAreasFeature(Codec<CrystalBlobConfig> codec) {super(codec);}

	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.LITTLE_CRYSTAL_BLOB_IN_DARK_AREAS_LIST;}

	@Override public boolean place(FeaturePlaceContext<CrystalBlobConfig> context)
	{
		BlockStateProvider blockProvider = context.config().crystalStateProvider();
		BlockPos pos = context.origin(); WorldGenLevel world = context.level(); RandomSource rand = context.random();
		int x = pos.getX(), y=10, z=pos.getZ();
		int ymax = 160;
		BlockPos blockpos;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		mutablePos.set(new BlockPos(x, y, z));
		boolean hasSupport = false;
		while (!hasSupport && y < ymax)
		{
			if (hasSupportToGenerate(mutablePos, world)) {hasSupport = true;}
			else {y++; mutablePos.set(new BlockPos(x, y, z));}
		}
		if (y==ymax) {return false;}
		if (!squareHasRoof(mutablePos, world)) {return false;}
		pos = mutablePos;
		
		if (rand.nextInt(160) < y) {return false;}
		if (!this.isDungeonSensitiveValid(context)) {return false;}

		world.setBlock(pos, blockProvider.getState(rand, pos), 2);
        for(int i = 0; i < 300; ++i)
        {
        	blockpos = pos.offset(rand.nextInt(2) - rand.nextInt(2), rand.nextInt(5), rand.nextInt(2) - rand.nextInt(2)); //55855

            if (world.isEmptyBlock(blockpos))
            {
            	int j = 0;

	            for(Direction direction : Direction.values())
	            {
		            if (world.getBlockState(blockpos.relative(direction)).is(AerialHellTags.Blocks.NATURAL_CRYSTAL_BLOCK)) {++j;}

		            if (j > 1) {break;}
	            }

	            if (j == 1) {world.setBlock(blockpos, blockProvider.getState(rand, blockpos), 2);}
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
