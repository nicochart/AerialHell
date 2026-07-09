package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class AdditionalConditionRandomLookAroundGoal extends RandomLookAroundGoal
{
    public AdditionalConditionRandomLookAroundGoal(Mob entityIn) {super(entityIn);}

    @Override public boolean canUse() {return this.additionalConditionMet() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.additionalConditionMet() && super.canContinueToUse();}

    public boolean additionalConditionMet() {return true;}
}