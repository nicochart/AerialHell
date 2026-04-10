package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VoluciteVibrantItem extends WithInformationItem
{ 
	public VoluciteVibrantItem(Item.Properties settings) {super(settings);}

	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (entity instanceof LivingEntity livingEntity)
		{
			if (livingEntity.getDeltaMovement().y < -0.2 && !livingEntity.isShiftKeyDown())
			{
				livingEntity.fallDistance = 0;
				livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x, -0.2, livingEntity.getDeltaMovement().z);
			}
		}
	}
}
