package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WithInformationBlockItem extends BlockItem implements ExtraHoverTextItem
{
	public WithInformationBlockItem(Block block, Properties prop) {super(block, prop);}

	@Override public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipAdder, TooltipFlag flag)
	{
		this.appendOptionalDescriptionsHoverText(tooltipAdder);
		this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), tooltipAdder);
		this.appendReactorMenuHoverText(ClientHelper.getLocalPlayer(), tooltipAdder);
	}

	@Override public Item getSelf() {return this;}
}
