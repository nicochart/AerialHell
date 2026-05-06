package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Util.DebugHelper;
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

    boolean partSuperHurtServer(ServerLevel level, DamageSource source, float amount); //override and return super.hurtServer(level, source, amount)
    boolean isPartInvulnerableToBase(DamageSource damageSource); //override and return super.isInvulnerableToBase(damageSource)

    MasterPartInfo getMasterInfo();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void partEntityTick() //call in tick()
    {
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDeadOrDying() || master.getSelf().isRemoved() || !master.is(this.getSelf()))
        {
            this.incrementTicksInInvalidSituation();
            if (this.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION)
            {
                DebugHelper.sendDebugMessage(this.getLevel(), "Child Part "+this.getSelf().getName().getString().replace("entity.aerialhell.", "")+": invalid situation with "+(master == null ? "null" : master.getSelf().isDeadOrDying() ? "dying" : master.getSelf().isRemoved() ? "removed" : "unrecognizing")+" master part", true); //temporary debug TODO remove
                this.reactToInvalidSituationWithMaster();
            }
            this.tryToFindBackMaster();
        }
        else {this.resetTicksInInvalidSituation();}
    }

    default boolean partDoHurtServer(ServerLevel level, DamageSource source, float amount, boolean forceLocalDamage) //replace super.hurtServer(level, source, amount) call by this call with false "forceLocalDamage" (do not call super!)
    {
        if (forceLocalDamage) {return this.partSuperHurtServer(level, source, amount);}
        else
        {
            if (this.isInvulnerable() || this.isPartInvulnerableToBase(source)) {return false;}
            return this.tryRedirectHurtServerToMaster(level, source, amount);
        }
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

    default PartInvalidSituationBehavior getInvalidSituationBehavior() {return PartInvalidSituationBehavior.DISCARD;}
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default EntityDataAccessor<Integer> getMasterIdData() {return this.getMasterInfo().getIdData();}
    default void setMasterRaw(MasterPartEntity master) {this.getMasterInfo().setMaster(master);}
    default @Nullable MasterPartEntity getMasterRaw() {return this.getMasterInfo().getMaster();}

    default int getTicksInInvalidSituation() {return this.getMasterInfo().getTicksInInvalidSituation();}
    default void incrementTicksInInvalidSituation() {this.getMasterInfo().incrementTicksInInvalidSituation();}
    default void resetTicksInInvalidSituation() {this.getMasterInfo().resetTicksInInvalidSituation();}

    default int getMasterId() {return this.getEntityData().get(this.getMasterIdData());}
    default boolean hasMasterId() {return this.getMasterId() != 0;}
    default void setMasterId(int entityId) {this.getEntityData().set(this.getMasterIdData(), entityId);}

    default void killPart()
    {
        this.onPartDeath();
        if (this.getLevel() instanceof ServerLevel serverLevel)
        {
            this.partDoHurtServer(serverLevel, this.getSelf().damageSources().fellOutOfWorld(), this.getSelf().getMaxHealth(), true);
        }
    }

    default boolean tryRedirectHurtServerToMaster(ServerLevel level, DamageSource source, float amount)
    {
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDeadOrDying()) {return this.partDoHurtServer(level, source, amount, true);} //if master can't receive hurtServer, hurt part instead
        return master.getSelf().hurtServer(level, source, amount);
    }

    //if the master is not found (for example after being placed with a structure nbt, or if the master was deleted for whatever reason)
    default void reactToInvalidSituationWithMaster()
    {
        switch (this.getInvalidSituationBehavior())
        {
            case NONE : {}
            case DISCARD :
            {
                if (!this.getLevel().isClientSide()) {this.getSelf().discard();}
            }
        };
    }

    private boolean tryToFindBackMaster()
    {
        MasterPartEntity master = this.getMasterByID();
        if (master == null || master.getSelf().isRemoved()) {return false;}
        else
        {
            this.setMasterRaw(master);
            return false;
        }
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

    enum PartInvalidSituationBehavior{NONE, DISCARD}
}
