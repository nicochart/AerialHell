package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class BossSpawnEggItem extends ForgeSpawnEggItem
{
	public BossSpawnEggItem(Supplier<? extends EntityType<?>> type, int backgroundColor, int highlightColor, Properties props) {super(type, backgroundColor, highlightColor, props);}
	
	@Override public boolean hasEffect(ItemStack stack) {return true;}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().mergeStyle(TextFormatting.DARK_RED));
	}

	@OnlyIn(Dist.CLIENT)
	public IFormattableTextComponent getDescription()
	{
		return new TranslationTextComponent("item.aerialhell.boss_spawn_egg.desc");
	}
}
