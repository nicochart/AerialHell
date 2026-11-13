package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

//Sound played by the toast of an Aerial Hell advancement, replacing all vanilla toast sounds
//see package fr.factionbedrock.aerialhell.Mixin.AdvancementSounds
public class AdvancementToastSounds
{
    //advancement folder (data/aerialhell/advancement/<folder>/...) -> toast sound
    private static final Map<String, Supplier<SoundEvent>> SOUND_BY_FOLDER = Map.of(
            "classic", AerialHellSoundEvents.ADVANCEMENT_CLASSIC,
            "story", AerialHellSoundEvents.ADVANCEMENT_STORY,
            "secret", AerialHellSoundEvents.ADVANCEMENT_SECRET
    );

    //null = not an Aerial Hell advancement (or not in a folder above) : vanilla toast sounds are kept
    public static @Nullable SoundEvent getSound(Identifier advancementId)
    {
        if (!advancementId.getNamespace().equals(AerialHell.MODID)) {return null;}
        String path = advancementId.getPath();
        int folderEnd = path.indexOf('/');
        if (folderEnd == -1) {return null;}
        Supplier<SoundEvent> sound = SOUND_BY_FOLDER.get(path.substring(0, folderEnd));
        return sound == null ? null : sound.get();
    }
}
