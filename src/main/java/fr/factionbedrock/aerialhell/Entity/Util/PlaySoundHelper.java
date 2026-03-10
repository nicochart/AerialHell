package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PlaySoundHelper
{
    private final boolean useMethodReference; //if true, playSoundMethodReference is not null, soundEvent is null. if false, contrary.
    @Nullable private final PlaySoundMethodReference playSoundMethodReference;
    @Nullable private final SoundEvent soundEvent;
    private final float volume;
    private final float pitch;

    public PlaySoundHelper(@NotNull PlaySoundHelper.PlaySoundMethodReference playSoundMethodReference) {this(playSoundMethodReference, 5.0F, 1.0F);}
    public PlaySoundHelper(@NotNull PlaySoundHelper.PlaySoundMethodReference playSoundMethodReference, float volume, float pitch)
    {
        this.useMethodReference = true;
        this.playSoundMethodReference = playSoundMethodReference;
        this.soundEvent = null;
        this.volume = volume;
        this.pitch = pitch;
    }

    public PlaySoundHelper(@NotNull SoundEvent soundEvent) {this(soundEvent, 5.0F, 1.0F);}
    public PlaySoundHelper(@NotNull SoundEvent soundEvent, float volume, float pitch)
    {
        this.useMethodReference = false;
        this.playSoundMethodReference = null;
        this.soundEvent = soundEvent;
        this.volume = volume;
        this.pitch = pitch;
    }

    public void playSound(@NotNull LivingEntity entity)
    {
        if (this.useMethodReference)
        {
            this.playSoundMethodReference.play(this.volume, this.pitch);
        }
        else
        {
            entity.playSound(this.soundEvent, this.volume, this.pitch);
        }
    }

    @FunctionalInterface public interface PlaySoundMethodReference {void play(float volume, float pitch);}
}