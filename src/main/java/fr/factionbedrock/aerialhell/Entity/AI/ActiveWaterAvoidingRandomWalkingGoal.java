package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class ActiveWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveWaterAvoidingRandomWalkingGoal(AbstractActivableEntity entityIn, double speedIn)
    {
        super(entityIn, speedIn);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean shouldExecute() {return this.activableGoalOwner.isActive() && super.shouldExecute();}
    @Override public boolean shouldContinueExecuting() {return this.activableGoalOwner.isActive() && super.shouldContinueExecuting();}
}
