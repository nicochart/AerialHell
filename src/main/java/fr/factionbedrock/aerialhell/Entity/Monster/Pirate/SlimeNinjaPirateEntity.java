package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLikeGoals;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.RubyShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SlimeNinjaPirateEntity extends AbstractSlimePirateEntity
{
    private int nextFurryShurikenNumber;
    public SlimeNinjaPirateEntity(EntityType<? extends SlimeNinjaPirateEntity> type, World world) {super(type, world);}

    public int getNextFurryShurikenNumber() {return nextFurryShurikenNumber;}
    public void resetNextFurryShurikenNumber() {this.nextFurryShurikenNumber = this.random.nextBetweenExclusive(3,5);}

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(1, new ShurikenAttackGoal(this));
        this.goalSelector.add(2, new NinjaMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand) {return new ItemStack(AerialHellItems.RUBY_SHURIKEN);}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.SLIME_NINJA_PIRATE;}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(18.0D, 4.0D, 0.25D, 35.0D);
    }

    public static class ShurikenAttackGoal extends GhastLikeGoals.ShootProjectileFlurryGoal
    {
        public ShurikenAttackGoal(SlimeNinjaPirateEntity entity) {super(entity, true);}

        @Override public SlimeNinjaPirateEntity getParentEntity() {return (SlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public boolean canStart()
        {
            LivingEntity target = getParentEntity().getTarget();
            double distanceToTarget = 0; if (target != null) {distanceToTarget = getParentEntity().distanceTo(target);}
            return target != null && target.isAlive() && getParentEntity().canTarget(target) && distanceToTarget > 2;
        }

        @Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
        {
            Random rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new RubyShurikenEntity(world, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f);
        }

        @Override protected void resetTask() {super.resetTask(); this.getParentEntity().resetNextFurryShurikenNumber();}

        @Override public int getShootTimeInterval() {return 20;}
        @Override public int getShootDelay() {return 0;}
        @Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
        @Override public double getYProjectileOffset() {return 0.5D;}
        @Override protected void setAttacking(boolean bool) {}
        @Override public SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT;}
        @Override public int getProjectileNumber() {return this.getParentEntity().getNextFurryShurikenNumber();}
        @Override public int getShootInvervalWithinBurst() {return 4;}
    }

    public static class NinjaMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public NinjaMeleeAttackGoal(PathAwareEntity entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}

        @Override public boolean additionalConditionMet()
        {
            LivingEntity target = this.goalOwner.getTarget();
            if (target == null) {return false;}
            return this.goalOwner.distanceTo(target) < 3;
        }
    }
}
