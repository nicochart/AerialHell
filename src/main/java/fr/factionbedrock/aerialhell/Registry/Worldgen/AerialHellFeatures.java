package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.World.Features.*;
import fr.factionbedrock.aerialhell.World.Features.Config.*;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ClassicGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.DeadGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ForkingGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.GiantPineTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.HugeMushroomFeature;
import fr.factionbedrock.aerialhell.World.Features.SolidEther.*;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class AerialHellFeatures
{
	public static final Feature<?> AERIAL_HELL_LAKE = register("lake", new AerialHellLakeFeature(AerialHellLakeFeature.Config.CODEC));

	public static final Feature<?> GIANT_GANODERMA_APPLANATUM = register("giant_ganoderma_applanatum", new GiantGanodermaApplanatumFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> HUGE_MUSHROOM = register("huge_mushroom", new HugeMushroomFeature(HugeMushroomFeatureConfig.CODEC));
	public static final Feature<?> LAZULI_ROOTS = register("lazuli_roots", new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocks.LAZULI_ROOTS, () -> AerialHellBlocks.LAZULI_ROOTS_PLANT));
	public static final Feature<?> STELLAR_ROOTS = register("stellar_roots", new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocks.STELLAR_ROOTS, () -> AerialHellBlocks.STELLAR_ROOTS_PLANT));
	public static final Feature<?> DEAD_ROOTS = register("dead_roots", new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocks.DEAD_ROOTS, () -> AerialHellBlocks.DEAD_ROOTS_PLANT));
	public static final Feature<?> GLOWING_ROOTS = register("glowing_roots", new AerialHellTwistingVinesFeature(AerialHellTwistingVinesConfig.CODEC, () -> AerialHellBlocks.GLOWING_ROOTS, () -> AerialHellBlocks.GLOWING_ROOTS_PLANT));

	public static final Feature<?> LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG = register("large_dead_stellar_jungle_tree_log", new LargeDeadLogFeature(DefaultFeatureConfig.CODEC, () -> AerialHellBlocks.DEAD_STELLAR_JUNGLE_TREE_LOG));

	public static final Feature<?> FULL_MOON_PLANT = register("full_moon_plant", new ChorusLikeFeature(ChorusLikePlantConfig.CODEC));

	public static final Feature<?> CLIMBING_VINE = register("climbing_vine", new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocks.CLIMBING_VINE));
	public static final Feature<?> STELLAR_SUGAR_CANE = register("stellar_sugar_cane", new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocks.STELLAR_SUGAR_CANE));
	public static final Feature<?> STELLAR_VERY_TALL_GRASS = register("stellar_very_tall_grass", new VerticalGrowingPlantFeature(VerticalGrowingPlantConfig.CODEC, () -> AerialHellBlocks.STELLAR_VERY_TALL_GRASS));

	public static final Feature<?> WHITE_SOLID_ETHER = register("white_solid_ether", new WhiteSolidEtherCloudFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> BLUE_SOLID_ETHER = register("blue_solid_ether", new BlueSolidEtherCloudFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> GOLDEN_SOLID_ETHER = register("golden_solid_ether", new GoldenSolidEtherCloudFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> GREEN_SOLID_ETHER = register("green_solid_ether", new GreenSolidEtherCloudFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> PURPLE_SOLID_ETHER = register("purple_solid_ether", new PurpleSolidEtherCloudFeature(DefaultFeatureConfig.CODEC));

	public static final Feature<?> ROOT_BRIDGE = register("root_bridge", new RootBridgeFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> CLASSIC_GIANT_TREE = register("classic_giant_tree", new ClassicGiantTreeFeature(ClassicGiantTreeConfig.CODEC));
	public static final Feature<?> FORKING_GIANT_TREE = register("forking_giant_tree", new ForkingGiantTreeFeature(ForkingGiantTreeConfig.CODEC));
	public static final Feature<?> GIANT_PINE_TREE = register("giant_pine_tree", new GiantPineTreeFeature(GiantPineTreeConfig.CODEC));
	public static final Feature<?> DEAD_GIANT_TREE = register("dead_giant_tree", new DeadGiantTreeFeature(DeadGiantTreeConfig.CODEC));
	public static final Feature<?> MUSHROOM_CAPS_COLUMN = register("mushroom_caps_column", new MushroomCapsColumnFeature(MushroomCapsColumnConfig.CODEC));

	public static final Feature<?> FLOOR_TRANSFORMATION = register("floor_transformation", new FloorTransformationFeature(FloorTransformationConfig.CODEC));
	public static final Feature<?> MOSSY_STELLAR_COBBLESTONE_ROCK = register("mossy_stellar_cobblestone_rock", new RockFeature(DefaultFeatureConfig.CODEC, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.getDefaultState(), 1).add(AerialHellBlocks.STELLAR_COBBLESTONE.getDefaultState(), 1))));
	public static final Feature<?> DANGLING_CHAIN = register("dangling_chain", new DanglingChainFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> SLIPPERY_SAND = register("slippery_sand", new SlipperySandFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> CRYSTAL_BLOB = register("crystal_blob", new CrystalBlobFeature(() -> AerialHellBlocks.CRYSTAL_BLOCK, DefaultFeatureConfig.CODEC));
	public static final Feature<?> SHADOW_CRYSTAL_BLOB = register("shadow_crystal_blob", new CrystalBlobFeature(() -> AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, DefaultFeatureConfig.CODEC));
	public static final Feature<?> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = register("stellar_stone_crystal_blob_in_dark_areas", new StellarStoneCrystalBlobFeature(() -> AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK, DefaultFeatureConfig.CODEC));
	public static final Feature<?> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = register("shadow_crystal_blob_in_dark_areas", new StellarStoneCrystalBlobFeature(() -> AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, DefaultFeatureConfig.CODEC));
	public static final Feature<?> CRYSTALLIZED_FIRE = register("cristallized_fire", new CrystallizedFireFeature(DefaultFeatureConfig.CODEC));
	public static final Feature<?> GIANT_CRYSTAL_BLOB = register("giant_crystal_blob", new GiantCrystalBlobFeature(DefaultFeatureConfig.CODEC));

	public static final Feature<?> RANDOM_PATCH_IN_DARK_AREA = register("random_patch_in_dark_area", new RandomPatchInDarkAreaFeature(RandomPatchFeatureConfig.CODEC));
	public static final Feature<?> SINGLE_BLOCK_NEEDING_SUPPORT = register("single_block_needing_support", new SingleBlockNeedingSupportFeature(SingleBlockNeedingSupportConfig.CODEC));

	public static Feature<?> register(String name, Feature<?> feature)
	{
		return Registry.register(Registries.FEATURE, AerialHell.id(name), feature);
	}

	public static void load() {}
}