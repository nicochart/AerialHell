package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ImplodingEntityInfo
{
    private final EntityDataAccessor<Boolean> implodingDataAccessor;
    private final ImplodingSoundInfo implodingSoundInfo;
    private final int castDuration; //ticks between imploding start and implosion - for sound synchro, you will need sound duration = castDuration - (implodingSoundInfo)soundOffset
    private final int cooldownDuration; //ticks between implosion and next imploding start
    private int castTicks;
    private int cooldownTicks;

    public ImplodingEntityInfo(EntityDataAccessor<Boolean> implodingDataAccessor, int castDuration, int cooldownDuration, ImplodingSoundInfo implodingSoundInfo)
    {
        this.implodingDataAccessor = implodingDataAccessor;
        this.castDuration = castDuration;
        this.cooldownDuration = cooldownDuration;
        this.implodingSoundInfo = implodingSoundInfo;
        this.castTicks = 0;
        this.cooldownTicks = 0;
    }

    public int getCastDuration() {return this.castDuration;}
    public int getCooldownDuration() {return this.cooldownDuration;}

    public int getCastTicks() {return this.castTicks;}
    public void setCastTicks(int value) {this.castTicks = value;}
    public int getCooldownTicks() {return this.cooldownTicks;}
    public void setCooldownTicks(int value) {this.cooldownTicks = value;}

    public void playImplodingSound(@NotNull LivingEntity source) {this.implodingSoundInfo.playSound(source);}
    public int getSoundOffset() {return this.implodingSoundInfo.soundStartOffsetInTicks;}

    public EntityDataAccessor<Boolean> getImplodingDataAccessor() {return this.implodingDataAccessor;}

    public static class ImplodingSoundInfo
    {
        private final boolean useMethodReference; //if true, playSoundMethodReference is not null, soundEvent is null. if false, contrary.
        @Nullable private final PlayImplodingSoundReference playSoundMethodReference;
        @Nullable private final SoundEvent soundEvent;
        private final float volume;
        private final float pitch;
        private final int soundStartOffsetInTicks;

        public ImplodingSoundInfo(@NotNull PlayImplodingSoundReference playImplodingSoundMethodReference) {this(playImplodingSoundMethodReference, 5.0F, 1.0F, 0);}
        public ImplodingSoundInfo(@NotNull PlayImplodingSoundReference playImplodingSoundMethodReference, int soundStartOffsetInTicks) {this(playImplodingSoundMethodReference, 5.0F, 1.0F, soundStartOffsetInTicks);}
        public ImplodingSoundInfo(@NotNull PlayImplodingSoundReference playImplodingSoundMethodReference, float volume, float pitch, int soundStartOffsetInTicks)
        {
            this.useMethodReference = true;
            this.playSoundMethodReference = playImplodingSoundMethodReference;
            this.soundEvent = null;
            this.volume = volume;
            this.pitch = pitch;
            this.soundStartOffsetInTicks = soundStartOffsetInTicks;
        }

        public ImplodingSoundInfo(@NotNull SoundEvent soundEvent) {this(soundEvent, 5.0F, 1.0F, 0);}
        public ImplodingSoundInfo(@NotNull SoundEvent soundEvent, float volume, float pitch, int soundStartOffsetInTicks)
        {
            this.useMethodReference = false;
            this.playSoundMethodReference = null;
            this.soundEvent = soundEvent;
            this.volume = volume;
            this.pitch = pitch;
            this.soundStartOffsetInTicks = soundStartOffsetInTicks;
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

        @FunctionalInterface public interface PlayImplodingSoundReference{void play(float volume, float pitch);}
    }
}
