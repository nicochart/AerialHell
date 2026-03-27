package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StrikeAttackSequence
{
    private final List<StrikeAttackPhase> phases;
    private int phaseIndex = 0;
    private float DISTANCE_OFFSET_TOLERANCE = 0.2F;
    private Vec3 cachedUnrotatedRelativePos;

    public StrikeAttackSequence(List<StrikeAttackPhase> phases) {this.phases = phases;}

    public StrikeAttackPhase getCurrentPhase() {return this.getPhase(this.phaseIndex);}
    public StrikeAttackPhase getPreviousPhase() {return this.getPhase(this.getPreviousPhaseIndex());}
    public StrikeAttackPhase getPhase(int phaseIndex) {return this.phases.get(phaseIndex);}

    public void tick()
    {
        if (!this.isActive()) {return;}

        this.updateUnrotatedRelativePos();

        this.getCurrentPhase().tick(this.getCachedUnrotatedRelativePos(), DISTANCE_OFFSET_TOLERANCE);
        if (this.getCurrentPhase().isFinished()) {this.startNextPhase();}
    }

    public boolean isActive() {return this.getCurrentPhase().getType() != StrikeAttackPhaseType.INACTIVE;}
    public boolean trigger() //return true if the attack sequence is successfully triggered
    {
        if (!this.isActive()) {return false;}
        else
        {
            this.startNextPhase();
            return this.isActive();
        }
    }

    public boolean isAtTargetPos(Vec3 currentRelativePos)
    {
        return this.getCurrentPhase().isAtTargetPos(currentRelativePos, DISTANCE_OFFSET_TOLERANCE);
    }

    private void startNextPhase()
    {
        this.phaseIndex = this.getNextPhaseIndex();
        this.getCurrentPhase().reset();
    }

    private int getNextPhaseIndex()
    {
        int nextPhaseIndex = this.phaseIndex + 1;
        return nextPhaseIndex >= this.phases.size() ? 0 : nextPhaseIndex;
    }

    private int getPreviousPhaseIndex()
    {
        int previousPhaseIndex = this.phaseIndex - 1;
        return previousPhaseIndex < 0 ? this.phases.size() - 1 : previousPhaseIndex;
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
