package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;

public interface ActivableEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface
    ActivableEntityInfo getActivableInfo();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void activableEntityTick() //call in tick()
    {
        if (this.getLevel().getNearestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToActivate(), EntitySelector.NO_CREATIVE_OR_SPECTATOR) != null)
        {
            if (!this.isActive() && this.getTicksWithTarget() >= this.getTicksToActivate())
            {
                this.setActive(true);
                this.onActivationStatusChange();
            }
            else {this.incrementTicksWithTarget();}

            if (this.isActive() && this.getTicksWithTarget() > 0) {this.tryIncrementTicksToDeactivate();}
        }
        else if (this.getLevel().getNearestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToDeactivate(), EntitySelector.NO_CREATIVE_OR_SPECTATOR) == null)
        {
            this.resetTicksWithTarget();
            if (this.getTicksWithoutAnyTarget() < this.getTicksToDeactivate()) {this.incrementTicksWithoutAnyTarget();}
            else if (this.getActivableInfo().getLastHurtByPlayerMemoryTimeAccessor() <= 0 && this.getTicksWithoutAnyTarget() >= this.getTicksToDeactivate())
            {
                this.setActive(false);
                this.onActivationStatusChange();
            }
        }
    }

    default void activableHurtServer(boolean superDamaged, ServerLevel level, DamageSource source, float amount) //call in hurtServer(level, source, amount)
    {
        if (superDamaged)
        {
            this.setActive(true);
            this.getActivableInfo().setLastHurtByPlayerMemoryTimeAccessor(100);
            this.onActivationStatusChange();
        }
    }

    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default void onActivationStatusChange()
    {
        this.resetTicksWithoutAnyTarget();
        this.resetTicksToDeactivate();
    }

    default int getTicksWithTarget() {return this.getActivableInfo().getTicksWithTarget();}
    default void incrementTicksWithTarget() {this.getActivableInfo().setTicksWithTarget(this.getTicksWithTarget() + 1);}
    default void resetTicksWithTarget() {this.getActivableInfo().setTicksWithTarget(0);}
    default int getTicksWithoutAnyTarget() {return this.getActivableInfo().getTicksWithoutTarget();}
    default void incrementTicksWithoutAnyTarget() {this.getActivableInfo().setTicksWithoutTarget(this.getTicksWithoutAnyTarget() + 1);}
    default void resetTicksWithoutAnyTarget() {this.getActivableInfo().setTicksWithoutTarget(0);}
    default int getTicksToActivate() {return this.getActivableInfo().ticksToActivate;}
    default int getTicksToDeactivate() {return this.getActivableInfo().getTicksToDeactivate();}
    default int getMaxTicksToDeactivate() {return this.getActivableInfo().maxTicksToDeactivate;}
    default void tryIncrementTicksToDeactivate() {if (this.getTicksToDeactivate() < this.getMaxTicksToDeactivate()) {this.getActivableInfo().setTicksToDeactivate(this.getTicksToDeactivate() + 1);}}
    default void resetTicksToDeactivate() {this.getActivableInfo().resetTicksToDeactivate();}

    default void setActive(boolean isActive) {this.getEntityData().set(this.getActiveDataAccessor(), isActive);}
    default boolean isActive() {return this.getEntityData().get(this.getActiveDataAccessor());}

    default double getMinDistanceToActivate() {return this.getActivableInfo().minDistanceToActivate;}
    default double getMinDistanceToDeactivate() {return this.getActivableInfo().minDistanceToDeactivate;}
    default EntityDataAccessor<Boolean> getActiveDataAccessor() {return this.getActivableInfo().getActiveDataAccessor();}

    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */

    class ActivableEntityInfo
    {
        private int ticksWithTarget;
        private int ticksWithoutAnyTarget;
        private int ticksToDeactivate;
        private final EntityDataAccessor<Boolean> activeDataAccessor;
        private final FieldAccessor<Integer> lastHurtByPlayerMemoryTimeAccessor;
        public final int ticksToActivate;
        public final int minTicksToDeactivate;
        public final int maxTicksToDeactivate;
        public final double minDistanceToActivate;
        public final double minDistanceToDeactivate;

        public ActivableEntityInfo(EntityDataAccessor<Boolean> activeDataAccessor, FieldAccessor<Integer> lastHurtByPlayerMemoryTimeAccessor, int ticksToActivate, int minTicksToDeactivate, int maxTicksToDeactivate, double minDistanceToActivate, double minDistanceToDeactivate)
        {
            this.ticksToDeactivate = minTicksToDeactivate;
            this.activeDataAccessor = activeDataAccessor;
            this.lastHurtByPlayerMemoryTimeAccessor = lastHurtByPlayerMemoryTimeAccessor;
            this.ticksToActivate = ticksToActivate;
            this.minTicksToDeactivate = minTicksToDeactivate;
            this.maxTicksToDeactivate = maxTicksToDeactivate;
            this.minDistanceToActivate = minDistanceToActivate;
            this.minDistanceToDeactivate = minDistanceToDeactivate;
        }

        public int getTicksWithTarget() {return this.ticksWithTarget;}
        public void setTicksWithTarget(int value) {this.ticksWithTarget = value;}
        public int getTicksWithoutTarget() {return this.ticksWithoutAnyTarget;}
        public void setTicksWithoutTarget(int value) {this.ticksWithoutAnyTarget = value;}
        public int getTicksToDeactivate() {return this.ticksToDeactivate;}
        public void setTicksToDeactivate(int value) {this.ticksToDeactivate = value;}
        public void resetTicksToDeactivate() {this.ticksToDeactivate = this.minTicksToDeactivate;}
        public EntityDataAccessor<Boolean> getActiveDataAccessor() {return this.activeDataAccessor;}
        public int getLastHurtByPlayerMemoryTimeAccessor() {return lastHurtByPlayerMemoryTimeAccessor.get();}
        public void setLastHurtByPlayerMemoryTimeAccessor(int value) {lastHurtByPlayerMemoryTimeAccessor.set(value);}
    }
}
