package fr.factionbedrock.aerialhell.Item;

import net.minecraft.item.Item;

import static fr.factionbedrock.aerialhell.Util.ItemHelper.burnTimeMap;

public class BurnableItem extends Item
{
	public BurnableItem(Item.Settings settings, int burnTimeIn)
	{
		super(settings);
		burnTimeMap.put(this, burnTimeIn);
	}
}
