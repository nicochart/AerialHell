package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ActiveMeleeAttackGoal extends MeleeAttackGoal
{
    protected final AbstractActivableEntity activableGoalOwner;

    public ActiveMeleeAttackGoal(AbstractActivableEntity entityIn, double speedIn, boolean useLongMemory)
    {
        super(entityIn, speedIn, useLongMemory);
        this.activableGoalOwner = entityIn;
    }

    //Returns whether the EntityAIBase should begin execution.
    @Override public boolean canUse() {return this.activableGoalOwner.isActive() && super.canUse();}

    //Returns whether an in-progress EntityAIBase should continue executing
    @Override public boolean canContinueToUse() {return this.activableGoalOwner.isActive() && super.canContinueToUse();}
}
