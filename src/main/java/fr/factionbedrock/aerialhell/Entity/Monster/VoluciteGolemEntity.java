package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class VoluciteGolemEntity extends AerialHellGolemEntity
{
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
    private @Nullable LivingEntity clientSideCachedAttackTarget;

    public VoluciteGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.xpReward = 16;
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(ATTACK_TARGET_ID, 0);
    }

    public void setActiveAttackTarget(int activeAttackTargetId) {this.entityData.set(ATTACK_TARGET_ID, activeAttackTargetId);}
    public boolean hasActiveAttackTarget() {return this.entityData.get(ATTACK_TARGET_ID) != 0;}

    public @Nullable LivingEntity getActiveAttackTarget()
    {
        if (!this.hasActiveAttackTarget()) {return null;}
        else if (this.level().isClientSide())
        {
            if (this.clientSideCachedAttackTarget != null) {return this.clientSideCachedAttackTarget;}
            else
            {
                Entity entity = this.level().getEntity(this.entityData.get(ATTACK_TARGET_ID));
                if (entity instanceof LivingEntity)
                {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                }
                else {return null;}
            }
        }
        else {return this.getTarget();}
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 17.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(4, new GuardianAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        //super.registerGoals(); removed super registerGoals because need to remove MeleeAttackGoal to make it work (atm)
        //this.goalSelector.addGoal(1, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(6, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new ActiveRandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    @Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}

    public int getAttackDuration() {return 80;}

    static class GuardianAttackGoal extends Goal
    {
        private final VoluciteGolemEntity entity;
        private int attackTime;
        private final boolean elder;

        public GuardianAttackGoal(VoluciteGolemEntity entity)
        {
            this.entity = entity;
            elder = false;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse()
        {
            LivingEntity livingentity = this.entity.getTarget();
            return livingentity != null && livingentity.isAlive();
        }

        public boolean canContinueToUse()
        {
            return super.canContinueToUse() && (this.elder || this.entity.getTarget() != null && this.entity.distanceToSqr(this.entity.getTarget()) < (double)120.0F);
        }

        public void start()
        {
            this.attackTime = -10;
            this.entity.getNavigation().stop();
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.entity.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
            }

            this.entity.needsSync = true;
        }

        public void stop()
        {
            this.entity.setActiveAttackTarget(0);
            //this.entity.setTarget((LivingEntity)null);
            this.attackTime = 0;
            //this.entity.randomStrollGoal.trigger();
        }

        public boolean requiresUpdateEveryTick() {return true;}

        public void tick()
        {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.entity.getNavigation().stop();
                this.entity.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
                if (!this.entity.hasLineOfSight(livingentity))
                {
                    this.entity.setTarget((LivingEntity)null);
                }
                else
                {
                    if (this.entity.getActiveAttackTarget() == null) {this.entity.setActiveAttackTarget(livingentity.getId());}
                    ++this.attackTime;
                    if (this.attackTime == 0)
                    {
                        if (!this.entity.isSilent())
                        {
                            //sound part ? (will crash because entity is not guardian)
                            //this.entity.level().broadcastEntityEvent(this.entity, (byte)21);
                        }
                    }
                    else if (this.attackTime >= this.entity.getAttackDuration())
                    {
                        float f = 1.0F;
                        if (this.entity.level().getDifficulty() == Difficulty.HARD) {f += 2.0F;}

                        if (this.elder) {f += 2.0F;}

                        ServerLevel serverlevel = getServerLevel(this.entity);
                        livingentity.hurtServer(serverlevel, this.entity.damageSources().indirectMagic(this.entity, this.entity), f);
                        this.entity.doHurtTarget(serverlevel, livingentity);
                        this.stop();
                        //this.entity.setTarget((LivingEntity)null); do not want to lose target
                    }
                    super.tick();
                }
            }
        }
    }
}