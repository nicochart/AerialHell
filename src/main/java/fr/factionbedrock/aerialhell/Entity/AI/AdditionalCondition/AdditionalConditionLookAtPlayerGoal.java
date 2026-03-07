package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class AdditionalConditionLookAtPlayerGoal extends LookAtPlayerGoal
{
    protected final int phase;

    public AdditionalConditionLookAtPlayerGoal(GoalConditionEntity entity, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {this(entity, watchTargetClass, maxDistance, 0);}
    public AdditionalConditionLookAtPlayerGoal(GoalConditionEntity entity, Class<? extends LivingEntity> watchTargetClass, float maxDistance, int goalPhase)
    {
        super(entity.getSelf(), watchTargetClass, maxDistance);
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return (GoalConditionEntity)this.mob;}

    @Override public boolean canUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalOwner().checkGoalCondition(phase) && super.canContinueToUse();}
}