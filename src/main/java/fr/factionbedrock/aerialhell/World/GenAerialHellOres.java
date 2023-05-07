package fr.factionbedrock.aerialhell.World;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class GenAerialHellOres
{
	public static class OrePlacements
	{
		//copy of methods of the same name in net.minecraft.data.worldgen.placement.OrePlacements;
		private static List<PlacementModifier> orePlacement(PlacementModifier countPlacementModifier, PlacementModifier placementModifier) {return List.of(countPlacementModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());}
		private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {return orePlacement(CountPlacement.of(count), placementModifier);}

		public final static List<PlacementModifier> STELLAR_PORTAL_FRAME_ORE = commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-55)));
		public final static List<PlacementModifier> IRON_STELLAR_ORE = commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(160)));
		public final static List<PlacementModifier> GOLD_STELLAR_ORE = commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(160)));
		public final static List<PlacementModifier> DIAMOND_STELLAR_ORE = commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(10), VerticalAnchor.absolute(100)));
		public final static List<PlacementModifier> FLUORITE_ORE = commonOrePlacement(40, HeightRangePlacement.triangle(VerticalAnchor.absolute(40), VerticalAnchor.absolute(200)));
		public final static List<PlacementModifier> RUBY_ORE = commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(140)));
		public final static List<PlacementModifier> MAGMATIC_GEL_ORE = commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(70)));
		public final static List<PlacementModifier> AZURITE_ORE = commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(170)));
		public final static List<PlacementModifier> SMOKY_QUARTZ_ORE = commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(200)));
		public final static List<PlacementModifier> VOLUCITE_ORE = commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.absolute(100), VerticalAnchor.absolute(180)));
		public final static List<PlacementModifier> OBSIDIAN_ORE = commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(70)));
		public final static List<PlacementModifier> GLAUCOPHANITE_ORE = commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(180)));
		public final static List<PlacementModifier> STELLAR_DIRT_ORE = commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(40), VerticalAnchor.absolute(200)));
		public final static List<PlacementModifier> STELLAR_COARSE_DIRT_ORE = commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(30), VerticalAnchor.absolute(150)));
		public final static List<PlacementModifier> STELLAR_CLAY_ORE = commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(40), VerticalAnchor.absolute(160)));
	}

	public final static ConfiguredFeature<?,?> STELLAR_PORTAL_FRAME_ORE = createOverworldOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE.get().defaultBlockState(), //Minerai généré dans la stone
			AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE.get().defaultBlockState(), //Minerai généré dans la deepslate
			5 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> IRON_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.IRON_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> GOLD_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.GOLD_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> FLUORITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.FLUORITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> RUBY_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.RUBY_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> AZURITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.AZURITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			5 //Taille de Filon
	);

	public final static ConfiguredFeature<?,?> SMOKY_QUARTZ_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.SMOKY_QUARTZ_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			14 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> DIAMOND_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.DIAMOND_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> VOLUCITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.VOLUCITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> OBSIDIAN_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.OBSIDIAN_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> GLAUCOPHANITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.GLAUCOPHANITE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			32 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> MAGMATIC_GEL_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_DIRT_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState(), //Minerai généré dans la stellar stone
			33 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_COARSE_DIRT_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().defaultBlockState(), //Minerai généré dans la stellar stone
			22 //Taille de Filon
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_CLAY_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_CLAY.get().defaultBlockState(), //Minerai généré dans la stellar stone
			28 //Taille de Filon
	);

	public static final RuleTest STELLAR_STONE_ORE_REPLACEABLES = new TagMatchTest(AerialHellTags.Blocks.STELLAR_STONE);

	public static ConfiguredFeature<?,?> createAerialHellOreConfiguredFeature(BlockState stellarStoneOreState, int oreVeinSize)
	{
		return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(getAerialHellTargetList(stellarStoneOreState), oreVeinSize));
	}

	public static ConfiguredFeature<?,?> createOverworldOreConfiguredFeature(BlockState stoneOreState, BlockState deepslateOreState, int oreVeinSize)
	{
		return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(getOverworldTargetList(stoneOreState, deepslateOreState), oreVeinSize));
	}

	public static List<OreConfiguration.TargetBlockState> getOverworldTargetList(BlockState stoneOreState, BlockState deepslateOreState)
	{
		return ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, stoneOreState), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslateOreState));
	}

	public static List<OreConfiguration.TargetBlockState> getAerialHellTargetList(BlockState stellarStoneOreState)
	{
		return ImmutableList.of(OreConfiguration.target(STELLAR_STONE_ORE_REPLACEABLES, stellarStoneOreState));
	}
}
