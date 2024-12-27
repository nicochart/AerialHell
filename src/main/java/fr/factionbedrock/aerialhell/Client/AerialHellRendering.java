package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AerialHellRendering
{
	public static void registerScreensMenus()
	{
        HandledScreens.register(AerialHellMenuTypes.OSCILLATOR, OscillatorScreen::new);
        HandledScreens.register(AerialHellMenuTypes.FREEZER, FreezerScreen::new);
        HandledScreens.register(AerialHellMenuTypes.STELLAR_FURNACE, StellarFurnaceScreen::new);
        HandledScreens.register(AerialHellMenuTypes.REACTOR, ReactorScreen::new);
    }

    public static void registerFluidsRender()
    {
        FluidRenderHandlerRegistry.INSTANCE.register(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING, new SimpleFluidRenderHandler(
                AerialHell.id("block/liquid_of_the_gods_still"),
                AerialHell.id("block/liquid_of_the_gods_flow"),
                AerialHell.id("block/liquid_of_the_gods_overlay")
        ));
        //TODO : fog?
    }
}
