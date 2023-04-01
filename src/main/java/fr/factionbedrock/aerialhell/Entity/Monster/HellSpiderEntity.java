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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class HellSpiderEntity extends AbstractAerialHellSpiderEntity
{
    public HellSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(HellSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5)
                .createMutableAttribute(Attributes.ARMOR, 0)
                .createMutableAttribute(Attributes.MAX_HEALTH, 32);
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
