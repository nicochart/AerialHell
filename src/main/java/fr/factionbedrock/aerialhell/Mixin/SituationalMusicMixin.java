package fr.factionbedrock.aerialhell.Mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.attribute.BackgroundMusic;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class SituationalMusicMixin
{
    //config useAerialHellSituationalMusic = false : the music & delays of Aerial Hell biomes are replaced by the overworld music & delays
    @ModifyReturnValue(method = "getSituationalMusic", at = @At("RETURN"))
    @Nullable private Music replaceAerialHellMusic(@Nullable Music music)
    {
        if (LoadedConfigParams.USE_AERIAL_HELL_SITUATIONAL_MUSIC || music == null || !isAerialHellMusic(music.sound().value())) {return music;}

        LocalPlayer player = ((Minecraft) (Object) this).player;
        boolean isCreative = player != null && player.getAbilities().instabuild && player.getAbilities().mayfly;
        return BackgroundMusic.OVERWORLD.select(isCreative, false).orElse(null); //vanilla overworld music : music.game, or music.creative
    }

    private static boolean isAerialHellMusic(SoundEvent sound)
    {
        return sound.equals(AerialHellSoundEvents.AERIALHELL_DIMENSION_MUSIC) || sound.equals(AerialHellSoundEvents.CRYSTAL_MUSIC) || sound.equals(AerialHellSoundEvents.SHADOW_MUSIC);
    }
}
