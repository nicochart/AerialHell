package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteWardenHeadEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteWardenHeadEntity.class, EntityDataSerializers.INT);
    private final BeamAttackEntity.BeamAttackEntityInfo BEAM_ATTACK_ENTITY_INFO = new BeamAttackEntity.BeamAttackEntityInfo(ATTACK_TARGET_ID, BEAMING_PHASE);
    /* ----------------------------- */

    private MainBeamAttackGoal BEAM_ATTACK_GOAL;

    public VoluciteWardenHeadEntity(EntityType<? extends VoluciteWardenPartEntity> type, Level level) {super(type, level);}

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
        //this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        super.registerGoals();
        this.goalSelector.addGoal(4, BEAM_ATTACK_GOAL);
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /* -------------------------------------------------------------------------------------------------------- */
    /* ---------- BeamAttackEntity (Main beam) : Interface method implementation + specific behavior ---------- */
    /* -------------------------------------------------------------------------------------------------------- */
    @Override public BeamAttackEntityInfo getBeamAttackEntityInfo() {return this.BEAM_ATTACK_ENTITY_INFO;}

    @Override public float getMaxBeamLength() {return MAX_BEAM_LENGTH;}

    @Override public float getBeamScale(int beamingPhase) {return BeamAttackEntity.super.getBeamScale(beamingPhase) * 7.0F;}

    @Override public  int getLoadConvergenceDuration() {return 30;}

    @Override public Vec3 getBeamStartPos(Vec3 eyePos, float partialTick)
    {
        Vec3 targetPos = this.getBeamEndPos();

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
