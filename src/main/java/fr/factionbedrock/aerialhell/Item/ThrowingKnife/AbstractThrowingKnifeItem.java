package fr.factionbedrock.aerialhell.Item.ThrowingKnife;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class AbstractThrowingKnifeItem extends Item
{
	public AbstractThrowingKnifeItem(Properties properties, ItemGroup group)
	{
		super(properties.maxStackSize(16).group(group));
	}
	
	public AbstractThrowingKnifeItem(Properties properties) //default group
	{
		super(properties.maxStackSize(16).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	}
}