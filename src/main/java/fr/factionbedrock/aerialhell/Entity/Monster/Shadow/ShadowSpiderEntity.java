package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import fr.factionbedrock.aerialhell.Entity.Monster.Spider.AbstractAerialHellSpiderEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ShadowSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public ShadowSpiderEntity(EntityType<? extends SpiderEntity> type, World world)
    {
        super(type, world);
    }
    
    @Override
    public void initGoals()
    {
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge(ShadowSpiderEntity.class));
        super.initGoals();
    }
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.ATTACK_DAMAGE, 7)
                .add(EntityAttributes.ARMOR, 4)
                .add(EntityAttributes.MAX_HEALTH, 32);
    }
    
    @Override
    public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
    	if (super.tryAttack(serverWorld, attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity livingEntity)
        	{
    			if (!EntityHelper.isLivingEntityShadowImmune(livingEntity))
    			{
	    			int amplifier = 0;
	    			if (livingEntity.getStatusEffect(StatusEffects.SLOWNESS) != null)
	    			{
	    				amplifier = livingEntity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier();
	    				if (amplifier < 2) {amplifier++;}
	    				else
	    				{
	    					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0));
	    				}
	    			}
	    			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 90 + amplifier * 30, amplifier));
    			}
        	}
    		return true;
    	}
    	else {return false;}
    }
}
