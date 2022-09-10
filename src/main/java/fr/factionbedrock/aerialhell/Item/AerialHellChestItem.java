package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public class AerialHellChestItem extends Item
{
	public AerialHellChestItem()
	{
		super(new Properties().group(AerialHellItemGroups.AERIAL_HELL_BLOCKS));
	}
	
	public AerialHellChestItem(ItemGroup group)
	{
		super(new Properties().group(group));
	}

    public AerialHellChestItem(Rarity rarity)
    {
        super(new Properties().group(AerialHellItemGroups.AERIAL_HELL_BLOCKS).rarity(rarity));
    }
}