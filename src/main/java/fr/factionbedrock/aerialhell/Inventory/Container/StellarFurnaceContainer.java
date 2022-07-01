package fr.factionbedrock.aerialhell.Inventory.Container;

import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class StellarFurnaceContainer extends AbstractFurnaceContainer
{
	public StellarFurnaceContainer(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellContainerTypes.STELLAR_FURNACE.get(), IRecipeType.SMELTING, RecipeBookCategory.FURNACE, windowId, playerInventory);
	}

	public StellarFurnaceContainer(int windowId, PlayerInventory playerInventory, IInventory furnaceInventory, IIntArray furnaceData)
	{
		super(AerialHellContainerTypes.STELLAR_FURNACE.get(), IRecipeType.SMELTING, RecipeBookCategory.FURNACE, windowId, playerInventory, furnaceInventory, furnaceData);
	}
}
