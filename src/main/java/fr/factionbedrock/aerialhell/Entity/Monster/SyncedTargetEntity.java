package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import org.jspecify.annotations.Nullable;

public interface SyncedTargetEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    SyncedTargetEntityInfo getSyncedTargetEntityInfo();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void syncedTargetEntityTick() //call in tick()
    {
        if (this.attackTargetNeedsSync()) {this.syncAttackTarget();}
    }

    @Nullable default LivingEntity getSyncedTarget() //call whenever you need synced target
    {
        return this.getCachedAttackTarget();
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* ----------------------------------------------------------------- */
    /* ------- Other methods to override for specific sync needs ------- */
    /* ----------------------------------------------------------------- */
    //by default, the attack target is synced if the server side target changed.
    //you can override this method to add additional conditions to the sync (for example, if you only need sync under some conditions).
    //do not always return true. Use at least this condition.
    //always returning false basically returns back for vanilla. Client target will be null.
    default boolean attackTargetNeedsSync()
    {
        if (!this.getLevel().isClient() && this.getSelf().getTarget() != this.getCachedAttackTarget()) {return true;} //server side : if real target is != from cached target

        LivingEntity target = this.getLevel().isClient() ? this.getCachedAttackTarget() : this.getSelf().getTarget();
        if (target == null) {return this.hasAttackTargetEntityId();} //if target is null but synced id exists
        else {return target.getId() != this.getAttackTargetEntityId();} //if target exists & synced id is not corresponding
    }
    /* ----------------------------------------------------------------- */
    /* ----------------------------------------------------------------- */
    /* ----------------------------------------------------------------- */
    //may need "two calls" to fully sync : one on server-side, and one on client-side (next tick).
    //if server has a new target, the synced target id will update on the first call.
    //client will update its cached attack target (with the new id) on the next call.
    default void syncAttackTarget()
    {
        if (this.getLevel().isClient()) //Client side
        {
            if (!this.hasAttackTargetEntityId()) {this.setCachedAttackTarget(null);}
            else
            {
                Entity entity = this.getLevel().getEntityById(this.getAttackTargetEntityId());
                if (entity instanceof LivingEntity livingEntity) {this.setCachedAttackTarget(livingEntity);}
            }
        }
        else //Server side
        {
            if (this.getTarget() == null)
            {
                this.setCachedAttackTarget(null);
                this.resetAttackTargetEntityId();
            }
            else
            {
                this.setAttackTargetEntityId(this.getTarget().getId());
                this.setCachedAttackTarget(this.getTarget());
            }
        }
    }

    default TrackedData<Integer> getAttackTargetEntityIdData() {return this.getSyncedTargetEntityInfo().attackTargetIdDataAccessor;};
    default @Nullable LivingEntity getCachedAttackTarget() {return this.getSyncedTargetEntityInfo().cachedAttackTarget;}
    default void setCachedAttackTarget(@Nullable LivingEntity entity) {this.getSyncedTargetEntityInfo().cachedAttackTarget = entity;}

    default int getAttackTargetEntityId() {return this.getEntityData().get(this.getAttackTargetEntityIdData());}
    default boolean hasAttackTargetEntityId() {return this.getEntityData().get(this.getAttackTargetEntityIdData()) != 0;}
    default void setAttackTargetEntityId(int entityTargetId) {this.getEntityData().set(this.getAttackTargetEntityIdData(), entityTargetId);}
    default void resetAttackTargetEntityId() {this.getEntityData().set(this.getAttackTargetEntityIdData(), 0);}

    class SyncedTargetEntityInfo
    {
        private final TrackedData<Integer> attackTargetIdDataAccessor;
        private @Nullable LivingEntity cachedAttackTarget;

        public SyncedTargetEntityInfo(TrackedData<Integer> attackTargetIdDataAccessor)
        {
            this.attackTargetIdDataAccessor = attackTargetIdDataAccessor;
        }
    }
}
