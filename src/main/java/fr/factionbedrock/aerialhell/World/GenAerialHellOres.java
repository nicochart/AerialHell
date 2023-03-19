package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class GenAerialHellOres
{
	public final static ConfiguredFeature<?,?> STELLAR_PORTAL_FRAME_ORE = OverworldOres
	(
			AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE.get().getDefaultState(), //Block enregistré à configurer
			5, //Taille de Filon
			0, //Hauteur minimum
			8, //Hauteur maximum
			64, //range
			12 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> IRON_STELLAR_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.IRON_STELLAR_ORE.get().getDefaultState(), //Block enregistré à configurer
			12, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			64, //range
			12 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> GOLD_STELLAR_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.GOLD_STELLAR_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			32, //range
			2 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> FLUORITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.FLUORITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			12, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			128, //range
			28 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> RUBY_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.RUBY_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			64, //range
			40 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> AZURITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.AZURITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			5, //Taille de Filon
			0, //Hauteur minimum
			128, //Hauteur maximum
			64, //range
			20 //Chance d'apparition
	);

	public final static ConfiguredFeature<?,?> SMOKY_QUARTZ_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.SMOKY_QUARTZ_ORE.get().getDefaultState(), //Block enregistré à configurer
			14, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			30 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> DIAMOND_STELLAR_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.DIAMOND_STELLAR_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			160, //Hauteur maximum
			32, //range
			1 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> VOLUCITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.VOLUCITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			112, //Hauteur minimum
			256, //Hauteur maximum
			32, //range
			4 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> OBSIDIAN_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.OBSIDIAN_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			150, //Hauteur maximum
			32, //range
			2 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> GLAUCOPHANITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.GLAUCOPHANITE.get().getDefaultState(), //Block enregistré à configurer
			32, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			10 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> MAGMATIC_GEL_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().getDefaultState(), //Block enregistré à configurer
			12, //Taille de Filon
			0, //Hauteur minimum
			128, //Hauteur maximum
			256, //range
			12 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_DIRT_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.STELLAR_DIRT.get().getDefaultState(), //Block enregistré à configurer
			33, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			10 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_COARSE_DIRT_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().getDefaultState(), //Block enregistré à configurer
			22, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			12 //Chance d'apparition
	);
	
	public final static ConfiguredFeature<?,?> STELLAR_CLAY_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.STELLAR_CLAY.get().getDefaultState(), //Block enregistré à configurer
			28, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			256, //range
			4 //Chance d'apparition
	);
	
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
    }
}
