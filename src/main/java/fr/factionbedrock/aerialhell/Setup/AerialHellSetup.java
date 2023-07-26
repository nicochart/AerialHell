package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellPaintingVariants;
import fr.factionbedrock.aerialhell.Registry.Worldgen.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AerialHellSetup
{
	public static void init(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
        	AerialHellBlocksAndItems.registerCompostableItems();
        	AerialHellEntities.entitySpawnPlacements();
        	AerialHellBlocksAndItems.registerPots();
        	AerialHellBlocksAndItems.registerAxeStrippingBlocks();
            AerialHellWoodTypes.addWoodTypesToSheets();
        });
    }
	
	public static void registration()
    {
    	AerialHellBlocksAndItems.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellFluids.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellFluids.FLUID_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellBlocksAndItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellMobEffects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPOI.POI.register(FMLJavaModLoadingContext.get().getModEventBus());
        //AerialHellStructures.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellParticleTypes.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellMenuTypes.MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellRecipes.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellRecipes.RecipeTypes.RECIPE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellBlockEntities.BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellSoundEvents.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellCreativeModeTabs.TABS.register(FMLJavaModLoadingContext.get().getModEventBus());

        AerialHellFeatures.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //AerialHellConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //AerialHellPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());

        AerialHellEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPaintingVariants.PAINTING_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
    {
        
    }
}
