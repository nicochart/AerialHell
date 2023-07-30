package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
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
}
