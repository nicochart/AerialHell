package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.ModuleCondition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final ModuleCondition condition;

    public ConditionModule(ModuleCondition condition) {super(); this.condition = condition;}

    public static ConditionModule entityCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((entity, itemStack, equipmentSlot) -> entityPredicate.test(entity));}

    public ConditionModule opposite() {return new ConditionModule((entity, stack, equipmentSlot) -> !this.conditionMet(entity, stack, equipmentSlot));}

    public boolean conditionMet(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot) {return condition.conditionMet(entity, itemStack, equipmentSlot);}
}
