package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class ChainedGodEntity extends AbstractBossEntity
{
	public int attackTimer;
	public int timeDying;

	private static final EntityDataAccessor<Boolean> IMPLODING = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> UNCHAINING = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> UNCHAINED = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);

	public ChainedGodEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.attackTimer = 0; this.hurtTime = 0; this.timeDying = 0;
		bossInfo.setColor(BossEvent.BossBarColor.RED);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(0, new ChainedGodEntity.UnchainHimselfGoal(this));
		this.goalSelector.addGoal(0, new ChainedGodEntity.ChainedGodRandomFireballAttackGoal(this));
		this.goalSelector.addGoal(1, new ChainedGodEntity.ChainedGodImplodeGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodEntity.ChainedGodSummonTornSpiritSkullGoal(this));
		this.goalSelector.addGoal(3, new ChainedGodMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new ChainedGodWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new ChainedGodLeapAtTargetGoal(this, 0.7F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }

	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1400.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 6.0D)
				.add(Attributes.ATTACK_DAMAGE, 25.0D);
    }
	
	@Override public boolean hurt(DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getDirectEntity();
		Entity trueSourceEntity = source.getEntity();
		if ((this.isImploding() || this.isUnchaining()) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (!this.canGetProjectileDamages() && EntityHelper.isProjectile(immediateSourceEntity)) {return false;}
		else
		{
			boolean flag = super.hurt(source, amount);
			if (flag)
			{
				if (trueSourceEntity instanceof LivingEntity && !EntityHelper.isProjectile(immediateSourceEntity))
				{
					if (!(trueSourceEntity instanceof Player && ((Player)trueSourceEntity).isCreative()))
					{
						this.setTarget((LivingEntity) trueSourceEntity);
					}
				}
			}
			return flag;
		}
	}

	@Override public boolean tryActuallyHurt(DamageSource damageSource, float amount)
	{
		return this.isFreelyMoving() && super.tryActuallyHurt(damageSource, amount);
	}

	@Override protected void defineSynchedData()
	{
	    super.defineSynchedData();
	    this.entityData.define(IMPLODING, false);
	    this.entityData.define(UNCHAINING, false);
	    this.entityData.define(UNCHAINED, false);
	}
	
	@Override public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
	    compound.putBoolean("Imploding", this.isImploding());
	    compound.putBoolean("Unchaining", this.isUnchaining());
		if (this.isUnchained()) {compound.putBoolean("Unchained", true);}
	}
	
	@Override public void readAdditionalSaveData(CompoundTag compound)
	{
	    super.readAdditionalSaveData(compound);
	    this.setImploding(compound.getBoolean("Imploding"));
	    this.setUnchaining(compound.getBoolean("Unchaining"));
	    this.setUnchained(compound.getBoolean("Unchained"));
	}
	
	public boolean isImploding() {return this.entityData.get(IMPLODING);}
	public void setImploding(boolean isImploding) {this.entityData.set(IMPLODING, isImploding);}
	public boolean isUnchaining() {return this.entityData.get(UNCHAINING);}
	public void setUnchaining(boolean isUnchaining) {this.entityData.set(UNCHAINING, isUnchaining);}
	public boolean isUnchained() {return this.entityData.get(UNCHAINED);}
	public void setUnchained(boolean isUnchained) {this.entityData.set(UNCHAINED, isUnchained);}

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return false;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION) {this.playTransitionSound();}
		if (nextPhase == BossPhase.SECOND_PHASE) {this.implode();}
		if (nextPhase == BossPhase.DYING) {this.playDeathSound();}
	}

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}
	
	@Override public void tick()
    {
		if (random.nextFloat() > 0.5 && this.level().isClientSide()) {spawnParticles(AerialHellParticleTypes.GOD_FLAME.get(), 1, -0.06D);}

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
		if (this.timeDying > 140) {this.tryDying(this.lastDamageSource);}
	}

	@Override public void tickDeadPhase() {this.tickDyingPhase();}

	@Override public Item getTrophy() {return AerialHellBlocksAndItems.CHAINED_GOD_TROPHY_ITEM.get();}

	protected enum NearbyEntitiesInteractionType {NONE, DRAG, REPULSE}

	protected void runTransitionEffect()
	{
		if (this.level().isClientSide()) {this.spawnParticles(ParticleTypes.SMALL_FLAME, 5, -0.06D);}
		this.runRoarEffects(NearbyEntitiesInteractionType.REPULSE);
	}

	protected void runRoarEffects() {this.runRoarEffects(NearbyEntitiesInteractionType.NONE);}

	protected void runRoarEffects(NearbyEntitiesInteractionType type)
	{
		if (this.random.nextInt(4) == 0) {this.makeRandomRoofBlockFall(5, 15, 12, 20);}
		this.dragOrRepulseEntities(type);
		if (this.level().isClientSide()) {this.spawnParticles(ParticleTypes.LAVA, 5, 0.5D);}
	}

	protected void dragOrRepulseEntities(NearbyEntitiesInteractionType type)
	{
		if (type == NearbyEntitiesInteractionType.NONE) {return;}
		List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(20), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 15));
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof LivingEntity && !EntityHelper.isImmuneToChainedGodDrag(entity)) {dragEntity(entity, type);}
		}
	}

	protected void dragEntity(Entity entityIn, NearbyEntitiesInteractionType type)
	{
		double dragOrRepulseFactor = type == NearbyEntitiesInteractionType.DRAG ? 1.0 : -0.3;
		double factor = 0.8 / Math.max(5, this.distanceTo(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vec3 toGod = new Vec3(this.getX() - entityIn.getX(), this.getY() - entityIn.getY(), this.getZ() - entityIn.getZ()).multiply(factor, factor, factor);
		entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(toGod.multiply(factor * dragOrRepulseFactor,factor * dragOrRepulseFactor,factor * dragOrRepulseFactor)));
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
			this.level().addParticle(type, x, y, z, dx, dy, dz);
		}
	}

	private void destroyObstacles()
	{
		BlockPos pos = this.blockPosition().above(); int x,y,z; int xzRadius = 3, yRadius = 3;
		for (x=-xzRadius;x<=xzRadius;x++)
		{
			for (y=-yRadius;y<=yRadius;y++)
			{
				for (z=-xzRadius;z<=xzRadius;z++)
				{
					if (level().getBlockState(pos.offset(x, y, z)).is(AerialHellTags.Blocks.CHAINED_GOD_CAN_WALK_DESTROY))
					{
						level().destroyBlock(pos.offset(x, y, z), this.random.nextInt(3) > 1);
					}
				}
			}
		}
	}
	
	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override public boolean doHurtTarget(Entity attackedEntity)
	{
	      this.level().broadcastEntityEvent(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float amount = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
	      float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
	      boolean flag = attackedEntity.hurt(this.damageSources().mobAttack(this), amount);
	      if (flag)
	      {
	    	 ((LivingEntity)attackedEntity).knockback(kb * 0.5F, (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
	         attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.8F, attackedEntity.getDeltaMovement().z);
	         this.doEnchantDamageEffects(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}

	@Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) //broadcastEntityEvent
	{
		if (id == 4)
		{
			this.attackTimer = 10;
			this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
		else {super.handleEntityEvent(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_DEATH.get();}
    protected SoundEvent getFastDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_FAST_DEATH.get();}

    @Override protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
    	if (!blockIn.liquid())
    	{
        	this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_STEP.get(), 0.5F, 0.8F + 0.5F*random.nextFloat());
        }
    }

	public void playDeathSound() {this.playSound(this.getDeathSound(), 5.0F, 1.0F);}
	@Override public void playFastDeathSound() {this.playSound(getFastDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
	public void playRoarSound(float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR.get(), 5.0F, pitch);}
	public void playUnchainSound() {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_UNCHAIN.get(), 5.0F, 0.8F);}
	public void playTransitionSound() {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_TRANSITION.get(), 5.0F, 1.0F);}

	@Override protected void playHurtSound(DamageSource damageSource, boolean died)
	{
		if (damageSource.is(DamageTypes.GENERIC_KILL) && this.isDeadOrDying()) {return;} //tryDying method is already playing death sound

		if (died)
		{
			SoundEvent soundevent = this.getDeathSound();
			if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
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
		Level level = this.level();
		if (!level.isClientSide())
		{
			Level.ExplosionInteraction explosionInteraction = ForgeEventFactory.getMobGriefingEvent(level, this) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
			level.explode(this, this.getX(), this.getY(), this.getZ(), (float)5, explosionInteraction);
		}
		this.spawnImplosionParticle();
	}

	public void spawnImplosionParticle()
	{
		Level level = this.level();
		if (level.isClientSide())
		{
			for(int i = 0; i < 30; ++i)
			{
				RandomSource rand = this.getRandom(); double d0 = rand.nextGaussian() * 0.02D; double d1 = rand.nextGaussian() * 0.02D; double d2 = rand.nextGaussian() * 0.02D;
				level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
			}
		}
		else {level.broadcastEntityEvent(this, (byte)20);}
	}

	/* Chained God Goals */

	public static class UnchainHimselfGoal extends Goal
	{
		private final ChainedGodEntity goalOwner;
		public int timeSinceUnchaining;
		public UnchainHimselfGoal(ChainedGodEntity entity) {this.goalOwner = entity;}

		@Override public boolean canUse() {return this.goalOwner.canUnchainHimself();}

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
		private void immobilizeGoalOwner() {if (!this.goalOwner.level().isClientSide()) {this.goalOwner.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, this.getTimeToUnchain() * 2, 10, true, false)));}}

		public int getTimeToUnchain() {return 38;} //tick/2
		protected boolean canUnchain() {return this.timeSinceUnchaining > getTimeToUnchain();}
		protected void resetTask() {this.timeSinceUnchaining = 0; this.goalOwner.setUnchaining(false);}
	}

	public static class ChainedGodFireballAttackGoal extends GhastLikeGoals.ShootProjectileFlurryGoal
	{
		public ChainedGodFireballAttackGoal(ChainedGodEntity entity) {super(entity);}

		@Override public boolean canUse()
		{
			ChainedGodEntity chainedGod = (ChainedGodEntity)this.getParentEntity();
			LivingEntity target = chainedGod.getTarget();
			double DistanceToTarget = 0; if (target != null) {DistanceToTarget = chainedGod.distanceTo(target);}
			return chainedGod.canShootFireballs() && target != null && target.isAlive() && chainedGod.canAttack(target) && DistanceToTarget < 16;
		}

		@Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
		{
			RandomSource rand = this.getParentEntity().getRandom(); double halfDistanceToTarget = this.getParentEntity().distanceTo(this.getParentEntity().getTarget()) / 2;
			return new ChainedGodFireballEntity(level, shooter, accX + 0.5 * rand.nextGaussian() * halfDistanceToTarget, accY, accZ + 0.5 * rand.nextGaussian() * halfDistanceToTarget);
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

		@Override public boolean canUse() {return super.canUse() && this.getChainedGodGoalOwner().isUnchaining();}

		@Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
		{
			RandomSource rand = this.getParentEntity().getRandom();
			ChainedGodFireballEntity projectile = new ChainedGodFireballEntity(level, shooter, rand.nextInt(7) - 3, rand.nextInt(5) - 2, rand.nextInt(7) - 3);
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

		@Override public boolean canUse() {return this.goalOwner.canImplode();}

		@Override public void start() {resetTask();}
		@Override public void stop() {resetTask(); this.goalOwner.setImploding(false);}
		@Override public boolean requiresUpdateEveryTick() {return true;}

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
		private void immobilizeGoalOwner() {if (!this.goalOwner.level().isClientSide()) {this.goalOwner.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10, true, false)));}}

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

		@Override public boolean canUse()
		{
			ChainedGodEntity goalOwner = this.getChainedGodGoalOwner();
			return goalOwner.isInTransitionPhase();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.TORN_SPIRIT.get().create(this.getGoalOwner().level());
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
		@Override public boolean canUse() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.canUse();}
		@Override public boolean canContinueToUse() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.canContinueToUse();}
	}
	
	public static class ChainedGodMeleeAttackGoal extends ActiveMeleeAttackGoal
	{
		public ChainedGodMeleeAttackGoal(ChainedGodEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && ((ChainedGodEntity) this.goalOwner).isFreelyMoving();}
	}
	
	public static class ChainedGodWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public ChainedGodWaterAvoidingRandomWalkingGoal(ChainedGodEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean canUse() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.canUse();}
		@Override public boolean canContinueToUse() {return ((ChainedGodEntity) this.activableGoalOwner).isFreelyMoving() && super.canContinueToUse();}
	}
}
