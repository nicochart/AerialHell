package fr.factionbedrock.aerialhell.Item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoluciteVibrantItem extends WithInformationItem
{ 
	public VoluciteVibrantItem(Item.Settings settings) {super(settings);}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (entity instanceof LivingEntity livingEntity)
		{
			if (livingEntity.getVelocity().y < -0.2 && !livingEntity.isSneaking())
			{
				livingEntity.fallDistance = 0;
				livingEntity.setVelocity(livingEntity.getVelocity().x, -0.2, livingEntity.getVelocity().z);
			}
		}
	}
}
