package fr.factionbedrock.aerialhell.Entity.Util;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteLightningEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

//volucite lightning attack : a visual lightning (VoluciteLightningEntity) from a start pos to an impact pos, hurting entities near the impact
//the aimed pos is chosen by the caller (target pos, random pos...), see strikeLightningBelow, findNearestTargetBelow, findRandomTargetAround and randomPosBelow. server side only
public class VoluciteLightningHelper
{
    //damage source (created for each lightning, from the lightning start pos), damage amount, radius around the impact, entities that can be hurt, and sound source of the impact sound
    public record LightningInfo(Function<Vec3, DamageSource> damageSourceFromStartPos, float damage, float hitRadius, Predicate<LivingEntity> canHurt, SoundSource soundSource) {}

    //lightning of a volucite mob : no damage to volucite entities (no friendly fire between volucite warden parts, for example)
    //volucite lightning damage type : caused by the attacker (by its master for a part entity, like the volucite warden head) to scale with difficulty
    //knockback away from the lightning start pos (not from the impact : a lightning aimed at a target hits its center, which would give a random knockback direction)
    public static LightningInfo voluciteMobLightningInfo(Mob attacker, float damage, float hitRadius)
    {
        Function<Vec3, DamageSource> damageSourceFromStartPos = startPos -> AerialHellDamageTypes.getDamageSource(attacker.level(), AerialHellDamageTypes.VOLUCITE_LIGHTNING, null, getTrueSource(attacker), startPos);
        return new LightningInfo(damageSourceFromStartPos, damage, hitRadius, entity -> canVoluciteMobHurt(attacker, entity), attacker.getSoundSource());
    }

    private static Entity getTrueSource(Mob attacker) {return attacker instanceof PartEntity part && part.getMaster() != null ? part.getMaster().getSelf() : attacker;}

    public static boolean canVoluciteMobHurt(LivingEntity attacker, LivingEntity entity)
    {
        return !entity.is(AerialHellTags.Entities.VOLUCITE) && entity.isAlive() && !entity.is(attacker) && attacker.canAttack(entity);
    }

    //lightning from startPos toward aimedPos, stopping at the first block on its way
    //requiresBlockHit : nothing happens if no block is reached (for random lightning, to avoid lightning ending in the air)
    //returns true if the lightning is struck
    public static boolean strikeLightning(ServerLevel level, Vec3 startPos, Vec3 aimedPos, boolean requiresBlockHit, LightningInfo info)
    {
        BlockHitResult blockHit = level.clip(new ClipContext(startPos, aimedPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, CollisionContext.empty()));
        if (requiresBlockHit && blockHit.getType() == HitResult.Type.MISS) {return false;}
        Vec3 impactPos = blockHit.getType() == HitResult.Type.MISS ? aimedPos : blockHit.getLocation();

        spawnLightning(level, startPos, impactPos);
        hurtEntitiesNearImpact(level, startPos, impactPos, info);

        RandomSource random = level.getRandom();
        level.playSound(null, impactPos.x, impactPos.y, impactPos.z, AerialHellSoundEvents.ENTITY_VOLUCITE_LIGHTNING_IMPACT.get(), info.soundSource(), 2.0F, 0.9F + random.nextFloat() * 0.2F);
        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, impactPos.x, impactPos.y, impactPos.z, 12, 0.3D, 0.3D, 0.3D, 0.3D);
        return true;
    }

    //lightning from startPos toward the center of the target, stopping at the first block on its way
    public static boolean strikeLightningAtTarget(ServerLevel level, Vec3 startPos, LivingEntity target, LightningInfo info) {return strikeLightning(level, startPos, target.getBoundingBox().getCenter(), false, info);}

    //lightning below startPos. with a targetChance probability (0.4F = 40%), toward the nearest valid target below (see findNearestTargetBelow)
    //otherwise (other lightning, or no target found), toward a random pos below (see randomPosBelow)
    //randomStrikeRequiresBlockHit : no random lightning if it doesn't reach the ground. if false, a random lightning can end in the air (maxDepth below startPos)
    //returns true if the lightning is struck
    public static boolean strikeLightningBelow(ServerLevel level, Vec3 startPos, float targetChance, Predicate<LivingEntity> isValidTarget, float targetHorizontalRange, float randomHorizontalSpread, float maxDepth, boolean randomStrikeRequiresBlockHit, LightningInfo info)
    {
        RandomSource random = level.getRandom();
        @Nullable LivingEntity target = random.nextFloat() < targetChance ? findNearestTargetBelow(level, startPos, targetHorizontalRange, maxDepth, isValidTarget) : null;
        if (target != null) {return strikeLightningAtTarget(level, startPos, target, info);}
        else {return strikeLightning(level, startPos, randomPosBelow(random, startPos, randomHorizontalSpread, maxDepth), randomStrikeRequiresBlockHit, info);}
    }

    //nearest valid target below pos, in horizontalRange (horizontal distance) and maxDepth
    @Nullable public static LivingEntity findNearestTargetBelow(ServerLevel level, Vec3 pos, float horizontalRange, float maxDepth, Predicate<LivingEntity> isValidTarget)
    {
        AABB searchBox = new AABB(pos.x - horizontalRange, pos.y - maxDepth, pos.z - horizontalRange, pos.x + horizontalRange, pos.y, pos.z + horizontalRange);
        return level.getEntitiesOfClass(LivingEntity.class, searchBox, target -> isValidTarget.test(target) && horizontalDistanceSqr(target.position(), pos) <= horizontalRange * horizontalRange)
                .stream().min(Comparator.comparingDouble(target -> target.distanceToSqr(pos))).orElse(null);
    }

    //random valid target around pos, in any direction : the center of its bounding box is at most radius blocks away from pos
    @Nullable public static LivingEntity findRandomTargetAround(ServerLevel level, Vec3 pos, float radius, Predicate<LivingEntity> isValidTarget)
    {
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos, pos).inflate(radius), target -> isValidTarget.test(target) && target.getBoundingBox().getCenter().distanceToSqr(pos) <= radius * radius);
        return targets.isEmpty() ? null : targets.get(level.getRandom().nextInt(targets.size()));
    }

    //random pos maxDepth blocks below pos, with a random horizontal offset (up to horizontalSpread on x and z)
    public static Vec3 randomPosBelow(RandomSource random, Vec3 pos, float horizontalSpread, float maxDepth)
    {
        return pos.add(Mth.nextFloat(random, -horizontalSpread, horizontalSpread), -maxDepth, Mth.nextFloat(random, -horizontalSpread, horizontalSpread));
    }

    private static void spawnLightning(ServerLevel level, Vec3 startPos, Vec3 endPos)
    {
        VoluciteLightningEntity lightning = AerialHellEntities.VOLUCITE_LIGHTNING.get().create(level, EntitySpawnReason.TRIGGERED);
        if (lightning == null) {return;}

        lightning.setPos(startPos.x, startPos.y, startPos.z);
        lightning.setEndPos(endPos);
        level.addFreshEntity(lightning);
    }

    private static void hurtEntitiesNearImpact(ServerLevel level, Vec3 startPos, Vec3 impactPos, LightningInfo info)
    {
        AABB hitBox = new AABB(impactPos, impactPos).inflate(info.hitRadius());
        DamageSource damageSource = info.damageSourceFromStartPos().apply(startPos);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, hitBox, info.canHurt()))
        {
            entity.hurtServer(level, damageSource, info.damage());
        }
    }

    private static double horizontalDistanceSqr(Vec3 pos1, Vec3 pos2)
    {
        double dx = pos1.x - pos2.x, dz = pos1.z - pos2.z;
        return dx * dx + dz * dz;
    }
}
