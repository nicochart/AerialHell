package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.World.Features.*;
import fr.factionbedrock.aerialhell.World.Features.Config.*;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ClassicGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.DeadGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ForkingGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.GiantPineTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.SolidEther.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellFeatures
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, AerialHell.MODID);

	public static final DeferredHolder<Feature<?>, AerialHellLakeFeature> AERIAL_HELL_LAKE = FEATURES.register("lake", () -> new AerialHellLakeFeature(AerialHellLakeFeature.Configuration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_GANODERMA_APPLANATUM = FEATURES.register("giant_ganoderma_applanatum", () -> new GiantGanodermaApplanatumFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<HugeMushroomFeatureConfiguration>> HUGE_MUSHROOM = FEATURES.register("huge_mushroom", () -> new HugeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<AerialHellTwistingVinesConfig>> LAZULI_ROOTS = FEATURES.register("lazuli_roots", () -> new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocksAndItems.LAZULI_ROOTS.get(), () -> AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get()));
	public static final DeferredHolder<Feature<?>, Feature<AerialHellTwistingVinesConfig>> STELLAR_ROOTS = FEATURES.register("stellar_roots", () -> new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocksAndItems.STELLAR_ROOTS.get(), () -> AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get()));
	public static final DeferredHolder<Feature<?>, Feature<AerialHellTwistingVinesConfig>> DEAD_ROOTS = FEATURES.register("dead_roots", () -> new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocksAndItems.DEAD_ROOTS.get(), () -> AerialHellBlocksAndItems.DEAD_ROOTS_PLANT.get()));
	public static final DeferredHolder<Feature<?>, Feature<AerialHellTwistingVinesConfig>> GLOWING_ROOTS = FEATURES.register("glowing_roots", () -> new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocksAndItems.GLOWING_ROOTS.get(), () -> AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get()));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG = FEATURES.register("large_dead_stellar_jungle_tree_log", () -> new LargeDeadLogFeature(NoneFeatureConfiguration.CODEC, () -> AerialHellBlocksAndItems.DEAD_STELLAR_JUNGLE_TREE_LOG.get()));

	public static final DeferredHolder<Feature<?>, Feature<ChorusLikePlantConfig>> FULL_MOON_PLANT = FEATURES.register("full_moon_plant", () -> new ChorusLikeFeature(ChorusLikePlantConfig.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<VerticalGrowingPlantConfig>> CLIMBING_VINE = FEATURES.register("climbing_vine", () -> new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocksAndItems.CLIMBING_VINE.get()));
	public static final DeferredHolder<Feature<?>, Feature<VerticalGrowingPlantConfig>> STELLAR_SUGAR_CANE = FEATURES.register("stellar_sugar_cane", () -> new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocksAndItems.STELLAR_SUGAR_CANE.get()));
	public static final DeferredHolder<Feature<?>, Feature<VerticalGrowingPlantConfig>> STELLAR_VERY_TALL_GRASS = FEATURES.register("stellar_very_tall_grass", () -> new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS.get()));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WHITE_SOLID_ETHER = FEATURES.register("white_solid_ether", () -> new WhiteSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BLUE_SOLID_ETHER = FEATURES.register("blue_solid_ether", () -> new BlueSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GOLDEN_SOLID_ETHER = FEATURES.register("golden_solid_ether", () -> new GoldenSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GREEN_SOLID_ETHER = FEATURES.register("green_solid_ether", () -> new GreenSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PURPLE_SOLID_ETHER = FEATURES.register("purple_solid_ether", () -> new PurpleSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROOT_BRIDGE = FEATURES.register("root_bridge", () -> new RootBridgeFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<ClassicGiantTreeConfig>> CLASSIC_GIANT_TREE = FEATURES.register("classic_giant_tree", () -> new ClassicGiantTreeFeature(ClassicGiantTreeConfig.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<ForkingGiantTreeConfig>> FORKING_GIANT_TREE = FEATURES.register("forking_giant_tree", () -> new ForkingGiantTreeFeature(ForkingGiantTreeConfig.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<GiantPineTreeConfig>> GIANT_PINE_TREE = FEATURES.register("giant_pine_tree", () -> new GiantPineTreeFeature(GiantPineTreeConfig.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<DeadGiantTreeConfig>> DEAD_GIANT_TREE = FEATURES.register("dead_giant_tree", () -> new DeadGiantTreeFeature(DeadGiantTreeConfig.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<MushroomCapsColumnConfig>> MUSHROOM_CAPS_COLUMN = FEATURES.register("mushroom_caps_column", () -> new MushroomCapsColumnFeature(MushroomCapsColumnConfig.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<FloorTransformationConfig>> FLOOR_TRANSFORMATION = FEATURES.register("floor_transformation", () -> new FloorTransformationFeature(FloorTransformationConfig.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MOSSY_STELLAR_COBBLESTONE_ROCK = FEATURES.register("mossy_stellar_cobblestone_rock", () -> new RockFeature(NoneFeatureConfiguration.CODEC, new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState(), 1).add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE.get().defaultBlockState(), 1))));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DANGLING_CHAIN = FEATURES.register("dangling_chain", () -> new DanglingChainFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SLIPPERY_SAND = FEATURES.register("slippery_sand", () -> new SlipperySandFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CRYSTAL_BLOB = FEATURES.register("crystal_blob", () -> new CrystalBlobFeature(() -> AerialHellBlocksAndItems.CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SHADOW_CRYSTAL_BLOB = FEATURES.register("shadow_crystal_blob", () -> new CrystalBlobFeature(() -> AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = FEATURES.register("stellar_stone_crystal_blob_in_dark_areas", () -> new StellarStoneCrystalBlobFeature(() -> AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = FEATURES.register("shadow_crystal_blob_in_dark_areas", () -> new StellarStoneCrystalBlobFeature(() -> AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CRYSTALLIZED_FIRE = FEATURES.register("cristallized_fire", () -> new CrystallizedFireFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_CRYSTAL_BLOB = FEATURES.register("giant_crystal_blob", () -> new GiantCrystalBlobFeature(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<RandomPatchConfiguration>> RANDOM_PATCH_IN_DARK_AREA = FEATURES.register("random_patch_in_dark_area", () -> new RandomPatchInDarkAreaFeature(RandomPatchConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<SingleBlockNeedingSupportConfig>> SINGLE_BLOCK_NEEDING_SUPPORT = FEATURES.register("single_block_needing_support", () -> new SingleBlockNeedingSupportFeature(SingleBlockNeedingSupportConfig.CODEC));
}
