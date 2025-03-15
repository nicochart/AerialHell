package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CrystalSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public CrystalSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn) {super(type, worldIn);}
    
    @Override
    public void initGoals()
    {
        this.goalSelector.add(4, new CrystalSpiderEntity.AttackGoal(this));
        this.targetSelector.add(1, new CrystalSpiderEntity.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge(CrystalSpiderEntity.class));
        super.initGoals();
    }
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.ATTACK_DAMAGE, 4)
                .add(EntityAttributes.ARMOR, 0)
                .add(EntityAttributes.MAX_HEALTH, 24);
    }
    
    static class AttackGoal extends MeleeAttackGoal
    {
        public AttackGoal(SpiderEntity spider)
        {
           super(spider, 1.0D, true);
        }
        
        public boolean shouldContinue()
        {
            float f = this.mob.getBrightnessAtEyes(); //goalOwner (=attacker) .getBrightness()
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0)
            {
            	this.mob.setTarget((LivingEntity)null);
                return false;
            }
            else {return super.shouldContinue();}
        }
    }
    
    static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T>
    {
        public TargetGoal(SpiderEntity spider, Class<T> classTarget)
        {
        	super(spider, classTarget, true);
        }

        public boolean canStart()
        {
        	float f = this.mob.getBrightnessAtEyes(); //goalOwner.getBrightness()
            return f >= 0.5F ? false : super.canStart();
        }
    }
}
