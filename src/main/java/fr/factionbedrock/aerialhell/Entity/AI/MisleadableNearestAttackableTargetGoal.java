package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class MisleadableNearestAttackableTargetGoal<T extends LivingEntity>  extends NearestAttackableTargetGoal<T>
{
    public MisleadableNearestAttackableTargetGoal(Mob entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}

    @Override protected void findTarget()
    {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {super.findTarget();}
        else
        {
            double x = this.mob.getX(), y = this.mob.getEyeY(), z = this.mob.getZ();
            List<Entity> nearbyEntities = this.mob.level().getEntities(this.mob, this.mob.getBoundingBox().inflate(20), EntitySelector.withinDistance(x, y, z, 16));

            List<Player> nearbyTargetablePlayers = nearbyEntities.stream()
                    .filter(entity -> entity instanceof Player)
                    .filter(entity -> !isPlayerMisleadingGoalOwner((Player) entity))
                    .map(entity -> (Player) entity)
                    .collect(Collectors.toList());
            this.target = this.mob.level().getNearestEntity(nearbyTargetablePlayers, this.targetConditions, target, x, y, z);
        }
    }

    public boolean isPlayerMisleadingGoalOwner(Player player) {return false;}
}
