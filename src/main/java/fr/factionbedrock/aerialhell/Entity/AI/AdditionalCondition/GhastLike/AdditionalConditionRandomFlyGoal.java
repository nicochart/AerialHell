package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.GhastLike;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.RandomFlyGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;

public class AdditionalConditionRandomFlyGoal extends RandomFlyGoal
{
    protected final int goalIndex;

    public AdditionalConditionRandomFlyGoal(GoalConditionEntity flyingMob) {this(flyingMob, 0);}
    public AdditionalConditionRandomFlyGoal(GoalConditionEntity flyingMob, int goalIndex)
    {
        super(flyingMob.getSelf());
        this.goalIndex = goalIndex;
    }

    public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity) this.getParentEntity();}

    @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canContinueToUse();}
}
