package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ShroomBoomEntity extends CreeperEntity
{
    public ShroomBoomEntity(EntityType<? extends ShroomBoomEntity> type, World world) {super(type, world);}
    
    @Override
    protected void initGoals()
    {
    	this.goalSelector.add(1, new SwimGoal(this));
    	this.goalSelector.add(2, new ShroomBoomMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(4, new CreeperIgniteGoal(this));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }
    
    @Override public boolean isCharged() {return false;}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 35.0F)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25F)
                .add(EntityAttributes.FOLLOW_RANGE, 24.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 2.0D);
    }
    
    public boolean canIgnite() {return this.getHealth() < this.getMaxHealth() / 3;}
    
    @Override public void setFuseSpeed(int state) //-1 for idle and 1 for ignited
    {
    	if (state == 1)
    	{
    		if (!this.canIgnite()) {super.setFuseSpeed(-1);}
    		else {super.setFuseSpeed(state);}
    	}
    	else {super.setFuseSpeed(state);}
    }
    
    @Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
        if (!this.canIgnite())
        {
            DamageSource damagesource = this.getDamageSources().mobAttack(this);
        	float damage = (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
        	float kb = (float)this.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK);
            boolean isAttackSuccess = attackedEntity.damage(serverWorld, damagesource, damage);
            if (isAttackSuccess)
            {
            	if (kb > 0.0F && attackedEntity instanceof LivingEntity)
                {
            		((LivingEntity)attackedEntity).takeKnockback(kb * 0.5F, (double) MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
                    EntityHelper.multiplyDeltaMovement(this, 0.6D, 1.0D);
                }
                EnchantmentHelper.onTargetDamaged(serverWorld, attackedEntity, damagesource);
            	this.onAttacking(attackedEntity);
            }
            return isAttackSuccess;
        }
        else {return super.tryAttack(serverWorld, attackedEntity);}
    }

    public static class ShroomBoomMeleeAttackGoal extends MeleeAttackGoal
    {
        protected final ShroomBoomEntity entity;

        public ShroomBoomMeleeAttackGoal(ShroomBoomEntity entityIn, double speedIn, boolean useLongMemory)
        {
            super(entityIn, speedIn, useLongMemory);
            this.entity = entityIn;
        }

        @Override public boolean canStart() {return !this.entity.canIgnite() && super.canStart();}
        @Override public boolean shouldContinue() {return !this.entity.canIgnite() && super.shouldContinue();}
    }
}