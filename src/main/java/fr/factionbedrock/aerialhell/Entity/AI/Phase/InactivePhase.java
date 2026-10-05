package fr.factionbedrock.aerialhell.Entity.AI.Phase;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class InactivePhase extends Phase
{
    public InactivePhase() {super(PhaseType.INACTIVE, () -> Vec3.ZERO, 1.0D, 1);}

    @Override public void tick(Goal sourceGoal, Vec3 currentUnrotatedRelativePos, float distanceOffsetTolerance) {}

    @Override public boolean isFinished() {return false;}
}
