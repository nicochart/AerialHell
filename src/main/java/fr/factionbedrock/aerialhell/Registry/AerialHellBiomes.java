package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
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
    
    public static void toDictionary()
    {
        BiomeDictionary.addTypes(AERIAL_HELL_PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(SLIPPERY_SAND_OCEAN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY);
        BiomeDictionary.addTypes(AERIAL_TREE_FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(COPPER_PINE_FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(CRYSTAL_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(CRYSTAL_FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.DENSE);
    }    
	
	private static ResourceLocation name(String name)
	{
        return new ResourceLocation(AerialHell.MODID, name);
    }

    private static RegistryKey<Biome> register(String name)
    {
        BIOMES.register(name, BiomeMaker::makeVoidBiome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, name(name));
    }
}
