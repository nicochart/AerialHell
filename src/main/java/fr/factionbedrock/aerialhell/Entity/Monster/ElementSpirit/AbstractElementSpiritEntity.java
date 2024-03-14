package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import java.util.List;

import fr.factionbedrock.aerialhell.Entity.Monster.AerialHellHostileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.Level;

public abstract class AbstractElementSpiritEntity extends AerialHellHostileEntity
{
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.<Boolean>defineId(AbstractElementSpiritEntity.class, EntityDataSerializers.BOOLEAN);
    private int tickStartAttacking;

	public AbstractElementSpiritEntity(EntityType<? extends AbstractElementSpiritEntity> type, Level worldIn) {super(type, worldIn);}
	
	@Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.3F));
    }
	
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
        		.add(Attributes.ATTACK_DAMAGE, 0.5D);
    }
    
    @Override
    public boolean doHurtTarget(Entity entityIn)
    {
    	boolean flag = super.doHurtTarget(entityIn);
    	if (flag) {this.setAttacking(); this.tickStartAttacking = this.tickCount;}
    	return flag;
    }

    @Override
    public void tick()
    {
        if (this.isAttacking() && this.tickCount > this.tickStartAttacking + 3) {this.attackSuicide();}
        super.tick();
    }

    public void attackSuicide()
    {
    	this.playSound(this.getDeathSound(), 1.5F, 0.95F + random.nextFloat() * 0.1F);
    	this.spawnParticle();
    	List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(4), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 4));
    	for (Entity entity : nearbyEntities)
    	{
    		if (entity instanceof LivingEntity)
    		{
    			applyEffect(entity);
    		}
    	}
    	this.discard();
    }
    
    public void spawnParticle()
    {
        for (int i=0; i<30; i++)
        {
            double d0 = (this.random.nextGaussian() - 0.5D) * 0.02D;
            double d1 = (this.random.nextGaussian() - 0.5D) * 0.02D;
            double d2 = (this.random.nextGaussian() - 0.5D) * 0.02D;
            this.level().addParticle(this.getParticleToSpawn(), this.getX(1.0D) + d0 * 10.0D, this.getRandomY() + d1 * 10.0D, this.getRandomZ(1.0D) + d2 * 10.0D, d0, d1, d2);
        }
    }

    public abstract void applyEffect(Entity entity);
    public abstract SimpleParticleType getParticleToSpawn();

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Disappearing", this.isAttacking());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        if (compound.getBoolean("Disappearing")) {this.setAttacking();}
    }

    public boolean isAttacking() {return this.entityData.get(ATTACKING);}
    public void setAttacking() {this.entityData.set(ATTACKING, true);}
}
