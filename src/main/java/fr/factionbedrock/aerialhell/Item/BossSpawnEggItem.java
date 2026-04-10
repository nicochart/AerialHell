package fr.factionbedrock.aerialhell.Item;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class BossSpawnEggItem extends SpawnEggItem
{
	public BossSpawnEggItem(Item.Properties settings) {super(settings);}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		textConsumer.accept(this.getDescription().withStyle(ChatFormatting.DARK_RED));
	}

	public MutableComponent getDescription() {return Component.translatable("item.aerialhell.boss_spawn_egg.desc");}
}
