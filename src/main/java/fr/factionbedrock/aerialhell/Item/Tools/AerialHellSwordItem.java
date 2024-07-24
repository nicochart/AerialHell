package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;

public class AerialHellSwordItem extends SwordItem
{
	public AerialHellSwordItem(Tier tier, Properties builderIn) {super(tier, builderIn);}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
	{
		components.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
