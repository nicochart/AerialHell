package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import fr.factionbedrock.aerialhell.Entity.Monster.Spider.AbstractAerialHellSpiderEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

public class ShadowSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public ShadowSpiderEntity(EntityType<? extends Spider> type, Level worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ShadowSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.ARMOR, 4)
                .add(Attributes.MAX_HEALTH, 32);
    }
    
    @Override
    public boolean doHurtTarget(Entity attackedEntity)
    {
    	if (super.doHurtTarget(attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity)
        	{
    			LivingEntity livingEntity = (LivingEntity) attackedEntity;
    			if (!EntityHelper.isLivingEntityShadowImmune(livingEntity))
    			{
	    			int amplifier = 0;
	    			if (livingEntity.getEffect(MobEffects.MOVEMENT_SLOWDOWN) != null)
	    			{
	    				amplifier = livingEntity.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier();
	    				if (amplifier < 2) {amplifier++;}
	    				else
	    				{
	    					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0));
	    				}
	    			}
	    			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.MOVEMENT_SLOWDOWN, 90 + amplifier * 30, amplifier));
    			}
        	}
    		return true;
    	}
    	else {return false;}
    }
}
