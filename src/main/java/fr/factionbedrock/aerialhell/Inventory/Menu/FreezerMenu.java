package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;

import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class FreezerMenu extends AbstractFurnaceScreenHandler
{
	public FreezerMenu(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellMenuTypes.FREEZER, RecipeTypes.FREEZING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public FreezerMenu(int windowId, PlayerInventory playerInventory, Inventory freezingInventory, PropertyDelegate data)
	{
		super(AerialHellMenuTypes.FREEZER, RecipeTypes.FREEZING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, freezingInventory, data);
	}

	@Override public boolean isFuel(ItemStack stack) {return FreezerBlockEntity.getFreezingMap().containsKey(stack.getItem());}
}