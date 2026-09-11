package fr.factionbedrock.aerialhell.Entity.Util;

import fr.factionbedrock.aerialhell.Entity.ActivableEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.function.ToBooleanBiFunction;

import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class ActivableEntityInfo
{
    private final EntityDataAccessor<Boolean> activeDataAccessor;
    public final ActivationMethod activationMethod;
    private int activationTicks; //ticks inactive with nearby target
    private int deactivationTicks; //ticks active without nearby target
    private int deactivationThreshold; //ticks active without nearby target needed to deactivate (not constant, will increment while the entity remains in combat and with each hit the entity receives

    public ActivableEntityInfo(EntityDataAccessor<Boolean> activeDataAccessor, ActivationMethod activationMethod)
    {
        this.activeDataAccessor = activeDataAccessor;
        this.activationMethod = activationMethod;
    }

    public EntityDataAccessor<Boolean> getActiveDataAccessor() {return this.activeDataAccessor;}
    public int getActivationTicks() {return this.activationTicks;}
    public void setActivationTicks(int value) {this.activationTicks = value;}
    public int getDeactivationTicks() {return this.deactivationTicks;}
    public void setDeactivationTicks(int value) {this.deactivationTicks = value;}
    public int getActivationThreshold() {return this.activationMethod.activationThreshold;}
    public int getDeactivationThreshold() {return this.deactivationThreshold;}
    public void setDeactivationThreshold(int value) {this.deactivationThreshold = value;}
    public void resetDeactivationThreshold() {this.deactivationThreshold = this.activationMethod.minDeactivationThreshold;}
    public double getTargetSearchDistance(ActivableEntity entity) {return this.activationMethod.targetSearchDistance.applyAsDouble(entity);}
    public int getMaxDeactivationThreshold() {return this.activationMethod.maxDeactivationThreshold;}
    public boolean canUseVanillaTargetForActivation() {return this.activationMethod.canUseVanillaTargetForActivation;}
    public boolean canSearchNearbyTargetForActivation() {return this.activationMethod.canSearchNearbyTargetForActivation;}
    public int getCheckForTargetPeriodInTicks() {return this.activationMethod.checkForTargetPeriodInTicks;}
    public boolean isValidTarget(ActivableEntity activableEntity, LivingEntity potentialTarget) {return this.activationMethod.validTargetCondition.applyAsBoolean(activableEntity, potentialTarget);}
    public boolean activateOnlyOnHit(ActivableEntity activableEntity) {return this.activationMethod.activateOnlyOnHit.test(activableEntity);}
    public boolean canDeactivate(ActivableEntity activableEntity) {return this.activationMethod.canDeactivate.test(activableEntity);}

    public static class ActivationMethod
    {
        public static int DEFAULT_CHECK_TARGET_PERIOD = 5;

        private Predicate<ActivableEntity> activateOnlyOnHit; //if returns true, the entity will not check / search for target in any way. Only a hit can activate the entity.
        private Predicate<ActivableEntity> canDeactivate; //if returns true, the entity can deactivate if ticks conditions met.
        private boolean canUseVanillaTargetForActivation; //if true, the entity will check this.getTarget() before searching for nearby target
        private boolean canSearchNearbyTargetForActivation; //if true, the entity can search for nearby target (independently of entity.getTarget()) if necessary
        private int checkForTargetPeriodInTicks; //if the value is n, the entity will search for target every n ticks.
        private ToBooleanBiFunction<ActivableEntity, LivingEntity> validTargetCondition; //if returns true, the living entity is considered as a valid target.
        private int activationThreshold; //ticks inactive with nearby target needed to activate
        private int minDeactivationThreshold; //min ticks active without nearby target needed to deactivate (base and minimum value)
        private int maxDeactivationThreshold; //max ticks active without nearby target needed to deactivate (can't increment higher than that)
        private ToDoubleFunction<ActivableEntity> targetSearchDistance; //distance at which the entity searches for a target

        public ActivationMethod()
        {
            this.activateOnlyOnHit = activableEntity -> false;
            this.canDeactivate = activableEntity -> true;
            this.canUseVanillaTargetForActivation = true;
            this.canSearchNearbyTargetForActivation = true;
            this.checkForTargetPeriodInTicks = DEFAULT_CHECK_TARGET_PERIOD;
            this.validTargetCondition = (activableEntity, potentialTarget) -> activableEntity.getSelf().hasLineOfSight(potentialTarget) && potentialTarget instanceof Player;
            this.activationThreshold = 10;
            this.minDeactivationThreshold = 120;
            this.maxDeactivationThreshold = 600;
            this.targetSearchDistance = entity -> entity.isActive() ? 48 : 8;
        }

        public ActivationMethod activateOnlyOnHit() {this.activateOnlyOnHit = activableEntity -> true; return this;}
        public ActivationMethod canDeactivate(boolean doesCan) {this.canDeactivate = activableEntity -> doesCan; return this;}
        public ActivationMethod canDeactivate(Predicate<ActivableEntity> condition) {this.canDeactivate = condition; return this;}
        public ActivationMethod activateOnlyOnHitCondition(Predicate<ActivableEntity> activateOnlyOnHit) {this.activateOnlyOnHit = activateOnlyOnHit; return this;}
        public ActivationMethod canUseVanillaTargetForActivation(boolean doesCan) {this.canUseVanillaTargetForActivation = doesCan; return this;}
        public ActivationMethod canSearchNearbyTargetForActivation(boolean doesCan) {this.canSearchNearbyTargetForActivation = doesCan; return this;}
        public ActivationMethod searchForNearbyTargetPeriodInTicks(int period) {this.checkForTargetPeriodInTicks = period; return this;}
        public ActivationMethod validTargetCondition(ToBooleanBiFunction<ActivableEntity, LivingEntity> condition) {this.validTargetCondition = condition; return this;}
        public ActivationMethod activationThreshold(int ticks) {this.activationThreshold = ticks; return this;}
        public ActivationMethod minDeactivationThreshold(int ticks) {this.minDeactivationThreshold = ticks; if (this.maxDeactivationThreshold < this.minDeactivationThreshold) {this.maxDeactivationThreshold = this.minDeactivationThreshold;} return this;}
        public ActivationMethod maxDeactivationThreshold(int ticks) {this.maxDeactivationThreshold = ticks; if (this.maxDeactivationThreshold < this.minDeactivationThreshold) {this.minDeactivationThreshold = this.maxDeactivationThreshold;} return this;}
        public ActivationMethod targetSearchDistance(ToDoubleFunction<ActivableEntity> targetSearchDistance) {this.targetSearchDistance = targetSearchDistance; return this;}
        public ActivationMethod targetSearchDistance(double inactiveTargetSearchDistance, double activeTargetSearchDistance) {this.targetSearchDistance = entity -> entity.isActive() ? activeTargetSearchDistance : inactiveTargetSearchDistance; return this;}

        public ActivationMethod copy() {return new ActivationMethod().activateOnlyOnHitCondition(this.activateOnlyOnHit).canDeactivate(this.canDeactivate).canUseVanillaTargetForActivation(this.canUseVanillaTargetForActivation).canSearchNearbyTargetForActivation(this.canSearchNearbyTargetForActivation).searchForNearbyTargetPeriodInTicks(this.checkForTargetPeriodInTicks).validTargetCondition(this.validTargetCondition).activationThreshold(this.activationThreshold).minDeactivationThreshold(this.minDeactivationThreshold).maxDeactivationThreshold(this.maxDeactivationThreshold).targetSearchDistance(this.targetSearchDistance);}
    }
}
