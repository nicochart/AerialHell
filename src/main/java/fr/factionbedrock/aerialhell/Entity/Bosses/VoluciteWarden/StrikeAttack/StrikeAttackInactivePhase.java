package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

import net.minecraft.world.phys.Vec3;

public class StrikeAttackInactivePhase extends StrikeAttackPhase
{
    public StrikeAttackInactivePhase()
    {
        super(StrikeAttackPhaseType.INACTIVE, () -> Vec3.ZERO, 1.0D, 1);
    }

    public void tick(Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance) {}

    public boolean isFinished() {return false;}
}
