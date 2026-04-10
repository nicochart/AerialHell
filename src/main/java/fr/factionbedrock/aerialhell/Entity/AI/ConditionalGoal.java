package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

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
        this.setControls(wrappedGoal.getControls());
    }

    @Override public boolean canStart() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.canStart();}
    @Override public boolean shouldContinue() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.shouldContinue();}
    @Override public boolean canStop() {return wrappedGoal.canStop();}
    @Override public void start() {wrappedGoal.start();}
    @Override public void stop() {wrappedGoal.stop();}
    @Override public void tick() {wrappedGoal.tick();}
    @Override public boolean shouldRunEveryTick() {return wrappedGoal.shouldRunEveryTick();}
}
