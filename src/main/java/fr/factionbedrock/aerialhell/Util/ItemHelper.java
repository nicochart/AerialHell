package fr.factionbedrock.aerialhell.Util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;

public class ItemHelper
{
    public static int getItemInTagCount(Iterable<ItemStack> stuff, ITag.INamedTag<Item> tag)
    {
        int count = 0;
        for (ItemStack item : stuff)
        {
            if (item.getItem().isIn(tag)) {count++;}
        }
        return count;
    }
}
