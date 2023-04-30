package fr.factionbedrock.aerialhell.Util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemHelper
{
    public static int getItemInTagCount(Iterable<ItemStack> stuff, TagKey<Item> tag)
    {
        int count = 0;
        for (ItemStack item : stuff)
        {
            if (item.is(tag)) {count++;}
        }
        return count;
    }
}
