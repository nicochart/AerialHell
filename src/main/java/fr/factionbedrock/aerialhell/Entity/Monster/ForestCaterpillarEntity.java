package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class ForestCaterpillarEntity extends SilverfishEntity
{
	public ForestCaterpillarEntity(EntityType<? extends SilverfishEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
	
	public ForestCaterpillarEntity(World worldIn)
    {
        this(AerialHellEntities.FOREST_CATERPILLAR.get(), worldIn);
    }
	
	@Override
	protected void registerGoals()
	{
	      this.goalSelector.addGoal(1, new SwimGoal(this));
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.2F));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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
