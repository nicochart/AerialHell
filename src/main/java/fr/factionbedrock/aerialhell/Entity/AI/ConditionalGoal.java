package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.ai.goal.Goal;

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
    }

    @Override public boolean canUse() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.canUse();}
    @Override public boolean canContinueToUse() {return entity.checkGoalCondition(conditionIndex) && wrappedGoal.canContinueToUse();}
    @Override public boolean isInterruptable() {return wrappedGoal.isInterruptable();}
    @Override public void start() {wrappedGoal.start();}
    @Override public void stop() {wrappedGoal.stop();}
    @Override public void tick() {wrappedGoal.tick();}
    @Override public boolean requiresUpdateEveryTick() {return wrappedGoal.requiresUpdateEveryTick();}
    @Override public void setFlags(EnumSet<Flag> flagSet) {super.setFlags(flagSet); this.setFlags(flagSet);}
}
