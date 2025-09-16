package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BossSpawnEggItem extends SpawnEggItem
{
	public BossSpawnEggItem(EntityType<? extends MobEntity> type, Item.Settings settings) {super(type, settings);}
	
	@Override public boolean hasGlint(ItemStack stack) {return true;}

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		textConsumer.accept(this.getDescription().formatted(Formatting.DARK_RED));
	}

	public MutableText getDescription() {return Text.translatable("item.aerialhell.boss_spawn_egg.desc");}
}
