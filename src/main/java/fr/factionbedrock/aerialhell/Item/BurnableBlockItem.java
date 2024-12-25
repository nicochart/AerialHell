package fr.factionbedrock.aerialhell.Item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import static fr.factionbedrock.aerialhell.Util.ItemHelper.burnTimeMap;

public class BurnableBlockItem extends BlockItem
{
	public BurnableBlockItem(Block blockIn, Item.Settings settings, int burnTimeIn)
	{
		super(blockIn, settings);
		burnTimeMap.put(this, burnTimeIn);
	}
}
