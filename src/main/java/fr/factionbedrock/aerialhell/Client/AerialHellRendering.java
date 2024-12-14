package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
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
}
