package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmSweepAttackGoal extends VoluciteWardenArmGoal
{
    private static final float SWEEP_DAMAGE = 12.0F;
    private static final float KNOCKBACK_STRENGTH = 2.5F; //horizontal, in the direction of the arm movement
    private static final float KNOCKBACK_Y = 0.5F;

    //collision data arrays, allocated once and reused between ticks
    private final IntSet hitEntityIds = new IntOpenHashSet(); //each entity is hit only once per sweep
    private AABB[] previousSegmentBoxes = new AABB[0];
    private Vec3[] previousSegmentPositions = new Vec3[0];
    private AABB[] sweptSegmentBoxes = new AABB[0];
    private Vec3[] segmentMovements = new Vec3[0];

    public VoluciteWardenArmSweepAttackGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsSweepAttackHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger() {return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmSweepAttack();}

    public Vec3 getHandLevelPos() {return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());}

    public float getHandHeight() {return this.getGuide() != null ? this.getGuide().getBbHeight() : 0.0F;}

    /* ----------------------------------------- */
    /* ---------- Targeting (once) ------------- */
    /* ----------------------------------------- */

    //feet height and mean horizontal distance (from the warden) of the biggest group of targets standing at a similar height
    public record SweepTargetInfo(double y, double horizontalDistance) {}

    //called once, at attack start. only targets with feet between minFeetRelativeY and maxFeetRelativeY (relative to the warden) are considered
    @Nullable public SweepTargetInfo findMajorityTargetsInfo(float horizontalRange, float minFeetRelativeY, float maxFeetRelativeY, float heightTolerance)
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return null;}

        Vec3 wardenPos = this.goalOwner.position();
        double minFeetY = wardenPos.y + minFeetRelativeY, maxFeetY = wardenPos.y + maxFeetRelativeY;
        AABB searchBox = new AABB(wardenPos.x - horizontalRange, minFeetY, wardenPos.z - horizontalRange, wardenPos.x + horizontalRange, maxFeetY, wardenPos.z + horizontalRange);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, searchBox, entity -> entity.getY() >= minFeetY && entity.getY() <= maxFeetY && this.isValidSweepTarget(entity));
        if (targets.isEmpty()) {return null;}

        //biggest group : target with the most other targets in [y - heightTolerance, y + heightTolerance] (few targets : n² is fine)
        LivingEntity groupReference = targets.getFirst();
        int biggestGroupSize = 0;
        for (LivingEntity target : targets)
        {
            int groupSize = 0;
            for (LivingEntity other : targets) {if (Math.abs(other.getY() - target.getY()) <= heightTolerance) {groupSize++;}}
            if (groupSize > biggestGroupSize) {biggestGroupSize = groupSize; groupReference = target;}
        }

        double ySum = 0.0D, horizontalDistanceSum = 0.0D;
        for (LivingEntity target : targets)
        {
            if (Math.abs(target.getY() - groupReference.getY()) <= heightTolerance)
            {
                ySum += target.getY();
                horizontalDistanceSum += target.position().subtract(wardenPos).horizontalDistance();
            }
        }
        return new SweepTargetInfo(ySum / biggestGroupSize, horizontalDistanceSum / biggestGroupSize);
    }

    /* ----------------------------------------- */
    /* ---------- Sweep collisions ------------- */
    /* ----------------------------------------- */

    public void resetSweep()
    {
        int segmentCount = this.arm.get().size();
        if (this.previousSegmentBoxes.length != segmentCount)
        {
            this.previousSegmentBoxes = new AABB[segmentCount];
            this.previousSegmentPositions = new Vec3[segmentCount];
            this.sweptSegmentBoxes = new AABB[segmentCount];
            this.segmentMovements = new Vec3[segmentCount];
        }
        Arrays.fill(this.previousSegmentBoxes, null);
        Arrays.fill(this.previousSegmentPositions, null);
        this.hitEntityIds.clear();
    }

    public void playSweepSound()
    {
        Vec3 handPos = this.getHandLevelPos();
        this.goalOwner.level().playSound(null, handPos.x, handPos.y, handPos.z, SoundEvents.PLAYER_ATTACK_SWEEP, this.goalOwner.getSoundSource(), 4.0F, 0.5F);
    }

    //hurts and knocks back entities touched by any arm segment, since last tick
    //one entity query per tick (in the box containing the whole arm movement), then a few box intersections per found entity
    public void tickSweepCollisions()
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return;}

        List<VoluciteWardenEntity.ArmPartInfo> arm = this.arm.get();
        @Nullable AABB searchBox = null;
        for (int i = 0; i < arm.size(); i++)
        {
            PartEntity part = arm.get(i).getPart();
            if (part == null) {this.sweptSegmentBoxes[i] = null; continue;}

            AABB box = part.getSelf().getBoundingBox();
            Vec3 pos = part.getSelf().position();

            //box covering the segment movement since last tick (segments can move a few blocks per tick : avoids passing through entities)
            this.sweptSegmentBoxes[i] = this.previousSegmentBoxes[i] != null ? box.minmax(this.previousSegmentBoxes[i]) : box;
            this.segmentMovements[i] = this.previousSegmentPositions[i] != null ? pos.subtract(this.previousSegmentPositions[i]) : Vec3.ZERO;
            this.previousSegmentBoxes[i] = box;
            this.previousSegmentPositions[i] = pos;

            searchBox = searchBox == null ? this.sweptSegmentBoxes[i] : searchBox.minmax(this.sweptSegmentBoxes[i]);
        }
        if (searchBox == null) {return;}

        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, searchBox, candidate -> !this.hitEntityIds.contains(candidate.getId()) && this.isValidSweepTarget(candidate)))
        {
            AABB entityBox = entity.getBoundingBox();
            for (int i = 0; i < arm.size(); i++)
            {
                if (this.sweptSegmentBoxes[i] != null && this.sweptSegmentBoxes[i].intersects(entityBox))
                {
                    this.hitEntity(level, entity, this.segmentMovements[i]);
                    break;
                }
            }
        }
    }

    private void hitEntity(ServerLevel level, LivingEntity entity, Vec3 segmentMovement)
    {
        this.hitEntityIds.add(entity.getId());

        Vec3 horizontalMovement = new Vec3(segmentMovement.x, 0.0D, segmentMovement.z);
        //knockback in the direction of the arm movement (or away from the warden, if the touching segment did not move)
        Vec3 knockbackDirection = horizontalMovement.lengthSqr() > 1.0E-4D ? horizontalMovement.normalize() : new Vec3(entity.getX() - this.goalOwner.getX(), 0.0D, entity.getZ() - this.goalOwner.getZ()).normalize();

        entity.hurtServer(level, this.goalOwner.damageSources().mobAttack(this.goalOwner), SWEEP_DAMAGE);
        entity.push(knockbackDirection.x * KNOCKBACK_STRENGTH, KNOCKBACK_Y, knockbackDirection.z * KNOCKBACK_STRENGTH);
        entity.syncVelocity = true; //sends knockback to the client, even if damage was blocked (player movement is client side)

        level.sendParticles(ParticleTypes.SWEEP_ATTACK, entity.getX(), entity.getY(0.5D), entity.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, this.goalOwner.getSoundSource(), 2.0F, 0.6F);
    }

    private boolean isValidSweepTarget(LivingEntity entity)
    {
        return !entity.is(AerialHellTags.Entities.VOLUCITE) && entity.isAlive() && !entity.is(this.goalOwner) && !EntityHelper.isCreaOrSpecPlayer(entity) && this.goalOwner.canAttack(entity);
    }
}
