package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AerialHellMobEffects
{
    public static final Holder<MobEffect> HEAD_IN_THE_CLOUDS = register("head_in_the_clouds", new HeadInTheCloudsEffect(MobEffectCategory.BENEFICIAL, 8171462).addAttributeModifier(Attributes.MOVEMENT_SPEED, AerialHell.id("movement_speed_modifier"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> GOD = register("god", new GodEffect(MobEffectCategory.BENEFICIAL, 9740385).addAttributeModifier(Attributes.ATTACK_SPEED, AerialHell.id("attack_speed_modifier"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> SHADOW_IMMUNITY = register("shadow_immunity", new AerialHellEffect(MobEffectCategory.BENEFICIAL, 9740385));
    public static final Holder<MobEffect> SHADOW_BIND = register("shadow_bind", new ShadowBind(MobEffectCategory.BENEFICIAL, 9740385));
    public static final Holder<MobEffect> VULNERABILITY = register("vulnerability", new VulnerabilityEffect(MobEffectCategory.HARMFUL, 6501508));
    public static final Holder<MobEffect> TRAITOR = register("traitor", new AerialHellEffect(MobEffectCategory.HARMFUL, 6501508));
    public static final Holder<MobEffect> AERIAL_HELL_PORTAL = register("aerial_hell_portal", new AerialHellPortalEffect(MobEffectCategory.NEUTRAL, 8171462));
    public static final Holder<MobEffect> AERIAL_HELL_PORTAL_COOLDOWN = register("aerial_hell_portal_cooldown", new AerialHellEffect(MobEffectCategory.NEUTRAL, 6501508));

    private static Holder<MobEffect> register(String name, MobEffect statusEffect)
    {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, AerialHell.id(name), statusEffect);
    }

    public static void load() {}

    /*public static class Cures TODO Effect cures
    {
        public static final EffectCure SHADOW_FRUIT_STEW = EffectCure.get("shadow_fruit_stew");
    }*/
}