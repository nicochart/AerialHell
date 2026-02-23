package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface PartEntity extends BaseMobEntityInterface
{
    //You may also want to edit the entity xpReward for part entities

    int MAX_TICKS_IN_INVALID_SITUATION = 5;

    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    boolean superHurtServer(ServerLevel level, DamageSource source, float amount); //override and return super.hurtServer(level, source, amount)
    boolean isPartInvulnerableToBase(DamageSource damageSource); //override and return super.isInvulnerableToBase(damageSource)

    EntityDataAccessor<Integer> getMasterIdData();
    void setMasterRaw(MasterPartEntity master);
    @Nullable MasterPartEntity getMasterRaw();

    int getTicksInInvalidSituation();
    void setTickInInvalidSituation(int newValue);
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void initPart() //call in constructor
    {
        this.resetTicksInInvalidSituation();
    }

    default void onTick() //call in tick()
    {
        if (this.getMasterRaw() == null) {this.setMasterRaw(this.getMasterByID());}
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDeadOrDying() || master.getSelf().isRemoved() || !master.is(this.getSelf()))
        {
            this.incrementTicksInInvalidSituation();
            if (this.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION) {this.killPart();}
        }
        else {this.resetTicksInInvalidSituation();}
    }

    default boolean onHurtServer(ServerLevel level, DamageSource source, float amount) //replace super.hurtServer(level, source, amount) call by this call (do not call super!)
    {
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDeadOrDying()) {return this.superHurtServer(level, source, amount);}
        if (this.isInvulnerable()) {return false;}
        return this.isPartInvulnerableToBase(source) ? false : master.getSelf().hurtServer(level, source, amount);
    }

    default boolean canPartBePushedBy(Entity other) //call as condition in push(entity)
    {
        if (this.getMaster() == null) {return true;}
        return !other.is(this.getMaster().getSelf());
    }

    default boolean recognizesPart(Entity other) //call in is(entity)
    {
        if (this.getMaster() == null) {return false;}
        return this.getMaster() == other || this.getMaster().recognizesChildPart(other);
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */

    default void onPartDeath() {} //additional things to do when a part is killed, override if necessary

    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default void incrementTicksInInvalidSituation() {this.setTickInInvalidSituation(this.getTicksInInvalidSituation() + 1);}
    default void resetTicksInInvalidSituation() {this.setTickInInvalidSituation(0);}

    default int getMasterId() {return this.getEntityData().get(this.getMasterIdData());}
    default boolean hasMasterId() {return this.getMasterId() != 0;}
    default void setMasterId(int entityId) {this.getEntityData().set(this.getMasterIdData(), entityId);}

    default void killPart()
    {
        this.onPartDeath();
        if (this.getLevel() instanceof ServerLevel serverLevel)
        {
            this.hurtPart(serverLevel, this.getSelf().damageSources().fellOutOfWorld(), this.getSelf().getMaxHealth(), true);
        }
    }

    default boolean hurtPart(ServerLevel level, DamageSource source, float amount, boolean forceLocalDamage)
    {
        if (forceLocalDamage) {return this.superHurtServer(level, source, amount);}
        else {return this.onHurtServer(level, source, amount);}
    }

    @Nullable default MasterPartEntity getMasterByID()
    {
        Entity entity = this.getLevel().getEntity(this.getMasterId());
        return entity instanceof MasterPartEntity masterEntity ? masterEntity : null;
    }

    @Nullable default MasterPartEntity getMaster() {return this.getMasterRaw();} //server-client sync is not necessary because sync is done once in onTick()
    default boolean setMaster(MasterPartEntity master)
    {
        boolean canSet = this.getMaster() == null;
        if (canSet)
        {
            this.setMasterRaw(master);
            this.setMasterId(master.getId());
        }
        return canSet;
    }
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
}
