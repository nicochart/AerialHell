package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BaseChildPartEntity extends Monster implements PartEntity
{
    /* -- PartEntity fields -- */
    private static final EntityDataAccessor<Integer> MASTER_ID = SynchedEntityData.defineId(BaseChildPartEntity.class, EntityDataSerializers.INT);
    private MasterPartEntity master;
    protected int ticksInInvalidSituation;
    /* ----------------------- */

    public BaseChildPartEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);

        /* -- PartEntity init -- */
        this.initPart();
        /* --------------------- */
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

    @Override public boolean superHurtServer(ServerLevel level, DamageSource source, float amount) {return super.hurtServer(level, source, amount);}
    @Override public boolean isPartInvulnerableToBase(DamageSource damageSource) {return super.isInvulnerableToBase(damageSource);}

    @Override public EntityDataAccessor<Integer> getMasterIdData() {return MASTER_ID;}
    @Override public void setMasterRaw(MasterPartEntity master) {this.master = master;}
    @Override public MasterPartEntity getMasterRaw() {return this.master;}

    @Override public int getTicksInInvalidSituation() {return ticksInInvalidSituation;}
    @Override public void setTickInInvalidSituation(int newValue) {this.ticksInInvalidSituation = newValue;}
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */
    /* ------------------------------------------------------------------- */

    /* ----------------------------------------------------------------------------------------- */
    /* ---------- PartEntity : Superclass methods Overridden to delegate to interface ---------- */
    /* ----------------------------------------------------------------------------------------- */
    @Override public void push(Entity other) {if (this.canPartBePushedBy(other)) {super.push(other);}}
    @Override public void tick() {this.onTick(); super.tick();}

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount) {return this.onHurtServer(level, source, amount);}

    @Override public boolean is(Entity other) {return this.isPartOf(other);}
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------- */

    /* --------------------------------------------------------------------------------------------- */
    /* ----------- PartEntity : Superclass methods Overridden for part-specific behavior ----------- */
    /* --------------------------------------------------------------------------------------------- */
    @Override public boolean isPickable() {return true;}
    @Override public boolean isPushable() {return false;}
    @Override public boolean isInWall() {return false;}
    @Override public boolean isAttackable() {return true;}
    @Override public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}
    @Override public boolean isNoGravity() {return !this.isDeadOrDying();}
    @Override public boolean isSilent() {return true;}
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------- */
}
