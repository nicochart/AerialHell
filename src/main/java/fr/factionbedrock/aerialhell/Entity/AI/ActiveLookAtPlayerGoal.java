package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;

public class ActiveLookAtPlayerGoal extends AdditionalConditionLookAtPlayerGoal
{
    public AbstractActivableEntity getActivableGoalOwner() {return (AbstractActivableEntity) this.goalOwner;}

    public ActiveLookAtPlayerGoal(AbstractActivableEntity entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
    }

    @Override public boolean additionalConditionMet() {return this.getActivableGoalOwner().isActive();}
}
