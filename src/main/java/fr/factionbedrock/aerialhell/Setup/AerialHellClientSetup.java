package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSpecialEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellWoodTypes;
import net.minecraft.client.renderer.DimensionSpecialEffects;
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
    	AerialHellRendering.registerScreensMenus();

        AerialHellWoodTypes.registerWoodTypes(event);
    }
    
    @SubscribeEvent
    public static void registerDimensionRenderInfo(FMLClientSetupEvent event)
    {
        AerialHellDimensionSpecialEffects renderInfo = new AerialHellDimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, false);
        DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(AerialHell.MODID, "aerial_hell"), renderInfo);
    }
}