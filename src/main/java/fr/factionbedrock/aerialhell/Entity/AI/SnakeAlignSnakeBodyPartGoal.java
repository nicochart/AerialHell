package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import java.util.EnumSet;
import net.minecraft.world.entity.ai.goal.Goal;

public class SnakeAlignSnakeBodyPartGoal extends Goal
{
    protected AbstractSnakeEntity snakeGoalOwner;

    public SnakeAlignSnakeBodyPartGoal(AbstractSnakeEntity entity)
    {
        this.snakeGoalOwner = entity;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override public boolean canUse() {return !this.snakeGoalOwner.isHead();}

    @Override public boolean canContinueToUse() {return !this.snakeGoalOwner.isHead();}

    @Override public void tick()
    {
        if (this.snakeGoalOwner.getPreviousBodyPart() != null) {this.snakeGoalOwner.lookAt(this.snakeGoalOwner.getPreviousBodyPart(), 30.0F, 30.0F);}
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}
}
