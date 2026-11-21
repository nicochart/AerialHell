package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

//follows the target from afar, straight to it (move control, no path finding : will hit obstacles), always looking at it
//walks toward the target once it is farther than startFollowDistance, stops once closer than stopFollowDistance (no stop & go around a single distance)
public class DirectFollowTargetGoal extends Goal
{
    protected final PathfinderMob goalOwner;
    protected final double speedModifier;
    private final double startFollowDistance, stopFollowDistance; //horizontal distances between the goal owner and its target
    private boolean isWalking;

    public DirectFollowTargetGoal(PathfinderMob goalOwner, double speedModifier, double startFollowDistance, double stopFollowDistance)
    {
        this.goalOwner = goalOwner;
        this.speedModifier = speedModifier;
        this.startFollowDistance = startFollowDistance;
        this.stopFollowDistance = stopFollowDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override public boolean canUse() {return this.isValidTarget(this.goalOwner.getTarget());}
    @Override public boolean canContinueToUse() {return this.canUse();}

    protected boolean isValidTarget(@Nullable LivingEntity target)
    {
        if (target == null || !target.isAlive()) {return false;}
        if (EntityHelper.isCreaOrSpecPlayer(target)) {return false;}
        return true;
    }

    @Override public void start() {this.isWalking = false;}

    @Override public void stop() {this.stopWalking();}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        @Nullable LivingEntity target = this.goalOwner.getTarget();
        if (target == null) {return;}
        this.goalOwner.getLookControl().setLookAt(target);

        double distanceSqr = this.horizontalDistanceToSqr(target);
        if (this.isWalking && distanceSqr < this.stopFollowDistance * this.stopFollowDistance) {this.stopWalking();}
        else if (!this.isWalking && distanceSqr > this.startFollowDistance * this.startFollowDistance) {this.isWalking = true;}

        if (this.isWalking) {this.moveToward(target);}
    }

    //called each tick while walking. the move control wanted position needs to be set again each tick : the goal owner stops as soon as it is not
    protected void moveToward(LivingEntity target)
    {
        //this.goalOwner.getNavigation().moveTo(target, this.speedModifier); //vanilla pathfinding, costs too much for big entities
        this.goalOwner.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), this.speedModifier); //straight to the target
    }

    protected void stopWalking()
    {
        this.isWalking = false;
        this.goalOwner.getNavigation().stop(); //only needed with path finding (see moveToward)
    }

    private double horizontalDistanceToSqr(LivingEntity target)
    {
        double dx = target.getX() - this.goalOwner.getX(), dz = target.getZ() - this.goalOwner.getZ();
        return dx * dx + dz * dz;
    }
}
