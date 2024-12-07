package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;

public class ActiveNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T>
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight)
    {
        super(entityIn, targetClassIn, checkSight);
        this.activableGoalOwner = entityIn;
    }

    @Override public boolean canStart() {return this.activableGoalOwner.isActive() && super.canStart();}
    @Override public boolean shouldContinue() {return this.activableGoalOwner.isActive() && super.shouldContinue();}
}
