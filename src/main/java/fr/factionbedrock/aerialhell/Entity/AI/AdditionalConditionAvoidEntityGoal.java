package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class AdditionalConditionAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
{
    private final int phase;
    public AdditionalConditionAvoidEntityGoal(GoalConditionEntity entity, Class<T> avoidClass, float avoidDistance, double farSpeed, double nearSpeed) {this(entity, avoidClass, avoidDistance, farSpeed, nearSpeed, 0);}
    public AdditionalConditionAvoidEntityGoal(GoalConditionEntity entity, Class<T> avoidClassIn, float avoidDistance, double farSpeed, double nearSpeed, int goalPhase)
    {
        super(entity.getSelf(), avoidClassIn, avoidDistance, farSpeed, nearSpeed);
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return (GoalConditionEntity)this.mob;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}
