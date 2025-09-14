package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.DataPacketPayloads;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.AerialHellConfigLoader;
import fr.factionbedrock.aerialhell.Event.Listeners.BlockEventListener;
import fr.factionbedrock.aerialhell.Event.Listeners.LivingEntityEventListener;
import fr.factionbedrock.aerialhell.Event.Listeners.ToolsAndArmorEventListener;
import fr.factionbedrock.aerialhell.Recipe.CustomBrewingRecipe;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntityAttributes;
import fr.factionbedrock.aerialhell.Registry.TrimMaterials.AerialHellTrimMaterials;
import fr.factionbedrock.aerialhell.Registry.Worldgen.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class AerialHellSetup
{
    public static void init(IEventBus bus)
    {
        AerialHellConfigLoader.loadAndStoreConfigParams();
        registration(bus);
        bus.addListener(AerialHellSetup::additionalRegistration);
        listen(bus);
        eventBusListen(NeoForge.EVENT_BUS);
    }

    public static void additionalRegistration(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
        	AerialHellItems.registerCompostableItems();
        	AerialHellBlocks.registerPots();
        	AerialHellBlocks.registerAxeStrippingBlocks();
        });
    }
	
	public static void registration(IEventBus bus)
    {
    	AerialHellBlocks.BLOCKS.register(bus);
    	AerialHellFluids.FLUIDS.register(bus);
    	AerialHellFluids.FLUID_TYPES.register(bus);
    	AerialHellItems.ITEMS.register(bus);
        AerialHellEntities.ENTITIES.register(bus);
        //AerialHellBiomes.BIOMES.register(bus);
        AerialHellMobEffects.EFFECTS.register(bus);
        AerialHellTrimMaterials.TRIM_MATERIALS.register(bus);
        AerialHellPOI.POI.register(bus);
        AerialHellStructures.STRUCTURES.register(bus);
        AerialHellStructurePlacements.STRUCTURE_PLACEMENTS.register(bus);
        AerialHellParticleTypes.PARTICLES.register(bus);
        AerialHellMenuTypes.MENUS.register(bus);
        AerialHellRecipes.RECIPE_SERIALIZERS.register(bus);
        AerialHellRecipes.RecipeTypes.RECIPE_TYPES.register(bus);
        AerialHellBlockEntities.BLOCK_ENTITY_TYPES.register(bus);
        AerialHellSoundEvents.SOUNDS.register(bus);
        AerialHellCreativeModeTabs.TABS.register(bus);

        AerialHellFeatures.FEATURES.register(bus);
        //AerialHellConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        //AerialHellPlacedFeatures.PLACED_FEATURES.register(bus);
    }

    public static void eventBusListen(IEventBus bus)
    {
        bus.addListener(BlockEventListener::onNeighborNotifyEvent);
        bus.addListener(BlockEventListener::onEntityPlaceEvent);
        bus.addListener(BlockEventListener::onOverlay);
        bus.addListener(LivingEntityEventListener::onSleepFinishEvent);
        bus.addListener(ToolsAndArmorEventListener::onProjectileCollideWithEntity);
        bus.addListener(ToolsAndArmorEventListener::onLivingDamageEvent);
        bus.addListener(ToolsAndArmorEventListener::onPlayerHarvest);
        //bus.addListener(ToolsAndArmorEventListener::addReach); TODO
        bus.addListener(CustomBrewingRecipe::addBrewingRecipes);
    }

    public static void listen(IEventBus bus)
    {
        bus.addListener(AerialHellEntities::entitySpawnPlacements);
        bus.addListener(AerialHellEntityAttributes::entityAttributes);
        bus.addListener(DataPacketPayloads::register);
    }
}
