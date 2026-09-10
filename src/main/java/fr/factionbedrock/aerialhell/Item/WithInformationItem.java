package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WithInformationItem extends Item implements ExtraHoverTextItem
{
	public WithInformationItem(Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag)
	{
		this.appendOptionalDescriptionsHoverText(components);
		this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), components);
		this.appendReactorMenuHoverText(ClientHelper.getLocalPlayer(), components);
	}

	@Override public Item getSelf() {return this;}
}
