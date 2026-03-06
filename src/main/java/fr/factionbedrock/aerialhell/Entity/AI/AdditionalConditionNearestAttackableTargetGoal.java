package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import java.util.function.Predicate;

public class AdditionalConditionNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    private final Predicate<Mob> canUseAdditionalCondition;

    public AdditionalConditionNearestAttackableTargetGoal(Mob mob, Class<T> targetClassIn, boolean checkSight, Predicate<Mob> canUseAdditionalConditionOnGoalOwner)
    {
        super(mob, targetClassIn, checkSight);
        this.canUseAdditionalCondition = canUseAdditionalConditionOnGoalOwner;
    }

    public AdditionalConditionNearestAttackableTargetGoal(Mob mob, Class<T> targetClassIn, boolean checkSight, Predicate<Mob> canUseAdditionalConditionOnGoalOwner, TargetingConditions.Selector selector)
    {
        super(mob, targetClassIn, checkSight, selector);
        this.canUseAdditionalCondition = canUseAdditionalConditionOnGoalOwner;
    }

    @Override public boolean canUse() {return this.canUseAdditionalCondition.test(this.mob) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.canUseAdditionalCondition.test(this.mob) && super.canContinueToUse();}
}
