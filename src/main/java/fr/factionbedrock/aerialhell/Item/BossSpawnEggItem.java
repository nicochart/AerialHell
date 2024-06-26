package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

public class BossSpawnEggItem extends DeferredSpawnEggItem
{
	public BossSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {super(type, backgroundColor, highlightColor, props);}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
	{
		components.add(this.getDescription().withStyle(ChatFormatting.DARK_RED));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable("item.aerialhell.boss_spawn_egg.desc");
	}
}
