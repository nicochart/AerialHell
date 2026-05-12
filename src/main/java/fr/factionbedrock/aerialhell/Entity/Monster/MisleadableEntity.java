package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public interface MisleadableEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface

    boolean isMisleadedBy(LivingEntity livingEntity); //return true if the livingEntity should not be attacked by self. Note that (gamemode) creative misleaders are not considered "misleaders" for hurt logic.
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default boolean misleadableHurtServer(ServerLevel serverWorld, DamageSource source, float amount, SuperHurtServerReference superReference) //call and return in damage(level, source, amount) instead of super (do not call super !). superReference is super::damage
    {
        Entity sourceEntity = source.getEntity();
        if (sourceEntity instanceof LivingEntity livingSource && this.isMisleadedBy(livingSource) && !EntityHelper.isCreaOrSpecPlayer(livingSource)) //got hurt by misleading entity
        {
            if (this.canMisleaderHurt())
            {
                if (this.traitorTrigger(source) == TraitorTrigger.ON_HURT)
                {
                    EntityHelper.applyTraitorEffectTo(livingSource);
                }
                return superReference.apply(serverWorld, source, amount); //calling super
            }
            else {return false;}
        }
        else //got hurt by not misleading entity
        {
            return superReference.apply(serverWorld, source, amount); //calling super
        }
    }

    default void misleadableDie(DamageSource damageSource) //call in die() before super.die(..)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (sourceEntity instanceof LivingEntity livingSource && this.isMisleadedBy(livingSource) && !EntityHelper.isCreaOrSpecPlayer(livingSource) && this.traitorTrigger(damageSource) == TraitorTrigger.ON_DEATH)
        {
            EntityHelper.applyTraitorEffectTo(livingSource);
        }
    }

    default boolean misleadableCanAttack(LivingEntity target, SuperCanAttackReference superReference) //call & return in canAttack(target) - do not call super !
    {
        return (!this.isMisleadedBy(target) || EntityHelper.isLivingEntityATraitor(target)) && superReference.apply(target);
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* ------- Other parameter methods to eventually override ------- */
    /* -------------------------------------------------------------- */
    default boolean canMisleaderHurt() {return true;}

    default TraitorTrigger traitorTrigger(DamageSource damageSource) {return TraitorTrigger.ON_DEATH;}
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* --------------------------------------- */
    /* -------- Other utility methods -------- */
    /* --------------------------------------- */
    @Nullable default LivingEntity misleadableFindTarget(TargetingConditions targetConditions) //call server side
    {
        if (!(this.getLevel() instanceof ServerLevel serverWorld)) {return null;}
        else
        {
            double x = this.getX(), y = this.getSelf().getEyeY(), z = this.getZ();
            List<Entity> nearbyEntities = serverWorld.getEntities(this.getSelf(), this.getSelf().getBoundingBox().inflate(20), EntitySelector.withinDistance(x, y, z, 16));

            List<LivingEntity> nearbyTargetablePlayers = this.getTargetableEntitiesFromList(nearbyEntities);
            return serverWorld.getNearestEntity(nearbyTargetablePlayers, targetConditions, this.getSelf(), x, y, z);
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
    enum TraitorTrigger{NEVER, ON_HURT, ON_DEATH}

    @FunctionalInterface interface SuperHurtServerReference {boolean apply(ServerLevel serverLevel, DamageSource damageSource, float amount);}
    @FunctionalInterface interface SuperCanAttackReference{boolean apply(LivingEntity target);}
}
