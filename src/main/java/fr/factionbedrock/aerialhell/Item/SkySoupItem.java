package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with sky bowl
{
	public SkySoupItem(Item.Properties settings) {super(settings);}

	@Override public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
	{
		if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW) && !level.isClientSide())
		{
			ItemHelper.removeEffectCuredBy(entity, stack);
		}
		if (entity instanceof Player player && !player.getAbilities().instabuild)
		{
			if (!player.getInventory().add(new ItemStack(AerialHellItems.SKY_BOWL, 1))) {player.drop(new ItemStack(AerialHellItems.SKY_BOWL, 1), false);}
		}
		return super.finishUsingItem(stack, level, entity);
	}
}