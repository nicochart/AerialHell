package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import java.util.List;

import fr.factionbedrock.aerialhell.Entity.Monster.AerialHellHostileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public abstract class AbstractElementSpiritEntity extends AerialHellHostileEntity
{
    private static final TrackedData<Boolean> ATTACKING = DataTracker.<Boolean>registerData(AbstractElementSpiritEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int tickStartAttacking;

	public AbstractElementSpiritEntity(EntityType<? extends AbstractElementSpiritEntity> type, World world) {super(type, world);}
	
	@Override
    protected void initGoals()
    {
    	super.initGoals();
    	this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.3F));
    }
	
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 15.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.24D)
        		.add(EntityAttributes.ATTACK_DAMAGE, 0.5D);
    }
    
    @Override
    public boolean tryAttack(ServerWorld serverWorld, Entity entityIn)
    {
    	boolean flag = super.tryAttack(serverWorld, entityIn);
    	if (flag) {this.setAttacking(); this.tickStartAttacking = this.age;}
    	return flag;
    }

    @Override
    public void tick()
    {
        if (this.isAttacking() && this.age > this.tickStartAttacking + 3) {this.attackSuicide();}
        super.tick();
    }

    public void attackSuicide()
    {
    	this.playSound(this.getDeathSound(), 1.5F, 0.95F + random.nextFloat() * 0.1F);
    	this.spawnParticle();
    	List<Entity> nearbyEntities = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(4), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 4));
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
            this.getWorld().addParticle(this.getParticleToSpawn(), this.getParticleX(1.0D) + d0 * 10.0D, this.getRandomBodyY() + d1 * 10.0D, this.getParticleZ(1.0D) + d2 * 10.0D, d0, d1, d2);
        }
    }

    public abstract void applyEffect(Entity entity);
    public abstract SimpleParticleType getParticleToSpawn();

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(ATTACKING, false);
    }

    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Disappearing", this.isAttacking());
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        if (nbt.getBoolean("Disappearing")) {this.setAttacking();}
    }

    public boolean isAttacking() {return this.getDataTracker().get(ATTACKING);}
    public void setAttacking() {this.getDataTracker().set(ATTACKING, true);}
}
