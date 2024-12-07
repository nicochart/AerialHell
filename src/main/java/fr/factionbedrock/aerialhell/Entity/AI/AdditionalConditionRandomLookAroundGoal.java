package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.mob.MobEntity;

public class AdditionalConditionRandomLookAroundGoal extends LookAroundGoal
{
    public AdditionalConditionRandomLookAroundGoal(MobEntity entityIn) {super(entityIn);}

    @Override public boolean canStart() {return this.additionalConditionMet() && super.canStart();}
    @Override public boolean shouldContinue() {return this.additionalConditionMet() && super.shouldContinue();}

    public boolean additionalConditionMet() {return true;}
}