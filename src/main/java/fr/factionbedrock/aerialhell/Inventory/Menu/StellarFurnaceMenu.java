package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class StellarFurnaceMenu extends AbstractFurnaceScreenHandler
{
	public StellarFurnaceMenu(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellMenuTypes.STELLAR_FURNACE, RecipeType.SMELTING, RecipeBookCategory.FURNACE, windowId, playerInventory);
	}

	public StellarFurnaceMenu(int windowId, PlayerInventory playerInventory, Inventory furnaceInventory, PropertyDelegate data)
	{
		super(AerialHellMenuTypes.STELLAR_FURNACE, RecipeType.SMELTING, RecipeBookCategory.FURNACE, windowId, playerInventory, furnaceInventory, data);
	}
}
