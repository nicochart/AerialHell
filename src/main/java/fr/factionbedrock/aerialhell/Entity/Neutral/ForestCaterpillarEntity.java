package fr.factionbedrock.aerialhell.Entity.Neutral;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;

public class ForestCaterpillarEntity extends AbstractCaterpillarEntity
{
	public ForestCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }
	
	public ForestCaterpillarEntity(Level worldIn)
    {
        this(AerialHellEntities.FOREST_CATERPILLAR.get(), worldIn);
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
        return AbstractCaterpillarEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
        		.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
	         if (entityIn instanceof LivingEntity)
	         {
	            int i = 0;
	            if (this.level().getDifficulty() == Difficulty.NORMAL) {i = 4;}
	            else if (this.level().getDifficulty() == Difficulty.HARD) {i = 9;}

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
