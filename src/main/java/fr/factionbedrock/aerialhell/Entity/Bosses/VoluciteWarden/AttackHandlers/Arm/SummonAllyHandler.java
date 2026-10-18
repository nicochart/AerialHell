package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonAllyGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSummonAllyHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;

import java.util.List;

public class SummonAllyHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmSummonAllyGoal goal;
    public final ArmsSummonAllyHandler globalHandler;
    public final List<Phase> attackSequence;

    public SummonAllyHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmSummonAllyGoal goal, ArmsSummonAllyHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(arm);
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    public boolean canTrigger() {return true;}
}
