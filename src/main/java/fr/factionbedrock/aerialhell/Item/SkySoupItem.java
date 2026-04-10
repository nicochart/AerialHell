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
	
	@Override public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving)
	{
		if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW) && !world.isClientSide())
		{
			ItemHelper.removeEffectCuredBy(entityLiving, stack);
		}
		ItemStack itemstack = super.finishUsingItem(stack, world, entityLiving);
		return entityLiving instanceof Player player && (player.getAbilities().instabuild) ? itemstack : new ItemStack(AerialHellItems.SKY_BOWL);
	}
}