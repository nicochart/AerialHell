package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class ActiveWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomStrollGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveWaterAvoidingRandomWalkingGoal(AbstractActivableEntity entityIn, double speedIn)
    {
        super(entityIn, speedIn);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean canUse() {return this.activableGoalOwner.isActive() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}
