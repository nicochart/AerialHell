package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.SummonAllyAttack.SummonAllyPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.SummonAllyAttack.SummonAllyPhaseType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmSummonAllyGoal extends Goal
{
    public final VoluciteWardenEntity goalOwner;
    public final Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm;
    private final float distanceOffsetTolerance;
    private int phaseIndex;
    private Vec3 cachedUnrotatedRelativePos;

    public VoluciteWardenArmSummonAllyGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance)
    {
        this.goalOwner = entity;
        this.arm = arm;
        this.distanceOffsetTolerance = distanceOffsetTolerance;
        this.phaseIndex = 0;
    }

    public List<SummonAllyPhase> getPhases() {return this.goalOwner.armsSummonAllyHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    public SummonAllyPhase getCurrentPhase() {return this.getPhase(this.phaseIndex);}
    public SummonAllyPhase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    public SummonAllyPhase getPhase(int phaseIndex) {return this.getPhases().get(phaseIndex);}

    public SummonAllyPhaseType getPhaseType() {return this.getCurrentPhase().getType();}

    @Override public boolean canUse()
    {
        if (this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmBeamAttack()) {this.trigger();}
        return this.isActive();
    }

    @Override public boolean canContinueToUse() {return this.isActive();}

    @Override public void start() {this.startFirstPhase();}
    @Override public void stop() {}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.goalOwner.getTarget() == null) {this.skipToRecoveryPhase();}
        if (!this.guideIsValid()) {this.skipToInactivePhase(); return;}
        if (this.getGuide() != null && this.cachedUnrotatedRelativePos == null)
        {
            this.cachedUnrotatedRelativePos = this.goalOwner.toUnrotatedRelativePos(this.getGuide().position());
        }

        this.setSegmentsPos();
        this.setMasterLookAt();

        this.updateGuideUnrotatedRelativePos();

        this.getCurrentPhase().tick(this, this.goalOwner, this.getCachedUnrotatedRelativePos(), this.distanceOffsetTolerance);
        if (this.getCurrentPhase().isFinished())
        {
            SummonAllyPhaseType currentType = this.getCurrentPhase().getType();
            this.startNextPhase();
            SummonAllyPhaseType nextType = this.getCurrentPhase().getType();
            this.goalOwner.armsSummonAllyHandler.onSummonAllyPhaseFinish(currentType, nextType, this.arm.get().getFirst().isRightArm);
        }
    }

    public boolean guideIsValid() {return this.getGuide() != null && this.getGuide().isAlive();}

    @Nullable public LivingEntity getGuide()
    {
        return this.arm.get().getLast().getPart() != null ? this.arm.get().getLast().getPart().getSelf() : null;
    }

    protected void setMasterLookAt()
    {
        Vec3 lookTarget = this.getLookAtTarget();
        if (lookTarget != null)
        {
            this.goalOwner.getSelf().getLookControl().setLookAt(lookTarget.x, lookTarget.y, lookTarget.z, 30.0F, 30.0F);
        }
    }

    @Nullable public Vec3 getLookAtTarget() {return this.goalOwner.getTarget() != null ? this.goalOwner.getTarget().position() : null;}

    public boolean isActive() {return this.getPhaseType() != SummonAllyPhaseType.INACTIVE;}

    public boolean trigger()
    {
        if (this.isActive()) {return false;}
        else
        {
            this.startFirstPhase();
            return this.isActive();
        }
    }

    public double getDistanceToTarget() { return this.getCurrentPhase().getDistanceToTarget(this.getCachedUnrotatedRelativePos()); }

    public void skipToInactivePhase() { this.skipToPhaseType(SummonAllyPhaseType.INACTIVE); }
    public void skipToRecoveryPhase() { this.skipToPhaseType(SummonAllyPhaseType.RECOVERY); }

    public void skipToPhaseType(SummonAllyPhaseType phaseType)
    {
        if (this.getCurrentPhase().getType() == phaseType) {return;}

        int previousPhaseIndex = this.phaseIndex;
        int newPhaseIndex = this.getNextPhaseIndex(previousPhaseIndex);
        while (this.getPhase(newPhaseIndex).getType() != phaseType && newPhaseIndex != previousPhaseIndex)
        {
            newPhaseIndex = this.getNextPhaseIndex(newPhaseIndex);
        }
        if (newPhaseIndex != previousPhaseIndex) {this.startPhase(newPhaseIndex);}
    }

    private void startFirstPhase() {this.startPhase(0);}
    private void startNextPhase() {this.startPhase(this.getNextPhaseIndex());}
    private void startPhase(int phaseIndex)
    {
        this.phaseIndex = phaseIndex;
        this.getCurrentPhase().reset();
    }

    private int getNextPhaseIndex() {return this.getNextPhaseIndex(this.phaseIndex);}

    private int getNextPhaseIndex(int phaseIndex)
    {
        int nextPhaseIndex = phaseIndex + 1;
        return nextPhaseIndex >= this.getPhases().size() ? 0 : nextPhaseIndex;
    }

    private int getPreviousPhaseIndex()
    {
        int previousPhaseIndex = this.phaseIndex - 1;
        return previousPhaseIndex < 0 ? this.getPhases().size() - 1 : previousPhaseIndex;
    }

    public Vec3 getCachedUnrotatedRelativePos() {return this.cachedUnrotatedRelativePos != null ? this.cachedUnrotatedRelativePos : this.getPreviousPhase().getUnrotatedRelativeTargetPos();}

    public Vec3 updateGuideUnrotatedRelativePos()
    {
        Vec3 previousURPos = this.getCachedUnrotatedRelativePos();
        SummonAllyPhase phase = this.getCurrentPhase();
        Vec3 newUnrotatedRelativePos = calculateGuideUnrotatedRelativePosDuringArmAttack(previousURPos, phase.getUnrotatedRelativeTargetPos(), phase.getSpeed());
        this.cachedUnrotatedRelativePos = newUnrotatedRelativePos;
        return newUnrotatedRelativePos;
    }

    public static Vec3 calculateGuideUnrotatedRelativePosDuringArmAttack(Vec3 unrotatedRelativeCurrentPos, Vec3 unrotatedRelativeTargetPos, double maxSpeed)
    {
        Vec3 direction = unrotatedRelativeTargetPos.subtract(unrotatedRelativeCurrentPos);
        double distance = direction.length();
        if (distance < 0.0001F) {return unrotatedRelativeCurrentPos;}

        double speed = Math.min(maxSpeed, distance);
        Vec3 movement = direction.normalize().scale(speed);

        return unrotatedRelativeCurrentPos.add(movement);
    }

    public void finishSummonAlly()
    {
        if (this.goalOwner.level() instanceof ServerLevel level)
        {
            Entity entity = AerialHellEntities.VOLUCITE_GOLEM.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            if (entity instanceof VoluciteGolemEntity golem)
            {
                Vec3 spawnPos = this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());
                golem.snapTo(spawnPos.x, spawnPos.y - 1.0D, spawnPos.z, this.goalOwner.getYRot(), 0.0F);
                level.addFreshEntity(golem);

                level.playSound(null, BlockPos.containing(spawnPos), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0F, 4.0F);
            }
        }
    }

    /* ------------------------------------------ */
    /* ---------- Setting segments pos ---------- */
    /* ------------------------------------------ */

    //copies of methods from VoluciteWardenStrikeAttackGoal

    protected void setSegmentsPos()
    {
        int totalSegments = this.arm.get().size();
        Vec3 armStartPos = this.arm.get().getFirst().getUnrotatedRelativePositionOffset();
        Vec3 armEndPos = this.getCachedUnrotatedRelativePos();
        double curveStrengthFactor = this.getPhaseType() == SummonAllyPhaseType.RECOVERY ? this.calculateRecoveryCurveStrengthFactor(this.getDistanceToTarget()) : 1.0D;

        for (VoluciteWardenEntity.ArmPartInfo partInfo : this.arm.get())
        {
            PartEntity part = partInfo.getPart();
            if (part != null)
            {
                this.setPartPos(part, armStartPos, armEndPos, curveStrengthFactor, partInfo.getSegmentIndex(), totalSegments);
                this.setPartRot(part, armStartPos, armEndPos);
            }
        }
    }

    private void setPartPos(@NotNull PartEntity part, Vec3 armStartPos, Vec3 armEndPos, double curveStrengthFactor, int segmentIndex, int totalSegments)
    {
        Vec3 armPos = this.interpolateArmPos(armStartPos, armEndPos, curveStrengthFactor, segmentIndex, totalSegments);
        part.getSelf().setPos(this.goalOwner.fromUnrotatedRelativeToLevelPos(armPos));

        if (segmentIndex == 1)
        {
            double deltaZ = ((part.getSelf().tickCount & 1) == 0 ? 0.003D : -0.003D);
            part.setPos(part.getX(), part.getY(), part.getZ() + deltaZ);
        }
    }

    private void setPartRot(@NotNull PartEntity part, Vec3 armStartPos, Vec3 armEndPos)
    {
        float relativeYRot = (this.arm.get().getFirst().isRightArm ? 1 : -1) * computeRelativeYRot(armStartPos, armEndPos, 1.0F, 1.0F);
        float yRot = this.goalOwner.toLevelYRot(relativeYRot);
        part.getSelf().setYRot(yRot);
        part.getSelf().yBodyRot = yRot;
        part.getSelf().yHeadRot = yRot;
    }

    private float computeRelativeYRot(Vec3 start, Vec3 end, float yWeight, float zWeight)
    {
        Vec3 delta = end.subtract(start);
        double length = delta.length();
        if (length < 1e-4) return 0.0F;

        Vec3 dir = delta.scale(1.0 / length);
        float yContribution = (float)Math.max(0, dir.y) * 180.0F;
        float zContribution = (float)(dir.z * 90.0F);

        float totalWeight = yWeight + zWeight;
        if (totalWeight <= 0.0001F) return 0.0F;

        float yPart = yContribution * yWeight;
        float zPart = zContribution * zWeight;

        return (yPart + zPart) / totalWeight;
    }

    private double calculateRecoveryCurveStrengthFactor(double distanceToTarget)
    {
        int maxFactorDistance = 4;
        return Mth.clamp(distanceToTarget / maxFactorDistance, 0.0F, 1.0F);
    }

    private Vec3 interpolateArmPos(Vec3 start, Vec3 end, double curveStrengthFactor, int index, int totalSegments)
    {
        int rightLeftfactor = this.arm.get().getFirst().isRightArm ? 1 : -1;
        double progress = (double)(index - 1) / (totalSegments - 1);

        Vec3 armMiddle = start.add(end).scale(0.5);
        Vec3 armDir = end.subtract(start).normalize();

        double heightDiff = end.y - start.y;
        float heightDiffMaxThreshold = 14.0F;
        double factor = Mth.clamp(heightDiff / heightDiffMaxThreshold, -1.0, 1.0);
        Vec3 controlDir = new Vec3(rightLeftfactor * armDir.z, 0, rightLeftfactor * factor * armDir.x).normalize();

        double curveStrength = curveStrengthFactor * switch (this.getCurrentPhase().getType())
        {
            case INACTIVE -> 0.0D;
            case PREPARE -> 8.0D * Mth.abs((float)factor);
            case SUMMON -> 2.0D;
            case RECOVERY -> 5.0D;
        };

        Vec3 control = armMiddle.add(controlDir.scale(curveStrength));
        return quadraticBezier(start, control, end, progress);
    }

    private Vec3 quadraticBezier(Vec3 start, Vec3 control, Vec3 end, double progress)
    {
        double remainingProgress = 1.0 - progress;
        Vec3 startWeight = start.scale(remainingProgress * remainingProgress);
        Vec3 controlWeight = control.scale(2 * remainingProgress * progress);
        Vec3 endWeight = end.scale(progress * progress);

        return startWeight.add(controlWeight).add(endWeight);
    }
}