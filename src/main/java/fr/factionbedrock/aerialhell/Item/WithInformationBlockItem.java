package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class WithInformationBlockItem extends BlockItem
{
	public WithInformationBlockItem(Block block, Item.Properties settings) {super(block, settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
