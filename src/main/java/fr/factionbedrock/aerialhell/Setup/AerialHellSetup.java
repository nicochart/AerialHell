package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellCarvers;
import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellPOI;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import fr.factionbedrock.aerialhell.TileEntity.VibratorTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.FreezerTileEntity;
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
        	AerialHellStructures.setupStructures();
        	AerialHellFeatures.registerConfiguredFeaturesAndStructures();
        	AerialHellCarvers.registerConfiguredCarvers();
        });
    }
	
	public static void registration()
    {
    	AerialHellBlocksAndItems.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellFluids.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	AerialHellBlocksAndItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPotionEffects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellPOI.POI.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellStructures.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellParticleTypes.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellContainerTypes.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellRecipes.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellTileEntityTypes.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellSoundEvents.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellCarvers.CARVERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
	
    private static void registerFuels() //enregistrement des carburants
    {
		VibratorTileEntity.addItemVibrationTime(AerialHellBlocksAndItems.FLUORITE.get(), 600);
		VibratorTileEntity.addItemVibrationTime(AerialHellBlocksAndItems.FLUORITE_BLOCK_ITEM.get(), 5400);
		VibratorTileEntity.addItemVibrationTime(AerialHellBlocksAndItems.CRYSTAL.get(), 150);
		VibratorTileEntity.addItemVibrationTime(AerialHellBlocksAndItems.CRYSTAL_BLOCK_ITEM.get(), 600);
		FreezerTileEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL.get(), 600);
		FreezerTileEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK_ITEM.get(), 5400);
	}

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
    {
        
    }
}
