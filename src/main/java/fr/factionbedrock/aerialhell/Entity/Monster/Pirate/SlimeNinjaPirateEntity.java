package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileFlurryGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
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
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SlimeNinjaPirateEntity extends AbstractSlimePirateEntity implements GoalConditionEntity.PhaseAwareGoalConditionEntity
{
    private int nextFurryShurikenNumber;
    private static final int MELEE_ATTACK_GOAL = 0, SHURIKEN_ATTACK_GOAL = 1;
    public SlimeNinjaPirateEntity(EntityType<? extends SlimeNinjaPirateEntity> type, World world) {super(type, world);}

    public int getNextFurryShurikenNumber() {return nextFurryShurikenNumber;}
    public void resetNextFurryShurikenNumber() {this.nextFurryShurikenNumber = this.random.nextBetweenExclusive(3,5);}

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathAwareEntity getSelf() {return this;}

    @Override public boolean canUseGoalsAdditionalCondition(int goalIndex)
    {
        LivingEntity target = this.getTarget();
        if (target == null) {return false;}
        double distanceToTarget = this.distanceTo(target);
        return (goalIndex == MELEE_ATTACK_GOAL && distanceToTarget < 3) || (goalIndex == SHURIKEN_ATTACK_GOAL && distanceToTarget > 2);
    }
    /* --------------------------------------------------------------------------- */

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(1, new ConditionalGoal(this, SHURIKEN_ATTACK_GOAL, new ShurikenAttackGoal(this)));
        this.goalSelector.add(2, new ConditionalGoal(this, MELEE_ATTACK_GOAL, new MeleeAttackGoal(this, 1.25D, false)));
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

    public static class ShurikenAttackGoal extends ShootProjectileFlurryGoal
    {
        public ShurikenAttackGoal(SlimeNinjaPirateEntity entity) {super(entity, true);}

        @Override public SlimeNinjaPirateEntity getParentEntity() {return (SlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
        {
            Random rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new RubyShurikenEntity(world, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f, AerialHellItems.RUBY_SHURIKEN.getDefaultStack());
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
}
