package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ImplodingEntityInfo
{
    private final EntityDataAccessor<Boolean> implodingDataAccessor;
    private final ImplodingSoundHelper implodingSoundInfo;
    private final int castDuration; //ticks between imploding start and implosion - for sound synchro, you will need sound duration = castDuration - (implodingSoundInfo)soundOffset
    private final int cooldownDuration; //ticks between implosion and next imploding start
    private int castTicks;
    private int cooldownTicks;

    public ImplodingEntityInfo(EntityDataAccessor<Boolean> implodingDataAccessor, int castDuration, int cooldownDuration, ImplodingSoundHelper implodingSoundInfo)
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

    public static class ImplodingSoundHelper extends PlaySoundHelper
    {
        private final int soundStartOffsetInTicks;

        public ImplodingSoundHelper(@NotNull PlaySoundHelper.PlaySoundMethodReference playImplodingSoundMethodReference) {this(playImplodingSoundMethodReference, 1.0F, 1.0F, 0);}
        public ImplodingSoundHelper(@NotNull PlaySoundHelper.PlaySoundMethodReference playImplodingSoundMethodReference, int soundStartOffsetInTicks) {this(playImplodingSoundMethodReference, 1.0F, 1.0F, soundStartOffsetInTicks);}
        public ImplodingSoundHelper(@NotNull PlaySoundHelper.PlaySoundMethodReference playImplodingSoundMethodReference, float volume, float pitch, int soundStartOffsetInTicks)
        {
            super(playImplodingSoundMethodReference, volume, pitch);
            this.soundStartOffsetInTicks = soundStartOffsetInTicks;
        }

        public ImplodingSoundHelper(@NotNull SoundEvent soundEvent) {this(soundEvent, 5.0F, 1.0F, 0);}
        public ImplodingSoundHelper(@NotNull SoundEvent soundEvent, float volume, float pitch, int soundStartOffsetInTicks)
        {
            super(soundEvent, volume, pitch);
            this.soundStartOffsetInTicks = soundStartOffsetInTicks;
        }
    }
}
