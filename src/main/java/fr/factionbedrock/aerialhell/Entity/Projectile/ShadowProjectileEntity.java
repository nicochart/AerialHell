package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;

public class ShadowProjectileEntity extends AbstractLightProjectileEntity
{
    public ShadowProjectileEntity(EntityType<? extends ShadowProjectileEntity> type, Level worldIn) {super(type, worldIn);}

    public ShadowProjectileEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
        super(AerialHellEntities.SHADOW_PROJECTILE.get(), shooter, world);
        this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result)
    {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        if (target instanceof LivingEntity && !(target instanceof LilithEntity))
        {
            ((LivingEntity) target).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 100, 0));
        }
    }

    @Override protected SimpleParticleType getImpactParticle() {return AerialHellParticleTypes.SHADOW_PARTICLE.get();}
    @Override protected SimpleParticleType getFlyParticle() {return AerialHellParticleTypes.SHADOW_LIGHT.get();}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHADOW_PROJECTILE_SHOOT.get();}
    @Override protected void playDisappearSound(float volume, float pitch) {}
}
