package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class AdditionalConditionWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal
{
    public AdditionalConditionWaterAvoidingRandomStrollGoal(PathfinderMob entityIn, double speedIn)
    {
        super(entityIn, speedIn);
    }

    @Override public boolean canUse() {return this.additionalConditionMet() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.additionalConditionMet() && super.canContinueToUse();}

    public boolean additionalConditionMet() {return true;}
}
