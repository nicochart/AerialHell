package fr.factionbedrock.aerialhell.Item;

import java.util.List;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

public class WithInformationItem extends Item
{
	public WithInformationItem(Item.Settings settings) {super(settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
