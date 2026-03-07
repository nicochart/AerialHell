package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;

public class AdditionalConditionLeapAtTargetGoal extends LeapAtTargetGoal
{
    protected final GoalConditionEntity goalOwner;
    protected final int phase;

    public AdditionalConditionLeapAtTargetGoal(GoalConditionEntity entity, float leapMotionY) {this(entity, leapMotionY, 0);}
    public AdditionalConditionLeapAtTargetGoal(GoalConditionEntity entity, float leapMotionY, int goalPhase)
    {
        super(entity.getSelf(), leapMotionY);
        this.goalOwner = entity;
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return this.goalOwner;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}
