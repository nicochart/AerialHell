package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VoluciteWardenArmBeamAttackGoal extends Goal
{
    public final VoluciteWardenEntity goalOwner;
    private final boolean isRightArm;
    private final float distanceOffsetTolerance; //used to avoid float imprecision. goal can skip to next phase only if distance to target < distanceOffsetTolerance for some (parametrized) ticks
    private int phaseIndex;
    private Vec3 cachedUnrotatedRelativePos; //guide pos

    public VoluciteWardenArmBeamAttackGoal(VoluciteWardenEntity entity, float distanceOffsetTolerance, boolean isRightArm)
    {
        this.goalOwner = entity;
        this.isRightArm = isRightArm;
        this.distanceOffsetTolerance = distanceOffsetTolerance;
        this.phaseIndex = 0;
    }

    public List<ArmBeamAttackPhase> getPhases() {return this.goalOwner.getArmBeamAttackSequence(this.isRightArm);}

    public ArmBeamAttackPhase getCurrentPhase() {return this.getPhase(this.phaseIndex);}
    public ArmBeamAttackPhase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    public ArmBeamAttackPhase getPhase(int phaseIndex) {return this.getPhases().get(phaseIndex);}

    public ArmBeamAttackPhaseType getPhaseType() {return this.getCurrentPhase().getType();}

    @Override public boolean canUse()
    {
        if (this.goalOwner.canUseArmBeamAttack() && this.goalOwner.shouldTriggerArmBeamAttack()) {this.trigger();}
        return this.isActive();
    }

    @Override public boolean canContinueToUse() {return this.isActive();}

    @Override public void start() {this.startFirstPhase();}
    @Override public void stop() {}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.goalOwner.canUseArmBeamAttack()) {this.skipToRecoveryPhase();}
        if (!this.guideIsValid()) {this.skipToInactivePhase(); return;}
        if (this.getGuide() != null && this.cachedUnrotatedRelativePos == null) {this.cachedUnrotatedRelativePos = this.goalOwner.toUnrotatedRelativePos(this.getGuide().position());}

        this.setSegmentsPos();
        this.setMasterLookAt();

        this.updateGuideUnrotatedRelativePos();

        this.getCurrentPhase().tick(this, this.goalOwner, this.getCachedUnrotatedRelativePos(), this.distanceOffsetTolerance);
        if (this.getCurrentPhase().isFinished())
        {
            ArmBeamAttackPhaseType currentType = this.getCurrentPhase().getType();
            this.startNextPhase();
            ArmBeamAttackPhaseType nextType = this.getCurrentPhase().getType();
            this.goalOwner.armsBeamAttackHandler.onArmBeamPhaseFinish(currentType, nextType, this.isRightArm);
        }
    }

    public boolean guideIsValid() {return this.getGuide() != null && this.getGuide().isAlive();}

    @Nullable public LivingEntity getGuide()
    {
        List<VoluciteWardenEntity.ArmPartInfo> arm = this.isRightArm ? this.goalOwner.getRightArm() : this.goalOwner.getLeftArm();
        return arm.getLast().getPart() != null ? arm.getLast().getPart().getSelf() : null;
    }

    protected void setMasterLookAt()
    {
        Vec3 lookTarget = this.getLookAtTarget();
        if (lookTarget != null)
        {
            this.goalOwner.getSelf().getLookControl().setLookAt(lookTarget.x, lookTarget.y, lookTarget.z, 30.0F, 30.0F);
        }
    }

    @Nullable public Vec3 getLookAtTarget()
    {
        if (this.goalOwner.getTarget() != null)
        {
            return this.goalOwner.getTarget().position();
        }
        else {return null;}
    }

    public boolean isBeaming() {return this.getPhaseType() == ArmBeamAttackPhaseType.BEAM;}
    public boolean isActive() {return this.getPhaseType() != ArmBeamAttackPhaseType.INACTIVE;}
    public boolean trigger() //return true if the attack sequence is successfully triggered
    {
        if (this.isActive()) {return false;}
        else
        {
            this.startFirstPhase();
            return this.isActive();
        }
    }

    public double getDistanceToTarget()
    {
        return this.getCurrentPhase().getDistanceToTarget(this.getCachedUnrotatedRelativePos());
    }

    public void skipToInactivePhase() {this.skipToPhaseType(ArmBeamAttackPhaseType.INACTIVE);}
    public void skipToRecoveryPhase() {this.skipToPhaseType(ArmBeamAttackPhaseType.RECOVERY);}

    public void skipToPhaseType(ArmBeamAttackPhaseType phaseType)
    {
        if (this.getCurrentPhase().getType() == phaseType) {return;}
        //disabling beam
        if (phaseType != ArmBeamAttackPhaseType.BEAM) {this.goalOwner.armsBeamAttackHandler.setArmBeam(this.isRightArm, false);}

        int previousPhaseIndex = this.phaseIndex;
        int newPhaseIndex = this.getNextPhaseIndex(previousPhaseIndex);
        while (this.getPhase(newPhaseIndex).getType() != phaseType && newPhaseIndex != previousPhaseIndex) //newPhaseIndex != previousPhaseIndex to avoid infinite cycle if there is no phase of this type in sequence (should never happen except for inactive phase)
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

    public Vec3 updateGuideUnrotatedRelativePos() //(actual) position of arm extremity (last segment)
    {
        Vec3 previousURPos = this.getCachedUnrotatedRelativePos();
        ArmBeamAttackPhase phase = this.getCurrentPhase();
        Vec3 newUnrotatedRelativePos = calculateGuideUnrotatedRelativePosDuringArmBeamAttack(previousURPos, phase.getUnrotatedRelativeTargetPos(), phase.getSpeed());
        this.cachedUnrotatedRelativePos = newUnrotatedRelativePos;
        return newUnrotatedRelativePos;
    }

    public static Vec3 calculateGuideUnrotatedRelativePosDuringArmBeamAttack(Vec3 unrotatedRelativeCurrentPos, Vec3 unrotatedRelativeTargetPos, double maxSpeed)
    {
        Vec3 direction = unrotatedRelativeTargetPos.subtract(unrotatedRelativeCurrentPos);
        double distance = direction.length();
        if (distance < 0.0001F) {return unrotatedRelativeCurrentPos;}

        double speed = Math.min(maxSpeed, distance);
        Vec3 movement = direction.normalize().scale(speed);

        Vec3 newPos = unrotatedRelativeCurrentPos.add(movement);
        return new Vec3(newPos.x, newPos.y, newPos.z);
    }

    public void finishArmBeamAttack() //at the end of beaming
    {

    }

    /* ------------------------------------------ */
    /* ---------- Setting segments pos ---------- */
    /* ------------------------------------------ */

    //copies of methods from VoluciteWardenStrikeAttackGoal

    protected void setSegmentsPos()
    {
        List<VoluciteWardenEntity.ArmPartInfo> arm = this.isRightArm ? this.goalOwner.getRightArm() : this.goalOwner.getLeftArm();
        int totalSegments = arm.size();
        Vec3 armStartPos = arm.getFirst().getUnrotatedRelativePositionOffset();
        Vec3 armEndPos = this.getCachedUnrotatedRelativePos();
        double curveStrengthFactor = this.getPhaseType() == ArmBeamAttackPhaseType.RECOVERY ? this.calculateRecoveryCurveStrengthFactor(this.getDistanceToTarget()) : 1.0D;

        for (VoluciteWardenEntity.ArmPartInfo partInfo : arm)
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

        if (segmentIndex == 1) //Tiny hack to correct the first segment's rotation when it's not moving. Little movement makes client immediately updates visual rot.
        {
            double deltaZ = ((part.getSelf().tickCount & 1) == 0 ? 0.003D : -0.003D);
            part.setPos(part.getX(), part.getY(), part.getZ() + deltaZ);
        }
    }

    private void setPartRot(@NotNull PartEntity part, Vec3 armStartPos, Vec3 armEndPos)
    {
        float relativeYRot = (this.isRightArm ? 1 : -1) * computeRelativeYRot(armStartPos, armEndPos, 1.0F, 1.0F);
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
        int rightLeftfactor = this.isRightArm ? 1 : -1;
        double progress = (double)(index - 1) / (totalSegments - 1);

        Vec3 armMiddle = start.add(end).scale(0.5);

        Vec3 armDir = end.subtract(start).normalize();

        double heightDiff = end.y - start.y;
        float heightDiffMaxThreshold = 14.0F;
        double factor = Mth.clamp(heightDiff / heightDiffMaxThreshold, -1.0, 1.0); // negative if arm down, positive if arm up. 0 if arm is horizontal. (absolute) starting to decrease if diff is <= heightDiffMaxThreshold
        Vec3 controlDir = new Vec3(rightLeftfactor * armDir.z, 0, rightLeftfactor * factor * armDir.x).normalize(); //orthogonal direction

        double curveStrength = curveStrengthFactor * switch (this.getCurrentPhase().getType())
        {
            case INACTIVE -> 0.0D;
            case PREPARE -> 8.0D * Mth.abs((float)factor);
            case BEAM -> 2.0D;
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