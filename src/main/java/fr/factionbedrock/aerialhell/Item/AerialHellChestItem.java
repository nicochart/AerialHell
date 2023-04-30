package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellCreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class AerialHellChestItem extends Item
{
	//TODO : encore utile ?
	public AerialHellChestItem()
	{
		super(new Properties().tab(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS));
	}
	
	public AerialHellChestItem(CreativeModeTab group)
	{
		super(new Properties().tab(group));
	}

    public AerialHellChestItem(Rarity rarity)
    {
        super(new Properties().tab(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS).rarity(rarity));
    }
}