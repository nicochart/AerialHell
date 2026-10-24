package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmLightningAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.ArmLightningAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsLightningAttackHandler
{
    public static final int ARC_DURATION = 60; //ticks
    public static final int LIGHTNING_INTERVAL = 3; //ticks between two lightnings during the arc

    //horizontal arc, around the shoulder (unrotated relative positions). angle 0 = in front of the shoulder, positive angle = outward
    private static final float ARC_PIVOT_X = 6.5F;
    private static final float ARC_HEIGHT = 20.5F; //core height
    private static final float ARC_RADIUS = 17.0F;
    private static final float ARC_START_ANGLE = -15.0F; //slightly toward the middle, in front of the core
    private static final float ARC_END_ANGLE = 105.0F; //slightly behind the shoulder

    private final VoluciteWardenEntity warden;

    private final ArmLightningAttackHandler rightArmHandler;
    private final ArmLightningAttackHandler leftArmHandler;

    public ArmsLightningAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmLightningAttackGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmLightningAttackGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new ArmLightningAttackHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new ArmLightningAttackHandler(warden, leftArm, leftGoal, this);
    }

    public boolean isClientSide() {return this.warden.level().isClientSide();}

    @Nullable public ArmLightningAttackHandler getOtherArmHandler(ArmLightningAttackHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public boolean canTrigger(boolean isRightArm) {return (isRightArm ? this.rightArmHandler : this.leftArmHandler).canTrigger();}

    public List<Phase> getAttackSequence(boolean isRightArm)
    {
        return isRightArm ? this.rightArmHandler.attackSequence : this.leftArmHandler.attackSequence;
    }

    //arm handler is needed (instead of arm) because arc target pos depends on its arc progress
    public List<Phase> createAttackSequence(ArmLightningAttackHandler armHandler)
    {
        int sideFactor = armHandler.isRightArm() ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos(sideFactor), 1.0D, 1)
                        .onStart((goal) -> armHandler.resetArc()), //resetting before action phase : action target pos is read before action phase start
                new Phase(PhaseType.PREPARE, () -> this.getRelativeArcPos(sideFactor, 0.0F), 1.0D, 15)
                        .onTick((goal) -> {if (goal instanceof VoluciteWardenArmLightningAttackGoal lightningGoal) {lightningGoal.spawnHandSparks();}}),
                new Phase(PhaseType.ACTION, () -> this.getRelativeArcPos(sideFactor, armHandler.getArcProgress()), 1.5D, 5)
                        .onTick((goal) -> armHandler.tickArc()),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    private Vec3 getRelativePreparePos(int sideFactor) {return new Vec3(sideFactor * 16.0F, 14.0F, 6.0F);}

    //linear progression (no easing) : arc target must move more than goal distanceOffsetTolerance each tick, else action phase would end too early (target considered reached)
    private Vec3 getRelativeArcPos(int sideFactor, float arcProgress)
    {
        float angle = (float) Math.toRadians(Mth.lerp(arcProgress, ARC_START_ANGLE, ARC_END_ANGLE));
        return new Vec3(sideFactor * (ARC_PIVOT_X + ARC_RADIUS * Mth.sin(angle)), ARC_HEIGHT, ARC_RADIUS * Mth.cos(angle));
    }

    private Vec3 getRelativeRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}
}
