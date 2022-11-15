package fr.factionbedrock.aerialhell.Item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WithInformationItem extends Item
{
	public WithInformationItem(Properties properties) {super(properties);}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().mergeStyle(TextFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public IFormattableTextComponent getDescription()
	{
		return new TranslationTextComponent(this.getTranslationKey() + ".desc");
	}
}
