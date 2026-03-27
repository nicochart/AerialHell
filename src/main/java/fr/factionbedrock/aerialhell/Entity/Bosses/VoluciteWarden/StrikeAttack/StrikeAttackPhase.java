package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class StrikeAttackPhase
{
    private final StrikeAttackPhaseType type;
    private final Supplier<Vec3> relativeTargetPosSupplier;
    private final double speed;
    private final int requiredTicksAtTarget;

    private int ticksAtTarget = 0;
    private static float TARGET_DISTANCE_OFFSET_TOLERANCE = 0.5F;

    public StrikeAttackPhase(StrikeAttackPhaseType type, Supplier<Vec3> unrotatedRelativeTargetPosSupplier, double speed, int requiredTicksAtTarget)
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
    public void reset() {this.ticksAtTarget = 0;}

    public void tick(Vec3 currentUnrotatedRelativePos)
    {
        if (this.isAtTargetPos(currentUnrotatedRelativePos)) {this.ticksAtTarget++;}
        else {this.ticksAtTarget = 0;}
    }

    public boolean isAtTargetPos(Vec3 currentRelativePos)
    {
        Vec3 target = getUnrotatedRelativeTargetPos();
        return currentRelativePos.distanceTo(target) <= TARGET_DISTANCE_OFFSET_TOLERANCE;
    }

    public boolean isFinished()
    {
        return this.ticksAtTarget >= this.requiredTicksAtTarget;
    }

    public StrikeAttackPhaseType getType() {return this.type;}
}
