package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with sky bowl
{
	public SkySoupItem(Item.Properties builder) {super(builder);}
	
	@Override public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving)
	{
		if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW) && !level.isClientSide)
		{
			ItemHelper.removeEffectCuredBy(entityLiving, stack);
		}
		ItemStack itemstack = super.finishUsingItem(stack, level, entityLiving);
		return entityLiving instanceof Player && ((Player)entityLiving).getAbilities().instabuild ? itemstack : new ItemStack(AerialHellItems.SKY_BOWL.get());
	}
}