package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;

public class FeatureHelper
{
    public static boolean generatesInAnyDungeon(ISeedReader reader, BlockPos pos)
    {
        return (
            reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()).findAny().isPresent() ||
                    reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get()).findAny().isPresent()
        );
    }

    public static boolean isShadowBiome(Biome biome)
    {
        ResourceLocation shadowPlain = AerialHellBiomes.SHADOW_PLAIN.get().getRegistryName();
        ResourceLocation shadowForest = AerialHellBiomes.SHADOW_FOREST.get().getRegistryName();
        return biome.getRegistryName() != null && (biome.getRegistryName().equals(shadowPlain) || biome.getRegistryName().equals(shadowForest));
    }
}
