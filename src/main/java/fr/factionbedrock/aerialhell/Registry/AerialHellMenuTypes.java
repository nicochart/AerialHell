package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class AerialHellMenuTypes
{
	public static final ScreenHandlerType<OscillatorMenu> OSCILLATOR = register("oscillator", OscillatorMenu::new, FeatureFlags.VANILLA_FEATURES);
	public static final ScreenHandlerType<FreezerMenu> FREEZER = register("freezer", FreezerMenu::new, FeatureFlags.VANILLA_FEATURES);
	public static final ScreenHandlerType<StellarFurnaceMenu> STELLAR_FURNACE = register("stellar_furnace", StellarFurnaceMenu::new, FeatureFlags.VANILLA_FEATURES);
	public static final ScreenHandlerType<ReactorMenu> REACTOR = register("reactor", ReactorMenu::new, FeatureFlags.VANILLA_FEATURES);

	private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory, FeatureSet featureSet)
	{
		return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, featureSet));
	}

	public static void load() {}
}