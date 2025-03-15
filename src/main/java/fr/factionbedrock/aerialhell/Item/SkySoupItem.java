package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with sky bowl
{
	public SkySoupItem(Item.Settings settings) {super(settings);}
	
	@Override public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entityLiving)
	{
		if (stack.isOf(AerialHellItems.SHADOW_FRUIT_STEW) && !world.isClient)
		{
			ItemHelper.removeEffectCuredBy(entityLiving, stack);
		}
		ItemStack itemstack = super.finishUsing(stack, world, entityLiving);
		return entityLiving instanceof PlayerEntity player && (player.getAbilities().creativeMode) ? itemstack : new ItemStack(AerialHellItems.SKY_BOWL);
	}
}