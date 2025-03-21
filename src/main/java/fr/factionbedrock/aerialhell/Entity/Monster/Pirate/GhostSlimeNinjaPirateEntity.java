package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.GhostGoals;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.AzuriteShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GhostSlimeNinjaPirateEntity extends SlimeNinjaPirateEntity
{
    public GhostSlimeNinjaPirateEntity(EntityType<? extends GhostSlimeNinjaPirateEntity> type, Level world) {super(type, world);}

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new GhostGoals.GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new GhostNinjaMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new GhostGoals.GhostPirateLookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(1, new GhostShurikenAttackGoal(this));
        this.targetSelector.addGoal(2, new GhostGoals.GhostPirateNearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float amount)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (EntityHelper.isImmuneToGhostBlockCollision(sourceEntity) && !EntityHelper.isCreaOrSpecPlayer(sourceEntity)) {return false;}
        return super.hurtServer(serverLevel, damageSource, amount);
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand) {return new ItemStack(AerialHellItems.AZURITE_SHURIKEN.get());}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE.get();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get();}

    public static class GhostShurikenAttackGoal extends SlimeNinjaPirateEntity.ShurikenAttackGoal
    {
        public GhostShurikenAttackGoal(GhostSlimeNinjaPirateEntity entity) {super(entity);}

        @Override public GhostSlimeNinjaPirateEntity getParentEntity() {return (GhostSlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public boolean canUse()
        {
            LivingEntity target = getParentEntity().getTarget();
            return !EntityHelper.isImmuneToGhostBlockCollision(target) && super.canUse();
        }

        @Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
        {
            RandomSource rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new AzuriteShurikenEntity(level, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f, AerialHellItems.AZURITE_SHURIKEN.toStack());
        }
    }

    public static class GhostNinjaMeleeAttackGoal extends GhostGoals.GhostPirateMeleeAttackGoal
    {
        public GhostNinjaMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}

        @Override public boolean additionalConditionMet()
        {
            LivingEntity target = this.goalOwner.getTarget();
            if (target == null) {return false;}
            return super.additionalConditionMet() && this.goalOwner.distanceTo(target) < 3;
        }
    }
}
