package fr.factionbedrock.aerialhell.Item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BurnableBlockItem extends BlockItem
{
	private int burnTime;
	
	public BurnableBlockItem(Block blockIn, Item.Settings settings, int burnTimeIn)
	{
		super(blockIn, settings);
		this.burnTime = burnTimeIn;
	}
	
	/*@Override TODO : Mixin to add custom fuels
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return recipeType == RecipeType.SMELTING ? burnTime : 0;
    }*/
}
