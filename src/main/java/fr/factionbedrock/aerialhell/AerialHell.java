package fr.factionbedrock.aerialhell;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import fr.factionbedrock.aerialhell.Setup.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AerialHell implements ModInitializer, ClientModInitializer
{
	public static final String MODID = "aerialhell";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override public void onInitialize()
	{
		AerialHellSetup.init();
	}

	@Override public void onInitializeClient()
	{
		AerialHellClientSetup.init();
	}

	public static Identifier id(String path) {return Identifier.of(MODID, path);}
}