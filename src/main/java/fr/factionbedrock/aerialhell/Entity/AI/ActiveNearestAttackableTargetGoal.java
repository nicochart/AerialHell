package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class ActiveNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight)
    {
        super(entityIn, targetClassIn, checkSight);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean shouldExecute() {return this.activableGoalOwner.isActive() && super.shouldExecute();}
    @Override public boolean shouldContinueExecuting() {return this.activableGoalOwner.isActive() && super.shouldContinueExecuting();}
}
