package fr.factionbedrock.aerialhell.Registry.Worldgen;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

public class AerialHellTreeGrowers
{
    public static final TreeGrower AERIAL_TREE = createSimpleTreeGrower("aerial_tree", Optional.of(AerialHellConfiguredFeatures.AERIAL_TREE));
    public static final TreeGrower COPPER_PINE = createSimpleTreeGrower("copper_pine", Optional.of(AerialHellConfiguredFeatures.COPPER_PINE));
    public static final TreeGrower GOLDEN_BEECH = createSimpleTreeGrower("golden_beech", Optional.of(AerialHellConfiguredFeatures.GOLDEN_BEECH));
    public static final TreeGrower LAPIS_ROBINIA = createSimpleTreeGrower("lapis_robinia", Optional.of(AerialHellConfiguredFeatures.LAPIS_ROBINIA));
    public static final TreeGrower PURPLE_SHADOW_PINE = createSimpleMegaTreeGrower("purple_shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE));
    public static final TreeGrower SHADOW_PINE = createSimpleMegaTreeGrower("shadow_pine", Optional.of(AerialHellConfiguredFeatures.MEGA_SHADOW_PINE), Optional.of(AerialHellConfiguredFeatures.SHADOW_PINE));
    public static final TreeGrower STELLAR_JUNGLE_TREE = createSimpleMegaTreeGrower("stellar_jungle_tree", Optional.of(AerialHellConfiguredFeatures.MEGA_STELLAR_JUNGLE_TREE), Optional.of(AerialHellConfiguredFeatures.STELLAR_JUNGLE_TREE));

    private static TreeGrower createSimpleTreeGrower(String name, Optional<ResourceKey<Feature>> tree)
    {
        return createSimpleMegaTreeGrower(name, Optional.empty(), tree);
    }

    private static TreeGrower createSimpleMegaTreeGrower(String name, Optional<ResourceKey<Feature>> megaTree, Optional<ResourceKey<Feature>> tree)
    {
        //26.3 : TreeGrower takes weighted lists of trees (no more secondary trees with a secondary chance)
        return new TreeGrower(name, toWeightedList(tree), toWeightedList(megaTree), WeightedList.of(), tree.orElse(null));
    }

    private static WeightedList<ResourceKey<Feature>> toWeightedList(Optional<ResourceKey<Feature>> feature)
    {
        return feature.isPresent() ? WeightedList.of(feature.get()) : WeightedList.of();
    }
}
