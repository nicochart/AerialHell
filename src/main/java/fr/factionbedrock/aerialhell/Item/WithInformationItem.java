package fr.factionbedrock.aerialhell.Item;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class WithInformationItem extends Item
{
	public WithInformationItem(Item.Settings settings) {super(settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(this.getDescription().formatted(Formatting.GRAY));
	}

	public MutableText getDescription() {return Text.translatable(this.getTranslationKey()+".desc");}
}
