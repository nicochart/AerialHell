package fr.factionbedrock.aerialhell.Entity.Neutral;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class ForestCaterpillarEntity extends AbstractCaterpillarEntity
{
	public ForestCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, World world)
    {
        super(type, world);
    }
	
	public ForestCaterpillarEntity(World world)
    {
        this(AerialHellEntities.FOREST_CATERPILLAR, world);
    }
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AbstractCaterpillarEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D)
        		.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public boolean tryAttack(Entity entityIn)
	{
		if (super.tryAttack(entityIn))
		{
	         if (entityIn instanceof LivingEntity)
	         {
	            int i = 0;
	            if (this.getWorld().getDifficulty() == Difficulty.NORMAL) {i = 4;}
	            else if (this.getWorld().getDifficulty() == Difficulty.HARD) {i = 9;}

	            if (i > 0)
	            {
	               ((LivingEntity)entityIn).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, i * 20, 2)); //poison III
	            }
	         }
	         return true;
	    }
		else {return false;}
	}
}
