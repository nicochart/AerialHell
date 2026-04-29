package fr.factionbedrock.aerialhell.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Consumer;

public class WithInformationBlockItem extends BlockItem
{
	public WithInformationBlockItem(Block block, Properties prop) {super(block, prop);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		this.getOptionalDescription(1).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GRAY)));
		this.getOptionalDescription(2).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GRAY)));
	}

	protected Optional<MutableComponent > getOptionalDescription(int index)
	{
		String key = this.getDescriptionId() + ".desc" + (index != 1 ? "_"+index : "");
		return Language.getInstance().has(key) ? Optional.of(Component.translatable(key).withStyle(ChatFormatting.GRAY)) : Optional.empty();
	}
}
