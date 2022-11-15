package fr.factionbedrock.aerialhell.Item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoluciteVibrantItem extends WithInformationItem
{ 
	public VoluciteVibrantItem(Properties properties) {super(properties);}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		LivingEntity livingEntityIn = (LivingEntity) entityIn;
		if (livingEntityIn.getMotion().y < -0.2 && !livingEntityIn.isSneaking())
		{
			livingEntityIn.fallDistance = 0;
			livingEntityIn.setMotion(livingEntityIn.getMotion().x, -0.2, livingEntityIn.getMotion().z);
		}
	}
}
