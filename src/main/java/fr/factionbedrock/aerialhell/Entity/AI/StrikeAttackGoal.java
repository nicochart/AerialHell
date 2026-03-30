package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StrikeAttackGoal extends Goal
{
    public final StrikeAttackEntity goalOwner;
    private final float distanceOffsetTolerance;
    private int phaseIndex;
    private Vec3 cachedUnrotatedRelativePos;

    public StrikeAttackGoal(StrikeAttackEntity goalOwner, float distanceOffsetTolerance)
    {
        this.goalOwner = goalOwner;
        this.distanceOffsetTolerance = distanceOffsetTolerance;
        this.phaseIndex = 0;
    }

    public List<StrikeAttackPhase> getPhases() {return this.goalOwner.getStrikeAttackSequence();}

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

        this.setEntityUsedToStrikePos();
        this.setLookAt();

        this.updateUnrotatedRelativePos();

        this.getCurrentPhase().tick(this.goalOwner, this.getCachedUnrotatedRelativePos(), this.distanceOffsetTolerance);
        if (this.getCurrentPhase().isFinished())
        {
            this.goalOwner.onStrikePhaseFinish(this.getPhaseType());
            this.startNextPhase();
        }
    }

    protected void setEntityUsedToStrikePos()
    {
        Mob strikingEntity = this.goalOwner.getEntityUsedToStrike();
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
        if (this.getPhaseType() == StrikeAttackPhaseType.STRIKE)
        {
            return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCurrentPhase().getUnrotatedRelativeTargetPos());
        }
        else if (this.goalOwner.getTarget() != null)
        {
            return this.goalOwner.getTarget().position();
        }
        else {return null;}
    }

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

    public void skipToRecoveryPhase()
    {
        if (this.getCurrentPhase().getType() == StrikeAttackPhaseType.RECOVERY) {return;}

        int previousPhaseIndex = this.phaseIndex;
        int newPhaseIndex = this.getNextPhaseIndex(previousPhaseIndex);
        while (this.getPhase(newPhaseIndex).getType() != StrikeAttackPhaseType.RECOVERY && newPhaseIndex != previousPhaseIndex) //newPhaseIndex != previousPhaseIndex to avoid infinite cycle if there is no recovery phase in sequence (should never happen)
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
}