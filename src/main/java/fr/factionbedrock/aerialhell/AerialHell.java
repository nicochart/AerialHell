package fr.factionbedrock.aerialhell;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import fr.factionbedrock.aerialhell.Setup.*;
import fr.factionbedrock.aerialhell.World.GenAerialHellOres;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
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
    
    
    public void biomeModification(final BiomeLoadingEvent event) {
		//biomelist copied from OverworldBiomeProvider.biomes
		List<Comparable<?>> overworldBiomes = ImmutableList.of(Biomes.OCEAN.getLocation(), Biomes.PLAINS.getLocation(), Biomes.DESERT.getLocation(), Biomes.MOUNTAINS.getLocation(), Biomes.FOREST.getLocation(), Biomes.TAIGA.getLocation(), Biomes.SWAMP.getLocation(), Biomes.RIVER.getLocation(), Biomes.FROZEN_OCEAN.getLocation(), Biomes.FROZEN_RIVER.getLocation(), Biomes.SNOWY_TUNDRA.getLocation(), Biomes.SNOWY_MOUNTAINS.getLocation(), Biomes.MUSHROOM_FIELDS.getLocation(), Biomes.MUSHROOM_FIELD_SHORE.getLocation(), Biomes.BEACH.getLocation(), Biomes.DESERT_HILLS.getLocation(), Biomes.WOODED_HILLS.getLocation(), Biomes.TAIGA_HILLS.getLocation(), Biomes.MOUNTAIN_EDGE.getLocation(), Biomes.JUNGLE.getLocation(), Biomes.JUNGLE_HILLS.getLocation(), Biomes.JUNGLE_EDGE.getLocation(), Biomes.DEEP_OCEAN.getLocation(), Biomes.STONE_SHORE.getLocation(), Biomes.SNOWY_BEACH.getLocation(), Biomes.BIRCH_FOREST.getLocation(), Biomes.BIRCH_FOREST_HILLS.getLocation(), Biomes.DARK_FOREST.getLocation(), Biomes.SNOWY_TAIGA.getLocation(), Biomes.SNOWY_TAIGA_HILLS.getLocation(), Biomes.GIANT_TREE_TAIGA.getLocation(), Biomes.GIANT_TREE_TAIGA_HILLS.getLocation(), Biomes.WOODED_MOUNTAINS.getLocation(), Biomes.SAVANNA.getLocation(), Biomes.SAVANNA_PLATEAU.getLocation(), Biomes.BADLANDS.getLocation(), Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU.getLocation(), Biomes.WARM_OCEAN.getLocation(), Biomes.LUKEWARM_OCEAN.getLocation(), Biomes.COLD_OCEAN.getLocation(), Biomes.DEEP_WARM_OCEAN.getLocation(), Biomes.DEEP_LUKEWARM_OCEAN.getLocation(), Biomes.DEEP_COLD_OCEAN.getLocation(), Biomes.DEEP_FROZEN_OCEAN.getLocation(), Biomes.SUNFLOWER_PLAINS.getLocation(), Biomes.DESERT_LAKES.getLocation(), Biomes.GRAVELLY_MOUNTAINS.getLocation(), Biomes.FLOWER_FOREST.getLocation(), Biomes.TAIGA_MOUNTAINS.getLocation(), Biomes.SWAMP_HILLS.getLocation(), Biomes.ICE_SPIKES.getLocation(), Biomes.MODIFIED_JUNGLE.getLocation(), Biomes.MODIFIED_JUNGLE_EDGE.getLocation(), Biomes.TALL_BIRCH_FOREST.getLocation(), Biomes.TALL_BIRCH_HILLS.getLocation(), Biomes.DARK_FOREST_HILLS.getLocation(), Biomes.SNOWY_TAIGA_MOUNTAINS.getLocation(), Biomes.GIANT_SPRUCE_TAIGA.getLocation(), Biomes.GIANT_SPRUCE_TAIGA_HILLS.getLocation(), Biomes.MODIFIED_GRAVELLY_MOUNTAINS.getLocation(), Biomes.SHATTERED_SAVANNA.getLocation(), Biomes.SHATTERED_SAVANNA_PLATEAU.getLocation(), Biomes.ERODED_BADLANDS.getLocation(), Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.getLocation(), Biomes.MODIFIED_BADLANDS_PLATEAU.getLocation());
		boolean isOverworldBiome = overworldBiomes.contains(event.getName());

		/* Adding Portal Ore & Abandonned Portal structure generation to Overworld Biomes */
		if (isOverworldBiome)
		{
			event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GenAerialHellOres.STELLAR_PORTAL_FRAME_ORE);
			event.getGeneration().getStructures().add(() -> AerialHellConfiguredFeatures.CONFIGURED_OVERWORLD_ABANDONNED_PORTAL_STRUCTURE);
		}
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
			tempMap.putIfAbsent(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.COPPER_PINE_COTTAGE_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.COPPER_PINE_COTTAGE_STRUCTURE.get()));
            tempMap.putIfAbsent(AerialHellStructures.SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get(), DimensionStructuresSettings.field_236191_b_.get(AerialHellStructures.SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
   }
}
