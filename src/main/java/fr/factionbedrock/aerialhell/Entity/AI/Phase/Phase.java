package fr.factionbedrock.aerialhell.Entity.AI.Phase;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Phase
{
    private final PhaseType type;
    private final Supplier<Vec3> relativeTargetPosSupplier;
    private final double speed;
    private final int requiredTicksAtTarget;

    private Consumer<Goal> onStart = goal -> {};
    private Consumer<Goal> onTick = goal -> {};
    private Consumer<Goal> onTargetReached = goal -> {};
    private Consumer<Goal> onEnd = goal -> {};
    private Consumer<Goal> onForceEnd = goal -> {};

    private int ticksAtTarget = 0;
    private boolean reachedTarget = false;
    private boolean started = false;
    private boolean ended = false;

    public Phase(PhaseType type, Supplier<Vec3> unrotatedRelativeTargetPosSupplier, double speed, int requiredTicksAtTarget)
    {
        this.type = type;
        this.relativeTargetPosSupplier = unrotatedRelativeTargetPosSupplier;
        this.speed = speed;
        this.requiredTicksAtTarget = requiredTicksAtTarget;
    }

    public Phase onStart(@NotNull Consumer<Goal> action)
    {
        this.onStart = this.onStart.andThen(action);
        return this;
    }

    public Phase onTick(@NotNull Consumer<Goal> action)
    {
        this.onTick = this.onTick.andThen(action);
        return this;
    }

    public Phase onTargetReached(@NotNull Consumer<Goal> action)
    {
        this.onTargetReached = this.onTargetReached.andThen(action);
        return this;
    }

    public Phase onEnd(@NotNull Consumer<Goal> action)
    {
        this.onEnd = this.onEnd.andThen(action);
        return this;
    }

    //when forceEnd(..) is called from exterior (goal, ...)
    public Phase onForceEnd(@NotNull Consumer<Goal> action)
    {
        this.onForceEnd = this.onForceEnd.andThen(action);
        return this;
    }

    public Vec3 getUnrotatedRelativeTargetPos() {return this.relativeTargetPosSupplier.get();}
    public double getSpeed() {return this.speed;}
    public PhaseType getType() {return this.type;}

    //called from exterior (goal, ...) on phase start
    public void reset()
    {
        this.ticksAtTarget = 0;
        this.reachedTarget = false;
        this.started = false;
        this.ended = false;
    }

    public void forceEnd(Goal sourceGoal)
    {
        this.ended = true;
        this.onForceEnd.accept(sourceGoal);
    }

    /* ------------------------------------------ */
    /* ------------- Lifecycle Logic ------------ */
    /* ------------------------------------------ */

    private void start(Goal sourceGoal)
    {
        this.started = true;
        this.onStart.accept(sourceGoal);
    }

    public void tick(Goal sourceGoal, Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance)
    {
        if (this.isFinished()) {return;} //phase is already finished

        //phase start
        if (!this.started) {this.start(sourceGoal);}
        this.onTick.accept(sourceGoal);

        if (this.isAtTargetPos(currentUnrotatedRelativePos, distanceOffsetTolerance))
        {
            //phase reach target
            //waiting 1 tick at target pos because of client interpolation. (Else it appears as if the interaction occurs before the contact with target).
            //except if this.requiredTicksAtTarget = 0 : executing onReachedTarget right now because phase will end
            if ((this.ticksAtTarget == 1 && !this.reachedTarget) || this.requiredTicksAtTarget == 0)
            {
                this.onTargetReached.accept(sourceGoal);
                this.reachedTarget = true;
            }

            //phase end
            if (this.canFinish() && !this.ended) {this.end(sourceGoal);}

            this.ticksAtTarget++;
        }
        else {this.ticksAtTarget = 0;}
    }

    private void end(Goal sourceGoal)
    {
        this.ended = true;
        this.onEnd.accept(sourceGoal);
    }

    /* ------------------------------------------ */
    /* ------------------------------------------ */
    /* ------------------------------------------ */

    public boolean isAtTargetPos(Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance)
    {
        return this.getDistanceToTarget(currentUnrotatedRelativePos) <= distanceOffsetTolerance;
    }

    public double getDistanceToTarget(Vec3 currentUnrotatedRelativePos)
    {
        return currentUnrotatedRelativePos.distanceTo(this.getUnrotatedRelativeTargetPos());
    }

    private boolean canFinish() {return this.ticksAtTarget >= this.requiredTicksAtTarget;}

    public boolean isStarted() {return this.started;}
    public boolean reachedTarget() {return this.reachedTarget;}
    public boolean isFinished() {return this.ended;}
}