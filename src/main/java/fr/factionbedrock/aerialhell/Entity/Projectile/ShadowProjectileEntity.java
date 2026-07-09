package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

public class ShadowProjectileEntity extends AbstractLightProjectileEntity
{
    public ShadowProjectileEntity(EntityType<? extends ShadowProjectileEntity> type, Level world) {super(type, world);}

    public ShadowProjectileEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
        super(AerialHellEntities.SHADOW_PROJECTILE, shooter, world);
        this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }

    @Override protected BiomeShifter.ShiftType getShiftType() {return BiomeShifter.ShiftType.CORRUPT;}
    
    @Override
    protected void onHitEntity(EntityHitResult result)
    {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        if (target instanceof LivingEntity && !(target instanceof LilithEntity))
        {
            ((LivingEntity) target).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 100, 0));
        }
    }

    @Override protected SimpleParticleType getImpactParticle() {return AerialHellParticleTypes.SHADOW_PARTICLE;}
    @Override protected SimpleParticleType getFlyParticle() {return AerialHellParticleTypes.SHADOW_LIGHT;}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHADOW_PROJECTILE_SHOOT;}
    @Override protected void playDisappearSound(float volume, float pitch) {}
}
