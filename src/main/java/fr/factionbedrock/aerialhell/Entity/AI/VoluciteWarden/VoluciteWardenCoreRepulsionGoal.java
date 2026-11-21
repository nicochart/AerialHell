package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.entity.ai.goal.Goal;

//core charges for chargeDuration ticks (very light repulsion of nearby entities), then strongly repulses them for repulsionDuration ticks (see CoreRepulsionHandler)
//repulsion is handled by CoreRepulsionHandler, depending on CORE_REPULSION_GOAL_STATUS (set here)
public class VoluciteWardenCoreRepulsionGoal extends Goal
{
    public final VoluciteWardenEntity goalOwner;
    private final int chargeDuration;
    private final int repulsionDuration;
    private boolean active = false;
    private int ticks = 0;

    public VoluciteWardenCoreRepulsionGoal(VoluciteWardenEntity entity, int chargeDuration, int repulsionDuration)
    {
        this.goalOwner = entity;
        this.chargeDuration = chargeDuration;
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
        this.goalOwner.setCoreRepulsionGoalStatus(VoluciteWardenEntity.CORE_CHARGING_STATUS);
    }

    @Override public void stop() {this.interrupt();}

    public void interrupt() //repulsion stops immediately
    {
        this.active = false;
        this.goalOwner.setCoreRepulsionGoalStatus(VoluciteWardenEntity.CORE_NEUTRAL_STATUS);
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.isActive()) {return;}

        this.ticks++;
        if (this.ticks == this.chargeDuration) //end of the charge, start of the repulsion
        {
            this.goalOwner.setCoreRepulsionGoalStatus(VoluciteWardenEntity.CORE_REPULSING_STATUS);
            this.goalOwner.coreRepulsionHandler.onRepulsionStart();
        }
        else if (this.ticks >= this.chargeDuration + this.repulsionDuration) //end of the repulsion
        {
            this.goalOwner.setCoreRepulsionGoalStatus(VoluciteWardenEntity.CORE_NEUTRAL_STATUS);
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
