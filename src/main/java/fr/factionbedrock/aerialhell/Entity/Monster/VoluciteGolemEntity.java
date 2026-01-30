package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.ActiveLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
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
import net.minecraft.world.entity.EntitySelector;
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
import net.minecraft.world.phys.*;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class VoluciteGolemEntity extends AerialHellGolemEntity
{
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BEAMING = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BEAM_TARGET_POS_NEEDS_SYNC = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private @Nullable LivingEntity clientSideCachedAttackTarget;
    private Vec3 beamTargetPos;
    private Vec3 prevBeamTargetPos;
    private Vec3 beamEndPos;
    private Vec3 prevBeamEndPos;

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

    public void setActiveAttackTargetId(int activeAttackTargetId) {this.entityData.set(ATTACK_TARGET_ID, activeAttackTargetId);}
    public int getActiveAttackTargetId() {return this.entityData.get(ATTACK_TARGET_ID);}
    public boolean hasActiveAttackTargetId() {return this.entityData.get(ATTACK_TARGET_ID) != 0;}
    public boolean isBeaming() {return this.entityData.get(BEAMING);}
    public void setBeaming(boolean bool) {this.entityData.set(BEAMING, bool);}
    public boolean beamingTargetPosNeedsSync() {return this.entityData.get(BEAM_TARGET_POS_NEEDS_SYNC);}
    private void setBeamingTargetPosNeedsSync() {this.entityData.set(BEAM_TARGET_POS_NEEDS_SYNC, true);}

    public @Nullable LivingEntity getActiveAttackTarget()
    {
        if (!this.hasActiveAttackTargetId()) {return null;}
        else if (this.level().isClientSide()) //Client side
        {
            if (this.clientSideCachedAttackTarget != null && this.clientSideCachedAttackTarget.getId() == this.getActiveAttackTargetId()) //if client cached target exists & is valid (same id as synced id)
            {
                return this.clientSideCachedAttackTarget;
            }
            else //trying to update clientSideCachedAttackTarget
            {
                Entity entity = this.level().getEntity(this.getActiveAttackTargetId());
                if (entity instanceof LivingEntity)
                {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                }
                else {return null;}
            }
        }
        else //Server side
        {
            LivingEntity serverSideTarget = this.getTarget();
            if (serverSideTarget == null) {return null;}

            if (serverSideTarget.getId() != this.getActiveAttackTargetId()) //updating synced id if necessary
            {
                this.setActiveAttackTargetId(serverSideTarget.getId());
            }
            return serverSideTarget;
        }
    }

    public @Nullable Vec3 getBeamTargetPos() {return getActiveAttackTarget() != null ? beamTargetPos : null;}
    public @Nullable Vec3 getPrevBeamTargetPos() {return getActiveAttackTarget() != null ? prevBeamTargetPos : null;}
    public @Nullable Vec3 getBeamEndPos() {return getActiveAttackTarget() != null ? beamEndPos : null;}
    public @Nullable Vec3 getPrevBeamEndPos() {return getActiveAttackTarget() != null ? prevBeamEndPos : null;}
    public Vec3 getBeamStartPos() {return this.getEyePosition();}
    private static Vec3 getBeamOffset(Entity target) {return new Vec3(0, target.getBoundingBox().getYsize() * 0.75F, 0);}

    public void updateBeamPositions() //must be deterministic to be calculated the same way on both client and server sides
    {
        LivingEntity beamTarget = this.getActiveAttackTarget(); //the active target is the only synchronized thing
        //beamTargetPos is not the real target pos. It is a fictitious "target" following real target.
        if (beamTarget == null || beamTargetPos == null) {return;}

        Vec3 previousStep = beamTargetPos.subtract(prevBeamTargetPos);
        prevBeamTargetPos = beamTargetPos;
        prevBeamEndPos = beamEndPos;

        //beamTarget update - fictitious "target" following real target
        float inertia = 0.99F; //0.95F
        float stepScale = 0.05F; //0.04F
        float maxStepLength = 0.4F; //0.3F allows easier dodge
        Vec3 attackTargetPos = beamTarget.position().add(getBeamOffset(beamTarget));

        Vec3 preTreatmentStep = attackTargetPos.subtract(beamTargetPos);
        Vec3 acceleration = preTreatmentStep.scale(stepScale);

        Vec3 step = previousStep.scale(inertia).add(acceleration);
        beamTargetPos = beamTargetPos.add(step.length() < maxStepLength ? step : step.normalize().scale(maxStepLength));

        //beamEnd update - the pos where the beam hits a solid obstacle
        Vec3 beamStart = this.getBeamStartPos();
        Vec3 direction = beamTargetPos.subtract(beamStart).normalize();
        double maxDistance = 30.0D;
        Vec3 furthestBeamEnd = beamStart.add(direction.scale(maxDistance));

        BlockHitResult beamHit = this.level().clip(new ClipContext(this.getEyePosition(), furthestBeamEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        beamEndPos = beamHit.getType() == HitResult.Type.BLOCK ? beamHit.getLocation() : furthestBeamEnd;
    }

    @Override public void tick()
    {
        boolean beamingSyncFlag = this.beamingTargetPosNeedsSync();
        if (beamingSyncFlag)
        {
            this.entityData.set(BEAM_TARGET_POS_NEEDS_SYNC, false);

            //forcing client side beam target (entity and pos) update
            this.clientSideCachedAttackTarget = null;
            this.beamTargetPos = null;
            this.prevBeamTargetPos = null;
            this.beamEndPos = null;
            this.prevBeamEndPos = null;
            if (!this.level().isClientSide() && this.getTarget() != null)
            {
                this.setActiveAttackTargetId(this.getTarget().getId());
            }
        }
        else if (this.isBeaming())
        {
            //initializing beam pos if not done yet
            boolean needsInitialization = this.beamTargetPos == null;
            if (needsInitialization && this.getActiveAttackTarget() != null && this.hasActiveAttackTargetId())
            {
                Vec3 targetInitialPos = this.getActiveAttackTarget().position().add(getBeamOffset(this.getActiveAttackTarget()));;
                this.beamTargetPos = targetInitialPos;
                this.prevBeamTargetPos = targetInitialPos;
            }

            this.updateBeamPositions();
        }

        //should never happen
        //if (this.getActiveAttackTarget() != null && this.getActiveAttackTarget().getId() != this.getActiveAttackTargetId())
        //{
        //    this.clientSideCachedAttackTarget = null;
        //    this.beamTargetPos = null;
        //    this.prevBeamTargetPos = null;
        //    this.beamEndPos = null;
        //    this.prevBeamEndPos = null;
        //}

        /* Beam debug
        if (beamingSyncFlag) {System.out.println("[Tick "+this.level().getGameTime()+"] "+(this.level().isClientSide() ? "Client : " : "Server : ") + "-- SYNCED --");}
        if (this.isBeaming())
        {
            boolean printPos = false;
            System.out.println("[Tick "+this.level().getGameTime()+"] "+(this.level().isClientSide() ? "Client : " : "Server : ") + "synced target id is "+this.getActiveAttackTargetId()+", real id is "+(this.getActiveAttackTarget() == null ? "null" : this.getActiveAttackTarget().getId()));
            if (printPos && this.getActiveAttackTarget() != null)
            {
                Vec3 pos = this.getActiveAttackTarget().position();
                System.out.println("[Tick "+this.level().getGameTime()+"] "+(this.level().isClientSide() ? "Client : " : "Server : ") + "real target pos is "+createVec3String(pos));
            }
            System.out.println("[Tick "+this.level().getGameTime()+"] "+(this.level().isClientSide() ? "Client : " : "Server : ") + (this.beamEndPos == null ? "beamEndPos = null" : "beamEndPos = " + createVec3String(beamEndPos)) + ", " + (this.prevBeamEndPos == null ? "prevBeamEndPos = null" : "prevBeamEndPos = " + createVec3String(prevBeamEndPos)));
        }

        if (this.getBeamEndPos() != null)
        {
            Vec3 beamStart = this.getBeamStartPos();
            Vec3 beamEnd = this.getBeamEndPos();
            Vec3 delta = beamEnd.subtract(beamStart);
            double distance = delta.length();

            if (distance < 0.001) return;

            Vec3 direction = delta.normalize();

            double particleStep = 0.2;

            for (double traveled = 0.0; traveled <= distance; traveled += particleStep)
            {
                Vec3 pos = beamStart.add(direction.scale(traveled));
                this.level().addParticle(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
            }
        }
        */

        super.tick();
    }

    //tmp debug method - string creation for Vec3
    private static String createVec3String(Vec3 vec)
    {
        float x = (int) ((vec.x * 100)) / 100.0F;
        float y = (int) ((vec.y * 100)) / 100.0F;
        float z = (int) ((vec.z * 100)) / 100.0F;
        return x + ", " + y + ", " + z;
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
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(4, new BeamAttackGoal(this, this.getBeamingDuration(), this.getBeamingCooldown()));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        //super.registerGoals(); removed super registerGoals because need to remove MeleeAttackGoal to make it work (atm)
        this.goalSelector.addGoal(5, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(6, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new ActiveRandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    @Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}

    public int getBeamingDuration() {return 160;}
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
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK)); //can't disable move and look flags because they are needed to avoid parasite head position change by move controls.. will need to separate head and body.
        }

        @Override public boolean canUse()
        {
            if (this.beamingCooldown > 0) {this.beamingCooldown--; return false;}
            LivingEntity livingentity = this.entity.getTarget();
            return livingentity != null && livingentity.isAlive();
        }

        @Override public boolean canContinueToUse()
        {
            return super.canContinueToUse() && (this.entity.getTarget() != null /*&& this.entity.distanceToSqr(this.entity.getTarget()) < (double)480.0F*/);
        }

        @Override public void start()
        {
            this.currentBeamingTime = 0;
            this.entity.setBeaming(true);
            //this.entity.getNavigation().stop(); slowness for the duration ?
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
            this.entity.setActiveAttackTargetId(0);
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
            LivingEntity livingentity = this.entity.getActiveAttackTarget();
            if (livingentity != null)
            {
                Vec3 beamTargetPos = this.entity.getBeamTargetPos();
                if (beamTargetPos != null)
                {
                    this.entity.getLookControl().setLookAt(beamTargetPos.x, beamTargetPos.y, beamTargetPos.z, 90.0F, 90.0F);
                }
                //if (!this.entity.hasLineOfSight(livingentity))
                //{
                //    this.entity.setTarget(null);
                //}
                if (false) {}
                else
                {
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
                        List<Entity> hitEntities = getBeamHitEntities(this.entity.level(), this.entity, this.entity.getBeamStartPos(), this.entity.getBeamEndPos());

                        for (Entity entity : hitEntities)
                        {
                            if (entity instanceof LivingEntity livingHit)
                            {
                                livingHit.hurtServer(serverlevel, AerialHellDamageTypes.getDamageSource(this.entity.level(), AerialHellDamageTypes.GOLEM_BEAM, this.entity, this.entity), damage);
                            }
                        }
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

        public static List<Entity> getBeamHitEntities(Level level, LivingEntity beamingEntity, Vec3 beamStart, Vec3 beamEnd)
        {
            AABB boxFromBeamStartToBeamEnd = new AABB(beamStart, beamEnd).inflate(1.0);

            List<Entity> entitiesInBox = level.getEntities(beamingEntity, boxFromBeamStartToBeamEnd, EntitySelector.ENTITY_STILL_ALIVE);

            List<Entity> hits = new ArrayList<>();

            for (Entity entity : entitiesInBox)
            {
                AABB hitbox = entity.getBoundingBox().inflate(0.3);
                hitbox.clip(beamStart, beamEnd).ifPresent(vec3 -> hits.add(new EntityHitResult(entity, vec3).getEntity()));
            }

            return hits;
        }
    }
}