package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

//copy of net.minecraft.entity.mob.SlimeEntity but without size system
public class CrystalSlimeEntity extends MobEntity
{
	public float targetSquish;
	public float squish;
	public float oSquish;
	private boolean wasOnGround;

	public CrystalSlimeEntity(EntityType<? extends CrystalSlimeEntity> type, World world)
	{
		super(type, world);
		this.moveControl = new CrystalSlimeMoveControl(this);
	}

	@Override protected void initGoals()
	{
		this.goalSelector.add(1, new CrystalSlimeEntity.CrystalSlimeSwimGoal(this));
		this.goalSelector.add(2, new CrystalSlimeEntity.CrystalSlimeAttackGoal(this));
		this.goalSelector.add(3, new CrystalSlimeEntity.CrystalSlimeRandomDirectionGoal(this));
		this.goalSelector.add(5, new CrystalSlimeEntity.CrystalSlimeKeepOnJumpingGoal(this));
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, (entity, serverWorld) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
		this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@Override public SoundCategory getSoundCategory() {return SoundCategory.HOSTILE;}

	@Override protected void writeCustomData(WriteView view)
	{
		super.writeCustomData(view);
		view.putBoolean("wasOnGround", this.wasOnGround);
	}

	@Override protected void readCustomData(ReadView view)
	{
		super.readCustomData(view);
		this.wasOnGround = view.getBoolean("wasOnGround", false);
	}

	protected ParticleEffect getParticleType()
	{
		return new BlockStateParticleEffect(ParticleTypes.BLOCK, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState());
	}

	@Override public void tick()
	{
		this.squish = this.squish + (this.targetSquish - this.squish) * 0.5F;
		this.oSquish = this.squish;
		super.tick();
		if (this.isOnGround() && !this.wasOnGround)
		{
			float f = this.getDimensions(this.getPose()).width() * 2.0F;
			float f1 = f / 2.0F;

			for (int i = 0; (float)i < f * 16.0F; i++)
			{
				float f2 = this.random.nextFloat() * (float) (Math.PI * 2);
				float f3 = this.random.nextFloat() * 0.5F + 0.5F;
				float f4 = MathHelper.sin(f2) * f1 * f3;
				float f5 = MathHelper.cos(f2) * f1 * f3;
				this.getEntityWorld().addParticleClient(this.getParticleType(), this.getX() + (double)f4, this.getY(), this.getZ() + (double)f5, 0.0, 0.0, 0.0);
			}

			this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			this.targetSquish = -0.5F;
		} else if (!this.isOnGround() && this.wasOnGround) {
			this.targetSquish = 1.0F;
		}

		this.wasOnGround = this.isOnGround();
		this.decreaseSquish();
	}

	protected void decreaseSquish() {this.targetSquish *= 0.6F;}
	protected int getJumpDelay() {return this.random.nextInt(20) + 10;}

	@Override public EntityType<? extends CrystalSlimeEntity> getType() {return (EntityType<? extends CrystalSlimeEntity>) super.getType();}

	@Override public void pushAwayFrom(Entity entity)
	{
		super.pushAwayFrom(entity);
		if (entity instanceof IronGolemEntity && this.isDealsDamage()) {this.dealDamage((LivingEntity)entity);}
	}

	@Override public void onPlayerCollision(PlayerEntity player) { if (this.isDealsDamage()) {this.dealDamage(player);}}

	protected void dealDamage(LivingEntity livingEntity)
	{
		if (this.isAlive() && this.isInAttackRange(livingEntity) && this.canSee(livingEntity))
		{
			DamageSource damagesource = this.getDamageSources().mobAttack(this);
			if (this.getEntityWorld() instanceof ServerWorld serverWorld && livingEntity.damage(serverWorld, damagesource, this.getAttackDamage()))
			{
				this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				if (this.getEntityWorld() instanceof ServerWorld serverlevel) {EnchantmentHelper.onTargetDamaged(serverlevel, livingEntity, damagesource);}
			}
		}
	}

	@Override protected Vec3d getPassengerAttachmentPos(Entity entity, EntityDimensions dimensions, float y)
	{
		return new Vec3d(0.0, (double)dimensions.height() - 0.015625 * 2 * (double)y, 0.0);
	}

	protected boolean isDealsDamage() {return this.canMoveVoluntarily();}
	protected float getAttackDamage() {return (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);}

	@Override protected SoundEvent getHurtSound(DamageSource source) {return SoundEvents.ENTITY_SLIME_HURT_SMALL;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_SLIME_DEATH_SMALL;}
	protected SoundEvent getSquishSound() {return SoundEvents.ENTITY_SLIME_SQUISH_SMALL;}
	protected SoundEvent getJumpSound() {return SoundEvents.ENTITY_SLIME_JUMP_SMALL;}

	@Override protected float getSoundVolume() {return 0.8F;}
	@Override public int getMaxLookPitchChange() {return 0;}
	protected boolean doPlayJumpSound() {return this.isAlive();}
	float getSlimeSoundPitch() {return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F;}

	@Override public void jump()
	{
		Vec3d vec3 = this.getVelocity();
		this.setVelocity(vec3.x, (double)this.getJumpVelocity(), vec3.z);
		this.velocityDirty = true;
	}

	public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return SlimeEntity.createMobAttributes()
        		.add(EntityAttributes.ATTACK_DAMAGE, 4D)
        		.add(EntityAttributes.MOVEMENT_SPEED, 0.4D)
        		.add(EntityAttributes.MAX_HEALTH, 24.0D)
        		.add(EntityAttributes.FOLLOW_RANGE, 16.0D);
    }
	
	public static boolean canSpawn(EntityType<? extends CrystalSlimeEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random)
    {
        return random.nextInt(10) == 0 && world.toServerWorld().isDay();
    }

	@Override public void remove(Entity.RemovalReason reason) //copied from Entity class
	{
		this.setRemoved(reason);
		//this.invalidateCaps();
	}

	public static class CrystalSlimeAttackGoal extends Goal
	{
		private final CrystalSlimeEntity slime;
		private int growTiredTimer;

		public CrystalSlimeAttackGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setControls(EnumSet.of(Goal.Control.LOOK));
		}

		@Override public boolean canStart()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.slime.canTarget(livingentity) ? false : this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
			}
		}

		@Override public void start()
		{
			this.growTiredTimer = toGoalTicks(300);
			super.start();
		}

		@Override public boolean shouldContinue()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.slime.canTarget(livingentity) ? false : --this.growTiredTimer > 0;
			}
		}

		@Override public boolean shouldRunEveryTick() {return true;}

		@Override public void tick()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity != null) {
				this.slime.lookAtEntity(livingentity, 10.0F, 10.0F);
			}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setDirection(this.slime.getYaw(), this.slime.isDealsDamage());}
		}
	}

	public static class CrystalSlimeSwimGoal extends Goal
	{
		private final CrystalSlimeEntity slime;

		public CrystalSlimeSwimGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
			entity.getNavigation().setCanSwim(true);
		}

		@Override public boolean canStart()
		{
			return (this.slime.isTouchingWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
		}

		@Override public boolean shouldRunEveryTick() {return true;}

		@Override public void tick()
		{
			if (this.slime.getRandom().nextFloat() < 0.8F) {this.slime.getJumpControl().setActive();}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setWantedMovement(1.2);}
		}
	}

	public static class CrystalSlimeKeepOnJumpingGoal extends Goal
	{
		private final CrystalSlimeEntity slime;

		public CrystalSlimeKeepOnJumpingGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
		}

		@Override public boolean canStart() {return !this.slime.hasVehicle();}

		@Override public void tick()
		{
			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setWantedMovement(1.0);}
		}
	}

	static class CrystalSlimeMoveControl extends MoveControl
	{
		private float yaw;
		private int jumpDelay;
		private final CrystalSlimeEntity slime;
		private boolean isAggressive;

		public CrystalSlimeMoveControl(CrystalSlimeEntity entity)
		{
			super(entity);
			this.slime = entity;
			this.yaw = 180.0F * entity.getYaw() / (float) Math.PI;
		}

		public void setDirection(float yaw, boolean isAgressive)
		{
			this.yaw = yaw;
			this.isAggressive = isAgressive;
		}

		public void setWantedMovement(double speedModivierIn)
		{
			this.speed = speedModivierIn;
			this.state = MoveControl.State.MOVE_TO;
		}

		@Override public void tick()
		{
			this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.yaw, 90.0F));
			this.entity.headYaw = this.entity.getYaw();
			this.entity.bodyYaw = this.entity.getYaw();
			if (this.state != MoveControl.State.MOVE_TO) {this.entity.setForwardSpeed(0.0F);}
			else
			{
				this.state = MoveControl.State.WAIT;
				if (this.entity.isOnGround())
				{
					this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED)));
					if (this.jumpDelay-- <= 0)
					{
						this.jumpDelay = this.slime.getJumpDelay();
						if (this.isAggressive) {this.jumpDelay /= 3;}

						this.slime.getJumpControl().setActive();
						if (this.slime.doPlayJumpSound()) {this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSlimeSoundPitch());}
					}
					else
					{
						this.slime.sidewaysSpeed = 0.0F;
						this.slime.forwardSpeed = 0.0F;
						this.entity.setMovementSpeed(0.0F);
					}
				}
				else {this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED)));}
			}
		}
	}

	public static class CrystalSlimeRandomDirectionGoal extends Goal
	{
		private final CrystalSlimeEntity slime;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public CrystalSlimeRandomDirectionGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setControls(EnumSet.of(Goal.Control.LOOK));
		}

		@Override public boolean canStart()
		{
			return this.slime.getTarget() == null && (this.slime.isOnGround() || this.slime.isTouchingWater() || this.slime.isInLava() || this.slime.hasStatusEffect(StatusEffects.LEVITATION)) && this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
		}

		@Override public void tick()
		{
			if (--this.nextRandomizeTime <= 0)
			{
				this.nextRandomizeTime = this.getTickCount(40 + this.slime.getRandom().nextInt(60));
				this.chosenDegrees = (float)this.slime.getRandom().nextInt(360);
			}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setDirection(this.chosenDegrees, false);}
		}
	}
}