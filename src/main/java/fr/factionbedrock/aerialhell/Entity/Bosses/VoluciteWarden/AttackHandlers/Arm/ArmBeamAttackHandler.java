package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;

import java.util.List;

public class ArmBeamAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmBeamAttackGoal goal;
    public final ArmsBeamAttackHandler globalHandler;
    public final List<Phase> attackSequence;

    public ArmBeamAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmBeamAttackGoal goal, ArmsBeamAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(arm);
    }

    public void enableBeam()
    {
        for (VoluciteWardenEntity.ArmPartInfo armSegmentInfo : this.arm)
        {
            if (armSegmentInfo.getPart() != null && armSegmentInfo.getPart().getSelf() instanceof VoluciteWardenArmSegmentEntity armSegment)
            {
                armSegment.queueBeamEnable(armSegmentInfo.segmentIndex * 5);
            }
        }
    }

    public void disableBeam()
    {
        for (VoluciteWardenEntity.ArmPartInfo armSegmentInfo : this.arm)
        {
            if (armSegmentInfo.getPart() != null && armSegmentInfo.getPart().getSelf() instanceof VoluciteWardenArmSegmentEntity armSegment)
            {
                armSegment.disableBeam();
            }
        }
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    public boolean canTrigger() {return true;}
}
