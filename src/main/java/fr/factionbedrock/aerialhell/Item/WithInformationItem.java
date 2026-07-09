package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import fr.factionbedrock.aerialhell.Util.ItemHelper;

public class WithInformationItem extends Item
{
	public WithInformationItem(Item.Properties settings) {super(settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
