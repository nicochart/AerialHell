package fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemHeadEntity extends PartEntity implements BeamAttackEntity
{
    public static final float MAX_BEAM_LENGTH = 30.0F;
    public static final int BEAMING_LOAD_DURATION = 35;
    public static final int BEAMING_OVERHEAT_DURATION = 60;
    public static final int BEAMING_TOTAL_DURATION = 260;
    public static final int BEAMING_COOLDOWN = 40;
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteGolemHeadEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteGolemHeadEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BEAM_TARGET_POS_NEEDS_SYNC = SynchedEntityData.defineId(VoluciteGolemHeadEntity.class, EntityDataSerializers.BOOLEAN);
    private @Nullable LivingEntity clientSideCachedAttackTarget;
    private Vec3 beamTargetPos;
    private Vec3 prevBeamTargetPos;
    private Vec3 beamEndPos;
    private Vec3 prevBeamEndPos;

    public VoluciteGolemHeadEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
    }

    @Override @Nullable public VoluciteGolemEntity getOwner() {return (VoluciteGolemEntity) super.getOwner();}

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(4, new BeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(ATTACK_TARGET_ID, 0);
        builder.define(BEAMING_PHASE, BeamingPhases.OFF);
        builder.define(BEAM_TARGET_POS_NEEDS_SYNC, false);
    }

    @Override public EntityDataAccessor<Integer> getBeamTargetEntityIdData() {return ATTACK_TARGET_ID;}
    @Override public EntityDataAccessor<Integer> getBeamingPhaseData() {return BEAMING_PHASE;}
    @Override public EntityDataAccessor<Boolean> getBeamTargetPosNeedsSyncData() {return BEAM_TARGET_POS_NEEDS_SYNC;}

    @Override @Nullable public LivingEntity getClientSideCachedAttackTarget() {return this.clientSideCachedAttackTarget;}
    @Override @Nullable public Vec3 getBeamTargetPos() {return getBeamAttackTarget() != null ? beamTargetPos : null;}
    @Override @Nullable public Vec3 getPrevBeamTargetPos() {return getBeamAttackTarget() != null ? prevBeamTargetPos : null;}
    @Override @Nullable public Vec3 getBeamEndPos() {return getBeamAttackTarget() != null ? beamEndPos : null;}
    @Override @Nullable public Vec3 getPrevBeamEndPos() {return getBeamAttackTarget() != null ? prevBeamEndPos : null;}

    @Override public void setClientSideCachedAttackTarget(@Nullable LivingEntity entity) {this.clientSideCachedAttackTarget = entity;}
    @Override public void setBeamTargetPos(Vec3 pos) {this.beamTargetPos = pos;}
    @Override public void setPrevBeamTargetPos(Vec3 pos) {this.prevBeamTargetPos = pos;}
    @Override public void setBeamEndPos(Vec3 pos) {this.beamEndPos = pos;}
    @Override public void setPrevBeamEndPos(Vec3 pos) {this.prevBeamEndPos = pos;}
    @Override public Mob getSelf() {return this;}

    //@Override public Vec3 getBeamStartPos() {return this.getEyePosition().add(0.0F, -1.0F, 0.0F);} moves the server-side beam but not the render, due to eyeHeight hard-coded usage in render
    @Override public boolean canBeamHitEntity(LivingEntity entity) {return this.getOwner() != null && !this.getOwner().is(entity);}
    @Override public Entity getImmediateBeamSource() {return this;}
    @Override public Entity getTrueBeamSource() {return this.getOwner() != null ? this.getOwner() : this;}
    @Override public void onStartBeaming(int beamingDuration) {if (this.getOwner() != null) {this.getOwner().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, beamingDuration, 2, false, false));}}
    @Override public void onStopBeaming() {if (this.getOwner() != null) {this.getOwner().removeEffect(MobEffects.SLOWNESS);}}

    @Override public void tick()
    {
        this.beamAttackTick();
        super.tick();
    }

    //@Override public void lerpHeadRotationStep(int lerpHeadSteps, double lerpYHeadRot)
    //{
    //    super.lerpHeadRotationStep(lerpHeadSteps, lerpYHeadRot);
    //}

    @Override public void playBeamSound(boolean start) {this.playSound(start ? AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START.get() : AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP.get(), 0.5F, 1.0F);}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ARMOR, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}

    @Override public void knockback(double strength, double x, double z) {}
}
