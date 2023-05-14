package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.BlockEntity.OscillatorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;

public class OscillatorMenu extends AbstractFurnaceMenu
{
	public OscillatorMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING, RecipeBookType.FURNACE, windowId, playerInventory);
	}

	public OscillatorMenu(int windowId, Inventory playerInventory, Container oscillatingInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING, RecipeBookType.FURNACE, windowId, playerInventory, oscillatingInventory, data);
	}

	@Override public boolean isFuel(ItemStack stack) {return OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem());}
}
