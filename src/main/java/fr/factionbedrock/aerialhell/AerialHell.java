package fr.factionbedrock.aerialhell;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import fr.factionbedrock.aerialhell.Setup.*;
import fr.factionbedrock.aerialhell.World.GenAerialHellOres;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AerialHell.MODID) 
public class AerialHell
{
	public static final String MODID = "aerialhell";
	public static final String NAME = "Aerial Hell";
	public static final String VERSION = "1.0";
	
	public static Logger LOGGER = LogManager.getLogger();

    public AerialHell()
    {
    	AerialHellSetup.registration();
    	
    	
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
		
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AerialHellSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AerialHellClientSetup::init);
    } 
    
    
    public void biomeModification(final BiomeLoadingEvent event)
    {	
    	/* Adding Portal Ore & Abandonned Portal structure generation to Overworld Biomes */
    	if (event.getCategory() == Biome.Category.TAIGA || event.getCategory() == Biome.Category.EXTREME_HILLS || event.getCategory() == Biome.Category.JUNGLE || event.getCategory() == Biome.Category.MESA || event.getCategory() == Biome.Category.PLAINS || event.getCategory() == Biome.Category.SAVANNA || event.getCategory() == Biome.Category.ICY || event.getCategory() == Biome.Category.BEACH || event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.OCEAN || event.getCategory() == Biome.Category.DESERT || event.getCategory() == Biome.Category.RIVER || event.getCategory() == Biome.Category.SWAMP || event.getCategory() == Biome.Category.MUSHROOM)
    	{
    		event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.STELLAR_PORTAL_FRAME_ORE);
    		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_OVERWORLD_ABANDONNED_PORTAL_STRUCTURE);
    	}
    		
    	/* Adding common features and structure in all classic aerial hell biomes */
    	if (event.getName().equals(AerialHellBiomes.AERIAL_HELL_PLAINS.getLocation()) || event.getName().equals(AerialHellBiomes.AERIAL_TREE_FOREST.getLocation()) || event.getName().equals(AerialHellBiomes.COPPER_PINE_FOREST.getLocation()) || event.getName().equals(AerialHellBiomes.SLIPPERY_SAND_OCEAN.getLocation()) || event.getName().equals(AerialHellBiomes.CRYSTAL_PLAINS.getLocation()) || event.getName().equals(AerialHellBiomes.LAPIS_ROBINIA_SAVANA.getLocation()) || event.getName().equals(AerialHellBiomes.CORTINARIUS_VIOLACEUS_FOREST.getLocation()) || event.getName().equals(AerialHellBiomes.VERDIGRIS_AGARIC_FOREST.getLocation()))
    	{
    		/* structure */
    		this.addAllDungeons(event);
    		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_STELLAR_STONE_BRICKS_TOWER_STRUCTURE);
    		
    		/* features */
    		event.getGeneration().getFeatures(GenerationStage.Decoration.TOP_LAYER_MODIFICATION).add(() -> AerialHellFeatures.SLIPPERY_SAND);
    		event.getGeneration().withFeature(GenerationStage.Decoration.LAKES, AerialHellFeatures.AERIAL_HELL_WATER_LAKE);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.WHITE_SOLID_ETHER); 
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.BLUE_SOLID_ETHER);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.GOLDEN_SOLID_ETHER);
   		    
   		    for (int i = 0; i < 5; i++)
   		    {
   		    	event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.STELLAR_STONE_CRYSTAL_BLOB);
   		    	event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.STELLAR_COARSE_FLOOR_ELLIPSOID);	
   		    }
   		 
   		    //rare sky cactus
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SKY_CACTUS_PLAIN);
   		    
   		    //plants
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_GRASS);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_GRASS_BALL);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_HELL_FLOWERS);
   		 
   		    /* ores */
   		    addOres(event);
        }
    	
    	/* Adding features exclusive to the SlipperySandOcean biome */
    	if (event.getName().equals(AerialHellBiomes.SLIPPERY_SAND_OCEAN.getLocation()))
    	{
    		//abandonned structure
    		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE);
    		
    		//slippery sand solid ether
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SLIPPERY_SAND_SOLID_ETHER);
    		
    		for (int i = 0; i < 50; i++)
   		    {
    			//a lot of sky cactus
    			event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SKY_CACTUS_OCEAN);
   		    }
    		//dead bushes
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_DEAD_BUSH);
    	}
    	
    	/* Adding features exclusive to the Aerial Tree Forest and Aerial Copper Pine Forest biomes */
    	if (event.getName().equals(AerialHellBiomes.AERIAL_TREE_FOREST.getLocation()) || event.getName().equals(AerialHellBiomes.COPPER_PINE_FOREST.getLocation()))
    	{
    		//green solid ether
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.GREEN_SOLID_ETHER);
    		
    		//more plants
   		 	event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.BRAMBLES);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_GRASS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_TALL_GRASS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_FERN);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_TALL_FERN);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_BERRY_BUSH_PATCH);
    	}
    	
    	/* Adding features exclusive to Copper Pine Forest biomes*/
    	if (event.getName().equals(AerialHellBiomes.COPPER_PINE_FOREST.getLocation()))
    	{
    		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_COPPER_PINE_COTTAGE_STRUCTURE);
    	}
    	
    	/* Adding features exclusive to the Lapis Robania Savana biome */
    	if (event.getName().equals(AerialHellBiomes.LAPIS_ROBINIA_SAVANA.getLocation()))
    	{   		
    		//more plants
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_HELL_BELLFLOWERS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_GRASS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_TALL_GRASS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_FERN);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.STELLAR_TALL_FERN);
    	}
    	
    	/* Adding features exclusive to the Crystal Plain biome */
    	if (event.getName().equals(AerialHellBiomes.CRYSTAL_PLAINS.getLocation()))
    	{
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.CRYSTALLIZED_FIRE);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.CRYSTAL_BLOB);
    		
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_BERRY_BUSH_PATCH);
    	}
    	/* Adding features exclusive to the Crystal Forest biome */
    	else if (event.getName().equals(AerialHellBiomes.CRYSTAL_FOREST.getLocation()))
    	{
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.CRYSTALLIZED_FIRE);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.CRYSTAL_BLOB);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.GIANT_CRYSTAL_BLOB);
    		
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_BERRY_BUSH_PATCH);
    	}
    	
    	/* Adding features exclusive to the Cortinarius Violaceus Forest biome */
    	if (event.getName().equals(AerialHellBiomes.CORTINARIUS_VIOLACEUS_FOREST.getLocation()))
    	{
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.PURPLISH_STELLAR_GRASS);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.VERDIGRIS_AGARIC_FOREST);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.CORTINARIUS_VIOLACEUS_FOREST);
    	}
    	
    	/* Adding features exclusive to the Verdigris Agaric Forest biome */
    	if (event.getName().equals(AerialHellBiomes.VERDIGRIS_AGARIC_FOREST.getLocation()))
    	{
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.VERDIGRIS_AGARIC_FOREST);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.HUGE_VERDIGRIS_AGARIC);
    	}
    	
    	/* Adding features exclusive to all Shadow Biomes */
    	if (event.getName().equals(AerialHellBiomes.SHADOW_PLAIN.getLocation()) || event.getName().equals(AerialHellBiomes.SHADOW_FOREST.getLocation()))
    	{
    		/* structure */
    		this.addAllDungeons(event);
    		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_STELLAR_STONE_BRICKS_TOWER_STRUCTURE);
    		
    		/* features */
    		event.getGeneration().getFeatures(GenerationStage.Decoration.TOP_LAYER_MODIFICATION).add(() -> AerialHellFeatures.SLIPPERY_SAND);
    		event.getGeneration().withFeature(GenerationStage.Decoration.LAKES, AerialHellFeatures.AERIAL_HELL_WATER_LAKE);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.WHITE_SOLID_ETHER); 
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.BLUE_SOLID_ETHER);
   		    
   		    for (int i = 0; i < 5; i++)
   		    {
   		    	event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.STELLAR_STONE_CRYSTAL_BLOB);
   		    	event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> AerialHellFeatures.STELLAR_COARSE_FLOOR_ELLIPSOID);	
   		    }
   		 
   		    //rare sky cactus
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SKY_CACTUS_PLAIN);
   		    
   		    //plants
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SHADOW_GRASS);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SHADOW_GRASS_BALL);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.AERIAL_HELL_FLOWERS);
   		    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> AerialHellFeatures.SHADOW_BRAMBLES);
   		 
   		    /* ores */
   		    this.addOres(event);
	    	
    		/* Adding features exclusive to Shadow Forest only*/
    		if (event.getName().equals(AerialHellBiomes.SHADOW_FOREST.getLocation()))
        	{
        		//nothing yet
        	}
    	}
    }
    
    private void addOres(BiomeLoadingEvent event)
    {
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.IRON_STELLAR_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.GOLD_STELLAR_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.DIAMOND_STELLAR_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.FLUORITE_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.MAGMATIC_GEL_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.RUBY_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.AZURITE_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.VOLUCITE_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.OBSIDIAN_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.GLAUCOPHANITE_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.STELLAR_DIRT_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.STELLAR_COARSE_DIRT_ORE);
    	event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.STELLAR_CLAY_ORE);
    }
    
    private void addAllDungeons(BiomeLoadingEvent event)
    {
    	event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_MUD_DUNGEON_STRUCTURE);
		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_LUNATIC_TEMPLE_STRUCTURE);
		event.getGeneration().getStructures().add(() -> AerialHellFeatures.CONFIGURED_GOLDEN_NETHER_PRISON_STRUCTURE);
    }
    
    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try
            {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e)
            {
                AerialHell.LOGGER.error("Was unable to check if " + serverWorld.getDimensionKey().getLocation() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD))
            {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(AerialHellStructures.OVERWORLD_ABANDONNED_PORTAL_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.OVERWORLD_ABANDONNED_PORTAL_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.COPPER_PINE_COTTAGE_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.COPPER_PINE_COTTAGE_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
   }
}
