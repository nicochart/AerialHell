package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.EntityPredicates;

import java.util.List;
import java.util.stream.Collectors;

public class MisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends NearestAttackableTargetGoal<T>
{
    public MisleadableNearestAttackableTargetGoal(MobEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findNearestTarget()
    {
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {super.findNearestTarget();}
        else
        {
            double x = this.goalOwner.getPosX(), y = this.goalOwner.getPosYEye(), z = this.goalOwner.getPosZ();
            List<Entity> nearbyEntities = this.goalOwner.world.getEntitiesInAABBexcluding(this.goalOwner, this.goalOwner.getBoundingBox().grow(20), EntityPredicates.withinRange(x, y, z, 16));

            List<PlayerEntity> nearbyTargetablePlayers = nearbyEntities.stream()
                                                                       .filter(entity -> entity instanceof PlayerEntity)
                                                                       .filter(entity -> !isPlayerMisleadingGoalOwner((PlayerEntity) entity))
                                                                       .map(entity -> (PlayerEntity) entity)
                                                                       .collect(Collectors.toList());
            this.nearestTarget = this.goalOwner.world.getClosestEntity(nearbyTargetablePlayers, this.targetEntitySelector, target, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(PlayerEntity player) {return false;}
}
