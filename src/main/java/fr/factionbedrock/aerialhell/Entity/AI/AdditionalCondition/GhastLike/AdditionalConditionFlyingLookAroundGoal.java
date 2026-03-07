package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.GhastLike;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.FlyingLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;

public class AdditionalConditionFlyingLookAroundGoal extends FlyingLookAroundGoal
{
    protected final int goalIndex;

    public AdditionalConditionFlyingLookAroundGoal(GoalConditionEntity flyingMob) {this(flyingMob, 0);}

    public AdditionalConditionFlyingLookAroundGoal(GoalConditionEntity flyingMob, int goalIndex)
    {
        super(flyingMob.getSelf());
        this.goalIndex = goalIndex;
    }

    public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity) this.getParentEntity();}

    @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canContinueToUse();}
}
