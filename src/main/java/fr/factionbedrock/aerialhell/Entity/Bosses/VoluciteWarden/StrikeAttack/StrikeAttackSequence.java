package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StrikeAttackSequence
{
    private final List<StrikeAttackPhase> phases;
    private int phaseIndex = 0;

    public StrikeAttackSequence(List<StrikeAttackPhase> phases)
    {
        this.phases = phases;
    }

    public StrikeAttackPhase getCurrentPhase()
    {
        return this.phases.get(this.phaseIndex);
    }

    public void tick(Vec3 currentUnrotatedRelativePos)
    {
        this.getCurrentPhase().tick(currentUnrotatedRelativePos);
        if (this.getCurrentPhase().isFinished())
        {
            this.nextPhase();
        }
    }

    public boolean isAtTargetPos(Vec3 currentRelativePos)
    {
        return this.getCurrentPhase().isAtTargetPos(currentRelativePos);
    }

    private void nextPhase()
    {
        this.phaseIndex++;
        if (this.phaseIndex >= this.phases.size()) {this.phaseIndex = 0;}
        getCurrentPhase().reset();
    }
}
