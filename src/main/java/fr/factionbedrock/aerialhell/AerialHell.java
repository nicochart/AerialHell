package fr.factionbedrock.aerialhell;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;

import fr.factionbedrock.aerialhell.Setup.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AerialHell.MODID) 
public class AerialHell
{
	public static final String MODID = "aerialhell";
	public static final String NAME = "Aerial Hell";
	public static final String VERSION = "1.0";
	
	public static Logger LOGGER = LogUtils.getLogger();

    public AerialHell(IEventBus modEventBus, ModContainer modContainer, Dist dist)
    {
    	AerialHellSetup.init(modEventBus);

        // Register the setup method for modloading

		if (dist == Dist.CLIENT) {AerialHellClientSetup.init(modEventBus);}
    }
}
