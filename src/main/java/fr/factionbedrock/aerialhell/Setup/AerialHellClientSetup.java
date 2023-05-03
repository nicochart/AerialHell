package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionRenderInfo;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AerialHellClientSetup
{
    public static void init(final FMLClientSetupEvent event)
    {
    	AerialHellRendering.registerBlockRenderLayers();
    	AerialHellRendering.registerBlockEntityRenderLayers();
    	//AerialHellRendering.registerEntityRenderers(event);
    	AerialHellRendering.registerBlockColors();
    	AerialHellRendering.registerItemColors();
    	AerialHellRendering.registerGuiFactories();
    }
    
    @SubscribeEvent
    public static void registerDimensionRenderInfo(FMLClientSetupEvent event)
    {
        AerialHellDimensionRenderInfo renderInfo = new AerialHellDimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NONE, false, false);
        DimensionRenderInfo.field_239208_a_.put(new ResourceLocation(AerialHell.MODID, "aerial_hell"), renderInfo);
    }
}