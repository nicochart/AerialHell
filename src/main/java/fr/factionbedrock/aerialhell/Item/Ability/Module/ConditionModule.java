package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleCondition;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final ModuleCondition condition;

    public ConditionModule(ModuleCondition condition) {super(); this.condition = condition;}

    public static ConditionModule entityCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((itemStack, itemOwner, equipmentSlot, enemyEntity, damageSource) -> entityPredicate.test(itemOwner));}

    public ConditionModule opposite() {return new ConditionModule((itemStack, itemOwner, equipmentSlot, enemyEntity, damageSource) -> !this.conditionMet(itemStack, itemOwner, equipmentSlot, enemyEntity, damageSource));}

    public boolean conditionMet(AbilityUseSituation useSituation) {return this.conditionMet(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.enemyEntity, useSituation.damageSource);}

    private boolean conditionMet(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable LivingEntity enemyEntity, @Nullable DamageSource damageSource) {return condition.conditionMet(itemStack, itemOwner, equipmentSlot, enemyEntity, damageSource);}
}
