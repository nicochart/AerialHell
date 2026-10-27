package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSweepAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.ArmSweepAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsSweepAttackHandler
{
    public static final int SWEEP_DURATION = 16; //ticks
    private static final int PAUSE_BEFORE_SWEEP = 12; //ticks
    private static final int PAUSE_AFTER_SWEEP = 10; //ticks
    private static final double SWEEP_SPEED = 4.0D; //max hand speed (blocks/tick) : must be higher than arc length / SWEEP_DURATION, else hand would lag behind the sweep target

    //horizontal arc, around the warden vertical axis (unrotated relative positions). angle 0 = in front of the warden, positive angle = arm side
    private static final float SWEEP_START_ANGLE = -70.0F; //front, on the other side (left for right arm)
    private static final float SWEEP_END_ANGLE = 80.0F; //arm side
    public static final float MIN_SWEEP_RADIUS = 11.0F, MAX_SWEEP_RADIUS = 22.0F; //min radius : with a smaller one, hand would go through the other arm
    public static final float MAX_SWEEP_HEIGHT = 28.0F; //hand height, relative to the warden. min height is the hand default height (arm would be too stretched below)
    public static final float TARGETING_RANGE = MAX_SWEEP_RADIUS + 4.0F; //horizontal range to find targets
    public static final float TARGETS_HEIGHT_TOLERANCE = 2.5F; //targets with less height difference are considered at the same height

    private final VoluciteWardenEntity warden;

    private final ArmSweepAttackHandler rightArmHandler;
    private final ArmSweepAttackHandler leftArmHandler;

    public ArmsSweepAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmSweepAttackGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmSweepAttackGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new ArmSweepAttackHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new ArmSweepAttackHandler(warden, leftArm, leftGoal, this);
    }

    public boolean isClientSide() {return this.warden.level().isClientSide();}

    @Nullable public ArmSweepAttackHandler getOtherArmHandler(ArmSweepAttackHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public boolean canTrigger(boolean isRightArm) {return (isRightArm ? this.rightArmHandler : this.leftArmHandler).canTrigger();}

    public float getSweepProgress(boolean isRightArm) {return (isRightArm ? this.rightArmHandler : this.leftArmHandler).getSweepProgress();}

    public List<Phase> getAttackSequence(boolean isRightArm)
    {
        return isRightArm ? this.rightArmHandler.attackSequence : this.leftArmHandler.attackSequence;
    }

    //arm handler is needed (instead of arm) because sweep target pos depends on its height, radius and progress
    public List<Phase> createAttackSequence(ArmSweepAttackHandler armHandler)
    {
        int sideFactor = armHandler.isRightArm() ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos(sideFactor), 1.0D, 1)
                        .onStart((goal) -> armHandler.startAttack()), //targets scan, before sweep phases : their target pos is read before their start
                new Phase(PhaseType.PREPARE, () -> this.getRelativeSweepPos(sideFactor, armHandler, 0.0F), 1.5D, PAUSE_BEFORE_SWEEP),
                new Phase(PhaseType.ACTION, () -> this.getRelativeSweepPos(sideFactor, armHandler, armHandler.getSweepProgress()), SWEEP_SPEED, PAUSE_AFTER_SWEEP)
                        .onStart((goal) -> {if (goal instanceof VoluciteWardenArmSweepAttackGoal sweepGoal) {sweepGoal.playSweepSound();}})
                        .onTick((goal) -> armHandler.tickSweep()),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    //far enough in front of the warden : from there, the hand goes to the sweep start without going through the torso
    private Vec3 getRelativePreparePos(int sideFactor) {return new Vec3(sideFactor * 6.0F, 12.0F, 16.0F);}

    //linear progression (no easing) : sweep target must move more than goal distanceOffsetTolerance each tick, else action phase would end too early (target considered reached)
    private Vec3 getRelativeSweepPos(int sideFactor, ArmSweepAttackHandler armHandler, float sweepProgress)
    {
        float angle = (float) Math.toRadians(Mth.lerp(sweepProgress, SWEEP_START_ANGLE, SWEEP_END_ANGLE));
        float radius = armHandler.getSweepRadius();
        return new Vec3(sideFactor * radius * Mth.sin(angle), armHandler.getSweepHeight(), radius * Mth.cos(angle));
    }

    private Vec3 getRelativeRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}
}
