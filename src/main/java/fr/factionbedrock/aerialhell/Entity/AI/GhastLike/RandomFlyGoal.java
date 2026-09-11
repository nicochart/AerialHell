package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

/* Same as net.minecraft.world.entity.monster.Ghast.RandomFloatAroundGoal but changed Ghast to Mob */
public class RandomFlyGoal extends Goal
{
    private final Mob parentEntity;

    public RandomFlyGoal(Mob flyingMob)
    {
        this.parentEntity = flyingMob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public Mob getParentEntity() {return parentEntity;}

    @Override public boolean canUse()
    {
        MoveControl movementcontroller = this.parentEntity.getMoveControl();
        if (!movementcontroller.hasWanted())
        {
            return true;
        }
        else
        {
            double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
            double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
            double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }
    }

    @Override public boolean canContinueToUse() {return false;}

    @Override public void start()
    {
        RandomSource random = this.parentEntity.getRandom();
        double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
        this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
    }
}
