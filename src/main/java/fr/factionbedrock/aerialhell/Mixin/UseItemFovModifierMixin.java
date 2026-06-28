package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemAbilities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class UseItemFovModifierMixin
{
    //copy of AbstractClientPlayer getFieldOfViewModifier part about bows, edited
    //modifies the FOV when a player is using aerial hell ranged weapon
    @Inject(method = "getFieldOfViewModifier", at = @At("RETURN"), cancellable = true)
    private void fovModifier(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> callbackInfoReturnable)
    {
        float modifier = 1.0F;
        AbstractClientPlayer player = (AbstractClientPlayer)(Object)this;
        if (player.isUsingItem())
        {
            if (player.getUseItem().is(AerialHellItems.RUBY_RESONATOR) || player.getUseItem().is(AerialHellItems.VOLUCITE_RESONATOR))
            {
                float scale = Math.min((float)player.getTicksUsingItem() / AerialHellItemAbilities.RESONATOR_USE_TICKS, 1.0F);
                modifier *= 1.0F - Mth.square(scale) * 0.15F;
            }
            callbackInfoReturnable.setReturnValue(ClientHooks.getFieldOfViewModifier(player, modifier, effectScale));
        }
    }
}
