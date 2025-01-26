package fr.factionbedrock.aerialhell.Setup;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.AerialHellRendering;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.FluidRenderHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSkyRenderer;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSpecialEffects;
import fr.factionbedrock.aerialhell.Event.Listeners.RenderListener;
import fr.factionbedrock.aerialhell.Registry.AerialHellWoodTypes;
import fr.factionbedrock.aerialhell.Registry.CreativeModeTabs.BuildContentsEvent;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.common.NeoForge;

public class AerialHellClientSetup
{
    public static void init(IEventBus modEventBus)
    {
        modEventBus.addListener(AerialHellWoodTypes::registerWoodTypes);
        modEventBus.addListener(AerialHellWoodTypes::addWoodTypesToSheets);
        modEventBus.addListener(BlocksAndItemsColorHandler::handleBlockColors);
        modEventBus.addListener(BlocksAndItemsColorHandler::handleItemColors);
        modEventBus.addListener(FluidRenderHandler::handleFluidRender);
        modEventBus.addListener(RenderRegistrationListener::onRegisterRenderers);
        modEventBus.addListener(RenderRegistrationListener::onRegisterLayerDefinitions);
        modEventBus.addListener(RenderRegistrationListener::onModelBake);
        modEventBus.addListener(AerialHellParticleTypes::registerParticleFactories);
        modEventBus.addListener(AerialHellClientSetup::registerDimensionRenderInfo);
        modEventBus.addListener(BuildContentsEvent::buildContents);
        modEventBus.addListener(AerialHellRendering::registerScreensMenus);
        NeoForge.EVENT_BUS.addListener(RenderListener::onRenderOverlayPost);
    }


    public static void registerDimensionRenderInfo(RegisterDimensionSpecialEffectsEvent event)
    {
        AerialHellDimensionSpecialEffects renderInfo = new AerialHellDimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, false);
        event.register(ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "aerial_hell"), renderInfo);
    }
}