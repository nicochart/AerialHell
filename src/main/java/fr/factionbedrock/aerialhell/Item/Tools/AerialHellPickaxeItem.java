package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class AerialHellPickaxeItem extends PickaxeItem
{
	public AerialHellPickaxeItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
