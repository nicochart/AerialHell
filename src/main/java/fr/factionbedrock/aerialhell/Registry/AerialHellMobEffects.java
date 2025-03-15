package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Effect.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class AerialHellMobEffects
{
    public static final RegistryEntry<StatusEffect> HEAD_IN_THE_CLOUDS = register("head_in_the_clouds", new HeadInTheCloudsEffect(StatusEffectCategory.BENEFICIAL, 8171462).addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, AerialHell.id("movement_speed_modifier"), 0.2F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> GOD = register("god", new GodEffect(StatusEffectCategory.BENEFICIAL, 9740385).addAttributeModifier(EntityAttributes.ATTACK_SPEED, AerialHell.id("attack_speed_modifier"), 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> SHADOW_IMMUNITY = register("shadow_immunity", new AerialHellEffect(StatusEffectCategory.BENEFICIAL, 9740385));
    public static final RegistryEntry<StatusEffect> SHADOW_BIND = register("shadow_bind", new ShadowBind(StatusEffectCategory.BENEFICIAL, 9740385));
    public static final RegistryEntry<StatusEffect> VULNERABILITY = register("vulnerability", new VulnerabilityEffect(StatusEffectCategory.HARMFUL, 6501508));
    public static final RegistryEntry<StatusEffect> TRAITOR = register("traitor", new AerialHellEffect(StatusEffectCategory.HARMFUL, 6501508));
    public static final RegistryEntry<StatusEffect> AERIAL_HELL_PORTAL = register("aerial_hell_portal", new AerialHellPortalEffect(StatusEffectCategory.NEUTRAL, 8171462));
    public static final RegistryEntry<StatusEffect> AERIAL_HELL_PORTAL_COOLDOWN = register("aerial_hell_portal_cooldown", new AerialHellEffect(StatusEffectCategory.NEUTRAL, 6501508));

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect)
    {
        return Registry.registerReference(Registries.STATUS_EFFECT, AerialHell.id(name), statusEffect);
    }

    public static void load() {}

    /*public static class Cures TODO Effect cures
    {
        public static final EffectCure SHADOW_FRUIT_STEW = EffectCure.get("shadow_fruit_stew");
    }*/
}