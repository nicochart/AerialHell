package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class AerialHellAxeItem extends AxeItem
{
	public AerialHellAxeItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}

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
		return stack.is(AerialHellItems.HEAVY_AXE);
	}
}
