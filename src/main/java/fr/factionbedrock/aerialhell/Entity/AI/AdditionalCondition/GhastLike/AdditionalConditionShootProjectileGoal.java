package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.GhastLike;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;

public abstract class AdditionalConditionShootProjectileGoal extends ShootProjectileGoal
{
    protected final int goalIndex;

    public AdditionalConditionShootProjectileGoal(GoalConditionEntity mob) {this(mob, false, 0);}
    public AdditionalConditionShootProjectileGoal(GoalConditionEntity mob, int goalPhase) {this(mob, false, goalPhase);}
    public AdditionalConditionShootProjectileGoal(GoalConditionEntity mob, boolean affectMovements) {this(mob, affectMovements, 0);}
    public AdditionalConditionShootProjectileGoal(GoalConditionEntity mob, boolean affectMovements, int goalIndex)
    {
        super(mob.getSelf(), affectMovements);
        this.goalIndex = goalIndex;
    }

    public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity) this.getParentEntity();}

    @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canContinueToUse();}
}
