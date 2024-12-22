package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Client.Packet.ServerPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.BuildContentsEvent;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class AerialHellSetup
{
    public static void init()
    {
        registration();
        additionalRegistration();
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
    }
	
	public static void registration()
    {
    	AerialHellBlocks.load();
        AerialHellItems.load();
        AerialHellFluids.load();
        AerialHellEntities.load();
        //AerialHellBiomes.BIOMES.register(bus);
        AerialHellMobEffects.load();
        AerialHellStructures.load();
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

    //public static void listen(IEventBus bus)
    //{
    //    bus.addListener(AerialHellEntities::entitySpawnPlacements);
    //    bus.addListener(DataPacketPayloads::register);
    //}
}
