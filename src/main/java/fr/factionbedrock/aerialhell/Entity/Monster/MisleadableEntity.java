package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public interface MisleadableEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    boolean isMisleadedBy(LivingEntity livingEntity); //return true if the livingEntity should not be attacked by self
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* --------------------------------------- */
    /* -------- Other utility methods -------- */
    /* --------------------------------------- */
    @Nullable default LivingEntity misleadableFindTarget(TargetingConditions targetConditions) //call server side
    {
        if (!(this.getLevel() instanceof ServerLevel serverLevel)) {return null;}
        else
        {
            double x = this.getX(), y = this.getSelf().getEyeY(), z = this.getZ();
            List<Entity> nearbyEntities = serverLevel.getEntities(this.getSelf(), this.getSelf().getBoundingBox().inflate(20), EntitySelector.withinDistance(x, y, z, 16));

            List<LivingEntity> nearbyTargetablePlayers = this.getTargetableEntitiesFromList(nearbyEntities);
            return serverLevel.getNearestEntity(nearbyTargetablePlayers, targetConditions, this.getSelf(), x, y, z);
        }
    }

    default List<LivingEntity> getTargetableEntitiesFromList(List<Entity> list)
    {
        return list.stream()
                .filter(entity -> entity instanceof LivingEntity)
                .filter(entity -> !this.isMisleadedBy((Player) entity))
                .map(entity -> (Player) entity)
                .collect(Collectors.toList());
    }
    /* --------------------------------------- */
    /* --------------------------------------- */
    /* --------------------------------------- */
}
