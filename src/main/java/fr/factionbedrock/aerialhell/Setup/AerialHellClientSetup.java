package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AerialHellClientSetup
{
    public static void init(final FMLClientSetupEvent event)
    {
    	AerialHellRendering.registerBlockRenderLayers();
    	AerialHellRendering.registerTileEntityRenderLayers();
    	AerialHellRendering.registerEntityRenderers(event);
    	AerialHellRendering.registerGuiFactories();
    }
}