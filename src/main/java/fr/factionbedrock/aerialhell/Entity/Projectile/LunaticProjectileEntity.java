package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LunaticProjectileEntity extends AbstractLightProjectileEntity
{
    public LunaticProjectileEntity(EntityType<? extends LunaticProjectileEntity> type, Level worldIn) {super(type, worldIn);}
    
    public LunaticProjectileEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
    	super(AerialHellEntities.LUNATIC_PROJECTILE.get(), shooter, world);
    	this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }

    @Override protected BiomeShifter.ShiftType getShiftType() {return BiomeShifter.ShiftType.UNCORRUPT;}

    @Override
    protected void onHit(HitResult result)
    {
        super.onHit(result);
    	this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    }
    
    private boolean targetIsImmuneToLunaticProjectileKb(Entity target) //target is not a ChainedGod or Lunatic Priest
    {
    	return (target instanceof ChainedGodEntity || target instanceof LunaticPriestEntity || (target instanceof Player && ((Player)target).isCreative()));
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result)
    {
    	super.onHitEntity(result);
        Entity target = result.getEntity();
        if (target != this.getOwner()) //target != projectile shooter (not working yet..)
        {
        	target.hurt(this.damageSources().thrown(this, getOwner()), 5);
            float amount = 4.0F;
            if (EntityHelper.isShadowEntity(target) || (target instanceof LivingEntity && EntityHelper.isLivingEntityVulnerable((LivingEntity) target))) {amount*=2;}
            target.hurt(AerialHellDamageTypes.getDamageSource(this.level(), AerialHellDamageTypes.LUNATIC_PROJECTION), amount);
            if (!targetIsImmuneToLunaticProjectileKb(target))
            {
            	target.push(this.getDeltaMovement().x, 0.3D, this.getDeltaMovement().z);
            }
        }
    }

    @Override protected SimpleParticleType getImpactParticle() {return AerialHellParticleTypes.COPPER_PINE_LEAVES.get();}
    @Override protected SimpleParticleType getFlyParticle() {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_SHOOT.get();}
    @Override protected void playDisappearSound(float volume, float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_DISAPPEAR.get(), volume, pitch);}
}
