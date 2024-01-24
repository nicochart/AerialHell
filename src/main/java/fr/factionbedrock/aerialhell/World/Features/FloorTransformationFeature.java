package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Config.FloorTransformationConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class FloorTransformationFeature extends Feature<FloorTransformationConfig>
{
	public FloorTransformationFeature(Codec<FloorTransformationConfig> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<FloorTransformationConfig> context)
	{
		FloorTransformationConfig config = context.config(); WorldGenLevel level = context.level(); RandomSource rand = context.random();
		BlockPos placementPos, origin = context.origin();
		BlockState placementState;

		int sizeX = getRandomEllipsisSize(context), sizeZ = getRandomEllipsisSize(context);

		if (canGenerate(context, origin))
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
							PlacementType type = shouldPlace(context, origin, sizeX, sizeZ, placementPos);
							if (type != PlacementType.NONE)
							{
								placementState = getPlacementState(context, placementPos, type);
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

	private int getRandomEllipsisSize(FeaturePlaceContext<FloorTransformationConfig> context)
	{
		FloorTransformationConfig config = context.config();
		int baseSize = config.ellipsisBaseSize(), randomSpread = config.ellipsisRandomSpreading();
		return randomSpread > 0 ? baseSize + context.random().nextInt(-randomSpread, randomSpread) : baseSize;
	}

	protected enum PlacementType {NONE, INNER, OUTER}

	protected PlacementType shouldPlace(FeaturePlaceContext<FloorTransformationConfig> context, BlockPos centerPos, int sizeX, int sizeZ, BlockPos placementPos)
	{
		float innerChance = context.config().innerChance(), outerChance = context.config().outerChance();
		float random = context.random().nextFloat();
		if (isInsideEllipsis(centerPos, (int) (sizeX * 3.0/4.0), (int) (sizeZ * 3.0/4.0), placementPos))
		{
			if (random <= innerChance) {return PlacementType.INNER;}
		}
		else if (isInsideEllipsis(centerPos, sizeX, sizeZ, placementPos))
		{
			if (random < outerChance) {return PlacementType.OUTER;}
		}
		return PlacementType.NONE;
	}

	protected boolean isInsideEllipsis(BlockPos centerPos, int sizeX, int sizeZ, BlockPos placementPos)
	{
		int x = placementPos.getX(), z = placementPos.getZ();
		int xc = centerPos.getX(), zc = centerPos.getZ();
		return (x - xc) * (x - xc) + (z - zc) * (z - zc) < sizeX * sizeZ;
	}

	protected BlockState getPlacementState(FeaturePlaceContext<FloorTransformationConfig> context, BlockPos pos, PlacementType type)
	{
		FloorTransformationConfig config = context.config(); RandomSource rand = context.random();
		return type == PlacementType.INNER ? config.innerStateProvider().getState(rand, pos) : config.outerStateProvider().getState(rand, pos);
	}

	protected boolean canGenerate(FeaturePlaceContext<FloorTransformationConfig> context, BlockPos pos)
	{
		return (isValidSupport(pos, context.level()) || isValidSupport(pos.below(), context.level())) && hasRoofIfNeeded(context, pos);
	}

	protected boolean isValidSupport(BlockPos pos, WorldGenLevel level)
	{
		return level.isEmptyBlock(pos.above()) && isReplaceable(level.getBlockState(pos));
	}

	protected boolean hasRoofIfNeeded(FeaturePlaceContext<FloorTransformationConfig> context, BlockPos pos)
	{
		return !context.config().needsRoof().equals("true") || hasAnyBlockAbove(pos, context.level());
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
