package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = AerialHell.MODID, bus = Bus.MOD)
public class AerialHellBiomes
{
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AerialHell.MODID);
	
	//Biome de base
	public static final RegistryKey<Biome> AERIAL_HELL_PLAINS = register("aerial_hell_plains");
	//Océan de slippery sand
    public static final RegistryKey<Biome> SLIPPERY_SAND_OCEAN = register("slippery_sand_ocean");
    //Forêt d'arbres aériens
    public static final RegistryKey<Biome> AERIAL_TREE_FOREST = register("aerial_tree_forest");
    //Forêt de sapins cuivrés
    public static final RegistryKey<Biome> COPPER_PINE_FOREST = register("copper_pine_forest");
    //Plaine de Crystal
  	public static final RegistryKey<Biome> CRYSTAL_PLAINS = register("crystal_plains");
    //Plaine de Crystal
  	public static final RegistryKey<Biome> CRYSTAL_FOREST = register("crystal_forest");
    //Savane de Robinier de Lapis
  	public static final RegistryKey<Biome> LAPIS_ROBINIA_SAVANA = register("lapis_robinia_savana");
  	//Plaine des Ombres
  	public static final RegistryKey<Biome> SHADOW_PLAIN = register("shadow_plain");
  	//Forêt des Ombres
  	public static final RegistryKey<Biome> SHADOW_FOREST = register("shadow_forest");
  	//Forêt de Cortinaire Violet
  	public static final RegistryKey<Biome> CORTINARIUS_VIOLACEUS_FOREST = register("cortinarius_violaceus_forest");
  	//Forêt de Strophaire Vert-de-Gris
  	public static final RegistryKey<Biome> VERDIGRIS_AGARIC_FOREST = register("verdigris_agaric_forest");
    
    public static void toDictionary()
    {
        BiomeDictionary.addTypes(AERIAL_HELL_PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(SLIPPERY_SAND_OCEAN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
        BiomeDictionary.addTypes(AERIAL_TREE_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(COPPER_PINE_FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(CRYSTAL_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(CRYSTAL_FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.DENSE);
        BiomeDictionary.addTypes(AERIAL_HELL_PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SAVANNA);
        BiomeDictionary.addTypes(SHADOW_PLAIN, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(SHADOW_FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(CORTINARIUS_VIOLACEUS_FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.DENSE);
        BiomeDictionary.addTypes(VERDIGRIS_AGARIC_FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.DENSE);
    }    
	
	private static ResourceLocation name(String name)
	{
        return new ResourceLocation(AerialHell.MODID, name);
    }

	private static RegistryKey<Biome> register(String name)
    {
    	BIOMES.register(name, AerialHellBiomes::makeAerialHellBiome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name(name));
    }
    
    public static Biome makeAerialHellBiome()
    {
    	return new Biome.Builder()
				.precipitation(Biome.RainType.NONE)
				.category(Biome.Category.NONE)
				.depth(0)
				.downfall(0)
				.scale(0)
				.temperature(0)
				.setEffects(new BiomeAmbience.Builder().setFogColor(0).setWaterColor(0).setWaterFogColor(0).withSkyColor(0).setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(AerialHellSoundEvents.AERIALHELL_DIMENSION_MUSIC.get())).build())
				.withGenerationSettings(new BiomeGenerationSettings.Builder().withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244184_p).build())
				.withMobSpawnSettings(MobSpawnInfo.EMPTY)
				.withTemperatureModifier(Biome.TemperatureModifier.NONE)
				.build();
    }
}
