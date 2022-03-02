package fr.factionbedrock.aerialhell.Inventory.Container;

import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;

import fr.factionbedrock.aerialhell.TileEntity.FreezerTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class FreezerContainer extends AbstractFurnaceContainer
{
	public FreezerContainer(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellContainerTypes.FREEZER.get(), RecipeTypes.FREEZING, RecipeBookCategory.FURNACE, windowId, playerInventory);
	}

	public FreezerContainer(int windowId, PlayerInventory playerInventory, IInventory freezingInventory, IIntArray furnaceData)
	{
		super(AerialHellContainerTypes.FREEZER.get(), RecipeTypes.FREEZING, RecipeBookCategory.FURNACE, windowId, playerInventory, freezingInventory, furnaceData);
	}

	@Override
	public boolean isFuel(ItemStack stack)
	{
		return FreezerTileEntity.getFreezingMap().containsKey(stack.getItem());
	}
}