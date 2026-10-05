package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface StrikeAttackEntity extends BaseMobEntityInterface
{
    List<Phase> DEFAULT_ATTACK_SEQUENCE = List.of(new InactivePhase());

    List<Phase> getStrikeAttackSequence(@NotNull LivingEntity entityUsedToStrike);

    boolean canUseStrikeAttack();

    default void strike(Vec3 levelPos, @NotNull LivingEntity entityUsedToStrike, float explosionRadius, float bonusDamageAmount, float bonusDamageRange, float knockbackScale, boolean destroyBlocks)
    {
        Level.ExplosionInteraction interaction = destroyBlocks ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE;
        this.getLevel().explode(entityUsedToStrike, levelPos.x, levelPos.y, levelPos.z, explosionRadius, interaction);
        List<LivingEntity> hitEntities = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(entityUsedToStrike, bonusDamageRange,  (potentialTarget) -> !potentialTarget.is(this.getSelf()) && EntitySelector.withinDistance(entityUsedToStrike.position().x, entityUsedToStrike.position().y + entityUsedToStrike.getBbHeight() * 0.5F, entityUsedToStrike.position().z, bonusDamageRange).test(potentialTarget));
        this.damageEntities(hitEntities, bonusDamageAmount, knockbackScale, entityUsedToStrike);

        this.getLevel().broadcastEntityEvent(this.getSelf(), (byte) 68);
    }

    default boolean shouldTriggerStrikeAttack() {return this.canUseStrikeAttack();} //return false if you want the goal to only be active when manually triggered

    default List<Phase> getStrikeAttackSequenceInternal(@Nullable LivingEntity entityUsedToStrike)
    {
        if (entityUsedToStrike == null) {return this.getDefaultStrikeAttackSequence();}
        else {return this.getStrikeAttackSequence(entityUsedToStrike);}
    }

    default List<Phase> getDefaultStrikeAttackSequence() {return DEFAULT_ATTACK_SEQUENCE;}

    private void damageEntities(List<LivingEntity> entities, float amount, float knockbackScale, LivingEntity source)
    {
        if (!(this.getLevel() instanceof ServerLevel serverLevel)) {return;}
        for (Entity target : entities)
        {
            if (target instanceof LivingEntity living)
            {
                living.hurtServer(serverLevel, this.getSelf().damageSources().mobAttack(source), amount);

                if (knockbackScale != 0.0F)
                {
                    Vec3 knockback = target.position().subtract(source.position()).normalize().scale(knockbackScale);
                    target.push(knockback.x, 0.5, knockback.z);
                }
            }
        }
    }
}
