package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.TileEntity.OscillatorTileEntity;
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
        	AerialHellConfiguredFeatures.registerConfiguredFeaturesAndStructures();
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
        AerialHellFeatures.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        AerialHellEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
	
    private static void registerFuels() //enregistrement des carburants
    {
		OscillatorTileEntity.addItemOscillatingTime(AerialHellBlocksAndItems.FLUORITE.get(), 1200);
		OscillatorTileEntity.addItemOscillatingTime(AerialHellBlocksAndItems.FLUORITE_BLOCK_ITEM.get(), 10800);
		OscillatorTileEntity.addItemOscillatingTime(AerialHellBlocksAndItems.CRYSTAL.get(), 300);
		OscillatorTileEntity.addItemOscillatingTime(AerialHellBlocksAndItems.CRYSTAL_BLOCK_ITEM.get(), 1200);
		FreezerTileEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL.get(), 600);
		FreezerTileEntity.addItemFreezingTime(AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK_ITEM.get(), 5400);
	}

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
    {
        
    }
}
