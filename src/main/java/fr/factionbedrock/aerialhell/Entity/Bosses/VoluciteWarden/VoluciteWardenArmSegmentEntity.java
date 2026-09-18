package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class VoluciteWardenArmSegmentEntity extends VoluciteWardenPartEntity implements BeamAttackEntity
{
    private int beamEnableDelay = -1;

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
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
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

    @Override public void beamAttackTick()
    {
        BeamAttackEntity.super.beamAttackTick();
        //Warden arm beam specificities (delay)
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

        @Override public boolean canUse() {return this.enabled && super.canUse();}
        @Override public boolean canContinueToUse() {return this.enabled && super.canContinueToUse();}
    }
}
