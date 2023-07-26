package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractAerialHellSpiderEntity;
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
import net.minecraft.world.level.Level;

public class CrystalSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public CrystalSpiderEntity(EntityType<? extends Spider> type, Level worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new CrystalSpiderEntity.AttackGoal(this));
        this.targetSelector.addGoal(1, new CrystalSpiderEntity.TargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(CrystalSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ARMOR, 0)
                .add(Attributes.MAX_HEALTH, 24);
    }
    
    static class AttackGoal extends MeleeAttackGoal
    {
        public AttackGoal(Spider spider)
        {
           super(spider, 1.0D, true);
        }
        
        public boolean canContinueToUse()
        {
            float f = this.mob.getLightLevelDependentMagicValue(); //goalOwner (=attacker) .getBrightness()
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0)
            {
            	this.mob.setTarget((LivingEntity)null);
                return false;
            }
            else
            {
        	    return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity attackTarget)
        {
        	return (double)(4.0F + attackTarget.getBbWidth());
        }
    }
    
    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
    {
        public TargetGoal(Spider spider, Class<T> classTarget)
        {
        	super(spider, classTarget, true);
        }

        public boolean canUse()
        {
        	float f = this.mob.getLightLevelDependentMagicValue(); //goalOwner.getBrightness()
            return f >= 0.5F ? false : super.canUse();
        }
    }
}
