package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

public class WithInformationBlockItem extends BlockItem
{
	public WithInformationBlockItem(Block block, Item.Properties settings) {super(block, settings);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), textConsumer);
	}
}
