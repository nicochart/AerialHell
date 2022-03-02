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
	public static ConfiguredFeature<?,?> BEDROCK_ORE = OverworldOres
	(
			AerialHellBlocksAndItems.BEDROCK_ORE.get().getDefaultState(), //Block enregistré à configurer
			5, //Taille de Filon
			0, //Hauteur minimum
			8, //Hauteur maximum
			2 //Chance d'apparition (Nombre de fillons par chunks)
	);
	
	public static ConfiguredFeature<?,?> FLUORITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.FLUORITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			9, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			2 //Chance d'apparition
	);
	
	public static ConfiguredFeature<?,?> RUBY_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.RUBY_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			6 //Chance d'apparition
	);
	
	public static ConfiguredFeature<?,?> AZURITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.AZURITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			5, //Taille de Filon
			0, //Hauteur minimum
			128, //Hauteur maximum
			9 //Chance d'apparition
	);

	public static ConfiguredFeature<?,?> VOLUCITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.VOLUCITE_ORE.get().getDefaultState(), //Block enregistré à configurer
			7, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			16 //Chance d'apparition
	);
	
	public static ConfiguredFeature<?,?> GLAUCOPHANITE_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.GLAUCOPHANITE.get().getDefaultState(), //Block enregistré à configurer
			16, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			10 //Chance d'apparition
	);
	
	public static ConfiguredFeature<?,?> MAGMATIC_GEL_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().getDefaultState(), //Block enregistré à configurer
			12, //Taille de Filon
			0, //Hauteur minimum
			56, //Hauteur maximum
			12 //Chance d'apparition
	);
	
	public static ConfiguredFeature<?,?> STELLAR_DIRT_ORE = AerialHellOres
	(
			AerialHellBlocksAndItems.STELLAR_DIRT.get().getDefaultState(), //Block enregistré à configurer
			33, //Taille de Filon
			0, //Hauteur minimum
			256, //Hauteur maximum
			10 //Chance d'apparition
	);
	
	public static final class FillerBlockType
	{
		public static final RuleTest BASE_STELLAR_STONE_AERIALHELL = new TagMatchRuleTest(AerialHellTags.Blocks.STELLAR_STONE);
	}
	
	public static ConfiguredFeature<?,?> AerialHellOres(BlockState blockState, int oreVeinSize, int minHeight, int maxHeight, int chance)
	{
        return Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.BASE_STELLAR_STONE_AERIALHELL, blockState, oreVeinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight,0,maxHeight))).square().chance(chance);
    }
	
	public static ConfiguredFeature<?,?> OverworldOres(BlockState blockState, int oreVeinSize, int minHeight, int maxHeight, int chance)
	{
        return Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, blockState, oreVeinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight,0,maxHeight))).square().chance(chance);
    }
}
