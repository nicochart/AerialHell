package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;

public class ActiveWaterAvoidingRandomWalkingGoal extends AdditionalConditionWaterAvoidingRandomStrollGoal
{
    public ActiveWaterAvoidingRandomWalkingGoal(AbstractActivableEntity entityIn, double speedIn)
    {
        super(entityIn, speedIn);
    }

    public AbstractActivableEntity getGoalOwner() {return (AbstractActivableEntity) this.mob;}

    @Override public boolean additionalConditionMet() {return this.getGoalOwner().isActive();}
}
