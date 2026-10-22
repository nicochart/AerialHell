package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

//particles displayed around a source position, in random directions, flying toward the source or from the source
public class DisplayParticlesHelper
{
    public static final float END_ROD_FRICTION = 0.91F;
    public static final float FLAME_FRICTION = 0.96F;
    public static final int FLAME_MAX_LIFETIME = 44; //flame lifetime : 12 to 44 ticks
    public static final float SMOKE_FRICTION = 0.96F;
    public static final int SMOKE_MAX_LIFETIME = 40; //smoke lifetime : 8 to 40 ticks
    public static final int LARGE_SMOKE_MAX_LIFETIME = 100; //large smoke lifetime : 20 to 100 ticks

    private final ParticleOptions particle;
    private final int number; //number of particles displayed each call
    private final Movement movement;
    private final float minDistance; //distance from source where particles spawn
    private final float maxDistance;
    private final float minSpeed; //FROM_SOURCE only
    private final float maxSpeed; //FROM_SOURCE only
    private final float particleFriction; //TOWARD_SOURCE only, used to compute the speed needed to reach the target
    private final int particleMaxLifetime; //TOWARD_SOURCE only, maximum lifetime of the particle (in ticks). 0 = long-lived particle (no time limit)
    private final float targetRadius; //TOWARD_SOURCE only, particles stop at this distance from the source (for example, to stop at the surface of an entity)

    private DisplayParticlesHelper(@NotNull ParticleOptions particle, int number, Movement movement, float minDistance, float maxDistance, float minSpeed, float maxSpeed, float particleFriction, int particleMaxLifetime, float targetRadius)
    {
        this.particle = particle;
        this.number = number;
        this.movement = movement;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.particleFriction = particleFriction;
        this.particleMaxLifetime = particleMaxLifetime;
        this.targetRadius = targetRadius;
    }

    //particles spawn between minDistance and maxDistance from the source, and fly to the source (speed depends on particle friction)
    //particles never go past the source : with friction and no time limit, they slow down and stop at the source
    public static DisplayParticlesHelper towardSource(@NotNull ParticleOptions particle, int number, float minDistance, float maxDistance, float particleFriction)
    {
        return towardSource(particle, number, minDistance, maxDistance, particleFriction, 0);
    }

    //same, for short-lived particles (flame, smoke...) : speed is increased so that a particle living particleMaxLifetime ticks stops exactly at the source
    //shorter-lived particles disappear before reaching the source, and no particle can go past it (as long as particleMaxLifetime is the actual maximum lifetime)
    public static DisplayParticlesHelper towardSource(@NotNull ParticleOptions particle, int number, float minDistance, float maxDistance, float particleFriction, int particleMaxLifetime)
    {
        return new DisplayParticlesHelper(particle, number, Movement.TOWARD_SOURCE, minDistance, maxDistance, 0.0F, 0.0F, particleFriction, particleMaxLifetime, 0.0F);
    }

    //particles spawn at startDistance from the source, and fly away from the source with a speed between minSpeed and maxSpeed
    public static DisplayParticlesHelper fromSource(@NotNull ParticleOptions particle, int number, float startDistance, float minSpeed, float maxSpeed)
    {
        return new DisplayParticlesHelper(particle, number, Movement.FROM_SOURCE, startDistance, startDistance, minSpeed, maxSpeed, 0.0F, 0, 0.0F);
    }

    //returns a copy of this helper, with particles stopping at targetRadius from the source instead of at the source itself (TOWARD_SOURCE only)
    public DisplayParticlesHelper targetRadius(float targetRadius)
    {
        return new DisplayParticlesHelper(this.particle, this.number, this.movement, this.minDistance, this.maxDistance, this.minSpeed, this.maxSpeed, this.particleFriction, this.particleMaxLifetime, targetRadius);
    }

    //client side only (level.addParticle does nothing server side)
    public void displayParticles(Level level, Vec3 sourcePos, RandomSource random)
    {
        for (int i = 0; i < this.number; i++)
        {
            Vec3 direction = randomDirection(random);
            float distance = Mth.nextFloat(random, this.minDistance, this.maxDistance);
            Vec3 pos = sourcePos.add(direction.scale(distance));
            float speed = this.movement == Movement.TOWARD_SOURCE ? -getSpeedToTravel(Math.max(0.0F, distance - this.targetRadius), this.particleFriction, this.particleMaxLifetime) : Mth.nextFloat(random, this.minSpeed, this.maxSpeed);
            Vec3 velocity = direction.scale(speed);
            level.addParticle(this.particle, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z);
        }
    }

    //a particle with initial speed v and friction f travels (at most) v / (1 - f) blocks
    public static float getSpeedToTravel(float distance, float particleFriction) {return distance * (1.0F - particleFriction);}

    //a particle with initial speed v and friction f travels v * (1 - f^ticks) / (1 - f) blocks in the given ticks (less if it dies before). ticks <= 0 : no time limit
    public static float getSpeedToTravel(float distance, float particleFriction, int ticks)
    {
        if (ticks <= 0) {return getSpeedToTravel(distance, particleFriction);}
        return distance * (1.0F - particleFriction) / (1.0F - (float)Math.pow(particleFriction, ticks));
    }

    //uniformly distributed random unit vector
    private static Vec3 randomDirection(RandomSource random) {return new Vec3(random.nextGaussian(), random.nextGaussian(), random.nextGaussian()).normalize();}

    public enum Movement {TOWARD_SOURCE, FROM_SOURCE}
}
