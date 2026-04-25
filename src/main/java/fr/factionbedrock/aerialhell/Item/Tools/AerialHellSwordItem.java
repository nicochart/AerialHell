package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;

public class AerialHellSwordItem extends Item
{
	public AerialHellSwordItem( Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
