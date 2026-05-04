package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

@FunctionalInterface public interface ModuleCondition
{
    boolean conditionMet(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable LivingEntity enemyEntity, @Nullable DamageSource damageSource);
}
