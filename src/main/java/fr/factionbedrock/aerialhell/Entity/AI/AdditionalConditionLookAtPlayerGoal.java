package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;

public class AdditionalConditionLookAtPlayerGoal extends LookAtEntityGoal
{
    protected final MobEntity goalOwner;

    public AdditionalConditionLookAtPlayerGoal(MobEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
        this.goalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canStart() {return this.additionalConditionMet() && super.canStart();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean shouldContinue() {return this.additionalConditionMet() && super.shouldContinue();}

    public boolean additionalConditionMet() {return true;}
}
