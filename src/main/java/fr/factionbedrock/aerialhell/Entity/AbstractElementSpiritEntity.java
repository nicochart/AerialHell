package fr.factionbedrock.aerialhell.Entity;

import java.util.List;

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
    	if (flag) {this.attackSuicide(entityIn);}
    	return flag;
    }
    
    public void attackSuicide(Entity entityIn)
    {
    	this.playSound(this.getDeathSound(), 1.5F, 0.95F + random.nextFloat() * 0.1F);
    	this.spawnParticle();
    	List<Entity> nearbyEntities = this.level.getEntities(this, this.getBoundingBox().inflate(4), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 4));
    	for (Entity entity : nearbyEntities)
    	{
    		if (entity instanceof LivingEntity)
    		{
    			applyEffect(entity);
    		}
    	}
    	this.removeAfterChangingDimensions();
    }
    
    public void spawnParticle()
    {
        if (this.level.isClientSide())
        {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            /*for (int i=0; i<30; i++) TODO testing
            {
                this.level.addParticle(this.getParticleToSpawn(), this.getX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, d0, d1, d2);
            }*/
            this.getServer().getLevel(this.getLevel().dimension()).sendParticles(this.getParticleToSpawn(), this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 30, 2 * d0, d1, 2 * d2, 0.5);
        }
    }
    
    public abstract void applyEffect(Entity entity);
    public abstract SimpleParticleType getParticleToSpawn();
}
