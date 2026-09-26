package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack;

import net.minecraft.world.phys.Vec3;

public class ArmBeamAttackInactivePhase extends ArmBeamAttackPhase
{
    public ArmBeamAttackInactivePhase() {super(ArmBeamAttackPhaseType.INACTIVE, () -> Vec3.ZERO, 1.0D, 1);}

    public void tick(Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance) {}

    public boolean isFinished() {return false;}
}
