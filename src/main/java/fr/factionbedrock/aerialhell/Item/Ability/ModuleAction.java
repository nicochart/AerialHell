package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface public interface ModuleAction
{
    void apply(LivingEntity entity, ItemStack stack, @Nullable EquipmentSlot slot);
}
