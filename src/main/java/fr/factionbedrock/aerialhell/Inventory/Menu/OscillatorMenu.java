package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;

public class OscillatorMenu extends AbstractFurnaceMenu
{
	public OscillatorMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.OSCILLATOR, RecipeTypes.OSCILLATING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public OscillatorMenu(int windowId, Inventory playerInventory, Container oscillatingInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.OSCILLATOR, RecipeTypes.OSCILLATING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, oscillatingInventory, data);
	}

	@Override public boolean isFuel(ItemStack stack) {return ItemHelper.getOscillatingMap().containsKey(stack.getItem());}
}