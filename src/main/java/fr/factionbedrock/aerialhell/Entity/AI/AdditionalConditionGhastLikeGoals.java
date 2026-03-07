package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AdditionalConditionGhastLikeGoals
{
    public static class LookAroundGoal extends GhastLikeGoals.LookAroundGoal
    {
        protected final int phase;
        public LookAroundGoal(GoalConditionEntity flyingMob) {this(flyingMob, 0);}
        public LookAroundGoal(GoalConditionEntity flyingMob, int goalPhase)
        {
            super(flyingMob.getSelf());
            this.phase = goalPhase;
        }

        public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity)this.getParentEntity();}

        @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canUse();}
        @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canContinueToUse();}
    }

    public static class RandomFlyGoal extends GhastLikeGoals.RandomFlyGoal
    {
        protected final int phase;
        public RandomFlyGoal(GoalConditionEntity flyingMob) {this(flyingMob, 0);}
        public RandomFlyGoal(GoalConditionEntity flyingMob, int goalPhase)
        {
            super(flyingMob.getSelf());
            this.phase = goalPhase;
        }

        public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity)this.getParentEntity();}

        @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canUse();}
        @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canContinueToUse();}
    }

    public abstract static class ShootProjectileGoal extends GhastLikeGoals.ShootProjectileGoal
    {
        protected final int phase;

        public ShootProjectileGoal(GoalConditionEntity mob) {this(mob, false, 0);}
        public ShootProjectileGoal(GoalConditionEntity mob, int goalPhase) {this(mob, false, goalPhase);}
        public ShootProjectileGoal(GoalConditionEntity mob, boolean affectMovements) {this(mob, affectMovements, 0);}
        public ShootProjectileGoal(GoalConditionEntity mob, boolean affectMovements, int goalPhase)
        {
            super(mob.getSelf(), affectMovements);
            this.phase = goalPhase;
        }

        public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity)this.getParentEntity();}

        @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canUse();}
        @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canContinueToUse();}
    }

    public abstract static class ShootProjectileFlurryGoal extends GhastLikeGoals.ShootProjectileFlurryGoal
    {
        protected final int phase;
        public ShootProjectileFlurryGoal(Mob mob) {this(mob, 0);}
        public ShootProjectileFlurryGoal(Mob mob, int goalPhase) {this(mob, false, goalPhase);}
        public ShootProjectileFlurryGoal(Mob mob, boolean affectMovements) {this(mob, affectMovements, 0);}
        public ShootProjectileFlurryGoal(Mob mob, boolean affectMovements, int goalPhase)
        {
            super(mob, affectMovements);
            this.phase = goalPhase;
        }

        public GoalConditionEntity getGoalConditionParentEntity() {return (GoalConditionEntity)this.getParentEntity();}

        @Override public boolean canUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canUse();}
        @Override public boolean canContinueToUse() {return this.getGoalConditionParentEntity().checkGoalCondition(phase) && super.canContinueToUse();}
    }
}
