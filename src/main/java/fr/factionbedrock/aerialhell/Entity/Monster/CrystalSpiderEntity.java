package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractAerialHellSpiderEntity;
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
import net.minecraft.world.World;

public class CrystalSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public CrystalSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new CrystalSpiderEntity.AttackGoal(this));
        this.targetSelector.addGoal(1, new CrystalSpiderEntity.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(CrystalSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.MAX_HEALTH, 15);
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
