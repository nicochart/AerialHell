package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;

//copy of net.minecraft.world.entity.monster.Slime but without size system
public class CrystalSlimeEntity extends Mob
{
	public float targetSquish;
	public float squish;
	public float oSquish;
	private boolean wasOnGround;

	public CrystalSlimeEntity(EntityType<? extends CrystalSlimeEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.moveControl = new CrystalSlimeMoveControl(this);
	}

	@Override protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new CrystalSlimeEntity.CrystalSlimeFloatGoal(this));
		this.goalSelector.addGoal(2, new CrystalSlimeEntity.CrystalSlimeAttackGoal(this));
		this.goalSelector.addGoal(3, new CrystalSlimeEntity.CrystalSlimeRandomDirectionGoal(this));
		this.goalSelector.addGoal(5, new CrystalSlimeEntity.CrystalSlimeKeepOnJumpingGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	@Override public SoundSource getSoundSource() {return SoundSource.HOSTILE;}

	@Override public void addAdditionalSaveData(CompoundTag compoundTag)
	{
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("wasOnGround", this.wasOnGround);
	}

	@Override public void readAdditionalSaveData(CompoundTag compoundTag)
	{
		super.readAdditionalSaveData(compoundTag);
		this.wasOnGround = compoundTag.getBoolean("wasOnGround");
	}

	protected ParticleOptions getParticleType()
	{
		return new BlockParticleOption(ParticleTypes.BLOCK, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().defaultBlockState());
	}

	@Override protected boolean shouldDespawnInPeaceful() {return true;}

	@Override public void tick()
	{
		this.squish = this.squish + (this.targetSquish - this.squish) * 0.5F;
		this.oSquish = this.squish;
		super.tick();
		if (this.onGround() && !this.wasOnGround)
		{
			float f = this.getDimensions(this.getPose()).width() * 2.0F;
			float f1 = f / 2.0F;

			for (int i = 0; (float)i < f * 16.0F; i++)
			{
				float f2 = this.random.nextFloat() * (float) (Math.PI * 2);
				float f3 = this.random.nextFloat() * 0.5F + 0.5F;
				float f4 = Mth.sin(f2) * f1 * f3;
				float f5 = Mth.cos(f2) * f1 * f3;
				this.level().addParticle(this.getParticleType(), this.getX() + (double)f4, this.getY(), this.getZ() + (double)f5, 0.0, 0.0, 0.0);
			}

			this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			this.targetSquish = -0.5F;
		} else if (!this.onGround() && this.wasOnGround) {
			this.targetSquish = 1.0F;
		}

		this.wasOnGround = this.onGround();
		this.decreaseSquish();
	}

	protected void decreaseSquish() {this.targetSquish *= 0.6F;}
	protected int getJumpDelay() {return this.random.nextInt(20) + 10;}

	@Override public EntityType<? extends CrystalSlimeEntity> getType() {return (EntityType<? extends CrystalSlimeEntity>) super.getType();}

	@Override public void push(Entity entity)
	{
		super.push(entity);
		if (entity instanceof IronGolem && this.isDealsDamage()) {this.dealDamage((LivingEntity)entity);}
	}

	@Override public void playerTouch(Player player) { if (this.isDealsDamage()) {this.dealDamage(player);}}

	protected void dealDamage(LivingEntity livingEntity)
	{
		if (this.isAlive())
		{
			if (this.distanceToSqr(livingEntity) < 0.36 * 4 && this.hasLineOfSight(livingEntity) && livingEntity.hurt(this.damageSources().mobAttack(this), this.getAttackDamage()))
			{
				this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.doEnchantDamageEffects(this, livingEntity);
			}
		}
	}

	@Override protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float y)
	{
		return new Vec3(0.0, (double)dimensions.height() - 0.015625 * 2 * (double)y, 0.0);
	}

	protected boolean isDealsDamage() {return this.isEffectiveAi();}
	protected float getAttackDamage() {return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);}

	@Override protected SoundEvent getHurtSound(DamageSource p_33631_) {return SoundEvents.SLIME_HURT_SMALL;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.SLIME_DEATH_SMALL;}
	protected SoundEvent getSquishSound() {return SoundEvents.SLIME_SQUISH_SMALL;}
	protected SoundEvent getJumpSound() {return SoundEvents.SLIME_JUMP_SMALL;}

	@Override protected float getSoundVolume() {return 0.8F;}
	@Override public int getMaxHeadXRot() {return 0;}
	protected boolean doPlayJumpSound() {return this.isAlive();}
	float getSoundPitch() {return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F;}

	@Override protected void jumpFromGround()
	{
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x, (double)this.getJumpPower(), vec3.z);
		this.hasImpulse = true;
	}

	public static AttributeSupplier.Builder registerAttributes()
    {
        return Slime.createMobAttributes()
        		.add(Attributes.ATTACK_DAMAGE, 4D)
        		.add(Attributes.MOVEMENT_SPEED, 0.4D)
        		.add(Attributes.MAX_HEALTH, 24.0D)
        		.add(Attributes.FOLLOW_RANGE, 16.0D);
    }
	
	public static boolean canSpawn(EntityType<CrystalSlimeEntity> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn)
    {
        return randomIn.nextInt(10) == 0 && worldIn.getLevel().isDay();
    }

	@Override public void remove(@Nonnull Entity.RemovalReason reason) //copied from Entity class
	{
		this.setRemoved(reason);
		if (reason == Entity.RemovalReason.KILLED) {this.gameEvent(GameEvent.ENTITY_DIE);}
		//this.invalidateCaps();
	}

	@Override protected ResourceKey<LootTable> getDefaultLootTable() {return this.getType().getDefaultLootTable();}

	public static class CrystalSlimeAttackGoal extends Goal
	{
		private final CrystalSlimeEntity slime;
		private int growTiredTimer;

		public CrystalSlimeAttackGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override public boolean canUse()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.slime.canAttack(livingentity) ? false : this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
			}
		}

		@Override public void start()
		{
			this.growTiredTimer = reducedTickDelay(300);
			super.start();
		}

		@Override public boolean canContinueToUse()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity == null) {
				return false;
			} else {
				return !this.slime.canAttack(livingentity) ? false : --this.growTiredTimer > 0;
			}
		}

		@Override public boolean requiresUpdateEveryTick() {return true;}

		@Override public void tick()
		{
			LivingEntity livingentity = this.slime.getTarget();
			if (livingentity != null) {
				this.slime.lookAt(livingentity, 10.0F, 10.0F);
			}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setDirection(this.slime.getYRot(), this.slime.isDealsDamage());}
		}
	}

	public static class CrystalSlimeFloatGoal extends Goal
	{
		private final CrystalSlimeEntity slime;

		public CrystalSlimeFloatGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
			entity.getNavigation().setCanFloat(true);
		}

		@Override public boolean canUse()
		{
			return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
		}

		@Override public boolean requiresUpdateEveryTick() {return true;}

		@Override public void tick()
		{
			if (this.slime.getRandom().nextFloat() < 0.8F) {this.slime.getJumpControl().jump();}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setWantedMovement(1.2);}
		}
	}

	public static class CrystalSlimeKeepOnJumpingGoal extends Goal
	{
		private final CrystalSlimeEntity slime;

		public CrystalSlimeKeepOnJumpingGoal(CrystalSlimeEntity entity)
		{
			this.slime = entity;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		@Override public boolean canUse() {return !this.slime.isPassenger();}

		@Override public void tick()
		{
			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setWantedMovement(1.0);}
		}
	}

	static class CrystalSlimeMoveControl extends MoveControl
	{
		private float yRot;
		private int jumpDelay;
		private final CrystalSlimeEntity slime;
		private boolean isAggressive;

		public CrystalSlimeMoveControl(CrystalSlimeEntity entity)
		{
			super(entity);
			this.slime = entity;
			this.yRot = 180.0F * entity.getYRot() / (float) Math.PI;
		}

		public void setDirection(float yRot, boolean isAgressive)
		{
			this.yRot = yRot;
			this.isAggressive = isAgressive;
		}

		public void setWantedMovement(double speedModivierIn)
		{
			this.speedModifier = speedModivierIn;
			this.operation = MoveControl.Operation.MOVE_TO;
		}

		@Override public void tick()
		{
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
			this.mob.yHeadRot = this.mob.getYRot();
			this.mob.yBodyRot = this.mob.getYRot();
			if (this.operation != MoveControl.Operation.MOVE_TO) {this.mob.setZza(0.0F);}
			else
			{
				this.operation = MoveControl.Operation.WAIT;
				if (this.mob.onGround())
				{
					this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
					if (this.jumpDelay-- <= 0)
					{
						this.jumpDelay = this.slime.getJumpDelay();
						if (this.isAggressive) {this.jumpDelay /= 3;}

						this.slime.getJumpControl().jump();
						if (this.slime.doPlayJumpSound()) {this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSoundPitch());}
					}
					else
					{
						this.slime.xxa = 0.0F;
						this.slime.zza = 0.0F;
						this.mob.setSpeed(0.0F);
					}
				}
				else {this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));}
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
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override public boolean canUse()
		{
			return this.slime.getTarget() == null && (this.slime.onGround() || this.slime.isInWater() || this.slime.isInLava() || this.slime.hasEffect(MobEffects.LEVITATION)) && this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl;
		}

		@Override public void tick()
		{
			if (--this.nextRandomizeTime <= 0)
			{
				this.nextRandomizeTime = this.adjustedTickDelay(40 + this.slime.getRandom().nextInt(60));
				this.chosenDegrees = (float)this.slime.getRandom().nextInt(360);
			}

			if (this.slime.getMoveControl() instanceof CrystalSlimeEntity.CrystalSlimeMoveControl movecontrol) {movecontrol.setDirection(this.chosenDegrees, false);}
		}
	}
}