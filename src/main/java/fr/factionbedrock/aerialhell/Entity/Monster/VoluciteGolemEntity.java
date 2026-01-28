package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class VoluciteGolemEntity extends AerialHellGolemEntity
{
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BEAMING = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BEAM_TARGET_POS_NEEDS_SYNC = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private @Nullable LivingEntity clientSideCachedAttackTarget;
    private Vec3 beamTargetPos = new Vec3(0, 0 ,0);
    private Vec3 prevBeamTargetPos = new Vec3(0, 0 ,0);
    private Vec3 beamEndPos = new Vec3(0, 0 ,0);
    private Vec3 prevBeamEndPos = new Vec3(0, 0 ,0);

    public VoluciteGolemEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
        this.xpReward = 16;
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(ATTACK_TARGET_ID, 0);
        builder.define(BEAMING, false);
        builder.define(BEAM_TARGET_POS_NEEDS_SYNC, false);
    }

    public void setActiveAttackTarget(int activeAttackTargetId) {this.entityData.set(ATTACK_TARGET_ID, activeAttackTargetId);}
    public boolean hasActiveAttackTarget() {return this.entityData.get(ATTACK_TARGET_ID) != 0;}
    public boolean isBeaming() {return this.entityData.get(BEAMING);}
    public void setBeaming(boolean bool) {this.entityData.set(BEAMING, bool);}
    public boolean beamingTargetPosNeedsSync() {return this.entityData.get(BEAM_TARGET_POS_NEEDS_SYNC);}
    private void setBeamingTargetPosNeedsSync() {this.entityData.set(BEAM_TARGET_POS_NEEDS_SYNC, true);}

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

    public @Nullable Vec3 getBeamTargetPos() {return getActiveAttackTarget() != null ? beamTargetPos : null;}
    public @Nullable Vec3 getPrevBeamTargetPos() {return getActiveAttackTarget() != null ? prevBeamTargetPos : null;}
    public @Nullable Vec3 getBeamEndPos() {return getActiveAttackTarget() != null ? beamEndPos : null;}
    public @Nullable Vec3 getPrevBeamEndPos() {return getActiveAttackTarget() != null ? prevBeamEndPos : null;}

    public void updateBeamPositions() //must be deterministic to be calculated the same way on both client and server sides
    {
        LivingEntity beamTarget = this.getActiveAttackTarget(); //the active target is the only synchronized thing
        if (beamTarget == null) {return;}
        prevBeamTargetPos = beamTargetPos;
        prevBeamEndPos = beamEndPos;

        //beamTarget update - fictitious "target" following real target
        double yOffset = beamTarget.getBoundingBox().getYsize() / 2;
        Vec3 attackTargetPos =  beamTarget.position().add(0, yOffset, 0);

        Vec3 toTarget = attackTargetPos.subtract(beamTargetPos);
        double distance = toTarget.length();
        double maxStep = 0.18D;

        if (distance <= maxStep) {beamTargetPos = attackTargetPos;}
        else
        {
            Vec3 step = toTarget.normalize().scale(maxStep);
            beamTargetPos = beamTargetPos.add(step);
        }

        //beamEnd update - the pos where the beam hits a solid obstacle
        Vec3 beamStart = this.getEyePosition();
        Vec3 direction = beamTargetPos.subtract(beamStart).normalize();
        double maxDistance = 30.0;
        Vec3 furthestBeamEnd = beamStart.add(direction.scale(maxDistance));

        BlockHitResult beamHit = this.level().clip(new ClipContext(this.getEyePosition(), furthestBeamEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        beamEndPos = beamHit.getType() == HitResult.Type.BLOCK ? beamHit.getLocation() : furthestBeamEnd;
    }

    @Override public void tick()
    {
        if (this.beamingTargetPosNeedsSync())
        {
            this.entityData.set(BEAM_TARGET_POS_NEEDS_SYNC, false);
            if (this.beamTargetPos.x == 0 && this.beamTargetPos.y == 0 && this.beamTargetPos.z == 0) //beaming start
            {
                if (this.getActiveAttackTarget() != null)
                {
                    this.beamTargetPos = this.getActiveAttackTarget().position();
                    this.prevBeamTargetPos = this.getActiveAttackTarget().position();
                }
            }
            else //beaming stop
            {
                this.beamTargetPos = new Vec3(0, 0 ,0);
                this.prevBeamTargetPos = new Vec3(0, 0 ,0);
            }
        }
        else
        {
            this.updateBeamPositions();
        }
        super.tick();
    }

    public void playBeamSound() {this.playSound(AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_SHOOT.get(), 1.0F, 0.8F);}
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}

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
        this.goalSelector.addGoal(4, new BeamAttackGoal(this, this.getBeamingDuration(), this.getBeamingCooldown()));
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

    public int getBeamingDuration() {return 80;}
    public int getBeamingCooldown() {return 40;}

    static class BeamAttackGoal extends Goal
    {
        private final VoluciteGolemEntity entity;
        private int currentBeamingTime;
        private int beamingDuration;
        private int beamingCooldown;
        private int beamingCooldownDuration;

        public BeamAttackGoal(VoluciteGolemEntity entity, int beamingDuration, int cooldownDuration)
        {
            this.entity = entity;
            this.currentBeamingTime = 0;
            this.beamingDuration = beamingDuration;
            this.beamingCooldown = 0;
            this.beamingCooldownDuration = cooldownDuration;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override public boolean canUse()
        {
            if (this.beamingCooldown > 0) {this.beamingCooldown--; return false;}
            LivingEntity livingentity = this.entity.getTarget();
            return livingentity != null && livingentity.isAlive();
        }

        @Override public boolean canContinueToUse()
        {
            return super.canContinueToUse() && (this.entity.getTarget() != null && this.entity.distanceToSqr(this.entity.getTarget()) < (double)240.0F);
        }

        @Override public void start()
        {
            this.currentBeamingTime = 0;
            this.entity.setBeaming(true);
            this.entity.getNavigation().stop();
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.entity.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
            }

            if (!this.entity.isSilent()) {this.entity.playBeamSound();}

            this.entity.needsSync = true;
            this.entity.setBeamingTargetPosNeedsSync();
        }

        @Override public void stop()
        {
            this.entity.setActiveAttackTarget(0);
            this.entity.setBeaming(false);
            //this.entity.setTarget((LivingEntity)null);
            this.beamingCooldown = beamingCooldownDuration;
            this.currentBeamingTime = 0;
            //this.entity.randomStrollGoal.trigger();
            this.entity.setBeamingTargetPosNeedsSync();
        }

        @Override public boolean requiresUpdateEveryTick() {return true;}

        @Override public void tick()
        {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.entity.getNavigation().stop();
                this.entity.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
                if (!this.entity.hasLineOfSight(livingentity))
                {
                    this.entity.setTarget(null);
                }
                else
                {
                    if (this.entity.getActiveAttackTarget() == null) {this.entity.setActiveAttackTarget(livingentity.getId());}
                    ++this.currentBeamingTime;
                    if (this.currentBeamingTime < 0.1F * this.beamingDuration)
                    {
                        //beam loading
                    }
                    else if (this.currentBeamingTime < this.beamingDuration)
                    {
                        //full power
                        float damage = 4.0F;
                        if (this.entity.level().getDifficulty() == Difficulty.HARD) {damage += 2.0F;}

                        ServerLevel serverlevel = getServerLevel(this.entity);
                        livingentity.hurtServer(serverlevel, AerialHellDamageTypes.getDamageSource(this.entity.level(), AerialHellDamageTypes.GOLEM_BEAM, this.entity, this.entity), damage);
                        //this.entity.doHurtTarget(serverlevel, livingentity); hit animation off
                    }
                    else //if (this.currentBeamingTime >= this.beamingDuration)
                    {
                        this.stop();
                    }
                    super.tick();
                }
            }
        }
    }
}