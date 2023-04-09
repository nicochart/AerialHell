package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

public class FleeBlockGoal<T extends Block> extends Goal
{
    private static final int BOX_TO_CHECK_SIZE_XZ = 5;
    private static final int BOX_TO_CHECK_SIZE_Y = 2;
    protected Path path;
    protected BlockPos posToAvoid;
    protected Vector3d fleePos;
    List<T> blocksToAvoid;
    private final CreatureEntity goalOwner;
    protected final PathNavigator navigator;
    private final double nearSpeed;
    private final double farSpeed;

    public FleeBlockGoal(CreatureEntity entity, List<T> blocks, double farSpeedIn, double nearSpeedIn)
    {
        this.goalOwner = entity;
        this.navigator = entity.getNavigator();
        this.blocksToAvoid = blocks;
        this.nearSpeed = nearSpeedIn;
        this.farSpeed = farSpeedIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute()
    {
        Vector3i goalOwnerPos = new Vector3i(this.goalOwner.getPosX(), this.goalOwner.getPosY(), this.goalOwner.getPosZ());
        this.posToAvoid = this.getPosToAvoid();
        if (this.posToAvoid == null) {return false;}
        else
        {
            Vector3d posToAvoidVector = new Vector3d(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ());
            this.fleePos = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.goalOwner, 16, 7, posToAvoidVector);
            if (this.fleePos == null) {return false;}
            else
            {
                if (this.posToAvoid.distanceSq(new Vector3i(fleePos.x, fleePos.y, fleePos.z)) < this.posToAvoid.distanceSq(goalOwnerPos)) {return false;}
                this.path = this.navigator.getPathToPos(fleePos.x, fleePos.y, fleePos.z, 0);
                return this.path != null;
            }
        }
    }

    @Override public void startExecuting() {this.navigator.setPath(this.path, this.farSpeed);}
    @Override public boolean shouldContinueExecuting() {return !this.navigator.noPath() && this.posToAvoid != null;}
    @Override public void resetTask() {this.posToAvoid = null; this.fleePos = null;}

    @Override
    public void tick()
    {
        if (this.goalOwner.getDistanceSq(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ()) < 49.0D) {this.navigator.setSpeed(this.nearSpeed);}
        else {this.navigator.setSpeed(this.farSpeed);}
    }

    @Nullable
    protected BlockPos getPosToAvoid()
    {
        int x = (int) goalOwner.getPosX(), y = (int) goalOwner.getPosY(), z = (int) goalOwner.getPosZ();
        for (int dx = -BOX_TO_CHECK_SIZE_XZ; dx <= BOX_TO_CHECK_SIZE_XZ; dx++)
        {
            for (int dy = -BOX_TO_CHECK_SIZE_Y; dy <= BOX_TO_CHECK_SIZE_Y; dy++)
            {
                for (int dz = -BOX_TO_CHECK_SIZE_XZ; dz <= BOX_TO_CHECK_SIZE_XZ; dz++)
                {
                    BlockPos currentPos = new BlockPos(x+dx, y+dy, z+dz);
                    Block currentBlock = this.goalOwner.world.getBlockState(currentPos).getBlock();
                    for (Block block : blocksToAvoid)
                    {
                        if (currentBlock == block) {return currentPos;}
                    }
                }
            }
        }
        return null;
    }
}