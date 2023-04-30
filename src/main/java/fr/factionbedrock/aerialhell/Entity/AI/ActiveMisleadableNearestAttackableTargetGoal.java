package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ActiveMisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends ActiveNearestAttackableTargetGoal<T>
{
    public ActiveMisleadableNearestAttackableTargetGoal(AbstractActivableEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findTarget()
    {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {super.findTarget();}
        else
        {
            double x = this.activableGoalOwner.getX(), y = this.activableGoalOwner.getEyeY(), z = this.activableGoalOwner.getZ();
            List<Entity> nearbyEntities = this.activableGoalOwner.level.getEntities(this.activableGoalOwner, this.activableGoalOwner.getBoundingBox().inflate(20), EntitySelector.withinDistance(x, y, z, 16));

            List<Player> nearbyTargetablePlayers = nearbyEntities.stream()
                                                                       .filter(entity -> entity instanceof Player)
                                                                       .filter(entity -> !isPlayerMisleadingGoalOwner((Player) entity))
                                                                       .map(entity -> (Player) entity)
                                                                       .collect(Collectors.toList());
            this.target = this.activableGoalOwner.level.getNearestEntity(nearbyTargetablePlayers, this.targetConditions, target, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(Player player) {return false;}
}
