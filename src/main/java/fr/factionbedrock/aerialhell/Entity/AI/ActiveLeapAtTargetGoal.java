package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;

public class ActiveLeapAtTargetGoal extends PounceAtTargetGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveLeapAtTargetGoal(AbstractActivableEntity entityIn, float leapMotionYIn)
    {
        super(entityIn, leapMotionYIn);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canStart() {return this.activableGoalOwner.isActive() && super.canStart();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean shouldContinue() {return this.activableGoalOwner.isActive() && super.shouldContinue();}
}
