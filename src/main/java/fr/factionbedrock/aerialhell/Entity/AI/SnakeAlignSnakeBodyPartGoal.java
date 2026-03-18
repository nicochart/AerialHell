package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SnakeAlignSnakeBodyPartGoal extends Goal
{
    protected AbstractSnakeEntity snakeGoalOwner;

    public SnakeAlignSnakeBodyPartGoal(AbstractSnakeEntity entity)
    {
        this.snakeGoalOwner = entity;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override public boolean canStart() {return !this.snakeGoalOwner.isHead();}

    @Override public boolean shouldContinue() {return !this.snakeGoalOwner.isHead();}

    @Override public void tick()
    {
        if (this.snakeGoalOwner.getPreviousBodyPart() != null) {this.snakeGoalOwner.lookAtEntity(this.snakeGoalOwner.getPreviousBodyPart(), 30.0F, 30.0F);}
    }

    @Override public boolean shouldRunEveryTick() {return true;}
}
