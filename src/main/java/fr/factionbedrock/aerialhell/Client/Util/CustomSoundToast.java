package fr.factionbedrock.aerialhell.Client.Util;

import net.minecraft.sounds.SoundEvent;
import org.jspecify.annotations.Nullable;

public interface CustomSoundToast
{
    //null = vanilla sounds are kept
    //else, custom sound is played instead of vanilla sound
    @Nullable SoundEvent getMatchingAerialHellAdvancementSound();
}
