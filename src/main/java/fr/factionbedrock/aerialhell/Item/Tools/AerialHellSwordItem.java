package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class AerialHellSwordItem extends SwordItem
{
	public AerialHellSwordItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}

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
		return stack.isOf(AerialHellItems.HEAVY_SWORD)
				|| stack.isOf(AerialHellItems.HEALTH_BOOST_SWORD)
				|| stack.isOf(AerialHellItems.NINJA_SWORD)
				|| stack.isOf(AerialHellItems.NINJA_MASTER_SWORD)
				|| stack.isOf(AerialHellItems.GLOUTON_SWORD)
				|| stack.isOf(AerialHellItems.RANDOM_SWORD)
				|| stack.isOf(AerialHellItems.DISLOYAL_SWORD)
				|| stack.isOf(AerialHellItems.CURSED_SWORD)
				|| stack.isOf(AerialHellItems.ABSOLUTE_ZERO_SWORD)
				|| stack.isOf(AerialHellItems.SWORD_OF_LIGHT)
				|| stack.isOf(AerialHellItems.ANTIDOTE_SWORD)
				|| stack.isOf(AerialHellItems.NETHERIAN_KING_SWORD)
				|| stack.isOf(AerialHellItems.GLASS_CANON_SWORD)
				|| stack.isOf(AerialHellItems.GOD_SWORD);
	}
}
