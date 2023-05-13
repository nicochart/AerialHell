package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.World.Features.*;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellFeatures
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AerialHell.MODID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GIANT_GANODERMA_APPLANATUM = FEATURES.register("giant_ganoderma_applanatum", () -> new GiantGanodermaApplanatumFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<HugeMushroomFeature> HUGE_MUSHROOM = FEATURES.register("huge_mushroom", () -> new HugeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));
	
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> WHITE_SOLID_ETHER = FEATURES.register("white_solid_ether", () -> new WhiteSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLUE_SOLID_ETHER = FEATURES.register("blue_solid_ether", () -> new BlueSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GOLDEN_SOLID_ETHER = FEATURES.register("golden_solid_ether", () -> new GoldenSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GREEN_SOLID_ETHER = FEATURES.register("green_solid_ether", () -> new GreenSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PURPLE_SOLID_ETHER = FEATURES.register("purple_solid_ether", () -> new PurpleSolidEtherCloudFeature(NoneFeatureConfiguration.CODEC));
	
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = FEATURES.register("stellar_coarse_floor_in_dark_areas", () -> new StellarCoarseFloorInDarkAreasFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> STELLAR_GRASS_IN_SHADOW_GRASS = FEATURES.register("stellar_grass_in_shadow_grass", () -> new StellarGrassInShadowGrassFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> MOSSY_STELLAR_COBBLESTONE_ROCK = FEATURES.register("mossy_stellar_cobblestone_rock", () -> new RockFeature(NoneFeatureConfiguration.CODEC, new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState(), 1).add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE.get().defaultBlockState(), 1))));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DANGLING_CHAIN = FEATURES.register("dangling_chain", () -> new DanglingChainFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SLIPPERY_SAND = FEATURES.register("slippery_sand", () -> new SlipperySandFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<AerialHellLakeFeature.Configuration>> AERIAL_HELL_WATER_LAKE = FEATURES.register("aerial_hell_water_lake", () -> new AerialHellLakeFeature(AerialHellLakeFeature.Configuration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRYSTAL_BLOB = FEATURES.register("crystal_blob", () -> new CrystalBlobFeature(() -> AerialHellBlocksAndItems.CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SHADOW_CRYSTAL_BLOB = FEATURES.register("shadow_crystal_blob", () -> new CrystalBlobFeature(() -> AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = FEATURES.register("stellar_stone_crystal_blob_in_dark_areas", () -> new StellarStoneCrystalBlobFeature(() -> AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = FEATURES.register("shadow_crystal_blob_in_dark_areas", () -> new StellarStoneCrystalBlobFeature(() -> AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRYSTALLIZED_FIRE = FEATURES.register("cristallized_fire", () -> new CrystallizedFireFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GIANT_CRYSTAL_BLOB = FEATURES.register("giant_crystal_blob", () -> new GiantCrystalBlobFeature(NoneFeatureConfiguration.CODEC));
}
