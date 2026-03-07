package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class AdditionalConditionWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal
{
    private final int phase;

    public AdditionalConditionWaterAvoidingRandomStrollGoal(GoalConditionEntity entity, double speed) {this(entity, speed, 0);}
    public AdditionalConditionWaterAvoidingRandomStrollGoal(GoalConditionEntity entity, double speed, int goalPhase)
    {
        super(entity.getSelf(), speed);
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return (GoalConditionEntity)this.mob;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}
