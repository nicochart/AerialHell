package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Features.*;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ClassicGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.DeadGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.ForkingGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.GiantPineTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.SolidEther.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

//Since 26.3, the feature type registry contains feature codecs (each feature instance holds its own configuration)
public class AerialHellFeatures
{
	public static final DeferredRegister<MapCodec<? extends Feature>> FEATURES = DeferredRegister.create(Registries.FEATURE_TYPE, AerialHell.MODID);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<RandomPatchFeature>> RANDOM_PATCH = FEATURES.register("random_patch", () -> RandomPatchFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<AerialHellLakeFeature>> AERIAL_HELL_LAKE = FEATURES.register("lake", () -> AerialHellLakeFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<GiantGanodermaApplanatumFeature>> GIANT_GANODERMA_APPLANATUM = FEATURES.register("giant_ganoderma_applanatum", () -> GiantGanodermaApplanatumFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<HugeMushroomFeature>> HUGE_MUSHROOM = FEATURES.register("huge_mushroom", () -> HugeMushroomFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<AerialHellTwistingVinesFeature>> TWISTING_ROOTS = FEATURES.register("twisting_roots", () -> AerialHellTwistingVinesFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<LargeDeadLogFeature>> LARGE_DEAD_LOG = FEATURES.register("large_dead_log", () -> LargeDeadLogFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<ChorusLikeFeature>> FULL_MOON_PLANT = FEATURES.register("full_moon_plant", () -> ChorusLikeFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<VerticalGrowingPlantFeature>> VERTICAL_GROWING_PLANT = FEATURES.register("vertical_growing_plant", () -> VerticalGrowingPlantFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<WhiteSolidEtherCloudFeature>> WHITE_SOLID_ETHER = FEATURES.register("white_solid_ether", () -> WhiteSolidEtherCloudFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<BlueSolidEtherCloudFeature>> BLUE_SOLID_ETHER = FEATURES.register("blue_solid_ether", () -> BlueSolidEtherCloudFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<GoldenSolidEtherCloudFeature>> GOLDEN_SOLID_ETHER = FEATURES.register("golden_solid_ether", () -> GoldenSolidEtherCloudFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<GreenSolidEtherCloudFeature>> GREEN_SOLID_ETHER = FEATURES.register("green_solid_ether", () -> GreenSolidEtherCloudFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<PurpleSolidEtherCloudFeature>> PURPLE_SOLID_ETHER = FEATURES.register("purple_solid_ether", () -> PurpleSolidEtherCloudFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<RootBridgeFeature>> ROOT_BRIDGE = FEATURES.register("root_bridge", () -> RootBridgeFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<ClassicGiantTreeFeature>> CLASSIC_GIANT_TREE = FEATURES.register("classic_giant_tree", () -> ClassicGiantTreeFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<ForkingGiantTreeFeature>> FORKING_GIANT_TREE = FEATURES.register("forking_giant_tree", () -> ForkingGiantTreeFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<GiantPineTreeFeature>> GIANT_PINE_TREE = FEATURES.register("giant_pine_tree", () -> GiantPineTreeFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<DeadGiantTreeFeature>> DEAD_GIANT_TREE = FEATURES.register("dead_giant_tree", () -> DeadGiantTreeFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<MushroomCapsColumnFeature>> MUSHROOM_CAPS_COLUMN = FEATURES.register("mushroom_caps_column", () -> MushroomCapsColumnFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<FloorTransformationFeature>> FLOOR_TRANSFORMATION = FEATURES.register("floor_transformation", () -> FloorTransformationFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<RockFeature>> ROCK = FEATURES.register("rock", () -> RockFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<DanglingChainFeature>> DANGLING_CHAIN = FEATURES.register("dangling_chain", () -> DanglingChainFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<SlipperySandFeature>> SLIPPERY_SAND = FEATURES.register("slippery_sand", () -> SlipperySandFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<NaturalFieldFeature>> NATURAL_FIELD = FEATURES.register("natural_field", () -> NaturalFieldFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<CrystalBlobFeature>> CRYSTAL_BLOB = FEATURES.register("crystal_blob", () -> CrystalBlobFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<LittleCrystalBlobInDarkAreasFeature>> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = FEATURES.register("little_crystal_blob_in_dark_areas", () -> LittleCrystalBlobInDarkAreasFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<CrystallizedFireFeature>> CRYSTALLIZED_FIRE = FEATURES.register("cristallized_fire", () -> CrystallizedFireFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<GiantCrystalBlobFeature>> GIANT_CRYSTAL_BLOB = FEATURES.register("giant_crystal_blob", () -> GiantCrystalBlobFeature.CODEC);

	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<RandomPatchInDarkAreaFeature>> RANDOM_PATCH_IN_DARK_AREA = FEATURES.register("random_patch_in_dark_area", () -> RandomPatchInDarkAreaFeature.CODEC);
	public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<SingleBlockNeedingSupportFeature>> SINGLE_BLOCK_NEEDING_SUPPORT = FEATURES.register("single_block_needing_support", () -> SingleBlockNeedingSupportFeature.CODEC);
}
