package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.DamageUseSituationInfo;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleCondition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final ModuleCondition condition;

    public ConditionModule(ModuleCondition condition) {super(); this.condition = condition;}

    public static ConditionModule itemOwnerCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((itemStack, itemOwner, equipmentSlot, damageInfo) -> entityPredicate.test(itemOwner));}
    public static ConditionModule otherEntityCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((itemStack, itemOwner, equipmentSlot, damageInfo) ->
    {
        if (damageInfo == null || !(damageInfo.otherEntity() instanceof LivingEntity livingOther)) {return true;}
        else {return entityPredicate.test(livingOther);}
    });}

    public ConditionModule opposite() {return new ConditionModule((itemStack, itemOwner, equipmentSlot, damageInfo) -> !this.conditionMet(itemStack, itemOwner, equipmentSlot, damageInfo));}

    public boolean conditionMet(AbilityUseSituation useSituation) {return this.conditionMet(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.damageUseSituationInfo);}

    private boolean conditionMet(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable DamageUseSituationInfo damageInfo) {return condition.conditionMet(itemStack, itemOwner, equipmentSlot, damageInfo);}
}
