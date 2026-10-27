package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.PhaseGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public abstract class VoluciteWardenArmGoal extends Goal implements PhaseGoal
{
    public final VoluciteWardenEntity goalOwner;
    public final Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm;
    public final PhaseInfo phaseInfo;

    public VoluciteWardenArmGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance)
    {
        this.goalOwner = entity;
        this.arm = arm;
        this.phaseInfo = new PhaseInfo(distanceOffsetTolerance, this.goalOwner);
    }

    @Override public Goal getSelf() {return this;}

    @Override public PhaseInfo getPhaseInfo() {return this.phaseInfo;}

    @Override public boolean canUse() {return this.canUsePhaseGoal();}
    @Override public boolean canContinueToUse() {return this.canContinueToUsePhaseGoal();}

    @Override public void start() {this.onStart();}
    @Override public void stop() {this.onStop();}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick() {this.tickPhase();}

    @Override public void setGuidePos() {this.setSegmentsPos();}

    @Override public LivingEntity getGuide()
    {
        PartEntity part = this.arm.get().getLast().getPart();
        return part != null ? part.getSelf() : null;
    }

    /* ------------------------------------------ */
    /* ---------- Setting segments pos ---------- */
    /* ------------------------------------------ */

    protected void setSegmentsPos()
    {
        int totalSegments = this.arm.get().size();
        Vec3 armStartPos = this.arm.get().getFirst().getUnrotatedRelativePositionOffset();
        Vec3 armEndPos = this.getCachedUnrotatedRelativePos();
        double distanceToTarget = this.getDistanceToTarget();
        double curveStrengthFactor = this.getPhaseType() == PhaseType.RECOVERY ? this.calculateRecoveryCurveStrengthFactor(distanceToTarget) : 1.0D;
        Vec3 curveControlPos = this.computeCurveControlPos(armStartPos, armEndPos, curveStrengthFactor); //same for every segment

        for (VoluciteWardenEntity.ArmPartInfo partInfo : this.arm.get())
        {
            PartEntity part = partInfo.getPart();
            if (part != null)
            {
                this.setPartPos(part, armStartPos, curveControlPos, armEndPos, partInfo.getSegmentIndex(), totalSegments);
                this.setPartRot(part, armStartPos, armEndPos);
            }
        }
    }

    private void setPartPos(@NotNull PartEntity part, Vec3 armStartPos, Vec3 curveControlPos, Vec3 armEndPos, int segmentIndex, int totalSegments)
    {
        double progress = (double)(segmentIndex - 1) / (totalSegments - 1);
        Vec3 armPos = this.quadraticBezier(armStartPos, curveControlPos, armEndPos, progress);
        part.getSelf().setPos(this.goalOwner.fromUnrotatedRelativeToLevelPos(armPos));

        if (segmentIndex == 1) //Tiny hack to correct the first segment's rotation when it's not moving. Little movement makes client immediately updates visual rot.
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

    //quadratic bezier control point : arm middle, moved orthogonally to the arm
    private Vec3 computeCurveControlPos(Vec3 start, Vec3 end, double curveStrengthFactor)
    {
        Vec3 armMiddle = start.add(end).scale(0.5);

        double heightDiff = end.y - start.y;
        float heightDiffMaxThreshold = 14.0F;
        double heightFactor = Mth.clamp(heightDiff / heightDiffMaxThreshold, -1.0, 1.0); // negative if arm down, positive if arm up. 0 if arm is horizontal. (absolute) starting to decrease if diff is <= heightDiffMaxThreshold

        double curveStrength = curveStrengthFactor * this.getCurveStrength(this.getPhaseType(), heightFactor);
        return armMiddle.add(this.getCurveDirection(start, end, heightFactor).scale(curveStrength));
    }

    //override for a goal-specific curve (start and end : unrotated relative positions of the shoulder and the hand)
    protected Vec3 getCurveDirection(Vec3 armStart, Vec3 armEnd, double heightFactor)
    {
        Vec3 armDir = armEnd.subtract(armStart).normalize();
        int rightLeftfactor = this.arm.get().getFirst().isRightArm ? 1 : -1;
        return new Vec3(rightLeftfactor * armDir.z, 0, rightLeftfactor * heightFactor * armDir.x).normalize(); //orthogonal direction
    }

    //override for a goal-specific curve
    protected double getCurveStrength(PhaseType phaseType, double heightFactor)
    {
        return switch (phaseType)
        {
            case INACTIVE -> 0.0D;
            case PREPARE -> 8.0D * Mth.abs((float) heightFactor);
            case ACTION -> 2.0D;
            case RECOVERY -> 5.0D;
        };
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
