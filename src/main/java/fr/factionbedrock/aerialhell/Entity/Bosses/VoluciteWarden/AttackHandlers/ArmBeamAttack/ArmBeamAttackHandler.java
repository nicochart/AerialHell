package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmBeamAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmBeamAttackGoal goal;
    public final ArmsBeamAttackHandler globalHandler;
    private int inactiveTicks;
    private int cooldown = 40;

    public ArmBeamAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmBeamAttackGoal goal, ArmsBeamAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
    }

    @Nullable private ArmBeamAttackHandler getOtherArmHandler()
    {
        return this.globalHandler.getOtherArmHandler(this);
    }

    public void tick()
    {
        if (warden.tickCount % 200 == 0) {this.cooldown = warden.getRandom().nextInt(200);}

        if (!warden.level().isClientSide())
        {
            boolean isBeaming = this.goal.isActive();

            if (isBeaming) {this.inactiveTicks = 0;}
            else
            {
                this.inactiveTicks++;
                if (this.inactiveTicks > this.cooldown && this.canTrigger())
                {
                    this.goal.trigger();
                }
            }
        }
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

    private boolean canTrigger()
    {
        return this.warden.getTarget() != null && !this.otherIsInPreparePhase() && !this.warden.isArmActive(this.arm);
    }

    private boolean otherIsInPreparePhase()
    {
        @Nullable ArmBeamAttackHandler other = this.getOtherArmHandler();
        if (other == null) {return false;}

        return other.goal.getPhaseType() == ArmBeamAttackPhaseType.PREPARE;
    }
}