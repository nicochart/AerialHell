package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AerialHellItemGroups
{
	public static final ItemGroup AERIAL_HELL_OVERWORLD = new ItemGroup("aerialhell_overworld_itemgroup")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.BEDROCK_ORE_ITEM.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_DIMENSION = new ItemGroup("aerialhell_dimension_itemgroup")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.FLUORITE.get());
        }
    };
}
