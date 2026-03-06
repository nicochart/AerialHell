package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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

    boolean isMisleadedBy(LivingEntity livingEntity); //return true if the livingEntity should not be attacked by self. Note that (gamemode) creative misleaders are not considered "misleaders" for hurt logic.
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default boolean misleadableHurtServer(ServerLevel serverLevel, DamageSource source, float amount, SuperHurtServerReference superReference) //call and return in hurtServer(level, source, amount) instead of super (do not call super !). superReference is super::hurtServer
    {
        Entity sourceEntity = source.getEntity();
        if (sourceEntity instanceof LivingEntity livingSource && this.isMisleadedBy(livingSource) && !EntityHelper.isCreaOrSpecPlayer(livingSource)) //got hurt by misleading entity
        {
            if (this.canMisleaderHurt())
            {
                if (this.doesApplyTraitorEffectToMisleaderHurtSource(livingSource))
                {
                    livingSource.addEffect(new MobEffectInstance(AerialHellMobEffects.TRAITOR.getDelegate(), 12000, 0));
                }
                return superReference.apply(serverLevel, source, amount); //calling super
            }
            else {return false;}
        }
        else //got hurt by not misleading entity
        {
            return superReference.apply(serverLevel, source, amount); //calling super
        }
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* ------- Other parameter methods to eventually override ------- */
    /* -------------------------------------------------------------- */
    default boolean canMisleaderHurt() {return true;}

    default boolean doesApplyTraitorEffectToMisleaderHurtSource(LivingEntity hurtSource) {return this.canMisleaderHurt();}
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

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

    @FunctionalInterface interface SuperHurtServerReference{boolean apply(ServerLevel serverLevel, DamageSource damageSource, float amount);}
}
