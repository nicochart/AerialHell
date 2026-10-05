package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmBeamAttackGoal extends VoluciteWardenArmGoal
{
    public VoluciteWardenArmBeamAttackGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsBeamAttackHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    //can automatically trigger (external of handler)
    @Override public boolean shouldTrigger()
    {
        return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmBeamAttack();
    }
}