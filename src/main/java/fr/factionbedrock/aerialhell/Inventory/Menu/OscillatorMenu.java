package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class OscillatorMenu extends AbstractFurnaceMenu
{
	private final RecipePropertySet oscillatingInputs;

	public OscillatorMenu(int windowId, Inventory playerInventory)
	{
		super(AerialHellMenuTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING.get(), RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory);
		this.oscillatingInputs = this.level.recipeAccess().propertySet(AerialHellRecipes.PropertySet.OSCILLATOR_INPUT);
	}

	public OscillatorMenu(int windowId, Inventory playerInventory, Container oscillatingInventory, ContainerData data)
	{
		super(AerialHellMenuTypes.OSCILLATOR.get(), RecipeTypes.OSCILLATING.get(), RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, windowId, playerInventory, oscillatingInventory, data);
		this.oscillatingInputs = this.level.recipeAccess().propertySet(AerialHellRecipes.PropertySet.OSCILLATOR_INPUT);
	}

	@Override public boolean isFuel(ItemStack stack) {return ItemHelper.getOscillatingMap().containsKey(stack.getItem());}

	@Override protected boolean canSmelt(ItemStack itemStack) {return this.oscillatingInputs.test(itemStack);}
}
