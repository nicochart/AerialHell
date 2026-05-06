package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplate;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.Ability.Module.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class AerialHellItemAbilities
{
    private static final ActionModule.MobEffect.Builder GOD_EFFECT_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(AerialHellMobEffects.GOD);
    private static final ActionModule.MobEffect.Builder JUMP_BOOST_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.JUMP_BOOST);
    private static final ActionModule.MobEffect.Builder INVISIBILITY_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.INVISIBILITY);
    private static final ActionModule.MobEffect.Builder RESISTANCE_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.RESISTANCE);
    private static final ActionModule.MobEffect.Builder SLOWNESS_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.SLOWNESS);
    private static final ActionModule.MobEffect.Builder SPEED_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.SPEED);
    private static final ActionModule.MobEffect.Builder POISON_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.POISON);
    private static final ActionModule.MobEffect.Builder WITHER_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.WITHER);
    private static final ActionModule.MobEffect.Builder SLOW_FALLING_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.SLOW_FALLING);
    private static final ActionModule.MobEffect.Builder REGENERATION_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.REGENERATION);
    private static final ActionModule.MobEffect.Builder FIRE_RESISTANCE_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.FIRE_RESISTANCE);
    private static final ActionModule.MobEffect.Builder SATURATION_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.SATURATION);
    private static final ActionModule.MobEffect.Builder BLINDNESS_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.BLINDNESS);
    private static final ActionModule.MobEffect.Builder WEAKNESS_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.WEAKNESS);
    private static final ActionModule.MobEffect.Builder MINING_FATIGUE_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.MINING_FATIGUE);
    private static final ActionModule.MobEffect.Builder STRENGTH_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.STRENGTH);
    private static final ActionModule.MobEffect.Builder DOLPHINS_GRACE_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.DOLPHINS_GRACE);
    private static final ActionModule.MobEffect.Builder WATER_BREATHING_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(MobEffects.WATER_BREATHING);
    private static final ActionModule.MobEffect.Builder HEAD_IN_THE_CLOUDS_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(AerialHellMobEffects.HEAD_IN_THE_CLOUDS);
    private static final ActionModule.MobEffect.Builder SHADOW_IMMUNITY_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(AerialHellMobEffects.SHADOW_IMMUNITY);
    private static final ActionModule.MobEffect.Builder VULNERABILITY_TO_SELF = ActionModule.MobEffect.toOwnerBuilder(AerialHellMobEffects.VULNERABILITY);

    private static final ActionModule.MobEffect.Builder SLOWNESS_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.SLOWNESS);
    private static final ActionModule.MobEffect.Builder POISON_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.POISON);
    private static final ActionModule.MobEffect.Builder WITHER_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.WITHER);
    private static final ActionModule.MobEffect.Builder BLINDNESS_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.BLINDNESS);
    private static final ActionModule.MobEffect.Builder WEAKNESS_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.WEAKNESS);
    private static final ActionModule.MobEffect.Builder MINING_FATIGUE_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(MobEffects.MINING_FATIGUE);
    private static final ActionModule.MobEffect.Builder VULNERABILITY_TO_OTHER = ActionModule.MobEffect.toOtherBuilder(AerialHellMobEffects.VULNERABILITY);
    
    private static final ActionModule.MultiplyDamage.Builder MULTIPLY_DAMAGE = ActionModule.MultiplyDamage.builder();

    private static final ActionModule.RemoveMobEffect.Builder REMOVE_EFFECT = ActionModule.RemoveMobEffect.builder();

    private static final ConditionModule IN_MAIN_OR_OFF_HAND = new ConditionModule((stack, itemOwner, equipmentSlot, damageInfo) -> stack.is(itemOwner.getMainHandItem().getItem()) || stack.is(itemOwner.getOffhandItem().getItem()));
    private static final ConditionModule IN_MAIN_HAND = new ConditionModule((stack, itemOwner, equipmentSlot, damageInfo) -> stack.is(itemOwner.getMainHandItem().getItem()));
    private static final ConditionModule IN_RIGHT_SLOT = new ConditionModule((stack, itemOwner, equipmentSlot, damageInfo) -> equipmentSlot == itemOwner.getEquipmentSlotForItem(stack));

    private static final ActionModule.MobEffectList RANDOM_SWORD_RANDOM_EFFECT = ActionModule.MobEffectList.builder().addEffects((itemOwner) ->
    {
        RandomSource rand = itemOwner.getRandom();
        if (rand.nextFloat() < 0.25F) {return List.of(new MobEffectTemplate(MobEffects.SPEED, 750, 0));}
        else if (rand.nextFloat() < 0.3333F) {return List.of(new MobEffectTemplate(MobEffects.JUMP_BOOST, 750, 0));}
        else if (rand.nextFloat() < 0.5F) {return List.of(new MobEffectTemplate(MobEffects.RESISTANCE, 750, 0));}
        else {return List.of(new MobEffectTemplate(MobEffects.UNLUCK, 750, 0), new MobEffectTemplate(MobEffects.POISON, 80, 1));}
    }).toOwnerBuild();

    private static final ActionModule CURSED_TOOL_INTERACTION = ActionModule.create((stack, itemOwner, equipmentSlot, damageInfo) ->
    {
        if (damageInfo == null || !(damageInfo.otherEntity() instanceof LivingEntity livingOther)) {return;}
        boolean otherIsLight = EntityHelper.isLightEntity(livingOther) || EntityHelper.hasFullLunaticStuff(livingOther);
        float amount = EntityHelper.isLivingEntityShadowImmune(livingOther) ? 6.0F : !otherIsLight ? 2.0F : 0.0F;
        if (amount != 0.0F) {itemOwner.hurt(AerialHellDamageTypes.getDamageSource(itemOwner.level(), AerialHellDamageTypes.CURSED_TOOL), amount);}
        livingOther.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, otherIsLight ? 1 : 0));
    });

    private static final ActionModule SLOW_DOWN_NEARBY_ENTITIES = ActionModule.create((stack, itemOwner, equipmentSlot, damageInfo) ->
    {
        List<LivingEntity> nearbyEntities = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(itemOwner, 6, (entity) -> !EntityHelper.isCreaOrSpecPlayer(entity));
        for (LivingEntity nearbyEntity : nearbyEntities)
        {
            nearbyEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 120, 3));
            if (itemOwner.level() instanceof ServerLevel serverLevel)
            {
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, nearbyEntity.getX(), nearbyEntity.getY(0.5), nearbyEntity.getZ(), 10, 0.5F, 0.5F, 0.5F, 0.4F);
            }
        }
    });

    private static final ActionModule LIFT_OFF = ActionModule.onItemOwner((itemOwner) -> itemOwner.setDeltaMovement(itemOwner.getDeltaMovement().add(0, 2.0F, 0)));

    private static final ActionModule IGNITE_OTHER = ActionModule.onOther((otherEntity) -> otherEntity.igniteForSeconds(5));

    private static final SideEffectModule.Cooldown NETHERIAN_KING_SWORD_COOLDOWN = SideEffectModule.Cooldown.builder().of(((itemOwner) -> EntityHelper.hasFullArsonistStuff(itemOwner) ? 300 : 600));

    private static final SideEffectModule.Cooldown LIGHT_WEAPON_COOLDOWN = SideEffectModule.Cooldown.builder().of(((itemOwner) -> EntityHelper.hasFullLunaticStuff(itemOwner) ? 80 : 160));

    private static final ActionModule.ThrowProjectile.Builder THROW_PROJECTILE = ActionModule.ThrowProjectile.builder();

    private static final SideEffectModule.Cooldown.Builder COOLDOWN = SideEffectModule.Cooldown.builder();

    private static final ConditionModule HAS_POISON_OR_WITHER = ConditionModule.itemOwnerCondition((itemOwner) -> itemOwner.hasEffect(MobEffects.POISON) || itemOwner.hasEffect(MobEffects.WITHER));

    private static final ConditionModule HAS_NO_SHADOW_STUFF = ConditionModule.itemOwnerCondition(EntityHelper::hasNoShadowStuff);
    private static final ConditionModule HAS_NO_HEAVY_STUFF = ConditionModule.itemOwnerCondition(EntityHelper::hasNoHeavyStuff);
    private static final ConditionModule HAS_NO_LUNATIC_STUFF = ConditionModule.itemOwnerCondition(EntityHelper::hasNoLunaticStuff);
    private static final ConditionModule HAS_FULL_VOLUCITE_STUFF = ConditionModule.itemOwnerCondition(EntityHelper::hasFullVoluciteStuff);
    private static final ConditionModule NOT_IN_WATER_OR_RAIN = ConditionModule.itemOwnerCondition(((itemOwner) -> !itemOwner.isInWaterOrRain()));
    private static final ConditionModule PLAYER_CAN_EAT = ConditionModule.itemOwnerCondition((itemOwner -> itemOwner instanceof Player playerOwner && playerOwner.canEat(false)));
    private static final ConditionModule PLAYER_SHIFT_KEY_DOWN = ConditionModule.itemOwnerCondition(Entity::isShiftKeyDown);
    private static final ConditionModule PLAYER_SHIFT_KEY_UP = PLAYER_SHIFT_KEY_DOWN.opposite();
    private static final ConditionModule ITEM_OWNER_IS_VULNERABLE = ConditionModule.itemOwnerCondition(EntityHelper::isLivingEntityVulnerable);
    private static final ConditionModule ITEM_OWNER_IS_NOT_VULNERABLE = ITEM_OWNER_IS_VULNERABLE.opposite();
    private static final ConditionModule OTHER_IS_CREATIVE_PLAYER = ConditionModule.otherEntityCondition(EntityHelper::isCreativePlayer);
    private static final ConditionModule OTHER_IS_NOT_CREATIVE_PLAYER = OTHER_IS_CREATIVE_PLAYER.opposite();
    private static final ConditionModule OTHER_IS_SHADOW_IMMUNE = ConditionModule.otherEntityCondition(EntityHelper::isLivingEntityShadowImmune);
    private static final ConditionModule OTHER_IS_NOT_SHADOW_IMMUNE = OTHER_IS_SHADOW_IMMUNE.opposite();
    private static final ConditionModule OTHER_IS_LIGHT_ENTITY = ConditionModule.otherEntityCondition(EntityHelper::isLightEntity);
    private static final ConditionModule OTHER_IS_NOT_LIGHT_ENTITY = OTHER_IS_LIGHT_ENTITY.opposite();
    private static final SideEffectModule.DamageItem DAMAGE_ITEM = SideEffectModule.DamageItem.simple();
    private static final SideEffectModule.Shrink SHRINK_ONE_ITEM = SideEffectModule.Shrink.builder().simple();
    private static final SideEffectModule.Shrink.Builder SHRINK = SideEffectModule.Shrink.builder();
    private static final ActionModule.Sound ILLUSIONER_CAST_SPELL_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.375F + 0.25F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound CHAINED_GOD_ROAR_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR.get(), 1.0F, 1.375F + 0.25F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound ENCHANTMENT_TABLE_USE_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.375F + 0.25F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_DRINK_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EAT_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.GENERIC_EAT.value(), 1.0F, 0.5F + itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXTINGUISH_FIRE_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound CREEPER_PRIMED_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.CREEPER_PRIMED, 1.0F, 0.2F + itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound GENERIC_EXPLODE_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 0.5F + itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound GLASS_BREAK_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.GLASS_BREAK, 1.0F, 0.25F + 0.5F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound PARROT_IMITATE_MAGMA_CUBE_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(SoundEvents.PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound SHURIKEN_SHOOT_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT.get(), 1.0F, 1.0F / (itemOwner.getRandom().nextFloat() * 0.4F + 0.8F)));
    private static final ActionModule.Sound FORGOTTEN_BATTLE_TRIDENT_USE_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE.get(), 1.0F, 1.375F + 0.25F * itemOwner.getRandom().nextFloat()));
    private static final ActionModule.Sound ICE_SPIRIT_DEATH_SOUND = new ActionModule.Sound((itemOwner) -> new PlaySoundHelper(AerialHellSoundEvents.ENTITY_ICE_SPIRIT_DEATH.get(), 1.0F, 0.875F + 0.25F * itemOwner.getRandom().nextFloat()));

    private static final ActionModule.Particle.Builder CLOUD_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.CLOUD);
    private static final ActionModule.Particle.Builder SMOKE_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.SMOKE);
    private static final ActionModule.Particle.Builder SNOWFLAKE_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.SNOWFLAKE);
    private static final ActionModule.Particle.Builder DRIPPING_WATER_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.DRIPPING_WATER);
    private static final ActionModule.Particle.Builder ENCHANT_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.ENCHANT);
    private static final ActionModule.Particle.Builder SPORE_BLOSSOM_AIR_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.SPORE_BLOSSOM_AIR);
    private static final ActionModule.Particle.Builder FLAME_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.FLAME);
    private static final ActionModule.Particle.Builder EXPLOSION_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.EXPLOSION);
    private static final ActionModule.Particle.Builder SHADOW_LIGHT_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(AerialHellParticleTypes.SHADOW_LIGHT.get());
    private static final ActionModule.Particle.Builder CRISMON_SPORE_PARTICLES_ON_SELF = ActionModule.Particle.onOwnerBuilder(ParticleTypes.CRIMSON_SPORE);

    private static final ActionModule.Particle.Builder SNOWFLAKE_PARTICLES_ON_OTHER = ActionModule.Particle.onOtherBuilder(ParticleTypes.SNOWFLAKE);

    //little test about summoning creatures
    private static final ActionModule INVOKE_CHAINED_GOD = ActionModule.create((stack, itemOwner, equipmentSlot, damageInfo) -> {
        ChainedGodEntity god = AerialHellEntities.CHAINED_GOD.get().create(itemOwner.level(), EntitySpawnReason.TRIGGERED);
        if (god != null)
        {
            god.setPos(itemOwner.position());
            itemOwner.level().addFreshEntity(god);
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
//            .addOnDealDamageModules(ModuleList.builder()
//                    .addActions()
//                    .addConditions()
//                    .addSideEffects()
//                    .build())
//            .addOnTakeDamageModules(ModuleList.builder()
//                    .addActions()
//                    .addConditions()
//                    .addSideEffects()
//                    .build())
//            .build();

    private static final ItemAbility NINJA_SWORD_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            CLOUD_PARTICLES_ON_SELF.of(20),
                            ILLUSIONER_CAST_SPELL_SOUND)
                    .addSideEffects(DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility NINJA_SWORD = ItemAbility.builder()
            .setDescId("ninja")
            .inheritsOf(NINJA_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            INVISIBILITY_TO_SELF.withDuration(200),
                            SPEED_TO_SELF.withDuration(120))
                    .addSideEffects(COOLDOWN.of(400))
                    .build())
            .build();

    public static final ItemAbility NINJA_MASTER_SWORD = ItemAbility.builder()
            .setDescId("ninja_master")
            .inheritsOf(NINJA_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            INVISIBILITY_TO_SELF.withDuration(220),
                            SPEED_TO_SELF.with(160, 1))
                    .addSideEffects(COOLDOWN.of(340))
                    .build())
            .build();

    public static final ItemAbility RANDOM_SWORD = ItemAbility.builder()
            .setDescId("random_sword")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            RANDOM_SWORD_RANDOM_EFFECT,
                            ENCHANT_PARTICLES_ON_SELF.of(20),
                            ENCHANTMENT_TABLE_USE_SOUND)
                    .addSideEffects(COOLDOWN.of(900), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility ANTIDOTE_SWORD = ItemAbility.builder()
            .setDescId("antidote_sword")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            REMOVE_EFFECT.effects(MobEffects.POISON, MobEffects.WITHER),
                            SPORE_BLOSSOM_AIR_PARTICLES_ON_SELF.of(20),
                            GENERIC_DRINK_SOUND)
                    .addConditions(HAS_POISON_OR_WITHER)
                    .addSideEffects(COOLDOWN.of(900), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility GLOUTON_SWORD = ItemAbility.builder()
            .setDescId("glouton_sword")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            SATURATION_TO_SELF.withDuration(1),
                            REGENERATION_TO_SELF.withDuration(40), GENERIC_EAT_SOUND)
                    .addConditions(PLAYER_CAN_EAT)
                    .addSideEffects(COOLDOWN.of(20), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility NETHERIAN_KING_SWORD = ItemAbility.builder()
            .setDescId("netherian_king")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            FIRE_RESISTANCE_TO_SELF.withDuration(280),
                            FLAME_PARTICLES_ON_SELF.of(20), GENERIC_EXTINGUISH_FIRE_SOUND)
                    .addConditions(NOT_IN_WATER_OR_RAIN)
                    .addSideEffects(NETHERIAN_KING_SWORD_COOLDOWN, DAMAGE_ITEM)
                    .build())
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(IGNITE_OTHER, MULTIPLY_DAMAGE.by((itemOwner) -> itemOwner.level().dimension() == Level.NETHER ? 2.0F : 1.0F, TestTarget.ITEM_OWNER))
                    .build())
            .build();

    public static final ItemAbility SPREAD_LIGHT = ItemAbility.builder()
            .setDescId("spread_light")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            THROW_PROJECTILE.build(AerialHellEntities.LUNATIC_PROJECTILE.get(), 0.7f, 0),
                            WEAKNESS_TO_SELF.with((itemOwner) -> LIGHT_WEAPON_COOLDOWN.getCooldownDuration(itemOwner) / 3, (itemOwner) -> 2, TestTarget.ITEM_OWNER))
                    .addConditions(HAS_NO_SHADOW_STUFF)
                    .addSideEffects(LIGHT_WEAPON_COOLDOWN, DAMAGE_ITEM)
                    .build())
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(MULTIPLY_DAMAGE.by((otherEntity) -> (EntityHelper.isShadowEntity(otherEntity) || EntityHelper.hasFullShadowStuff(otherEntity)) ? 1.8F : 1.0F, TestTarget.OTHER))
                    .addConditions(HAS_NO_SHADOW_STUFF)
                    .build())
            .build();

    public static final ItemAbility LUNAR_TOOL = ItemAbility.builder()
            .setDescId("lunar_tool")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(MULTIPLY_DAMAGE.by((otherEntity) -> (EntityHelper.isShadowEntity(otherEntity) || EntityHelper.hasFullShadowStuff(otherEntity)) ? 1.4F : 1.0F, TestTarget.OTHER))
                    .addConditions(HAS_NO_SHADOW_STUFF)
                    .build())
            .build();

    public static final ItemAbility VOLUCITE_POWER = ItemAbility.builder()
            .setDescId("volucite_power")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            SLOW_FALLING_TO_SELF.with((itemOwner) -> EntityHelper.hasFullVoluciteStuff(itemOwner) ? 120 : 80, (itemOwner) -> 0, TestTarget.ITEM_OWNER),
                            HEAD_IN_THE_CLOUDS_TO_SELF.with((itemOwner) -> EntityHelper.hasFullVoluciteStuff(itemOwner) ? 100 : -1, (itemOwner) -> 1, TestTarget.ITEM_OWNER),
                            CLOUD_PARTICLES_ON_SELF.of(20),
                            ILLUSIONER_CAST_SPELL_SOUND)
                    .addConditions(HAS_NO_HEAVY_STUFF)
                    .addSideEffects(COOLDOWN.of(250), DAMAGE_ITEM)
                    .build())
            .build();

    private static final ItemAbility GLASS_CANNON_SWORD_COMMON = ItemAbility.builder()
            .addOnUseModules(ModuleList.builder()
                    .addActions(EXPLOSION_PARTICLES_ON_SELF.of(20))
                    .addSideEffects(DAMAGE_ITEM)
                    .build())
            .addOnTakeDamageModules(ModuleList.builder()
                    .addActions(MULTIPLY_DAMAGE.by(2.0F))
                    .addConditions(IN_MAIN_HAND)
                    .build())
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(MULTIPLY_DAMAGE.by(2.0F))
                    .addConditions(IN_MAIN_HAND)
                    .build())
            .build();

    public static final ItemAbility GLASS_CANNON_LIFTOFF = ItemAbility.builder()
            .setDescId("glass_cannon_liftoff")
            .inheritsOf(GLASS_CANNON_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(LIFT_OFF, SLOW_FALLING_TO_SELF.with(200, 2), GENERIC_EXPLODE_SOUND)
                    .addConditions(PLAYER_SHIFT_KEY_UP)
                    .addSideEffects(COOLDOWN.of(600))
                    .build())
            .build();

    public static final ItemAbility GLASS_CANNON_ARMORED_GLASS = ItemAbility.builder()
            .setDescId("glass_cannon_armored_glass")
            .inheritsOf(GLASS_CANNON_SWORD_COMMON)
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            RESISTANCE_TO_SELF.with(200, 1),
                            SLOWNESS_TO_SELF.with(100, 0),
                            GLASS_BREAK_SOUND)
                    .addConditions(PLAYER_SHIFT_KEY_DOWN)
                    .addSideEffects(COOLDOWN.of(400))
                    .build())
            .build();

    public static final ItemAbility REAPER_SCYTHE = ItemAbility.builder()
            .setDescId("reaper_scythe")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            INVISIBILITY_TO_SELF.withDuration(200),
                            SPEED_TO_SELF.withDuration(120),
                            SHADOW_IMMUNITY_TO_SELF.withDuration(120),
                            SHADOW_LIGHT_PARTICLES_ON_SELF.of(20),
                            ILLUSIONER_CAST_SPELL_SOUND)
                    .addConditions(HAS_NO_LUNATIC_STUFF)
                    .addSideEffects(COOLDOWN.of(600), DAMAGE_ITEM)
                    .build())
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(
                            VULNERABILITY_TO_SELF.with(60, 0),
                            //if other (hit target) is not shadow entity nor shadow immune
                            BLINDNESS_TO_OTHER.with((other) -> !EntityHelper.isShadowEntity(other) && !EntityHelper.isLivingEntityShadowImmune(other) ? 100 : -1, (other) -> 0, TestTarget.OTHER),
                            WEAKNESS_TO_OTHER.with((other) -> !EntityHelper.isShadowEntity(other) && !EntityHelper.isLivingEntityShadowImmune(other) ? 100 : -1, (other) -> 1, TestTarget.OTHER),
                            SLOWNESS_TO_OTHER.with((other) -> !EntityHelper.isShadowEntity(other) && !EntityHelper.isLivingEntityShadowImmune(other) ? 100 : -1, (other) -> 1, TestTarget.OTHER),
                            VULNERABILITY_TO_OTHER.with((other) -> !EntityHelper.isShadowEntity(other) && !EntityHelper.isLivingEntityShadowImmune(other) ? 70 : -1, (other) -> 0, TestTarget.OTHER),
                            //if other (hit target) is shadow entity or shadow immune
                            WITHER_TO_SELF.with((other) -> EntityHelper.isShadowEntity(other) || EntityHelper.isLivingEntityShadowImmune(other) ? 80 : -1, (other) -> 3, TestTarget.OTHER))
                    .addConditions(ITEM_OWNER_IS_NOT_VULNERABLE)
                    .build())
            .build();

    public static final ItemAbility MAGMA_CUBE = ItemAbility.builder()
            .setDescId("magma_cube")
            .addPassiveModules(ModuleList.builder()
                    .addActions(JUMP_BOOST_TO_SELF.passiveBuild())
                    .addConditions(IN_MAIN_OR_OFF_HAND)
                    .build())
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            JUMP_BOOST_TO_SELF.with(100, 2),
                            CRISMON_SPORE_PARTICLES_ON_SELF.of(20),
                            PARROT_IMITATE_MAGMA_CUBE_SOUND)
                    .addSideEffects(COOLDOWN.of(400), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility FORGOTTEN_BATTLE_TRIDENT = ItemAbility.builder()
            .setDescId("forgotten_battle_trident")
            .addOnUseModules(ModuleList.builder()
                    .addActions(
                            WATER_BREATHING_TO_SELF.withDuration(120),
                            DOLPHINS_GRACE_TO_SELF.withDuration(120),
                            SPEED_TO_SELF.withDuration(120),
                            STRENGTH_TO_SELF.withDuration(300),
                            DRIPPING_WATER_PARTICLES_ON_SELF.of(20),
                            FORGOTTEN_BATTLE_TRIDENT_USE_SOUND)
                    .addSideEffects(COOLDOWN.of(540), DAMAGE_ITEM)
                    .build())
            .build();

    public static final ItemAbility GOD = ItemAbility.builder()
            .setDescId("god")
            .addPassiveModules(ModuleList.builder()
                    .addActions(GOD_EFFECT_TO_SELF.passiveBuild())
                    .addConditions(IN_MAIN_OR_OFF_HAND)
                    .build())
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(IGNITE_OTHER)
                    .build()
            ).build();

    public static final ItemAbility MAGMATIC_GEL_TOOL = ItemAbility.builder()
            .setDescId("magmatic_gel_tool")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(
                            SLOWNESS_TO_OTHER.with((itemOwner) -> 120, (itemOwner) -> EntityHelper.hasFullMagmaticGelStuff(itemOwner) ? 1 : 0, TestTarget.ITEM_OWNER),
                            SNOWFLAKE_PARTICLES_ON_OTHER.of(5))
                    .build()
            ).build();

    public static final ItemAbility ABSOLUTE_ZERO = ItemAbility.builder()
            .setDescId("absolute_zero")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(
                            SLOWNESS_TO_OTHER.with(100, 2),
                            SNOWFLAKE_PARTICLES_ON_OTHER.of(5))
                    .build())
            .addOnUseModules(ModuleList.builder()
                    .addActions(SLOW_DOWN_NEARBY_ENTITIES, SNOWFLAKE_PARTICLES_ON_SELF.of(20), ICE_SPIRIT_DEATH_SOUND)
                    .addSideEffects(COOLDOWN.of(300), DAMAGE_ITEM)
                    .build()
            ).build();

    public static final ItemAbility MAGMATIC_GEL_ARMOR = ItemAbility.builder()
            .setDescId("magmatic_gel_armor")
            .addOnTakeDamageModules(ModuleList.builder()
                    .addActions(
                            SLOWNESS_TO_OTHER.with(120, 1),
                            SNOWFLAKE_PARTICLES_ON_OTHER.of(5))
                    .addConditions(OTHER_IS_NOT_CREATIVE_PLAYER, IN_RIGHT_SLOT)
                    .build()
            ).build();

    public static final ItemAbility ARSONIST_ARMOR = ItemAbility.builder()
            .setDescId("arsonist_armor")
            .addOnTakeDamageModules(ModuleList.builder()
                    .addActions(IGNITE_OTHER, MULTIPLY_DAMAGE.by(itemOwner -> itemOwner.getRemainingFireTicks() > 0 ? 0.93F : 1.0F, TestTarget.ITEM_OWNER))
                    .addConditions(OTHER_IS_NOT_CREATIVE_PLAYER, IN_RIGHT_SLOT)
                    .build()
            ).build();

    public static final ItemAbility ARSONIST_TOOL = ItemAbility.builder()
            .setDescId("arsonist_tool")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(IGNITE_OTHER, MULTIPLY_DAMAGE.by(itemOwner -> itemOwner.getRemainingFireTicks() > 0 ? 1.5F : 1.0F, TestTarget.ITEM_OWNER))
                    .build()
            ).build();

    public static final ItemAbility DISADVANTAGE_OPPONENT = ItemAbility.builder()
            .setDescId("disadvantage_opponent")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(
                            SLOWNESS_TO_OTHER.with(100, 0),
                            WEAKNESS_TO_OTHER.with(100, 0),
                            MINING_FATIGUE_TO_OTHER.with(100, 0))
                    .build()
            ).build();

    public static final ItemAbility CURSED_TOOL = ItemAbility.builder()
            .setDescId("cursed_tool")
            .addOnDealDamageModules(ModuleList.builder()
                    .addActions(CURSED_TOOL_INTERACTION)
                    .build()
            ).build();

    public static final ItemAbility THROW_IRON_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.IRON_SHURIKEN.get(), 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_GOLD_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.GOLD_SHURIKEN.get(), 2.0F, 1.0F)).addSideEffects(COOLDOWN.of(7), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_DIAMOND_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.DIAMOND_SHURIKEN.get(), 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_NETHERITE_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.NETHERITE_SHURIKEN.get(), 1.6F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_RUBY_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.RUBY_SHURIKEN.get(), 1.8F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_AZURITE_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.AZURITE_SHURIKEN.get(), 2.0F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_MAGMATIC_GEL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), 1.7F, 1.5F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_VOLUCITE_GEL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.VOLUCITE_SHURIKEN.get(), 1.6F, 0.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_OBSIDIAN_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), 1.6F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_LUNATIC_CRYSTAL_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), 1.8F, 0.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_ARSONIST_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.ARSONIST_SHURIKEN.get(), 1.7F, 1.0F)).addSideEffects(COOLDOWN.of(9), SHRINK_ONE_ITEM).build()).build();
    public static final ItemAbility THROW_LIGHTNING_SHURIKEN = ItemAbility.builder().addOnUseModules(ModuleList.builder().addActions(THROW_PROJECTILE.build(AerialHellEntities.LIGHTNING_SHURIKEN.get(), 1.7F, 1.0F)).addSideEffects(COOLDOWN.of(8), SHRINK_ONE_ITEM).build()).build();
}