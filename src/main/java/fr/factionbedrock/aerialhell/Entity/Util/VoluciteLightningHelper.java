package fr.factionbedrock.aerialhell.Entity.Util;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteLightningEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
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
import java.util.function.Predicate;

//volucite lightning attack : a visual lightning (VoluciteLightningEntity) from a start pos to an impact pos, hurting entities near the impact
//the aimed pos is chosen by the caller (target pos, random pos...), see strikeLightningBelow, findNearestTargetBelow and randomPosBelow. server side only
public class VoluciteLightningHelper
{
    //damage source, damage amount, radius around the impact, entities that can be hurt, and sound source of the impact sound
    public record LightningInfo(DamageSource damageSource, float damage, float hitRadius, Predicate<LivingEntity> canHurt, SoundSource soundSource) {}

    //lightning of a volucite mob : no damage to volucite entities (no friendly fire between volucite warden parts, for example)
    public static LightningInfo voluciteMobLightningInfo(Mob attacker, float damage, float hitRadius)
    {
        return new LightningInfo(attacker.damageSources().lightningBolt(), damage, hitRadius, entity -> canVoluciteMobHurt(attacker, entity), attacker.getSoundSource());
    }

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
        hurtEntitiesNearImpact(level, impactPos, info);

        RandomSource random = level.getRandom();
        level.playSound(null, impactPos.x, impactPos.y, impactPos.z, SoundEvents.LIGHTNING_BOLT_IMPACT, info.soundSource(), 2.0F, 1.2F + random.nextFloat() * 0.4F);
        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, impactPos.x, impactPos.y, impactPos.z, 12, 0.3D, 0.3D, 0.3D, 0.3D);
        return true;
    }

    //lightning below startPos. with a targetChance probability (0.4F = 40%), toward the nearest valid target below (see findNearestTargetBelow)
    //otherwise (other lightning, or no target found), toward a random pos below (see randomPosBelow)
    //requiresBlockHitForRandomStrike : no random lightning if it doesn't reach the ground. if false, a random lightning can end in the air (maxDepth below startPos)
    //returns true if the lightning is struck
    public static boolean strikeLightningBelow(ServerLevel level, Vec3 startPos, float targetChance, Predicate<LivingEntity> isValidTarget, float targetHorizontalRange, float randomHorizontalSpread, float maxDepth, boolean randomStrikeRequiresBlockHit, LightningInfo info)
    {
        RandomSource random = level.getRandom();
        @Nullable LivingEntity target = random.nextFloat() < targetChance ? findNearestTargetBelow(level, startPos, targetHorizontalRange, maxDepth, isValidTarget) : null;
        if (target != null) {return strikeLightning(level, startPos, target.getBoundingBox().getCenter(), false, info);}
        else {return strikeLightning(level, startPos, randomPosBelow(random, startPos, randomHorizontalSpread, maxDepth), randomStrikeRequiresBlockHit, info);}
    }

    //nearest valid target below pos, in horizontalRange (horizontal distance) and maxDepth
    @Nullable public static LivingEntity findNearestTargetBelow(ServerLevel level, Vec3 pos, float horizontalRange, float maxDepth, Predicate<LivingEntity> isValidTarget)
    {
        AABB searchBox = new AABB(pos.x - horizontalRange, pos.y - maxDepth, pos.z - horizontalRange, pos.x + horizontalRange, pos.y, pos.z + horizontalRange);
        return level.getEntitiesOfClass(LivingEntity.class, searchBox, target -> isValidTarget.test(target) && horizontalDistanceSqr(target.position(), pos) <= horizontalRange * horizontalRange)
                .stream().min(Comparator.comparingDouble(target -> target.distanceToSqr(pos))).orElse(null);
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

    private static void hurtEntitiesNearImpact(ServerLevel level, Vec3 impactPos, LightningInfo info)
    {
        AABB hitBox = new AABB(impactPos, impactPos).inflate(info.hitRadius());
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, hitBox, info.canHurt()))
        {
            entity.hurtServer(level, info.damageSource(), info.damage());
        }
    }

    private static double horizontalDistanceSqr(Vec3 pos1, Vec3 pos2)
    {
        double dx = pos1.x - pos2.x, dz = pos1.z - pos2.z;
        return dx * dx + dz * dz;
    }
}
