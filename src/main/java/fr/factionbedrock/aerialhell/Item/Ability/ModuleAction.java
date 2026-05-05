package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

@FunctionalInterface public interface ModuleAction
{
    void apply(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable DamageUseSituationInfo damageInfo);
}
