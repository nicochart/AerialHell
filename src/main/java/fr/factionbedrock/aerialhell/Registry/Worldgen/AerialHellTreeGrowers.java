package fr.factionbedrock.aerialhell.Registry.Worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class AerialHellTreeGrowers
{
    public static final TreeGrower AERIAL_TREE = createSimpleTreeGrower("aerial_tree", Optional.of(AerialHellConfiguredFeatures.AERIAL_TREE));
    public static final TreeGrower COPPER_PINE = createSimpleTreeGrower("copper_pine", Optional.of(AerialHellConfiguredFeatures.COPPER_PINE));
    public static final TreeGrower GOLDEN_BEECH = createSimpleTreeGrower("golden_beech", Optional.of(AerialHellConfiguredFeatures.GOLDEN_BEECH));
    public static final TreeGrower LAPIS_ROBINIA = createSimpleTreeGrower("lapis_robinia", Optional.of(AerialHellConfiguredFeatures.LAPIS_ROBINIA));
    public static final TreeGrower PURPLE_SHADOW_PINE = createSimpleMegaTreeGrower("purple_shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE));
    public static final TreeGrower SHADOW_PINE = createSimpleMegaTreeGrower("shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.SHADOW_PINE));
    public static final TreeGrower STELLAR_JUNGLE_TREE = createSimpleMegaTreeGrower("stellar_jungle_tree", Optional.of(AerialHellConfiguredFeatures.MEGA_STELLAR_JUNGLE_TREE), Optional.of(AerialHellConfiguredFeatures.STELLAR_JUNGLE_TREE));

    private static TreeGrower createSimpleTreeGrower(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree)
    {
        return createSimpleMegaTreeGrower(name, Optional.empty(), tree);
    }

    private static TreeGrower createSimpleMegaTreeGrower(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree)
    {
        return createTreeGrower(name, 0.0F, megaTree,Optional.empty(), tree, Optional.empty(), Optional.empty(), Optional.empty());
    }

    private static TreeGrower createTreeGrower(String name, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers)
    {
        return new TreeGrower(name, secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }
}
