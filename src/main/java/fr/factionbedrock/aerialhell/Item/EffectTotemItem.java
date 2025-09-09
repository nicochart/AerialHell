package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.Nullable;

public class EffectTotemItem extends Item
{
	private int timer; 
	
	public EffectTotemItem(Properties properties)
	{
		super(properties);
		this.timer = 0;
	}

	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!level.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity)
			{
				LivingEntity livingEntityIn = (LivingEntity) entity;
				if (livingEntityIn.getMainHandItem().getItem() == this || livingEntityIn.getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.REGENERATION_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 0));
					}
					else if (this == AerialHellItems.SPEED_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0));
					}
					else if (this == AerialHellItems.SPEED_II_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 1));
					}
					else if (this == AerialHellItems.NIGHT_VISION_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0));
					}
					else if (this == AerialHellItems.AGILITY_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0));
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 1200, 0));
					}
					else if (this == AerialHellItems.HEAD_IN_THE_CLOUDS_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 1000, 0));
					}
					else if (this == AerialHellItems.HERO_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 1200, 0));
					}
					else if (this == AerialHellItems.GOD_TOTEM.get())
					{
						livingEntityIn.addEffect(new MobEffectInstance(AerialHellMobEffects.GOD.getDelegate(), 1200, 0));
					}
					else if (this == AerialHellItems.CURSED_TOTEM.get())
					{
						if (!(ItemHelper.getItemInTagCount(EntityHelper.getEquippedHumanoidArmorItemList(livingEntityIn), AerialHellTags.Items.SHADOW_ARMOR) >= 4))
						{
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 300, 0));
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600, 0));
							livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 900, 0));
						}
						livingEntityIn.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1500, 2));
					}
					else if (this == AerialHellItems.SHADOW_TOTEM.get())
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
