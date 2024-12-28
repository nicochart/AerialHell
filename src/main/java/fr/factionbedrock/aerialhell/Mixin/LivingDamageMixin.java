package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingDamageMixin
{
    @Inject(method = "damage", at = @At("RETURN"), cancellable = true)
    private void onDamage(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity damagedEntity = (LivingEntity) (Object) this;
        Entity sourceEntity = damageSource.getAttacker();

        float baseAmount = calculateBaseAmount(damagedEntity, damageSource, amount);
        float damageMultiplier = applyDamageEffectsAndCalculateDamageMultiplier(damagedEntity, damageSource, baseAmount);
        applyMultipliedDamage(damagedEntity, damageSource, baseAmount, damageMultiplier);
    }

    //target method (damage(..)) already dealt baseAmount damage
    //apply damage multiplier by applying damage diff (adding or removing health)
    private static void applyMultipliedDamage(LivingEntity damagedEntity, DamageSource source, float baseAmount, float multiplier)
    {
        if (multiplier == 1) {return;}
        else if (multiplier > 1)
        {
            float additionalDamage = (multiplier - 1) * baseAmount;
            float totalDamage = multiplier * baseAmount;
            if (totalDamage > damagedEntity.lastDamageTaken)
            {
                damagedEntity.applyDamage(source, additionalDamage);
                damagedEntity.lastDamageTaken = totalDamage;
            }
        }
        else //multiplier < 1
        {
            float healthToRestore = (1 - multiplier) * baseAmount;
            float totalDamage = multiplier * baseAmount;
            damagedEntity.setHealth(damagedEntity.getHealth() + healthToRestore);
            damagedEntity.lastDamageTaken = totalDamage;
        }
    }

    private static float calculateBaseAmount(LivingEntity damagedEntity, DamageSource source, float amount)
    {
        float baseAmount = amount;
        if (damagedEntity.blockedByShield(source)) {return 0.0F;}
        if (source.isIn(DamageTypeTags.IS_FREEZING) && damagedEntity.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {baseAmount *= 5.0F;}
        if (source.isIn(DamageTypeTags.DAMAGES_HELMET) && !damagedEntity.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {baseAmount *= 0.75F;}

        return baseAmount;
    }

    private static float applyDamageEffectsAndCalculateDamageMultiplier(LivingEntity target, DamageSource damageSource, float baseAmount)
    {
        float multiplier = 1;
        Entity attackerEntity = damageSource.getAttacker();

        multiplier *= applyEffectsDueToPotionEffects(damageSource, target);

        Item targetMainHandItem = target.getMainHandStack().getItem();
        multiplier *= getDamageMultiplierFromTargetMainHandItemStack(targetMainHandItem, target);

        if (attackerEntity instanceof LivingEntity sourceLiving)
        {
            Iterable<ItemStack> stuff = target.getArmorItems();
            multiplier *= applyEffectsBasedOnTargetEquippedArmor(stuff, sourceLiving, target);

            ItemStack mainHandItemStack = sourceLiving.getMainHandStack();
            multiplier *= applyEffectsBasedOnSourceHandEquippedItem(mainHandItemStack, sourceLiving, target, baseAmount);

            if (sourceLiving instanceof PlayerEntity sourcePlayer)
            {
                applyTraitorEffectIfNecessary(sourcePlayer, target);
            }
        }
        return multiplier;
    }

    private static float applyEffectsDueToPotionEffects(DamageSource damageSource, LivingEntity target)
    {
        float multiplier = 1.0F;
        if ((damageSource.isOf(DamageTypes.ON_FIRE) || damageSource.isOf(DamageTypes.ON_FIRE) || damageSource.isOf(DamageTypes.LAVA)) && target.hasStatusEffect(AerialHellMobEffects.GOD)) {multiplier = 0;} //target with Gods Effect has Fire Resistance
        if (EntityHelper.isLivingEntityVulnerable(target)) {
            int vulnerabilityMultiplier = target.getStatusEffect(AerialHellMobEffects.VULNERABILITY).getAmplifier() + 1;
            multiplier = 2.0F * vulnerabilityMultiplier; //*2 *vulnerabilityMultiplier damage if target is vulnerable
            if (damageSource.getAttacker() instanceof LilithEntity) {multiplier *= 1.5F;} //total *3 *multiplier if source is Lilith boss
        }
        return multiplier;
    }

    private static float getDamageMultiplierFromTargetMainHandItemStack(Item targetEquippedItem, LivingEntity target)
    {
        return targetEquippedItem == AerialHellItems.GLASS_CANON_SWORD ? 2.0F : 1.0F; //*2 damage if target has glass cannon sword
    }

    private static float applyEffectsBasedOnTargetEquippedArmor(Iterable<ItemStack> armorStuff, LivingEntity source, LivingEntity target)
    {
        float multiplier = 1.0F;
        for (ItemStack armorStack : armorStuff)
        {
            if (armorStack.isIn(AerialHellTags.Items.MAGMATIC_GEL)) //target equipped of any magmatic gel armor
            {
                if (!EntityHelper.isCreativePlayer(source))
                {
                    source.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 120, 1, true, false));
                }
            }
            if (armorStack.isIn(AerialHellTags.Items.ARSONIST)) //target equipped of any arsonist armor
            {
                source.setOnFireFor(5);
                if (target.getFireTicks() > 0) //damage reduction if player with arsonist armor is on fire
                {
                    multiplier *= 0.93F;
                }
            }
        }
        return multiplier;
    }

    private static float applyEffectsBasedOnSourceHandEquippedItem(ItemStack sourceEquippedItemStack, LivingEntity source, LivingEntity target, float amount)
    {
        Item sourceEquippedItem = sourceEquippedItemStack.getItem();
        float multiplier = 1.0F;
        if (sourceEquippedItemStack.isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source attacking target with any magmatic gel tool
        {
            int count = 0;
            for (ItemStack armorStack : source.getArmorItems()) {if (armorStack.isIn(AerialHellTags.Items.MAGMATIC_GEL)) {count++;}}
            int amplifier = count == 4 ? 1 : 0;
            target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 120, amplifier, true, false)));
        }
        else if (sourceEquippedItem == AerialHellItems.ABSOLUTE_ZERO_SWORD) //source attacking target with absolute zero sword
        {
            target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2, true, false)));
        }
        else if (sourceEquippedItemStack.isIn(AerialHellTags.Items.ARSONIST)) //source attacking target with any arsonist tool
        {
            target.setOnFireFor(5);
            if (source.getFireTicks() > 0)
            {
                multiplier *= 1.5F; //damage bonus when on fire
            }
        }
        else if (sourceEquippedItem == AerialHellItems.DISLOYAL_SWORD) //source attacking target with disloyal sword
        {
            target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0, true, false)));
            target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0, true, false)));
            target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 0, true, false)));
        }
        else if (sourceEquippedItem == AerialHellItems.GOD_SWORD) //source attacking target with god sword
        {
            target.setOnFireFor(5);
        }
        else if (sourceEquippedItem == AerialHellItems.REAPER_SCYTHE) //source attacking target with reaper scythe
        {
            if (!EntityHelper.isLivingEntityShadowImmune(target))
            {
                target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0, true, false)));
                target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1, true, false)));
                target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1, true, false)));
                target.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 70, 0, true, false)));
            }
            else
            {
                source.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.WITHER, 80, 2, true, false)));
            }
            source.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 60, 0, true, false)));
        }
        else if (sourceEquippedItem == AerialHellItems.CURSED_SWORD || sourceEquippedItem == AerialHellItems.CURSED_AXE) //source attacking target with cursed tool
        {
            float damage_return_amount = (EntityHelper.isLivingEntityShadowImmune(source) || EntityHelper.isLivingEntityVulnerable(target)) ? amount / 2 : amount;
            source.damage(AerialHellDamageTypes.getDamageSource(source.getWorld(), AerialHellDamageTypes.CURSED_TOOL), damage_return_amount);
            if (!EntityHelper.isLivingEntityShadowImmune(target))
            {
                int vulnerabilityAmplifier = (EntityHelper.isLightEntity(target) && !(target instanceof LunaticPriestEntity)) ? 1 : 0;
                target.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 40, vulnerabilityAmplifier));
            }
        }
        else if (sourceEquippedItem == AerialHellItems.SWORD_OF_LIGHT || sourceEquippedItem == AerialHellItems.AXE_OF_LIGHT || sourceEquippedItem == AerialHellItems.LUNATIC_SWORD || sourceEquippedItem == AerialHellItems.LUNATIC_AXE || sourceEquippedItem == AerialHellItems.LUNATIC_HOE || sourceEquippedItem == AerialHellItems.LUNATIC_SHOVEL || sourceEquippedItem == AerialHellItems.LUNATIC_PICKAXE || sourceEquippedItem == AerialHellItems.STELLAR_STONE_BREAKER) //source attacking target with light tool
        {
            if (EntityHelper.isShadowEntity(target))
            {
                multiplier *= (sourceEquippedItem == AerialHellItems.SWORD_OF_LIGHT || sourceEquippedItem == AerialHellItems.AXE_OF_LIGHT) ? 1.8F : 1.4F;
            }
        }
        else if (sourceEquippedItem == AerialHellItems.NETHERIAN_KING_SWORD && source.getWorld().getRegistryKey() == World.NETHER)
        {
            multiplier *= 2.0F;
        }
        return multiplier;
    }

    private static void applyTraitorEffectIfNecessary(LivingEntity sourceLivingEntity, LivingEntity target)
    {
        if (sourceLivingEntity instanceof PlayerEntity playerEntity && EntityHelper.isCreativePlayer(playerEntity))
        {
            if ((target instanceof CrystalGolemEntity || target instanceof LunaticPriestEntity) && EntityHelper.isLivingEntityMisleadingLunar(sourceLivingEntity))
            {
                playerEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.TRAITOR, 12000, 0));
            }
            else if ((target instanceof ShadowAutomatonEntity || target instanceof LilithEntity) && EntityHelper.isLivingEntityMisleadingShadow(sourceLivingEntity))
            {
                playerEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.TRAITOR, 12000, 0));
            }
        }
    }
}
