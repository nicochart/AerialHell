package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AdditionalConditionMeleeAttackGoal extends MeleeAttackGoal
{
    protected final PathfinderMob goalOwner;

    public AdditionalConditionMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory)
    {
        super(entityIn, speedIn, useLongMemory);
        this.goalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse() {return this.additionalConditionMet() && super.canUse();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.additionalConditionMet() && super.canContinueToUse();}

    public boolean additionalConditionMet() {return true;}
}
