package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Effect.GodEffect;
import fr.factionbedrock.aerialhell.Effect.HeadInTheCloudsEffect;
import fr.factionbedrock.aerialhell.Effect.AerialHellEffect;
import fr.factionbedrock.aerialhell.Effect.VulnerabilityEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellPotionEffects
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, AerialHell.MODID);

    public static final RegistryObject<Effect> HEAD_IN_THE_CLOUDS = EFFECTS.register("head_in_the_clouds", () -> (new HeadInTheCloudsEffect(EffectType.BENEFICIAL, 8171462)).addAttributesModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", (double)0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> GOD = EFFECTS.register("god", () -> (new GodEffect(EffectType.BENEFICIAL, 9740385)).addAttributesModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", (double)0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", (double)0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> SHADOW_IMMUNITY = EFFECTS.register("shadow_immunity", () -> (new AerialHellEffect(EffectType.BENEFICIAL, 9740385)));
    public static final RegistryObject<Effect> VULNERABILITY = EFFECTS.register("vulnerability", () -> (new VulnerabilityEffect(EffectType.HARMFUL, 6501508)));
    public static final RegistryObject<Effect> TRAITOR = EFFECTS.register("traitor", () -> (new AerialHellEffect(EffectType.HARMFUL, 6501508)));
}