package fr.factionbedrock.aerialhell.Inventory.Container;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.TileEntity.VibratorTileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class VibratorContainer extends AbstractFurnaceContainer
{
	public VibratorContainer(int windowId, PlayerInventory playerInventory)
	{
		super(AerialHellContainerTypes.VIBRATOR.get(), RecipeTypes.VIBRATION, RecipeBookCategory.FURNACE, windowId, playerInventory);
	}

	public VibratorContainer(int windowId, PlayerInventory playerInventory, IInventory vibrationInventory, IIntArray furnaceData)
	{
		super(AerialHellContainerTypes.VIBRATOR.get(), RecipeTypes.VIBRATION, RecipeBookCategory.FURNACE, windowId, playerInventory, vibrationInventory, furnaceData);
	}

	@Override
	public boolean isFuel(ItemStack stack)
	{
		return VibratorTileEntity.getVibrationMap().containsKey(stack.getItem());
	}
}
