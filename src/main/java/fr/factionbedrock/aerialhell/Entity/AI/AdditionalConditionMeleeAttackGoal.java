package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class AdditionalConditionMeleeAttackGoal extends MeleeAttackGoal
{
    protected final PathAwareEntity goalOwner;

    public AdditionalConditionMeleeAttackGoal(PathAwareEntity entityIn, double speedIn, boolean useLongMemory)
    {
        super(entityIn, speedIn, useLongMemory);
        this.goalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canStart() {return this.additionalConditionMet() && super.canStart();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean shouldContinue() {return this.additionalConditionMet() && super.shouldContinue();}

    public boolean additionalConditionMet() {return true;}
}
