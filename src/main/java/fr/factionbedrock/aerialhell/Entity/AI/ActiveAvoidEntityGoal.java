package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class ActiveAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
{
    protected final AbstractActivableEntity activableEntity;

    public ActiveAvoidEntityGoal(AbstractActivableEntity entityIn, Class<T> avoidClassIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        super(entityIn, avoidClassIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        this.activableEntity = entityIn;
    }

    @Override public boolean canUse() {return this.activableEntity.isActive() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.activableEntity.isActive() && super.canContinueToUse();}
}
