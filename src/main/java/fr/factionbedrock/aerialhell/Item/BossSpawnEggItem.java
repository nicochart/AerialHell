package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class BossSpawnEggItem extends ForgeSpawnEggItem
{
	public BossSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {super(type, backgroundColor, highlightColor, props);}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().withStyle(ChatFormatting.DARK_RED));
	}

	@OnlyIn(Dist.CLIENT)
	public TranslatableComponent getDescription()
	{
		return new TranslatableComponent("item.aerialhell.boss_spawn_egg.desc");
	}
}
