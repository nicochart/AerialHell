package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class ActiveNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight)
    {
        super(entityIn, targetClassIn, checkSight);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean canUse() {return this.activableGoalOwner.isActive() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}
