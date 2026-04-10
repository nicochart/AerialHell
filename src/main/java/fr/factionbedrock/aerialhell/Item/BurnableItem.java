package fr.factionbedrock.aerialhell.Item;

import static fr.factionbedrock.aerialhell.Util.ItemHelper.burnTimeMap;

import net.minecraft.world.item.Item;

public class BurnableItem extends Item
{
	public BurnableItem(Item.Properties settings, int burnTimeIn)
	{
		super(settings);
		burnTimeMap.put(this, burnTimeIn);
	}
}
