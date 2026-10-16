package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public record GiantCrystalBlobFeature() implements Feature, DungeonSensitiveFeatureCheck
{
	public static final MapCodec<GiantCrystalBlobFeature> CODEC = MapCodec.unit(GiantCrystalBlobFeature::new);

	@Override public MapCodec<GiantCrystalBlobFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GIANT_CRYSTAL_BLOB_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		if (!level.isEmptyBlock(origin)) {return false;}
		else
		{
			BlockState blockstate = level.getBlockState(origin.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
		    else
		    {
				place(origin, level, random);
		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, ServerLevelAccessor world, RandomSource rand)
	{
		world.setBlock(pos, AerialHellBlocks.CRYSTAL_BLOCK.defaultBlockState(), 2);

		BlockPos blockpos;
		for(int i = 0; i < 3000; ++i)
		{

			if (i < 1000)
			{
				blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));
			}
			else if (i < 1500)
			{
				blockpos = pos.offset(rand.nextInt(7) - rand.nextInt(7), 6 + rand.nextInt(6), rand.nextInt(7) - rand.nextInt(7));
			}
			else
			{
				blockpos = pos.offset(rand.nextInt(9) - rand.nextInt(9), rand.nextInt(4) - rand.nextInt(8), rand.nextInt(9) - rand.nextInt(9));
			}
			if (world.getBlockState(blockpos).is(Blocks.AIR) || world.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT) || world.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_STONE))
			{
				int j = 0;

				for(Direction direction : Direction.values())
				{
					if (world.getBlockState(blockpos.relative(direction)).is(AerialHellBlocks.CRYSTAL_BLOCK))
					{
						++j;
					}

					if (j > 1) {break;}
				}

				if (j == 1 || j == 2 && rand.nextInt(25) == 0)
				{
					world.setBlock(blockpos, AerialHellBlocks.CRYSTAL_BLOCK.defaultBlockState(), 2);
				}
			}
		}
		for(int i = 0; i < 100; ++i)
		{
			blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
			if (world.getBlockState(blockpos).is(Blocks.AIR) && (world.getBlockState(blockpos.below()).is(AerialHellBlocks.CRYSTAL_BLOCK)))
			{
				world.setBlock(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.defaultBlockState(), 2);
			}
		}
	}
}
