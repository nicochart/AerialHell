package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ShadowProjectileEntity extends ThrowableEntity
{
    private int ticksInAir = 0;
    public ShadowProjectileEntity(EntityType<? extends ShadowProjectileEntity> type, World worldIn) {super(type, worldIn);}

    public ShadowProjectileEntity(World world, LivingEntity shooter)
    {
    	super(AerialHellEntities.SHADOW_PROJECTILE.get(), shooter, world);
        this.setShooter(shooter);
    }

    public ShadowProjectileEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
    {
    	this(world, shooter);
    	this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
    }
    
    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
    	super.shoot(x, y, z, velocity, inaccuracy);
    	this.playSound(AerialHellSoundEvents.ENTITY_SHADOW_PROJECTILE_SHOOT.get(), 3, 0.875F + 0.25F * rand.nextFloat());
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerData() {}

    @Override
    public void tick()
    {
    	double d1,d2,d3; d1 = 0.5D - rand.nextFloat(); d2 = 0.5D - rand.nextFloat(); d3 = 0.5D - rand.nextFloat();
        this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getPosX() + d1, this.getPosY() + 0.3D + d2, this.getPosZ() + d3, d1, d2, d3);
        super.tick();
        if (!this.onGround) {++this.ticksInAir;}
        if (this.ticksInAir > 300) {this.remove();}
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
    	double d1,d2,d3,d4,d5,d6; 
    	d1 = 0.5D - rand.nextFloat(); d2 = 0.5D - rand.nextFloat(); d3 = 0.5D - rand.nextFloat(); d4 = 0.5D - rand.nextFloat(); d5 = 0.5D - rand.nextFloat(); d6 = 0.5D - rand.nextFloat();
        this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getPosX() - d1, this.getPosY() - d2, this.getPosZ() - d3, -d1, -d2, -d3);
        this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getPosX() - d4, this.getPosY() - d5, this.getPosZ() - d6, -d4, -d5, -d6);
        this.world.addParticle(ParticleTypes.CLOUD, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        this.world.addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), this.getPosX() + d1, this.getPosY() + d2, this.getPosZ() + d3, d1, d2, d3);
        this.world.addParticle(AerialHellParticleTypes.SHADOW_TROLL_BAT.get(), this.getPosX() + d4, this.getPosY() + d5, this.getPosZ() + d6, d4, d5, d6);
        super.onImpact(result);
        if (result.getType() != RayTraceResult.Type.ENTITY && !this.world.isRemote) {this.remove();}
    }
    
    private boolean targetIsImmuneToLunaticProjectileKb(Entity target) //target is not a ChainedGod or Lunatic Priest
    {
    	return (target instanceof ChainedGodEntity || target instanceof LunaticPriestEntity || (target instanceof PlayerEntity && ((PlayerEntity)target).isCreative()));
    }
    
    @Override
    protected void onEntityHit(EntityRayTraceResult result)
    {
        Entity target = result.getEntity();
        if (target instanceof LivingEntity && !(target instanceof LilithEntity))
        {
            ((LivingEntity) target).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 100, 0));
        }
    }
    
    @Override
    protected float getGravityVelocity() {return 0.0F;}
}
