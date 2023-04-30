package fr.factionbedrock.aerialhell.Item;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class BurnableItem extends Item
{
	private int burnTime;
	
	public BurnableItem(Properties properties, int burnTimeIn)
	{
		super(properties);
		this.burnTime = burnTimeIn;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return recipeType == RecipeType.SMELTING ? burnTime : 0;
    }
}
