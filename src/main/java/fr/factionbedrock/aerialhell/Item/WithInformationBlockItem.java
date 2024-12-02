package fr.factionbedrock.aerialhell.Item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class WithInformationBlockItem extends BlockItem
{
	public WithInformationBlockItem(Block block, Item.Settings settings) {super(block, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(this.getDescription().formatted(Formatting.GRAY));
	}

	public MutableText getDescription() {return Text.translatable(this.getTranslationKey()+".desc");}
}
