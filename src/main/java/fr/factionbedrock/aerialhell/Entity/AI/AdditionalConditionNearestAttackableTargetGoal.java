package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class AdditionalConditionNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    protected final int phase;

    public AdditionalConditionNearestAttackableTargetGoal(GoalConditionEntity mob, Class<T> targetClassIn, boolean checkSight) {this(mob, targetClassIn, checkSight, 0);}
    public AdditionalConditionNearestAttackableTargetGoal(GoalConditionEntity mob, Class<T> targetClassIn, boolean checkSight, int goalPhase)
    {
        super(mob.getSelf(), targetClassIn, checkSight);
        this.phase = goalPhase;
    }

    public AdditionalConditionNearestAttackableTargetGoal(GoalConditionEntity mob, Class<T> targetClassIn, boolean checkSight, TargetingConditions.Selector selector) {this(mob, targetClassIn, checkSight, selector, 0);}
    public AdditionalConditionNearestAttackableTargetGoal(GoalConditionEntity mob, Class<T> targetClassIn, boolean checkSight, TargetingConditions.Selector selector, int goalPhase)
    {
        super(mob.getSelf(), targetClassIn, checkSight, selector);
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return (GoalConditionEntity)this.mob;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}
