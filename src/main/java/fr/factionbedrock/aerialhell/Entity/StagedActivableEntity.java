package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

//adds an "activating" phase between "deactivated" and "activated"
public interface StagedActivableEntity extends ActivableEntity
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface
    @Override StagedActivableEntityInfo getActivableInfo();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    @Override default void activableEntityTick() //call in tick()
    {
        ActivableEntity.super.activableEntityTick();
        if (this.needsActivatingTicksSyncClientSide() && this.getLevel().isClientSide()) {this.setActivatingTicks(this.isActivating() ? this.getActivatingTicks() + 1 : 0);}
        if (this.isActivating())
        {
            this.onActivatingPhaseTick(); //calling onActivatingPhaseTick() here to call it on both client and server side.
        }
    }

    @Override default void activableHurtServer(boolean superDamaged, ServerLevel serverLevel, DamageSource source, float amount) //call in hurtServer(level, source, amount)
    {
        ActivableEntity.super.activableHurtServer(superDamaged, serverLevel, source, amount);
    }

    @Override default void activableAddAdditionalSaveData(ValueOutput valueOutput) //call in addAdditionalSaveData(valueOutput)
    {
        ActivableEntity.super.activableAddAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("is_activating", this.isActivating());
        valueOutput.putInt("activating_ticks", this.getActivatingTicks());
        if (this.alreadyActivatedOnce()) {valueOutput.putBoolean("activated_once", true);}
    }

    @Override default void activableReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        ActivableEntity.super.activableReadAdditionalSaveData(valueInput);
        this.setActivating(valueInput.getBooleanOr("is_activating", false));
        this.setActivatingTicks(valueInput.getIntOr("activating_ticks", 0));
        if (valueInput.getBooleanOr("activated_once", false)) {this.setAlreadyActivatedOnce();}
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */
    default void onStartActivating() {this.tryPlayActivatingStartSound();} //server-side
    default void onActivatingPhaseTick() {} //both client and server side
    default void onFinishActivating() {} //server-side

    default boolean needsActivatingTicksSyncClientSide() {return false;} //if false, this.getActivatingTicks will always return 0 client-side.

    @Override default void onActiveStatusChange(ServerLevel serverLevel, boolean newActiveStatus) {} //only server side
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    @Override default void changeActiveStatus(ServerLevel serverLevel, boolean newStatus) //this method is supposed to get called only server side - overriden to start activating phase if condition met
    {
        ActivableEntity.super.changeActiveStatus(serverLevel, newStatus);
        if (newStatus && !this.skipActivatingPhase()) //start activating phase
        {
            this.startActivating();
        }
    }

    @Override default void updateActiveStatus()
    {
        if (this.isActivating()) //activating phase
        {
            this.activatingPhaseTick();
            if (this.getActivatingTicks() > this.getActivatingThreshold()) {this.finishActivating();}
        }
        else //normal ActivableEntity "updateActiveStatus"
        {
            ActivableEntity.super.updateActiveStatus();
        }
    }

    default void startActivating() //server-side
    {
        this.setActivating(true);
        this.setActivatingTicks(0);
        if (this.shouldImmobilizeWithSlownessForActivatingPhase()) {this.immobilizeWithSlowness(this.getActivatingThreshold());}
        this.onStartActivating();
    }

    default void activatingPhaseTick() //server-side
    {
        this.incrementActivatingTicks();
        //onActivatingPhaseTick() is called in activableEntityTick() to get called both on client and server side
    }

    default void finishActivating() //server-side
    {
        this.setActivating(false);
        this.onFinishActivating();
        this.setAlreadyActivatedOnce();
    }

    private void immobilizeWithSlowness(int duration) {if (!this.getSelf().level().isClientSide()) {this.getSelf().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, duration, 10, true, false));}}

    default boolean alreadyActivatedOnce() {return this.getActivableInfo().activatedOnceDataAccessor != null ? this.getEntityData().get(this.getActivableInfo().activatedOnceDataAccessor) : this.getActivableInfo().activatedOnce;}
    default void setAlreadyActivatedOnce()
    {
        this.getActivableInfo().activatedOnce = true;
        if (this.getActivableInfo().activatedOnceDataAccessor != null)
        {
            this.getEntityData().set(this.getActivableInfo().activatedOnceDataAccessor, true);
        }
    }

    default boolean skipActivatingPhase() {return this.getActivableInfo().activatingPhaseParameters.shouldSkipActivatingPhase.test(this);}
    default void setActivating(boolean isActivating) {this.getEntityData().set(this.getActivableInfo().activatingDataAccessor, isActivating);}
    default boolean isActivating() {return this.getEntityData().get(this.getActivableInfo().activatingDataAccessor);}
    default int getActivatingTicks() {return this.getActivableInfo().activatingTicks;}
    default void incrementActivatingTicks() {this.getActivableInfo().activatingTicks = this.getActivatingTicks() + 1;}
    default void setActivatingTicks(int value) {this.getActivableInfo().activatingTicks = value;}
    default int getActivatingThreshold() {return this.getActivableInfo().activatingPhaseParameters.activatingThreshold;}
    default boolean shouldImmobilizeWithSlownessForActivatingPhase() {return this.getActivableInfo().activatingPhaseParameters.immobilizeWithSlowness;}

    default void tryPlayActivatingStartSound()
    {
        @Nullable PlaySoundHelper helper = this.getActivableInfo().activatingPhaseParameters.activatingStartSoundHelper;
        if (helper != null) {helper.playSound(this.getSelf());}
    }
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */

    class StagedActivableEntityInfo extends ActivableEntityInfo
    {
        private final EntityDataAccessor<Boolean> activatingDataAccessor; //true if the entity is in activating phase
        @Nullable private final EntityDataAccessor<Boolean> activatedOnceDataAccessor; //true if the Activable Entity was active once (i.e. already got though activating phase once) - use this if you want client-server sync of activatedOnce
        private boolean activatedOnce; //true if the Activable Entity was active once (i.e. already got though activating phase once) - only server side
        private int activatingTicks; //transition from deactivated to active ticks count
        private final ActivatingPhaseParameters activatingPhaseParameters;

        public StagedActivableEntityInfo(ActivableEntityInfo activableInfo, EntityDataAccessor<Boolean> activatingDataAccessor, ActivatingPhaseParameters activatingPhaseParameters) {this(activableInfo.getActiveDataAccessor(), activatingDataAccessor, null, activableInfo.activationMethod, activatingPhaseParameters);}
        public StagedActivableEntityInfo(ActivableEntityInfo activableInfo, EntityDataAccessor<Boolean> activatingDataAccessor, @Nullable EntityDataAccessor<Boolean> activatedOnceDataAccessor, ActivatingPhaseParameters activatingPhaseParameters) {this(activableInfo.getActiveDataAccessor(), activatingDataAccessor, activatedOnceDataAccessor, activableInfo.activationMethod, activatingPhaseParameters);}
        public StagedActivableEntityInfo(EntityDataAccessor<Boolean> activeDataAccessor, EntityDataAccessor<Boolean> activatingDataAccessor, @Nullable EntityDataAccessor<Boolean> activatedOnceDataAccessor, ActivationMethod activationMethod, ActivatingPhaseParameters activatingPhaseParameters)
        {
            super(activeDataAccessor, activationMethod);
            this.activatingDataAccessor = activatingDataAccessor;
            this.activatedOnceDataAccessor = activatedOnceDataAccessor;
            this.activatingPhaseParameters = activatingPhaseParameters;
            this.activatedOnce = false;
        }

        public static class ActivatingPhaseParameters
        {
            private int activatingThreshold; //max ticks in activating phase
            @Nullable private PlaySoundHelper activatingStartSoundHelper; //if not null, contains a method to play activating start sound
            private Predicate<StagedActivableEntity> shouldSkipActivatingPhase; //if returns true, the entity will skip activating phase
            private boolean immobilizeWithSlowness; //true if the Activable Entity should get immobilized with slowness effect on activating start

            public ActivatingPhaseParameters()
            {
                this.activatingThreshold = 0;
                this.activatingStartSoundHelper = null;
                this.shouldSkipActivatingPhase = entity -> false;
                this.immobilizeWithSlowness = false;
            }

            public ActivatingPhaseParameters activatingThreshold(int ticks) {this.activatingThreshold = ticks; return this;}
            public ActivatingPhaseParameters activatingStartSoundHelper(@Nullable PlaySoundHelper playActivatingSoundHelper) {this.activatingStartSoundHelper = playActivatingSoundHelper; return this;}
            public ActivatingPhaseParameters shouldSkipActivatingPhase(boolean shouldSkip) {this.shouldSkipActivatingPhase = entity -> shouldSkip; return this;}
            public ActivatingPhaseParameters shouldSkipActivatingPhase(Predicate<StagedActivableEntity> shouldSkipPredicate) {this.shouldSkipActivatingPhase = shouldSkipPredicate; return this;}
            public ActivatingPhaseParameters immobilizeWithSlowness(boolean doesImmobilizeWithSlowness) {this.immobilizeWithSlowness = doesImmobilizeWithSlowness; return this;}

            public ActivatingPhaseParameters copy() {return new ActivatingPhaseParameters().activatingThreshold(this.activatingThreshold).activatingStartSoundHelper(this.activatingStartSoundHelper).shouldSkipActivatingPhase(this.shouldSkipActivatingPhase).immobilizeWithSlowness(this.immobilizeWithSlowness);}
        }
    }

    StagedActivableEntityInfo.ActivatingPhaseParameters PLAY_ACTIVATING_PHASE_ONLY_ONCE = new StagedActivableEntityInfo.ActivatingPhaseParameters().shouldSkipActivatingPhase(entity -> entity.alreadyActivatedOnce());
}
