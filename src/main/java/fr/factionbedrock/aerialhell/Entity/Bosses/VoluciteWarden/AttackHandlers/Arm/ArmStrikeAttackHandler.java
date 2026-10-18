package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmStrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsStrikeAttackHandler;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmStrikeAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmStrikeAttackGoal goal;
    public final ArmsStrikeAttackHandler globalHandler;
    public final List<Phase> attackSequence;

    public ArmStrikeAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmStrikeAttackGoal goal, ArmsStrikeAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(arm);
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    public boolean canTrigger() {return this.getHandPart() != null;}

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
