package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class LunaticProjectileEntity extends AbstractLightProjectileEntity
{
    public LunaticProjectileEntity(EntityType<? extends LunaticProjectileEntity> type, World world) {super(type, world);}
    
    public LunaticProjectileEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
    	super(AerialHellEntities.LUNATIC_PROJECTILE, shooter, world);
    	this.setVelocity(accelX, accelY, accelZ, velocity, inaccuracy);
    }

    @Override protected BiomeShifter.ShiftType getShiftType() {return BiomeShifter.ShiftType.UNCORRUPT;}

    @Override
    protected void onCollision(HitResult result)
    {
        super.onCollision(result);
    	this.getWorld().addParticleClient(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    }
    
    private boolean targetIsImmuneToLunaticProjectileKb(Entity target) //target is not a ChainedGod or Lunatic Priest
    {
    	return (target instanceof ChainedGodEntity || target instanceof LunaticPriestEntity || EntityHelper.isCreaOrSpecPlayer(target));
    }
    
    @Override
    protected void onEntityHit(EntityHitResult result)
    {
    	super.onEntityHit(result);
        Entity target = result.getEntity();
        if (target != this.getOwner()) //target != projectile shooter (not working yet..)
        {
        	target.serverDamage(this.getDamageSources().thrown(this, getOwner()), 5);
            float amount = 4.0F;
            if (EntityHelper.isShadowEntity(target) || (target instanceof LivingEntity && EntityHelper.isLivingEntityVulnerable((LivingEntity) target))) {amount*=2;}
            target.serverDamage(AerialHellDamageTypes.getDamageSource(this.getWorld(), AerialHellDamageTypes.LUNATIC_PROJECTION), amount);
            if (!targetIsImmuneToLunaticProjectileKb(target))
            {
            	target.addVelocity(this.getVelocity().x, 0.3D, this.getVelocity().z);
            }
        }
    }

    @Override protected SimpleParticleType getImpactParticle() {return AerialHellParticleTypes.COPPER_PINE_LEAVES;}
    @Override protected SimpleParticleType getFlyParticle() {return AerialHellParticleTypes.LUNATIC_PARTICLE;}
    @Override protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_SHOOT;}
    @Override protected void playDisappearSound(float volume, float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_LUNATIC_PROJECTILE_DISAPPEAR, volume, pitch);}
}
