package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenHealBeaconFindWardenGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenHealBeaconRepulsionGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.SyncedTargetEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.awt.Color;

//beacon healing the volucite warden (like end crystals with the ender dragon), looking like a set of volucite blocks
//its target is the volucite warden to heal (synced, for particles & heal beam render). it never targets an entity to attack
//states : inactive (no target) -> active (charging, particles toward the warden) -> healing (heal beam)
//each time it loses 25% of its max health while active or healing : attacking (repulsion goal), then active again (shorter charge)
public class VoluciteWardenHealBeaconEntity extends Mob implements SyncedTargetEntity
{
    public static final float HEAL_RANGE = 32.0F; //max distance between beacon core and warden core
    public static final float HEAL_AMOUNT = 0.5F; //per tick
    public static final int FIRST_CHARGE_DURATION = 200; //ticks in active state before healing, when the warden is found
    public static final int CHARGE_DURATION_AFTER_ATTACK = 100; //ticks in active state before healing again, after an attack
    public static final float ATTACK_HEALTH_STEP = 0.25F; //the beacon attacks each time it loses this ratio of its max health
    public static final float CORE_RELATIVE_HEIGHT = 1.5F; //core center, relative to the beacon position

    public static final float REPULSION_RANGE = 12.0F;
    private static final double REPULSION_MAX_STRENGTH = 2.0D, REPULSION_MIN_STRENGTH = 0.6D; //horizontal push, near the core & at max range
    private static final double REPULSION_VERTICAL_STRENGTH = 0.5D;

    public static final int ACTIVE_COLOR = new Color(139, 138, 223).getRGB(); //color of the active layer (charge particles)
    public static final int HEALING_COLOR = new Color(88, 199, 156).getRGB(); //color of the healing layer (heal beam)
    private static final DustParticleOptions CHARGE_PARTICLE = new DustParticleOptions(ACTIVE_COLOR, 1.5F);
    private static final int CHARGE_PARTICLES_PER_TICK = 5;

    /* -- Beacon state fields -- */
    private static final EntityDataAccessor<Integer> BEACON_STATE = SynchedEntityData.defineId(VoluciteWardenHealBeaconEntity.class, EntityDataSerializers.INT);
    public static final int INACTIVE = 0, ACTIVE = 1, HEALING = 2, ATTACKING = 3;
    private int chargeTicks, chargeDuration; //active state (server side)
    private int lostHealthSteps; //number of ATTACK_HEALTH_STEP of max health already lost (server side)
    /* ------------------------- */

    /* -- SyncedTargetEntity fields -- */
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(VoluciteWardenHealBeaconEntity.class, EntityDataSerializers.INT);
    private final SyncedTargetEntityInfo SYNCED_TARGET_ENTITY_INFO = new SyncedTargetEntityInfo(ATTACK_TARGET_ID);
    /* ------------------------------- */

    private VoluciteWardenHealBeaconRepulsionGoal REPULSION_GOAL; //only server side

    public VoluciteWardenHealBeaconEntity(EntityType<? extends VoluciteWardenHealBeaconEntity> type, Level level) {super(type, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(BEACON_STATE, INACTIVE);
        /* -- SyncedTargetEntity synched data -- */
        builder.define(ATTACK_TARGET_ID, 0);
        /* ------------------------------------- */
    }

    @Override protected void registerGoals()
    {
        this.REPULSION_GOAL = new VoluciteWardenHealBeaconRepulsionGoal(this, VoluciteWardenHealBeaconRepulsionGoal.PREPARATION_DURATION, VoluciteWardenHealBeaconRepulsionGoal.AFTER_REPULSION_DURATION);
        this.goalSelector.addGoal(1, this.REPULSION_GOAL);
        this.targetSelector.addGoal(1, new VoluciteWardenHealBeaconFindWardenGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0D);
    }

    /* ------- SyncedTargetEntity : Interface method implementation ------- */
    @Override public SyncedTargetEntityInfo getSyncedTargetEntityInfo() {return this.SYNCED_TARGET_ENTITY_INFO;}
    @Override public Mob getSelf() {return this;}
    /* -------------------------------------------------------------------- */

    @Override public void tick()
    {
        super.tick();
        /* SyncedTargetEntity tick */
        this.syncedTargetEntityTick();
        /* ----------------------- */

        if (this.level().isClientSide()) {this.tickChargeParticles();}
        else if (this.isAlive()) {this.tickBeaconState();}
    }

    /* ------------------------------------------- */
    /* ---------- Beacon state (server) ---------- */
    /* ------------------------------------------- */

    private void tickBeaconState()
    {
        @Nullable VoluciteWardenEntity warden = this.getHealTarget();
        if (warden == null) //losing the target is the only way to become inactive
        {
            if (!this.isBeaconInactive())
            {
                this.REPULSION_GOAL.cancel();
                this.setBeaconState(INACTIVE);
                this.playBeaconSound(SoundEvents.BEACON_DEACTIVATE);
            }
            return;
        }

        if (this.isBeaconInactive())
        {
            this.startCharging(FIRST_CHARGE_DURATION);
            this.playBeaconSound(SoundEvents.BEACON_ACTIVATE);
        }
        else if (this.isBeaconActive())
        {
            if (++this.chargeTicks >= this.chargeDuration) {this.setBeaconState(HEALING);}
        }
        else if (this.isBeaconHealing()) {warden.heal(HEAL_AMOUNT);}
        //attacking state is handled by the repulsion goal
    }

    private void startCharging(int duration)
    {
        this.chargeTicks = 0;
        this.chargeDuration = duration;
        this.setBeaconState(ACTIVE);
    }

    //called by the repulsion goal when it stops
    public void onRepulsionGoalEnd()
    {
        if (this.getHealTarget() != null) {this.startCharging(CHARGE_DURATION_AFTER_ATTACK);}
        else {this.setBeaconState(INACTIVE);}
    }

    public int getBeaconState() {return this.entityData.get(BEACON_STATE);} //both sides
    public void setBeaconState(int state) {this.entityData.set(BEACON_STATE, state);} //server side

    public boolean isBeaconInactive() {return this.getBeaconState() == INACTIVE;}
    public boolean isBeaconActive() {return this.getBeaconState() == ACTIVE;}
    public boolean isBeaconHealing() {return this.getBeaconState() == HEALING;}
    public boolean isBeaconAttacking() {return this.getBeaconState() == ATTACKING;}

    /* ------------------------------------------ */
    /* ---------- Heal target (warden) ---------- */
    /* ------------------------------------------ */

    //server side target, if it is still a valid volucite warden to heal
    @Nullable public VoluciteWardenEntity getHealTarget()
    {
        return this.getTarget() instanceof VoluciteWardenEntity warden && this.isValidHealTarget(warden) ? warden : null;
    }

    public boolean isValidHealTarget(VoluciteWardenEntity warden)
    {
        return warden.isAlive() && !warden.isInDeadOrDyingPhase() && this.getCoreCenterPos(1.0F).distanceToSqr(getWardenCoreCenterPos(warden, 1.0F)) <= HEAL_RANGE * HEAL_RANGE;
    }

    public Vec3 getCoreCenterPos(float partialTick) {return this.getPosition(partialTick).add(0.0F, CORE_RELATIVE_HEIGHT, 0.0F);}
    public static Vec3 getWardenCoreCenterPos(VoluciteWardenEntity warden, float partialTick) {return warden.getPosition(partialTick).add(0.0F, VoluciteWardenEntity.CORE_CENTER_RELATIVE_HEIGHT, 0.0F);}

    //client side : particles between the beacon and the warden while charging
    private void tickChargeParticles()
    {
        if (!this.isBeaconActive() || !(this.getSyncedTarget() instanceof VoluciteWardenEntity warden)) {return;}

        Vec3 start = this.getCoreCenterPos(1.0F);
        Vec3 path = getWardenCoreCenterPos(warden, 1.0F).subtract(start);
        for (int i = 0; i < CHARGE_PARTICLES_PER_TICK; i++)
        {
            Vec3 pos = start.add(path.scale(this.random.nextDouble()));
            this.level().addParticle(CHARGE_PARTICLE, pos.x + this.random.nextGaussian() * 0.2D, pos.y + this.random.nextGaussian() * 0.2D, pos.z + this.random.nextGaussian() * 0.2D, 0.0D, 0.0D, 0.0D);
        }
    }

    /* ---------------------------------------- */
    /* ---------- Damage & repulsion ---------- */
    /* ---------------------------------------- */

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        if (source.getEntity() != null && source.getEntity().is(AerialHellTags.Entities.VOLUCITE)) {return false;} //not hurt by the warden or its allies
        boolean hurt = super.hurtServer(level, source, amount);
        if (hurt && this.isAlive()) {this.updateLostHealthSteps();}
        return hurt;
    }

    //each time ATTACK_HEALTH_STEP of max health is lost, the beacon attacks (only if it is active or healing)
    private void updateLostHealthSteps()
    {
        int lostHealthSteps = this.computeLostHealthSteps();
        if (lostHealthSteps > this.lostHealthSteps)
        {
            this.lostHealthSteps = lostHealthSteps;
            if (this.isBeaconActive() || this.isBeaconHealing()) {this.REPULSION_GOAL.trigger();}
        }
    }

    private int computeLostHealthSteps() {return Mth.floor((1.0F - this.getHealth() / this.getMaxHealth()) / ATTACK_HEALTH_STEP + 1.0E-4F);}

    @Override public void die(DamageSource damageSource)
    {
        super.die(damageSource);
        if (!this.level().isClientSide()) //a dead beacon stops healing / attacking (beacon state is not ticked anymore)
        {
            this.REPULSION_GOAL.cancel();
            this.setBeaconState(INACTIVE);
        }
    }

    //called by the repulsion goal : pushes nearby entities away from the core, without damage
    public void repulseNearbyEntities()
    {
        if (!(this.level() instanceof ServerLevel level)) {return;}

        Vec3 corePos = this.getCoreCenterPos(1.0F);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(REPULSION_RANGE), this::canRepulse))
        {
            Vec3 offset = entity.position().subtract(corePos);
            double distance = offset.length();
            if (distance > REPULSION_RANGE) {continue;}

            double horizontalDistance = offset.horizontalDistance();
            Vec3 direction = horizontalDistance > 1.0E-4D ? new Vec3(offset.x / horizontalDistance, 0.0D, offset.z / horizontalDistance) : new Vec3(1.0D, 0.0D, 0.0D);
            double strength = Mth.lerp(distance / REPULSION_RANGE, REPULSION_MAX_STRENGTH, REPULSION_MIN_STRENGTH);
            entity.push(direction.x * strength, REPULSION_VERTICAL_STRENGTH, direction.z * strength);
            entity.syncVelocity = true; //sends the push to the client (player movement is client side)
        }

        level.sendParticles(ParticleTypes.EXPLOSION, corePos.x, corePos.y, corePos.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        level.playSound(null, corePos.x, corePos.y, corePos.z, SoundEvents.GENERIC_EXPLODE, this.getSoundSource(), 4.0F, 0.6F);
    }

    private boolean canRepulse(LivingEntity entity)
    {
        return entity != this && entity.isAlive() && !entity.is(AerialHellTags.Entities.VOLUCITE) && !EntityHelper.isCreaOrSpecPlayer(entity);
    }

    private void playBeaconSound(SoundEvent sound)
    {
        Vec3 corePos = this.getCoreCenterPos(1.0F);
        this.level().playSound(null, corePos.x, corePos.y, corePos.z, sound, this.getSoundSource(), 2.0F, 1.0F);
    }

    @Override protected void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.lostHealthSteps = this.computeLostHealthSteps(); //health steps lost before saving do not trigger attacks again
    }

    /* ------------------------------------------------ */
    /* ---------- Block-like (static) entity ---------- */
    /* ------------------------------------------------ */

    @Override public boolean canBeCollidedWith(@Nullable Entity entity) {return true;} //block-like collision
    @Override public boolean isNoGravity() {return true;}
    @Override public boolean isPushable() {return false;}
    @Override public boolean isPushedByFluid() {return false;}
    @Override public boolean isInWall() {return false;}
    @Override public boolean canBreatheUnderwater() {return true;}
    @Override public boolean canBeLeashed() {return false;}
    @Override public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
    @Override public void knockback(double strength, double x, double z, DamageSource damageSource, float damage) {}
    @Override public void knockback(double strength, double x, double z, DamageSource damageSource, float damage, boolean comesFromEffect) {}

    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.STONE_HIT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.STONE_BREAK;}
}
