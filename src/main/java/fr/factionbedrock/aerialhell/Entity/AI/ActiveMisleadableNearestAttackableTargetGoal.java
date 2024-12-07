package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ActiveMisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends ActiveNearestAttackableTargetGoal<T>
{
    public ActiveMisleadableNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findClosestTarget()
    {
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {super.findClosestTarget();}
        else
        {
            double x = this.activableGoalOwner.getX(), y = this.activableGoalOwner.getEyeY(), z = this.activableGoalOwner.getZ();
            List<Entity> nearbyEntities = this.activableGoalOwner.getWorld().getOtherEntities(this.activableGoalOwner, this.activableGoalOwner.getBoundingBox().expand(20), EntityPredicates.maxDistance(x, y, z, 16));

            List<PlayerEntity> nearbyTargetablePlayers = nearbyEntities.stream()
                                                                       .filter(entity -> entity instanceof PlayerEntity)
                                                                       .filter(entity -> !isPlayerMisleadingGoalOwner((PlayerEntity) entity))
                                                                       .map(entity -> (PlayerEntity) entity)
                                                                       .collect(Collectors.toList());
            this.target = this.activableGoalOwner.getWorld().getClosestEntity(nearbyTargetablePlayers, this.targetPredicate, target, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(PlayerEntity player) {return false;}
}
