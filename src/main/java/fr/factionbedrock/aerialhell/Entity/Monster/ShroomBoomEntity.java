package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ShroomBoomEntity extends CreeperEntity
{
    public ShroomBoomEntity(EntityType<? extends ShroomBoomEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new SwimGoal(this));
    	this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new CreeperSwellGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }
    
    @Override public boolean isCharged() {return false;}

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 35.0F)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25F)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
    }
    
    public boolean canIgnite() {return this.getHealth() < this.getMaxHealth() / 3;}
    
    public void setCreeperState(int state) //-1 for idle and 1 for ignited
    {
    	if (state == 1)
    	{
    		if (!this.canIgnite()) {super.setCreeperState(-1);}
    		else {super.setCreeperState(state);}
    	}
    	else {super.setCreeperState(state);}
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!this.canIgnite())
        {
        	float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        	float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
            boolean isAttackSuccess = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
            if (isAttackSuccess)
            {
            	if (kb > 0.0F && entityIn instanceof LivingEntity)
                {
            		((LivingEntity)entityIn).applyKnockback(kb * 0.5F, (double)MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F))));
            		this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
                }
            	this.applyEnchantments(this, entityIn);
            	this.setLastAttackedEntity(entityIn);
            }
            return isAttackSuccess;
        }
        else {return super.attackEntityAsMob(entityIn);}        
    }
}