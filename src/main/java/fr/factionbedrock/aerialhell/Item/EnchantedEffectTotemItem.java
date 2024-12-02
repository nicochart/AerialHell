package fr.factionbedrock.aerialhell.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedEffectTotemItem extends EffectTotemItem
{
	public EnchantedEffectTotemItem(Item.Settings settings) {super(settings);}
	
	@Override public boolean hasGlint(ItemStack stack) {return true;}
}
