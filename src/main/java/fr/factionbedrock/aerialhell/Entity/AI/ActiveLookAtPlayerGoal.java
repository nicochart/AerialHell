package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;

public class ActiveLookAtPlayerGoal extends LookAtGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveLookAtPlayerGoal(AbstractActivableEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean shouldExecute()
    {
        return this.activableGoalOwner.isActive() && super.shouldExecute();
    }

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean shouldContinueExecuting() {return this.activableGoalOwner.isActive() && super.shouldContinueExecuting();}
}
