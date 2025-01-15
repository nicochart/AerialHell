package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;

public class StellarFurnaceMenu extends AbstractFurnaceMenu
{
	public StellarFurnaceMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.STELLAR_FURNACE.get(), RecipeType.SMELTING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public StellarFurnaceMenu(int windowId, Inventory playerInventory, Container furnaceInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.STELLAR_FURNACE.get(), RecipeType.SMELTING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, furnaceInventory, data);
	}
}
