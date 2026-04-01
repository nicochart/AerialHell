package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.StrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class VoluciteWardenStrikeAttackGoal extends StrikeAttackGoal
{
    private final boolean isRightArm;

    public VoluciteWardenStrikeAttackGoal(VoluciteWardenEntity entity, float distanceOffsetTolerance, StrikeInfo strikeInfo, boolean isRightArm)
    {
        super(entity, distanceOffsetTolerance, strikeInfo);
        this.isRightArm = isRightArm;
    }

    private VoluciteWardenEntity getGoalOwner() {return (VoluciteWardenEntity) this.goalOwner;}

    @Override protected void setEntityUsedToStrikePos()
    {
        List<VoluciteWardenEntity.ArmPartInfo> arm = this.isRightArm ? this.getGoalOwner().getRightArm() : this.getGoalOwner().getLeftArm();
        Vec3 armStartPos = arm.getFirst().getUnrotatedRelativePositionOffset();
        Vec3 armEndPos = this.getCachedUnrotatedRelativePos();
        double curveStrengthFactor = this.getPhaseType() == StrikeAttackPhaseType.RECOVERY ? this.calculateRecoveryCurveStrengthFactor(this.getDistanceToTarget()) : 1.0D;

        for (VoluciteWardenEntity.ArmPartInfo partInfo : arm)
        {
            if (partInfo.getPart() != null)
            {
                Vec3 armPos = this.interpolateArmPos(armStartPos, armEndPos, curveStrengthFactor, partInfo.getSegmentIndex(), 7);
                partInfo.getPart().getSelf().setPos(this.getGoalOwner().fromUnrotatedRelativeToLevelPos(armPos));
            }
        }
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
            case WINDUP -> 8.0D * Mth.abs((float)factor);
            case STRIKE -> 2.0D;
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
