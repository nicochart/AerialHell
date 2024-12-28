package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class WithInformationBlockItem extends BlockItem
{
	public WithInformationBlockItem(Block block, Item.Settings settings) {super(block, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
