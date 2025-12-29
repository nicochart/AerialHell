package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public class HellSpiderEntity extends AbstractAerialHellSpiderEntity
{
    private int timeNoThorns;
    public HellSpiderEntity(EntityType<? extends Spider> type, Level worldIn)
    {
        super(type, worldIn);
        this.timeNoThorns = 0;
    }
    
    @Override
    public void registerGoals()
    {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(HellSpiderEntity.class));
        super.registerGoals();
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ARMOR, 0)
                .add(Attributes.MAX_HEALTH, 32);
    }

    @Override public void tick()
    {
        if (this.invalidTimeNoThorns()) {this.timeNoThorns = 0;}
        else if (this.timeNoThorns > 0) {this.timeNoThorns--;}
        super.tick();
    }

    private boolean invalidTimeNoThorns()
    {
        return this.timeNoThorns < 0 || this.timeNoThorns > 45;
    }

    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
    {
        boolean flag = super.hurtServer(serverLevel, source, amount);

        if (flag && !source.is(DamageTypes.MAGIC) && source.getDirectEntity() instanceof LivingEntity livingentity)
        {
            boolean hasNoThorns = this.timeNoThorns > 0;
        	if (!hasNoThorns && !source.is(DamageTypes.EXPLOSION))
        	{
        		livingentity.hurt(this.damageSources().thorns(this), 2.0F);
            }
            this.timeNoThorns = hasNoThorns ? 30 : 45;
        }
        
        return flag;
    }
}
