package fr.factionbedrock.aerialhell.Mixin.AdvancementSounds;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import fr.factionbedrock.aerialhell.Client.Util.CustomSoundToast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToastManager.class)
public class ToastManagerSlideSoundMixin
{
    //Vanilla plays slide in / slide out sounds (UI_TOAST_IN / UI_TOAST_OUT)
    //This mixin disables this for Aerial Hell advancements
    //Target : lambda of visibleToasts.removeIf(...) in update(), "if (toast.visibility != previousVisibility && visibilityChangeSoundPlayed.isFalse())"
    //Returning false also skips visibilityChangeSoundPlayed.setTrue(), so another toast can still play its slide sound in the same update
    @ModifyExpressionValue(method = "lambda$update$0", at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/mutable/MutableBoolean;isFalse()Z"))
    private boolean skipSlideSoundOfCustomSoundToast(boolean canPlaySlideSound, @Local(argsOnly = true) ToastManager.ToastInstance<?> toastInstance)
    {
        return canPlaySlideSound && !(toastInstance.getToast() instanceof CustomSoundToast toast && toast.getMatchingAerialHellAdvancementSound() != null);
    }
}
