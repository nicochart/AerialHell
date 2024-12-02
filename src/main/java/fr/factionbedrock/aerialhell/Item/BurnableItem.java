package fr.factionbedrock.aerialhell.Item;

import net.minecraft.item.Item;

public class BurnableItem extends Item
{
	private int burnTime;
	
	public BurnableItem(Item.Settings settings, int burnTimeIn)
	{
		super(settings);
		this.burnTime = burnTimeIn;
	}
	
	/*@Override TODO : Mixin to add custom fuels
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return recipeType == RecipeType.SMELTING ? burnTime : 0;
    }*/
}
