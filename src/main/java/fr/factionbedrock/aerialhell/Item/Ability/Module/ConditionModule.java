package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.ModuleUseSituation;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final Predicate<LivingEntity> condition;

    public ConditionModule(Predicate<LivingEntity> condition, ModuleUseSituation useSituation)
    {
        super(useSituation);
        this.condition = condition;
    }

    public boolean conditionMet(LivingEntity entity) {return condition.test(entity);}

    public static ConditionModule passive(Predicate<LivingEntity> condition) {return new ConditionModule(condition, ModuleUseSituation.PASSIVE);}
    public static ConditionModule onUse(Predicate<LivingEntity> condition) {return new ConditionModule(condition, ModuleUseSituation.ON_USE);}
    public static ConditionModule onHurtEnemy(Predicate<LivingEntity> condition) {return new ConditionModule(condition, ModuleUseSituation.ON_HURT_ENEMY);}
}
