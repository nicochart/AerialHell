package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractLightProjectileEntity extends ThrowableEntity
{
    private int ticksInAir = 0;
    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, World world) {super(type, world);}

    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, LivingEntity shooter, World world)
    {
        super(type, shooter, world);
        this.setShooter(shooter);
    }
    @Override public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
    	super.shoot(x, y, z, velocity, inaccuracy);
    	this.playSound(this.getShootSound(), 3, 0.875F + 0.25F * rand.nextFloat());
    }

    @Override public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override protected void registerData() {}
    @Override protected float getGravityVelocity() {return 0.0F;}

    @Override public void tick()
    {
    	double d1,d2,d3; d1 = 0.5D - rand.nextFloat(); d2 = 0.5D - rand.nextFloat(); d3 = 0.5D - rand.nextFloat();
        this.world.addParticle(this.getFlyParticle(), this.getPosX() + d1, this.getPosY() + 0.3D + d2, this.getPosZ() + d3, d1, d2, d3);
        super.tick();
        if (!this.onGround) {++this.ticksInAir;}
        if (this.ticksInAir > 300) {this.remove();}
    }

    @Override protected void onImpact(RayTraceResult result)
    {
    	double d1,d2,d3,d4,d5,d6; 
    	d1 = 0.5D - rand.nextFloat(); d2 = 0.5D - rand.nextFloat(); d3 = 0.5D - rand.nextFloat(); d4 = 0.5D - rand.nextFloat(); d5 = 0.5D - rand.nextFloat(); d6 = 0.5D - rand.nextFloat();
        this.world.addParticle(this.getImpactParticle(), this.getPosX() - d1, this.getPosY() - d2, this.getPosZ() - d3, -d1, -d2, -d3);
        this.world.addParticle(this.getImpactParticle(), this.getPosX() - d4, this.getPosY() - d5, this.getPosZ() - d6, -d4, -d5, -d6);
        this.world.addParticle(this.getFlyParticle(), this.getPosX() + d1, this.getPosY() + d2, this.getPosZ() + d3, d1, d2, d3);
        this.world.addParticle(this.getFlyParticle(), this.getPosX() + d4, this.getPosY() + d5, this.getPosZ() + d6, d4, d5, d6);
        this.playDisappearSound(1, 0.75F + 0.5F * rand.nextFloat());
        super.onImpact(result);
        if (result.getType() != RayTraceResult.Type.ENTITY && !this.world.isRemote) {this.remove();}
    }

    @Override protected void onEntityHit(EntityRayTraceResult result)
    {
        this.playDisappearSound(1, 0.25F + 0.25F * rand.nextFloat());
    }

    protected abstract BasicParticleType getImpactParticle();
    protected abstract BasicParticleType getFlyParticle();
    protected abstract SoundEvent getShootSound();
    protected abstract void playDisappearSound(float volume, float pitch);
}
