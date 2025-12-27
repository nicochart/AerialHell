package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class GhastLikeGoals
{
    /* Same as net.minecraft.entity.mob.GhastEntity.LookAtTargetGoal but changed GhastEntity to MobEntity */
    public static class LookAroundGoal extends Goal
    {
        private final MobEntity parentEntity;
        public LookAroundGoal(MobEntity flyingMobEntity) {this.parentEntity = flyingMobEntity; this.setControls(EnumSet.of(Goal.Control.LOOK));}

        @Override public boolean canStart() {return true;}

        @Override public void tick()
        {
            if (this.parentEntity.getTarget() == null)
            {
                Vec3d vec = this.parentEntity.getVelocity();
                this.parentEntity.setYaw(-((float) MathHelper.atan2(vec.x, vec.z)) * (180F / (float)Math.PI));
                this.parentEntity.bodyYaw = this.parentEntity.getYaw();
            }
            else
            {
                LivingEntity livingentity = this.parentEntity.getTarget();
                if (livingentity.squaredDistanceTo(this.parentEntity) < 64*64)
                {
                    double x = livingentity.getX() - this.parentEntity.getX();
                    double z = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.setYaw(-((float)MathHelper.atan2(x, z)) * (180F / (float)Math.PI));
                    this.parentEntity.bodyYaw = this.parentEntity.getYaw();
                }
            }
        }
    }

    /* Same as net.minecraft.entity.mob.GhastEntity.FlyRandomlyGoal but changed GhastEntity to MobEntity */
    public static class RandomFlyGoal extends Goal
    {
        private final MobEntity parentEntity;
        public RandomFlyGoal(MobEntity flyingMobEntity) {this.parentEntity = flyingMobEntity; this.setControls(EnumSet.of(Goal.Control.MOVE));}

        public MobEntity getParentEntity() {return parentEntity;}

        @Override public boolean canStart()
        {
            MoveControl movementcontroller = this.parentEntity.getMoveControl();
            if (!movementcontroller.isMoving()) {return true;}
            else
            {
                double d0 = movementcontroller.getTargetX() - this.parentEntity.getX();
                double d1 = movementcontroller.getTargetY() - this.parentEntity.getY();
                double d2 = movementcontroller.getTargetZ() - this.parentEntity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0 || d3 > 3600.0;
            }
        }
        @Override public boolean shouldContinue() {return false;}
        @Override public void start()
        {
            Random random = this.parentEntity.getRandom();
            double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.parentEntity.getMoveControl().moveTo(d0, d1, d2, 1.0);
        }
    }

    /* Same as net.minecraft.entity.mob.GhastEntity.GhastMoveControl but changed Ghast to MobEntity */
    public static class MoveHelperController extends MoveControl
    {
        private final MobEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(MobEntity flyingMobEntity) {super(flyingMobEntity); this.parentEntity = flyingMobEntity;}

        @Override public void tick()
        {
            if (this.state == MoveControl.State.MOVE_TO)
            {
                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown = this.courseChangeCooldown + this.parentEntity.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.parentEntity.getX(), this.targetY - this.parentEntity.getY(), this.targetZ - this.parentEntity.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {this.parentEntity.setVelocity(this.parentEntity.getVelocity().add(vec3d.multiply(0.1)));}
                    else {this.state = MoveControl.State.WAIT;}
                }
            }
        }

        private boolean willCollide(Vec3d direction, int steps)
        {
            Box box = this.parentEntity.getBoundingBox();
            for (int i = 1; i < steps; i++)
            {
                box = box.offset(direction);
                if (!this.parentEntity.getEntityWorld().isSpaceEmpty(this.parentEntity, box)) {return false;}
            }
            return true;
        }
    }

    /* Same as net.minecraft.entity.mob.GhastEntity.ShootFireballGoal but changed Ghast to MobEntity, and edited a bit to generalize */
    public abstract static class ShootProjectileGoal extends Goal
    {
        private final MobEntity parentEntity;
        public int shootTimer;

        protected final boolean imitateSkeletonBowAttackMovement;
        private final double speedModifier = 1.0F;
        private final float attackRadiusSqr = 16F*16F;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public ShootProjectileGoal(MobEntity mob) {this(mob, false);}

        public ShootProjectileGoal(MobEntity mob, boolean affectMovements) {this.parentEntity = mob; imitateSkeletonBowAttackMovement = affectMovements;}

        public MobEntity getParentEntity() {return parentEntity;}

        @Override public boolean canStart() {return parentEntity.getTarget() != null;}
        @Override public void start() {resetTask();}
        @Override public void stop() {resetTask(); this.setAttacking(false);}

        @Override public void tick()
        {
            LivingEntity target = parentEntity.getTarget();
            if (target.squaredDistanceTo(this.parentEntity) < 64*64 && this.parentEntity.canSee(target))
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
            //copy of net.minecraft.entity.ai.goal.BowAttackGoal tick method part modifying skeleton navigation
            LivingEntity target = this.getParentEntity().getTarget();
            if (target != null)
            {
                double distanceToTarget = this.getParentEntity().squaredDistanceTo(target.getX(), target.getY(), target.getZ());
                boolean hasLineOfSight = this.getParentEntity().getVisibilityCache().canSee(target);
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
                    this.getParentEntity().getNavigation().startMovingTo(target, this.speedModifier);
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

                    this.getParentEntity().getMoveControl().strafeTo(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    Entity entity = this.getParentEntity().getControllingVehicle();
                    if (entity instanceof MobEntity mob) {mob.lookAtEntity(target, 30.0F, 30.0F);}

                    this.getParentEntity().lookAtEntity(target, 30.0F, 30.0F);
                }
                else {this.getParentEntity().getLookControl().lookAt(target, 30.0F, 30.0F);}
            }
        }

        protected void resetTask() {this.shootTimer = - this.getShootTimeInterval();} //restart

        protected boolean tryPlayingShootSound() //returns true if the playsound is a success
        {
            if (this.getShootSound() != null) //if it's time to play shoot sound
            {
                this.parentEntity.playSound(this.getShootSound(), 3.0F, (this.parentEntity.getEntityWorld().random.nextFloat() - this.parentEntity.getEntityWorld().random.nextFloat()) * 0.2F + 1.0F);
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
            this.parentEntity.getEntityWorld().spawnEntity(createProjectile(target));
            this.tryPlayingShootSound();
        }

        public ProjectileEntity createProjectile(LivingEntity target)
        {
            double Xdistance = target.getX() - (this.parentEntity.getX());
            double Ydistance = target.getBodyY(0.5)  -  this.parentEntity.getBodyY(0.5);
            double Zdistance = target.getZ() - (this.parentEntity.getZ());
            ProjectileEntity projectile = this.createProjectile(this.parentEntity.getEntityWorld(), this.parentEntity, Xdistance, Ydistance, Zdistance);
            projectile.setPos(this.parentEntity.getX(), this.parentEntity.getBodyY(0.5) + this.getYProjectileOffset(), this.parentEntity.getZ());
            return projectile;
        }

        public abstract double getYProjectileOffset();
        public abstract int getShootDelay(); //(tick) actual moment for the projectile to spawn. The sound is played at 0 and projectile sent at this time
        public abstract int getShootTimeInterval(); //time gap between two shots
        public abstract boolean doesShootTimeDecreaseWhenTargetOutOfSight(); //usually true if shoot delay != 0, can be false if shoot delay == 0
        public abstract ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ);
        protected abstract void setAttacking(boolean bool);
        @Nullable public abstract SoundEvent getShootSound();
    }

    /* Same as previous one (ShootProjectileGoal) but shoots a flurry of projectile, like a blaze, instead of only one projectile */
    public abstract static class ShootProjectileFlurryGoal extends ShootProjectileGoal
    {
        private int shotProjectileEntityCount;
        public ShootProjectileFlurryGoal(MobEntity mob) {super(mob);}
        public ShootProjectileFlurryGoal(MobEntity mob, boolean affectMovements) {super(mob, affectMovements);}

        @Override public void start() {super.start(); this.shotProjectileEntityCount = 0;}
        @Override public void stop() {super.stop(); this.shotProjectileEntityCount = 0;}

        @Override protected void resetTask() {super.resetTask(); this.shotProjectileEntityCount = 0;}

        @Override protected boolean tryShooting(LivingEntity target) //returns true if all the projectiles have been shot
        {
            boolean isNotFirstProjectileEntityToBeShot = (this.shotProjectileEntityCount > 0);
            boolean shouldShootAnotherProjectileEntityNow = isNotFirstProjectileEntityToBeShot && this.shootTimer > this.getShootDelay() && this.shootTimer - this.shotProjectileEntityCount * getShootInvervalWithinBurst() >= this.getShootDelay();
            if (this.shootTimer == this.getShootDelay() || shouldShootAnotherProjectileEntityNow) //if it's time to actually shoot. (== is exact time to shoot the first projectile)
            {
                this.shootWithSound(target);
                return ++this.shotProjectileEntityCount >= getProjectileNumber();
            }
            return false;
        }

        public abstract int getProjectileNumber(); //number of projectiles in one flurry
        public abstract int getShootInvervalWithinBurst(); //time gap between two projectiles of the same flurry
    }
}
