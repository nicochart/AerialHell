package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SegmentBeamTargetManager
{
    private final VoluciteWardenEntity warden;
    private final Predicate<Entity> targetPredicate;
    private final Map<LivingEntity, TargetAssignment> targetPool = new HashMap<>();

    private int scanCooldown = 0;
    private static final int SCAN_INTERVAL = 200; //10 seconds
    private static final int SEGMENT_TARGET_CHECK_INTERVAL = 10; //each segment checks (or searches) its target every 10 ticks

    public SegmentBeamTargetManager(VoluciteWardenEntity warden, Predicate<Entity> targetPredicate) {this.warden = warden; this.targetPredicate = targetPredicate;}

    public void tick()
    {
        if (--this.scanCooldown <= 0)
        {
            this.scanForTargets();
            this.scanCooldown = SCAN_INTERVAL;
        }

        //validating and cleaning existing targets
        this.validateAndCleanTargets();

        if (this.warden.isRightArmBeaming())
        {
            this.assignTargetsForArm(this.warden.getRightArm());
        }
        if (this.warden.isLeftArmBeaming())
        {
            this.assignTargetsForArm(this.warden.getLeftArm());
        }
    }

    //heavy scan (updating the list of potential targets)
    private void scanForTargets()
    {
        List<LivingEntity> foundEntities = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(this.warden, VoluciteWardenArmSegmentEntity.MAX_BEAM_LENGTH, targetPredicate);

        //forgetting unassigned entities that left the scan area
        this.targetPool.entrySet().removeIf(entry -> entry.getValue().isEmpty() && !foundEntities.contains(entry.getKey()));

        for (LivingEntity entity : foundEntities)
        {
            this.targetPool.putIfAbsent(entity, new TargetAssignment());
        }
    }

    private void validateAndCleanTargets()
    {
        Iterator<Map.Entry<LivingEntity, TargetAssignment>> iterator = this.targetPool.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<LivingEntity, TargetAssignment> entry = iterator.next();
            LivingEntity target = entry.getKey();

            if (!targetPredicate.test(target))
            {
                TargetAssignment duo = entry.getValue();
                //managing target loss on segment
                if (duo.getRightSegment() != null) duo.getRightSegment().setTarget(null);
                if (duo.getLeftSegment() != null) duo.getLeftSegment().setTarget(null);

                iterator.remove();
            }
        }
    }

    private void assignTargetsForArm(List<VoluciteWardenEntity.ArmPartInfo> arm)
    {
        boolean isRightArm = arm.getFirst().isRightArm;
        for (VoluciteWardenEntity.ArmPartInfo armPartInfo : arm)
        {
            if (armPartInfo.getPart() == null || !(armPartInfo.getPart().getSelf() instanceof VoluciteWardenArmSegmentEntity segment)) {continue;}
            //checks use raycasts : spread over ticks, staggered by segment index
            if ((this.warden.tickCount + armPartInfo.segmentIndex) % SEGMENT_TARGET_CHECK_INTERVAL != 0) {continue;}

            LivingEntity currentTarget = segment.getTarget();
            if (currentTarget != null)
            {
                //if has valid target
                if (this.canSegmentHit(segment, currentTarget)) {continue;}
                //else (behind a wall or out of beam range) : releasing it, so that another segment can take it
                this.releaseSegmentTarget(segment, currentTarget, isRightArm);
            }

            LivingEntity bestTarget = null;

            for (Map.Entry<LivingEntity, TargetAssignment> entry : this.targetPool.entrySet())
            {
                if (entry.getValue().canAccept(isRightArm) && this.canSegmentHit(segment, entry.getKey()))
                {
                    bestTarget = entry.getKey();
                    entry.getValue().assignSegment(isRightArm, segment);
                    break;
                }
            }

            segment.setTarget(bestTarget); //null target = fallback beam pattern
        }
    }

    //the target must be within beam range and visible from the segment beam start (no block in between)
    private boolean canSegmentHit(VoluciteWardenArmSegmentEntity segment, LivingEntity target)
    {
        if (!this.targetPredicate.test(target)) {return false;}

        Vec3 beamStart = segment.getBeamStartPos();
        Vec3 aimedPos = target.position().add(segment.getBeamTargetPosOffset(target)); //same pos as the one followed by the beam
        if (beamStart.distanceToSqr(aimedPos) > segment.getMaxBeamLength() * segment.getMaxBeamLength()) {return false;}

        return segment.level().clip(new ClipContext(beamStart, aimedPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, segment)).getType() == HitResult.Type.MISS;
    }

    private void releaseSegmentTarget(VoluciteWardenArmSegmentEntity segment, LivingEntity target, boolean isRightArm)
    {
        TargetAssignment assignment = this.targetPool.get(target);
        if (assignment != null && assignment.getSegment(isRightArm) == segment) {assignment.removeSegment(isRightArm);}
        segment.setTarget(null);
    }

    //call when beam goal is disabled
    public void clearArmTargets(boolean isRightArm)
    {
        for (TargetAssignment duo : this.targetPool.values())
        {
            duo.removeSegment(isRightArm);
        }
    }
}