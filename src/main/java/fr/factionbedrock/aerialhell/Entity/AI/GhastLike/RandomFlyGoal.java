package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.random.Random;

import java.util.EnumSet;

/* Same as net.minecraft.world.entity.monster.Ghast.RandomFloatAroundGoal but changed Ghast to MobEntity */
public class RandomFlyGoal extends Goal
{
    private final MobEntity parentEntity;

    public RandomFlyGoal(MobEntity flyingMob)
    {
        this.parentEntity = flyingMob;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public MobEntity getParentEntity() {return parentEntity;}

    @Override public boolean canStart()
    {
        MoveControl movementcontroller = this.parentEntity.getMoveControl();
        if (!movementcontroller.isMoving())
        {
            return true;
        }
        else
        {
            double d0 = movementcontroller.getTargetX() - this.parentEntity.getX();
            double d1 = movementcontroller.getTargetY() - this.parentEntity.getY();
            double d2 = movementcontroller.getTargetZ() - this.parentEntity.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }
    }

    @Override public boolean shouldContinue() {return false;}

    @Override public void start()
    {
        Random random = this.parentEntity.getRandom();
        double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        this.parentEntity.getMoveControl().moveTo(d0, d1, d2, 1.0);
    }
}
