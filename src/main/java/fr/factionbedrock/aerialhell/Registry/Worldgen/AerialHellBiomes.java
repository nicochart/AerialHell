package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = AerialHell.MODID, bus = Bus.MOD)
public class AerialHellBiomes
{
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AerialHell.MODID);
	
	//Biome de base
	public static final RegistryObject<Biome> AERIAL_HELL_PLAINS = BIOMES.register("aerial_hell_plains", AerialHellBiomes::makeAerialHellBiome);
	//For�t d'arbres a�riens
	public static final RegistryObject<Biome> AERIAL_TREE_FOREST = BIOMES.register("aerial_tree_forest", AerialHellBiomes::makeAerialHellBiome);
	//Oc�an de slippery sand
	public static final RegistryObject<Biome> SLIPPERY_SAND_OCEAN = BIOMES.register("slippery_sand_ocean", AerialHellBiomes::makeAerialHellBiome);
	//For�t de sapins cuivr�s
	public static final RegistryObject<Biome> COPPER_PINE_FOREST = BIOMES.register("copper_pine_forest", AerialHellBiomes::makeAerialHellBiome);
	//Plaine de Crystal
	public static final RegistryObject<Biome> CRYSTAL_PLAINS = BIOMES.register("crystal_plains", AerialHellBiomes::makeAerialHellBiome);
	//For�t de Crystal
	public static final RegistryObject<Biome> CRYSTAL_FOREST = BIOMES.register("crystal_forest", AerialHellBiomes::makeAerialHellBiome);
	//Savane de Robinier de Lapis
	public static final RegistryObject<Biome> LAPIS_ROBINIA_SAVANA = BIOMES.register("lapis_robinia_savana", AerialHellBiomes::makeAerialHellBiome);
	//Plaine des Ombres
	public static final RegistryObject<Biome> SHADOW_PLAIN = BIOMES.register("shadow_plain", AerialHellBiomes::makeAerialHellBiome);
	//For�t des Ombres
	public static final RegistryObject<Biome> SHADOW_FOREST = BIOMES.register("shadow_forest", AerialHellBiomes::makeAerialHellBiome);
	//For�t de Cortinaire Violet
	public static final RegistryObject<Biome> CORTINARIUS_VIOLACEUS_FOREST = BIOMES.register("cortinarius_violaceus_forest", AerialHellBiomes::makeAerialHellBiome);
	//For�t de Strophaire Vert-de-Gris
	public static final RegistryObject<Biome> VERDIGRIS_AGARIC_FOREST = BIOMES.register("verdigris_agaric_forest", AerialHellBiomes::makeAerialHellBiome);
	//For�t de Champignon Rouge
	public static final RegistryObject<Biome> GIANT_RED_MUSHROOM_FOREST = BIOMES.register("giant_red_mushroom_forest", AerialHellBiomes::makeAerialHellBiome);
    
    public static void toDictionary()
    {
        BiomeDictionary.addTypes(getBiomeKey(AERIAL_HELL_PLAINS.get()), BiomeDictionary.Type.HOT, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(getBiomeKey(SLIPPERY_SAND_OCEAN.get()), BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
        BiomeDictionary.addTypes(getBiomeKey(AERIAL_TREE_FOREST.get()), BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(getBiomeKey(COPPER_PINE_FOREST.get()), BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(getBiomeKey(CRYSTAL_PLAINS.get()), BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(getBiomeKey(CRYSTAL_FOREST.get()), BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.DENSE);
        BiomeDictionary.addTypes(getBiomeKey(LAPIS_ROBINIA_SAVANA.get()), BiomeDictionary.Type.HOT, BiomeDictionary.Type.SAVANNA);
        BiomeDictionary.addTypes(getBiomeKey(SHADOW_PLAIN.get()), BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(getBiomeKey(SHADOW_FOREST.get()), BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(getBiomeKey(CORTINARIUS_VIOLACEUS_FOREST.get()), BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH);
        BiomeDictionary.addTypes(getBiomeKey(VERDIGRIS_AGARIC_FOREST.get()), BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH);
        BiomeDictionary.addTypes(getBiomeKey(GIANT_RED_MUSHROOM_FOREST.get()), BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.LUSH);
    }

	private static ResourceKey<Biome> getBiomeKey(Biome biome)
	{
		return ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
	}
    
    public static Biome makeAerialHellBiome()
    {
    	return new Biome.BiomeBuilder()
				.precipitation(Biome.Precipitation.NONE)
				.biomeCategory(Biome.BiomeCategory.NONE)
				.downfall(0)
				.temperature(0)
				.specialEffects(new BiomeSpecialEffects.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).backgroundMusic(new Music(AerialHellSoundEvents.AERIALHELL_DIMENSION_MUSIC.get(), 1200, 3600, false)).build())
				.generationSettings(new BiomeGenerationSettings.Builder().build())
				.mobSpawnSettings(MobSpawnSettings.EMPTY)
				.temperatureAdjustment(Biome.TemperatureModifier.NONE)
				.build();
    }
}
