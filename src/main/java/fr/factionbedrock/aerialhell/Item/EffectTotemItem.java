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
	
	@Override public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
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
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.SPEED_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.SPEED_II_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1));
					}
					else if (this == AerialHellBlocksAndItems.NIGHT_VISION_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.AGILITY_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0));
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.JUMP, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.HEAD_IN_THE_CLOUDS_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 1000, 0));
					}
					else if (this == AerialHellBlocksAndItems.HERO_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.GOD_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.GOD.getDelegate(), 1200, 0));
					}
					else if (this == AerialHellBlocksAndItems.CURSED_TOTEM.get())
					{
						if (!(ItemHelper.getItemInTagCount(livingEntityIn.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4))
						{
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600, 0));
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 900, 0));
						}
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1500, 2));
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
