package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalCondition.GhastLike.AdditionalConditionShootProjectileFlurryGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.RubyShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlimeNinjaPirateEntity extends AbstractSlimePirateEntity implements GoalConditionEntity.PhaseAwareGoalConditionEntity
{
    private int nextFurryShurikenNumber;
    private static final int MELEE_ATTACK_GOAL = 0, SHURIKEN_ATTACK_GOAL = 1;
    public SlimeNinjaPirateEntity(EntityType<? extends SlimeNinjaPirateEntity> type, Level world) {super(type, world);}

    public int getNextFurryShurikenNumber() {return nextFurryShurikenNumber;}
    public void resetNextFurryShurikenNumber() {this.nextFurryShurikenNumber = this.random.nextInt(3,5);}

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathfinderMob getSelf() {return this;}

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
        this.goalSelector.addGoal(1, new ShurikenAttackGoal(this, SHURIKEN_ATTACK_GOAL));
        this.goalSelector.addGoal(2, new AdditionalConditionMeleeAttackGoal(this, 1.25D, false, MELEE_ATTACK_GOAL));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand) {return new ItemStack(AerialHellItems.RUBY_SHURIKEN.get());}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.SLIME_PIRATE.get();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.SLIME_NINJA_PIRATE.get();}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(18.0D, 4.0D, 0.25D, 35.0D);
    }

    public static class ShurikenAttackGoal extends AdditionalConditionShootProjectileFlurryGoal
    {
        public ShurikenAttackGoal(SlimeNinjaPirateEntity entity, int goalPhase) {super(entity, true, goalPhase);}

        @Override public SlimeNinjaPirateEntity getParentEntity() {return (SlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
        {
            RandomSource rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new RubyShurikenEntity(level, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f, AerialHellItems.RUBY_SHURIKEN.toStack());
        }

        @Override protected void resetTask() {super.resetTask(); this.getParentEntity().resetNextFurryShurikenNumber();}

        @Override public int getShootTimeInterval() {return 20;}
        @Override public int getShootDelay() {return 0;}
        @Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
        @Override public double getYProjectileOffset() {return 0.5D;}
        @Override protected void setAttacking(boolean bool) {}
        @Override public SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT.get();}
        @Override public int getProjectileNumber() {return this.getParentEntity().getNextFurryShurikenNumber();}
        @Override public int getShootInvervalWithinBurst() {return 4;}
    }
}
