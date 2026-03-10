package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.List;

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
        if (!this.getLevel().isClientSide())
        {
            this.updateActiveStatus();
        }
    }

    default void activableHurtServer(boolean superDamaged, ServerLevel serverLevel, DamageSource source, float amount) //call in hurtServer(level, source, amount)
    {
        if (superDamaged)
        {
            if (this.isActive()) {this.tryIncrementDeactivationThreshold(20);}
            else {this.changeActiveStatus(serverLevel, true);}
        }
    }

    default void activableAddAdditionalSaveData(ValueOutput valueOutput) //call in addAdditionalSaveData(valueOutput)
    {
        valueOutput.putBoolean("is_active", this.isActive());
    }

    default void activableReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        this.setActive(valueInput.getBooleanOr("is_active", false));
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */
    default void onActiveStatusChange(ServerLevel serverLevel, boolean newActiveStatus) {} //only server side
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default void updateActiveStatus()
    {
        if (this.activateOnlyOnHit() && !this.canDeactivate()) {return;}
        if (!(this.getLevel() instanceof ServerLevel serverLevel)) {return;}
        if (this.getSelf().tickCount % this.getCheckForTargetPeriodInTicks() != 0) {return;}
        int increment = this.getCheckForTargetPeriodInTicks();

        boolean hasTarget = this.checkVanillaTarget() || this.checkNearbyTarget();
        boolean wasActive = this.isActive();

        if (!this.activateOnlyOnHit())
        {
            if (!wasActive && !hasTarget)
            {
                this.resetActivationTicks();
            }
            if (!wasActive && hasTarget)
            {
                this.incrementActivationTicks(increment);
                if (this.getActivationTicks() >= this.getActivationThreshold()) {this.changeActiveStatus(serverLevel, true);}
            }
        }
        if (this.canDeactivate())
        {
            if (wasActive && hasTarget)
            {
                this.resetDeactivationTicks();
                this.tryIncrementDeactivationThreshold(increment);
            }
            if (wasActive && !hasTarget)
            {
                this.incrementDeactivationTicks(increment);
                if (this.getDeactivationTicks() >= this.getDeactivationThreshold()) {this.changeActiveStatus(serverLevel, false);}
            }
        }
    }

    default boolean checkVanillaTarget()
    {
        if (!this.getActivableInfo().canUseVanillaTargetForActivation()) {return false;}
        return this.getSelf().getTarget() != null;
    }

    default boolean checkNearbyTarget()
    {
        if (!this.getActivableInfo().canSearchNearbyTargetForActivation()) {return false;}
        return this.hasNearbyTarget();
    }

    default void changeActiveStatus(ServerLevel serverLevel, boolean newStatus) //this method is supposed to get called only server side
    {
        this.setActive(newStatus);
        this.resetDeactivationTicks();
        this.resetActivationTicks();
        this.resetDeactivationThreshold();
        this.onActiveStatusChange(serverLevel, newStatus);
    }

    default boolean hasNearbyTarget()
    {
        List<Entity> nearbyEntities = this.getLevel().getEntities(this.getSelf(), this.getSelf().getBoundingBox().inflate(this.getTargetSearchDistance()), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        for (Entity entity : nearbyEntities)
        {
            if (entity instanceof LivingEntity livingEntity && this.activableEntityCanTarget(this, livingEntity))
            {
                return true;
            }
        }
        return false;
    }

    default int getActivationTicks() {return this.getActivableInfo().getActivationTicks();}
    default void incrementActivationTicks() {this.incrementActivationTicks(1);}
    default void incrementActivationTicks(int increment) {this.getActivableInfo().setActivationTicks(this.getActivationTicks() + increment);}
    default void resetActivationTicks() {this.getActivableInfo().setActivationTicks(0);}
    default int getDeactivationTicks() {return this.getActivableInfo().getDeactivationTicks();}
    default void incrementDeactivationTicks() {this.getActivableInfo().setDeactivationTicks(this.getDeactivationTicks() + 1);}
    default void incrementDeactivationTicks(int increment) {this.getActivableInfo().setDeactivationTicks(this.getDeactivationTicks() + increment);}
    default void resetDeactivationTicks() {this.getActivableInfo().setDeactivationTicks(0);}
    default int getActivationThreshold() {return this.getActivableInfo().getActivationThreshold();}
    default int getDeactivationThreshold() {return this.getActivableInfo().getDeactivationThreshold();}
    default int getMaxDeactivationThreshold() {return this.getActivableInfo().getMaxDeactivationThreshold();}
    default void tryIncrementDeactivationThreshold() {if (this.getDeactivationThreshold() < this.getMaxDeactivationThreshold()) {this.getActivableInfo().setDeactivationThreshold(this.getDeactivationThreshold() + 1);}}
    default void tryIncrementDeactivationThreshold(int increment) {this.getActivableInfo().setDeactivationThreshold(Math.min(this.getDeactivationThreshold() + increment, this.getMaxDeactivationThreshold()));}
    default void resetDeactivationThreshold() {this.getActivableInfo().resetDeactivationThreshold();}

    default boolean activableEntityCanTarget(ActivableEntity activableEntity, LivingEntity potentialTarget) {return this.getActivableInfo().isValidTarget(activableEntity, potentialTarget);}
    default boolean activateOnlyOnHit() {return this.getActivableInfo().activateOnlyOnHit(this) || !this.getActivableInfo().canUseVanillaTargetForActivation() && !this.getActivableInfo().canSearchNearbyTargetForActivation();}
    default boolean canDeactivate() {return this.getActivableInfo().canDeactivate(this);}

    default void setActive(boolean isActive) {this.getEntityData().set(this.getActiveDataAccessor(), isActive);}
    default boolean isActive() {return this.getEntityData().get(this.getActiveDataAccessor());}

    default int getCheckForTargetPeriodInTicks() {return this.getActivableInfo().getCheckForTargetPeriodInTicks();}
    default double getTargetSearchDistance() {return this.getActivableInfo().getTargetSearchDistance(this);}
    default EntityDataAccessor<Boolean> getActiveDataAccessor() {return this.getActivableInfo().getActiveDataAccessor();}

    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */

    //examples of Activation Method (do not forget to .copy() if you reuse and edit)
    ActivableEntityInfo.ActivationMethod DEFAULT_ACTIVATION_METHOD = new ActivableEntityInfo.ActivationMethod();
    ActivableEntityInfo.ActivationMethod CHECK_VANILLA_TARGET = DEFAULT_ACTIVATION_METHOD.copy().canSearchNearbyTargetForActivation(false);
    ActivableEntityInfo.ActivationMethod NEARBY_TARGET_SEARCH = DEFAULT_ACTIVATION_METHOD.copy().canUseVanillaTargetForActivation(false);
    ActivableEntityInfo.ActivationMethod ONLY_HURT = DEFAULT_ACTIVATION_METHOD.copy().activateOnlyOnHit();
}
