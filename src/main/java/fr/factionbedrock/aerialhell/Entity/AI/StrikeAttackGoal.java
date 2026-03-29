package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StrikeAttackGoal extends Goal
{
    private final StrikeAttackEntity entity;
    private final float distanceOffsetTolerance;
    private int phaseIndex;
    private Vec3 cachedUnrotatedRelativePos;

    public StrikeAttackGoal(StrikeAttackEntity entity, float distanceOffsetTolerance)
    {
        this.entity = entity;
        this.distanceOffsetTolerance = distanceOffsetTolerance;
        this.phaseIndex = 0;
    }

    public List<StrikeAttackPhase> getPhases() {return this.entity.getStrikeAttackSequence();}

    public StrikeAttackPhase getCurrentPhase() {return this.getPhase(this.phaseIndex);}
    public StrikeAttackPhase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    public StrikeAttackPhase getPhase(int phaseIndex) {return this.getPhases().get(phaseIndex);}

    public StrikeAttackPhaseType getPhaseType() {return this.getCurrentPhase().getType();}

    @Override public boolean canUse()
    {
        return this.isActive() && this.entity.canUseStrikeAttack();
    }

    @Override public boolean canContinueToUse()
    {
        return this.isActive() && this.entity.canUseStrikeAttack();
    }

    @Override public void start()
    {
        this.phaseIndex = 0;
    }

    @Override public void stop()
    {
        this.phaseIndex = 0;
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        this.updateUnrotatedRelativePos();

        this.getCurrentPhase().tick(this.entity, this.getCachedUnrotatedRelativePos(), this.distanceOffsetTolerance);
        if (this.getCurrentPhase().isFinished())
        {
            this.entity.onStrikePhaseFinish(this.getPhaseType());
            this.startNextPhase();
        }
    }

    public boolean isActive() {return this.getCurrentPhase().getType() != StrikeAttackPhaseType.INACTIVE;}
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
        return this.getCurrentPhase().getDistanceToTarget(this.cachedUnrotatedRelativePos);
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
        int nextPhaseIndex = this.phaseIndex + 1;
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