package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLikeGoals;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.RubyShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlimeNinjaPirateEntity extends AbstractSlimePirateEntity
{
    private int nextFurryShurikenNumber;
    public SlimeNinjaPirateEntity(EntityType<? extends SlimeNinjaPirateEntity> type, Level world) {super(type, world);}

    public int getNextFurryShurikenNumber() {return nextFurryShurikenNumber;}
    public void resetNextFurryShurikenNumber() {this.nextFurryShurikenNumber = this.random.nextInt(3,5);}

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(1, new ShurikenAttackGoal(this));
        this.goalSelector.addGoal(2, new NinjaMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override protected ItemStack getRandomWeapon(RandomSource rand) {return new ItemStack(AerialHellBlocksAndItems.RUBY_SHURIKEN.get());}

    @Override protected EntityType<? extends AbstractSlimePirateEntity> getLittlePirateType() {return AerialHellEntities.SLIME_PIRATE.get();}

    public static class ShurikenAttackGoal extends GhastLikeGoals.ShootProjectileFlurryGoal
    {
        private final double speedModifier = 1.0F;
        private final float attackRadiusSqr = 16F*16F;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public ShurikenAttackGoal(SlimeNinjaPirateEntity entity) {super(entity);}

        @Override public SlimeNinjaPirateEntity getParentEntity() {return (SlimeNinjaPirateEntity) super.getParentEntity();}

        @Override public void tick()
        {
            super.tick();
            //copy of net.minecraft.world.entity.ai.goal.RangedBowAttackGoal tick method part modifying skeleton navigation
            LivingEntity livingentity = this.getParentEntity().getTarget();
            if (livingentity != null)
            {
                double d0 = this.getParentEntity().distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                boolean flag = this.getParentEntity().getSensing().hasLineOfSight(livingentity);
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) {this.seeTime = 0;}

                if (flag) {++this.seeTime;}
                else {--this.seeTime;}

                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20)
                {
                    this.getParentEntity().getNavigation().stop();
                    ++this.strafingTime;
                }
                else
                {
                    this.getParentEntity().getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20)
                {
                    if ((double)this.getParentEntity().getRandom().nextFloat() < 0.3D) {this.strafingClockwise = !this.strafingClockwise;}
                    if ((double)this.getParentEntity().getRandom().nextFloat() < 0.3D) {this.strafingBackwards = !this.strafingBackwards;}
                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1)
                {
                    if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {this.strafingBackwards = false;}
                    else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {this.strafingBackwards = true;}

                    this.getParentEntity().getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    Entity entity = this.getParentEntity().getControlledVehicle();
                    if (entity instanceof Mob)
                    {
                        Mob mob = (Mob)entity;
                        mob.lookAt(livingentity, 30.0F, 30.0F);
                    }

                    this.getParentEntity().lookAt(livingentity, 30.0F, 30.0F);
                }
                else {this.getParentEntity().getLookControl().setLookAt(livingentity, 30.0F, 30.0F);}
            }
        }

        @Override public boolean canUse()
        {
            LivingEntity target = getParentEntity().getTarget();
            double distanceToTarget = 0; if (target != null) {distanceToTarget = getParentEntity().distanceTo(target);}
            return target != null && target.isAlive() && getParentEntity().canAttack(target) && distanceToTarget > 2;
        }

        @Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
        {
            RandomSource rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
            return new RubyShurikenEntity(level, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget, 1.3f, 0.0f);
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

    public static class NinjaMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public NinjaMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}

        @Override public boolean additionalConditionMet()
        {
            LivingEntity target = this.goalOwner.getTarget();
            if (target == null) {return false;}
            return this.goalOwner.distanceTo(target) < 3;
        }
    }
}
