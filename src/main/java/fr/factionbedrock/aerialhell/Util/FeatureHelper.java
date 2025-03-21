package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.MapColor;
import org.joml.Vector3f;

public class FeatureHelper
{
    public static boolean isFeatureGeneratingNextToDungeon(FeaturePlaceContext<?> context)
    {
        /* TODO (currently makes worldgen stops with no error in log, new chunks do not load, infinite loop)
        WorldGenLevel level = context.level();
        //context.chunkGenerator().findNearestMapStructure(level.getLevel(), StructureHelper.getDungeonsHolderSet(level.registryAccess()), context.origin(), 100, false);
        BlockPos nearestDungeonPos = level.getLevel().findNearestMapStructure(AerialHellTags.Structures.DUNGEONS, context.origin(), 100, false);
        if (nearestDungeonPos != null)
        {
            return context.origin().distSqr(nearestDungeonPos) < 100;
        }
        else {return false;}
        */
        return false;
    }

    public static boolean isShadowBiome(Biome biome)
    {
        //ResourceLocation shadowPlain = AerialHellBiomes.SHADOW_PLAIN.location();
        //ResourceLocation shadowForest = AerialHellBiomes.SHADOW_FOREST.location();
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

    public static BlockPos getFeatureCenter(FeaturePlaceContext<?> context)
    {
        BlockPos origin = context.origin();
        int x = origin.getX(), y = origin.getY(), z = origin.getZ();

        if (origin.getX() < 0) {x++;}
        if (origin.getZ() < 0) {z++;}

        int chunkX = x / 16;
        int chunkZ = z / 16;

        if (origin.getX() < 0) {chunkX--;}
        if (origin.getZ() < 0) {chunkZ--;}

        int centerOfFeatureX = chunkX * 16 + 8;
        int centerOfFeatureZ = chunkZ * 16 + 8;
        return new BlockPos(centerOfFeatureX, y, centerOfFeatureZ);
    }

    public static BlockPos getRandomPosInFeatureRegion(BlockPos featureCenter, RandomSource rand, int MAX_XZ_DISTANCE_FROM_CENTER, int MAX_Y_DISTANCE_FROM_CENTER)
    {
        //MAX_XZ_DISTANCE_FROM_CENTER must be <= 23
        return featureCenter.offset(rand.nextInt(- MAX_XZ_DISTANCE_FROM_CENTER, MAX_XZ_DISTANCE_FROM_CENTER), rand.nextInt(- MAX_Y_DISTANCE_FROM_CENTER, MAX_Y_DISTANCE_FROM_CENTER), rand.nextInt(- MAX_XZ_DISTANCE_FROM_CENTER, MAX_XZ_DISTANCE_FROM_CENTER));
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

    public static boolean isBelowMaxBuildHeight(FeaturePlaceContext<?> context, BlockPos pos)
    {
        return pos.getY() < context.level().getMaxY();
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

    public static Vector3f getRandomOrthogonalVectorToLineDefinedWith2Points(BlockPos linePos1, BlockPos linePos2, RandomSource rand)
    {
        Vector3f vector1 = new Vector3f(linePos2.getX() - linePos1.getX(), linePos2.getY() - linePos1.getY(), linePos2.getZ() - linePos1.getZ());
        Vector3f vector2 = new Vector3f(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
        if (vector2.x / vector1.x == vector2.y / vector1.y) {vector2.x = - vector2.x / 2;} //quickly handle the case where vector1 and vector2 may be collinear
        return new Vector3f(vector1.y * vector2.z - vector1.z * vector2.y, vector1.z * vector2.x - vector1.x * vector2.z, vector1.x * vector2.y - vector1.y * vector2.x);
    }

    public static void generateDebug(FeaturePlaceContext<?> context)
    {
        WorldGenLevel reader = context.level();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
        for (int i = -50; i <= 50; i++)
        {
            reader.setBlock(centerOfFeature.offset(i, 0, 0), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
            reader.setBlock(centerOfFeature.offset(0, i, 0), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
            reader.setBlock(centerOfFeature.offset(0, 0, i), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
        }

        //feature center
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    reader.setBlock(centerOfFeature.offset(x, y, z), AerialHellBlocks.ARSONIST_BLOCK.get().defaultBlockState(), 0);
                }
            }
        }

        //feature origin
        reader.setBlock(context.origin(), AerialHellBlocks.CRYSTAL_BRICKS.get().defaultBlockState(), 0);
    }
}
