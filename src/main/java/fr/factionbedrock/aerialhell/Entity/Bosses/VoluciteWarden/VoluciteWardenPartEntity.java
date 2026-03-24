package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartInfo;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VoluciteWardenPartEntity extends Monster implements PartEntity
{
    /* -- PartEntity fields -- */
    private static final EntityDataAccessor<Integer> MASTER_ID = SynchedEntityData.defineId(VoluciteWardenPartEntity.class, EntityDataSerializers.INT);
    private final MasterPartInfo MASTER = new MasterPartInfo(MASTER_ID);
    /* ----------------------- */

    public VoluciteWardenPartEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);

        /* -- PartEntity synched data -- */
        builder.define(MASTER_ID, 0);
        /* ----------------------------- */
    }

    /* ------------------------------------------------------------------- */
    /* ---------- PartEntity : Interface methods implementation ---------- */
    /* ------------------------------------------------------------------- */
    @Override public Mob getSelf() {return this;}

    @Override public boolean partSuperHurtServer(ServerLevel level, DamageSource source, float amount) {return super.hurtServer(level, source, amount);}
    @Override public boolean isPartInvulnerableToBase(DamageSource damageSource) {return super.isInvulnerableToBase(damageSource);}

    @Override public MasterPartInfo getMasterInfo() {return this.MASTER;}
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */

    /* ----------------------------------------------------------------------------------------- */
    /* ---------- PartEntity : Superclass methods Overridden to delegate to interface ---------- */
    /* ----------------------------------------------------------------------------------------- */
    @Override public void push(Entity other) {if (this.canPartBePushedBy(other)) {super.push(other);}}
    //@Override public void tick() {this.onTick(); super.tick();} done above for both PartEntity & BeamAttackEntity

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount) {return this.partDoHurtServer(level, source, amount, false);}

    @Override public boolean is(Entity other) {return super.is(other) || this.recognizesPart(other);}

    @Override public void tick()
    {
        this.partEntityTick();
        super.tick();
    }

    @Override public void aiStep()
    {
        super.aiStep();
        this.partEntityAiStep();
    }
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */

    /* --------------------------------------------------------------------------------------------- */
    /* ----------- PartEntity : Superclass methods Overridden for part-specific behavior ----------- */
    /* --------------------------------------------------------------------------------------------- */
    @Override public boolean isPushable() {return false;}
    @Override public boolean isInWall() {return false;}
    @Override public boolean isAttackable() {return true;}
    @Override public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}
    @Override public boolean isNoGravity() {return !this.isDeadOrDying();}
    @Override public boolean isSilent() {return true;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
    @Override public void knockback(double strength, double x, double z) {}
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */

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
}
