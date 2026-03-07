package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.world.entity.PathfinderMob;

public interface GoalConditionEntity extends BaseMobEntityInterface
{
    /* ---------- Global methods needing implementation ---------- */
    @Override PathfinderMob getSelf();
    
    boolean checkGoalCondition(int goalIndex);
    /* ----------------------------------------------------------- */

    interface GoalSimpleConditionEntity extends GoalConditionEntity
    {
        /* ---------- Global methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition();
        /* ----------------------------------------------------------- */

        default boolean checkGoalCondition(int goalIndex) {return this.canUseGoalsAdditionalCondition();}
    }

    interface PhaseAwareGoalConditionEntity extends GoalConditionEntity
    {
        /* ---------- Methods needing implementation ---------- */
        boolean canUseGoalsAdditionalCondition(int goalIndex);
        /* ---------------------------------------------------- */

        default boolean checkGoalCondition(int goalIndex) {return canUseGoalsAdditionalCondition(goalIndex);}
    }
}
