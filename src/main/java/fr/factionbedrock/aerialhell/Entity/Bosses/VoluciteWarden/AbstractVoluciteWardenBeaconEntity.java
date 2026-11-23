package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Monster.SyncedTargetEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

//volucite warden beacons, with synced "target" (linked volucite warden)
public abstract class AbstractVoluciteWardenBeaconEntity extends Mob implements SyncedTargetEntity
{
    /* -- SyncedTargetEntity fields -- */
    private static final EntityDataAccessor<Integer> ATTACK_TARGET_ID = SynchedEntityData.defineId(AbstractVoluciteWardenBeaconEntity.class, EntityDataSerializers.INT);
    private final SyncedTargetEntityInfo SYNCED_TARGET_ENTITY_INFO = new SyncedTargetEntityInfo(ATTACK_TARGET_ID);
    /* ------------------------------- */

    protected AbstractVoluciteWardenBeaconEntity(EntityType<? extends AbstractVoluciteWardenBeaconEntity> type, Level level) {super(type, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        /* -- SyncedTargetEntity synched data -- */
        builder.define(ATTACK_TARGET_ID, 0);
        /* ------------------------------------- */
    }

    //attributes shared by the beacons, each beacon adds its max health
    public static AttributeSupplier.Builder createBeaconAttributes()
    {
        return Mob.createMobAttributes()
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
    }

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        if (source.getEntity() != null && source.getEntity().is(AerialHellTags.Entities.VOLUCITE)) {return false;} //not hurt by the warden or its allies
        return super.hurtServer(level, source, amount);
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
