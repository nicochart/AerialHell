package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class ActiveLookAtPlayerGoal extends LookAtPlayerGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveLookAtPlayerGoal(AbstractActivableEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse()
    {
        return this.activableGoalOwner.isActive() && super.canUse();
    }

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}
