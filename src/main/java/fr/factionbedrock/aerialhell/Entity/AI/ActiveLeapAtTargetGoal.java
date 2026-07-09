package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;

public class ActiveLeapAtTargetGoal extends LeapAtTargetGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveLeapAtTargetGoal(AbstractActivableEntity entityIn, float leapMotionYIn)
    {
        super(entityIn, leapMotionYIn);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse() {return this.activableGoalOwner.isActive() && super.canUse();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}
