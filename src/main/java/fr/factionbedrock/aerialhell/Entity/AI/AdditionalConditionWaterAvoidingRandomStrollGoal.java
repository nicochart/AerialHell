package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class AdditionalConditionWaterAvoidingRandomStrollGoal extends WanderAroundFarGoal
{
    public AdditionalConditionWaterAvoidingRandomStrollGoal(PathAwareEntity entityIn, double speedIn)
    {
        super(entityIn, speedIn);
    }

    @Override public boolean canStart() {return this.additionalConditionMet() && super.canStart();}
    @Override public boolean shouldContinue() {return this.additionalConditionMet() && super.shouldContinue();}

    public boolean additionalConditionMet() {return true;}
}
