package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSegmentJumpGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSegmentTargetGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartStatus;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.PositionAndRotation;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class VoluciteWardenArmSegmentEntity extends VoluciteWardenPartEntity implements BeamAttackEntity
{
    private int beamEnableDelay = -1;
    private int beamEnabledTicks = 0;

    /* -- BeamAttackEntity fields -- */
    public static final int MAX_BEAM_LENGTH = 50;
    public static final int BEAMING_LOAD_DURATION = 42;
    public static final int BEAMING_OVERHEAT_DURATION = 140;
    public static final int BEAMING_TOTAL_DURATION = 226; //load 42 + normal & overheat 60 60 60 + 4 (beam overlap)
    public static final int BEAMING_COOLDOWN = 100;
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteWardenArmSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteWardenArmSegmentEntity.class, EntityDataSerializers.INT);
    private final BeamAttackEntityInfo BEAM_ATTACK_ENTITY_INFO = new BeamAttackEntityInfo(ATTACK_TARGET_ID, BEAMING_PHASE);
    /* ----------------------------- */

    private ArmSegmentBeamAttackGoal BEAM_ATTACK_GOAL;

    public VoluciteWardenArmSegmentEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level) {super(type, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        /* -- BeamAttackEntity synched data -- */
        builder.define(ATTACK_TARGET_ID, 0);
        builder.define(BEAMING_PHASE, BeamingPhases.OFF);
        /* ----------------------------------- */
    }

    @Override protected void registerGoals()
    {
        this.BEAM_ATTACK_GOAL = new ArmSegmentBeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN);
        //this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        super.registerGoals();
        this.goalSelector.addGoal(4, BEAM_ATTACK_GOAL);
        this.goalSelector.addGoal(5, new VoluciteWardenArmSegmentJumpGoal(this));
        this.targetSelector.addGoal(1, new VoluciteWardenArmSegmentTargetGoal(this)); //final phase only
    }

    @Override public void tick()
    {
        this.beamAttackTick();
        super.tick();
        if (this.canJumpAround()) {this.turnTowardTarget();} //after vanilla body and head rotation updates

        //only in semi detached phase (master final phase)
        if (this.level().isClientSide() && this.canJumpAround()) {this.tickLandingParticles();}
    }

    @Override public void onPartDeath()
    {
        this.setBeamingPhaseToOff();
        this.disableBeam(); //specific to volucite warden
    }

    /* ---------- BeamAttackEntity : Interface implementation ---------- */
    @Override public BeamAttackEntityInfo getBeamAttackEntityInfo() {return this.BEAM_ATTACK_ENTITY_INFO;}
    @Override public boolean canBeamHitEntity(LivingEntity entity) {return this.getMaster() != null && !this.getMaster().is(entity) && !entity.is(AerialHellTags.Entities.VOLUCITE);}
    @Override public Entity getImmediateBeamSource() {return this;}
    @Override public Entity getTrueBeamSource() {return this.getMaster() != null ? this.getMaster().getSelf() : this;}

    //specific to arm segment
    @Override public void onStartBeaming(int beamingDuration) {}
    @Override public void onStopBeaming() {}
    @Override public boolean isBeamSilent() {return true;}

    /* ---------- Warden arm beam specificities ---------- */

    @Override public void updateBeamPositions()
    {
        //default behavior if there is a target
        LivingEntity target = this.getSyncedTarget();
        if (target != null && target.isAlive())
        {
            BeamAttackEntity.super.updateBeamPositions();
            return;
        }

        //fallback (if no available target)
        //custom calculation to make a circle around x axis (relative to master)
        //the beam target position rotates around the x-axis centered on the master, and beamEnabledTicks is used for angle calculation.
        //below setting allows (each time the beam is activated) to follow a semicircle starting horizontally from the back of the master to the front (horizontally).
        boolean lowHalfCircle = true;

        Vec3 beamTargetPos = this.getBeamTargetPos();
        Vec3 beamEndPos = this.getBeamEndPos();

        if (beamTargetPos == null) {return;}

        Vec3 prevBeamTargetPos = this.getPrevBeamTargetPos();
        Vec3 previousStep = beamTargetPos.subtract(prevBeamTargetPos);

        this.setPrevBeamTargetPos(beamTargetPos);
        this.setPrevBeamEndPos(beamEndPos);

        Vec3 startPos = this.getBeamStartPos();
        LivingEntity referenceEntity = this.getMaster() != null ? this.getMaster().getSelf() : this.getSelf();

        float yaw = referenceEntity.yBodyRot;
        float pitch = 0.0F;

        Vec3 forwardVec = Vec3.directionFromRotation(pitch, yaw);
        Vec3 rightVec = forwardVec.cross(new Vec3(0, 1, 0)).normalize();
        Vec3 upVec = rightVec.cross(forwardVec).normalize();

        double rotationSpeed = 0.016D;
        double beamAngle = (lowHalfCircle ? Math.PI : 0) + this.beamEnabledTicks * rotationSpeed;
        double radius = 8.0D;

        Vec3 patternTargetPos = startPos.add(forwardVec.scale(Math.cos(beamAngle) * radius)).add(upVec.scale(Math.sin(beamAngle) * radius));

        // ------- Shaking -------
        double shakeAmplitude = 1.0D;
        Vec3 shake = rightVec.scale(Math.cos(beamAngle * 0.8D) * shakeAmplitude).add(upVec.scale(Math.sin(beamAngle * 0.7D) * shakeAmplitude));
        patternTargetPos = patternTargetPos.add(shake);
        // -----------------------

        this.updateBeamTargetPosRealisticWithInertia(previousStep, patternTargetPos, prevBeamTargetPos, 0.95F, 0.05F , 0.8F);
        this.updateBeamEndPos(this.getBeamTargetPos(), this.getMaxBeamLength());
    }

    @Override public void beamAttackTick()
    {
        BeamAttackEntity.super.beamAttackTick();

        //target evaluation
        if (this.isBeaming()) {this.beamEnabledTicks++;}
        else
        {
            this.beamEnabledTicks = 0;
            //can't set target null here because target is updated on queue enable beam. set target to null on beam disable
            //if (!this.level().isClientSide()) {this.setTarget(null);}
        }

        //delay after activation
        if (this.beamEnableDelay > 0) {this.beamEnableDelay--;}
        else if (this.beamEnableDelay == 0)
        {
            this.enableBeam();
            this.beamEnableDelay = -1;
        }
    }

    //temporarily removed this to avoid the segment "jump" between the "looking forward" position and the "looking to initial pos"
    /*
    @Override @NotNull public Vec3 getBeamTargetDefaultInitialPos()
    {
        boolean lowHalfCircle = true;
        double beamInitialAngle = lowHalfCircle ? Math.PI : 0;

        LivingEntity referenceEntity = this.getMaster() != null ? this.getMaster().getSelf() : this.getSelf();

        float yaw = referenceEntity.yBodyRot;
        float pitch = 0.0F;
        double radius = 8.0D;

        Vec3 forwardVec = Vec3.directionFromRotation(pitch, yaw);
        Vec3 rightVec = forwardVec.cross(new Vec3(0, 1, 0)).normalize();
        Vec3 upVec = rightVec.cross(forwardVec).normalize();

        //initial pos of semi-circular pattern
        Vec3 beamTargetInitialPos = this.getBeamStartPos().add(forwardVec.scale(Math.cos(beamInitialAngle) * radius)).add(upVec.scale(Math.sin(beamInitialAngle) * radius));

        return beamTargetInitialPos;
    }*/

    public void queueBeamEnable(int delayTicks)
    {
        if (delayTicks <= 0) {this.enableBeam();}
        else {this.beamEnableDelay = delayTicks;}
    }

    public void enableBeam()
    {
        if (this.BEAM_ATTACK_GOAL != null) {this.BEAM_ATTACK_GOAL.enabled = true;}
    }

    public void disableBeam()
    {
        if (this.BEAM_ATTACK_GOAL != null) {this.BEAM_ATTACK_GOAL.enabled = false;}
        this.beamEnableDelay = -1;
        this.beamEnabledTicks = 0;
        if (!this.level().isClientSide()) {this.setTarget(null);}
    }

    @Override public float getMaxBeamLength() {return MAX_BEAM_LENGTH;}

    /* -------------------------------------------------------------------- */
    /* ---------- Final phase : VoluciteWardenArmSegmentJumpGoal ---------- */
    /* -------------------------------------------------------------------- */
    public static final float LANDING_DAMAGE = 6.0F;
    public static final float LANDING_DAMAGE_RANGE = 4.0F; //horizontal range around the segment hitbox
    public static final float LANDING_KNOCKBACK_SCALE = 1.5F;
    public static final float MAX_TURN_PER_TICK = 10.0F; //degrees
    public static final double LANDING_PARTICLES_MIN_FALL_HEIGHT = 1.0D;
    private boolean wasOnGround = true; //client side, see tickLandingParticles
    private double highestAirborneY; //client side, see tickLandingParticles

    @Nullable public VoluciteWardenEntity getWardenMaster() {return this.getMaster() != null && this.getMaster().getSelf() instanceof VoluciteWardenEntity warden ? warden : null;}

    public boolean canJumpAround()
    {
        VoluciteWardenEntity warden = this.getWardenMaster();
        return this.getPartStatus() == PartStatus.SEMI_DETACHED && this.isAlive() && warden != null && warden.isInFinalPhase();
    }

    //server side : same sound as the arms strike attack, damage and knockback around the segment
    //the landing particles are spawned client side, see tickLandingParticles
    public void onJumpLanding()
    {
        if (!(this.level() instanceof ServerLevel level)) {return;}

        Vec3 landingPos = this.position();
        level.playSound(null, landingPos.x, landingPos.y, landingPos.z, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.HOSTILE, 4.0F, (1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F) * 0.7F); //vanilla explosion sound (this entity is silent)

        AABB damageArea = this.getBoundingBox().inflate(LANDING_DAMAGE_RANGE, 1.0D, LANDING_DAMAGE_RANGE);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, damageArea, this::canBeHurtByLanding))
        {
            entity.hurtServer(level, this.damageSources().mobAttack(this), LANDING_DAMAGE);
            Vec3 knockback = new Vec3(entity.getX() - landingPos.x, 0.0D, entity.getZ() - landingPos.z).normalize().scale(LANDING_KNOCKBACK_SCALE);
            entity.push(knockback.x, 0.5D, knockback.z);
        }

        this.needsSync = true; //landing pos and ground state sent at the end of this tick : client side landing particles (see tickLandingParticles)
    }

    private boolean canBeHurtByLanding(LivingEntity entity) {return entity.isAlive() && !EntityHelper.isVoluciteEntity(entity) && !EntityHelper.isCreaOrSpecPlayer(entity);}

    //both sides (the synced target is known client side) : the segment progressively turns toward its own target (see VoluciteWardenArmSegmentTargetGoal)
    private void turnTowardTarget()
    {
        LivingEntity target = this.getSyncedTarget();
        if (target == null) {return;}

        float targetYaw = (float) (Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX()) * Mth.RAD_TO_DEG) - 90.0F;
        float yaw = Mth.approachDegrees(this.yBodyRot, targetYaw, MAX_TURN_PER_TICK);
        this.setYRot(yaw);
        this.yBodyRot = yaw;
        this.yHeadRot = yaw;
    }

    //client side : landing particles when the segment touches the ground (like vanilla AbstractCubeMob), no additional sync : the ground state comes with the server move packet
    //client side pos is still interpolated (in the air) when this packet is received : particles at the interpolation target pos (landing pos)
    //only after a real fall : not when a player starts tracking a segment already on the ground (client side ground state is false until the first move packet)
    private void tickLandingParticles()
    {
        boolean onGround = this.onGround();
        if (!onGround) {this.highestAirborneY = this.wasOnGround ? this.getY() : Math.max(this.highestAirborneY, this.getY());}
        else if (!this.wasOnGround)
        {
            PositionAndRotation interpolationTarget = this.getInterpolation().target();
            Vec3 landingPos = interpolationTarget != null ? interpolationTarget.position() : this.position();
            VoluciteWardenEntity warden = this.getWardenMaster();
            if (warden != null && this.highestAirborneY - landingPos.y > LANDING_PARTICLES_MIN_FALL_HEIGHT) {warden.spawnStrikeParticles(landingPos, 0.5F, 0.25F);} //fewer particles than the strike (many segments)
        }
        this.wasOnGround = onGround;
    }

    /* -------------------------------------------------------------------- */
    /* -------------------------------------------------------------------- */
    /* -------------------------------------------------------------------- */

    public static class ArmSegmentBeamAttackGoal extends BeamAttackGoal
    {
        public boolean enabled;

        public ArmSegmentBeamAttackGoal(BeamAttackEntity entity, int beamingLoadDuration, int beamingOverheatDuration, int beamingTotalDuration, int cooldownDuration)
        {
            super(entity, beamingLoadDuration, beamingOverheatDuration, beamingTotalDuration, cooldownDuration);
            this.enabled = false;
        }

        //redirect beam sound to master
        @Override public void tickBeamSounds(int currentBeamingTime, int loadDuration)
        {
            if (this.getGoalOwner() instanceof PartEntity armSegment && armSegment.getMaster() != null && armSegment.getMaster().getSelf() instanceof VoluciteWardenEntity master)
            {
                master.armsBeamAttackHandler.tickBeamSounds(this.currentBeamingTime(), this.beamingLoadDuration(), this.beamingTotalDuration());
            }
        }

        @Override public boolean canUse()
        {
            if (!this.decreaseAndCheckCooldown()) {return false;}
            return this.enabled; //&& super.canUse();} currently ignoring target condition because beam can do a pattern instead of focusing a living entity
        }

        @Override public boolean canContinueToUse() {return this.enabled;} // && super.canContinueToUse();} currently ignoring target condition because beam can do a pattern instead of focusing a living entity
    }
}
