package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Client.Packet.ServerPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.AerialHellConfigLoader;
import fr.factionbedrock.aerialhell.Event.Listeners.BlockEventListener;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.BuildContentsEvent;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTrimMaterials;
import fr.factionbedrock.aerialhell.Registry.Worldgen.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AerialHellSetup
{
    public static void init()
    {
        AerialHellConfigLoader.loadAndStoreConfigParams();
        registration();
        additionalRegistration();
        addFeaturesToOverworldBiomes();
        //listen(bus);
        //eventBusListen(NeoForge.EVENT_BUS);

        PayloadTypeRegistry.playS2C().register(AerialHellData.ID, AerialHellData.CODEC);
        ServerPayloadHandler.handleDataOnMain();
    }

    public static void additionalRegistration()
    {
        BuildContentsEvent.buildContents();
        AerialHellItems.registerCompostableItems();
        AerialHellBlocks.registerAxeStrippingBlocks();
        AerialHellBlocks.registerShovelFlattenableBlocks();
        AerialHellBlocks.registerHoeTillableBlocks();
        AerialHellEntities.registerEntitySpawnPlacements();
        AerialHellDimensions.makePortal();
    }
	
	public static void registration()
    {
    	AerialHellBlocks.load();
        AerialHellItems.load();
        AerialHellComponents.load();
        AerialHellPOI.load();
        AerialHellFluids.load();
        AerialHellEntities.load();
        AerialHellTrimMaterials.load();
        //AerialHellBiomes.BIOMES.register(bus);
        AerialHellMobEffects.load();
        AerialHellStructures.load();
        AerialHellStructurePlacement.load();
        AerialHellParticleTypes.load();
        AerialHellMenuTypes.load();
        AerialHellRecipes.load();
        AerialHellRecipes.RecipeTypes.load();
        AerialHellBlockEntities.load();
        AerialHellSoundEvents.load();
        AerialHellCreativeModeTabs.load();

        AerialHellFeatures.load();
        //AerialHellConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        //AerialHellPlacedFeatures.PLACED_FEATURES.register(bus);
    }

    public static void addFeaturesToOverworldBiomes()
    {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, AerialHellPlacedFeatures.STELLAR_PORTAL_FRAME_ORE);
    }

    //public static void listen(IEventBus bus)
    //{
    //    bus.addListener(DataPacketPayloads::register);
    //}
}
