package fr.factionbedrock.aerialhell.Item;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

public class BurnableItem extends Item
{
	private int burnTime;
	
	public BurnableItem(Properties properties, int burnTimeIn)
	{
		super(properties);
		this.burnTime = burnTimeIn;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType)
    {
        return recipeType == IRecipeType.SMELTING ? burnTime : 0;
    }
}
