package fr.factionbedrock.aerialhell.Item;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;

public class BurnableItem extends Item
{
	private int burnTime;
	
	public BurnableItem(Properties properties, int burnTimeIn)
	{
		super(properties);
		this.burnTime = burnTimeIn;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues)
    {
        return recipeType == RecipeType.SMELTING ? burnTime : 0;
    }
}
