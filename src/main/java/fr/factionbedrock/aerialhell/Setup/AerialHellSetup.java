package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.BlockEntity.OscillatorBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellMotive;
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
        	registerFuels();
        	
        	AerialHellBlocksAndItems.registerCompostableItems();
        	AerialHellEntities.entitySpawnPlacements();
        	AerialHellBlocksAndItems.registerPots();
        	AerialHellBlocksAndItems.registerAxeStrippingBlocks();
        });
    }
	
	public static void registration()
    {
    	AerialHellBlocksAndItems.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellFluids.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellBlocksAndItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellMobEffects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPOI.POI.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellStructures.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellParticleTypes.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellMenuTypes.MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellRecipes.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellBlockEntities.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellSoundEvents.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        AerialHellFeatures.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());

        AerialHellEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellMotive.PAINTING_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
	
    private static void registerFuels() //enregistrement des carburants
    {
		OscillatorBlockEntity.addItemOscillatingTime(AerialHellBlocksAndItems.FLUORITE.get(), 1200);
		OscillatorBlockEntity.addItemOscillatingTime(AerialHellBlocksAndItems.FLUORITE_BLOCK_ITEM.get(), 10800);
		OscillatorBlockEntity.addItemOscillatingTime(AerialHellBlocksAndItems.CRYSTAL.get(), 300);
		OscillatorBlockEntity.addItemOscillatingTime(AerialHellBlocksAndItems.CRYSTAL_BLOCK_ITEM.get(), 1200);
		FreezerBlockEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL.get(), 600);
		FreezerBlockEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK_ITEM.get(), 5400);
	}

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
    {
        
    }
}
