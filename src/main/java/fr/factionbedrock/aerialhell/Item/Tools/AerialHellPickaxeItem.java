package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class AerialHellPickaxeItem extends PickaxeItem
{
	public AerialHellPickaxeItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
