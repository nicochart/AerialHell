package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Item.AerialHellItemInterface;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class CanDisableShieldMixin
{
    @Inject(method = "canDisableShield", at = @At("RETURN"), cancellable = true)
    private void onCanDisableShield(CallbackInfoReturnable<Boolean> callbackInfo)
    {
        if (callbackInfo.getReturnValue()) {return;}

        ItemStack weaponStack = ((LivingEntity) (Object) this).getWeaponItem();
        callbackInfo.setReturnValue(weaponStack.getItem() instanceof AerialHellItemInterface ahItem && ahItem.canDisableShield(weaponStack));
    }
}