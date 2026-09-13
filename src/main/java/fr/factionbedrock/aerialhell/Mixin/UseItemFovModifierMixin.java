package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellItemAbilities;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
    private void fovModifier(CallbackInfoReturnable<Float> callbackInfoReturnable)
    {
        AbstractClientPlayer player = (AbstractClientPlayer)(Object)this;
        if (player.isUsingItem())
        {
            if (player.getUseItem().is(AerialHellBlocksAndItems.RUBY_RESONATOR.get()) || player.getUseItem().is(AerialHellBlocksAndItems.VOLUCITE_RESONATOR.get()))
            {
                float fieldOfView = 1.0F;
                if (player.getAbilities().flying) {fieldOfView *= 1.1F;}
                fieldOfView *= ((float)player.getAttributeValue(Attributes.MOVEMENT_SPEED) / player.getAbilities().getWalkingSpeed() + 1.0F) / 2.0F;
                if (player.getAbilities().getWalkingSpeed() == 0.0F || Float.isNaN(fieldOfView) || Float.isInfinite(fieldOfView)) {fieldOfView = 1.0F;}

                int ticksUsingItem = player.getTicksUsingItem();
                float scale = (float)ticksUsingItem / AerialHellItemAbilities.RESONATOR_USE_TICKS;
                if (scale > 1.0F) {scale = 1.0F;}
                else {scale *= scale;}

                fieldOfView *= 1.0F - scale * 0.15F;
                callbackInfoReturnable.setReturnValue(fieldOfView);
            }
        }
    }
}
