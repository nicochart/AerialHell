package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class AdditionalConditionRandomLookAroundGoal extends RandomLookAroundGoal
{
    private final GoalConditionEntity goalOwner;
    private final int phase;

    public AdditionalConditionRandomLookAroundGoal(GoalConditionEntity entity) {this(entity, 0);}
    public AdditionalConditionRandomLookAroundGoal(GoalConditionEntity entity, int goalPhase)
    {
        super(entity.getSelf());
        this.goalOwner = entity;
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return this.goalOwner;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}