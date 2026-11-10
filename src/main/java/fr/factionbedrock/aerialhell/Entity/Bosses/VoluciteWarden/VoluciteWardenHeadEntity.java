package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.FlyMoveHelperController;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.FlyingLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenHeadFlyAroundCoreGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenHeadLightningGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartStatus;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class VoluciteWardenHeadEntity extends VoluciteWardenPartEntity implements BeamAttackEntity
{
    /* -- BeamAttackEntity fields -- */
    public static final int MAX_BEAM_LENGTH = 80;
    public static final int BEAMING_LOAD_DURATION = 82;
    public static final int BEAMING_OVERHEAT_DURATION = 118; //59 + 59
    public static final int BEAMING_TOTAL_DURATION = 260; //load 82 + normal & overheat 59 59 59 + 1 overlap
    public static final int BEAMING_COOLDOWN = 40;
    public static final float BEAM_TRACKING_SPEED_FACTOR = 0.48F; //much slower than the volucite golem's beam
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteWardenHeadEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteWardenHeadEntity.class, EntityDataSerializers.INT);
    private final BeamAttackEntity.BeamAttackEntityInfo BEAM_ATTACK_ENTITY_INFO = new BeamAttackEntity.BeamAttackEntityInfo(ATTACK_TARGET_ID, BEAMING_PHASE);
    /* ----------------------------- */

    private MainBeamAttackGoal BEAM_ATTACK_GOAL;
    private VoluciteWardenHeadLightningGoal LIGHTNING_GOAL;

    public VoluciteWardenHeadEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level)
    {
        super(type, level);
        //ghast move control, only used by the final phase flight. stops the head if it should not fly anymore (attached heads are positioned by the master)
        this.moveControl = new FlyMoveHelperController(this, () -> this.getPartStatus() == PartStatus.SEMI_DETACHED && !this.canFlyAroundCore());
    }

    public static AttributeSupplier.Builder registerAttributes() {return VoluciteWardenPartEntity.registerAttributes().add(Attributes.FLYING_SPEED, 0.06D);} //same flying speed as vanilla ghast

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);

        /* -- BeamAttackEntity synched data (main beam) -- */
        builder.define(ATTACK_TARGET_ID, 0);
        builder.define(BEAMING_PHASE, BeamingPhases.OFF);
        /* ----------------------------------------------- */
    }

    public void enableBeam(boolean enabled){if (this.BEAM_ATTACK_GOAL != null) {this.BEAM_ATTACK_GOAL.enabled = enabled;}}
    public boolean isBeamEnabled(){return this.BEAM_ATTACK_GOAL != null && this.BEAM_ATTACK_GOAL.enabled;}

    @Override public void tick()
    {
        super.tick();
        this.beamAttackTick();
    }

    @Override protected void registerGoals()
    {
        this.BEAM_ATTACK_GOAL = new MainBeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN);
        this.LIGHTNING_GOAL = new VoluciteWardenHeadLightningGoal(this);
        //this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        super.registerGoals();
        this.goalSelector.addGoal(4, BEAM_ATTACK_GOAL);
        this.goalSelector.addGoal(4, LIGHTNING_GOAL);
        this.goalSelector.addGoal(5, new VoluciteWardenHeadFlyAroundCoreGoal(this));
        this.goalSelector.addGoal(5, new FlightLookAroundGoal(this));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public boolean isNoGravity() {return !this.isDeadOrDying();} //flying part, even when detached

    //final phase : semi detached head, while its master is fighting
    public boolean canStrikeLightning() {return this.getPartStatus() == PartStatus.SEMI_DETACHED && this.isAlive() && !this.isMasterInDeadOrDyingPhase();}

    private boolean isMasterInDeadOrDyingPhase() {return this.getMaster() != null && this.getMaster().getSelf() instanceof VoluciteWardenEntity warden && warden.isInDeadOrDyingPhase();}

    //final phase : semi detached head, flying around the core like a ghast (see VoluciteWardenHeadFlyAroundCoreGoal)
    public boolean canFlyAroundCore() {return this.getPartStatus() == PartStatus.SEMI_DETACHED && this.isAlive() && this.getMaster() != null && this.getMaster().getSelf() instanceof VoluciteWardenEntity warden && warden.isInFinalPhase();}

    @Override public void travel(Vec3 input)
    {
        if (this.canFlyAroundCore()) {this.travelFlying(input, 0.02F);} //same as vanilla ghast (same air friction on every axis)
        else {super.travel(input);}
    }

    //ghast look goal (faces its target if it has one, else its movement direction), only during final phase flight
    private static class FlightLookAroundGoal extends FlyingLookAroundGoal
    {
        private final VoluciteWardenHeadEntity head;

        public FlightLookAroundGoal(VoluciteWardenHeadEntity head)
        {
            super(head);
            this.head = head;
        }

        @Override public boolean canUse() {return this.head.canFlyAroundCore();}
    }

    /* -------------------------------------------------------------------------------------------------------- */
    /* ---------- BeamAttackEntity (Main beam) : Interface method implementation + specific behavior ---------- */
    /* -------------------------------------------------------------------------------------------------------- */
    @Override public BeamAttackEntityInfo getBeamAttackEntityInfo() {return this.BEAM_ATTACK_ENTITY_INFO;}

    @Override public float getMaxBeamLength() {return MAX_BEAM_LENGTH;}

    @Override public float getBeamScale(int beamingPhase) {return BeamAttackEntity.super.getBeamScale(beamingPhase) * 7.0F;}

    @Override public  int getLoadConvergenceDuration() {return 40;}

    @Override public float getBeamTrackingSpeedFactor() {return BEAM_TRACKING_SPEED_FACTOR;}

    @Override public Vec3 getBeamStartPos(Vec3 eyePos, float partialTick)
    {
        Vec3 targetPos = this.getBeamEndPos(partialTick);

        if (targetPos == null) {return BeamAttackEntity.super.getBeamStartPos(eyePos, partialTick);}

        double eyeHeight = this.getEyeHeight();
        Vec3 pivot = eyePos.subtract(0.0D, eyeHeight, 0.0D);

        Vec3 dir = targetPos.subtract(eyePos).normalize();
        double horizontalDist = Math.sqrt(dir.x * dir.x + dir.z * dir.z);

        float pitchRad = (float) Math.atan2(dir.y, horizontalDist);
        float yawRad = (float) Math.atan2(dir.x, dir.z);

        double offX = 0.0D;
        double offY = eyeHeight;
        double offZ = 0.0D;

        Vec3 localEyeOffset = new Vec3(offX, offY, offZ);

        Vec3 rotatedOffset = localEyeOffset.xRot(pitchRad).yRot(yawRad);

        return pivot.add(rotatedOffset);
    }

    public static int MAIN_BEAM_OVERHEAT_COLOR = new Color(0, 255, 255).getRGB();
    @Override public int getBeamColor(int beamingPhase)
    {
        if (beamingPhase == BeamingPhases.BEAMING_OVERHEAT) {return MAIN_BEAM_OVERHEAT_COLOR;}
        return BeamAttackEntity.super.getBeamColor(beamingPhase);
    }

    @Override public boolean canBeamHitEntity(LivingEntity entity) {return this.getMaster() != null && !this.getMaster().is(entity) && !entity.is(AerialHellTags.Entities.VOLUCITE);}
    @Override public Entity getImmediateBeamSource() {return this;}
    @Override public Entity getTrueBeamSource() {return this.getMaster() != null ? this.getMaster().getSelf() : this;}

    @Override public boolean isBeamSilent() {return false;} //PartEntity is silent but Beam Sound is still played by this part

    @Override public SoundEvent getBeamLoadSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_BEAM_LOAD.get();} //load duration = 82
    @Override public SoundEvent getBeamStartSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_BEAM_START.get();} //start (useless to know, but 61 ticks)
    @Override public SoundEvent getBeamLoopSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_BEAM_LOOP.get();} //loop duration = 63 (interval 59 + 4 overlap)
    @Override public int getBeamLoopSoundRepeatInterval() {return 59;}
    @Override public int getBeamLoopSoundOverlap() {return 4;} //real duration - repeat interval
    /* -------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------- */

    public static class MainBeamAttackGoal extends BeamAttackGoal
    {
        public boolean enabled;

        public MainBeamAttackGoal(BeamAttackEntity entity, int beamingLoadDuration, int beamingOverheatDuration, int beamingTotalDuration, int cooldownDuration)
        {
            super(entity, beamingLoadDuration, beamingOverheatDuration, beamingTotalDuration, cooldownDuration);
            this.enabled = false;
        }

        @Override public void setLookAt(@NotNull Vec3 beamTargetPos) {}

        @Override public boolean canUse() {return this.enabled && super.canUse();}
        @Override public boolean canContinueToUse() {return this.enabled && super.canContinueToUse();}
    }
}
