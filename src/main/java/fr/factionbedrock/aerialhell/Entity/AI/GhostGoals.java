package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;

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
}
