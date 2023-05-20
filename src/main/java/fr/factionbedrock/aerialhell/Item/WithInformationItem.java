package fr.factionbedrock.aerialhell.Item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WithInformationItem extends Item
{
	public WithInformationItem(Properties properties) {super(properties);}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public TranslatableComponent getDescription()
	{
		return new TranslatableComponent(this.getDescriptionId() + ".desc");
	}
}
