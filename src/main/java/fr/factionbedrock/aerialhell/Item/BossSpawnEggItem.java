package fr.factionbedrock.aerialhell.Item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;

public class BossSpawnEggItem extends SpawnEggItem
{
	public BossSpawnEggItem(EntityType<? extends Mob> type, int primaryColor, int secondaryColor, Item.Properties settings) {super(type, primaryColor, secondaryColor, settings);}
	
	@Override public boolean isFoil(ItemStack stack) {return true;}
	
	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		tooltip.add(this.getDescription().withStyle(ChatFormatting.DARK_RED));
	}

	public MutableComponent getDescription() {return Component.translatable("item.aerialhell.boss_spawn_egg.desc");}
}
