package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;

import fr.factionbedrock.aerialhell.Inventory.Container.OscillatorContainer;
import fr.factionbedrock.aerialhell.Inventory.Container.StellarFurnaceContainer;
import fr.factionbedrock.aerialhell.Inventory.Container.FreezerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellContainerTypes
{
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, AerialHell.MODID);

	public static final RegistryObject<ContainerType<OscillatorContainer>> OSCILLATOR = CONTAINERS.register("oscillator", () -> new ContainerType<>(OscillatorContainer::new));
	public static final RegistryObject<ContainerType<FreezerContainer>> FREEZER = CONTAINERS.register("freezer", () -> new ContainerType<>(FreezerContainer::new));
	public static final RegistryObject<ContainerType<StellarFurnaceContainer>> STELLAR_FURNACE = CONTAINERS.register("stellar_furnace", () -> new ContainerType<>(StellarFurnaceContainer::new));
}