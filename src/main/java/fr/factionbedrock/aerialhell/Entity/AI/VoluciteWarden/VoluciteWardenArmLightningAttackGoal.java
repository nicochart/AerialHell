package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteLightningEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmLightningAttackGoal extends VoluciteWardenArmGoal
{
    private static final float LIGHTNING_DAMAGE = 5.0F;
    private static final float LIGHTNING_HIT_RADIUS = 1.5F; //entities this close to the lightning impact are hurt
    private static final float TARGET_HORIZONTAL_RANGE = 12.0F; //enemies below the hand, in this horizontal radius, can be targeted
    private static final float TARGET_MAX_DEPTH = 40.0F; //max distance below the hand
    private static final float GROUND_LIGHTNING_SPREAD = 5.0F; //random horizontal offset of the lightning on the ground, when there is no target

    public VoluciteWardenArmLightningAttackGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsLightningAttackHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger() {return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmLightningAttack();}

    public Vec3 getHandLevelPos() {return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());}

    public void spawnHandSparks()
    {
        if (this.goalOwner.level() instanceof ServerLevel level)
        {
            Vec3 handPos = this.getHandLevelPos();
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK, handPos.x, handPos.y, handPos.z, 8, 1.0D, 1.0D, 1.0D, 0.15D);
        }
    }

    //lightning from the hand to the nearest enemy below the hand. if there is none, lightning on the ground below the hand
    //lightning stops at the first block on its way
    public void strikeLightning()
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return;}

        Vec3 handPos = this.getHandLevelPos();
        @Nullable LivingEntity target = this.getNearestLightningTarget(level, handPos);
        Vec3 aimedPos = target != null ? target.getBoundingBox().getCenter() : handPos.add(randomHorizontalOffset(this.goalOwner.getRandom(), GROUND_LIGHTNING_SPREAD)).add(0.0D, -TARGET_MAX_DEPTH, 0.0D);

        BlockHitResult blockHit = level.clip(new ClipContext(handPos, aimedPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, CollisionContext.empty()));
        if (target == null && blockHit.getType() == HitResult.Type.MISS) {return;} //no target and no ground below the hand
        Vec3 impactPos = blockHit.getType() == HitResult.Type.MISS ? aimedPos : blockHit.getLocation();

        this.spawnLightning(level, handPos, impactPos);
        this.hurtEntitiesNearImpact(level, impactPos);

        RandomSource random = this.goalOwner.getRandom();
        level.playSound(null, impactPos.x, impactPos.y, impactPos.z, SoundEvents.LIGHTNING_BOLT_IMPACT, this.goalOwner.getSoundSource(), 2.0F, 1.2F + random.nextFloat() * 0.4F);
        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, impactPos.x, impactPos.y, impactPos.z, 12, 0.3D, 0.3D, 0.3D, 0.3D);
    }

    @Nullable private LivingEntity getNearestLightningTarget(ServerLevel level, Vec3 handPos)
    {
        AABB searchBox = new AABB(handPos.x - TARGET_HORIZONTAL_RANGE, handPos.y - TARGET_MAX_DEPTH, handPos.z - TARGET_HORIZONTAL_RANGE, handPos.x + TARGET_HORIZONTAL_RANGE, handPos.y, handPos.z + TARGET_HORIZONTAL_RANGE);
        return level.getEntitiesOfClass(LivingEntity.class, searchBox, target -> this.isValidLightningTarget(target) && horizontalDistanceSqr(target.position(), handPos) <= TARGET_HORIZONTAL_RANGE * TARGET_HORIZONTAL_RANGE)
                .stream().min(Comparator.comparingDouble(target -> target.distanceToSqr(handPos))).orElse(null);
    }

    private void spawnLightning(ServerLevel level, Vec3 startPos, Vec3 endPos)
    {
        VoluciteLightningEntity lightning = AerialHellEntities.VOLUCITE_LIGHTNING.get().create(level, EntitySpawnReason.TRIGGERED);
        if (lightning == null) {return;}

        lightning.setPos(startPos.x, startPos.y, startPos.z);
        lightning.setEndPos(endPos);
        level.addFreshEntity(lightning);
    }

    private void hurtEntitiesNearImpact(ServerLevel level, Vec3 impactPos)
    {
        AABB hitBox = new AABB(impactPos, impactPos).inflate(LIGHTNING_HIT_RADIUS);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, hitBox, this::isValidLightningTarget))
        {
            entity.hurtServer(level, this.goalOwner.damageSources().lightningBolt(), LIGHTNING_DAMAGE);
        }
    }

    private boolean isValidLightningTarget(LivingEntity entity)
    {
        return !entity.is(AerialHellTags.Entities.VOLUCITE) && entity.isAlive() && !entity.is(this.goalOwner) && this.goalOwner.canAttack(entity);
    }

    private static Vec3 randomHorizontalOffset(RandomSource random, float maxOffset)
    {
        return new Vec3(Mth.nextFloat(random, -maxOffset, maxOffset), 0.0D, Mth.nextFloat(random, -maxOffset, maxOffset));
    }

    private static double horizontalDistanceSqr(Vec3 pos1, Vec3 pos2)
    {
        double dx = pos1.x - pos2.x, dz = pos1.z - pos2.z;
        return dx * dx + dz * dz;
    }
}
