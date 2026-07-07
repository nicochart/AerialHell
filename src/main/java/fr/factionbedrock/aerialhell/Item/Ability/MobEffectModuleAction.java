package fr.factionbedrock.aerialhell.Item.Ability;

import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateListProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@FunctionalInterface public interface MobEffectModuleAction
{
    void apply(LivingEntity entity, ItemStack stack, @Nullable EquipmentSlot slot, List<MobEffectTemplateListProvider> mobEffectTemplateListProviders);
}
