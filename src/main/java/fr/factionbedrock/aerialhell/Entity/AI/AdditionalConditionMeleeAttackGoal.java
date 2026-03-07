package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AdditionalConditionMeleeAttackGoal extends MeleeAttackGoal
{
    protected final int phase;

    public AdditionalConditionMeleeAttackGoal(GoalConditionEntity entity, double speedIn, boolean useLongMemory) {this(entity, speedIn, useLongMemory, 0);}
    public AdditionalConditionMeleeAttackGoal(GoalConditionEntity entity, double speedIn, boolean useLongMemory, int goalPhase)
    {
        super(entity.getSelf(), speedIn, useLongMemory);
        this.phase = goalPhase;
    }

    public GoalConditionEntity getGoalOwner() {return (GoalConditionEntity)this.mob;}

    @Override public boolean canUse() {return this.additionalConditionMet() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.additionalConditionMet() && super.canContinueToUse();}

    public boolean additionalConditionMet()
    {
        return this.getGoalOwner().checkGoalCondition(phase);
    }
}
