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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class HellSpiderEntity extends SpiderEntity
{
    public HellSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
    	this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(HellSpiderEntity.class));
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.MAX_HEALTH, 15);
    }
    
    @Override
    public int getExperiencePoints(PlayerEntity player)
    {
        return player.getEntityWorld().getRandom().nextInt(12);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!source.isMagicDamage() && source.getImmediateSource() instanceof LivingEntity)
        {
        	LivingEntity livingentity = (LivingEntity)source.getImmediateSource();
        	if (!source.isExplosion())
        	{
        		livingentity.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
            }
        }
        
        return super.attackEntityFrom(source, amount);
    }
}
