package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GhastLikeGoals
{
    /* Same as net.minecraft.world.entity.monster.Ghast.GhastLookGoal but changed GhastEntity to Mob */
    public static class LookAroundGoal extends Goal
    {
        private final Mob parentEntity;
        public LookAroundGoal(Mob flyingMob) {this.parentEntity = flyingMob; this.setFlags(EnumSet.of(Goal.Flag.LOOK));}

        @Override public boolean canUse() {return true;}

        @Override public void tick()
        {
            if (this.parentEntity.getTarget() == null)
            {
                Vec3 vec = this.parentEntity.getDeltaMovement();
                this.parentEntity.setYRot(-((float) MathHelper.atan2(vec.x, vec.z)) * (180F / (float)Math.PI));
                this.parentEntity.yBodyRot = this.parentEntity.getYaw();
            }
            else
            {
                LivingEntity livingentity = this.parentEntity.getTarget();
                if (livingentity.distanceToSqr(this.parentEntity) < 64*64)
                {
                    double x = livingentity.getX() - this.parentEntity.getX();
                    double z = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.setYRot(-((float)MathHelper.atan2(x, z)) * (180F / (float)Math.PI));
                    this.parentEntity.yBodyRot = this.parentEntity.getYaw();
                }
            }
        }
    }

    /* Same as net.minecraft.world.entity.monster.Ghast.RandomFloatAroundGoal but changed Ghast to Mob */
    public static class RandomFlyGoal extends Goal
    {
        private final Mob parentEntity;
        public RandomFlyGoal(Mob flyingMob) {this.parentEntity = flyingMob; this.setFlags(EnumSet.of(Goal.Flag.MOVE));}

        public Mob getParentEntity() {return parentEntity;}

        @Override public boolean canUse()
        {
            MoveControl movementcontroller = this.parentEntity.getMoveControl();
            if (!movementcontroller.hasWanted()) {return true;}
            else
            {
                double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
                double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
                double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0 || d3 > 3600.0;
            }
        }
        @Override public boolean canContinueToUse() {return false;}
        @Override public void start()
        {
            RandomSource random = this.parentEntity.getRandom();
            double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
        }
    }

    /* Same as net.minecraft.world.entity.monster.Ghast.GhastMoveControl but changed Ghast to Mob */
    public static class MoveHelperController extends MoveControl
    {
        private final Mob parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(Mob flyingMob) {super(flyingMob); this.parentEntity = flyingMob;}

        @Override public void tick()
        {
            if (this.operation == MoveControl.Operation.MOVE_TO)
            {
                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
                    Vec3 vec = new Vec3(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                    double d0 = vec.length();
                    vec = vec.normalize();
                    if (this.canReach(vec, MathHelper.ceil(d0))) {this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec.scale(0.1)));}
                    else {this.operation = MoveControl.Operation.WAIT;}
                }
            }
        }

        private boolean canReach(Vec3 pos, int distance)
        {
            AABB boundingBox = this.parentEntity.getBoundingBox();

            for (int i = 1; i < distance; ++i)
            {
                boundingBox = boundingBox.move(pos);
                if (!this.parentEntity.level().noCollision(this.parentEntity, boundingBox)) {return false;}
            }
            return true;
        }
    }

    /* Same as net.minecraft.world.entity.monster.Ghast.GhastShootFireballGoal but changed Ghast to Mob, and edited a bit to generalize */
    public abstract static class ShootProjectileGoal extends Goal
    {
        private final Mob parentEntity;
        public int shootTimer;

        protected final boolean imitateSkeletonBowAttackMovement;
        private final double speedModifier = 1.0F;
        private final float attackRadiusSqr = 16F*16F;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public ShootProjectileGoal(Mob mob) {this(mob, false);}

        public ShootProjectileGoal(Mob mob, boolean affectMovements) {this.parentEntity = mob; imitateSkeletonBowAttackMovement = affectMovements;}

        public Mob getParentEntity() {return parentEntity;}

        @Override public boolean canUse() {return parentEntity.getTarget() != null;}
        @Override public void start() {resetTask();}
        @Override public void stop() {resetTask(); this.setAttacking(false);}

        @Override public void tick()
        {
            LivingEntity target = parentEntity.getTarget();
            if (target.distanceToSqr(this.parentEntity) < 64*64 && this.parentEntity.hasLineOfSight(target))
            {
                ++this.shootTimer;

                if (tryShooting(target)) {this.resetTask();}
            }
            else if (this.doesShootTimeDecreaseWhenTargetOutOfSight() && this.shootTimer > - this.getShootTimeInterval()) {this.shootTimer--;}

            this.setAttacking(shootTimer > 0);

            if (this.imitateSkeletonBowAttackMovement) {this.imitateBowAttackMovementTick();}
        }

        public void imitateBowAttackMovementTick()
        {
            //copy of net.minecraft.world.entity.ai.goal.RangedBowAttackGoal tick method part modifying skeleton navigation
            LivingEntity target = this.getParentEntity().getTarget();
            if (target != null)
            {
                double distanceToTarget = this.getParentEntity().distanceToSqr(target.getX(), target.getY(), target.getZ());
                boolean hasLineOfSight = this.getParentEntity().getSensing().hasLineOfSight(target);
                boolean seeingTarget = this.seeTime > 0;
                if (hasLineOfSight != seeingTarget) {this.seeTime = 0;}

                if (hasLineOfSight) {++this.seeTime;}
                else {--this.seeTime;}

                if (!(distanceToTarget > (double)this.attackRadiusSqr) && this.seeTime >= 20)
                {
                    this.getParentEntity().getNavigation().stop();
                    ++this.strafingTime;
                }
                else
                {
                    this.getParentEntity().getNavigation().moveTo(target, this.speedModifier);
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
                    if (distanceToTarget > (double)(this.attackRadiusSqr * 0.75F)) {this.strafingBackwards = false;}
                    else if (distanceToTarget < (double)(this.attackRadiusSqr * 0.25F)) {this.strafingBackwards = true;}

                    this.getParentEntity().getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    Entity entity = this.getParentEntity().getControlledVehicle();
                    if (entity instanceof Mob mob) {mob.lookAt(target, 30.0F, 30.0F);}

                    this.getParentEntity().lookAt(target, 30.0F, 30.0F);
                }
                else {this.getParentEntity().getLookControl().setLookAt(target, 30.0F, 30.0F);}
            }
        }

        protected void resetTask() {this.shootTimer = - this.getShootTimeInterval();} //restart

        protected boolean tryPlayingShootSound() //returns true if the playsound is a success
        {
            if (this.getShootSound() != null) //if it's time to play shoot sound
            {
                this.parentEntity.playSound(this.getShootSound(), 3.0F, (this.parentEntity.level().random.nextFloat() - this.parentEntity.level().random.nextFloat()) * 0.2F + 1.0F);
                return true;
            }
            return false;
        }

        protected boolean tryShooting(LivingEntity target) //returns true if the shoot is a success
        {
            if (this.shootTimer >= this.getShootDelay()) //if it's time to actually shoot. (== is exact time to shoot the first projectile)
            {
                this.shootWithSound(target);
                return true;
            }
            return false;
        }

        protected void shootWithSound(LivingEntity target)
        {
            this.parentEntity.level().spawnEntity(createProjectile(target));
            this.tryPlayingShootSound();
        }

        public Projectile createProjectile(LivingEntity target)
        {
            double Xdistance = target.getX() - (this.parentEntity.getX());
            double Ydistance = target.getY(0.5)  -  this.parentEntity.getY(0.5);
            double Zdistance = target.getZ() - (this.parentEntity.getZ());
            Projectile projectile = this.createProjectile(this.parentEntity.level(), this.parentEntity, Xdistance, Ydistance, Zdistance);
            projectile.setPos(this.parentEntity.getX(), this.parentEntity.getY(0.5) + this.getYProjectileOffset(), this.parentEntity.getZ());
            return projectile;
        }

        public abstract double getYProjectileOffset();
        public abstract int getShootDelay(); //(tick) actual moment for the projectile to spawn. The sound is played at 0 and projectile sent at this time
        public abstract int getShootTimeInterval(); //time gap between two shots
        public abstract boolean doesShootTimeDecreaseWhenTargetOutOfSight(); //usually true if shoot delay != 0, can be false if shoot delay == 0
        public abstract Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ);
        protected abstract void setAttacking(boolean bool);
        @Nullable public abstract SoundEvent getShootSound();
    }

    /* Same as previous one (ShootProjectileGoal) but shoots a flurry of projectile, like a blaze, instead of only one projectile */
    public abstract static class ShootProjectileFlurryGoal extends ShootProjectileGoal
    {
        private int shotProjectileCount;
        public ShootProjectileFlurryGoal(Mob mob) {super(mob);}
        public ShootProjectileFlurryGoal(Mob mob, boolean affectMovements) {super(mob, affectMovements);}

        @Override public void start() {super.start(); this.shotProjectileCount = 0;}
        @Override public void stop() {super.stop(); this.shotProjectileCount = 0;}

        @Override protected void resetTask() {super.resetTask(); this.shotProjectileCount = 0;}

        @Override protected boolean tryShooting(LivingEntity target) //returns true if all the projectiles have been shot
        {
            boolean isNotFirstProjectileToBeShot = (this.shotProjectileCount > 0);
            boolean shouldShootAnotherProjectileNow = isNotFirstProjectileToBeShot && this.shootTimer > this.getShootDelay() && this.shootTimer - this.shotProjectileCount * getShootInvervalWithinBurst() >= this.getShootDelay();
            if (this.shootTimer == this.getShootDelay() || shouldShootAnotherProjectileNow) //if it's time to actually shoot. (== is exact time to shoot the first projectile)
            {
                this.shootWithSound(target);
                return ++this.shotProjectileCount >= getProjectileNumber();
            }
            return false;
        }

        public abstract int getProjectileNumber(); //number of projectiles in one flurry
        public abstract int getShootInvervalWithinBurst(); //time gap between two projectiles of the same flurry
    }
}
