package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;

public class ActiveAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
{
    protected final AbstractActivableEntity activableEntity;

    public ActiveAvoidEntityGoal(AbstractActivableEntity entityIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        super(entityIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        this.activableEntity = entityIn;
    }

    @Override public boolean shouldExecute() {return this.activableEntity.isActive() && super.shouldExecute();}
    @Override public boolean shouldContinueExecuting() {return this.activableEntity.isActive() && super.shouldContinueExecuting();}
}
