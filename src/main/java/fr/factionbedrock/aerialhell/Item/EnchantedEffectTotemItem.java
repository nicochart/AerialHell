package fr.factionbedrock.aerialhell.Item;

import net.minecraft.item.ItemStack;

public class EnchantedEffectTotemItem extends EffectTotemItem
{
	public EnchantedEffectTotemItem(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
