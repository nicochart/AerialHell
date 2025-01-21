package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;

import java.util.OptionalInt;

public class AerialHellTreeConfig
{
    public static final TreeConfiguration AERIAL_TREE_BASE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.get().defaultBlockState(),
            new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.AERIAL_TREE_LEAVES.get().defaultBlockState(),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    public static final TreeConfiguration FOREST_AERIAL_TREE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.get().defaultBlockState(),
            new StraightTrunkPlacer(5, 3, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.AERIAL_TREE_LEAVES.get().defaultBlockState(),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    public static final TreeConfiguration COPPER_PINE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.COPPER_PINE_LOG.get().defaultBlockState(),
            new StraightTrunkPlacer(5, 2, 1),
            AerialHellBlocks.COPPER_PINE_LEAVES.get().defaultBlockState(),
            new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2));

    public static final TreeConfiguration LAPIS_ROBINIA_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(AerialHellBlocks.LAPIS_ROBINIA_LOG.get().defaultBlockState(), 1)
                    .add(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG.get().defaultBlockState(), 1)),
            new ForkingTrunkPlacer(5, 2, 2),
            BlockStateProvider.simple(AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get().defaultBlockState()),
            new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 2)
    )).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).ignoreVines().build();

    public static final TreeConfiguration GOLDEN_BEECH_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.GOLDEN_BEECH_LOG.get().defaultBlockState(),
            new FancyTrunkPlacer(3, 11, 0),
            AerialHellBlocks.GOLDEN_BEECH_LEAVES.get().defaultBlockState(),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4),4), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)));

    public static final TreeConfiguration SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.get().defaultBlockState(), 2)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
            new StraightTrunkPlacer(6, 2, 1),
            BlockStateProvider.simple(AerialHellBlocks.SHADOW_PINE_LEAVES.get().defaultBlockState()),
            new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2)
    )).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).ignoreVines().build();

    public static final TreeConfiguration PURPLE_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.get().defaultBlockState(), 2)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
            new StraightTrunkPlacer(6, 2, 1),
            BlockStateProvider.simple(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get().defaultBlockState()),
            new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2)
    )).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).ignoreVines().build();

    public static final TreeConfiguration MEGA_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.get().defaultBlockState(), 6)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
            new GiantTrunkPlacer(13, 2, 14),
            BlockStateProvider.simple(AerialHellBlocks.SHADOW_PINE_LEAVES.get().defaultBlockState()),
            new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 4)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 1, 2)
    )).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(AerialHellBlocks.SHADOW_GRASS_BLOCK.get().defaultBlockState())))).ignoreVines().build();

    public static final TreeConfiguration MEGA_PURPLE_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.get().defaultBlockState(), 4)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
            new GiantTrunkPlacer(13, 2, 14),
            BlockStateProvider.simple(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get().defaultBlockState()),
            new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(4, 13)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 1, 2)
    )).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(AerialHellBlocks.SHADOW_GRASS_BLOCK.get().defaultBlockState())))).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).ignoreVines().build();

    public static final TreeConfiguration CRYSTALLIZED_TREE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.get().defaultBlockState(),
            new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.CRYSTALLIZED_LEAVES.get().defaultBlockState(),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    private static TreeConfiguration.TreeConfigurationBuilder makeClassicTreeConfigBuilder(BlockState logBlockState, TrunkPlacer trunkPlacer, BlockState leavesBlockState, FoliagePlacer foliagePlacer, FeatureSize featureSize)
    {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(logBlockState), trunkPlacer, BlockStateProvider.simple(leavesBlockState), foliagePlacer, featureSize);
    }

    private static TreeConfiguration makeClassicStellarDirtTreeConfig(BlockState logBlockState, TrunkPlacer trunkPlacer, BlockState leavesBlockState, FoliagePlacer foliagePlacer, FeatureSize featureSize)
    {
        return makeClassicTreeConfigBuilder(logBlockState, trunkPlacer, leavesBlockState, foliagePlacer, featureSize).dirt(BlockStateProvider.simple(AerialHellBlocks.STELLAR_DIRT.get())).ignoreVines().build();
    }
}
