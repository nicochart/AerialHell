package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractAerialHellSpiderEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ShadowSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public ShadowSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ShadowSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7)
                .createMutableAttribute(Attributes.ARMOR, 4)
                .createMutableAttribute(Attributes.MAX_HEALTH, 32);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity attackedEntity)
    {
    	if (super.attackEntityAsMob(attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity)
        	{
    			LivingEntity livingEntity = (LivingEntity) attackedEntity;
    			if (!EntityHelper.isLivingEntityShadowImmune(livingEntity))
    			{
	    			int amplifier = 0;
	    			if (livingEntity.getActivePotionEffect(Effects.SLOWNESS) != null)
	    			{
	    				amplifier = livingEntity.getActivePotionEffect(Effects.SLOWNESS).getAmplifier();
	    				if (amplifier < 2) {amplifier++;}
	    				else
	    				{
	    					livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 20, 0));
	    				}
	    			}
	    			livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 90 + amplifier * 30, amplifier));
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
