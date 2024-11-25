package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

public class EffectTotemItem extends Item
{
	private int timer; 
	
	public EffectTotemItem(Properties properties)
	{
		super(properties);
		this.timer = 0;
	}
	
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (!worldIn.isClientSide() && timer <= 0)
		{
			if (entityIn instanceof LivingEntity)
			{
				LivingEntity livingEntityIn = (LivingEntity) entityIn;
				if (livingEntityIn.getMainHandItem().getItem() == this || livingEntityIn.getOffhandItem().getItem() == this)
				{
					if (this == AerialHellBlocksAndItems.REGENERATION_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.SPEED_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MOVEMENT_SPEED, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.SPEED_II_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MOVEMENT_SPEED, 1200, 1));
					}
					else if (this == AerialHellBlocksAndItems.NIGHT_VISION_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.AGILITY_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MOVEMENT_SPEED, 1200, 0));
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.HEAD_IN_THE_CLOUDS_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 1000, 0));
					}
					else if (this == AerialHellBlocksAndItems.HERO_TOTEM.get())
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.GOD_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.GOD.getDelegate(), 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.CURSED_TOTEM.get())
					{
						if (!(ItemHelper.getItemInTagCount(livingEntityIn.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4))
						{
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.CONFUSION, 300, 0));
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 0));
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MOVEMENT_SLOWDOWN, 900, 0));
						}
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.DAMAGE_RESISTANCE, 1500, 2));
					}
					else if (this == AerialHellBlocksAndItems.SHADOW_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.getDelegate(), 1000, 0));
					}
				}
			}
			timer = 300;
		}
		else if (timer > -10)
		{
			timer--;
		}
	}
}
