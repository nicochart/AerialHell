package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class StrikeAttackGoal extends Goal
{
    public final StrikeAttackEntity goalOwner;
    private final float distanceOffsetTolerance;
    private int phaseIndex;
    private Vec3 cachedUnrotatedRelativePos;
    private final StrikeInfo strikeInfo;

    public StrikeAttackGoal(StrikeAttackEntity goalOwner, float distanceOffsetTolerance, StrikeInfo strikeInfo)
    {
        this.goalOwner = goalOwner;
        this.distanceOffsetTolerance = distanceOffsetTolerance;
        this.phaseIndex = 0;
        this.strikeInfo = strikeInfo;
    }

    public List<StrikeAttackPhase> getPhases() {return this.goalOwner.getStrikeAttackSequenceInternal(this.getEntityUsedToStrike());}

    public StrikeAttackPhase getCurrentPhase() {return this.getPhase(this.phaseIndex);}
    public StrikeAttackPhase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    public StrikeAttackPhase getPhase(int phaseIndex) {return this.getPhases().get(phaseIndex);}

    public StrikeAttackPhaseType getPhaseType() {return this.getCurrentPhase().getType();}

    @Override public boolean canUse()
    {
        if (this.goalOwner.canUseStrikeAttack() && this.goalOwner.shouldTrigger()) {this.trigger();}
        return this.isActive();
    }

    @Override public boolean canContinueToUse()
    {
        return this.isActive();
    }

    @Override public void start() {this.startFirstPhase();}
    @Override public void stop() {}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.goalOwner.canUseStrikeAttack()) {this.skipToRecoveryPhase();}
        if (!this.entityUsedToStrikeIsValid()) {this.skipToInactivePhase(); return;}
        if (this.getEntityUsedToStrike() != null && this.cachedUnrotatedRelativePos == null) {this.cachedUnrotatedRelativePos = this.goalOwner.toUnrotatedRelativePos(this.getEntityUsedToStrike().position());}

        this.setEntityUsedToStrikePos();
        this.setLookAt();

        this.updateUnrotatedRelativePos();

        this.getCurrentPhase().tick(this, this.goalOwner, this.getCachedUnrotatedRelativePos(), this.distanceOffsetTolerance);
        if (this.getCurrentPhase().isFinished())
        {
            this.goalOwner.onStrikePhaseFinish(this.getPhaseType());
            this.startNextPhase();
        }
    }

    public boolean entityUsedToStrikeIsValid() {return this.getEntityUsedToStrike() != null && this.getEntityUsedToStrike().isAlive();}

    @Nullable public LivingEntity getEntityUsedToStrike() {return this.strikeInfo.entityUsedToStrikeSupplier.get();}

    protected void setEntityUsedToStrikePos()
    {
        @Nullable LivingEntity strikingEntity = this.getEntityUsedToStrike();
        if (strikingEntity != null)
        {
            strikingEntity.setPos(this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos()));
        }
    }

    protected void setLookAt()
    {
        Vec3 lookTarget = this.getLookAtTarget();
        if (lookTarget != null)
        {
            this.goalOwner.getSelf().getLookControl().setLookAt(lookTarget.x, lookTarget.y, lookTarget.z, 30.0F, 30.0F);
        }
    }

    @Nullable public Vec3 getLookAtTarget()
    {
        if (this.isStriking())
        {
            return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCurrentPhase().getUnrotatedRelativeTargetPos());
        }
        else if (this.goalOwner.getTarget() != null)
        {
            return this.goalOwner.getTarget().position();
        }
        else {return null;}
    }

    public boolean isStriking() {return this.getPhaseType() == StrikeAttackPhaseType.STRIKE;}
    public boolean isActive() {return this.getPhaseType() != StrikeAttackPhaseType.INACTIVE;}
    public boolean trigger() //return true if the attack sequence is successfully triggered
    {
        if (this.isActive()) {return false;}
        else
        {
            this.startFirstPhase();
            return this.isActive();
        }
    }

    public double getDistanceToTarget()
    {
        return this.getCurrentPhase().getDistanceToTarget(this.getCachedUnrotatedRelativePos());
    }

    public void skipToInactivePhase() {this.skipToPhaseType(StrikeAttackPhaseType.INACTIVE);}
    public void skipToRecoveryPhase() {this.skipToPhaseType(StrikeAttackPhaseType.RECOVERY);}

    public void skipToPhaseType(StrikeAttackPhaseType phaseType)
    {
        if (this.getCurrentPhase().getType() == phaseType) {return;}

        int previousPhaseIndex = this.phaseIndex;
        int newPhaseIndex = this.getNextPhaseIndex(previousPhaseIndex);
        while (this.getPhase(newPhaseIndex).getType() != phaseType && newPhaseIndex != previousPhaseIndex) //newPhaseIndex != previousPhaseIndex to avoid infinite cycle if there is no phase of this type in sequence (should never happen except for inactive phase)
        {
            newPhaseIndex = this.getNextPhaseIndex(newPhaseIndex);
        }
        if (newPhaseIndex != previousPhaseIndex) {this.startPhase(newPhaseIndex);}
    }

    private void startFirstPhase() {this.startPhase(0);}
    private void startNextPhase() {this.startPhase(this.getNextPhaseIndex());}
    private void startPhase(int phaseIndex)
    {
        this.phaseIndex = phaseIndex;
        this.getCurrentPhase().reset();
    }

    private int getNextPhaseIndex()
    {
        return this.getNextPhaseIndex(this.phaseIndex);
    }

    private int getNextPhaseIndex(int phaseIndex)
    {
        int nextPhaseIndex = phaseIndex + 1;
        return nextPhaseIndex >= this.getPhases().size() ? 0 : nextPhaseIndex;
    }

    private int getPreviousPhaseIndex()
    {
        int previousPhaseIndex = this.phaseIndex - 1;
        return previousPhaseIndex < 0 ? this.getPhases().size() - 1 : previousPhaseIndex;
    }

    public Vec3 getCachedUnrotatedRelativePos() {return this.cachedUnrotatedRelativePos != null ? this.cachedUnrotatedRelativePos : this.getPreviousPhase().getUnrotatedRelativeTargetPos();}

    public Vec3 updateUnrotatedRelativePos()
    {
        Vec3 previousURPos = this.getCachedUnrotatedRelativePos();
        StrikeAttackPhase phase = this.getCurrentPhase();
        Vec3 newUnrotatedRelativePos = calculateArmUnrotatedRelativePosDuringStrike(previousURPos, phase.getUnrotatedRelativeTargetPos(), phase.getSpeed());
        this.cachedUnrotatedRelativePos = newUnrotatedRelativePos;
        return newUnrotatedRelativePos;
    }

    public static Vec3 calculateArmUnrotatedRelativePosDuringStrike(Vec3 unrotatedRelativeCurrentPos, Vec3 unrotatedRelativeTargetPos, double maxSpeed)
    {
        Vec3 direction = unrotatedRelativeTargetPos.subtract(unrotatedRelativeCurrentPos);
        double distance = direction.length();
        if (distance < 0.0001F) {return unrotatedRelativeCurrentPos;}

        double speed = Math.min(maxSpeed, distance);
        Vec3 movement = direction.normalize().scale(speed);

        Vec3 newPos = unrotatedRelativeCurrentPos.add(movement);
        return new Vec3(newPos.x, newPos.y, newPos.z);
    }

    public void strike()
    {
        if (this.getEntityUsedToStrike() != null)
        {
            this.goalOwner.strike(this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCurrentPhase().getUnrotatedRelativeTargetPos()), this.getEntityUsedToStrike(), this.strikeInfo.explosionRadius, this.strikeInfo.bonusDamageAmount, this.strikeInfo.bonusDamageRange, this.strikeInfo.knockbackScale, this.strikeInfo.destroyBlocks);
        }
    }

    public static class StrikeInfo
    {
        private final Supplier<LivingEntity> entityUsedToStrikeSupplier;
        private final float explosionRadius;
        private final float bonusDamageAmount;
        private final float bonusDamageRange;
        private final float knockbackScale;
        private final boolean destroyBlocks;

        public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, boolean destroyBlocks) {this(entityUsedToStrikeSupplier, explosionRadius, 0.0F, 0.0F, 0.0F, destroyBlocks);}
        public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, float bonusDamageAmount, float bonusDamageRange, float knockbackScale, boolean destroyBlocks)
        {
            this.entityUsedToStrikeSupplier = entityUsedToStrikeSupplier;
            this.explosionRadius = explosionRadius;
            this.bonusDamageAmount = bonusDamageAmount;
            this.bonusDamageRange = bonusDamageRange;
            this.knockbackScale = knockbackScale;
            this.destroyBlocks = destroyBlocks;
        }
    }
}