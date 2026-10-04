package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.SummonAllyAttack;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonAllyGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSummonAllyHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SummonAllyHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmSummonAllyGoal goal;
    public final ArmsSummonAllyHandler globalHandler;
    public final List<SummonAllyPhase> attackSequence;
    private int inactiveTicks;
    private int cooldown = 40;

    public SummonAllyHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmSummonAllyGoal goal, ArmsSummonAllyHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(arm);
    }

    @Nullable private SummonAllyHandler getOtherArmHandler() {return this.globalHandler.getOtherArmHandler(this);}

    public void tick()
    {
        if (this.warden.tickCount % 200 == 0) {this.cooldown = this.warden.getRandom().nextInt(200);}

        if (!this.warden.level().isClientSide())
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

    private boolean canTrigger()
    {
        return this.warden.getTarget() != null && !this.otherIsInPreparePhase() && !this.warden.isArmActive(this.arm);
    }

    private boolean otherIsInPreparePhase()
    {
        @Nullable SummonAllyHandler other = this.getOtherArmHandler();
        if (other == null) {return false;}

        return other.goal.getPhaseType() == SummonAllyPhaseType.PREPARE;
    }
}