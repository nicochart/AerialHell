package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;

public class AerialHellMenuTypes
{
	public static final MenuType<OscillatorMenu> OSCILLATOR = register("oscillator", OscillatorMenu::new, FeatureFlags.VANILLA_SET);
	public static final MenuType<FreezerMenu> FREEZER = register("freezer", FreezerMenu::new, FeatureFlags.VANILLA_SET);
	public static final MenuType<StellarFurnaceMenu> STELLAR_FURNACE = register("stellar_furnace", StellarFurnaceMenu::new, FeatureFlags.VANILLA_SET);
	public static final MenuType<ReactorMenu> REACTOR = register("reactor", ReactorMenu::new, FeatureFlags.VANILLA_SET);

	private static <T extends AbstractContainerMenu> MenuType<T> register(String id, MenuType.MenuSupplier<T> factory, FeatureFlagSet featureSet)
	{
		return Registry.register(BuiltInRegistries.MENU, id, new MenuType<>(factory, featureSet));
	}

	public static void load() {}
}