package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class AerialHellAxeItem extends AxeItem
{
	public AerialHellAxeItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(this.getDescription().formatted(Formatting.GRAY));
	}

	public MutableText getDescription() {return Text.translatable(this.getTranslationKey()+".desc");}

	@Override public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return !(cantRepair(stack));
	}

	public boolean cantRepair(ItemStack stack) //TODO use item tag in cantRepair method
	{
		return stack.isOf(AerialHellItems.HEAVY_AXE);
	}
}
