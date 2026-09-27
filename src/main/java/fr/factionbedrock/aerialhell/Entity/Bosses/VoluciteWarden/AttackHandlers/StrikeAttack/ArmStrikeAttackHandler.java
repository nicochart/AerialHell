package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.StrikeAttack;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmStrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsStrikeAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmStrikeAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmStrikeAttackGoal goal;
    public final ArmsStrikeAttackHandler globalHandler;
    public final List<StrikeAttackPhase> attackSequence;
    private int inactiveTicks;
    private int cooldown = 40;

    public ArmStrikeAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmStrikeAttackGoal goal, ArmsStrikeAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(arm);
    }

    @Nullable private ArmStrikeAttackHandler getOtherArmHandler()
    {
        return this.globalHandler.getOtherArmHandler(this);
    }

    public void tick()
    {
        if (this.warden.tickCount % 200 == 0) {this.cooldown = this.warden.getRandom().nextInt(200);}

        if (!this.warden.level().isClientSide())
        {
            boolean isStriking = this.goal.isActive();

            if (isStriking) {this.inactiveTicks = 0;}
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

    public boolean canTrigger()
    {
        return this.warden.getTarget() != null && !this.otherIsInWindupPhase() && !this.warden.isArmActive(this.arm) && this.getHandPart() != null;
    }

    private boolean otherIsInWindupPhase()
    {
        @Nullable ArmStrikeAttackHandler other = this.getOtherArmHandler();
        if (other == null) {return false;}

        return other.goal.getPhaseType() == StrikeAttackPhaseType.WINDUP;
    }

    @Nullable public VoluciteWardenEntity.ArmPartInfo getHandPartInfo()
    {
        return this.arm.isEmpty() ? null : this.arm.getLast();
    }

    @Nullable public Object getHandPart()
    {
        VoluciteWardenEntity.ArmPartInfo handInfo = this.getHandPartInfo();
        return handInfo != null && handInfo.getPart() != null ? handInfo.getPart().getSelf() : null;
    }
}