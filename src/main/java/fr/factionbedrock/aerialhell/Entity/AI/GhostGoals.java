package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.ToDoubleFunction;

public class GhostGoals
{
    public static class GhostPirateNearestAttackableTargetGoal<T extends LivingEntity> extends MisleadableNearestAttackableTargetGoal<T>
    {
        public GhostPirateNearestAttackableTargetGoal(Mob entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(Player player) {return EntityHelper.isImmuneToGhostBlockCollision(player);}
    }

    public static class GhostPirateMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public GhostPirateMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}
        @Override public boolean additionalConditionMet() {return !EntityHelper.isImmuneToGhostBlockCollision(this.goalOwner.getTarget());}
    }

    public static class GhostPirateLookAtPlayerGoal extends AdditionalConditionLookAtPlayerGoal
    {
        public GhostPirateLookAtPlayerGoal(Mob entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {super(entityIn, watchTargetClass, maxDistance);}
        @Override public boolean additionalConditionMet() {return !EntityHelper.isImmuneToGhostBlockCollision(this.goalOwner.getTarget());}
    }

    //custom random stroll goal to consider ghost blocks as stable destination
    public static class GhostPirateWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal
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

        //copy of LandRandomPos.getPos(PathfinderMob mob, int xzDistance, int yDistance, ToDoubleFunction<BlockPos> walkTargetValue) calling custom generateRandomPosTowardDirection
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

        //copy of LandRandomPos.generateRandomPosTowardDirection(PathfinderMob mob, int xzDistance, boolean mobRestricted, BlockPos randomDirection) not calling GoalUtils.isNotStable(...). calling isStableDestination(...) instead
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
}
