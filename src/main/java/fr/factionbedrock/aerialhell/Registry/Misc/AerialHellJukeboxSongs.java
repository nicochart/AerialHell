package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.JukeboxSong;

public interface AerialHellJukeboxSongs
{
    ResourceKey<JukeboxSong> AERIAL_HELL_THEME_TOMMAUP = create("aerial_hell_theme_tommaup");
    ResourceKey<JukeboxSong> SWEDEN_ANDREAS_ZOELLER = create("sweden_andreas_zoeller");
    ResourceKey<JukeboxSong> ENTHUSIAST_TOURS = create("enthusiast_tours");
    ResourceKey<JukeboxSong> BMINOR_ARULO = create("bminor_arulo");
    ResourceKey<JukeboxSong> RETRO_EXPLORATION_TOMMAUP = create("retro_exploration_tommaup");

    private static ResourceKey<JukeboxSong> create(String name)
    {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));
    }
}
