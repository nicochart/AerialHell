package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToDoubleFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.phys.Vec3;

//custom random stroll goal to consider ghost blocks as stable destination
public class GhostPirateWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal
{
    public GhostPirateWaterAvoidingRandomStrollGoal(PathfinderMob mob, double speedIn) {super(mob, speedIn);}

    @Override @Nullable protected Vec3 getPosition()
    {
        if (this.mob.isInWater())
        {
            Vec3 vec3 = LandRandomPos.getPos(this.mob, 15, 7);
            return vec3 == null ? super.getPosition() : vec3;
        }
        else
        {
            return this.mob.getRandom().nextFloat() >= this.probability ? getPos(this.mob, 10, 7, mob::getWalkTargetValue) : super.getPosition();
        }
    }

    //copy of LandRandomPos.getPos(PathAwareEntity mob, int xzDistance, int yDistance, ToDoubleFunction<BlockPos> walkTargetValue) calling custom generateRandomPosTowardDirection
    @Nullable public static Vec3 getPos(PathfinderMob mob, int xzDistance, int yDistance, ToDoubleFunction<BlockPos> walkTargetValue)
    {
        boolean flag = GoalUtils.mobRestricted(mob, xzDistance);
        return RandomPos.generateRandomPos(() ->
        {
            BlockPos randomDirection = RandomPos.generateRandomDirection(mob.getRandom(), xzDistance, yDistance);
            BlockPos targetPos = generateRandomPosTowardDirection(mob, xzDistance, flag, randomDirection);
            return targetPos == null ? null : LandRandomPos.movePosUpOutOfSolid(mob, targetPos);
        }, walkTargetValue);
    }

    //copy of LandRandomPos.generateRandomPosTowardDirection(PathAwareEntity mob, int xzDistance, boolean mobRestricted, BlockPos randomDirection) not calling GoalUtils.isNotStable(...). calling isStableDestination(...) instead
    @Nullable public static BlockPos generateRandomPosTowardDirection(PathfinderMob mob, int xzDistance, boolean mobRestricted, BlockPos randomDirection)
    {
        BlockPos blockpos = RandomPos.generateRandomPosTowardDirection(mob, xzDistance, mob.getRandom(), randomDirection);
        return !GoalUtils.isOutsideLimits(blockpos, mob) && !GoalUtils.isRestricted(mobRestricted, mob, blockpos) && isStableDestination(mob, blockpos) ? blockpos : null;
    }

    public static boolean isStableDestination(PathfinderMob mob, BlockPos pos)
    {
        return !GoalUtils.isNotStable(mob.getNavigation(), pos) || mob.level().getBlockState(pos).is(AerialHellTags.Blocks.GHOST_BLOCK);
    }
}
