package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class AerialHellBiomes
{
	//Biome de base
	public static final ResourceKey<Biome> AERIAL_HELL_PLAINS = makeBiomeKey("aerial_hell_plains");
	//Foret d'arbres aeriens
	public static final ResourceKey<Biome> AERIAL_TREE_FOREST = makeBiomeKey("aerial_tree_forest");
	//Ocean de slippery sand
	public static final ResourceKey<Biome> SLIPPERY_SAND_OCEAN = makeBiomeKey("slippery_sand_ocean");
	//Foret de sapins cuivres
	public static final ResourceKey<Biome> COPPER_PINE_FOREST = makeBiomeKey("copper_pine_forest");
	//Foret de sapins cuivres des hauteurs
	public static final ResourceKey<Biome> COPPER_PINE_HIGHLAND_FOREST = makeBiomeKey("copper_pine_highland_forest");
	//Plaine de Crystal
	public static final ResourceKey<Biome> CRYSTAL_PLAINS = makeBiomeKey("crystal_plains");
	//Foret de Crystal
	public static final ResourceKey<Biome> CRYSTAL_FOREST = makeBiomeKey("crystal_forest");
	//Savane de Robinier de Lapis
	public static final ResourceKey<Biome> LAPIS_ROBINIA_SAVANA = makeBiomeKey("lapis_robinia_savana");
	//Plaine des Ombres
	public static final ResourceKey<Biome> SHADOW_PLAIN = makeBiomeKey("shadow_plain");
	//Foret des Ombres
	public static final ResourceKey<Biome> SHADOW_FOREST = makeBiomeKey("shadow_forest");
	//Foret de Cortinaire Violet
	public static final ResourceKey<Biome> CORTINARIUS_VIOLACEUS_FOREST = makeBiomeKey("cortinarius_violaceus_forest");
	//Foret de Strophaire Vert-de-Gris
	public static final ResourceKey<Biome> VERDIGRIS_AGARIC_FOREST = makeBiomeKey("verdigris_agaric_forest");
	//Foret de Champignon Rouge
	public static final ResourceKey<Biome> GIANT_RED_MUSHROOM_FOREST = makeBiomeKey("giant_red_mushroom_forest");

	private static ResourceKey<Biome> makeBiomeKey(String name)
	{
		return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
	}
}