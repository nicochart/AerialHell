package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;

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
}
