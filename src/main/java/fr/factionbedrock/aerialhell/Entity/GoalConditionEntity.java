package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.world.entity.PathfinderMob;

public interface GoalConditionEntity extends BaseMobEntityInterface
{
    /* ---------- Global methods needing implementation ---------- */
    @Override PathfinderMob getSelf();
    
    boolean checkGoalCondition(int conditionIndex);
    /* ----------------------------------------------------------- */

    interface GoalSimpleConditionEntity extends GoalConditionEntity
    {
        /* ---------- Global methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition();
        /* ----------------------------------------------------------- */

        default boolean checkGoalCondition(int conditionIndex) {return this.canUseGoalsAdditionalCondition();}
    }

    interface PhaseAwareGoalConditionEntity extends GoalConditionEntity
    {
        /* ---------- Methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition(int conditionIndex);
        /* ---------------------------------------------------- */

        default boolean checkGoalCondition(int conditionIndex) {return canUseGoalsAdditionalCondition(conditionIndex);}
    }
}
