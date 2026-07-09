package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedEffectTotemItem extends EffectTotemItem
{
	public EnchantedEffectTotemItem(Item.Properties settings) {super(settings);}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}
}
