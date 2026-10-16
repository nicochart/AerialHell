package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record FloorTransformationFeature(int ellipsisBaseSize, int ellipsisRandomSpreading, float innerChance, float outerChance, Holder<BlockStateProvider> innerStateProvider, Holder<BlockStateProvider> outerStateProvider, String needsRoof) implements Feature, DungeonSensitiveFeatureCheck
{
	public static final MapCodec<FloorTransformationFeature> CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				ExtraCodecs.POSITIVE_INT.fieldOf("size").forGetter(FloorTransformationFeature::ellipsisBaseSize),
				ExtraCodecs.POSITIVE_INT.fieldOf("random_spread").forGetter(FloorTransformationFeature::ellipsisRandomSpreading),
				ExtraCodecs.POSITIVE_FLOAT.fieldOf("inner_chance").forGetter(FloorTransformationFeature::innerChance),
				ExtraCodecs.POSITIVE_FLOAT.fieldOf("outer_chance").forGetter(FloorTransformationFeature::outerChance),
				BlockStateProvider.CODEC.fieldOf("inner_state_provider").forGetter(FloorTransformationFeature::innerStateProvider),
				BlockStateProvider.CODEC.fieldOf("outer_state_provider").forGetter(FloorTransformationFeature::outerStateProvider),
				ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(FloorTransformationFeature::needsRoof)
			)
			.apply(i, FloorTransformationFeature::new)
	);

	@Override public MapCodec<FloorTransformationFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FLOOR_TRANSFORMATION_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		BlockPos placementPos;
		BlockState placementState;

		int sizeX = getRandomEllipsisSize(random), sizeZ = getRandomEllipsisSize(random);

		if (canGenerate(level, origin) && this.isDungeonSensitiveValid(level, origin))
	    {
	    	int x,y,z;

	    	for(x = origin.getX() - sizeX; x <= origin.getX() + sizeX; x++)
	        {
	            for(z = origin.getZ() - sizeZ; z <= origin.getZ() + sizeZ; z++)
	            {
	            	for (y = origin.getY() - 3; y < origin.getY() + 4; y++) //searching the surface
	            	{
	            		placementPos = new BlockPos(x, y, z);
	            		if (isValidSupport(placementPos, level)) //checking for surface
	            		{
							PlacementType type = shouldPlace(random, origin, sizeX, sizeZ, placementPos);
							if (type != PlacementType.NONE)
							{
								placementState = getPlacementState(level, random, placementPos, type);
								level.setBlock(placementPos, placementState, 2);
							}
	            			y += 7; //if we found stellar grass block <=> we found surface, skip other y values
	            		}
	            	}
	            }
	        }
	    	return true;
	    }
		else {return false;}
	}

	private int getRandomEllipsisSize(RandomSource random)
	{
		int baseSize = this.ellipsisBaseSize, randomSpread = this.ellipsisRandomSpreading;
		return randomSpread > 0 ? baseSize + random.nextInt(-randomSpread, randomSpread) : baseSize;
	}

	protected enum PlacementType {NONE, INNER, OUTER}

	protected PlacementType shouldPlace(RandomSource random, BlockPos centerPos, int sizeX, int sizeZ, BlockPos placementPos)
	{
		float randomFloat = random.nextFloat();
		if (isInsideEllipsis(centerPos, (int) (sizeX * 3.0/4.0), (int) (sizeZ * 3.0/4.0), placementPos))
		{
			if (randomFloat <= this.innerChance) {return PlacementType.INNER;}
		}
		else if (isInsideEllipsis(centerPos, sizeX, sizeZ, placementPos))
		{
			if (randomFloat < this.outerChance) {return PlacementType.OUTER;}
		}
		return PlacementType.NONE;
	}

	protected boolean isInsideEllipsis(BlockPos centerPos, int sizeX, int sizeZ, BlockPos placementPos)
	{
		int x = placementPos.getX(), z = placementPos.getZ();
		int xc = centerPos.getX(), zc = centerPos.getZ();
		return (x - xc) * (x - xc) + (z - zc) * (z - zc) < sizeX * sizeZ;
	}

	protected BlockState getPlacementState(WorldGenLevel level, RandomSource random, BlockPos pos, PlacementType type)
	{
		return type == PlacementType.INNER ? this.innerStateProvider.value().getState(level, random, pos) : this.outerStateProvider.value().getState(level, random, pos);
	}

	protected boolean canGenerate(WorldGenLevel level, BlockPos pos)
	{
		return (isValidSupport(pos, level) || isValidSupport(pos.below(), level)) && hasRoofIfNeeded(level, pos);
	}

	protected boolean isValidSupport(BlockPos pos, WorldGenLevel level)
	{
		return level.isEmptyBlock(pos.above()) && isReplaceable(level.getBlockState(pos));
	}

	protected boolean hasRoofIfNeeded(WorldGenLevel level, BlockPos pos)
	{
		return !this.needsRoof.equals("true") || hasAnyBlockAbove(pos, level);
	}

	protected boolean isReplaceable(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}

	protected boolean hasAnyBlockAbove(BlockPos pos, WorldGenLevel level)
	{
		for (BlockPos blockpos1 = pos.above(); blockpos1.getY() < 250; blockpos1 = blockpos1.above())
		{
			if (!level.isEmptyBlock(blockpos1)) {return true;}
		}
		return false;
	}
}
