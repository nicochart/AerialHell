package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Client.Packet.ClientPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSpecialEffects;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellWoodTypes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;

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
        //if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT) condition doesn't work properly because config isn't loaded yet
        //{
            RenderRegistrationListener.registerShiftingBakedModels();
        //}
        RenderRegistrationListener.registerShiftingBakedModels();
        AerialHellRendering.registerFluidsRender();
        AerialHellParticleTypes.registerParticleFactories();
        AerialHellClientSetup.registerDimensionRenderInfo();
        AerialHellRendering.registerScreensMenus();
        //NeoForge.EVENT_BUS.addListener(RenderListener::onRenderOverlayPost);

        BlocksAndItemsColorHandler.handleBlockColors();
        BlocksAndItemsColorHandler.handleItemColors();

        AerialHellItems.registerItemProperties();
    }

    public static void registerDimensionRenderInfo()
    {
        AerialHellDimensionSpecialEffects renderInfo = new AerialHellDimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, false);
        DimensionRenderingRegistry.registerSkyRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, renderInfo);
        DimensionRenderingRegistry.registerCloudRenderer(AerialHellDimensions.AERIAL_HELL_DIMENSION, new AerialHellDimensionSpecialEffects.AerialHellCloudRenderer());
    }
}