package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;

public class ActiveRandomLookAroundGoal extends AdditionalConditionRandomLookAroundGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveRandomLookAroundGoal(AbstractActivableEntity entityIn)
    {
        super(entityIn);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean additionalConditionMet() {return this.activableGoalOwner.isActive();}
}