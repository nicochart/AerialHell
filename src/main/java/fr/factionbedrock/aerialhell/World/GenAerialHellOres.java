package fr.factionbedrock.aerialhell.World;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
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
	/* now done in aerialhell data
	public static class OrePlacements
	{
		//copy of methods of the same name in net.minecraft.data.worldgen.placement.OrePlacements;
		private static List<PlacementModifier> orePlacement(PlacementModifier countPlacementModifier, PlacementModifier placementModifier) {return List.of(countPlacementModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());}
		private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {return orePlacement(CountPlacement.of(count), placementModifier);}

		public final static List<PlacementModifier> STELLAR_PORTAL_FRAME_ORE = commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-55)));
		public final static List<PlacementModifier> IRON_STELLAR_ORE = commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(160)));
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

	private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
	private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

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
		return ImmutableList.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, stoneOreState), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, deepslateOreState));
	}

	public static List<OreConfiguration.TargetBlockState> getAerialHellTargetList(BlockState stellarStoneOreState)
	{
		return ImmutableList.of(OreConfiguration.target(STELLAR_STONE_ORE_REPLACEABLES, stellarStoneOreState));
	}*/
}
