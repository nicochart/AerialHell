package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class CanDisableShieldMixin
{
    @Inject(method = "canDisableShield", at = @At("RETURN"), cancellable = true)
    private void onGetEffectiveGravity(CallbackInfoReturnable<Boolean> callbackInfo)
    {
        if (callbackInfo.getReturnValue()) {return;}

        ItemStack weaponStack = ((LivingEntity) (Object) this).getWeaponItem();
        callbackInfo.setReturnValue(weaponStack.getItem() instanceof AerialHellItem ahItem && ahItem.canDisableShield(weaponStack));
    }
}