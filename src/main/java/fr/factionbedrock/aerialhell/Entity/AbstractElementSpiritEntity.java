package fr.factionbedrock.aerialhell.Entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;

public abstract class AbstractElementSpiritEntity extends AerialHellHostileEntity
{
	public AbstractElementSpiritEntity(EntityType<? extends AbstractElementSpiritEntity> type, World worldIn) {super(type, worldIn);}
	
	@Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
    }
	
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24D)
        		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.5D);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	boolean flag = super.attackEntityAsMob(entityIn);
    	if (flag) {this.attackSuicide(entityIn);}
    	return flag;
    }
    
    public void attackSuicide(Entity entityIn)
    {
    	this.playSound(this.getDeathSound(), 1.5F, 0.95F + rand.nextFloat() * 0.1F);
    	this.spawnParticle();
    	List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(4), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 4));
    	for (Entity entity : nearbyEntities)
    	{
    		if (entity instanceof LivingEntity)
    		{
    			applyEffect(entity);
    		}
    	}
    	this.setDead();
    }
    
    public void spawnParticle()
    {
        double d0 = this.rand.nextGaussian() * 0.02D;
        double d1 = this.rand.nextGaussian() * 0.02D;
        double d2 = this.rand.nextGaussian() * 0.02D;
        this.getServer().getWorld(this.getEntityWorld().getDimensionKey()).spawnParticle(this.getParticleToSpawn(), this.getPosXWidth(1.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, 30, 2 * d0, d1, 2 * d2, 0.5);
    }
    
    public abstract void applyEffect(Entity entity);
    public abstract BasicParticleType getParticleToSpawn();
}
