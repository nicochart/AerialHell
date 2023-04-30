package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;

public class FeatureHelper
{
    public static boolean generatesInAnyDungeon(WorldGenLevel reader, BlockPos pos)
    {
        /* TODO : find a new way
        return (
            reader.func_241827_a(SectionPos.of(pos), AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.of(pos), AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.of(pos), AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.of(pos), AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get()).findAny().isPresent()
        );*/

        /* TODO : new way : (just needs the chunk generator, from feature generation context) - this is the same way as StructureHelper. Just use StructureHelper.
        long seed = reader.getSeed();
        ChunkPos chunkPos = new ChunkPos(pos.getX() / 16, pos.getZ() / 16);
        return chunkGenerator.hasFeatureChunkInRange([Structure], seed, chunkPos.x, chunkPos.z, 10) || .. ;*/
        return false;
    }

    public static boolean isShadowBiome(Biome biome)
    {
        ResourceLocation shadowPlain = AerialHellBiomes.SHADOW_PLAIN.get().getRegistryName();
        ResourceLocation shadowForest = AerialHellBiomes.SHADOW_FOREST.get().getRegistryName();
        // Used to test getNoiseBiome() method in isFeatureChunk structure gen condition. This method doesn't return the right biome : do not use isShadowBiome(Biome biome) in isFeatureChunk context.
        // if (!(biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest)))) {System.out.println("not shadow biome detected : registry name =  "+biome.getRegistryName());}
        return biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest));
    }
}
