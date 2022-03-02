package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CrystalSpiderEntity extends SpiderEntity
{
    public CrystalSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
    	this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new CrystalSpiderEntity.AttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new CrystalSpiderEntity.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(CrystalSpiderEntity.class));
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.MAX_HEALTH, 15);
    }
    
    @Override
    public int getExperiencePoints(PlayerEntity player)
    {
        return player.getEntityWorld().getRandom().nextInt(10);
    }
    
    static class AttackGoal extends MeleeAttackGoal
    {
        public AttackGoal(SpiderEntity spider)
        {
           super(spider, 1.0D, true);
        }
        
        public boolean shouldContinueExecuting()
        {
            float f = this.attacker.getBrightness();
            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0)
            {
            	this.attacker.setAttackTarget((LivingEntity)null);
                return false;
            }
            else
            {
        	    return super.shouldContinueExecuting();
            }
        }

        protected double getAttackReachSqr(LivingEntity attackTarget)
        {
        	return (double)(4.0F + attackTarget.getWidth());
        }
    }
    
    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
    {
        public TargetGoal(SpiderEntity spider, Class<T> classTarget)
        {
        	super(spider, classTarget, true);
        }

        public boolean shouldExecute()
        {
        	float f = this.goalOwner.getBrightness();
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }
}
