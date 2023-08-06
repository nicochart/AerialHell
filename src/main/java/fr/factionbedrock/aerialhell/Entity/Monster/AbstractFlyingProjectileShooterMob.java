package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLikeGoals;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public abstract class AbstractFlyingProjectileShooterMob extends FlyingMob implements Enemy
{
	public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(AbstractFlyingProjectileShooterMob.class, EntityDataSerializers.BOOLEAN);

	public AbstractFlyingProjectileShooterMob(EntityType<? extends AbstractFlyingProjectileShooterMob> type, Level levelIn) {super(type, levelIn); this.moveControl = new GhastLikeGoals.MoveHelperController(this);}

	@Override protected void registerGoals()
	{
		this.goalSelector.addGoal(5, new GhastLikeGoals.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new GhastLikeGoals.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new GhastLikeGoals.ShootProjectileGoal(this));
		//no target defined here
	}

	@Override protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACKING, false);
	}
	
	public boolean isAttacking() {return this.entityData.get(ATTACKING);}
	public void setAttacking(boolean isAttacking) {this.entityData.set(ATTACKING, isAttacking);}

	@Override protected boolean shouldDespawnInPeaceful() {return true;}
	@Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return true;}

	@Override public void aiStep()
	{
		super.aiStep();
		if (this.getY() < 0 || this.getY() > 272) {this.discard();}
	}

	public abstract AbstractHurtingProjectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ, int explosionPower);
	public abstract SoundEvent getShootSound();
}