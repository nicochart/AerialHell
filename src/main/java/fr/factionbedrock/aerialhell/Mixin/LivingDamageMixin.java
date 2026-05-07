package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Item.Ability.DamageUseSituationInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import fr.factionbedrock.aerialhell.Util.MutableFloat;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingDamageMixin
{
    @Inject(method = "hurtServer", at = @At("RETURN"), cancellable = true)
    private void onDamage(ServerLevel serverWorld, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity damagedEntity = (LivingEntity) (Object) this;
        Entity sourceEntity = damageSource.getEntity();

        float baseAmount = calculateBaseAmount(serverWorld, damagedEntity, damageSource, amount);
        float damageMultiplier = applyDamageEffectsAndCalculateDamageMultiplier(sourceEntity, damagedEntity, damageSource, baseAmount);
        applyMultipliedDamage(serverWorld, damagedEntity, damageSource, baseAmount, damageMultiplier);
    }

    //target method (damage(..)) already dealt baseAmount damage
    //apply damage multiplier by applying damage diff (adding or removing health)
    private static void applyMultipliedDamage(ServerLevel serverWorld, LivingEntity damagedEntity, DamageSource source, float baseAmount, float multiplier)
    {
        if (multiplier == 1) {return;}
        else if (multiplier > 1)
        {
            float additionalDamage = (multiplier - 1) * baseAmount;
            float totalDamage = multiplier * baseAmount;
            if (totalDamage > damagedEntity.lastHurt)
            {
                damagedEntity.actuallyHurt(serverWorld, source, additionalDamage);
                damagedEntity.lastHurt = totalDamage;
            }
        }
        else //multiplier < 1
        {
            float healthToRestore = (1 - multiplier) * baseAmount;
            float totalDamage = multiplier * baseAmount;
            damagedEntity.setHealth(damagedEntity.getHealth() + healthToRestore);
            damagedEntity.lastHurt = totalDamage;
        }
    }

    private static float calculateBaseAmount(ServerLevel serverWorld, LivingEntity damagedEntity, DamageSource source, float amount)
    {
        float baseAmount = amount;
        float blockedAmount = damagedEntity.applyItemBlocking(serverWorld, source, amount);
        baseAmount -= blockedAmount;
        if (baseAmount <= 0.0F) {return 0.0F;}
        if (source.is(DamageTypeTags.IS_FREEZING) && damagedEntity.is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {baseAmount *= 5.0F;}
        if (source.is(DamageTypeTags.DAMAGES_HELMET) && !damagedEntity.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {baseAmount *= 0.75F;}

        return baseAmount;
    }

    private static float applyDamageEffectsAndCalculateDamageMultiplier(Entity sourceEntity, LivingEntity target, DamageSource damageSource, float baseAmount)
    {
        float multiplier = applyEffectsDueToPotionEffects(damageSource, target); //base value

        MutableFloat damageMultiplier = new MutableFloat(multiplier);

        if (sourceEntity instanceof LivingEntity attacker)
        {
            ItemHelper.forEachAerialHellItem(EntityHelper.getEquippepItemStackList(attacker), (ahItem, equippedItemStack) ->
            {
                //attacker is the ah item owner, dealing damage to target (enemy)
                ahItem.onDealDamage(equippedItemStack.stack(), attacker, equippedItemStack.slot(), new DamageUseSituationInfo(target, damageSource, new FieldAccessor<>(damageMultiplier::get, damageMultiplier::set)));
            });
        }

        ItemHelper.forEachAerialHellItem(EntityHelper.getEquippepItemStackList(target), (ahItem, equippedItemStack) ->
        {
            //target is the ah item owner, receiving damage from attacker (enemy)
            ahItem.onTakeDamage(equippedItemStack.stack(), target, equippedItemStack.slot(), new DamageUseSituationInfo(sourceEntity, damageSource, new FieldAccessor<>(damageMultiplier::get, damageMultiplier::set)));
        });

        //damage multiplier value is changed internally in item abilities (onDealDamage / onTakeDamage)
        return damageMultiplier.get();
    }

    private static float applyEffectsDueToPotionEffects(DamageSource damageSource, LivingEntity target)
    {
        float multiplier = 1.0F;
        if ((damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.LAVA)) && target.hasEffect(AerialHellMobEffects.GOD)) {multiplier = 0;} //target with Gods Effect has Fire Resistance
        if (EntityHelper.isLivingEntityVulnerable(target))
        {
            int vulnerabilityMultiplier = target.getEffect(AerialHellMobEffects.VULNERABILITY).getAmplifier() + 1;
            multiplier = 2.0F * vulnerabilityMultiplier; //*2 *vulnerabilityMultiplier damage if target is vulnerable
            if (damageSource.getEntity() instanceof LilithEntity) {multiplier *= 1.5F;} //total *3 *multiplier if source is Lilith boss
        }
        return multiplier;
    }
}
