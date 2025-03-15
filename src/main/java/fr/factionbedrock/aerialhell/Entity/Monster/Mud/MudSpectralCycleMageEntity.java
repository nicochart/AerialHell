package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MudSpectralCycleMageEntity extends HostileEntity implements MudSpectralEntity
{
    @Nullable private MudCycleMageEntity master;
    public MudSpectralCycleMageEntity(EntityType<? extends MudSpectralCycleMageEntity> type, World world) {super(type, world);}

    public void setMaster(MudCycleMageEntity master) {this.master = master;}

    @Override protected void initGoals()
    {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(2, new AvoidSunlightGoal(this));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, TornSpiritEntity.class, true));
        this.goalSelector.add(4, new FleeEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return MudSpectralEntity.createSpectralAttributes(25.0D, 0.0D, 5.0D, 0.25D, 24.0D);
    }

    @Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        boolean flag = super.damage(serverWorld, source, amount);
        if (flag && this.master.isAlive()) //damage master without showing
        {
            if (!this.master.isInvulnerableTo(serverWorld, source) && this.master.getHealth() > 20.0F)
            {
                float amountToMaster = amount / (4 + this.master.getDifficulty());
                this.master.setHealth(this.master.getHealth() - amountToMaster);
            }
        }
        return flag;
    }

    @Override
    public void handleStatus(byte id)
    {
        if (id == 5) {this.popDisappearingParticles(this, 15);}
        else {super.handleStatus(id);}
    }

    @Override public void tick()
    {
        super.tick();
        this.spectralEntityTick(this);
    }

    public int getMaxTicksExisting() {return 700;}

    @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
}
