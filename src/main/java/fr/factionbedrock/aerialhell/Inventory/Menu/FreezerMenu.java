package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;

import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class FreezerMenu extends AbstractFurnaceMenu
{
	public FreezerMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.FREEZER.get(), RecipeTypes.FREEZING.get(), RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public FreezerMenu(int windowId, Inventory playerInventory, Container freezingInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.FREEZER.get(), RecipeTypes.FREEZING.get(), RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, freezingInventory, data);
	}

	@Override public boolean isFuel(ItemStack stack) {return FreezerBlockEntity.getFreezingMap().containsKey(stack.getItem());}
}