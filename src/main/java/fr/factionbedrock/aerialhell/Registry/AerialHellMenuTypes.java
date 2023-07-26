package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;

import fr.factionbedrock.aerialhell.Inventory.Menu.OscillatorMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import fr.factionbedrock.aerialhell.Inventory.Menu.FreezerMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellMenuTypes
{
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, AerialHell.MODID);

	public static final RegistryObject<MenuType<OscillatorMenu>> OSCILLATOR = MENUS.register("oscillator", () -> new MenuType<>(OscillatorMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final RegistryObject<MenuType<FreezerMenu>> FREEZER = MENUS.register("freezer", () -> new MenuType<>(FreezerMenu::new, FeatureFlags.DEFAULT_FLAGS));
	public static final RegistryObject<MenuType<StellarFurnaceMenu>> STELLAR_FURNACE = MENUS.register("stellar_furnace", () -> new MenuType<>(StellarFurnaceMenu::new, FeatureFlags.DEFAULT_FLAGS));
}