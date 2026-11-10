package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingArmorAbsorbMixin
{
    //partial armor piercing : armor only blocks (1 - piercing) of what it normally blocks (see AerialHellDamageTypes.getArmorPiercing)
    //same as the NeoForge DamageContainer.Reduction.ARMOR modifier. armor durability loss is computed before, with the full damage
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("RETURN"), cancellable = true)
    private void applyArmorPiercing(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir)
    {
        float armorPiercing = AerialHellDamageTypes.getArmorPiercing(damageSource);
        if (armorPiercing > 0.0F)
        {
            float armorReduction = damage - cir.getReturnValue();
            cir.setReturnValue(damage - armorReduction * (1.0F - armorPiercing));
        }
    }
}
