package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunk.*;

import java.util.OptionalInt;

public class AerialHellTreeConfig
{
    public static final TreeFeatureConfig AERIAL_TREE_BASE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.getDefaultState(),
            new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.AERIAL_TREE_LEAVES.getDefaultState(),
            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    public static final TreeFeatureConfig FOREST_AERIAL_TREE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.getDefaultState(),
            new StraightTrunkPlacer(5, 3, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.AERIAL_TREE_LEAVES.getDefaultState(),
            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    public static final TreeFeatureConfig COPPER_PINE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.COPPER_PINE_LOG.getDefaultState(),
            new StraightTrunkPlacer(5, 2, 1),
            AerialHellBlocks.COPPER_PINE_LEAVES.getDefaultState(),
            new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2));

    public static final TreeFeatureConfig LAPIS_ROBINIA_CONFIG = (new TreeFeatureConfig.Builder(
            new WeightedBlockStateProvider(Pool.<BlockState>builder()
                    .add(AerialHellBlocks.LAPIS_ROBINIA_LOG.getDefaultState(), 1)
                    .add(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG.getDefaultState(), 1)),
            new ForkingTrunkPlacer(5, 2, 2),
            BlockStateProvider.of(AerialHellBlocks.LAPIS_ROBINIA_LEAVES.getDefaultState()),
            new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 2)
    )).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).ignoreVines().build();

    public static final TreeFeatureConfig GOLDEN_BEECH_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.GOLDEN_BEECH_LOG.getDefaultState(),
            new LargeOakTrunkPlacer(3, 11, 0),
            AerialHellBlocks.GOLDEN_BEECH_LEAVES.getDefaultState(),
            new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4),4), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)));

    public static final TreeFeatureConfig SHADOW_PINE_CONFIG = (new TreeFeatureConfig.Builder(
            new WeightedBlockStateProvider(Pool.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.getDefaultState(), 2)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.getDefaultState(), 1)),
            new StraightTrunkPlacer(6, 2, 1),
            BlockStateProvider.of(AerialHellBlocks.SHADOW_PINE_LEAVES.getDefaultState()),
            new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2)
    )).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).ignoreVines().build();

    public static final TreeFeatureConfig PURPLE_SHADOW_PINE_CONFIG = (new TreeFeatureConfig.Builder(
            new WeightedBlockStateProvider(Pool.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.getDefaultState(), 2)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.getDefaultState(), 1)),
            new StraightTrunkPlacer(6, 2, 1),
            BlockStateProvider.of(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.getDefaultState()),
            new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(3, 0, 2)
    )).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).ignoreVines().build();

    public static final TreeFeatureConfig MEGA_SHADOW_PINE_CONFIG = (new TreeFeatureConfig.Builder(
            new WeightedBlockStateProvider(Pool.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.getDefaultState(), 6)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.getDefaultState(), 1)),
            new GiantTrunkPlacer(13, 2, 14),
            BlockStateProvider.of(AerialHellBlocks.SHADOW_PINE_LEAVES.getDefaultState()),
            new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(3, 4)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 1, 2)
    )).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(AerialHellBlocks.SHADOW_GRASS_BLOCK.getDefaultState())))).ignoreVines().build();

    public static final TreeFeatureConfig MEGA_PURPLE_SHADOW_PINE_CONFIG = (new TreeFeatureConfig.Builder(
            new WeightedBlockStateProvider(Pool.<BlockState>builder()
                    .add(AerialHellBlocks.SHADOW_PINE_LOG.getDefaultState(), 4)
                    .add(AerialHellBlocks.EYE_SHADOW_PINE_LOG.getDefaultState(), 1)),
            new GiantTrunkPlacer(13, 2, 14),
            BlockStateProvider.of(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.getDefaultState()),
            new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(4, 13)), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 1, 2)
    )).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(AerialHellBlocks.SHADOW_GRASS_BLOCK.getDefaultState())))).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).ignoreVines().build();

    public static final TreeFeatureConfig CRYSTALLIZED_TREE_CONFIG = makeClassicStellarDirtTreeConfig(
            AerialHellBlocks.AERIAL_TREE_LOG.getDefaultState(),
            new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
            AerialHellBlocks.CRYSTALLIZED_LEAVES.getDefaultState(),
            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3), //rayon,décalage,hauteur
            new TwoLayersFeatureSize(1, 0, 1));

    private static TreeFeatureConfig.Builder makeClassicTreeConfigBuilder(BlockState logBlockState, TrunkPlacer trunkPlacer, BlockState leavesBlockState, FoliagePlacer foliagePlacer, FeatureSize featureSize)
    {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(logBlockState), trunkPlacer, BlockStateProvider.of(leavesBlockState), foliagePlacer, featureSize);
    }

    private static TreeFeatureConfig makeClassicStellarDirtTreeConfig(BlockState logBlockState, TrunkPlacer trunkPlacer, BlockState leavesBlockState, FoliagePlacer foliagePlacer, FeatureSize featureSize)
    {
        return makeClassicTreeConfigBuilder(logBlockState, trunkPlacer, leavesBlockState, foliagePlacer, featureSize).dirtProvider(BlockStateProvider.of(AerialHellBlocks.STELLAR_DIRT)).ignoreVines().build();
    }
}
