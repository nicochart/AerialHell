package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhostPirateWaterAvoidingRandomStrollGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MisleadableEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.AzuriteShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GhostSlimeNinjaPirateEntity extends SlimeNinjaPirateEntity implements MisleadableEntity, GoalConditionEntity.PhaseAwareGoalConditionEntity
{
    private static final int OTHER = 0, MELEE_ATTACK_GOAL = 1, SHURIKEN_ATTACK_GOAL = 2;
    public GhostSlimeNinjaPirateEntity(EntityType<? extends GhostSlimeNinjaPirateEntity> type, World world) {super(type, world);}

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isImmuneToGhostBlockCollision(livingEntity);
    }
    /* ------------------------------------------------------------------- */

    /* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
    @Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        return this.misleadableDamage(serverWorld, source, amount, super::damage);
    }
    /* ------------------------------------------------------------------------------------------ */

    /* ------- MisleadableEntity : Interface methods Overridden for specific behavior ------- */
    @Override public boolean canMisleaderDamage() {return false;}
    /* -------------------------------------------------------------------------------------- */

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathAwareEntity getSelf() {return this;}

    @Override public boolean canUseGoalsAdditionalCondition(int goalIndex)
    {
        LivingEntity target = this.getTarget();
        if (target == null || EntityHelper.isImmuneToGhostBlockCollision(target)) {return false;}
        if (goalIndex == OTHER) {return true;}
        double distanceToTarget = this.distanceTo(target);
        return (goalIndex == MELEE_ATTACK_GOAL && distanceToTarget < 3) || (goalIndex == SHURIKEN_ATTACK_GOAL && distanceToTarget > 2);
    }
    /* --------------------------------------------------------------------------- */

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new ConditionalGoal(this, MELEE_ATTACK_GOAL, new MeleeAttackGoal(this, 1.25D, false)));
        this.goalSelector.add(4, new ConditionalGoal(this, OTHER, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F)));
        this.goalSelector.add(1, new ConditionalGoal(this, SHURIKEN_ATTACK_GOAL, new GhostShurikenAttackGoal(this)));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true, (potentialTarget, serverWorld) -> !this.isMisleadedBy(potentialTarget)));
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand) {return new ItemStack(AerialHellItems.AZURITE_SHURIKEN);}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_NINJA_PIRATE;}

    public static class GhostShurikenAttackGoal extends SlimeNinjaPirateEntity.ShurikenAttackGoal
    {
        public GhostShurikenAttackGoal(GhostSlimeNinjaPirateEntity entity) {super(entity);}

        @Override public GhostSlimeNinjaPirateEntity getParentEntity() {return (GhostSlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
        {
            Random rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new AzuriteShurikenEntity(world, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f, AerialHellItems.AZURITE_SHURIKEN.getDefaultStack());
        }
    }
}
