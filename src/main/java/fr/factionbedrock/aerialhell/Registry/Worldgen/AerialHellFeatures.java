package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Features.*;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ClassicGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.DeadGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ForkingGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.GiantPineTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.SolidEther.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

//Since 26.3, the feature type registry contains feature codecs (each feature instance holds its own configuration)
public class AerialHellFeatures
{

	public static final MapCodec<RandomPatchFeature> RANDOM_PATCH = register("random_patch", RandomPatchFeature.CODEC);

	public static final MapCodec<AerialHellLakeFeature> AERIAL_HELL_LAKE = register("lake", AerialHellLakeFeature.CODEC);

	public static final MapCodec<GiantGanodermaApplanatumFeature> GIANT_GANODERMA_APPLANATUM = register("giant_ganoderma_applanatum", GiantGanodermaApplanatumFeature.CODEC);
	public static final MapCodec<HugeMushroomFeature> HUGE_MUSHROOM = register("huge_mushroom", HugeMushroomFeature.CODEC);
	public static final MapCodec<AerialHellTwistingVinesFeature> TWISTING_ROOTS = register("twisting_roots", AerialHellTwistingVinesFeature.CODEC);

	public static final MapCodec<LargeDeadLogFeature> LARGE_DEAD_LOG = register("large_dead_log", LargeDeadLogFeature.CODEC);

	public static final MapCodec<ChorusLikeFeature> FULL_MOON_PLANT = register("full_moon_plant", ChorusLikeFeature.CODEC);

	public static final MapCodec<VerticalGrowingPlantFeature> VERTICAL_GROWING_PLANT = register("vertical_growing_plant", VerticalGrowingPlantFeature.CODEC);

	public static final MapCodec<WhiteSolidEtherCloudFeature> WHITE_SOLID_ETHER = register("white_solid_ether", WhiteSolidEtherCloudFeature.CODEC);
	public static final MapCodec<BlueSolidEtherCloudFeature> BLUE_SOLID_ETHER = register("blue_solid_ether", BlueSolidEtherCloudFeature.CODEC);
	public static final MapCodec<GoldenSolidEtherCloudFeature> GOLDEN_SOLID_ETHER = register("golden_solid_ether", GoldenSolidEtherCloudFeature.CODEC);
	public static final MapCodec<GreenSolidEtherCloudFeature> GREEN_SOLID_ETHER = register("green_solid_ether", GreenSolidEtherCloudFeature.CODEC);
	public static final MapCodec<PurpleSolidEtherCloudFeature> PURPLE_SOLID_ETHER = register("purple_solid_ether", PurpleSolidEtherCloudFeature.CODEC);

	public static final MapCodec<RootBridgeFeature> ROOT_BRIDGE = register("root_bridge", RootBridgeFeature.CODEC);
	public static final MapCodec<ClassicGiantTreeFeature> CLASSIC_GIANT_TREE = register("classic_giant_tree", ClassicGiantTreeFeature.CODEC);
	public static final MapCodec<ForkingGiantTreeFeature> FORKING_GIANT_TREE = register("forking_giant_tree", ForkingGiantTreeFeature.CODEC);
	public static final MapCodec<GiantPineTreeFeature> GIANT_PINE_TREE = register("giant_pine_tree", GiantPineTreeFeature.CODEC);
	public static final MapCodec<DeadGiantTreeFeature> DEAD_GIANT_TREE = register("dead_giant_tree", DeadGiantTreeFeature.CODEC);
	public static final MapCodec<MushroomCapsColumnFeature> MUSHROOM_CAPS_COLUMN = register("mushroom_caps_column", MushroomCapsColumnFeature.CODEC);

	public static final MapCodec<FloorTransformationFeature> FLOOR_TRANSFORMATION = register("floor_transformation", FloorTransformationFeature.CODEC);
	public static final MapCodec<RockFeature> ROCK = register("rock", RockFeature.CODEC);
	public static final MapCodec<DanglingChainFeature> DANGLING_CHAIN = register("dangling_chain", DanglingChainFeature.CODEC);
	public static final MapCodec<SlipperySandFeature> SLIPPERY_SAND = register("slippery_sand", SlipperySandFeature.CODEC);
	public static final MapCodec<NaturalFieldFeature> NATURAL_FIELD = register("natural_field", NaturalFieldFeature.CODEC);
	public static final MapCodec<CrystalBlobFeature> CRYSTAL_BLOB = register("crystal_blob", CrystalBlobFeature.CODEC);
	public static final MapCodec<LittleCrystalBlobInDarkAreasFeature> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = register("little_crystal_blob_in_dark_areas", LittleCrystalBlobInDarkAreasFeature.CODEC);
	public static final MapCodec<CrystallizedFireFeature> CRYSTALLIZED_FIRE = register("cristallized_fire", CrystallizedFireFeature.CODEC);
	public static final MapCodec<GiantCrystalBlobFeature> GIANT_CRYSTAL_BLOB = register("giant_crystal_blob", GiantCrystalBlobFeature.CODEC);

	public static final MapCodec<RandomPatchInDarkAreaFeature> RANDOM_PATCH_IN_DARK_AREA = register("random_patch_in_dark_area", RandomPatchInDarkAreaFeature.CODEC);
	public static final MapCodec<SingleBlockNeedingSupportFeature> SINGLE_BLOCK_NEEDING_SUPPORT = register("single_block_needing_support", SingleBlockNeedingSupportFeature.CODEC);

	private static <F extends Feature> MapCodec<F> register(String name, MapCodec<F> codec)
	{
		return Registry.register(BuiltInRegistries.FEATURE_TYPE, AerialHell.id(name), codec);
	}

	public static void load() {}
}
