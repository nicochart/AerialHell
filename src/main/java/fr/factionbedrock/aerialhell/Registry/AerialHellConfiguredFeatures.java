package fr.factionbedrock.aerialhell.Registry;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Features.AerialHellLakeFeature;
import fr.factionbedrock.aerialhell.World.GenAerialHellOres;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AerialHellConfiguredFeatures
{
	public static final class Configs
	{
        public static final RandomPatchConfiguration STELLAR_GRASS_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_GRASS.get()))));
        public static final RandomPatchConfiguration STELLAR_GRASS_BALL_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get()))));
        public static final RandomPatchConfiguration BRAMBLES_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.BRAMBLES.get()))));
        public static final RandomPatchConfiguration STELLAR_FERN_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_FERN.get()))));
        public static final RandomPatchConfiguration STELLAR_TALL_GRASS_PATCH_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get()))));
        public static final RandomPatchConfiguration STELLAR_TALL_FERN_PATCH_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_TALL_FERN.get()))));
        public static final RandomPatchConfiguration STELLAR_DEAD_BUSH_PATCH_CONFIG = new RandomPatchConfiguration(8, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_DEAD_BUSH.get()))));
        public static final RandomPatchConfiguration AERIAL_BERRY_BUSH_PATCH_CONFIG = new RandomPatchConfiguration(8, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get()))));
        public static final RandomPatchConfiguration SHADOW_GRASS_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_GRASS.get()))));
        public static final RandomPatchConfiguration SHADOW_GRASS_BALL_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get()))));
        public static final RandomPatchConfiguration SHADOW_BRAMBLES_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_BRAMBLES.get()))));
        public static final RandomPatchConfiguration PURPLISH_STELLAR_GRASS_PATCH_CONFIG = new RandomPatchConfiguration(16, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS.get()))));
        public static final RandomPatchConfiguration VERDIGRIS_AGARIC_PATCH_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get()))));
        public static final RandomPatchConfiguration CORTINARIUS_VIOLACEUS_PATCH_CONFIG = new RandomPatchConfiguration(32, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.CORTINARIUS_VIOLACEUS.get()))));
        public static final RandomPatchConfiguration SKY_CACTUS_PATCH_CONFIG = new RandomPatchConfiguration(8, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(AerialHellBlocksAndItems.SKY_CACTUS.get()))));

        public static final RandomPatchConfiguration AERIAL_HELL_BELLFLOWERS_CONFIG = new RandomPatchConfiguration(8, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(AerialHellBlocksAndItems.BELLFLOWER.get()))));

        public static final RandomPatchConfiguration AERIAL_HELL_FLOWERS_CONFIG = new RandomPatchConfiguration(8, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(AerialHellBlocksAndItems.BLUE_FLOWER.get().defaultBlockState(), 1)
                .add(AerialHellBlocksAndItems.BLACK_ROSE.get().defaultBlockState(), 1)
                .add(AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get().defaultBlockState(), 1)))));
		
		public static final TreeConfiguration AERIAL_TREE_BASE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().defaultBlockState()),
			    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
			    new TwoLayersFeatureSize(1, 0, 1)
        )).ignoreVines().build();

        public static final TreeConfiguration AERIAL_TREE_FOREST_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(5, 3, 0), //hauteur de base, randomizer1, randomizer2
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(1, 0, 1)
        )).ignoreVines().build();

        public static final TreeConfiguration COPPER_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(AerialHellBlocksAndItems.COPPER_PINE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get().defaultBlockState()),
                new SpruceFoliagePlacer(UniformInt.of(3, 1), UniformInt.of(0, 2), UniformInt.of(1, 2)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(3, 0, 2)
        )).ignoreVines().build();

        public static final TreeConfiguration LAPIS_ROBINIA_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG.get().defaultBlockState(), 1)
                        .add(AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get().defaultBlockState(), 1)),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get().defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(1, 0, 2)
        )).ignoreVines().build();

        public static final TreeConfiguration GOLDEN_BEECH_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(AerialHellBlocksAndItems.GOLDEN_BEECH_LOG.get().defaultBlockState()),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get().defaultBlockState()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4),4), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        )).ignoreVines().build();

        public static final TreeConfiguration SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().defaultBlockState(), 2)
                        .add(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
                new StraightTrunkPlacer(6, 2, 1),
                BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().defaultBlockState()),
                new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(3, 0, 2)
        )).ignoreVines().build();

        public static final TreeConfiguration PURPLE_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().defaultBlockState(), 2)
                        .add(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
                new StraightTrunkPlacer(6, 2, 1),
                BlockStateProvider.simple(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get().defaultBlockState()),
                new SpruceFoliagePlacer(UniformInt.of(2, 1), UniformInt.of(0, 2), UniformInt.of(1, 1)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(3, 0, 2)
        )).ignoreVines().build();

        public static final TreeConfiguration MEGA_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().defaultBlockState(), 6)
                        .add(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().defaultBlockState()),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 4)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(1, 1, 2)
        )).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().defaultBlockState())))).ignoreVines().build();

        public static final TreeConfiguration MEGA_PURPLE_SHADOW_PINE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().defaultBlockState(), 4)
                        .add(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get().defaultBlockState(), 1)),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().defaultBlockState()),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 4)), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(1, 1, 2)
        )).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().defaultBlockState())))).ignoreVines().build();

        public static final TreeConfiguration CRYSTALLIZED_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState()),
                new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
                BlockStateProvider.simple(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //rayon,décalage,hauteur
                new TwoLayersFeatureSize(1, 0, 1)
        )).ignoreVines().build();

		public static final HugeFungusConfiguration GIANT_CORTINARIUS_VIOLACEUS_CONFIG = new HugeFungusConfiguration(
				AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState(),
				AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_STEM.get().defaultBlockState(),
				AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().defaultBlockState(),
				AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get().defaultBlockState(),
				false);

        public static final HugeMushroomFeatureConfiguration GIANT_VERDIGRIS_AGARIC_MUSHROOM_CONFIG = new HugeMushroomFeatureConfiguration(
                BlockStateProvider.simple(AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                BlockStateProvider.simple(AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_STEM.get().defaultBlockState()),
                2); //Foliage Radius

        public static final HugeMushroomFeatureConfiguration GIANT_RED_MUSHROOM_CONFIG = new HugeMushroomFeatureConfiguration(
                BlockStateProvider.simple(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
                BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()),
                2); //Foliage Radius

        public static final AerialHellLakeFeature.Configuration AERIAL_HELL_WATER_LAKE_CONFIG = new AerialHellLakeFeature.Configuration(
                BlockStateProvider.simple(Blocks.WATER.defaultBlockState()),
                BlockStateProvider.simple(AerialHellBlocksAndItems.STELLAR_STONE.get().defaultBlockState()));
	}

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AerialHell.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> AERIAL_HELL_WATER_LAKE = CONFIGURED_FEATURES.register("aerial_hell_water_lake", () -> new ConfiguredFeature<>(AerialHellFeatures.AERIAL_HELL_WATER_LAKE.get(), Configs.AERIAL_HELL_WATER_LAKE_CONFIG));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_GRASS = CONFIGURED_FEATURES.register("patch_stellar_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_GRASS_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_GRASS_BALL = CONFIGURED_FEATURES.register("patch_stellar_grass_ball", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_GRASS_BALL_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_BRAMBLES = CONFIGURED_FEATURES.register("patch_brambles", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.BRAMBLES_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_FERN = CONFIGURED_FEATURES.register("patch_stellar_fern", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_FERN_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_TALL_GRASS = CONFIGURED_FEATURES.register("patch_stellar_tall_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_TALL_GRASS_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_TALL_FERN = CONFIGURED_FEATURES.register("patch_stellar_tall_fern", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_TALL_FERN_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_STELLAR_DEAD_BUSH = CONFIGURED_FEATURES.register("patch_stellar_dead_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.STELLAR_DEAD_BUSH_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_AERIAL_BERRY_BUSH = CONFIGURED_FEATURES.register("patch_aerial_berry_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.AERIAL_BERRY_BUSH_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_SHADOW_GRASS = CONFIGURED_FEATURES.register("patch_shadow_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.SHADOW_GRASS_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_SHADOW_GRASS_BALL = CONFIGURED_FEATURES.register("patch_shadow_grass_ball", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.SHADOW_GRASS_BALL_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_SHADOW_BRAMBLES = CONFIGURED_FEATURES.register("patch_shadow_brambles", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.SHADOW_BRAMBLES_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_PURPLISH_STELLAR_GRASS = CONFIGURED_FEATURES.register("patch_purplish_stellar_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.PURPLISH_STELLAR_GRASS_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_SKY_CACTUS = CONFIGURED_FEATURES.register("patch_sky_cactus", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.SKY_CACTUS_PATCH_CONFIG));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AERIAL_HELL_FLOWERS = CONFIGURED_FEATURES.register("aerial_hell_flowers", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.AERIAL_HELL_FLOWERS_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> AERIAL_HELL_BELLFLOWERS = CONFIGURED_FEATURES.register("aerial_hell_bellflowers", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.AERIAL_HELL_BELLFLOWERS_CONFIG));

//    public static final RegistryObject<ConfiguredFeature<?, ?>> AERIAL_TREE = CONFIGURED_FEATURES.register("aerial_tree", () -> new ConfiguredFeature<>(Feature.TREE, Configs.AERIAL_TREE_BASE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> AERIAL_TREE_FOREST = CONFIGURED_FEATURES.register("aerial_tree_forest", () -> new ConfiguredFeature<>(Feature.TREE, Configs.AERIAL_TREE_FOREST_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> COPPER_PINE = CONFIGURED_FEATURES.register("copper_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.COPPER_PINE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> LAPIS_ROBINIA = CONFIGURED_FEATURES.register("lapis_robinia", () -> new ConfiguredFeature<>(Feature.TREE, Configs.LAPIS_ROBINIA_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> SHADOW_PINE = CONFIGURED_FEATURES.register("shadow_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.SHADOW_PINE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> PURPLE_SHADOW_PINE = CONFIGURED_FEATURES.register("purple_shadow_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.PURPLE_SHADOW_PINE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> MEGA_SHADOW_PINE = CONFIGURED_FEATURES.register("mega_shadow_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.MEGA_SHADOW_PINE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> MEGA_PURPLE_SHADOW_PINE = CONFIGURED_FEATURES.register("mega_purple_shadow_pine", () -> new ConfiguredFeature<>(Feature.TREE, Configs.MEGA_PURPLE_SHADOW_PINE_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLDEN_BEECH = CONFIGURED_FEATURES.register("golden_beech", () -> new ConfiguredFeature<>(Feature.TREE, Configs.GOLDEN_BEECH_CONFIG));
//    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTALLIZED_TREE = CONFIGURED_FEATURES.register("crystallized_tree", () -> new ConfiguredFeature<>(Feature.TREE, Configs.CRYSTALLIZED_TREE_CONFIG));

    public static final RegistryObject<ConfiguredFeature<?, ?>> GIANT_CORTINARIUS_VIOLACEUS = CONFIGURED_FEATURES.register("giant_cortinarius_violaceus", () -> new ConfiguredFeature<>(Feature.HUGE_FUNGUS, Configs.GIANT_CORTINARIUS_VIOLACEUS_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GIANT_VERDIGRIS_AGARIC = CONFIGURED_FEATURES.register("giant_verdigris_agaric", () -> new ConfiguredFeature<>(Feature.HUGE_RED_MUSHROOM, Configs.GIANT_VERDIGRIS_AGARIC_MUSHROOM_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_CORTINARIUS_VIOLACEUS = CONFIGURED_FEATURES.register("patch_cortinarius_violaceus", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.CORTINARIUS_VIOLACEUS_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_VERDIGRIS_AGARIC = CONFIGURED_FEATURES.register("patch_verdigris_agaric", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.VERDIGRIS_AGARIC_PATCH_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> HUGE_VERDIGRIS_AGARIC = CONFIGURED_FEATURES.register("huge_verdigris_agaric", () -> new ConfiguredFeature<>(AerialHellFeatures.HUGE_MUSHROOM.get(), Configs.GIANT_VERDIGRIS_AGARIC_MUSHROOM_CONFIG));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GIANT_GANODERMA_APPLANATUM = CONFIGURED_FEATURES.register("giant_ganoderma_applanatum", () -> new ConfiguredFeature<>(AerialHellFeatures.GIANT_GANODERMA_APPLANATUM.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GIANT_RED_MUSHROOM = CONFIGURED_FEATURES.register("giant_red_mushroom", () -> new ConfiguredFeature<>(Feature.HUGE_RED_MUSHROOM, Configs.GIANT_RED_MUSHROOM_CONFIG));

    public static final RegistryObject<ConfiguredFeature<?, ?>> WHITE_SOLID_ETHER = CONFIGURED_FEATURES.register("white_solid_ether", () -> new ConfiguredFeature<>(AerialHellFeatures.WHITE_SOLID_ETHER.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_SOLID_ETHER = CONFIGURED_FEATURES.register("blue_solid_ether", () -> new ConfiguredFeature<>(AerialHellFeatures.BLUE_SOLID_ETHER.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLDEN_SOLID_ETHER = CONFIGURED_FEATURES.register("golden_solid_ether", () -> new ConfiguredFeature<>(AerialHellFeatures.GOLDEN_SOLID_ETHER.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GREEN_SOLID_ETHER = CONFIGURED_FEATURES.register("green_solid_ether", () -> new ConfiguredFeature<>(AerialHellFeatures.GREEN_SOLID_ETHER.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PURPLE_SOLID_ETHER = CONFIGURED_FEATURES.register("purple_solid_ether", () -> new ConfiguredFeature<>(AerialHellFeatures.PURPLE_SOLID_ETHER.get(), new NoneFeatureConfiguration()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = CONFIGURED_FEATURES.register("stellar_coarse_floor_in_dark_areas", () -> new ConfiguredFeature<>(AerialHellFeatures.STELLAR_COARSE_FLOOR_IN_DARK_AREAS.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_GRASS_IN_SHADOW_GRASS = CONFIGURED_FEATURES.register("stellar_grass_in_shadow_grass", () -> new ConfiguredFeature<>(AerialHellFeatures.STELLAR_GRASS_IN_SHADOW_GRASS.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MOSSY_STELLAR_COBBLESTONE_ROCK = CONFIGURED_FEATURES.register("mossy_stellar_cobblestone_rock", () -> new ConfiguredFeature<>(AerialHellFeatures.MOSSY_STELLAR_COBBLESTONE_ROCK.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DANGLING_CHAIN = CONFIGURED_FEATURES.register("dangling_chain", () -> new ConfiguredFeature<>(AerialHellFeatures.DANGLING_CHAIN.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SLIPPERY_SAND = CONFIGURED_FEATURES.register("slippery_sand", () -> new ConfiguredFeature<>(AerialHellFeatures.SLIPPERY_SAND.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTAL_BLOB = CONFIGURED_FEATURES.register("crystal_blob", () -> new ConfiguredFeature<>(AerialHellFeatures.CRYSTAL_BLOB.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SHADOW_CRYSTAL_BLOB = CONFIGURED_FEATURES.register("shadow_crystal_blob", () -> new ConfiguredFeature<>(AerialHellFeatures.SHADOW_CRYSTAL_BLOB.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = CONFIGURED_FEATURES.register("stellar_stone_crystal_blob_in_dark_areas", () -> new ConfiguredFeature<>(AerialHellFeatures.STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = CONFIGURED_FEATURES.register("shadow_crystal_blob_in_dark_areas", () -> new ConfiguredFeature<>(AerialHellFeatures.SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTALLIZED_FIRE = CONFIGURED_FEATURES.register("cristallized_fire", () -> new ConfiguredFeature<>(AerialHellFeatures.CRYSTALLIZED_FIRE.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GIANT_CRYSTAL_BLOB = CONFIGURED_FEATURES.register("giant_crystal_blob", () -> new ConfiguredFeature<>(AerialHellFeatures.GIANT_CRYSTAL_BLOB.get(), new NoneFeatureConfiguration()));

//    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_PORTAL_FRAME_ORE = CONFIGURED_FEATURES.register("stellar_portal_frame_ore", () -> GenAerialHellOres.STELLAR_PORTAL_FRAME_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> IRON_STELLAR_ORE = CONFIGURED_FEATURES.register("iron_stellar_ore", () -> GenAerialHellOres.IRON_STELLAR_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> GOLD_STELLAR_ORE = CONFIGURED_FEATURES.register("gold_stellar_ore", () -> GenAerialHellOres.GOLD_STELLAR_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> DIAMOND_STELLAR_ORE = CONFIGURED_FEATURES.register("diamond_stellar_ore", () -> GenAerialHellOres.DIAMOND_STELLAR_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> FLUORITE_ORE = CONFIGURED_FEATURES.register("fluorite_ore", () -> GenAerialHellOres.FLUORITE_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE = CONFIGURED_FEATURES.register("ruby_ore", () -> GenAerialHellOres.RUBY_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> MAGMATIC_GEL_ORE = CONFIGURED_FEATURES.register("magmatic_gel_ore", () -> GenAerialHellOres.MAGMATIC_GEL_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> AZURITE_ORE = CONFIGURED_FEATURES.register("azurite_ore", () -> GenAerialHellOres.AZURITE_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> SMOKY_QUARTZ_ORE = CONFIGURED_FEATURES.register("smoky_quartz_ore", () -> GenAerialHellOres.SMOKY_QUARTZ_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> VOLUCITE_ORE = CONFIGURED_FEATURES.register("volucite_ore", () -> GenAerialHellOres.VOLUCITE_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> OBSIDIAN_ORE = CONFIGURED_FEATURES.register("obsidian_ore", () -> GenAerialHellOres.OBSIDIAN_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> GLAUCOPHANITE_ORE = CONFIGURED_FEATURES.register("glaucophanite_ore", () -> GenAerialHellOres.GLAUCOPHANITE_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_DIRT_ORE = CONFIGURED_FEATURES.register("stellar_dirt_ore", () -> GenAerialHellOres.STELLAR_DIRT_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_COARSE_DIRT_ORE = CONFIGURED_FEATURES.register("stellar_coarse_dirt_ore", () -> GenAerialHellOres.STELLAR_COARSE_DIRT_ORE);
//    public static final RegistryObject<ConfiguredFeature<?, ?>> STELLAR_CLAY_ORE = CONFIGURED_FEATURES.register("stellar_clay_ore", () -> GenAerialHellOres.STELLAR_CLAY_ORE);


    //TODO : is there another way ?
    public static final RegistryObject<ConfiguredFeature<HugeFungusConfiguration, ?>> GIANT_CORTINARIUS_VIOLACEUS_PLANTED = CONFIGURED_FEATURES.register("giant_cortinarius_violaceus_planted", () -> new ConfiguredFeature<>(Feature.HUGE_FUNGUS, Configs.GIANT_CORTINARIUS_VIOLACEUS_CONFIG));
}