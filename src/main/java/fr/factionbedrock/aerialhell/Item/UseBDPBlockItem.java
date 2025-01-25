package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class UseBDPBlockItem extends BlockItem
{
    //uses block lang translation
    //Temporary solution : I am lazy and didn't want to add .useBlockDescriptionPrefix() to all 395 block item properties...
    public UseBDPBlockItem(Block block, Properties properties)
    {
        super(block, properties.useBlockDescriptionPrefix());
    }
}
