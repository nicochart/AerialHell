package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class AdditionalConditionLookAtPlayerGoal extends LookAtPlayerGoal
{
    protected final Mob goalOwner;

    public AdditionalConditionLookAtPlayerGoal(Mob entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
        this.goalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse() {return this.additionalConditionMet() && super.canUse();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.additionalConditionMet() && super.canContinueToUse();}

    public boolean additionalConditionMet() {return true;}
}
