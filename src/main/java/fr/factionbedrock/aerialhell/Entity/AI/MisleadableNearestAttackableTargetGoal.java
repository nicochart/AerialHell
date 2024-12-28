package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends ActiveTargetGoal<T>
{
    public MisleadableNearestAttackableTargetGoal(MobEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findClosestTarget()
    {
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {super.findClosestTarget();}
        else
        {
            double x = this.mob.getX(), y = this.mob.getEyeY(), z = this.mob.getZ();
            List<Entity> nearbyEntities = this.mob.getWorld().getOtherEntities(this.mob, this.mob.getBoundingBox().expand(20), EntityPredicates.maxDistance(x, y, z, 16));

            List<PlayerEntity> nearbyTargetablePlayers = nearbyEntities.stream()
                    .filter(entity -> entity instanceof PlayerEntity)
                    .filter(entity -> !isPlayerMisleadingGoalOwner((PlayerEntity) entity))
                    .filter(entity -> this.mob.canSee(entity))
                    .map(entity -> (PlayerEntity) entity)
                    .collect(Collectors.toList());

            this.targetEntity = this.mob.getWorld().getClosestEntity(nearbyTargetablePlayers, this.targetPredicate, targetEntity, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(PlayerEntity player) {return false;}
}
