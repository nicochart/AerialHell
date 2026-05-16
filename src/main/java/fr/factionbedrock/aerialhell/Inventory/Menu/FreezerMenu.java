package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;

public class FreezerMenu extends AbstractFurnaceMenu
{
	private final RecipePropertySet freezingInputs;

	public FreezerMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.FREEZER, RecipeTypes.FREEZING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
		this.freezingInputs = this.level.recipeAccess().propertySet(AerialHellRecipes.PropertySet.FREEZER_INPUT);
	}

	public FreezerMenu(int windowId, Inventory playerInventory, Container freezingInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.FREEZER, RecipeTypes.FREEZING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, freezingInventory, data);
		this.freezingInputs = this.level.recipeAccess().propertySet(AerialHellRecipes.PropertySet.FREEZER_INPUT);
	}

	@Override public boolean isFuel(ItemStack stack) {return FreezerBlockEntity.getFreezingMap().containsKey(stack.getItem());}

	@Override protected boolean canSmelt(ItemStack itemStack) {return this.freezingInputs.test(itemStack);}
}