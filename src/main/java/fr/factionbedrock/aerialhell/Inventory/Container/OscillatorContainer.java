package fr.factionbedrock.aerialhell.Inventory.Container;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.TileEntity.OscillatorTileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class OscillatorContainer extends AbstractFurnaceContainer
{
	public OscillatorContainer(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellContainerTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING, RecipeBookCategory.FURNACE, windowId, playerInventory);
	}

	public OscillatorContainer(int windowId, PlayerInventory playerInventory, IInventory oscillatingInventory, IIntArray furnaceData)
	{
		super(AerialHellContainerTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING, RecipeBookCategory.FURNACE, windowId, playerInventory, oscillatingInventory, furnaceData);
	}

	@Override
	public boolean isFuel(ItemStack stack)
	{
		return OscillatorTileEntity.getOscillatingMap().containsKey(stack.getItem());
	}
}
