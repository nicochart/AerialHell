package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class ActiveLookRandomlyGoal extends LookRandomlyGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveLookRandomlyGoal(AbstractActivableEntity entityIn)
    {
        super(entityIn);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean shouldExecute() {return this.activableGoalOwner.isActive() && super.shouldExecute();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean shouldContinueExecuting() {return this.activableGoalOwner.isActive() && super.shouldContinueExecuting();}
}