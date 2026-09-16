package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.ArmBeamAttack;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class ArmBeamAttackPhase
{
    private final ArmBeamAttackPhaseType type;
    private final Supplier<Vec3> relativeTargetPosSupplier;
    private final double speed;
    private final int requiredTicksAtTarget;

    private int ticksAtTarget = 0;
    private boolean reachedTarget = false;

    public ArmBeamAttackPhase(ArmBeamAttackPhaseType type, Supplier<Vec3> unrotatedRelativeTargetPosSupplier, double speed, int requiredTicksAtTarget)
    {
        this.type = type;
        this.relativeTargetPosSupplier = unrotatedRelativeTargetPosSupplier;
        this.speed = speed;
        this.requiredTicksAtTarget = requiredTicksAtTarget;
    }

    public Vec3 getUnrotatedRelativeTargetPos()
    {
        return this.relativeTargetPosSupplier.get();
    }

    public double getSpeed() {return this.speed;}
    public void reset()
    {
        this.ticksAtTarget = 0;
        this.reachedTarget = false;
    }

    public void tick(VoluciteWardenArmBeamAttackGoal sourceGoal, VoluciteWardenEntity sourceEntity, Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance)
    {
        if (this.isAtTargetPos(currentUnrotatedRelativePos, distanceOffsetTolerance))
        {
            if (this.ticksAtTarget == 1 && !this.reachedTarget)
            {
                //waiting 1 tick at target pos because of client interpolation. (Else it appears as if the interaction occurs before the contact with target).
                if (this.getType() == ArmBeamAttackPhaseType.BEAM) {sourceGoal.finishArmBeamAttack();}
                this.reachedTarget = true;
            }
            this.ticksAtTarget++;
        }
        else {this.ticksAtTarget = 0;}
    }

    public boolean isAtTargetPos(Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance)
    {
        return this.getDistanceToTarget(currentUnrotatedRelativePos) <= distanceOffsetTolerance;
    }

    public double getDistanceToTarget(Vec3 currentUnrotatedRelativePos)
    {
        Vec3 target = this.getUnrotatedRelativeTargetPos();
        return currentUnrotatedRelativePos.distanceTo(target);
    }

    public boolean isFinished() {return this.ticksAtTarget >= this.requiredTicksAtTarget;}

    public ArmBeamAttackPhaseType getType() {return this.type;}
}
