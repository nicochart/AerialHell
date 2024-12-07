package fr.factionbedrock.aerialhell.Entity.Monster.Spider;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class HellSpiderEntity extends AbstractAerialHellSpiderEntity
{
    private int timeNoThorns;
    public HellSpiderEntity(EntityType<? extends SpiderEntity> type, World world)
    {
        super(type, world);
        this.timeNoThorns = 0;
    }
    
    @Override
    public void initGoals()
    {
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge(HellSpiderEntity.class));
        super.initGoals();
    }
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 32);
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

    @Override public boolean damage(DamageSource source, float amount)
    {
        boolean flag = super.damage(source, amount);

        if (flag && !source.isOf(DamageTypes.MAGIC) && source.getSource() instanceof LivingEntity livingentity)
        {
            boolean hasNoThorns = this.timeNoThorns > 0;
        	if (!hasNoThorns && !source.isOf(DamageTypes.EXPLOSION))
        	{
        		livingentity.damage(this.getDamageSources().thorns(this), 2.0F);
            }
            this.timeNoThorns = hasNoThorns ? 30 : 45;
        }
        
        return flag;
    }
}
