package fr.factionbedrock.aerialhell.Item.Ability.Module;

import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;

public class ConditionModule extends AbilityModule
{
    private final Predicate<LivingEntity> condition;

    public ConditionModule(Predicate<LivingEntity> condition, UseSituation useSituation)
    {
        super(useSituation);
        this.condition = condition;
    }

    public boolean isSituationFavorableToApplyModules(LivingEntity entity) {return condition.test(entity);}

    public static ConditionModule passive(Predicate<LivingEntity> condition) {return new ConditionModule(condition, UseSituation.PASSIVE);}
    public static ConditionModule onUse(Predicate<LivingEntity> condition) {return new ConditionModule(condition, UseSituation.ON_USE);}
    public static ConditionModule onHurtEnemy(Predicate<LivingEntity> condition) {return new ConditionModule(condition, UseSituation.ON_HURT_ENEMY);}
}
