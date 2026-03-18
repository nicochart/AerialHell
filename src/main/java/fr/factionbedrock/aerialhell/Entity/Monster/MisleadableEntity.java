package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
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
    default boolean misleadableDamage(ServerWorld serverWorld, DamageSource source, float amount, SuperDamageReference superReference) //call and return in damage(level, source, amount) instead of super (do not call super !). superReference is super::damage
    {
        Entity sourceEntity = source.getAttacker();
        if (sourceEntity instanceof LivingEntity livingSource && this.isMisleadedBy(livingSource) && !EntityHelper.isCreaOrSpecPlayer(livingSource)) //got hurt by misleading entity
        {
            if (this.canMisleaderDamage())
            {
                if (this.doesApplyTraitorEffectToMisleaderDamageSource(livingSource))
                {
                    livingSource.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.TRAITOR, 12000, 0));
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
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* ------- Other parameter methods to eventually override ------- */
    /* -------------------------------------------------------------- */
    default boolean canMisleaderDamage() {return true;}

    default boolean doesApplyTraitorEffectToMisleaderDamageSource(LivingEntity damageSource) {return this.canMisleaderDamage();}
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* --------------------------------------- */
    /* -------- Other utility methods -------- */
    /* --------------------------------------- */
    @Nullable default LivingEntity misleadableFindTarget(TargetPredicate targetConditions) //call server side
    {
        if (!(this.getLevel() instanceof ServerWorld serverWorld)) {return null;}
        else
        {
            double x = this.getX(), y = this.getSelf().getEyeY(), z = this.getZ();
            List<Entity> nearbyEntities = serverWorld.getOtherEntities(this.getSelf(), this.getSelf().getBoundingBox().expand(20), EntityPredicates.maxDistance(x, y, z, 16));

            List<LivingEntity> nearbyTargetablePlayers = this.getTargetableEntitiesFromList(nearbyEntities);
            return serverWorld.getClosestEntity(nearbyTargetablePlayers, targetConditions, this.getSelf(), x, y, z);
        }
    }

    default List<LivingEntity> getTargetableEntitiesFromList(List<Entity> list)
    {
        return list.stream()
                .filter(entity -> entity instanceof LivingEntity)
                .filter(entity -> !this.isMisleadedBy((PlayerEntity) entity))
                .map(entity -> (PlayerEntity) entity)
                .collect(Collectors.toList());
    }
    /* --------------------------------------- */
    /* --------------------------------------- */
    /* --------------------------------------- */

    @FunctionalInterface interface SuperDamageReference {boolean apply(ServerWorld serverWorld, DamageSource damageSource, float amount);}
}
