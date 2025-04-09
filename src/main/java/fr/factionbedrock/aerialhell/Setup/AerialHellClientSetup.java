package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Client.Packet.ClientPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSpecialEffects;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellWoodTypes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.DimensionEffects;

public class AerialHellClientSetup
{
    public static void init()
    {
        ClientPayloadHandler.handleDataOnMain();

        AerialHellWoodTypes.registerWoodTypes();
        AerialHellWoodTypes.addWoodTypesToSheets();
        //modEventBus.addListener(BlocksAndItemsColorHandler::handleBlockColors);
        //modEventBus.addListener(BlocksAndItemsColorHandler::handleItemColors);
        RenderRegistrationListener.registerBlockRenderLayers();
        RenderRegistrationListener.registerRenderers();
        RenderRegistrationListener.registerLayerDefinitions();
        if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT)
        {
            RenderRegistrationListener.registerShiftingBakedModels();
        }
        AerialHellRendering.registerFluidsRender();
        AerialHellParticleTypes.registerParticleFactories();
        AerialHellClientSetup.registerDimensionRenderInfo();
        AerialHellRendering.registerScreensMenus();
        //NeoForge.EVENT_BUS.addListener(RenderListener::onRenderOverlayPost);

        BlocksAndItemsColorHandler.handleBlockColors();
        //BlocksAndItemsColorHandler.handleItemColors(); now done in ItemTintMixin
    }

    public static void registerDimensionRenderInfo()
    {
        //Sky render is done in RenderSkyMixin because can't get Fabric's DimensionRenderingRegistry.SkyRenderer to work
        //AerialHellDimensionSpecialEffects renderInfo = new AerialHellDimensionSpecialEffects(Float.NaN, true, DimensionEffects.SkyType.NORMAL, false, false);
        //DimensionRenderingRegistry.registerSkyRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, renderInfo);
        DimensionRenderingRegistry.registerCloudRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, new AerialHellDimensionSpecialEffects.AerialHellCloudRenderer());
    }
}