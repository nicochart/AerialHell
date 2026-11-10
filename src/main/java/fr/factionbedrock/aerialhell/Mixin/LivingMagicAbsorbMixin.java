package fr.factionbedrock.aerialhell.Mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingMagicAbsorbMixin
{
    //partial enchantment piercing : protection enchantments only block (1 - piercing) of what they normally block (see AerialHellDamageTypes.getEnchantmentPiercing)
    //same as the NeoForge DamageContainer.Reduction.ENCHANTMENTS modifier. only wraps the enchantments part : resistance effect reduction (applied before) is not modified
    @WrapOperation(method = "getDamageAfterMagicAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterMagicAbsorb(FF)F"))
    private float applyEnchantmentPiercing(float damage, float enchantmentArmor, Operation<Float> original, @Local(argsOnly = true) DamageSource damageSource)
    {
        float damageAfterEnchantments = original.call(damage, enchantmentArmor);
        float enchantmentPiercing = AerialHellDamageTypes.getEnchantmentPiercing(damageSource);
        if (enchantmentPiercing <= 0.0F) {return damageAfterEnchantments;}

        float enchantmentReduction = damage - damageAfterEnchantments;
        return damage - enchantmentReduction * (1.0F - enchantmentPiercing);
    }
}
