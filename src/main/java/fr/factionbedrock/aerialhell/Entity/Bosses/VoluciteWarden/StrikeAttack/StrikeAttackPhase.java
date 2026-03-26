package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

import net.minecraft.world.phys.Vec3;

public class StrikeAttackPhase
{
    private final StrikeAttackPhaseType type;
    private final Vec3 relativeTargetPos;
    private final double speed;
    private final int requiredTicksAtTarget;

    private int ticksAtTarget = 0;

    public StrikeAttackPhase(StrikeAttackPhaseType type, Vec3 relativeTargetPos, double speed, int requiredTicksAtTarget)
    {
        this.type = type;
        this.relativeTargetPos = relativeTargetPos;
        this.speed = speed;
        this.requiredTicksAtTarget = requiredTicksAtTarget;
    }

    public Vec3 getRelativeTargetPos()
    {
        return this.relativeTargetPos;
    }

    public double getSpeed() {return this.speed;}
    public void reset() {this.ticksAtTarget = 0;}

    public void tick(Vec3 currentRelativePos, float tolerance)
    {
        Vec3 target = getRelativeTargetPos();
        if (currentRelativePos.distanceTo(target) <= tolerance) {this.ticksAtTarget++;}
        else {this.ticksAtTarget = 0;}
    }

    public boolean isFinished()
    {
        return this.ticksAtTarget >= this.requiredTicksAtTarget;
    }

    public StrikeAttackPhaseType getType() {return this.type;}
}
