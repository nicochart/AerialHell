package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface PhaseGoal
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    List<Phase> getPhases();
    Goal getSelf();
    PhaseInfo getPhaseInfo();
    boolean shouldTrigger(); //condition to automatically trigger
    LivingEntity getGuide();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default boolean canUsePhaseGoal() //return result in canUse()
    {
        if (this.shouldTrigger()) {this.trigger();}
        return this.isActive();
    }

    default boolean canContinueToUsePhaseGoal() {return this.isActive();} //return result in canContinueToUse()

    default void onStart() {this.startFirstPhase();} //call in start()
    default void onStop() {this.skipToInactivePhase();} //call in stop()

    default void tickPhase() //call in tick()
    {
        if (!this.isActive()) {return;} //canContinueToUse if only called every 2 ticks. tickPhase() can be called with inactive phase.

        if (this.getPhaseInfo().goalOwner.getTarget() == null) {this.skipToRecoveryPhase();}
        if (!this.isGuideValid()) {this.skipToInactivePhase(); return;}
        if (this.getGuide() != null && this.getPhaseInfo().cachedUnrotatedRelativePos == null) {this.initializeGuidePos();}

        this.setGuidePos();
        this.setGoalOwnerLookAt();

        this.updateCachedGuideUnrotatedRelativePos();

        this.getCurrentPhase().tick(this.getSelf(), this.getCachedUnrotatedRelativePos(), this.getPhaseInfo().distanceOffsetTolerance);

        if (this.getCurrentPhase().isFinished()) {this.startNextPhase();}
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    default Phase getCurrentPhase() {return this.getPhase(this.getPhaseInfo().phaseIndex);}
    default Phase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    default Phase getPhase(int phaseIndex) { return this.getPhases().get(phaseIndex); }
    default PhaseType getPhaseType() {return this.getCurrentPhase().getType();}

    default boolean isActive() {return this.getPhaseType() != PhaseType.INACTIVE;}

    private void initializeGuidePos()
    {
        this.getPhaseInfo().cachedUnrotatedRelativePos = EntityHelper.toUnrotatedRelativePos(this.getPhaseInfo().goalOwner, this.getGuide().position());
    }

    default Vec3 getCachedUnrotatedRelativePos() {return this.getPhaseInfo().cachedUnrotatedRelativePos != null ? this.getPhaseInfo().cachedUnrotatedRelativePos : this.getPreviousPhase().getUnrotatedRelativeTargetPos();}

    default double getDistanceToTarget()
    {
        return this.getCurrentPhase().getDistanceToTarget(this.getCachedUnrotatedRelativePos());
    }

    default void updateCachedGuideUnrotatedRelativePos() //(actual) position of guide
    {
        Phase phase = this.getCurrentPhase();
        Vec3 previousPos = this.getCachedUnrotatedRelativePos(); //guide pos
        Vec3 targetPos = phase.getUnrotatedRelativeTargetPos(); //target pos
        this.getPhaseInfo().cachedUnrotatedRelativePos = EntityHelper.calculateNewUnrotatedRelativePos(previousPos, targetPos, phase.getSpeed());
    }

    default boolean isGuideValid() {return this.getGuide() != null && this.getGuide().isAlive();}

    /* --------------------------------------------- */
    /* ---------- Setting pos and look at ---------- */
    /* --------------------------------------------- */

    //actually setting the pos of the guide (entity) in the level
    default void setGuidePos()
    {
        @Nullable LivingEntity guide = this.getGuide();
        if (guide != null)
        {
            guide.setPos(EntityHelper.fromUnrotatedRelativeToLevelPos(this.getPhaseInfo().goalOwner, this.getCachedUnrotatedRelativePos()));
        }
    }

    default void setGoalOwnerLookAt()
    {
        Vec3 lookTarget = this.getLookAtTarget();
        if (lookTarget != null)
        {
            this.getPhaseInfo().goalOwner.getLookControl().setLookAt(lookTarget.x, lookTarget.y, lookTarget.z, 30.0F, 30.0F);
        }
    }

    @Nullable default Vec3 getLookAtTarget()
    {
        if (this.getPhaseInfo().goalOwner.getTarget() != null)
        {
            return this.getPhaseInfo().goalOwner.getTarget().position();
        }
        else {return null;}
    }

    /* --------------------------------------------- */
    /* --------------------------------------------- */
    /* --------------------------------------------- */

    default void skipToInactivePhase() {this.skipToPhaseType(PhaseType.INACTIVE);}
    default void skipToRecoveryPhase() {this.skipToPhaseType(PhaseType.RECOVERY);}

    default void skipToPhaseType(PhaseType phaseType)
    {
        if (this.getCurrentPhase().getType() == phaseType) {return;}

        this.getCurrentPhase().forceEnd(this.getSelf());

        int previousPhaseIndex = this.getPhaseInfo().phaseIndex;
        int newPhaseIndex = this.getNextPhaseIndex(previousPhaseIndex);
        while (this.getPhase(newPhaseIndex).getType() != phaseType && newPhaseIndex != previousPhaseIndex)
        {
            newPhaseIndex = this.getNextPhaseIndex(newPhaseIndex);
        }
        if (newPhaseIndex != previousPhaseIndex) {this.startPhase(newPhaseIndex);}
    }

    default boolean trigger() //return true if the attack sequence is successfully triggered
    {
        if (this.isActive()) {return false;}
        else
        {
            this.startFirstPhase();
            return this.isActive();
        }
    }

    default void startFirstPhase() {this.startPhase(0);}
    default void startNextPhase() {this.startPhase(this.getNextPhaseIndex());}
    default void startPhase(int phaseIndex)
    {
        this.getPhaseInfo().phaseIndex = phaseIndex;
        this.getCurrentPhase().reset();
    }

    default int getNextPhaseIndex() {return this.getNextPhaseIndex(this.getPhaseInfo().phaseIndex);}
    default int getNextPhaseIndex(int phaseIndex)
    {
        int nextPhaseIndex = phaseIndex + 1;
        return nextPhaseIndex >= this.getPhases().size() ? 0 : nextPhaseIndex;
    }

    default int getPreviousPhaseIndex()
    {
        int previousPhaseIndex = this.getPhaseInfo().phaseIndex - 1;
        return previousPhaseIndex < 0 ? this.getPhases().size() - 1 : previousPhaseIndex;
    }

    class PhaseInfo
    {
        private final float distanceOffsetTolerance; //used to avoid float imprecision. goal can skip to next phase only if distance to target < distanceOffsetTolerance for some (parametrized) ticks
        private final Mob goalOwner;
        private int phaseIndex;
        private Vec3 cachedUnrotatedRelativePos; //guide pos

        public PhaseInfo(float distanceOffsetTolerance, Mob goalOwner)
        {
            this.distanceOffsetTolerance = distanceOffsetTolerance;
            this.goalOwner = goalOwner;
            this.phaseIndex = 0;
            this.cachedUnrotatedRelativePos = null;
        }
    }
}
