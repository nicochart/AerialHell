package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.GhostGoals;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.AzuriteShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.entity.*;

public class GhostSlimeNinjaPirateEntity extends SlimeNinjaPirateEntity
{
    public GhostSlimeNinjaPirateEntity(EntityType<? extends GhostSlimeNinjaPirateEntity> type, World world) {super(type, world);}

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new GhostGoals.GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new GhostNinjaMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(4, new GhostGoals.GhostPirateLookAtPlayerGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.add(1, new GhostShurikenAttackGoal(this));
        this.targetSelector.add(2, new GhostGoals.GhostPirateNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public boolean damage(ServerWorld serverWorld, DamageSource damageSource, float amount)
    {
        Entity sourceEntity = damageSource.getAttacker();
        if (EntityHelper.isImmuneToGhostBlockCollision(sourceEntity) && !EntityHelper.isCreaOrSpecPlayer(sourceEntity)) {return false;}
        return super.damage(serverWorld, damageSource, amount);
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand) {return new ItemStack(AerialHellItems.AZURITE_SHURIKEN);}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_NINJA_PIRATE;}

    public static class GhostShurikenAttackGoal extends SlimeNinjaPirateEntity.ShurikenAttackGoal
    {
        public GhostShurikenAttackGoal(GhostSlimeNinjaPirateEntity entity) {super(entity);}

        @Override public GhostSlimeNinjaPirateEntity getParentEntity() {return (GhostSlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public boolean canStart()
        {
            LivingEntity target = getParentEntity().getTarget();
            return !EntityHelper.isImmuneToGhostBlockCollision(target) && super.canStart();
        }

        @Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
        {
            Random rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new AzuriteShurikenEntity(world, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f, AerialHellItems.AZURITE_SHURIKEN.getDefaultStack());
        }
    }

    public static class GhostNinjaMeleeAttackGoal extends GhostGoals.GhostPirateMeleeAttackGoal
    {
        public GhostNinjaMeleeAttackGoal(PathAwareEntity entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}

        @Override public boolean additionalConditionMet()
        {
            LivingEntity target = this.goalOwner.getTarget();
            if (target == null) {return false;}
            return super.additionalConditionMet() && this.goalOwner.distanceTo(target) < 3;
        }
    }
}
