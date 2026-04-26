package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.ModuleCondition;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleUseSituation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final ModuleCondition condition;

    public ConditionModule(ModuleCondition condition, ModuleUseSituation useSituation)
    {
        super(useSituation);
        this.condition = condition;
    }

    public boolean conditionMet(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot) {return condition.conditionMet(entity, itemStack, equipmentSlot);}

    public static ConditionModule passive(Predicate<LivingEntity> condition) {return passive((entity, stack, equipmentSlot) -> condition.test(entity));}
    public static ConditionModule onUse(Predicate<LivingEntity> condition) {return onUse((entity, stack, equipmentSlot) -> condition.test(entity));}
    public static ConditionModule onHurtEnemy(Predicate<LivingEntity> condition) {return onHurtEnemy((entity, stack, equipmentSlot) -> condition.test(entity));}
    public static ConditionModule passive(ModuleCondition condition) {return new ConditionModule(condition, ModuleUseSituation.PASSIVE);}
    public static ConditionModule onUse(ModuleCondition condition) {return new ConditionModule(condition, ModuleUseSituation.ON_USE);}
    public static ConditionModule onHurtEnemy(ModuleCondition condition) {return new ConditionModule(condition, ModuleUseSituation.ON_HURT_ENEMY);}
}
