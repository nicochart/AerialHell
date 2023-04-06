package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LunaticProjectileEntity extends AbstractLightProjectileEntity
{
    public LunaticProjectileEntity(EntityType<? extends LunaticProjectileEntity> type, World worldIn) {super(type, worldIn);}
    
    public LunaticProjectileEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
    	super(AerialHellEntities.LUNATIC_PROJECTILE.get(), shooter, world);
    	this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        super.onImpact(result);
    	this.world.addParticle(ParticleTypes.CLOUD, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    
    private boolean targetIsImmuneToLunaticProjectileKb(Entity target) //target is not a ChainedGod or Lunatic Priest
    {
    	return (target instanceof ChainedGodEntity || target instanceof LunaticPriestEntity || (target instanceof PlayerEntity && ((PlayerEntity)target).isCreative()));
    }
    
    @Override
    protected void onEntityHit(EntityRayTraceResult result)
    {
    	super.onEntityHit(result);
        Entity target = result.getEntity();
        if (target != this.func_234616_v_()) //target != projectile shooter (not working yet..)
        {
        	target.attackEntityFrom(DamageSource.causeThrownDamage(this, func_234616_v_()), 5);
            float amount = 4.0F;
            if (EntityHelper.isShadowEntity(target) || (target instanceof LivingEntity && EntityHelper.isLivingEntityVulnerable((LivingEntity) target))) {amount*=2;}
            target.attackEntityFrom(new DamageSource("lunatic_projection"), amount);
            if (!targetIsImmuneToLunaticProjectileKb(target))
            {
            	target.addVelocity(this.getMotion().x, 0.3D, this.getMotion().z);
            }
        }
    }

    @Override protected BasicParticleType getImpactParticle() {return AerialHellParticleTypes.COPPER_PINE_LEAVES.get();}
    @Override protected BasicParticleType getFlyParticle() {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_SHOOT.get();}
    @Override protected void playDisappearSound(float volume, float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_DISAPPEAR.get(), volume, pitch);}
}
