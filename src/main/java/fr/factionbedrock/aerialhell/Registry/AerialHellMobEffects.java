package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Effect.GodEffect;
import fr.factionbedrock.aerialhell.Effect.HeadInTheCloudsEffect;
import fr.factionbedrock.aerialhell.Effect.AerialHellEffect;
import fr.factionbedrock.aerialhell.Effect.VulnerabilityEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellMobEffects
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AerialHell.MODID);

    public static final RegistryObject<MobEffect> HEAD_IN_THE_CLOUDS = EFFECTS.register("head_in_the_clouds", () -> (new HeadInTheCloudsEffect(MobEffectCategory.BENEFICIAL, 8171462)).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", (double)0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> GOD = EFFECTS.register("god", () -> (new GodEffect(MobEffectCategory.BENEFICIAL, 9740385)).addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", (double)0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", (double)0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> SHADOW_IMMUNITY = EFFECTS.register("shadow_immunity", () -> (new AerialHellEffect(MobEffectCategory.BENEFICIAL, 9740385)));
    public static final RegistryObject<MobEffect> VULNERABILITY = EFFECTS.register("vulnerability", () -> (new VulnerabilityEffect(MobEffectCategory.HARMFUL, 6501508)));
    public static final RegistryObject<MobEffect> TRAITOR = EFFECTS.register("traitor", () -> (new AerialHellEffect(MobEffectCategory.HARMFUL, 6501508)));
}