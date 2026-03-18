package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/* Same as net.minecraft.world.entity.monster.Ghast.GhastShootFireballGoal but changed Ghast to Mob, and edited a bit to generalize */
public abstract class ShootProjectileGoal extends Goal
{
    private final MobEntity parentEntity;
    public int shootTimer;

    protected final boolean imitateSkeletonBowAttackMovement;
    private final double speedModifier = 1.0F;
    private final float attackRadiusSqr = 16F * 16F;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public ShootProjectileGoal(MobEntity mob) {this(mob, false);}

    public ShootProjectileGoal(MobEntity mob, boolean affectMovements)
    {
        this.parentEntity = mob;
        imitateSkeletonBowAttackMovement = affectMovements;
    }

    public MobEntity getParentEntity() {return parentEntity;}

    @Override public boolean canStart() {return this.isValidTarget(this.parentEntity.getTarget());}

    @Override public void start() {resetTask();}

    @Override public void stop()
    {
        resetTask();
        this.setAttacking(false);
    }

    public boolean isValidTarget(@Nullable LivingEntity target)
    {
        return target != null && this.getParentEntity().canTarget(target);
    }

    @Override public void tick()
    {
        LivingEntity target = parentEntity.getTarget();
        if (target.squaredDistanceTo(this.parentEntity) < 64 * 64 && this.parentEntity.canSee(target)) {
            ++this.shootTimer;

            if (tryShooting(target)) {this.resetTask();}
        }
        else if (this.doesShootTimeDecreaseWhenTargetOutOfSight() && this.shootTimer > -this.getShootTimeInterval()) {this.shootTimer--;}

        this.setAttacking(shootTimer > 0);

        if (this.imitateSkeletonBowAttackMovement) {this.imitateBowAttackMovementTick();}
    }

    public void imitateBowAttackMovementTick()
    {
        //copy of net.minecraft.world.entity.ai.goal.RangedBowAttackGoal tick method part modifying skeleton navigation
        LivingEntity target = this.getParentEntity().getTarget();
        if (target != null)
        {
            double distanceToTarget = this.getParentEntity().squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            boolean canSee = this.getParentEntity().getVisibilityCache().canSee(target);
            boolean seeingTarget = this.seeTime > 0;
            if (canSee != seeingTarget) {this.seeTime = 0;}

            if (canSee) {++this.seeTime;}
            else {--this.seeTime;}

            if (!(distanceToTarget > (double) this.attackRadiusSqr) && this.seeTime >= 20)
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
                if ((double) this.getParentEntity().getRandom().nextFloat() < 0.3D) {this.strafingClockwise = !this.strafingClockwise;}
                if ((double) this.getParentEntity().getRandom().nextFloat() < 0.3D) {this.strafingBackwards = !this.strafingBackwards;}
                this.strafingTime = 0;
            }

            if (this.strafingTime > -1)
            {
                if (distanceToTarget > (double) (this.attackRadiusSqr * 0.75F)) {this.strafingBackwards = false;}
                else if (distanceToTarget < (double) (this.attackRadiusSqr * 0.25F)) {this.strafingBackwards = true;}

                this.getParentEntity().getMoveControl().strafeTo(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                Entity entity = this.getParentEntity().getControllingVehicle();
                if (entity instanceof MobEntity mob) {mob.lookAtEntity(target, 30.0F, 30.0F);}

                this.getParentEntity().lookAtEntity(target, 30.0F, 30.0F);
            }
            else
            {
                this.getParentEntity().getLookControl().lookAt(target, 30.0F, 30.0F);
            }
        }
    }

    protected void resetTask() {
        this.shootTimer = -this.getShootTimeInterval();
    } //restart

    protected boolean tryPlayingShootSound() //returns true if the playsound is a success
    {
        if (this.getShootSound() != null) //if it's time to play shoot sound
        {
            this.parentEntity.playSound(this.getShootSound(), 3.0F, (this.parentEntity.getEntityWorld().random.nextFloat() - this.parentEntity.getEntityWorld().random.nextFloat()) * 0.2F + 1.0F);
            return true;
        }
        return false;
    }

    protected boolean tryShootingAll(@Nullable LivingEntity realTarget) {return this.tryShootingAll(realTarget, null);}

    protected boolean tryShootingAll(@Nullable LivingEntity realTarget, @Nullable Predicate<Entity> targetPredicate)
    {
        if (this.shootTimer >= this.getShootDelay()) //if it's time to actually shoot. (== is exact time to shoot the first projectile)
        {
            this.shootAll(realTarget, targetPredicate);
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

    protected void shootAll(@Nullable LivingEntity realTarget, @Nullable Predicate<Entity> targetPredicate)
    {
        Predicate<Entity> predicate = targetPredicate != null ? targetPredicate : this.getDefaultTargetPredicate();
        boolean needToShotTarget = realTarget != null;
        List<LivingEntity> targetList = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(this.parentEntity, 15, predicate);
        for (LivingEntity target : targetList)
        {
            this.shootSilent(target);
            if (needToShotTarget && target.isPartOf(realTarget)) {needToShotTarget = false;}
        }
        if (needToShotTarget) {this.shootSilent(realTarget);}
        this.tryPlayingShootSound();
    }

    protected void shootWithSound(LivingEntity target)
    {
        this.shootSilent(target);
        this.tryPlayingShootSound();
    }

    protected void shootSilent(LivingEntity target)
    {
        this.parentEntity.getEntityWorld().spawnEntity(createProjectile(target));
    }

    public ProjectileEntity createProjectile(LivingEntity target)
    {
        double Xdistance = target.getX() - (this.parentEntity.getX());
        double Ydistance = target.getBodyY(0.5) - this.parentEntity.getBodyY(0.5);
        double Zdistance = target.getZ() - (this.parentEntity.getZ());
        ProjectileEntity projectile = this.createProjectile(this.parentEntity.getEntityWorld(), this.parentEntity, Xdistance, Ydistance, Zdistance);
        projectile.setPos(this.parentEntity.getX(), this.parentEntity.getBodyY(0.5) + this.getYProjectileOffset(), this.parentEntity.getZ());
        return projectile;
    }

    public Predicate<Entity> getDefaultTargetPredicate()
    {
        return (potentialTarget) -> potentialTarget instanceof LivingEntity livingTarget && this.parentEntity.canSee(livingTarget) && livingTarget.canTakeDamage();
    }

    public abstract double getYProjectileOffset();
    public abstract int getShootDelay(); //(tick) actual moment for the projectile to spawn. The sound is played at 0 and projectile sent at this time
    public abstract int getShootTimeInterval(); //time gap between two shots
    public abstract boolean doesShootTimeDecreaseWhenTargetOutOfSight(); //usually true if shoot delay != 0, can be false if shoot delay == 0
    public abstract ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ);
    protected abstract void setAttacking(boolean bool);
    @Nullable public abstract SoundEvent getShootSound();
}
