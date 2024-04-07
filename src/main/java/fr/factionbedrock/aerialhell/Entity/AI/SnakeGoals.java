package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class SnakeGoals
{
    public static class SnakeWaterAvoidingRandomWalkingGoal extends AdditionalConditionWaterAvoidingRandomStrollGoal
    {
        public SnakeWaterAvoidingRandomWalkingGoal(AbstractSnakeEntity entity, double speedIn) {super(entity, speedIn);}
        @Override public boolean additionalConditionMet() {return ((AbstractSnakeEntity)this.mob).isHead();}
    }

    public static class SnakeMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public SnakeMeleeAttackGoal(AbstractSnakeEntity entity, double speedIn) {super(entity, speedIn, false);}
        @Override public boolean additionalConditionMet() {return ((AbstractSnakeEntity)this.mob).isHead();}
    }

    public static class SnakeLookAtPlayerGoal extends AdditionalConditionLookAtPlayerGoal
    {
        protected AbstractSnakeEntity snakeGoalOwner;
        public SnakeLookAtPlayerGoal(AbstractSnakeEntity entity) {super(entity, Player.class, 8.0F); this.snakeGoalOwner = entity;}
        @Override public boolean additionalConditionMet() {return this.snakeGoalOwner.isHead();}
    }

    public static class SnakeRandomLookAroundGoal extends AdditionalConditionRandomLookAroundGoal
    {
        protected AbstractSnakeEntity snakeGoalOwner;
        public SnakeRandomLookAroundGoal(AbstractSnakeEntity entity) {super(entity); this.snakeGoalOwner = entity;}
        @Override public boolean additionalConditionMet() {return this.snakeGoalOwner.isHead();}
    }

    public static class AlignSnakeBodyPartGoal extends Goal
    {
        protected AbstractSnakeEntity snakeGoalOwner;

        public AlignSnakeBodyPartGoal(AbstractSnakeEntity entity)
        {
            this.snakeGoalOwner = entity;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override public boolean canUse() {return !this.snakeGoalOwner.isHead();}
        @Override public boolean canContinueToUse() {return !this.snakeGoalOwner.isHead();}

        @Override public void tick()
        {
            if (this.snakeGoalOwner.getPreviousBodyPart() != null)
            {
                this.snakeGoalOwner.lookAt(this.snakeGoalOwner.getPreviousBodyPart(), 30.0F, 30.0F);
            }
        }

        @Override public boolean requiresUpdateEveryTick() {return true;}
    }
}
