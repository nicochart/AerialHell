package fr.factionbedrock.aerialhell.Client;

import com.google.common.base.Supplier;

import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class AerialHellRendering
{
	public static void registerScreensMenus(RegisterMenuScreensEvent event)
	{
        event.register(AerialHellMenuTypes.OSCILLATOR.get(), OscillatorScreen::new);
        event.register(AerialHellMenuTypes.FREEZER.get(), FreezerScreen::new);
        event.register(AerialHellMenuTypes.STELLAR_FURNACE.get(), StellarFurnaceScreen::new);
        event.register(AerialHellMenuTypes.PROTECTOR.get(), ProtectorScreen::new);
    }
}
