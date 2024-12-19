package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface AerialHellJukeboxSongs
{
    RegistryKey<JukeboxSong> AERIAL_HELL_THEME_TOMMAUP = create("aerial_hell_theme_tommaup");
    RegistryKey<JukeboxSong> SWEDEN_ANDREAS_ZOELLER = create("sweden_andreas_zoeller");
    RegistryKey<JukeboxSong> ENTHUSIAST_TOURS = create("enthusiast_tours");
    RegistryKey<JukeboxSong> BMINOR_ARULO = create("bminor_arulo");
    RegistryKey<JukeboxSong> RETRO_EXPLORATION_TOMMAUP = create("retro_exploration_tommaup");

    private static RegistryKey<JukeboxSong> create(String name)
    {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, AerialHell.id(name));
    }
}
