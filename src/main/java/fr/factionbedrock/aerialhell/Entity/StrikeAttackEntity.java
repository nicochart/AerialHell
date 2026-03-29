package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;

import java.util.List;

public interface StrikeAttackEntity extends BaseMobEntityInterface
{
    List<StrikeAttackPhase> getStrikeAttackSequence();

    boolean canUseStrikeAttack();

    void strike();

    default void onStrikePhaseStartFinishing(StrikeAttackPhaseType currentPhaseType) //when entity reaches target pos
    {
        if (currentPhaseType == StrikeAttackPhaseType.STRIKE)
        {
            this.strike();
        }
    }

    default void onStrikePhaseFinish(StrikeAttackPhaseType currentPhaseType) //when entity stayed at target pos for long enough so the sequence updates to next phase
    {

    }
}
