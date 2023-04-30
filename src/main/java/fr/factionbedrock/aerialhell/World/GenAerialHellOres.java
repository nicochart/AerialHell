package fr.factionbedrock.aerialhell.World;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class GenAerialHellOres
{
	public final static ConfiguredFeature<?,?> STELLAR_PORTAL_FRAME_ORE = createOverworldOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE.get().defaultBlockState(), //Minerai généré dans la stone
			AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE.get().defaultBlockState(), //Minerai généré dans la deepslate
			5/*, //Taille de Filon
			0, //Hauteur minimum
			8, //Hauteur maximum
			64, //range
			12 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> IRON_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.IRON_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12/*, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			64, //range
			12 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> GOLD_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.GOLD_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7/*, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			32, //range
			2 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> FLUORITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.FLUORITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			128, //range
			28 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> RUBY_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.RUBY_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7/*, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			64, //range
			40 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> AZURITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.AZURITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			5/*, //Taille de Filon
			0, //Hauteur minimum
			128, //Hauteur maximum
			64, //range
			20 //Chance d'apparition*/
	);

	public final static ConfiguredFeature<?,?> SMOKY_QUARTZ_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.SMOKY_QUARTZ_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			14/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			30 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> DIAMOND_STELLAR_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.DIAMOND_STELLAR_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7/*, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			32, //range
			1 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> VOLUCITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.VOLUCITE_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7/*, //Taille de Filon
			112, //Hauteur minimum
			256, //Hauteur maximum
			32, //range
			4 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> OBSIDIAN_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.OBSIDIAN_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			7/*, //Taille de Filon
			0, //Hauteur minimum
			150, //Hauteur maximum
			32, //range
			2 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> GLAUCOPHANITE_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.GLAUCOPHANITE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			32/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			10 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> MAGMATIC_GEL_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().defaultBlockState(), //Minerai généré dans la stellar stone
			12/*, //Taille de Filon
			0, //Hauteur minimum
			128, //Hauteur maximum
			256, //range
			12 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_DIRT_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState(), //Minerai généré dans la stellar stone
			33/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			10 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_COARSE_DIRT_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().defaultBlockState(), //Minerai généré dans la stellar stone
			22/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			12 //Chance d'apparition*/
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_CLAY_ORE = createAerialHellOreConfiguredFeature
	(
			AerialHellBlocksAndItems.STELLAR_CLAY.get().defaultBlockState(), //Minerai généré dans la stellar stone
			28/*, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			4 //Chance d'apparition*/
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

	/* OUTDATED ; TODO : remove
	public static final class FillerBlockType
	{
		public static final RuleTest BASE_STELLAR_STONE_AERIALHELL = new TagMatchRuleTest(AerialHellTags.Blocks.STELLAR_STONE);
	}

	public static ConfiguredFeature<?,?> AerialHellOres(BlockState blockState, int oreVeinSize, int minHeight, int maxHeight, int range, int chance)
	{
        return Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.BASE_STELLAR_STONE_AERIALHELL, blockState, oreVeinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight,0,maxHeight))).range(range).square().func_242731_b(chance);
    }

	public static ConfiguredFeature<?,?> OverworldOres(BlockState blockState, int oreVeinSize, int minHeight, int maxHeight, int range, int chance)
	{
        return Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, blockState, oreVeinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight,0,maxHeight))).range(range).square().func_242731_b(chance);
    }*/
}
