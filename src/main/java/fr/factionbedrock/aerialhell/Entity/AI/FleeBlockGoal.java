package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class FleeBlockGoal<T extends Block> extends Goal
{
    private static final int BOX_TO_CHECK_SIZE_XZ = 5;
    private static final int BOX_TO_CHECK_SIZE_Y = 2;
    protected Path path;
    protected BlockPos posToAvoid;
    protected Vec3d fleePos;
    List<T> blocksToAvoid;
    private final PathAwareEntity goalOwner;
    protected final EntityNavigation navigator;
    private final double nearSpeed;
    private final double farSpeed;

    public FleeBlockGoal(PathAwareEntity entity, List<T> blocks, double farSpeedIn, double nearSpeedIn)
    {
        this.goalOwner = entity;
        this.navigator = entity.getNavigation();
        this.blocksToAvoid = blocks;
        this.nearSpeed = nearSpeedIn;
        this.farSpeed = farSpeedIn;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart()
    {
        Vec3i goalOwnerPos = new Vec3i((int)this.goalOwner.getX(), (int)this.goalOwner.getY(), (int)this.goalOwner.getZ());
        this.posToAvoid = this.getPosToAvoid();
        if (this.posToAvoid == null) {return false;}
        else
        {
            Vec3d posToAvoidVector = new Vec3d(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ());
            this.fleePos = NoPenaltyTargeting.findFrom(this.goalOwner, 16, 7, posToAvoidVector);
            if (this.fleePos == null) {return false;}
            else
            {
                if (this.posToAvoid.getSquaredDistance(new Vec3i((int)fleePos.x, (int)fleePos.y, (int)fleePos.z)) < this.posToAvoid.getSquaredDistance(goalOwnerPos)) {return false;}
                this.path = this.navigator.findPathTo(fleePos.x, fleePos.y, fleePos.z, 0);
                return this.path != null;
            }
        }
    }

    @Override public void start() {this.navigator.startMovingAlong(this.path, this.farSpeed);}
    @Override public boolean shouldContinue() {return !this.navigator.isIdle() && this.posToAvoid != null;}
    @Override public void stop() {this.posToAvoid = null; this.fleePos = null;}

    @Override
    public void tick()
    {
        if (this.goalOwner.squaredDistanceTo(this.posToAvoid.getX(), this.posToAvoid.getY(), this.posToAvoid.getZ()) < 49.0D) {this.navigator.setSpeed(this.nearSpeed);}
        else {this.navigator.setSpeed(this.farSpeed);}
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
                    Block currentBlock = this.goalOwner.getEntityWorld().getBlockState(currentPos).getBlock();
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