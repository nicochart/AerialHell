package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.MapColor;

public class FeatureHelper
{
    public static boolean isFeatureGeneratingNextToDungeon(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel level = context.level();
        //context.chunkGenerator().findNearestMapStructure(level.getLevel(), StructureHelper.getDungeonsHolderSet(level.registryAccess()), context.origin(), 100, false);
        BlockPos nearestDungeonPos = level.getLevel().findNearestMapStructure(AerialHellTags.Structures.DUNGEONS, context.origin(), 100, false);
        if (nearestDungeonPos != null)
        {
            return context.origin().distSqr(nearestDungeonPos) < 100;
        }
        else {return false;}
    }

    public static boolean isShadowBiome(Biome biome)
    {
        ResourceLocation shadowPlain = AerialHellBiomes.SHADOW_PLAIN.location();
        ResourceLocation shadowForest = AerialHellBiomes.SHADOW_FOREST.location();
        // Used to test getNoiseBiome() method in isFeatureChunk structure gen condition. This method doesn't return the right biome : do not use isShadowBiome(Biome biome) in isFeatureChunk context.
        // if (!(biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest)))) {System.out.println("not shadow biome detected : registry name =  "+biome.getRegistryName());}
        return false;//biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest)); //TODO
    }

    public static boolean isReplaceableByLogOrLeavesFeature(LevelAccessor level, BlockPos pos, boolean canReplacePlant)
    {
        return level.isStateAtPosition(pos, (state) ->
        {
            return state.canBeReplaced() || canReplacePlant && state.getMapColor(level, pos) == MapColor.PLANT; //TODO : it works ?
        });
    }

    public static BlockPos getFeatureCenter(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos origin = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();

        int chunkX = origin.getX() / 16;
        int chunkZ = origin.getZ() / 16;

        int xSign = (origin.getX() > 0) ? +1 : -1;
        int zSign = (origin.getZ() > 0) ? +1 : -1;

        int centerOfFeatureX = chunkX * 16 + xSign * 8;
        int centerOfFeatureZ = chunkZ * 16 + zSign * 8;
        return new BlockPos(centerOfFeatureX, origin.getY(), centerOfFeatureZ);
    }

    public static boolean isBlockPosInFeatureRegion(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos)
    {
        BlockPos featureCenter = getFeatureCenter(context);
        return isBlockPosInFeatureRegion(featureCenter, pos);
    }

    public static boolean isBlockPosInFeatureRegion(BlockPos featureCenter, BlockPos pos)
    {
        int MAX_FEATURE_SIZE_HORIZONTAL = 3 * 16, MAX_FEATURE_SIZE_VERTICAL = 80; //features are int 3x3 chunks
        int maxAbsHorizontalOffset = getMaxAbsoluteXZOffset(featureCenter, pos);
        int absVerticalOffset = Math.abs(featureCenter.getY() - pos.getY());
        return maxAbsHorizontalOffset < MAX_FEATURE_SIZE_HORIZONTAL/2 - 1 && absVerticalOffset < MAX_FEATURE_SIZE_VERTICAL/2;
        //MAX_FEATURE_SIZE_HORIZONTAL/2 - 1 because we check from "feature center" blockpos, which is not really the center, since a chunk is 16x16.. the center is 2x2
    }

    public static int getMaxAbsoluteXZOffset(BlockPos pos1, BlockPos pos2)
    {
        int xOffset = pos2.getX() - pos1.getX(); int zOffset = pos2.getZ() - pos1.getZ();
        return Math.max(Math.abs(xOffset), Math.abs(zOffset));
    }

    public static int getMaxAbsoluteXYZOffset(BlockPos pos1, BlockPos pos2)
    {
        int xOffset = pos2.getX() - pos1.getX(); int yOffset = pos2.getY() - pos1.getY(); int zOffset = pos2.getZ() - pos1.getZ();
        return Math.max(Math.max(Math.abs(xOffset), Math.abs(yOffset)), Math.abs(zOffset));
    }
}
