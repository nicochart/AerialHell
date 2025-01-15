package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FoilItem extends Item
{
    public FoilItem(Item.Properties properties) {super(properties);}
    
    @Override
    public boolean isFoil(ItemStack stack) {return true;}
}
