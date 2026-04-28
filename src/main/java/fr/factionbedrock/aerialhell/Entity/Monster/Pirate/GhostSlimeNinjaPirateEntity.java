package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhostPirateWaterAvoidingRandomStrollGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MisleadableEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.ShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GhostSlimeNinjaPirateEntity extends SlimeNinjaPirateEntity implements MisleadableEntity, GoalConditionEntity.PhaseAwareGoalConditionEntity
{
    private static final int OTHER = 0, MELEE_ATTACK_GOAL = 1, SHURIKEN_ATTACK_GOAL = 2;
    public GhostSlimeNinjaPirateEntity(EntityType<? extends GhostSlimeNinjaPirateEntity> type, Level world) {super(type, world);}

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isImmuneToGhostBlockCollision(livingEntity);
    }
    /* ------------------------------------------------------------------- */

    /* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
    {
        return this.misleadableHurtServer(serverLevel, source, amount, super::hurtServer);
    }
    /* ------------------------------------------------------------------------------------------ */

    /* ------- MisleadableEntity : Interface methods Overridden for specific behavior ------- */
    @Override public boolean canMisleaderHurt() {return false;}
    /* -------------------------------------------------------------------------------------- */

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathfinderMob getSelf() {return this;}

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
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new ConditionalGoal(this, MELEE_ATTACK_GOAL, new MeleeAttackGoal(this, 1.25D, false)));
        this.goalSelector.addGoal(4, new ConditionalGoal(this, OTHER, new LookAtPlayerGoal(this, Player.class, 16.0F)));
        this.goalSelector.addGoal(1, new ConditionalGoal(this, SHURIKEN_ATTACK_GOAL, new GhostShurikenAttackGoal(this)));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget)));
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand) {return new ItemStack(AerialHellItems.AZURITE_SHURIKEN.get());}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE.get();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get();}

    public static class GhostShurikenAttackGoal extends SlimeNinjaPirateEntity.ShurikenAttackGoal
    {
        public GhostShurikenAttackGoal(GhostSlimeNinjaPirateEntity entity) {super(entity);}

        @Override public GhostSlimeNinjaPirateEntity getParentEntity() {return (GhostSlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
        {
            ShurikenEntity shuriken = new ShurikenEntity(AerialHellEntities.AZURITE_SHURIKEN.get(), level, 9.0F);
            shuriken.setOwner(shuriken);
            shuriken.shoot(accX, accY, accZ, 1.3F, 2.0F);
            return shuriken;
        }
    }
}