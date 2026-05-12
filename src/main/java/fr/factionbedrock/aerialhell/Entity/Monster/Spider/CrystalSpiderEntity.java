package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import fr.factionbedrock.aerialhell.Entity.Monster.LunarMisleadableEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CrystalSpiderEntity extends AbstractAerialHellSpiderEntity implements LunarMisleadableEntity
{
    public CrystalSpiderEntity(EntityType<? extends Spider> type, Level worldIn)
    {
        super(type, worldIn);
    }

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public Mob getSelf() {return this;}
    /* ------------------------------------------------------------------- */

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
        this.goalSelector.addGoal(4, new AttackGoal(this));
        this.targetSelector.addGoal(1, new TargetGoal<>(this, Player.class, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget)));
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
            else {return super.canContinueToUse();}
        }
    }
    
    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
    {
        public TargetGoal(Spider spider, Class<T> classTarget, TargetingConditions.Selector selector)
        {
        	super(spider, classTarget, true, selector);
        }

        public boolean canUse()
        {
        	float f = this.mob.getLightLevelDependentMagicValue(); //goalOwner.getBrightness()
            return f >= 0.5F ? false : super.canUse();
        }
    }
}
