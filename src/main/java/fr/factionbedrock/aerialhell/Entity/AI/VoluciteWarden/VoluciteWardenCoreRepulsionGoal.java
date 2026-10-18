package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.entity.ai.goal.Goal;

//core attracts nearby entities for attractionDuration ticks, then repulses them for repulsionDuration ticks (see CoreRepulsionHandler)
//drag / repulse is handled by CoreRepulsionHandler and particles by the warden, depending on CORE_DRAG_OR_REPULSE_GOAL_STATUS (set here)
public class VoluciteWardenCoreRepulsionGoal extends Goal
{
    public final VoluciteWardenEntity goalOwner;
    private final int attractionDuration;
    private final int repulsionDuration;
    private boolean active = false;
    private int ticks = 0;

    public VoluciteWardenCoreRepulsionGoal(VoluciteWardenEntity entity, int attractionDuration, int repulsionDuration)
    {
        this.goalOwner = entity;
        this.attractionDuration = attractionDuration;
        this.repulsionDuration = repulsionDuration;
    }

    @Override public boolean canUse()
    {
        if (this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerCoreRepulsion()) {this.trigger();}
        return this.isActive();
    }

    @Override public boolean canContinueToUse() {return this.isActive();}

    @Override public void start()
    {
        this.ticks = 0;
        this.goalOwner.setCoreDragOrRepulseGoalStatus(VoluciteWardenEntity.CORE_ATTRACTING_STATUS);
    }

    @Override public void stop()
    {
        this.active = false;
        this.goalOwner.setCoreDragOrRepulseGoalStatus(VoluciteWardenEntity.CORE_NEUTRAL_STATUS);
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.isActive()) {return;}

        this.ticks++;
        if (this.ticks == this.attractionDuration) //end of attraction, start of repulsion
        {
            this.goalOwner.setCoreDragOrRepulseGoalStatus(VoluciteWardenEntity.CORE_REPULSING_STATUS);
            this.goalOwner.coreRepulsionHandler.onRepulsionStart();
        }
        else if (this.ticks >= this.attractionDuration + this.repulsionDuration) //end of repulsion
        {
            this.goalOwner.setCoreDragOrRepulseGoalStatus(VoluciteWardenEntity.CORE_NEUTRAL_STATUS);
            this.active = false;
        }
    }

    public boolean isActive() {return this.active;}

    public boolean trigger() //return true if the attack is successfully triggered
    {
        if (this.isActive()) {return false;}
        this.active = true;
        return true;
    }
}
