package fr.factionbedrock.aerialhell.Item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class WithInformationBlockItem extends BlockItem implements ExtraHoverTextItem
{
	public WithInformationBlockItem(Block block, Properties prop) {super(block, prop);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		this.appendOptionalDescriptionsHoverText(context, tooltipAdder);
		this.appendAbilityDescriptionHoverText(context, tooltipAdder);
		this.appendReactorMenuHoverText(context, tooltipAdder);
	}

	@Override public Item getSelf() {return this;}
}
