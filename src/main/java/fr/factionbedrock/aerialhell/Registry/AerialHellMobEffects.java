package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Effect.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellMobEffects
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AerialHell.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> HEAD_IN_THE_CLOUDS = EFFECTS.register("head_in_the_clouds", () -> (new HeadInTheCloudsEffect(MobEffectCategory.BENEFICIAL, 8171462)).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "movement_speed_modifier"), (double)0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "jump_strength_modifier"), (double)0.4F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> GOD = EFFECTS.register("god", () -> (new GodEffect(MobEffectCategory.BENEFICIAL, 9740385)).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "attack_speed_modifier"), (double)0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "attack_speed_modifier"), (double)0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> SHADOW_IMMUNITY = EFFECTS.register("shadow_immunity", () -> (new AerialHellEffect(MobEffectCategory.BENEFICIAL, 9740385)));
    public static final DeferredHolder<MobEffect, MobEffect> SHADOW_BIND = EFFECTS.register("shadow_bind", () -> (new ShadowBind(MobEffectCategory.BENEFICIAL, 9740385)));
    public static final DeferredHolder<MobEffect, MobEffect> VULNERABILITY = EFFECTS.register("vulnerability", () -> (new VulnerabilityEffect(MobEffectCategory.HARMFUL, 6501508)));
    public static final DeferredHolder<MobEffect, MobEffect> TRAITOR = EFFECTS.register("traitor", () -> (new AerialHellEffect(MobEffectCategory.HARMFUL, 6501508)));
    public static final DeferredHolder<MobEffect, MobEffect> AERIAL_HELL_PORTAL = EFFECTS.register("aerial_hell_portal", () -> (new AerialHellPortalEffect(MobEffectCategory.NEUTRAL, 8171462)));
    public static final DeferredHolder<MobEffect, MobEffect> AERIAL_HELL_PORTAL_COOLDOWN = EFFECTS.register("aerial_hell_portal_cooldown", () -> (new AerialHellEffect(MobEffectCategory.NEUTRAL, 6501508)));
}