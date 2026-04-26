package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplate;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateListProvider;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class AerialHellItemAbilities
{
    private static final ActionModule.MobEffect GOD_EFFECT_PASSIVE = ActionModule.MobEffect.passive(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(AerialHellMobEffects.GOD, 32, 0, true, true, true))); //ambient = true to always display icon
    private static final ActionModule.MobEffect JUMP_BOOST_PASSIVE = ActionModule.MobEffect.passive(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.JUMP_BOOST, 32, 0, true, true, true))); //ambient = true to always display icon

    private static final ConditionModule STACK_IN_MAIN_OR_OFF_HAND_PASSIVE = ConditionModule.passive((entity, stack, equipmentSlot) -> stack.is(entity.getMainHandItem().getItem()) || stack.is(entity.getOffhandItem().getItem()));

    private static final ActionModule.MobEffect HALF_VOLUCITE_POWER_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.SLOW_FALLING, 80, 0)));
    private static final ActionModule.MobEffect FULL_VOLUCITE_POWER_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.SLOW_FALLING, 120, 0), new MobEffectTemplate(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 100, 1)));
    private static final ActionModule.MobEffect NINJA_EFFECT_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.INVISIBILITY, 200, 0), new MobEffectTemplate(MobEffects.SPEED, 120, 0)));
    private static final ActionModule.MobEffect NINJA_MASTER_EFFECT_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.INVISIBILITY, 220, 0), new MobEffectTemplate(MobEffects.SPEED, 160, 1)));
    private static final ActionModule.MobEffect RANDOM_EFFECT_ON_USE = ActionModule.MobEffect.onUse((entity) ->
    {
        RandomSource rand = entity.getRandom();
        if (rand.nextFloat() < 0.25F) {return List.of(new MobEffectTemplate(MobEffects.SPEED, 750, 0));}
        else if (rand.nextFloat() < 0.3333F) {return List.of(new MobEffectTemplate(MobEffects.JUMP_BOOST, 750, 0));}
        else if (rand.nextFloat() < 0.5F) {return List.of(new MobEffectTemplate(MobEffects.RESISTANCE, 750, 0));}
        else
        {
            return List.of(new MobEffectTemplate(MobEffects.UNLUCK, 750, 0), new MobEffectTemplate(MobEffects.POISON, 80, 1));
        }
    });
    private static final ActionModule.MobEffect GLOUTON_EFFECT_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.SATURATION, 1, 0), new MobEffectTemplate(MobEffects.REGENERATION, 40, 0)));
    private static final ActionModule.MobEffect ARMORED_GLASS_EFFECT_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.RESISTANCE, 200, 1), new MobEffectTemplate(MobEffects.SLOWNESS, 100, 0)));

    private static final ActionModule LIFTOFF_ON_USE = ActionModule.onUse((entity, stack, slot) -> entity.setDeltaMovement(entity.getDeltaMovement().add(0, 2.0F, 0)));

    private static final SideEffectModule.Cooldown LIGHT_AXE_COOLDOWN_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> EntityHelper.hasFullLunaticStuff(entity) ? 160 : 320));
    private static final SideEffectModule.Cooldown LIGHT_SWORD_COOLDOWN_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> EntityHelper.hasFullLunaticStuff(entity) ? 80 : 160));
    private static final ActionModule.MobEffect LIGHT_AXE_WEAKNESS_ON_USE = ActionModule.MobEffect.onUse((LivingEntity entity) -> List.of(new MobEffectTemplate(MobEffects.WEAKNESS, LIGHT_AXE_COOLDOWN_ON_USE.getCooldownDuration(entity) / 2, 2)));
    private static final ActionModule.MobEffect LIGHT_SWORD_WEAKNESS_ON_USE = ActionModule.MobEffect.onUse((LivingEntity entity) -> List.of(new MobEffectTemplate(MobEffects.WEAKNESS, LIGHT_SWORD_COOLDOWN_ON_USE.getCooldownDuration(entity) / 2, 2)));
    private static final ActionModule.ThrowProjectile LIGHT_PROJECTILE_ON_USE = ActionModule.ThrowProjectile.onUse(AerialHellEntities.LUNATIC_PROJECTILE.get(), 0.7f, 0);

    private static final SideEffectModule.Cooldown NETHERIAN_KING_SWORD_COOLDOWN_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> EntityHelper.hasFullArsonistStuff(entity) ? 300 : 600));

    private static final SideEffectModule.Cooldown COOLDOWN_20_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 20));
    private static final SideEffectModule.Cooldown COOLDOWN_250_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 250));
    private static final SideEffectModule.Cooldown COOLDOWN_340_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 340));
    private static final SideEffectModule.Cooldown COOLDOWN_400_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 400));
    private static final SideEffectModule.Cooldown COOLDOWN_600_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 600));
    private static final SideEffectModule.Cooldown COOLDOWN_900_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 900));

    private static final ConditionModule HAS_POISON_OR_WITHER_ON_USE = ConditionModule.onUse((entity) -> entity.hasEffect(MobEffects.POISON) || entity.hasEffect(MobEffects.WITHER));

    private static final ActionModule.RemoveMobEffect REMOVE_POISON_ON_USE = ActionModule.RemoveMobEffect.onUse(MobEffects.POISON);
    private static final ActionModule.RemoveMobEffect REMOVE_WITHER_ON_USE = ActionModule.RemoveMobEffect.onUse(MobEffects.WITHER);

    private static final ActionModule.MobEffect FIRE_RESISTANCE_280_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.FIRE_RESISTANCE, 280, 0)));
    private static final ActionModule.MobEffect SLOW_FALLING_III_200_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.SLOW_FALLING, 200, 2)));
    private static final ActionModule.MobEffect JUMP_BOOST_III_100_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(MobEffects.JUMP_BOOST, 100, 2)));
    private static final ActionModule.MobEffect SHADOW_IMMUNITY_120_ON_USE = ActionModule.MobEffect.onUse(MobEffectTemplateListProvider.createFromTemplateList(new MobEffectTemplate(AerialHellMobEffects.SHADOW_IMMUNITY, 120, 0)));
    private static final ConditionModule HAS_NO_SHADOW_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasNoShadowStuff);
    private static final ConditionModule HAS_NO_HEAVY_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasNoHeavyStuff);
    private static final ConditionModule HAS_NO_LUNATIC_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasNoLunaticStuff);
    private static final ConditionModule HAS_FULL_VOLUCITE_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasFullVoluciteStuff);
    private static final ConditionModule NOT_IN_WATER_OR_RAIN_ON_USE = ConditionModule.onUse(((entity) -> !entity.isInWaterOrRain()));
    private static final ConditionModule PLAYER_CAN_EAT_ON_USE = ConditionModule.onUse((entity -> entity instanceof Player player && player.canEat(false)));
    private static final ConditionModule PLAYER_SHIFT_KEY_DOWN_ON_USE = ConditionModule.onUse((Entity::isShiftKeyDown));
    private static final SideEffectModule.DamageItem DAMAGE_ITEM_ON_USE = SideEffectModule.DamageItem.onUse();
    private static final ActionModule.Sound ILLUSIONER_CAST_SPELL_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound ENCHANTMENT_TABLE_USE_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_DRINK_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EAT_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EAT.value(), 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXTINGUISH_FIRE_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound CREEPER_PRIMED_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.CREEPER_PRIMED, 1.0F, 0.2F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXPLODE_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GLASS_BREAK_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.GLASS_BREAK, 1.0F, 0.25F + 0.5F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound PARROT_IMITATE_MAGMA_CUBE_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Particle CLOUD_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.CLOUD);
    private static final ActionModule.Particle ENCHANT_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.ENCHANT);
    private static final ActionModule.Particle SPORE_BLOSSOM_AIR_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.SPORE_BLOSSOM_AIR);
    private static final ActionModule.Particle FLAME_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.FLAME);
    private static final ActionModule.Particle EXPLOSION_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.EXPLOSION);
    private static final ActionModule.Particle SHADOW_LIGHT_PARTICLES_ON_USE = ActionModule.Particle.onUse(AerialHellParticleTypes.SHADOW_LIGHT.get());
    private static final ActionModule.Particle CRISMON_SPORE_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.CRIMSON_SPORE);

    private static final ItemAbility NINJA_SWORD_COMMON = new ItemAbility(ILLUSIONER_CAST_SPELL_SOUND_ON_USE, DAMAGE_ITEM_ON_USE, CLOUD_PARTICLES_ON_USE);
    public static final ItemAbility NINJA_SWORD = NINJA_SWORD_COMMON.copy().addModules(NINJA_EFFECT_ON_USE, COOLDOWN_400_ON_USE);
    public static final ItemAbility NINJA_MASTER_SWORD = NINJA_SWORD_COMMON.copy().addModules(NINJA_MASTER_EFFECT_ON_USE, COOLDOWN_340_ON_USE);

    public static final ItemAbility RANDOM_SWORD = new ItemAbility(RANDOM_EFFECT_ON_USE, ENCHANT_PARTICLES_ON_USE, ENCHANTMENT_TABLE_USE_SOUND_ON_USE, COOLDOWN_900_ON_USE, DAMAGE_ITEM_ON_USE);

    public static final ItemAbility ANTIDOTE_SWORD = new ItemAbility(REMOVE_POISON_ON_USE, REMOVE_WITHER_ON_USE, SPORE_BLOSSOM_AIR_PARTICLES_ON_USE, GENERIC_DRINK_SOUND_ON_USE, HAS_POISON_OR_WITHER_ON_USE, COOLDOWN_900_ON_USE, DAMAGE_ITEM_ON_USE);

    public static final ItemAbility GLOUTON_SWORD = new ItemAbility(GLOUTON_EFFECT_ON_USE, PLAYER_CAN_EAT_ON_USE, COOLDOWN_20_ON_USE, GENERIC_EAT_SOUND_ON_USE, DAMAGE_ITEM_ON_USE);

    public static final ItemAbility NETHERIAN_KING_SWORD = new ItemAbility(FIRE_RESISTANCE_280_ON_USE, NOT_IN_WATER_OR_RAIN_ON_USE, FLAME_PARTICLES_ON_USE, GENERIC_EXTINGUISH_FIRE_SOUND_ON_USE, NETHERIAN_KING_SWORD_COOLDOWN_ON_USE, DAMAGE_ITEM_ON_USE);

    private static final ItemAbility LIGHT_WEAPON_COMMON = new ItemAbility(DAMAGE_ITEM_ON_USE, LIGHT_PROJECTILE_ON_USE, HAS_NO_SHADOW_STUFF_ON_USE);
    public static final ItemAbility AXE_OF_LIGHT = LIGHT_WEAPON_COMMON.copy().addModules(LIGHT_AXE_COOLDOWN_ON_USE, LIGHT_AXE_WEAKNESS_ON_USE);
    public static final ItemAbility SWORD_OF_LIGHT = LIGHT_WEAPON_COMMON.copy().addModules(LIGHT_SWORD_COOLDOWN_ON_USE, LIGHT_SWORD_WEAKNESS_ON_USE);

    private static final ItemAbility VOLUCITE_POWER_COMMON = new ItemAbility(HAS_NO_HEAVY_STUFF_ON_USE, ILLUSIONER_CAST_SPELL_SOUND_ON_USE, CLOUD_PARTICLES_ON_USE, COOLDOWN_250_ON_USE, DAMAGE_ITEM_ON_USE);
    public static final ItemAbility HALF_VOLUCITE_POWER = VOLUCITE_POWER_COMMON.copy().addModule(HALF_VOLUCITE_POWER_ON_USE);
    public static final ItemAbility FULL_VOLUCITE_POWER = VOLUCITE_POWER_COMMON.copy().addModules(HAS_FULL_VOLUCITE_STUFF_ON_USE, FULL_VOLUCITE_POWER_ON_USE).setFallback(HALF_VOLUCITE_POWER);

    private static final ItemAbility GLASS_CANON_SWORD_COMMON = new ItemAbility(EXPLOSION_PARTICLES_ON_USE, DAMAGE_ITEM_ON_USE);
    private static final ItemAbility LIFTOFF = GLASS_CANON_SWORD_COMMON.copy().addModules(LIFTOFF_ON_USE, SLOW_FALLING_III_200_ON_USE, GENERIC_EXPLODE_SOUND_ON_USE, COOLDOWN_600_ON_USE);
    private static final ItemAbility ARMORED_GLASS = GLASS_CANON_SWORD_COMMON.copy().addModules(PLAYER_SHIFT_KEY_DOWN_ON_USE, ARMORED_GLASS_EFFECT_ON_USE, GLASS_BREAK_SOUND_ON_USE, COOLDOWN_400_ON_USE);
    public static final ItemAbility GLASS_CANON_SWORD = ARMORED_GLASS.copy().setFallback(LIFTOFF);

    public static final ItemAbility REAPER_WALK = new ItemAbility(HAS_NO_LUNATIC_STUFF_ON_USE, NINJA_EFFECT_ON_USE, SHADOW_IMMUNITY_120_ON_USE, SHADOW_LIGHT_PARTICLES_ON_USE, ILLUSIONER_CAST_SPELL_SOUND_ON_USE, COOLDOWN_600_ON_USE, DAMAGE_ITEM_ON_USE);

    public static final ItemAbility MAGMA_CUBE = new ItemAbility(STACK_IN_MAIN_OR_OFF_HAND_PASSIVE, JUMP_BOOST_PASSIVE, JUMP_BOOST_III_100_ON_USE, CRISMON_SPORE_PARTICLES_ON_USE, PARROT_IMITATE_MAGMA_CUBE_SOUND_ON_USE, COOLDOWN_400_ON_USE, DAMAGE_ITEM_ON_USE);

    public static final ItemAbility GOD = new ItemAbility(STACK_IN_MAIN_OR_OFF_HAND_PASSIVE, GOD_EFFECT_PASSIVE);
}
