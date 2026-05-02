package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplate;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.ItemAbility;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleList;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class AerialHellItemAbilities
{
    private static final ActionModule.MobEffect.Builder GOD_EFFECT = ActionModule.MobEffect.builder(AerialHellMobEffects.GOD);
    private static final ActionModule.MobEffect.Builder JUMP_BOOST_EFFECT = ActionModule.MobEffect.builder(MobEffects.JUMP_BOOST);
    private static final ActionModule.MobEffect.Builder INVISIBILITY_EFFECT = ActionModule.MobEffect.builder(MobEffects.INVISIBILITY);
    private static final ActionModule.MobEffect.Builder RESISTANCE_EFFECT = ActionModule.MobEffect.builder(MobEffects.RESISTANCE);
    private static final ActionModule.MobEffect.Builder SLOWNESS_EFFECT = ActionModule.MobEffect.builder(MobEffects.SLOWNESS);
    private static final ActionModule.MobEffect.Builder SPEED_EFFECT = ActionModule.MobEffect.builder(MobEffects.SPEED);
    private static final ActionModule.MobEffect.Builder SLOW_FALLING_EFFECT = ActionModule.MobEffect.builder(MobEffects.SLOW_FALLING);
    private static final ActionModule.MobEffect.Builder REGENERATION_EFFECT = ActionModule.MobEffect.builder(MobEffects.REGENERATION);
    private static final ActionModule.MobEffect.Builder FIRE_RESISTANCE_EFFECT = ActionModule.MobEffect.builder(MobEffects.FIRE_RESISTANCE);
    private static final ActionModule.MobEffect.Builder SATURATION_EFFECT = ActionModule.MobEffect.builder(MobEffects.SATURATION);
    private static final ActionModule.MobEffect.Builder WEAKNESS_EFFECT = ActionModule.MobEffect.builder(MobEffects.WEAKNESS);
    private static final ActionModule.MobEffect.Builder STRENGTH_EFFECT = ActionModule.MobEffect.builder(MobEffects.STRENGTH);
    private static final ActionModule.MobEffect.Builder DOLPHINS_GRACE_EFFECT = ActionModule.MobEffect.builder(MobEffects.DOLPHINS_GRACE);
    private static final ActionModule.MobEffect.Builder WATER_BREATHING_EFFECT = ActionModule.MobEffect.builder(MobEffects.WATER_BREATHING);
    private static final ActionModule.MobEffect.Builder HEAD_IN_THE_CLOUDS_EFFECT = ActionModule.MobEffect.builder(AerialHellMobEffects.HEAD_IN_THE_CLOUDS);
    private static final ActionModule.MobEffect.Builder SHADOW_IMMUNITY_EFFECT = ActionModule.MobEffect.builder(AerialHellMobEffects.SHADOW_IMMUNITY);

    private static final ActionModule.RemoveMobEffect.Builder REMOVE_EFFECT = ActionModule.RemoveMobEffect.builder();

    private static final ConditionModule STACK_IN_MAIN_OR_OFF_HAND_PASSIVE = new ConditionModule((entity, stack, equipmentSlot) -> stack.is(entity.getMainHandItem().getItem()) || stack.is(entity.getOffhandItem().getItem()));

    private static final ActionModule.MobEffectList RANDOM_SWORD_RANDOM_EFFECT = ActionModule.MobEffectList.builder().addEffects((entity) ->
    {
        RandomSource rand = entity.getRandom();
        if (rand.nextFloat() < 0.25F) {return List.of(new MobEffectTemplate(MobEffects.SPEED, 750, 0));}
        else if (rand.nextFloat() < 0.3333F) {return List.of(new MobEffectTemplate(MobEffects.JUMP_BOOST, 750, 0));}
        else if (rand.nextFloat() < 0.5F) {return List.of(new MobEffectTemplate(MobEffects.RESISTANCE, 750, 0));}
        else {return List.of(new MobEffectTemplate(MobEffects.UNLUCK, 750, 0), new MobEffectTemplate(MobEffects.POISON, 80, 1));}
    }).build();

    private static final ActionModule LIFT_OFF = ActionModule.fromEntity((entity) -> entity.setDeltaMovement(entity.getDeltaMovement().add(0, 2.0F, 0)));

    private static final SideEffectModule.Cooldown NETHERIAN_KING_SWORD_COOLDOWN = SideEffectModule.Cooldown.builder().of(((entity) -> EntityHelper.hasFullArsonistStuff(entity) ? 300 : 600));

    private static final SideEffectModule.Cooldown LIGHT_AXE_COOLDOWN = SideEffectModule.Cooldown.builder().of(((entity) -> EntityHelper.hasFullLunaticStuff(entity) ? 160 : 320));
    private static final SideEffectModule.Cooldown LIGHT_SWORD_COOLDOWN = SideEffectModule.Cooldown.builder().of(((entity) -> EntityHelper.hasFullLunaticStuff(entity) ? 80 : 160));

    private static final ActionModule.ThrowProjectile.Builder THROW_PROJECTILE = ActionModule.ThrowProjectile.builder();

    private static final SideEffectModule.Cooldown.Builder COOLDOWN = SideEffectModule.Cooldown.builder();

    private static final ConditionModule HAS_POISON_OR_WITHER = ConditionModule.entityCondition((entity) -> entity.hasEffect(MobEffects.POISON) || entity.hasEffect(MobEffects.WITHER));

    private static final ConditionModule HAS_NO_SHADOW_STUFF = ConditionModule.entityCondition(EntityHelper::hasNoShadowStuff);
    private static final ConditionModule HAS_NO_HEAVY_STUFF = ConditionModule.entityCondition(EntityHelper::hasNoHeavyStuff);
    private static final ConditionModule HAS_NO_LUNATIC_STUFF = ConditionModule.entityCondition(EntityHelper::hasNoLunaticStuff);
    private static final ConditionModule HAS_FULL_VOLUCITE_STUFF = ConditionModule.entityCondition(EntityHelper::hasFullVoluciteStuff);
    private static final ConditionModule NOT_IN_WATER_OR_RAIN = ConditionModule.entityCondition(((entity) -> !entity.isInWaterOrRain()));
    private static final ConditionModule PLAYER_CAN_EAT = ConditionModule.entityCondition((entity -> entity instanceof Player player && player.canEat(false)));
    private static final ConditionModule PLAYER_SHIFT_KEY_DOWN = ConditionModule.entityCondition((Entity::isShiftKeyDown));
    private static final ConditionModule PLAYER_SHIFT_KEY_UP = PLAYER_SHIFT_KEY_DOWN.opposite();
    private static final SideEffectModule.DamageItem DAMAGE_ITEM = SideEffectModule.DamageItem.simple();
    private static final SideEffectModule.Shrink SHRINK_ONE_ITEM = SideEffectModule.Shrink.builder().simple();
    private static final SideEffectModule.Shrink.Builder SHRINK = SideEffectModule.Shrink.builder();
    private static final ActionModule.Sound ILLUSIONER_CAST_SPELL_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound CHAINED_GOD_ROAR_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound ENCHANTMENT_TABLE_USE_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_DRINK_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EAT_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EAT.value(), 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXTINGUISH_FIRE_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound CREEPER_PRIMED_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.CREEPER_PRIMED, 1.0F, 0.2F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXPLODE_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound GLASS_BREAK_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.GLASS_BREAK, 1.0F, 0.25F + 0.5F * entity.getRandom().nextFloat()));
    private static final ActionModule.Sound PARROT_IMITATE_MAGMA_CUBE_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(SoundEvents.PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + entity.getRandom().nextFloat()));
    private static final ActionModule.Sound SHURIKEN_SHOOT_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT, 1.0F, 1.0F / (entity.getRandom().nextFloat() * 0.4F + 0.8F)));
    private static final ActionModule.Sound FORGOTTEN_BATTLE_TRIDENT_USE_SOUND = new ActionModule.Sound((entity) -> new PlaySoundHelper(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE, 1.0F, 1.375F + 0.25F * entity.getRandom().nextFloat()));

    private static final ActionModule.Particle.Builder CLOUD_PARTICLES = ActionModule.Particle.builder(ParticleTypes.CLOUD);
    private static final ActionModule.Particle.Builder SMOKE_PARTICLES = ActionModule.Particle.builder(ParticleTypes.SMOKE);
    private static final ActionModule.Particle.Builder DRIPPING_WATER_PARTICLES = ActionModule.Particle.builder(ParticleTypes.DRIPPING_WATER);
    private static final ActionModule.Particle.Builder ENCHANT_PARTICLES = ActionModule.Particle.builder(ParticleTypes.ENCHANT);
    private static final ActionModule.Particle.Builder SPORE_BLOSSOM_AIR_PARTICLES = ActionModule.Particle.builder(ParticleTypes.SPORE_BLOSSOM_AIR);
    private static final ActionModule.Particle.Builder FLAME_PARTICLES = ActionModule.Particle.builder(ParticleTypes.FLAME);
    private static final ActionModule.Particle.Builder EXPLOSION_PARTICLES = ActionModule.Particle.builder(ParticleTypes.EXPLOSION);
    private static final ActionModule.Particle.Builder SHADOW_LIGHT_PARTICLES = ActionModule.Particle.builder(AerialHellParticleTypes.SHADOW_LIGHT);
    private static final ActionModule.Particle.Builder CRISMON_SPORE_PARTICLES = ActionModule.Particle.builder(ParticleTypes.CRIMSON_SPORE);

    //little test about summoning creatures
    private static final ActionModule INVOKE_CHAINED_GOD = ActionModule.create((entity, stack, equipment) -> {
        ChainedGodEntity god = AerialHellEntities.CHAINED_GOD.create(entity.level(), EntitySpawnReason.TRIGGERED);
        if (god != null)
        {
            god.setPos(entity.position());
            entity.level().addFreshEntity(god);
        }
    });

//    public static final ItemAbility YOUR_ABILITY_NAME = ItemAbility.builder()
//            .inheritsOf(OTHER_ABILITY)
//            .addPassiveModules(ModuleList.builder()
//                    .addActions()
//                    .addConditions()
//                    .addSideEffects()
//                    .build())
//            .addOnUseModules(ModuleList.builder()
//                    .addActions()
//                    .addConditions()
//                    .addSideEffects()
//                    .build())
//            .addOnHurtEnemyModules(ModuleList.builder()
//                    .addActions()
//                    .addConditions()
//                    .addSideEffects()
//                    .build())
//            .build();

    private static final ItemAbility NINJA_SWORD_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(CLOUD_PARTICLES.of(20), ILLUSIONER_CAST_SPELL_SOUND)
                    .addSideEffects(DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility NINJA_SWORD = ItemAbility.builder()
            .inheritsOf(NINJA_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(INVISIBILITY_EFFECT.withDuration(200), SPEED_EFFECT.withDuration(120))
                    .addSideEffects(COOLDOWN.of(400))
                    .build())
            .build();

    public static final ItemAbility NINJA_MASTER_SWORD = ItemAbility.builder()
            .inheritsOf(NINJA_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(INVISIBILITY_EFFECT.withDuration(220), SPEED_EFFECT.with(160, 1))
                    .addSideEffects(COOLDOWN.of(340))
                    .build())
            .build();

    public static final ItemAbility RANDOM_SWORD = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(RANDOM_SWORD_RANDOM_EFFECT, ENCHANT_PARTICLES.of(20), ENCHANTMENT_TABLE_USE_SOUND)
                    .addSideEffects(COOLDOWN.of(900), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility ANTIDOTE_SWORD = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(REMOVE_EFFECT.effects(MobEffects.POISON, MobEffects.WITHER), SPORE_BLOSSOM_AIR_PARTICLES.of(20), GENERIC_DRINK_SOUND)
                    .addConditions(HAS_POISON_OR_WITHER)
                    .addSideEffects(COOLDOWN.of(900), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility GLOUTON_SWORD = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(SATURATION_EFFECT.withDuration(1), REGENERATION_EFFECT.withDuration(40), GENERIC_EAT_SOUND)
                    .addConditions(PLAYER_CAN_EAT)
                    .addSideEffects(COOLDOWN.of(20), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility NETHERIAN_KING_SWORD = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(FIRE_RESISTANCE_EFFECT.withDuration(280), FLAME_PARTICLES.of(20), GENERIC_EXTINGUISH_FIRE_SOUND)
                    .addConditions(NOT_IN_WATER_OR_RAIN)
                    .addSideEffects(NETHERIAN_KING_SWORD_COOLDOWN, DAMAGE_ITEM)
                    .build())
            .build();

    private static final ItemAbility LIGHT_WEAPON_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(THROW_PROJECTILE.build(AerialHellEntities.LUNATIC_PROJECTILE, 0.7f, 0))
                    .addConditions(HAS_NO_SHADOW_STUFF)
                    .addSideEffects(DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility AXE_OF_LIGHT = ItemAbility.builder()
            .inheritsOf(LIGHT_WEAPON_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(WEAKNESS_EFFECT.with((entity) -> LIGHT_AXE_COOLDOWN.getCooldownDuration(entity) / 2, (entity) -> 2))
                    .addSideEffects(LIGHT_AXE_COOLDOWN)
                    .build())
            .build();

    public static final ItemAbility SWORD_OF_LIGHT = ItemAbility.builder()
            .inheritsOf(LIGHT_WEAPON_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(WEAKNESS_EFFECT.with((entity) -> LIGHT_SWORD_COOLDOWN.getCooldownDuration(entity) / 2, (entity) -> 2))
                    .addSideEffects(LIGHT_SWORD_COOLDOWN)
                    .build())
            .build();

    private static final ItemAbility VOLUCITE_POWER_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(CLOUD_PARTICLES.of(20), ILLUSIONER_CAST_SPELL_SOUND)
                    .addConditions(HAS_NO_HEAVY_STUFF)
                    .addSideEffects(COOLDOWN.of(250), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility HALF_VOLUCITE_POWER = ItemAbility.builder()
            .inheritsOf(VOLUCITE_POWER_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(SLOW_FALLING_EFFECT.withDuration(80))
                    .build())
            .build();

    public static final ItemAbility FULL_VOLUCITE_POWER = ItemAbility.builder()
            .inheritsOf(VOLUCITE_POWER_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(SLOW_FALLING_EFFECT.withDuration(120), HEAD_IN_THE_CLOUDS_EFFECT.with(100, 1))
                    .addConditions(HAS_FULL_VOLUCITE_STUFF)
                    .build())
            .build();

    private static final ItemAbility GLASS_CANNON_SWORD_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(EXPLOSION_PARTICLES.of(20))
                    .addSideEffects(DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility GLASS_CANNON_LIFTOFF = ItemAbility.builder()
            .inheritsOf(GLASS_CANNON_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(LIFT_OFF, SLOW_FALLING_EFFECT.with(200, 2), GENERIC_EXPLODE_SOUND)
                    .addConditions(PLAYER_SHIFT_KEY_UP)
                    .addSideEffects(COOLDOWN.of(600))
                    .build())
            .build();

    public static final ItemAbility GLASS_CANNON_ARMORED_GLASS = ItemAbility.builder()
            .inheritsOf(GLASS_CANNON_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(RESISTANCE_EFFECT.with(200, 1), SLOWNESS_EFFECT.with(100, 0), GLASS_BREAK_SOUND)
                    .addConditions(PLAYER_SHIFT_KEY_DOWN)
                    .addSideEffects(COOLDOWN.of(400))
                    .build())
            .build();

    public static final ItemAbility REAPER_WALK = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(INVISIBILITY_EFFECT.withDuration(200), SPEED_EFFECT.withDuration(120), SHADOW_IMMUNITY_EFFECT.withDuration(120), SHADOW_LIGHT_PARTICLES.of(20), ILLUSIONER_CAST_SPELL_SOUND)
                    .addConditions(HAS_NO_LUNATIC_STUFF)
                    .addSideEffects(COOLDOWN.of(600), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility MAGMA_CUBE = ItemAbility.builder()
            .addPassiveModules(ModuleList.builder()
                    .addActions(JUMP_BOOST_EFFECT.passiveBuild())
                    .addConditions(STACK_IN_MAIN_OR_OFF_HAND_PASSIVE)
                    .build())
            .addOnUseModules(ModuleList.builder()
                    .addActions(JUMP_BOOST_EFFECT.with(100, 2), CRISMON_SPORE_PARTICLES.of(20), PARROT_IMITATE_MAGMA_CUBE_SOUND)
                    .addSideEffects(COOLDOWN.of(400), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility FORGOTTEN_BATTLE_TRIDENT = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(WATER_BREATHING_EFFECT.withDuration(120), DOLPHINS_GRACE_EFFECT.withDuration(120), SPEED_EFFECT.withDuration(120), STRENGTH_EFFECT.withDuration(300), DRIPPING_WATER_PARTICLES.of(20), FORGOTTEN_BATTLE_TRIDENT_USE_SOUND)
                    .addSideEffects(COOLDOWN.of(540), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility GOD = ItemAbility.builder()
            .addPassiveModules(ModuleList.builder()
                    .addActions(GOD_EFFECT.passiveBuild())
                    .addConditions(STACK_IN_MAIN_OR_OFF_HAND_PASSIVE)
                    .build()
            ).build();

    public static final ItemAbility THROW_IRON_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.IRON_SHURIKEN, 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_GOLD_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.GOLD_SHURIKEN, 2.0F, 1.0F)).addSideEffects(COOLDOWN.of(7), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_DIAMOND_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.DIAMOND_SHURIKEN, 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_NETHERITE_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.NETHERITE_SHURIKEN, 1.6F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_RUBY_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.RUBY_SHURIKEN, 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_AZURITE_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.AZURITE_SHURIKEN, 2.0F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_MAGMATIC_GEL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, 1.7F, 1.5F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_VOLUCITE_GEL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.VOLUCITE_SHURIKEN, 1.6F, 0.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_OBSIDIAN_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.OBSIDIAN_SHURIKEN, 1.6F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_LUNATIC_CRYSTAL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, 1.8F, 0.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_ARSONIST_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.ARSONIST_SHURIKEN, 1.7F, 1.0F)).addSideEffects(COOLDOWN.of(9), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_LIGHTNING_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.LIGHTNING_SHURIKEN, 1.7F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
}