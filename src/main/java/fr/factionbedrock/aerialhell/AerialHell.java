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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.server.level.ServerLevel;
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
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AerialHellSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AerialHellClientSetup::init);
    }
}
