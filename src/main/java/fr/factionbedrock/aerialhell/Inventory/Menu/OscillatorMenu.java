package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.BlockEntity.OscillatorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class OscillatorMenu extends AbstractFurnaceScreenHandler
{
	public OscillatorMenu(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellMenuTypes.OSCILLATOR, RecipeTypes.OSCILLATING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public OscillatorMenu(int windowId, PlayerInventory playerInventory, Inventory oscillatingInventory, PropertyDelegate data)
	{
		super(AerialHellMenuTypes.OSCILLATOR, RecipeTypes.OSCILLATING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, oscillatingInventory, data);
	}

	@Override public boolean isFuel(ItemStack stack) {return OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem());}
}
