package fr.factionbedrock.aerialhell.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FoilItem extends Item
{
    public FoilItem(Item.Settings settings) {super(settings);}
    
    @Override
    public boolean hasGlint(ItemStack stack) {return true;}
}
