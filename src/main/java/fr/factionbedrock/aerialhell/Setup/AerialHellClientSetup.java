package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Client.Packet.ClientPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellRenderPipelines;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Event.Listeners.RenderListener;
import fr.factionbedrock.aerialhell.Registry.AerialHellWoodTypes;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;

public class AerialHellClientSetup
{
    public static void init()
    {
        ClientPayloadHandler.handleDataOnMain();

        AerialHellWoodTypes.registerWoodTypes();
        //modEventBus.addListener(BlocksAndItemsColorHandler::handleBlockColors);
        //modEventBus.addListener(BlocksAndItemsColorHandler::handleItemColors);
        RenderRegistrationListener.registerBlockRenderLayers();
        RenderRegistrationListener.registerRenderers();
        RenderRegistrationListener.registerLayerDefinitions();
        RenderRegistrationListener.registerBlockStateModels();
        RenderRegistrationListener.registerSpriteSources();
        AerialHellRenderPipelines.registerReloadListener();
        if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT)
        {
            RenderRegistrationListener.registerShiftingBakedModels();
        }
        AerialHellRendering.registerFluidsRender();
        AerialHellParticleTypes.registerParticleFactories();
        AerialHellClientSetup.registerDimensionRenderInfo();
        AerialHellRendering.registerScreensMenus();
        //NeoForge.EVENT_BUS.addListener(RenderListener::onRenderOverlayPost);
        LevelExtractionEvents.AFTER_BLOCK_OUTLINE_EXTRACTION.register(RenderListener::onBlockOutlineExtraction);

        BlocksAndItemsColorHandler.handleBlockColors();
        BlocksAndItemsColorHandler.handleColorResolvers();
        //BlocksAndItemsColorHandler.handleItemColors(); now done in ItemTintMixin
    }

    public static void registerDimensionRenderInfo()
    {
        //TODO remove clouds
        //DimensionRenderingRegistry.registerCloudRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, new AerialHellDimensionSpecialEffects.AerialHellCloudRenderer());
    }
}