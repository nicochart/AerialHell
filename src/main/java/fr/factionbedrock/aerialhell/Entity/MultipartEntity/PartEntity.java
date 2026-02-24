package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public interface PartEntity extends BaseMobEntityInterface
{
    //You may also want to edit the entity xpReward for part entities

    int MAX_TICKS_IN_INVALID_SITUATION = 5;

    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    boolean partSuperDamage(ServerWorld world, DamageSource source, float amount); //override and return super.damage(world, source, amount)
    boolean isPartAlwaysInvulnerableTo(DamageSource damageSource); //override and return super.isAlwaysInvulnerableTo(damageSource)

    TrackedData<Integer> getMasterIdData();
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

    default void partEntityTick() //call in tick()
    {
        if (this.getMasterRaw() == null) {this.setMasterRaw(this.getMasterByID());}
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDead() || master.getSelf().isRemoved() || !master.is(this.getSelf()))
        {
            this.incrementTicksInInvalidSituation();
            if (this.getTicksInInvalidSituation() > MAX_TICKS_IN_INVALID_SITUATION) {this.killPart();}
        }
        else {this.resetTicksInInvalidSituation();}
    }

    default boolean partDamage(ServerWorld world, DamageSource source, float amount, boolean forceLocalDamage) //replace super.damage(world, source, amount) call by this call with false "forceLocalDamage" (do not call super!)
    {
        if (forceLocalDamage) {return this.partSuperDamage(world, source, amount);}
        else
        {
            if (this.isInvulnerable() || this.isPartAlwaysInvulnerableTo(source)) {return false;}
            return this.tryRedirectDamageToMaster(world, source, amount);
        }
    }

    default boolean canPartBePushAwayBy(Entity other) //call as condition in pushAwayFrom(entity)
    {
        if (this.getMaster() == null) {return true;}
        return !other.isPartOf(this.getMaster().getSelf());
    }

    default boolean recognizesPart(Entity other) //call in isPartOf(entity)
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
        if (this.getLevel() instanceof ServerWorld serverWorld)
        {
            this.partDamage(serverWorld, this.getSelf().getDamageSources().outOfWorld(), this.getSelf().getMaxHealth(), true);
        }
    }

    default boolean tryRedirectDamageToMaster(ServerWorld world, DamageSource source, float amount)
    {
        MasterPartEntity master = this.getMasterRaw();
        if (master == null || master.getSelf().isDead()) {return this.partDamage(world, source, amount, true);} //if master can't receive damage, damage part instead
        return master.getSelf().damage(world, source, amount);
    }

    @Nullable default MasterPartEntity getMasterByID()
    {
        Entity entity = this.getLevel().getEntityById(this.getMasterId());
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
