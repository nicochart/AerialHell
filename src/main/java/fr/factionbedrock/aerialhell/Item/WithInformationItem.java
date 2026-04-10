package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import fr.factionbedrock.aerialhell.Util.ItemHelper;

public class WithInformationItem extends Item
{
	public WithInformationItem(Item.Properties settings) {super(settings);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), textConsumer);
	}
}
