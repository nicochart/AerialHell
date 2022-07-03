package fr.factionbedrock.aerialhell.Item;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

public class BurnableBlockItem extends BlockItem
{
	private int burnTime;
	
	public BurnableBlockItem(Block blockIn, Properties builder, int burnTimeIn)
	{
		super(blockIn, builder);
		this.burnTime = burnTimeIn;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType)
    {
        return recipeType == IRecipeType.SMELTING ? burnTime : 0;
    }
}
