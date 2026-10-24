package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmLightningAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsLightningAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;

import java.util.List;

public class ArmLightningAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmLightningAttackGoal goal;
    public final ArmsLightningAttackHandler globalHandler;
    public final List<Phase> attackSequence;

    private int arcTicks = 0;
    private float arcProgress = 0.0F; //0 = arc start (hand in front of the warden), 1 = arc end (hand outside)

    public ArmLightningAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmLightningAttackGoal goal, ArmsLightningAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(this);
    }

    public boolean isRightArm() {return this.arm.getFirst().isRightArm;}

    public float getArcProgress() {return this.arcProgress;}

    public void resetArc()
    {
        this.arcTicks = 0;
        this.arcProgress = 0.0F;
    }

    //called each tick of the arc (action phase) : moves arc target forward and strikes lightnings
    public void tickArc()
    {
        this.arcTicks++;
        this.arcProgress = Math.min(1.0F, (float) this.arcTicks / ArmsLightningAttackHandler.ARC_DURATION);

        this.goal.spawnHandSparks();
        if (this.arcProgress < 1.0F && this.arcTicks % ArmsLightningAttackHandler.LIGHTNING_INTERVAL == 0) {this.goal.strikeLightning();}
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    public boolean canTrigger() {return true;}
}
