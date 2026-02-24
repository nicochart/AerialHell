package fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem;

import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemHeadEntity extends HostileEntity implements PartEntity, BeamAttackEntity
{
    /* -- PartEntity fields -- */
    private static final TrackedData<Integer> MASTER_ID = DataTracker.registerData(VoluciteGolemHeadEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private MasterPartEntity master;
    protected int ticksInInvalidSituation;
    /* ----------------------- */

    /* -- BeamAttackEntity fields -- */
    public static final float MAX_BEAM_LENGTH = 30.0F;
    public static final int BEAMING_LOAD_DURATION = 35;
    public static final int BEAMING_OVERHEAT_DURATION = 60;
    public static final int BEAMING_TOTAL_DURATION = 260;
    public static final int BEAMING_COOLDOWN = 40;
    private static final TrackedData<Integer> ATTACK_TARGET_ID = DataTracker.registerData(VoluciteGolemHeadEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BEAMING_PHASE = DataTracker.registerData(VoluciteGolemHeadEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> BEAM_TARGET_POS_NEEDS_SYNC = DataTracker.registerData(VoluciteGolemHeadEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private @Nullable LivingEntity clientSideCachedAttackTarget;
    private Vec3d beamTargetPos;
    private Vec3d prevBeamTargetPos;
    private Vec3d beamEndPos;
    private Vec3d prevBeamEndPos;
    /* ----------------------------- */

    public VoluciteGolemHeadEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);

        /* -- PartEntity init -- */
        this.initPart();
        /* --------------------- */
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        /* -- BeamAttackEntity synched data -- */
        builder.add(ATTACK_TARGET_ID, 0);
        builder.add(BEAMING_PHASE, BeamingPhases.OFF);
        builder.add(BEAM_TARGET_POS_NEEDS_SYNC, false);
        /* ----------------------------------- */

        /* -- PartEntity synched data -- */
        builder.add(MASTER_ID, 0);
        /* ----------------------------- */
    }

    @Override protected void initGoals()
    {
        this.goalSelector.add(4, new BeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 100.0D)
                .add(EntityAttributes.ARMOR, 1.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1D)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(EntityAttributes.FOLLOW_RANGE, 48.0D);
    }

    @Override public void tick()
    {
        /* -- PartEntity & BeamAttackEntity tick -- */
        this.partEntityTick(); //PartEntity tick delegate to interface
        this.beamAttackTick(); //BeamAttackEntity tick delegate to interface
        /* ---------------------------------------- */
        super.tick();
    }

    @Override public MobEntity getSelf() {return this;} //both PartEntity & BeamAttackEntity need this implementation
    @Override @Nullable public VoluciteGolemEntity getMaster() {return (VoluciteGolemEntity) PartEntity.super.getMaster();}

    /* ------------------------------------------------------------------- */
    /* ---------- PartEntity : Interface methods implementation ---------- */
    /* ------------------------------------------------------------------- */
    @Override public boolean partSuperDamage(ServerWorld world, DamageSource source, float amount) {return super.damage(world, source, amount);}
    @Override public boolean isPartAlwaysInvulnerableTo(DamageSource damageSource) {return super.isAlwaysInvulnerableTo(damageSource);}

    @Override public TrackedData<Integer> getMasterIdData() {return MASTER_ID;}
    @Override public void setMasterRaw(MasterPartEntity master) {this.master = master;}
    @Override @Nullable public MasterPartEntity getMasterRaw() {return this.master;}

    @Override public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    @Override public void setTickInInvalidSituation(int newValue) {this.ticksInInvalidSituation = newValue;}
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */

    /* ----------------------------------------------------------------------------------------- */
    /* ---------- PartEntity : Superclass methods Overridden to delegate to interface ---------- */
    /* ----------------------------------------------------------------------------------------- */
    @Override public void pushAwayFrom(Entity other) {if (this.canPartBePushAwayBy(other)) {super.pushAwayFrom(other);}}
    //@Override public void tick() {this.onTick(); super.tick();} done above for both PartEntity & BeamAttackEntity

    @Override public final boolean damage(ServerWorld world, DamageSource source, float amount) {return this.partDamage(world, source, amount, false);}

    @Override public boolean isPartOf(Entity other) {return super.isPartOf(other) || this.recognizesPart(other);}
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */

    /* --------------------------------------------------------------------------------------------- */
    /* ----------- PartEntity : Superclass methods Overridden for part-specific behavior ----------- */
    /* --------------------------------------------------------------------------------------------- */
    @Override public boolean isPushable() {return false;}
    @Override public boolean isInsideWall() {return false;}
    @Override public boolean isAttackable() {return true;}
    @Override public boolean handleFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}
    @Override public boolean hasNoGravity() {return !this.isDead();}
    @Override public boolean isSilent() {return true;}
    @Override public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {return false;}
    @Override public void takeKnockback(double strength, double x, double z) {;}
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------- */
    /* ---------- PartEntity : Interface methods Overridden for specific behavior ---------- */
    /* ------------------------------------------------------------------------------------- */
    @Override public void onPartDeath()
    {
        this.setBeamingPhaseToOff();
    }

    @Override public boolean partDamage(ServerWorld world, DamageSource source, float amount, boolean forceLocalDamage)
    {
        boolean flag = PartEntity.super.partDamage(world, source, amount, forceLocalDamage);
        if (flag && this.updateTargetOnHurtByFarAwayLivingEntity())
        {
            Entity trueSourceEntity = source.getAttacker();
            if (trueSourceEntity instanceof LivingEntity && !EntityHelper.isCreativePlayer(trueSourceEntity) && this.distanceTo(trueSourceEntity) > 10)
            {
                this.setTarget((LivingEntity) trueSourceEntity);
            }
        }
        return flag;
    }
    /* ------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------- */
    /* ---------- BeamAttackEntity : Interface methods implementation ---------- */
    /* ------------------------------------------------------------------------- */
    @Override public TrackedData<Integer> getBeamTargetEntityIdData() {return ATTACK_TARGET_ID;}
    @Override public TrackedData<Integer> getBeamingPhaseData() {return BEAMING_PHASE;}
    @Override public TrackedData<Boolean> getBeamTargetPosNeedsSyncData() {return BEAM_TARGET_POS_NEEDS_SYNC;}

    @Override @Nullable public LivingEntity getClientSideCachedAttackTarget() {return this.clientSideCachedAttackTarget;}
    @Override @Nullable public Vec3d getBeamTargetPos() {return getBeamAttackTarget() != null ? beamTargetPos : null;}
    @Override @Nullable public Vec3d getPrevBeamTargetPos() {return getBeamAttackTarget() != null ? prevBeamTargetPos : null;}
    @Override @Nullable public Vec3d getBeamEndPos() {return getBeamAttackTarget() != null ? beamEndPos : null;}
    @Override @Nullable public Vec3d getPrevBeamEndPos() {return getBeamAttackTarget() != null ? prevBeamEndPos : null;}

    @Override public void setClientSideCachedAttackTarget(@Nullable LivingEntity entity) {this.clientSideCachedAttackTarget = entity;}
    @Override public void setBeamTargetPos(Vec3d pos) {this.beamTargetPos = pos;}
    @Override public void setPrevBeamTargetPos(Vec3d pos) {this.prevBeamTargetPos = pos;}
    @Override public void setBeamEndPos(Vec3d pos) {this.beamEndPos = pos;}
    @Override public void setPrevBeamEndPos(Vec3d pos) {this.prevBeamEndPos = pos;}
    /* ------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------- */
    /* ---------- BeamAttackEntity : Interface methods Overridden for specific behavior ---------- */
    /* ------------------------------------------------------------------------------------------- */
    //@Override public Vec3d getBeamStartPos() {return this.getEyePos().add(0.0F, -1.0F, 0.0F);} moves the server-side beam but not the render, due to eyeHeight hard-coded usage in render
    @Override public boolean canBeamHitEntity(LivingEntity entity) {return this.getMaster() != null && !this.getMaster().is(entity);}
    @Override public Entity getImmediateBeamSource() {return this;}
    @Override public Entity getTrueBeamSource() {return this.getMaster() != null ? this.getMaster() : this;}
    @Override public void onStartBeaming(int beamingDuration) {if (this.getMaster() != null) {this.getMaster().addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, beamingDuration, 2, false, false));}}
    @Override public void onStopBeaming() {if (this.getMaster() != null) {this.getMaster().removeStatusEffect(StatusEffects.SLOWNESS);}}

    @Override public boolean isBeamSilent() {return false;} //PartEntity is silent but Beam Sound is still played by this part
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */

    public boolean updateTargetOnHurtByFarAwayLivingEntity() {return true;}
}
