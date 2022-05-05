package fr.factionbedrock.aerialhell.Entity.Neutral;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class ForestCaterpillarEntity extends AbstractCaterpillarEntity
{
	public ForestCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
	
	public ForestCaterpillarEntity(World worldIn)
    {
        this(AerialHellEntities.FOREST_CATERPILLAR.get(), worldIn);
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 16.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0D)
        		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		if (super.attackEntityAsMob(entityIn))
		{
	         if (entityIn instanceof LivingEntity)
	         {
	            int i = 0;
	            if (this.world.getDifficulty() == Difficulty.NORMAL) {i = 4;}
	            else if (this.world.getDifficulty() == Difficulty.HARD) {i = 9;}

	            if (i > 0)
	            {
	               ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, i * 20, 2)); //poison III
	            }
	         }
	         return true;
	    }
		else
		{
	         return false;
	    }
	}
}
