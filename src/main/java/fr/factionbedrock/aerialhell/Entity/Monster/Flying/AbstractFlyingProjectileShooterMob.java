package fr.factionbedrock.aerialhell.Entity.Monster.Flying;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLikeGoals;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class AbstractFlyingProjectileShooterMob extends MobEntity implements Monster
{
	public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(AbstractFlyingProjectileShooterMob.class, TrackedDataHandlerRegistry.BOOLEAN);

	public AbstractFlyingProjectileShooterMob(EntityType<? extends AbstractFlyingProjectileShooterMob> type, World world) {super(type, world); this.moveControl = new GhastLikeGoals.MoveHelperController(this);}

	@Override protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {}
	@Override public void travel(Vec3d movementInput) {this.travelFlying(movementInput, 0.02F);}
	@Override public boolean isClimbing() {return false;}

	@Override protected void initGoals()
	{
		this.goalSelector.add(5, new GhastLikeGoals.RandomFlyGoal(this));
		this.goalSelector.add(7, new GhastLikeGoals.LookAroundGoal(this));
		this.goalSelector.add(7, new AbstractFlyingProjectileShooterMob.ShootProjectileGoal(this));
		//no target defined here
	}

	@Override protected void initDataTracker(DataTracker.Builder builder)
	{
		super.initDataTracker(builder);
		builder.add(ATTACKING, false);
	}
	
	public boolean isAttacking() {return this.getDataTracker().get(ATTACKING);}
	public void setAttacking(boolean isAttacking) {this.getDataTracker().set(ATTACKING, isAttacking);}

	@Override protected boolean isDisallowedInPeaceful() {return true;}
	@Override public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {return true;}

	@Override public void tickMovement()
	{
		super.tickMovement();
		if (this.getY() < 0 || this.getY() > 272) {this.discard();}
	}

	public abstract ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ);
	public abstract SoundEvent getShootSound();

	public static class ShootProjectileGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public ShootProjectileGoal(AbstractFlyingProjectileShooterMob flyingMob) {super(flyingMob);}

		@Override public double getYProjectileOffset() {return 0;}
		@Override public int getShootDelay() {return 10;}
		@Override public int getShootTimeInterval() {return 50;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return true;}
		@Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ) {return ((AbstractFlyingProjectileShooterMob)getParentEntity()).createProjectile(world, shooter, accX, accY, accZ);}
		@Override protected void setAttacking(boolean bool) {((AbstractFlyingProjectileShooterMob)getParentEntity()).setAttacking(bool);}
		@Override public SoundEvent getShootSound() {return ((AbstractFlyingProjectileShooterMob)getParentEntity()).getShootSound();}
	}
}