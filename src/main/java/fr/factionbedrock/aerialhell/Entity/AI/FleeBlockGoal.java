package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

public class FleeBlockGoal<T extends Block> extends Goal
{
    private static final int BOX_TO_CHECK_SIZE_XZ = 5;
    private static final int BOX_TO_CHECK_SIZE_Y = 2;
    protected Path path;
    protected BlockPos posToAvoid;
    protected Vec3 fleePos;
    List<T> blocksToAvoid;
    private final PathfinderMob goalOwner;
    protected final PathNavigation navigator;
    private final double nearSpeed;
    private final double farSpeed;

    public FleeBlockGoal(PathfinderMob entity, List<T> blocks, double farSpeedIn, double nearSpeedIn)
    {
        this.goalOwner = entity;
        this.navigator = entity.getNavigation();
        this.blocksToAvoid = blocks;
        this.nearSpeed = nearSpeedIn;
        this.farSpeed = farSpeedIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse()
    {
        Vec3i goalOwnerPos = new Vec3i(this.goalOwner.getX(), this.goalOwner.getY(), this.goalOwner.getZ());
        this.posToAvoid = this.getPosToAvoid();
        if (this.posToAvoid == null) {return false;}
        else
        {
            Vec3 posToAvoidVector = new Vec3(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ());
            this.fleePos = DefaultRandomPos.getPosAway(this.goalOwner, 16, 7, posToAvoidVector);
            if (this.fleePos == null) {return false;}
            else
            {
                if (this.posToAvoid.distSqr(new Vec3i(fleePos.x, fleePos.y, fleePos.z)) < this.posToAvoid.distSqr(goalOwnerPos)) {return false;}
                this.path = this.navigator.createPath(fleePos.x, fleePos.y, fleePos.z, 0);
                return this.path != null;
            }
        }
    }

    @Override public void start() {this.navigator.moveTo(this.path, this.farSpeed);}
    @Override public boolean canContinueToUse() {return !this.navigator.isDone() && this.posToAvoid != null;}
    @Override public void stop() {this.posToAvoid = null; this.fleePos = null;}

    @Override
    public void tick()
    {
        if (this.goalOwner.distanceToSqr(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ()) < 49.0D) {this.navigator.setSpeedModifier(this.nearSpeed);}
        else {this.navigator.setSpeedModifier(this.farSpeed);}
    }

    @Nullable
    protected BlockPos getPosToAvoid()
    {
        int x = (int) goalOwner.getX(), y = (int) goalOwner.getY(), z = (int) goalOwner.getZ();
        for (int dx = -BOX_TO_CHECK_SIZE_XZ; dx <= BOX_TO_CHECK_SIZE_XZ; dx++)
        {
            for (int dy = -BOX_TO_CHECK_SIZE_Y; dy <= BOX_TO_CHECK_SIZE_Y; dy++)
            {
                for (int dz = -BOX_TO_CHECK_SIZE_XZ; dz <= BOX_TO_CHECK_SIZE_XZ; dz++)
                {
                    BlockPos currentPos = new BlockPos(x+dx, y+dy, z+dz);
                    Block currentBlock = this.goalOwner.level.getBlockState(currentPos).getBlock();
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