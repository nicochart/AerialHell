package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VoluciteVibrantItem extends WithInformationItem
{ 
	public VoluciteVibrantItem(Properties properties) {super(properties);}
	
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		LivingEntity livingEntityIn = (LivingEntity) entityIn;
		if (livingEntityIn.getDeltaMovement().y < -0.2 && !livingEntityIn.isShiftKeyDown())
		{
			livingEntityIn.fallDistance = 0;
			livingEntityIn.setDeltaMovement(livingEntityIn.getDeltaMovement().x, -0.2, livingEntityIn.getDeltaMovement().z);
		}
	}
}
