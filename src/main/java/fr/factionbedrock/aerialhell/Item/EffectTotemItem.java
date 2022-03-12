package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class EffectTotemItem extends Item
{
	private int timer; 
	
	public EffectTotemItem(Properties properties)
	{
		super(properties);
		this.timer = 0;
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (timer <= 0)
		{
			if (entityIn instanceof LivingEntity)
			{
				if (((LivingEntity) entityIn).getHeldItemOffhand().getItem() == this || ((LivingEntity) entityIn).getHeldItemMainhand().getItem() == this)
				{
					if (this == AerialHellBlocksAndItems.REGENERATION_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 1200, 1));
					}
					else if (this == AerialHellBlocksAndItems.SPEED_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SPEED, 1200, 1));
					}
					else if (this == AerialHellBlocksAndItems.NIGHT_VISION_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.AGILITY_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SPEED, 1200, 1));
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1200, 1));
					}
					else if (this == AerialHellBlocksAndItems.HERO_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.GOD_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(AerialHellPotionEffects.GOD.get(), 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.CURSED_TOTEM.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 0));
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 600, 0));
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 900, 0));
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1200, 2));
					}
				}
			}
			timer = 300;
		}
		timer--;
	}
}
