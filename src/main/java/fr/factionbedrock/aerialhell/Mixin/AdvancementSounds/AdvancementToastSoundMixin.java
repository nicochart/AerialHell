package fr.factionbedrock.aerialhell.Mixin.AdvancementSounds;

import fr.factionbedrock.aerialhell.Client.Util.AdvancementToastSounds;
import fr.factionbedrock.aerialhell.Client.Util.CustomSoundToast;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.sounds.SoundEvent;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementToast.class)
public class AdvancementToastSoundMixin implements CustomSoundToast
{
    @Shadow @Final private AdvancementHolder advancement;

    //Vanilla ToastManager plays UI_TOAST_CHALLENGE_COMPLETE for challenge frames (nothing for others)
    //This mixin instead returns custom sound for all advancements
    @Inject(method = "getSoundEvent", at = @At("HEAD"), cancellable = true)
    private void replaceToastSound(CallbackInfoReturnable<SoundEvent> cir)
    {
        SoundEvent customSound = this.getMatchingAerialHellAdvancementSound();
        if (customSound != null) {cir.setReturnValue(customSound);}
    }

    @Override public @Nullable SoundEvent getMatchingAerialHellAdvancementSound() {return AdvancementToastSounds.getSound(this.advancement.id());}
}
