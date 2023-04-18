package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class ShadowProjectileEntity extends AbstractLightProjectileEntity
{
    public ShadowProjectileEntity(EntityType<? extends ShadowProjectileEntity> type, World worldIn) {super(type, worldIn);}

    public ShadowProjectileEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
        super(AerialHellEntities.SHADOW_PROJECTILE.get(), shooter, world);
        this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }
    
    @Override
    protected void onEntityHit(EntityRayTraceResult result)
    {
        super.onEntityHit(result);
        Entity target = result.getEntity();
        if (target instanceof LivingEntity && !(target instanceof LilithEntity))
        {
            ((LivingEntity) target).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 100, 0));
        }
    }

    @Override protected BasicParticleType getImpactParticle() {return AerialHellParticleTypes.SHADOW_PARTICLE.get();}
    @Override protected BasicParticleType getFlyParticle() {return AerialHellParticleTypes.SHADOW_LIGHT.get();}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHADOW_PROJECTILE_SHOOT.get();}
    @Override protected void playDisappearSound(float volume, float pitch) {}
}
