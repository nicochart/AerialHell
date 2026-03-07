package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.world.entity.PathfinderMob;

public interface GoalConditionEntity extends BaseMobEntityInterface
{
    /* ---------- Global methods needing implementation ---------- */
    @Override PathfinderMob getSelf();
    
    boolean checkGoalCondition(int phase);
    /* ----------------------------------------------------------- */

    interface GoalSimpleConditionEntity extends GoalConditionEntity
    {
        /* ---------- Global methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition();
        /* ----------------------------------------------------------- */

        default boolean checkGoalCondition(int phase) {return this.canUseGoalsAdditionalCondition();}
    }

    interface PhaseAwareGoalConditionEntity extends GoalConditionEntity
    {
        /* ---------- Methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition(int phase);
        /* ---------------------------------------------------- */

        default boolean checkGoalCondition(int phase) {return canUseGoalsAdditionalCondition(phase);}
    }
}
