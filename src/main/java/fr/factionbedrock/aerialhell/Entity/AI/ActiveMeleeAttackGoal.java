package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;

public class ActiveMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
{
    public AbstractActivableEntity getActivableGoalOwner() {return (AbstractActivableEntity) this.goalOwner;}

    public ActiveMeleeAttackGoal(AbstractActivableEntity entityIn, double speedIn, boolean useLongMemory)
    {
        super(entityIn, speedIn, useLongMemory);
    }

    @Override public boolean additionalConditionMet() {return this.getActivableGoalOwner().isActive();}
}
