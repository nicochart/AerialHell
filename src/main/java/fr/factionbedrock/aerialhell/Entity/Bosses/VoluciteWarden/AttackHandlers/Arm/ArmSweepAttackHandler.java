package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSweepAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSweepAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmSweepAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmSweepAttackGoal goal;
    public final ArmsSweepAttackHandler globalHandler;
    public final List<Phase> attackSequence;

    //chosen at attack start, from targets positions
    private float sweepHeight; //hand height, relative to warden
    private float sweepRadius = ArmsSweepAttackHandler.MIN_SWEEP_RADIUS; //hand horizontal distance to warden

    private int sweepTicks = 0;
    private float sweepProgress = 0.0F; //0 = sweep start (hand on the other side), 1 = sweep end (hand on its own side)

    public ArmSweepAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmSweepAttackGoal goal, ArmsSweepAttackHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(this);
        this.sweepHeight = this.getDefaultHandHeight();
    }

    public boolean isRightArm() {return this.arm.getFirst().isRightArm;}

    //hand (last segment) default height, relative to warden. arm is too stretched if the hand goes lower
    private float getDefaultHandHeight() {return (float) this.arm.getLast().getUnrotatedRelativePositionOffset().y;}

    //hand is centered on targets bodies (feet + 0.9) : conversion between hand height and targets feet height (relative to warden)
    private float getHandHeightOffset() {return 0.9F - this.goal.getHandHeight() / 2.0F;}

    public float getSweepHeight() {return this.sweepHeight;}
    public float getSweepRadius() {return this.sweepRadius;}
    public float getSweepProgress() {return this.sweepProgress;}

    //called at attack start (only box scan of this attack) : aims at the height of the biggest group of reachable targets, and at their mean distance
    //reachable targets : the hand does not need to go lower than its default height. if there is none, the attack is cancelled
    public void startAttack()
    {
        this.sweepTicks = 0;
        this.sweepProgress = 0.0F;
        this.goal.resetSweep();

        float handHeightOffset = this.getHandHeightOffset();
        float minFeetHeight = this.getDefaultHandHeight() - handHeightOffset;
        float maxFeetHeight = ArmsSweepAttackHandler.MAX_SWEEP_HEIGHT - handHeightOffset;
        @Nullable VoluciteWardenArmSweepAttackGoal.SweepTargetInfo targetInfo = this.goal.findMajorityTargetsInfo(ArmsSweepAttackHandler.TARGETING_RANGE, minFeetHeight, maxFeetHeight, ArmsSweepAttackHandler.TARGETS_HEIGHT_TOLERANCE);
        if (targetInfo == null) {this.goal.skipToInactivePhase(); return;} //arm did not move yet

        float targetsFeetHeight = (float) (targetInfo.y() - this.warden.getY());
        this.sweepHeight = Mth.clamp(targetsFeetHeight + handHeightOffset, this.getDefaultHandHeight(), ArmsSweepAttackHandler.MAX_SWEEP_HEIGHT);
        this.sweepRadius = Mth.clamp((float) targetInfo.horizontalDistance(), ArmsSweepAttackHandler.MIN_SWEEP_RADIUS, ArmsSweepAttackHandler.MAX_SWEEP_RADIUS);
    }

    //called each tick of the sweep (action phase) : moves sweep target forward and checks collisions while the arm is moving
    public void tickSweep()
    {
        this.sweepTicks++;
        this.sweepProgress = Math.min(1.0F, (float) this.sweepTicks / ArmsSweepAttackHandler.SWEEP_DURATION);

        //+1 : guide reaches the last sweep target one tick after it is set
        if (this.sweepTicks <= ArmsSweepAttackHandler.SWEEP_DURATION + 1) {this.goal.tickSweepCollisions();}
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    //never both arms sweeping at the same time. a master target means there is at least one target to hit : targets are scanned only at attack start
    public boolean canTrigger()
    {
        @Nullable ArmSweepAttackHandler otherArmHandler = this.globalHandler.getOtherArmHandler(this);
        if (otherArmHandler != null && otherArmHandler.goal.isActive()) {return false;}

        return this.warden.getTarget() != null;
    }
}
