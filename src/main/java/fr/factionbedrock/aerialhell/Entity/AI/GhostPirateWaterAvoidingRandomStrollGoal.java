package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.entity.ai.FuzzyPositions;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToDoubleFunction;

//custom random stroll goal to consider ghost blocks as stable destination
public class GhostPirateWaterAvoidingRandomStrollGoal extends WanderAroundFarGoal
{
    public GhostPirateWaterAvoidingRandomStrollGoal(PathAwareEntity mob, double speedIn) {super(mob, speedIn);}

    @Override @Nullable protected Vec3d getWanderTarget()
    {
        if (this.mob.isTouchingWater())
        {
            Vec3d vec3 = FuzzyTargeting.find(this.mob, 15, 7);
            return vec3 == null ? super.getWanderTarget() : vec3;
        }
        else
        {
            return this.mob.getRandom().nextFloat() >= this.probability ? getPos(this.mob, 10, 7, mob::getPathfindingFavor) : super.getWanderTarget();
        }
    }

    //copy of LandRandomPos.getPos(PathAwareEntity mob, int xzDistance, int yDistance, ToDoubleFunction<BlockPos> walkTargetValue) calling custom generateRandomPosTowardDirection
    @Nullable public static Vec3d getPos(PathAwareEntity mob, int xzDistance, int yDistance, ToDoubleFunction<BlockPos> walkTargetValue)
    {
        boolean flag = NavigationConditions.isPositionTargetInRange(mob, xzDistance);
        return FuzzyPositions.guessBest(() ->
        {
            BlockPos randomDirection = FuzzyPositions.localFuzz(mob.getRandom(), xzDistance, yDistance);
            BlockPos targetPos = generateRandomPosTowardDirection(mob, xzDistance, flag, randomDirection);
            return targetPos == null ? null : FuzzyTargeting.validate(mob, targetPos);
        }, walkTargetValue);
    }

    //copy of LandRandomPos.generateRandomPosTowardDirection(PathAwareEntity mob, int xzDistance, boolean mobRestricted, BlockPos randomDirection) not calling GoalUtils.isNotStable(...). calling isStableDestination(...) instead
    @Nullable public static BlockPos generateRandomPosTowardDirection(PathAwareEntity mob, int xzDistance, boolean mobRestricted, BlockPos randomDirection)
    {
        BlockPos blockpos = FuzzyPositions.towardTarget(mob, xzDistance, mob.getRandom(), randomDirection);
        return !NavigationConditions.isHeightInvalid(blockpos, mob) && !NavigationConditions.isPositionTargetOutOfWalkRange(mobRestricted, mob, blockpos) && isStableDestination(mob, blockpos) ? blockpos : null;
    }

    public static boolean isStableDestination(PathAwareEntity mob, BlockPos pos)
    {
        return !NavigationConditions.isInvalidPosition(mob.getNavigation(), pos) || mob.getEntityWorld().getBlockState(pos).isIn(AerialHellTags.Blocks.GHOST_BLOCK);
    }
}
