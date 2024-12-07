package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;

public class ActiveAvoidEntityGoal<T extends LivingEntity> extends FleeEntityGoal<T>
{
    protected final AbstractActivableEntity activableEntity;

    public ActiveAvoidEntityGoal(AbstractActivableEntity entityIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        super(entityIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        this.activableEntity = entityIn;
    }

    @Override public boolean canStart() {return this.activableEntity.isActive() && super.canStart();}
    @Override public boolean shouldContinue() {return this.activableEntity.isActive() && super.shouldContinue();}
}
