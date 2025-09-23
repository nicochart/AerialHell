package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.World.Features.Config.FloorTransformationConfig;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class FloorTransformationFeature extends Feature<FloorTransformationConfig> implements DungeonSensitiveFeatureCheck
{
	public FloorTransformationFeature(Codec<FloorTransformationConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FLOOR_TRANSFORMATION_LIST;}

	@Override public boolean generate(FeatureContext<FloorTransformationConfig> context)
	{
		FloorTransformationConfig config = context.getConfig(); StructureWorldAccess level = context.getWorld(); Random rand = context.getRandom();
		BlockPos placementPos, origin = context.getOrigin();
		BlockState placementState;

		int sizeX = getRandomEllipsisSize(context), sizeZ = getRandomEllipsisSize(context);

		if (canGenerate(context, origin) && this.isDungeonSensitiveValid(context))
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
								level.setBlockState(placementPos, placementState, 2);
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

	private int getRandomEllipsisSize(FeatureContext<FloorTransformationConfig> context)
	{
		FloorTransformationConfig config = context.getConfig();
		int baseSize = config.ellipsisBaseSize(), randomSpread = config.ellipsisRandomSpreading();
		return randomSpread > 0 ? baseSize + context.getRandom().nextBetweenExclusive(-randomSpread, randomSpread) : baseSize;
	}

	protected enum PlacementType {NONE, INNER, OUTER}

	protected PlacementType shouldPlace(FeatureContext<FloorTransformationConfig> context, BlockPos centerPos, int sizeX, int sizeZ, BlockPos placementPos)
	{
		float innerChance = context.getConfig().innerChance(), outerChance = context.getConfig().outerChance();
		float random = context.getRandom().nextFloat();
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

	protected BlockState getPlacementState(FeatureContext<FloorTransformationConfig> context, BlockPos pos, PlacementType type)
	{
		FloorTransformationConfig config = context.getConfig(); Random rand = context.getRandom();
		return type == PlacementType.INNER ? config.innerStateProvider().get(rand, pos) : config.outerStateProvider().get(rand, pos);
	}

	protected boolean canGenerate(FeatureContext<FloorTransformationConfig> context, BlockPos pos)
	{
		return (isValidSupport(pos, context.getWorld()) || isValidSupport(pos.down(), context.getWorld())) && hasRoofIfNeeded(context, pos);
	}

	protected boolean isValidSupport(BlockPos pos, StructureWorldAccess level)
	{
		return level.isAir(pos.up()) && isReplaceable(level.getBlockState(pos));
	}

	protected boolean hasRoofIfNeeded(FeatureContext<FloorTransformationConfig> context, BlockPos pos)
	{
		return !context.getConfig().needsRoof().equals("true") || hasAnyBlockAbove(pos, context.getWorld());
	}

	protected boolean isReplaceable(BlockState state) {return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT);}

	protected boolean hasAnyBlockAbove(BlockPos pos, StructureWorldAccess level)
	{
		for (BlockPos blockpos1 = pos.up(); blockpos1.getY() < 250; blockpos1 = blockpos1.up())
		{
			if (!level.isAir(blockpos1)) {return true;}
		}
		return false;
	}
}
