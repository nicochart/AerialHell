package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class AerialHellSwordItem extends SwordItem
{
	public AerialHellSwordItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}

	@Override public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient)
	{
		return !(cantRepair(stack));
	}

	public boolean cantRepair(ItemStack stack) //TODO use item tag in cantRepair method
	{
		return stack.is(AerialHellItems.HEAVY_SWORD)
				|| stack.is(AerialHellItems.HEALTH_BOOST_SWORD)
				|| stack.is(AerialHellItems.NINJA_SWORD)
				|| stack.is(AerialHellItems.NINJA_MASTER_SWORD)
				|| stack.is(AerialHellItems.GLOUTON_SWORD)
				|| stack.is(AerialHellItems.RANDOM_SWORD)
				|| stack.is(AerialHellItems.DISLOYAL_SWORD)
				|| stack.is(AerialHellItems.CURSED_SWORD)
				|| stack.is(AerialHellItems.ABSOLUTE_ZERO_SWORD)
				|| stack.is(AerialHellItems.SWORD_OF_LIGHT)
				|| stack.is(AerialHellItems.ANTIDOTE_SWORD)
				|| stack.is(AerialHellItems.NETHERIAN_KING_SWORD)
				|| stack.is(AerialHellItems.GLASS_CANON_SWORD)
				|| stack.is(AerialHellItems.GOD_SWORD);
	}
}
