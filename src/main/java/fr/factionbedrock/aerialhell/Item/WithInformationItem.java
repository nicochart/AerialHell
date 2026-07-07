package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class WithInformationItem extends Item implements ExtraHoverTextItem
{
	public WithInformationItem(Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
	{
		this.appendOptionalDescriptionsHoverText(tooltipContext, components);
		this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
		this.appendReactorMenuHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
	}

	@Override public Item getSelf() {return this;}
}
