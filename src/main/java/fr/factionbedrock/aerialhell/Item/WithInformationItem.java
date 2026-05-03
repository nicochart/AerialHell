package fr.factionbedrock.aerialhell.Item;

import java.util.function.Consumer;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;

public class WithInformationItem extends Item implements ExtraHoverTextItem
{
	public WithInformationItem(Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		this.appendOptionalDescriptionsHoverText(context, tooltipAdder);
		this.appendAbilityDescriptionHoverText(context, tooltipAdder);
		this.appendReactorMenuHoverText(context, tooltipAdder);
	}

	@Override public Item getSelf() {return this;}
}
