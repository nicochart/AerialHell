package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class AdditionalConditionNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    private final Predicate<Mob> canUseAdditionalCondition;
    @Nullable private final BiFunction<TargetingConditions, Mob, @Nullable LivingEntity> findTargetFunction;

    public AdditionalConditionNearestAttackableTargetGoal(Mob mob, Class<T> targetClassIn, boolean checkSight, Predicate<Mob> canUseAdditionalConditionOnGoalOwner)
    {
        this(mob, targetClassIn, checkSight, canUseAdditionalConditionOnGoalOwner, null);
    }

    public AdditionalConditionNearestAttackableTargetGoal(Mob mob, Class<T> targetClassIn, boolean checkSight, Predicate<Mob> canUseAdditionalConditionOnGoalOwner, @Nullable BiFunction<TargetingConditions, Mob, @Nullable LivingEntity> findTargetFunction)
    {
        super(mob, targetClassIn, checkSight);
        this.canUseAdditionalCondition = canUseAdditionalConditionOnGoalOwner;
        this.findTargetFunction = findTargetFunction;
    }

    @Override public boolean canUse() {return this.canUseAdditionalCondition.test(this.mob) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.canUseAdditionalCondition.test(this.mob) && super.canContinueToUse();}

    @Override protected void findTarget()
    {
        if (this.findTargetFunction == null) {super.findTarget();}
        else {this.target = this.findTargetFunction.apply(this.targetConditions, this.mob);}
    }
}
