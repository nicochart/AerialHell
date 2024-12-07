package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ShadowProjectileEntity extends AbstractLightProjectileEntity
{
    public ShadowProjectileEntity(EntityType<? extends ShadowProjectileEntity> type, World world) {super(type, world);}

    public ShadowProjectileEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
        super(AerialHellEntities.SHADOW_PROJECTILE, shooter, world);
        this.setVelocity(accelX, accelY, accelZ, velocity, inaccuracy);
    }

    @Override protected BiomeShifter.ShiftType getShiftType() {return BiomeShifter.ShiftType.CORRUPT;}
    
    @Override
    protected void onEntityHit(EntityHitResult result)
    {
        super.onEntityHit(result);
        Entity target = result.getEntity();
        if (target instanceof LivingEntity && !(target instanceof LilithEntity))
        {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 100, 0));
        }
    }

    @Override protected SimpleParticleType getImpactParticle() {return AerialHellParticleTypes.SHADOW_PARTICLE;}
    @Override protected SimpleParticleType getFlyParticle() {return AerialHellParticleTypes.SHADOW_LIGHT;}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHADOW_PROJECTILE_SHOOT;}
    @Override protected void playDisappearSound(float volume, float pitch) {}
}
