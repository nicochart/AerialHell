package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class MudSpectralCycleMageEntity extends Monster implements MudSpectralEntity
{
    @Nullable private MudCycleMageEntity master;
    public MudSpectralCycleMageEntity(EntityType<? extends MudSpectralCycleMageEntity> type, Level level) {super(type, level);}

    public void setMaster(MudCycleMageEntity master) {this.master = master;}

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return MudSpectralEntity.createSpectralAttributes(25.0D, 0.0D, 5.0D, 0.25D, 24.0D);
    }

    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
    {
        boolean flag = super.hurtServer(serverLevel, source, amount);
        if (flag && this.master.isAlive()) //damage master without showing
        {
            if (!this.master.isInvulnerableTo(serverLevel, source) && this.master.getHealth() > 20.0F)
            {
                float amountToMaster = amount / (4 + this.master.getDifficulty());
                this.master.setHealth(this.master.getHealth() - amountToMaster);
            }
        }
        return flag;
    }

    @Override
    public void handleEntityEvent(byte id)
    {
        if (id == 5) {this.popDisappearingParticles(this, 15);}
        else {super.handleEntityEvent(id);}
    }

    @Override public void tick()
    {
        super.tick();
        this.spectralEntityTick(this);
    }

    public int getMaxTicksExisting() {return 700;}

    @Override protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_SKELETON_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH;}
}
