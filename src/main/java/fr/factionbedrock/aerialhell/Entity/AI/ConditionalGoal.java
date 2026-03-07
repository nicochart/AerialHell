package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class ConditionalGoal extends Goal
{
    private final Goal wrappedGoal;
    private final GoalConditionEntity entity;
    private final int conditionIndex;

    public ConditionalGoal(GoalConditionEntity goalOwner, Goal goal) {this(goalOwner, 0, goal);}
    public ConditionalGoal(GoalConditionEntity goalOwner, int conditionIndex, Goal goal)
    {
        this.wrappedGoal = goal;
        this.entity = goalOwner;
        this.conditionIndex = conditionIndex;
    }

    @Override public boolean canUse() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.canUse();}
    @Override public boolean canContinueToUse() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.canContinueToUse();}
    @Override public void start() {wrappedGoal.start();}
    @Override public void stop() {wrappedGoal.stop();}
    @Override public void tick() {wrappedGoal.tick();}
}
