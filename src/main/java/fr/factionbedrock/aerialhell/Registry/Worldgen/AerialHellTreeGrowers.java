package fr.factionbedrock.aerialhell.Registry.Worldgen;

import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Optional;

public class AerialHellTreeGrowers
{
    public static final SaplingGenerator AERIAL_TREE = createSimpleTreeGrower("aerial_tree", Optional.of(AerialHellConfiguredFeatures.AERIAL_TREE));
    public static final SaplingGenerator COPPER_PINE = createSimpleTreeGrower("copper_pine", Optional.of(AerialHellConfiguredFeatures.COPPER_PINE));
    public static final SaplingGenerator GOLDEN_BEECH = createSimpleTreeGrower("golden_beech", Optional.of(AerialHellConfiguredFeatures.GOLDEN_BEECH));
    public static final SaplingGenerator LAPIS_ROBINIA = createSimpleTreeGrower("lapis_robinia", Optional.of(AerialHellConfiguredFeatures.LAPIS_ROBINIA));
    public static final SaplingGenerator PURPLE_SHADOW_PINE = createSimpleMegaTreeGrower("purple_shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE));
    public static final SaplingGenerator SHADOW_PINE = createSimpleMegaTreeGrower("shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.SHADOW_PINE));
    public static final SaplingGenerator STELLAR_JUNGLE_TREE = createSimpleMegaTreeGrower("stellar_jungle_tree", Optional.of(AerialHellConfiguredFeatures.MEGA_STELLAR_JUNGLE_TREE), Optional.of(AerialHellConfiguredFeatures.STELLAR_JUNGLE_TREE));

    private static SaplingGenerator createSimpleTreeGrower(String name, Optional<RegistryKey<ConfiguredFeature<?, ?>>> tree)
    {
        return createSimpleMegaTreeGrower(name, Optional.empty(), tree);
    }

    private static SaplingGenerator createSimpleMegaTreeGrower(String name, Optional<RegistryKey<ConfiguredFeature<?, ?>>> megaTree, Optional<RegistryKey<ConfiguredFeature<?, ?>>> tree)
    {
        return createTreeGrower(name, 0.0F, megaTree,Optional.empty(), tree, Optional.empty(), Optional.empty(), Optional.empty());
    }

    private static SaplingGenerator createTreeGrower(String name, float secondaryChance, Optional<RegistryKey<ConfiguredFeature<?, ?>>> megaTree, Optional<RegistryKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<RegistryKey<ConfiguredFeature<?, ?>>> tree, Optional<RegistryKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<RegistryKey<ConfiguredFeature<?, ?>>> flowers, Optional<RegistryKey<ConfiguredFeature<?, ?>>> secondaryFlowers)
    {
        return new SaplingGenerator(name, secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }
}
