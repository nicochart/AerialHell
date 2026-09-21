package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Util.DebugHelper;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class VoluciteWardenArmSegmentEntity extends VoluciteWardenPartEntity implements BeamAttackEntity
{
    private int beamEnableDelay = -1;
    private int beamEnabledTicks = 0;

    /* -- BeamAttackEntity fields -- */
    public static final int MAX_BEAM_LENGTH = 50;
    public static final int BEAMING_LOAD_DURATION = 35;
    public static final int BEAMING_OVERHEAT_DURATION = 140;
    public static final int BEAMING_TOTAL_DURATION = 200;
    public static final int BEAMING_COOLDOWN = 20;
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteWardenArmSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteWardenArmSegmentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BEAM_TARGET_POS_NEEDS_SYNC = SynchedEntityData.defineId(VoluciteWardenArmSegmentEntity.class, EntityDataSerializers.BOOLEAN);
    private final BeamAttackEntityInfo BEAM_ATTACK_ENTITY_INFO = new BeamAttackEntityInfo(ATTACK_TARGET_ID, BEAMING_PHASE, BEAM_TARGET_POS_NEEDS_SYNC);
    /* ----------------------------- */

    private VoluciteWardenArmBeamAttackGoal BEAM_ATTACK_GOAL;

    public VoluciteWardenArmSegmentEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level) {super(type, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        /* -- BeamAttackEntity synched data -- */
        builder.define(ATTACK_TARGET_ID, 0);
        builder.define(BEAMING_PHASE, BeamingPhases.OFF);
        builder.define(BEAM_TARGET_POS_NEEDS_SYNC, false);
        /* ----------------------------------- */
    }

    @Override protected void registerGoals()
    {
        this.BEAM_ATTACK_GOAL = new VoluciteWardenArmBeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN);
        //this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        super.registerGoals();
        this.goalSelector.addGoal(4, BEAM_ATTACK_GOAL);
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public void tick()
    {
        this.beamAttackTick();
        super.tick();
    }

    @Override public void onPartDeath()
    {
        this.setBeamingPhaseToOff();
        this.disableBeam(); //specific to volucite warden
    }

    /* ---------- BeamAttackEntity : Interface implementation ---------- */
    @Override public BeamAttackEntityInfo getBeamAttackEntityInfo() {return this.BEAM_ATTACK_ENTITY_INFO;}
    @Override public boolean canBeamHitEntity(LivingEntity entity) {return this.getMaster() != null && !this.getMaster().is(entity);}
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

        //TODO fix initialisation problem (beamTargetPos is never initialized in super beamAttackTick because target is null)
        //+fix beam jumping from pos to another pos on target change (I think it's either due to "beamingTargetPosNeedsSync" or "needsInitialization")
        if (beamTargetPos == null) {return;}

        this.setPrevBeamTargetPos(beamTargetPos);
        this.setPrevBeamEndPos(beamEndPos);

        Vec3 startPos = this.getBeamStartPos();
        LivingEntity referenceEntity = this.getMaster() != null ? this.getMaster().getSelf() : this.getSelf();

        float yaw = referenceEntity.yBodyRot;
        float pitch = 0.0F;

        Vec3 forwardVec = Vec3.directionFromRotation(pitch, yaw);
        Vec3 rightVec = forwardVec.cross(new Vec3(0, 1, 0)).normalize();
        Vec3 upVec = rightVec.cross(forwardVec).normalize();

        double rotationSpeed = 0.018D;
        double beamAngle = (lowHalfCircle ? Math.PI : 0) + this.beamEnabledTicks * rotationSpeed;
        double radius = 8.0D;

        Vec3 patternTargetPos = startPos.add(forwardVec.scale(Math.cos(beamAngle) * radius)).add(upVec.scale(Math.sin(beamAngle) * radius));

        // ------- Shaking -------
        double shakeAmplitude = 1.0D;
        Vec3 shake = rightVec.scale(Math.cos(beamAngle * 0.8D) * shakeAmplitude).add(upVec.scale(Math.sin(beamAngle * 0.7D) * shakeAmplitude));
        patternTargetPos = patternTargetPos.add(shake);
        // -----------------------

        this.setBeamTargetPos(patternTargetPos);
        this.updateBeamEndPos(this.getBeamTargetPos(), this.getMaxBeamLength());
    }

    @Override public void beamAttackTick()
    {
        BeamAttackEntity.super.beamAttackTick();

        //target evaluation
        if (this.isBeaming())
        {
            this.beamEnabledTicks++;

            if (!this.level().isClientSide())
            {
                LivingEntity currentTarget = this.getSyncedTarget();

                if (this.getMaster() != null && this.getMaster().getSelf() instanceof VoluciteWardenEntity master)
                {
                    //master evaluate current target (and maybe assign a new target)
                    LivingEntity assignedTarget = master.evaluateSegmentTarget(this, currentTarget);
                    if (assignedTarget != currentTarget) {this.setTarget(assignedTarget);}
                }
                else
                {
                    //security if part is detached from master
                    this.disableBeam();
                }
            }
        }
        else
        {
            this.beamEnabledTicks = 0;
            if (!this.level().isClientSide()) {this.setTarget(null);}
        }

        //delay after activation
        if (this.beamEnableDelay > 0) {this.beamEnableDelay--;}
        else if (this.beamEnableDelay == 0)
        {
            this.enableBeam();
            this.beamEnableDelay = -1;
        }
    }

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
    }

    @Override public float getMaxBeamLength() {return MAX_BEAM_LENGTH;}

    public static class VoluciteWardenArmBeamAttackGoal extends BeamAttackGoal
    {
        public boolean enabled;

        public VoluciteWardenArmBeamAttackGoal(BeamAttackEntity entity, int beamingLoadDuration, int beamingOverheatDuration, int beamingTotalDuration, int cooldownDuration)
        {
            super(entity, beamingLoadDuration, beamingOverheatDuration, beamingTotalDuration, cooldownDuration);
            this.enabled = false;
        }

        //redirect beam sound to master
        @Override public void makeBeamSound()
        {
            if (this.getGoalOwner() instanceof PartEntity armSegment && armSegment.getMaster() != null && armSegment.getMaster().getSelf() instanceof VoluciteWardenEntity master)
            {
                master.makeBeamSound();
            }
        }

        @Override public void makeBeamStartSound()
        {
            if (this.getGoalOwner() instanceof PartEntity armSegment && armSegment.getMaster() != null && armSegment.getMaster().getSelf() instanceof VoluciteWardenEntity master)
            {
                master.makeBeamStartSound();
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
