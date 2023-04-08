package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.EntityPredicates;

import java.util.List;
import java.util.stream.Collectors;

public class ActiveMisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends ActiveNearestAttackableTargetGoal<T>
{
    public ActiveMisleadableNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findNearestTarget()
    {
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {super.findNearestTarget();}
        else
        {
            double x = this.activableGoalOwner.getPosX(), y = this.activableGoalOwner.getPosYEye(), z = this.activableGoalOwner.getPosZ();
            List<Entity> nearbyEntities = this.activableGoalOwner.world.getEntitiesInAABBexcluding(this.activableGoalOwner, this.activableGoalOwner.getBoundingBox().grow(20), EntityPredicates.withinRange(x, y, z, 16));

            List<PlayerEntity> nearbyTargetablePlayers = nearbyEntities.stream()
                                                                       .filter(entity -> entity instanceof PlayerEntity)
                                                                       .filter(entity -> !isPlayerMisleadingGoalOwner((PlayerEntity) entity))
                                                                       .map(entity -> (PlayerEntity) entity)
                                                                       .collect(Collectors.toList());
            this.nearestTarget = this.activableGoalOwner.world.getClosestEntity(nearbyTargetablePlayers, this.targetEntitySelector, target, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(PlayerEntity player) {return false;}
}
