package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import fr.factionbedrock.aerialhell.Entity.Monster.ShadowMisleadableEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.AbstractAerialHellSpiderEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ShadowSpiderEntity extends AbstractAerialHellSpiderEntity implements ShadowMisleadableEntity
{
    public ShadowSpiderEntity(EntityType<? extends Spider> type, Level world) {super(type, world);}

	/* ------- MisleadableEntity : Interface method implementation ------- */
	@Override public Mob getSelf() {return this;}

	/* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
	@Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
	{
		return this.misleadableHurtServer(serverLevel, source, amount, super::hurtServer);
	}

	@Override public void die(DamageSource damageSource)
	{
		this.misleadableDie(damageSource);
		super.die(damageSource);
	}

	@Override public boolean canAttack(LivingEntity target) {return this.misleadableCanAttack(target, super::canAttack);}
	/* ------------------------------------------------------------------------------------------ */

    @Override public void registerGoals()
    {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget)));
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
    
    @Override public boolean doHurtTarget(ServerLevel serverWorld, Entity attackedEntity)
    {
    	if (super.doHurtTarget(serverWorld, attackedEntity))
    	{
    		if (attackedEntity instanceof LivingEntity livingEntity)
        	{
    			if (!EntityHelper.isLivingEntityShadowImmune(livingEntity))
    			{
	    			int amplifier = 0;
	    			if (livingEntity.getEffect(MobEffects.SLOWNESS) != null)
	    			{
	    				amplifier = livingEntity.getEffect(MobEffects.SLOWNESS).getAmplifier();
	    				if (amplifier < 2) {amplifier++;}
	    				else
	    				{
	    					livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0));
	    				}
	    			}
	    			livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 90 + amplifier * 30, amplifier));
    			}
        	}
    		return true;
    	}
    	else {return false;}
    }
}
