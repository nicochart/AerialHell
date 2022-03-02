package fr.factionbedrock.aerialhell.Setup;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config
{
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent)
    {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent)
    {
    	
    }
}
