package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Features.*;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellFeatures
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AerialHell.MODID);

	public static final RegistryObject<Feature<NoFeatureConfig>> GIANT_GANODERMA_APPLANATUM = FEATURES.register("giant_ganoderma_applanatum", () -> new GiantGanodermaApplanatumFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<HugeMushroomFeature> HUGE_MUSHROOM = FEATURES.register("huge_mushroom", () -> new HugeMushroomFeature(BigMushroomFeatureConfig.CODEC));
	
	public static final RegistryObject<Feature<NoFeatureConfig>> WHITE_SOLID_ETHER = FEATURES.register("white_solid_ether", () -> new WhiteSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BLUE_SOLID_ETHER = FEATURES.register("blue_solid_ether", () -> new BlueSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GOLDEN_SOLID_ETHER = FEATURES.register("golden_solid_ether", () -> new GoldenSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GREEN_SOLID_ETHER = FEATURES.register("green_solid_ether", () -> new GreenSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_));
	
	public static final RegistryObject<Feature<NoFeatureConfig>> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = FEATURES.register("stellar_coarse_floor_in_dark_areas", () -> new StellarCoarseFloorInDarkAreasFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> STELLAR_GRASS_IN_SHADOW_GRASS = FEATURES.register("stellar_grass_in_shadow_grass", () -> new StellarGrassInShadowGrassFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DANGLING_CHAIN = FEATURES.register("dangling_chain", () -> new DanglingChainFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SLIPPERY_SAND = FEATURES.register("slippery_sand", () -> new SlipperySandFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BlockStateFeatureConfig>> AERIAL_HELL_WATER_LAKE = FEATURES.register("aerial_hell_water_lake", () -> new AerialHellLakeFeature(BlockStateFeatureConfig.field_236455_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> CRYSTAL_BLOB = FEATURES.register("crystal_blob", () -> new CrystalBlobFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> STELLAR_STONE_CRYSTAL_BLOB = FEATURES.register("stellar_stone_crystal_blob", () -> new StellarStoneCrystalBlobFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> CRYSTALLIZED_FIRE = FEATURES.register("cristallized_fire", () -> new CrystallizedFireFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> GIANT_CRYSTAL_BLOB = FEATURES.register("giant_crystal_blob", () -> new GiantCrystalBlobFeature(NoFeatureConfig.field_236558_a_));
}
