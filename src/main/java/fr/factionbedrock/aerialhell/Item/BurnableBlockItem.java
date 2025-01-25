package fr.factionbedrock.aerialhell.Item;

import javax.annotation.Nullable;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.FuelValues;

public class BurnableBlockItem extends UseBDPBlockItem
{
	private int burnTime;
	
	public BurnableBlockItem(Block blockIn, Properties builder, int burnTimeIn)
	{
		super(blockIn, builder);
		this.burnTime = burnTimeIn;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues)
    {
        return recipeType == RecipeType.SMELTING ? burnTime : 0;
    }
}
