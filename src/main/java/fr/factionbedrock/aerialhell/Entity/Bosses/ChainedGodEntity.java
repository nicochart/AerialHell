package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ChainedGodEntity extends AbstractBossEntity
{
	public int attackTimer;
	public int timeDying;

	private static final TrackedData<Boolean> IMPLODING = DataTracker.registerData(ChainedGodEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> UNCHAINING = DataTracker.registerData(ChainedGodEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> UNCHAINED = DataTracker.registerData(ChainedGodEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public ChainedGodEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
		this.attackTimer = 0; this.hurtTime = 0; this.timeDying = 0;
		bossInfo.setColor(BossBar.Color.RED);
		bossInfo.setStyle(BossBar.Style.NOTCHED_6);
	}

	@Override protected void initGoals()
    {
		this.targetSelector.add(2, new ActiveNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(1, new RevengeGoal(this));
		this.goalSelector.add(0, new ChainedGodEntity.UnchainHimselfGoal(this));
		this.goalSelector.add(0, new ChainedGodEntity.ChainedGodRandomFireballAttackGoal(this));
		this.goalSelector.add(1, new ChainedGodEntity.ChainedGodImplodeGoal(this));
		this.goalSelector.add(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.add(2, new ChainedGodEntity.ChainedGodSummonTornSpiritSkullGoal(this));
		this.goalSelector.add(3, new ChainedGodMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new ChainedGodWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.add(6, new ChainedGodLeapAtTargetGoal(this, 0.7F));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MudCycleMageEntity.class, true));
    }

	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 1400.0D)
				.add(EntityAttributes.FOLLOW_RANGE, 32.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3D)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.2D)
				.add(EntityAttributes.ATTACK_KNOCKBACK, 6.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 25.0D);
    }
	
	@Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getSource();
		Entity trueSourceEntity = source.getAttacker();
		if ((this.isImploding() || this.isUnchaining()) && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (!this.canGetProjectileDamages() && EntityHelper.isProjectile(immediateSourceEntity)) {return false;}
		else
		{
			boolean flag = super.damage(serverWorld, source, amount);
			if (flag)
			{
				if (trueSourceEntity instanceof LivingEntity && !EntityHelper.isProjectile(immediateSourceEntity))
				{
					if (!EntityHelper.isCreativePlayer(trueSourceEntity))
					{
						this.setTarget((LivingEntity) trueSourceEntity);
					}
				}
			}
			return flag;
		}
	}

	@Override public boolean tryActuallyHurt(ServerWorld serverWorld, DamageSource damageSource, float amount)
	{
		return this.isFreelyMoving() && super.tryActuallyHurt(serverWorld, damageSource, amount);
	}

	@Override protected void initDataTracker(DataTracker.Builder builder)
	{
	    super.initDataTracker(builder);
	    builder.add(IMPLODING, false);
	    builder.add(UNCHAINING, false);
	    builder.add(UNCHAINED, false);
	}
	
	@Override public void writeCustomDataToNbt(NbtCompound nbt)
	{
		super.writeCustomDataToNbt(nbt);
	    nbt.putBoolean("Imploding", this.isImploding());
	    nbt.putBoolean("Unchaining", this.isUnchaining());
		if (this.isUnchained()) {nbt.putBoolean("Unchained", true);}
	}
	
	@Override public void readCustomDataFromNbt(NbtCompound nbt)
	{
	    super.readCustomDataFromNbt(nbt);
	    this.setImploding(nbt.getBoolean("Imploding"));
	    this.setUnchaining(nbt.getBoolean("Unchaining"));
	    this.setUnchained(nbt.getBoolean("Unchained"));
	}
	
	public boolean isImploding() {return this.getDataTracker().get(IMPLODING);}
	public void setImploding(boolean isImploding) {this.getDataTracker().set(IMPLODING, isImploding);}
	public boolean isUnchaining() {return this.getDataTracker().get(UNCHAINING);}
	public void setUnchaining(boolean isUnchaining) {this.getDataTracker().set(UNCHAINING, isUnchaining);}
	public boolean isUnchained() {return this.getDataTracker().get(UNCHAINED);}
	public void setUnchained(boolean isUnchained) {this.getDataTracker().set(UNCHAINED, isUnchained);}

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return false;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION) {this.playTransitionSound();}
		if (nextPhase == BossPhase.SECOND_PHASE) {this.implode();}
		if (nextPhase == BossPhase.DYING) {this.playDeathSound();}
	}

	@Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}
	
	@Override public boolean handleFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}
	
	@Override public void tick()
    {
		if (random.nextFloat() > 0.5 && this.getWorld().isClient()) {spawnParticles(AerialHellParticleTypes.GOD_FLAME, 1, -0.06D);}

		if (this.isImploding()) {this.runRoarEffects(NearbyEntitiesInteractionType.DRAG);}
		if (this.isUnchaining()) {this.runRoarEffects();}
		this.destroyObstacles();
		super.tick();

		if (!this.isInDeadOrDyingPhase()) {this.timeDying = 0;}
    }

	@Override public void tickTransitionPhase()
	{
		this.runTransitionEffect();
		float newHealth = this.getHealth() + 7.5F;
		if (newHealth < this.getMaxHealth()) {this.setHealth(newHealth);}
		else {this.updateToNextPhase();}
	}

	@Override public void tickDyingPhase()
	{
		this.runRoarEffects();
		this.timeDying++;
		if (this.timeDying > 140) {this.tryDying(this.lastDamageSource == null ? this.getDamageSources().generic() : this.lastDamageSource);}
	}

	@Override public void tickDeadPhase() {this.tickDyingPhase();}

	@Override public Item getTrophy() {return AerialHellItems.CHAINED_GOD_TROPHY;}

	protected enum NearbyEntitiesInteractionType {NONE, DRAG, REPULSE}

	protected void runTransitionEffect()
	{
		if (this.getWorld().isClient()) {this.spawnParticles(ParticleTypes.SMALL_FLAME, 5, -0.06D);}
		this.runRoarEffects(NearbyEntitiesInteractionType.REPULSE);
	}

	protected void runRoarEffects() {this.runRoarEffects(NearbyEntitiesInteractionType.NONE);}

	protected void runRoarEffects(NearbyEntitiesInteractionType type)
	{
		if (this.random.nextInt(4) == 0) {this.makeRandomRoofBlockFall(5, 15, 12, 20);}
		this.dragOrRepulseEntities(type);
		if (this.getWorld().isClient()) {this.spawnParticles(ParticleTypes.LAVA, 5, 0.5D);}
	}

	protected void dragOrRepulseEntities(NearbyEntitiesInteractionType type)
	{
		if (type == NearbyEntitiesInteractionType.NONE) {return;}
		List<Entity> nearbyEntities = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(20), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 15));
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof LivingEntity && !EntityHelper.isImmuneToChainedGodDrag(entity)) {dragEntity(entity, type);}
		}
	}

	protected void dragEntity(Entity entityIn, NearbyEntitiesInteractionType type)
	{
		double dragOrRepulseFactor = type == NearbyEntitiesInteractionType.DRAG ? 1.0 : -0.3;
		double factor = 0.8 / Math.max(5, this.distanceTo(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vec3d toGod = new Vec3d(this.getX() - entityIn.getX(), this.getY() - entityIn.getY(), this.getZ() - entityIn.getZ()).multiply(factor, factor, factor);
		entityIn.setVelocity(entityIn.getVelocity().add(toGod.multiply(factor * dragOrRepulseFactor,factor * dragOrRepulseFactor,factor * dragOrRepulseFactor)));
	}

	private void spawnParticles(SimpleParticleType type, int number, double dy)
	{
		for (int i=0; i<number; i++)
		{
			double rand = random.nextFloat() * 2;
			double x = getX() + (random.nextFloat() - 0.5F) * rand;
			double y = (this.getBoundingBox().minY + rand) + 0.5D;
			double z = getZ() + (random.nextFloat() - 0.5F) * rand;
			double dx = (random.nextFloat() - 0.5F)/10;
			double dz = (random.nextFloat() - 0.5F)/10;
			this.getWorld().addParticle(type, x, y, z, dx, dy, dz);
		}
	}

	private void destroyObstacles()
	{
		if (!LoadedConfigParams.DO_BOSS_GRIEFING) {return;}
		
		BlockPos pos = this.getBlockPos().up(); int x,y,z; int xzRadius = 3, yRadius = 3;
		for (x=-xzRadius;x<=xzRadius;x++)
		{
			for (y=-yRadius;y<=yRadius;y++)
			{
				for (z=-xzRadius;z<=xzRadius;z++)
				{
					if (getWorld().getBlockState(pos.add(x, y, z)).isIn(AerialHellTags.Blocks.CHAINED_GOD_CAN_WALK_DESTROY))
					{
						getWorld().breakBlock(pos.add(x, y, z), this.random.nextInt(3) > 1);
					}
				}
			}
		}
	}
	
	@Override public void tickMovement()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.tickMovement();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
	{
		DamageSource damagesource = this.getDamageSources().mobAttack(this);
		this.getWorld().sendEntityStatus(this, (byte)4);
		float f = (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
		float amount = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		float kb = (float)this.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK);
		boolean flag = attackedEntity.damage(serverWorld, damagesource, amount);
		if (flag)
		{
			((LivingEntity)attackedEntity).takeKnockback(kb * 0.5F, (double) MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
			attackedEntity.setVelocity(attackedEntity.getVelocity().x, (double)0.8F, attackedEntity.getVelocity().z);
			if (getWorld() instanceof ServerWorld serverLevel) {EnchantmentHelper.onTargetDamaged(serverLevel, attackedEntity, damagesource);}
		}

		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}

	@Override
	public void handleStatus(byte id) //broadcastEntityEvent
	{
		if (id == 4)
		{
			this.attackTimer = 10;
			this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
		else {super.handleStatus(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_DEATH;}
    protected SoundEvent getFastDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_FAST_DEATH;}

    @Override protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
    	if (!blockIn.isLiquid())
    	{
        	this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_STEP, 0.5F, 0.8F + 0.5F*random.nextFloat());
        }
    }

	public void playDeathSound() {this.playSound(this.getDeathSound(), 5.0F, 1.0F);}
	@Override public void playFastDeathSound() {this.playSound(getFastDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
	public void playRoarSound(float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR, 5.0F, pitch);}
	public void playUnchainSound() {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_UNCHAIN, 5.0F, 0.8F);}
	public void playTransitionSound() {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_TRANSITION, 5.0F, 1.0F);}

	@Override protected void playHurtSound(DamageSource damageSource, boolean died)
	{
		if (damageSource.isOf(DamageTypes.GENERIC_KILL) && this.isDead()) {return;} //tryDying method is already playing death sound

		if (died)
		{
			SoundEvent soundevent = this.getDeathSound();
			if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());}
		}
		else {this.playHurtSound(damageSource);}
	}

	public boolean isInAnyPhaseBeforeSecondPhase() {return this.getPhase() == BossPhase.FIRST_PHASE || this.getPhase() == BossPhase.FIRST_TO_SECOND_TRANSITION;}
	public boolean canUnchainHimself() {return !this.isUnchained() && this.isActive();}
	public boolean canGetProjectileDamages() {return this.getPhase() == BossPhase.FIRST_PHASE;}
	public boolean canShootFireballs() {return isActiveAndUnchained() && !this.isImploding() && !isInTransitionOrDyingPhase() && (this.getPhase() == BossPhase.SECOND_PHASE || this.getHealth() * 2 < this.getMaxHealth());}
	public boolean canImplode() {return isActiveAndUnchained() && this.getPhase() == BossPhase.SECOND_PHASE;}
	public boolean isFreelyMoving() {return !isInTransitionOrDyingPhase() && !this.isImploding() && !this.isUnchaining();}
	public boolean isActiveAndUnchained() {return this.isActive() && this.isUnchained();}

	protected void implode()
	{
		World world = this.getWorld();
		if (!world.isClient())
		{
			world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)5, World.ExplosionSourceType.MOB);
		}
		this.spawnImplosionParticle();
	}

	public void spawnImplosionParticle()
	{
		World world = this.getWorld();
		if (world.isClient())
		{
			for(int i = 0; i < 30; ++i)
			{
				Random rand = this.getRandom(); double d0 = rand.nextGaussian() * 0.02D; double d1 = rand.nextGaussian() * 0.02D; double d2 = rand.nextGaussian() * 0.02D;
				world.addParticle(ParticleTypes.LARGE_SMOKE, this.getParticleX(1.0D) - d0 * 10.0D, this.getRandomBodyY() - d1 * 10.0D, this.getParticleZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
			}
		}
		else {world.sendEntityStatus(this, (byte)20);}
	}

	/* Chained God Goals */

	public static class UnchainHimselfGoal extends Goal
	{
		private final ChainedGodEntity goalOwner;
		public int timeSinceUnchaining;
		public UnchainHimselfGoal(ChainedGodEntity entity) {this.goalOwner = entity;}

		@Override public boolean canStart() {return this.goalOwner.canUnchainHimself();}

		@Override public void start() {resetTask();}
		@Override public void stop() {resetTask();}

		@Override public void tick()
		{
			if (!this.goalOwner.isUnchaining()) {startUnchaining();}
			else
			{
				this.playUnchainingEffect();
				this.timeSinceUnchaining++;
				if (this.canUnchain()) {this.finishUnchaining();}
			}
		}

		protected void startUnchaining()
		{
			this.immobilizeGoalOwner();
			this.goalOwner.setUnchaining(true);
			this.playStartUnchainingEffect();
		}

		protected void finishUnchaining()
		{
			this.goalOwner.setUnchained(true);
			this.goalOwner.setUnchaining(false);
		}

		public void playStartUnchainingEffect()
		{
			this.goalOwner.playRoarSound(1.6F);
			this.goalOwner.playUnchainSound();
		}

		public void playUnchainingEffect() {}
		private void immobilizeGoalOwner() {if (!this.goalOwner.getWorld().isClient()) {this.goalOwner.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, this.getTimeToUnchain() * 2, 10, true, false));}}

		public int getTimeToUnchain() {return 38;} //tick/2
		protected boolean canUnchain() {return this.timeSinceUnchaining > getTimeToUnchain();}
		protected void resetTask() {this.timeSinceUnchaining = 0; this.goalOwner.setUnchaining(false);}
	}

	public static class ChainedGodFireballAttackGoal extends GhastLikeGoals.ShootProjectileFlurryGoal
	{
		public ChainedGodFireballAttackGoal(ChainedGodEntity entity) {super(entity);}

		@Override public boolean canStart()
		{
			ChainedGodEntity chainedGod = (ChainedGodEntity)this.getParentEntity();
			LivingEntity target = chainedGod.getTarget();
			double DistanceToTarget = 0; if (target != null) {DistanceToTarget = chainedGod.distanceTo(target);}
			return chainedGod.canShootFireballs() && target != null && target.isAlive() && chainedGod.canTarget(target) && DistanceToTarget < 16;
		}

		@Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
		{
			Random rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
			return new ChainedGodFireballEntity(world, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget);
		}

		@Override public int getShootTimeInterval() {return 50 - ((ChainedGodEntity)this.getParentEntity()).getDifficulty() * 2;}
		@Override public int getShootDelay() {return 0;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
		@Override public double getYProjectileOffset() {return 0.5D;}
		@Override protected void setAttacking(boolean bool) {}
		@Override public SoundEvent getShootSound() {return null;}
		@Override public int getProjectileNumber() {return 3;}
		@Override public int getShootInvervalWithinBurst() {return 4;}
	}

	public static class ChainedGodRandomFireballAttackGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public ChainedGodRandomFireballAttackGoal(ChainedGodEntity entity) {super(entity);}
		private ChainedGodEntity getChainedGodGoalOwner() {return (ChainedGodEntity)this.getParentEntity();}

		@Override public boolean canStart() {return super.canStart() && this.getChainedGodGoalOwner().isUnchaining();}

		@Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
		{
			Random rand = this.getParentEntity().getRandom();
			ChainedGodFireballEntity projectile = new ChainedGodFireballEntity(world, shooter, rand.nextInt(7) - 3, rand.nextInt(5) - 2, rand.nextInt(7) - 3);
			projectile.setPos(projectile.getX(), projectile.getY() + 1.0F, projectile.getZ());
			return projectile;
		}

		@Override public int getShootTimeInterval() {return 2;}
		@Override public int getShootDelay() {return 0;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
		@Override public double getYProjectileOffset() {return 0.0D;}
		@Override protected void setAttacking(boolean bool) {}
		@Override public SoundEvent getShootSound() {return null;}
	}

	public static class ChainedGodImplodeGoal extends Goal
	{
		private final ChainedGodEntity goalOwner;
		public int implodeTimer, timeSinceImploding;
		public ChainedGodImplodeGoal(ChainedGodEntity entity) {this.goalOwner = entity;}

		@Override public boolean canStart() {return this.goalOwner.canImplode();}

		@Override public void start() {resetTask();}
		@Override public void stop() {resetTask(); this.goalOwner.setImploding(false);}
		@Override public boolean shouldRunEveryTick() {return true;}

		@Override public void tick()
		{
			this.implodeTimer++;

			if (this.willStartImplodingSoon()) {this.playStartImplodingSound(); this.immobilizeGoalOwner();}
			if (this.shouldStartImploding()) {this.startImploding();}

			if (this.goalOwner.isImploding()) //goal owner stop moving, raise his arms, make bonus particles, create an explosion if timeSince = fuzetime
			{
				this.timeSinceImploding++;
				if (this.shouldFinishImploding()) {this.finishImploding();}
			}
		}

		protected void startImploding()
		{
			this.goalOwner.setImploding(true);
			this.timeSinceImploding = 0;
		}

		protected void finishImploding()
		{
			this.goalOwner.implode();
			this.resetTask();
			this.goalOwner.setImploding(false);
		}

		protected void playStartImplodingSound() {this.goalOwner.playRoarSound(1.0F);}
		private void immobilizeGoalOwner() {if (!this.goalOwner.getWorld().isClient()) {this.goalOwner.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 10, true, false));}}

		public int getSoundOffset() {return 12;}
		public int getImplodeTimerTargetValue() {return 600;}
		public int getImplosionCastDuration() {return 126;}
		protected boolean shouldStartImploding() {return !this.goalOwner.isImploding() && this.implodeTimer >= this.getImplodeTimerTargetValue();}
		protected boolean shouldFinishImploding() {return this.timeSinceImploding >= this.getImplosionCastDuration();}
		protected boolean willStartImplodingSoon() {return this.implodeTimer == this.getImplodeTimerTargetValue() - this.getSoundOffset();}
		protected void resetTask() {this.implodeTimer = 0; this.timeSinceImploding = 0;}
	}

	public static class ChainedGodSummonTornSpiritSkullGoal extends SummonEntitiesGoal
	{
		public ChainedGodSummonTornSpiritSkullGoal(ChainedGodEntity entity) {super(entity, 1.1D);}

		public ChainedGodEntity getChainedGodGoalOwner() {return (ChainedGodEntity) this.getGoalOwner();}

		@Override public boolean canStart()
		{
			ChainedGodEntity goalOwner = this.getChainedGodGoalOwner();
			return goalOwner.isInTransitionPhase();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.TORN_SPIRIT.create(this.getGoalOwner().getWorld(), SpawnReason.MOB_SUMMONED);
		}

		@Override protected void setEntityPosToSummonPos(Entity entity) {entity.setPos(this.getGoalOwner().getX(), this.getGoalOwner().getY() + 1.0, this.getGoalOwner().getZ());}

		@Override protected int getSummonTimerTargetValue()
		{
			int difficulty = this.getChainedGodGoalOwner().getDifficulty();
			return switch (difficulty)
			{
				default-> 10; //never happens theorically. 0 is when there is no player nearby
				case 1 -> 10;
				case 2 -> 9;
				case 3 -> 7;
				case 4 -> 6;
				case 5 -> 5;
				case 6 -> 5;
			};
		}

		@Override protected void playEffect() {}
	}
	
	public static class ChainedGodLeapAtTargetGoal extends ActiveLeapAtTargetGoal
	{		
		public ChainedGodLeapAtTargetGoal(ChainedGodEntity godIn, float leapMotionYIn) {super(godIn, leapMotionYIn);}
		@Override public boolean canStart() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.canStart();}
		@Override public boolean shouldContinue() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.shouldContinue();}
	}
	
	public static class ChainedGodMeleeAttackGoal extends ActiveMeleeAttackGoal
	{
		public ChainedGodMeleeAttackGoal(ChainedGodEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && ((ChainedGodEntity) this.goalOwner).isFreelyMoving();}
	}
	
	public static class ChainedGodWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public ChainedGodWaterAvoidingRandomWalkingGoal(ChainedGodEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && ((ChainedGodEntity) this.getGoalOwner()).isFreelyMoving();}
	}
}
