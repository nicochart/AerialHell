package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;

import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.ProtectorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellMenuTypes
{
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, AerialHell.MODID);

	public static final DeferredHolder<MenuType<?>, MenuType<OscillatorMenu>> OSCILLATOR = MENUS.register("oscillator", () -> new MenuType<>(OscillatorMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final DeferredHolder<MenuType<?>, MenuType<FreezerMenu>> FREEZER = MENUS.register("freezer", () -> new MenuType<>(FreezerMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final DeferredHolder<MenuType<?>, MenuType<StellarFurnaceMenu>> STELLAR_FURNACE = MENUS.register("stellar_furnace", () -> new MenuType<>(StellarFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final DeferredHolder<MenuType<?>, MenuType<ProtectorMenu>> PROTECTOR = MENUS.register("protector", () -> new MenuType<>(ProtectorMenu::new, FeatureFlags.DEFAULT_FLAGS));
}