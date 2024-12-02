package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class ShroomBoomEntity extends Creeper
{
    public ShroomBoomEntity(EntityType<? extends ShroomBoomEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new FloatGoal(this));
    	this.goalSelector.addGoal(2, new ShroomBoomMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new SwellGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }
    
    @Override public boolean isPowered() {return false;}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 35.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
    
    public boolean canIgnite() {return this.getHealth() < this.getMaxHealth() / 3;}
    
    public void setSwellDir(int state) //-1 for idle and 1 for ignited
    {
    	if (state == 1)
    	{
    		if (!this.canIgnite()) {super.setSwellDir(-1);}
    		else {super.setSwellDir(state);}
    	}
    	else {super.setSwellDir(state);}
    }
    
    @Override public boolean doHurtTarget(Entity attackedEntity)
    {
        if (!this.canIgnite())
        {
            DamageSource damagesource = this.damageSources().mobAttack(this);
        	float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        	float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
            boolean isAttackSuccess = attackedEntity.hurt(damagesource, damage);
            if (isAttackSuccess)
            {
            	if (kb > 0.0F && attackedEntity instanceof LivingEntity)
                {
            		((LivingEntity)attackedEntity).knockback(kb * 0.5F, (double) MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
                    EntityHelper.multiplyDeltaMovement(this, 0.6D, 1.0D);
                }
                if (level() instanceof ServerLevel serverLevel) {
                    EnchantmentHelper.doPostAttackEffects(serverLevel, attackedEntity, damagesource);}
            	this.setLastHurtMob(attackedEntity);
            }
            return isAttackSuccess;
        }
        else {return super.doHurtTarget(attackedEntity);}
    }

    public static class ShroomBoomMeleeAttackGoal extends MeleeAttackGoal
    {
        protected final ShroomBoomEntity entity;

        public ShroomBoomMeleeAttackGoal(ShroomBoomEntity entityIn, double speedIn, boolean useLongMemory)
        {
            super(entityIn, speedIn, useLongMemory);
            this.entity = entityIn;
        }

        @Override public boolean canUse() {return !this.entity.canIgnite() && super.canUse();}
        @Override public boolean canContinueToUse() {return !this.entity.canIgnite() && super.canContinueToUse();}
    }
}