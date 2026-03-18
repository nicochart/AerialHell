package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;

/* Same as ShootProjectileGoal but shoots a flurry of projectile, like a blaze, instead of only one projectile */
public abstract class ShootProjectileFlurryGoal extends ShootProjectileGoal
{
    private int shotProjectileCount;

    public ShootProjectileFlurryGoal(MobEntity mob) {super(mob);}

    public ShootProjectileFlurryGoal(MobEntity mob, boolean affectMovements) {super(mob, affectMovements);}

    @Override public void start()
    {
        super.start();
        this.shotProjectileCount = 0;
    }

    @Override public void stop()
    {
        super.stop();
        this.shotProjectileCount = 0;
    }

    @Override protected void resetTask()
    {
        super.resetTask();
        this.shotProjectileCount = 0;
    }

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
