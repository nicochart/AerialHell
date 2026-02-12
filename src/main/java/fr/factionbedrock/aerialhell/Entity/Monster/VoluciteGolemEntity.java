package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemEntity extends AerialHellGolemEntity implements BeamAttackEntity
{
    public static final float MAX_BEAM_LENGTH = 30.0F;
    public static final int BEAMING_LOAD_DURATION = 35;
    public static final int BEAMING_OVERHEAT_DURATION = 60;
    public static final int BEAMING_TOTAL_DURATION = 260;
    public static final int BEAMING_COOLDOWN = 40;
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMING_PHASE = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
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

    @Override public void tick()
    {
        this.beamAttackTick();
        super.tick();
    }

    @Override public void playBeamSound(boolean start) {this.playSound(start ? AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START.get() : AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP.get(), 0.5F, 1.0F);}
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
        this.goalSelector.addGoal(4, new BeamAttackGoal(this, BEAMING_LOAD_DURATION, BEAMING_OVERHEAT_DURATION, BEAMING_TOTAL_DURATION, BEAMING_COOLDOWN));
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
}