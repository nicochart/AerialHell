package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.material.Material;

public class FeatureHelper
{
    public static boolean generatesInAnyDungeon(ChunkGenerator chunkGenerator, WorldGenLevel reader, BlockPos pos)
    {

        /* TODO : new way : (just needs the chunk generator, from feature generation context) - this is the same way as StructureHelper. Just use StructureHelper.*/
        long seed = reader.getSeed();
        ChunkPos chunkPos = new ChunkPos(pos.getX() / 16, pos.getZ() / 16);
        return StructureHelper.hasDungeonNearby(chunkGenerator, seed, chunkPos.x, chunkPos.z, 2,true);
    }

    public static boolean isShadowBiome(Biome biome)
    {
        ResourceLocation shadowPlain = AerialHellBiomes.SHADOW_PLAIN.get().getRegistryName();
        ResourceLocation shadowForest = AerialHellBiomes.SHADOW_FOREST.get().getRegistryName();
        // Used to test getNoiseBiome() method in isFeatureChunk structure gen condition. This method doesn't return the right biome : do not use isShadowBiome(Biome biome) in isFeatureChunk context.
        // if (!(biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest)))) {System.out.println("not shadow biome detected : registry name =  "+biome.getRegistryName());}
        return biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest));
    }

    public static boolean isReplaceableByLogOrLeavesFeature(LevelAccessor level, BlockPos pos, boolean canReplacePlant)
    {
        return level.isStateAtPosition(pos, (state) ->
        {
            Material material = state.getMaterial();
            return state.getMaterial().isReplaceable() || canReplacePlant && material == Material.PLANT;
        });
    }
}
