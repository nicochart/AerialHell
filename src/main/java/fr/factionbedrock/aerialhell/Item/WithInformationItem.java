package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

public class WithInformationItem extends Item
{
	public WithInformationItem(Item.Settings settings) {super(settings);}

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), textConsumer);
	}
}
