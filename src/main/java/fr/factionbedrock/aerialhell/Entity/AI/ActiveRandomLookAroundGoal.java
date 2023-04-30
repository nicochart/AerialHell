package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class ActiveRandomLookAroundGoal extends RandomLookAroundGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveRandomLookAroundGoal(AbstractActivableEntity entityIn)
    {
        super(entityIn);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse() {return this.activableGoalOwner.isActive() && super.canUse();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}