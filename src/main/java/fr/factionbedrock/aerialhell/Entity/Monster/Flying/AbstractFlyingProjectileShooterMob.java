package fr.factionbedrock.aerialhell.Entity.Monster.Flying;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLikeGoals;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractFlyingProjectileShooterMob extends Mob implements Enemy
{
	public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(AbstractFlyingProjectileShooterMob.class, EntityDataSerializers.BOOLEAN);

	public AbstractFlyingProjectileShooterMob(EntityType<? extends AbstractFlyingProjectileShooterMob> type, Level levelIn) {super(type, levelIn); this.moveControl = new GhastLikeGoals.MoveHelperController(this);}

	@Override protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {}
	@Override public void travel(Vec3 vec3) {this.travelFlying(vec3, 0.02F);}
	@Override public boolean onClimbable() {return false;}

	@Override protected void registerGoals()
	{
		this.goalSelector.addGoal(5, new GhastLikeGoals.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new GhastLikeGoals.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new AbstractFlyingProjectileShooterMob.ShootProjectileGoal(this));
		//no target defined here
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);
		builder.define(ATTACKING, false);
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

	public abstract Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ);
	public abstract SoundEvent getShootSound();

	public static class ShootProjectileGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public ShootProjectileGoal(AbstractFlyingProjectileShooterMob flyingMob) {super(flyingMob);}

		@Override public double getYProjectileOffset() {return 0;}
		@Override public int getShootDelay() {return 10;}
		@Override public int getShootTimeInterval() {return 50;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return true;}
		@Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ) {return ((AbstractFlyingProjectileShooterMob)getParentEntity()).createProjectile(level, shooter, accX, accY, accZ);}
		@Override protected void setAttacking(boolean bool) {((AbstractFlyingProjectileShooterMob)getParentEntity()).setAttacking(bool);}
		@Override public SoundEvent getShootSound() {return ((AbstractFlyingProjectileShooterMob)getParentEntity()).getShootSound();}
	}
}