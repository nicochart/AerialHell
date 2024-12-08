package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Client.Packet.ServerPayloadHandler;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class AerialHellSetup
{
    public static void init()
    {
        registration();
        //addListener(AerialHellSetup::additionalRegistration);
        //listen(bus);
        //eventBusListen(NeoForge.EVENT_BUS);

        PayloadTypeRegistry.playS2C().register(AerialHellData.ID, AerialHellData.CODEC);
        ServerPayloadHandler.handleDataOnMain();
    }

    //public static void additionalRegistration(final FMLCommonSetupEvent event)
    //{
    //    event.enqueueWork(() ->
    //    {
    //    	AerialHellBlocksAndItems.registerCompostableItems();
    //    	AerialHellBlocksAndItems.registerPots();
    //    	AerialHellBlocksAndItems.registerAxeStrippingBlocks();
    //    });
    //}
	
	public static void registration()
    {
    	AerialHellBlocks.load();
        AerialHellItems.load();
        //AerialHellFluids.FLUIDS.register(bus);
        //AerialHellFluids.FLUID_TYPES.register(bus);
        AerialHellEntities.load();
        //AerialHellBiomes.BIOMES.register(bus);
        AerialHellMobEffects.load();
        //AerialHellPOI.POI.register(bus);
        //AerialHellStructures.STRUCTURES.register(bus);
        //AerialHellParticleTypes.PARTICLES.register(bus);
        AerialHellMenuTypes.load();
        //AerialHellRecipes.RECIPE_SERIALIZERS.register(bus);
        //AerialHellRecipes.RecipeTypes.RECIPE_TYPES.register(bus);
        AerialHellBlockEntities.load();
        AerialHellSoundEvents.load();
        //AerialHellCreativeModeTabs.TABS.register(bus);

        //AerialHellFeatures.FEATURES.register(bus);
        //AerialHellConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        //AerialHellPlacedFeatures.PLACED_FEATURES.register(bus);
    }

    //public static void eventBusListen(IEventBus bus)
    //{
    //    bus.addListener(BlockEventListener::onNeighborNotifyEvent);
    //    bus.addListener(BlockEventListener::onEntityPlaceEvent);
    //    bus.addListener(BlockEventListener::onOverlay);
    //    bus.addListener(LivingEntityEventListener::onLivingJumpEvent);
    //    bus.addListener(LivingEntityEventListener::onSleepFinishEvent);
    //    bus.addListener(ToolsAndArmorEventListener::onProjectileCollideWithEntity);
    //    bus.addListener(ToolsAndArmorEventListener::onLivingDamageEvent);
    //    bus.addListener(ToolsAndArmorEventListener::onPlayerHarvest);
    //    //bus.addListener(ToolsAndArmorEventListener::addReach); TODO
    //    bus.addListener(CustomBrewingRecipe::addBrewingRecipes);
    //}

    //public static void listen(IEventBus bus)
    //{
    //    bus.addListener(AerialHellEntities::entitySpawnPlacements);
    //    bus.addListener(DataPacketPayloads::register);
    //}
}
