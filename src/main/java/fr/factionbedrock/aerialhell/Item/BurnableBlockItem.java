package fr.factionbedrock.aerialhell.Item;

import static fr.factionbedrock.aerialhell.Util.ItemHelper.burnTimeMap;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BurnableBlockItem extends BlockItem
{
	public BurnableBlockItem(Block blockIn, Item.Properties settings, int burnTimeIn)
	{
		super(blockIn, settings);
		burnTimeMap.put(this, burnTimeIn);
	}
}
