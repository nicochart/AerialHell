package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;


@FunctionalInterface public interface ModuleCondition
{
    boolean conditionMet(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable DamageUseSituationInfo damageInfo, @Nullable MiningUseSituationInfo miningInfo);
}
