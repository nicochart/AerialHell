package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.item.ItemStack;

public class EnchantedEffectTotemItem extends EffectTotemItem
{
	public EnchantedEffectTotemItem(Properties properties)
	{
		super(properties);
	}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}
}
