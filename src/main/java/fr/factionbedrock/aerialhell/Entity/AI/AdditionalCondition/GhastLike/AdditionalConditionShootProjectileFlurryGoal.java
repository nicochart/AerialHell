package fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.GhastLike;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileFlurryGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.world.entity.Mob;

public abstract class AdditionalConditionShootProjectileFlurryGoal extends ShootProjectileFlurryGoal
{
    protected final int goalIndex;

    public AdditionalConditionShootProjectileFlurryGoal(Mob mob) {this(mob, 0);}
    public AdditionalConditionShootProjectileFlurryGoal(Mob mob, int goalIndex) {this(mob, false, goalIndex);}
    public AdditionalConditionShootProjectileFlurryGoal(Mob mob, boolean affectMovements) {this(mob, affectMovements, 0);}
    public AdditionalConditionShootProjectileFlurryGoal(Mob mob, boolean affectMovements, int goalIndex)
    {
        super(mob, affectMovements);
        this.goalIndex = goalIndex;
    }

    public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity) this.getParentEntity();}

    @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canUse();}
    @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(goalIndex) && super.canContinueToUse();}
}
