package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface StrikeAttackEntity extends BaseMobEntityInterface
{
    List<StrikeAttackPhase> getStrikeAttackSequence();

    boolean canUseStrikeAttack();

    void strike();

    @Nullable default Mob getEntityUsedToStrike() {return this.getSelf();} //entity that is moving and will strike (can be different from goal owner)

    default boolean shouldTrigger() {return this.canUseStrikeAttack();} //return false if you want the goal to only be active when manually triggered

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
