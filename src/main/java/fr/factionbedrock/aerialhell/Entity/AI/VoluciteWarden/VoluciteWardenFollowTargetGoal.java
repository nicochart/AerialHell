package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.DirectFollowTargetGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;

//free warden only (see VoluciteWardenEntity isAnchored) : follows its target from afar, straight to it (see DirectFollowTargetGoal). no obstacle nor void check for now
//no path finding : vanilla path finding is not made for such a big entity (the path starts from the warden center block, but its nodes are handled as the corner of the warden footprint : the whole path is shifted by half its width)
public class VoluciteWardenFollowTargetGoal extends DirectFollowTargetGoal
{
    public static final double START_FOLLOW_DISTANCE = 24.0D, STOP_FOLLOW_DISTANCE = 16.0D; //horizontal distances between the warden and its target

    private final VoluciteWardenEntity warden;

    public VoluciteWardenFollowTargetGoal(VoluciteWardenEntity warden, double speedModifier)
    {
        super(warden, speedModifier, START_FOLLOW_DISTANCE, STOP_FOLLOW_DISTANCE);
        this.warden = warden;
    }

    @Override public boolean canUse() {return !this.warden.isAnchored() && !this.warden.isInDeadOrDyingPhase() && super.canUse();}

    //cheaper movement, but the boss does not walk straight to the player, and spins on itself while moving (needs the light navigation commented in VoluciteWardenEntity, and LivingEntity import)
    //private int pathRecalculationCooldown;
    //@Override protected void moveToward(LivingEntity target)
    //{
    //    if (--this.pathRecalculationCooldown > 0) {return;}
    //    this.pathRecalculationCooldown = 40; //path finding is costly for such a big entity, even with the light navigation
    //    this.goalOwner.getNavigation().moveTo(target, this.speedModifier);
    //}
}
