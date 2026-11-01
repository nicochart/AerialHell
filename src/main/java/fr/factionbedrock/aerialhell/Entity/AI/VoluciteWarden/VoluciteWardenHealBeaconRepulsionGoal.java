package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.world.entity.ai.goal.Goal;

//heal beacon attack (see VoluciteWardenCoreRepulsionGoal) : the beacon prepares for preparationDuration ticks (attacking state), then repulses nearby entities.
//after afterRepulsionDuration ticks, the goal ends and the beacon goes back to active state (see VoluciteWardenHealBeaconEntity.onRepulsionGoalEnd)
//triggered by the beacon each time it loses 25% of its max health
public class VoluciteWardenHealBeaconRepulsionGoal extends Goal
{
    public static final int PREPARATION_DURATION = 60;
    public static final int AFTER_REPULSION_DURATION = 10;

    public final VoluciteWardenHealBeaconEntity goalOwner;
    private final int preparationDuration;
    private final int afterRepulsionDuration;
    private boolean active = false;
    private int ticks = 0;

    public VoluciteWardenHealBeaconRepulsionGoal(VoluciteWardenHealBeaconEntity entity, int preparationDuration, int afterRepulsionDuration)
    {
        this.goalOwner = entity;
        this.preparationDuration = preparationDuration;
        this.afterRepulsionDuration = afterRepulsionDuration;
    }

    @Override public boolean canUse() {return this.isActive();}

    @Override public boolean canContinueToUse() {return this.isActive();}

    @Override public void start()
    {
        this.ticks = 0;
        this.goalOwner.setBeaconState(VoluciteWardenHealBeaconEntity.ATTACKING);
    }

    @Override public void stop()
    {
        this.active = false;
        this.goalOwner.onRepulsionGoalEnd();
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.isActive()) {return;}

        this.ticks++;
        if (this.ticks == this.preparationDuration) {this.goalOwner.repulseNearbyEntities();}
        else if (this.ticks >= this.preparationDuration + this.afterRepulsionDuration) {this.active = false;}
    }

    public boolean isActive() {return this.active;}

    public boolean trigger() //return true if the attack is successfully triggered
    {
        if (this.isActive()) {return false;}
        this.active = true;
        return true;
    }

    public void cancel() {this.active = false;} //goal will stop (if started)
}
