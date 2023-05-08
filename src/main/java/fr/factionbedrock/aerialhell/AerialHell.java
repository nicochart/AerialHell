package fr.factionbedrock.aerialhell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.factionbedrock.aerialhell.Setup.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
