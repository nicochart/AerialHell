package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Event.Listeners.BlockEventListener;
import fr.factionbedrock.aerialhell.Event.Listeners.LivingEntityEventListener;
import fr.factionbedrock.aerialhell.Event.Listeners.RenderListener;
import fr.factionbedrock.aerialhell.Event.Listeners.ToolsAndArmorEventListener;
import fr.factionbedrock.aerialhell.Recipe.CustomBrewingRecipe;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntityAttributes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

public class AerialHellSetup
{
    public static void init(IEventBus bus)
    {
        registration(bus);
        bus.addListener(AerialHellSetup::additionalRegistration);
        listen(bus);
        eventBusListen(NeoForge.EVENT_BUS);
    }

    public static void additionalRegistration(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
        	AerialHellBlocksAndItems.registerCompostableItems();
        	AerialHellBlocksAndItems.registerPots();
        	AerialHellBlocksAndItems.registerAxeStrippingBlocks();
        });
    }
	
	public static void registration(IEventBus bus)
    {
    	AerialHellBlocksAndItems.BLOCKS.register(bus);
    	AerialHellFluids.FLUIDS.register(bus);
    	AerialHellFluids.FLUID_TYPES.register(bus);
    	AerialHellBlocksAndItems.ITEMS.register(bus);
        AerialHellEntities.ENTITIES.register(bus);
        //AerialHellBiomes.BIOMES.register(bus);
        AerialHellMobEffects.EFFECTS.register(bus);
        AerialHellPOI.POI.register(bus);
        AerialHellStructures.STRUCTURES.register(bus);
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
        bus.addListener(LivingEntityEventListener::onLivingJumpEvent);
        bus.addListener(LivingEntityEventListener::onSleepFinishEvent);
        bus.addListener(ToolsAndArmorEventListener::onProjectileCollideWithEntity);
        bus.addListener(ToolsAndArmorEventListener::onLivingDamageEvent);
        bus.addListener(ToolsAndArmorEventListener::onPlayerHarvest);
        //bus.addListener(ToolsAndArmorEventListener::addReach); TODO
        bus.addListener(CustomBrewingRecipe::addBrewingRecipes);
        bus.addListener(RenderListener::onRenderOverlayPost);
    }

    public static void listen(IEventBus bus)
    {
        bus.addListener(AerialHellEntities::entitySpawnPlacements);
        bus.addListener(AerialHellEntityAttributes::entityAttributes);
    }
}
