package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class EffectTotemItem extends Item
{
	private int timer; 
	
	public EffectTotemItem(Item.Settings settings)
	{
		super(settings);
		this.timer = 0;
	}

	@Override public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!world.isClient() && timer <= 0)
		{
			if (entity instanceof LivingEntity)
			{
				LivingEntity livingEntityIn = (LivingEntity) entity;
				if (livingEntityIn.getMainHandStack().getItem() == this || livingEntityIn.getOffHandStack().getItem() == this)
				{
					if (this == AerialHellItems.REGENERATION_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 0));
					}
					else if (this == AerialHellItems.SPEED_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0));
					}
					else if (this == AerialHellItems.SPEED_II_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 1));
					}
					else if (this == AerialHellItems.NIGHT_VISION_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0));
					}
					else if (this == AerialHellItems.AGILITY_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0));
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1200, 0));
					}
					else if (this == AerialHellItems.HEAD_IN_THE_CLOUDS_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 1000, 0));
					}
					else if (this == AerialHellItems.HERO_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 1200, 0));
					}
					else if (this == AerialHellItems.GOD_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.GOD, 1200, 0));
					}
					else if (this == AerialHellItems.CURSED_TOTEM)
					{
						if (!(ItemHelper.getItemInTagCount(EntityHelper.getEquippedHumanoidArmorItemList(livingEntityIn), AerialHellTags.Items.SHADOW_ARMOR) >= 4))
						{
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0));
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 0));
							livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 900, 0));
						}
						livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1500, 2));
					}
					else if (this == AerialHellItems.SHADOW_TOTEM)
					{
						livingEntityIn.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 1000, 0));
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
