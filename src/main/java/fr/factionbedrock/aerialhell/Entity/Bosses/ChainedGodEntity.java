package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.*;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Entity.Util.ImplodingEntityInfo;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ChainedGodEntity extends AbstractBossEntity implements ImplodingEntity, StagedActivableEntity, GoalConditionEntity.PhaseAwareGoalConditionEntity
{
	public static final int OTHER_GOALS = 0, FREELY_MOVING_GOALS = 1, IMPLODE_GOAL = 2;
	public int attackTimer;
	public int timeDying;

	/* --- Imploding Entity fields --- */
	private static final EntityDataAccessor<Boolean> IMPLODING = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);
	private final ImplodingEntityInfo IMPLODING_INFO = new ImplodingEntityInfo(IMPLODING, 126, 600, new ImplodingEntityInfo.ImplodingSoundHelper(this::playRoarSound, 5.0F, 1.0F, -12));
	/* ------------------------------- */
	/* --- StagedActivableEntity fields --- */
	private static final EntityDataAccessor<Boolean> UNCHAINING = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);
	StagedActivableEntityInfo.ActivatingPhaseParameters CHAINED_GOD_UNCHAINING_PARAMETERS = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(76).activatingStartSoundHelper(new PlaySoundHelper(this::playRoarSound, 5.0F, 1.6F));
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.ACTIVABLE_INFO, UNCHAINING, CHAINED_GOD_UNCHAINING_PARAMETERS);
	/* -------------------------------------- */

	public ChainedGodEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.attackTimer = 0; this.hurtTime = 0; this.timeDying = 0;
		bossInfo.setColor(BossEvent.BossBarColor.RED);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	/* ------- ImplodingEntity : Interface method implementation or override for specific behavior ------- */
	@Override public ImplodingEntityInfo getImplodingEntityInfo() {return this.IMPLODING_INFO;}

	@Override public void onImplodingCastTick() {this.runRoarEffects(NearbyEntitiesInteractionInfo.DRAG_NEAR);}

	@Override public ImplodingTargetPolicy getImplodingTargetPolicy() {return new ImplodingTargetPolicy(true, false, 0);} //cooldown does not reset on target loss
	/* --------------------------------------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : Interface method implementation ------- */
	@Override public StagedActivableEntityInfo getActivableInfo() {return STAGED_ACTIVABLE_INFO;}
	/* ----------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : overriden methods pour specific behavior ------- */
	@Override public void onActivatingPhaseTick()
	{
		StagedActivableEntity.super.onActivatingPhaseTick();
		this.runRoarEffects();
	}
	/* -------------------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : alias method to clarity chained god's behavior in code ------- */
	public boolean isUnchaining() {return this.isActivating();}
	public boolean isUnchained() {return this.alreadyActivatedOnce();}
	/* ---------------------------------------------------------------------------------------------- */

	/* ----- GoalConditionEntity.PhaseAwareGoalConditionEntity : Interface method implementation ----- */
	@Override public boolean checkGoalCondition(int conditionIndex) {return this.canUseGoalsAdditionalCondition(conditionIndex);} //need to override checkGoalCondition because priest implements both GoalSimpleConditionEntity and PhaseAwareGoalConditionEntity

	@Override public boolean canUseGoalsAdditionalCondition(int goalIndex)
	{
		boolean superFlag = super.canUseGoalsAdditionalCondition();
		if (goalIndex == OTHER_GOALS) {return superFlag;}
		else if (goalIndex == FREELY_MOVING_GOALS)
		{
			return superFlag && this.isFreelyMoving();
		}
		else if (goalIndex == IMPLODE_GOAL)
		{
			return superFlag && this.canStartImploding() || this.isImploding(); //if the boss is already imploding, it needs to finish implosion (even if it is no longer active)
		}
		else {return false;}
	}
	/* ----------------------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ConditionalGoal(this, OTHER_GOALS, new NearestAttackableTargetGoal<>(this, Player.class, true)));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(0, new ChainedGodEntity.ChainedGodRandomFireballAttackGoal(this));
		this.goalSelector.addGoal(1, new ConditionalGoal(this, IMPLODE_GOAL, new ImplodeGoal(this)));
		this.goalSelector.addGoal(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodSummonTornSpiritGoal(this));
		this.goalSelector.addGoal(3, new ConditionalGoal(this, FREELY_MOVING_GOALS, new MeleeAttackGoal(this, 1.25D, false)));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new ConditionalGoal(this, FREELY_MOVING_GOALS, new WaterAvoidingRandomStrollGoal(this, 0.6D)));
        this.goalSelector.addGoal(6, new ConditionalGoal(this, FREELY_MOVING_GOALS, new LeapAtTargetGoal(this, 0.7F)));
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

	@Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getDirectEntity();
		Entity trueSourceEntity = source.getEntity();
		if ((this.isImploding() || this.isUnchaining()) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (!this.canGetProjectileDamages() && EntityHelper.isProjectile(immediateSourceEntity)) {return false;}
		else
		{
			boolean flag = super.hurtServer(serverLevel, source, amount);
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

	@Override public boolean tryActuallyHurt(ServerLevel serverLevel, DamageSource damageSource, float amount)
	{
		return this.isFreelyMoving() && super.tryActuallyHurt(serverLevel, damageSource, amount);
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
	    super.defineSynchedData(builder);
	    builder.define(IMPLODING, false);
	    builder.define(UNCHAINING, false);
	}

	@Override public void addAdditionalSaveData(ValueOutput valueOutput)
	{
		super.addAdditionalSaveData(valueOutput);
		/* ------- ImplodingEntity : Delegating to interface ------- */
		this.implodingAddAdditionalSaveData(valueOutput);
		/* --------------------------------------------------------- */
	}

	@Override public void readAdditionalSaveData(ValueInput valueInput)
	{
	    super.readAdditionalSaveData(valueInput);
		/* ------- ImplodingEntity : Delegating to interface ------- */
		this.implodingReadAdditionalSaveData(valueInput);
		/* --------------------------------------------------------- */
	}

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

	@Override public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public void tick()
    {
		/* -- ImplodingEntity : tick --*/
		this.implodingTick();
		/* ----------------------------*/
		if (random.nextFloat() > 0.5 && this.level().isClientSide()) {spawnParticles(AerialHellParticleTypes.GOD_FLAME.get(), 1, -0.06D);}

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
		if (this.timeDying > 140)
		{
			if (this.lastDamageSource != null && lastDamageSource.getEntity() instanceof ServerPlayer player)
			{
				CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(player, this, lastDamageSource);
			}
			this.tryDying(this.lastDamageSource == null ? this.damageSources().generic() : this.lastDamageSource);
		}
	}

	@Override public void tickDeadPhase() {this.tickDyingPhase();}

	@Override public Item getTrophy() {return AerialHellItems.CHAINED_GOD_TROPHY.get();}

	protected void runTransitionEffect()
	{
		if (this.level().isClientSide()) {this.spawnParticles(ParticleTypes.SMALL_FLAME, 5, -0.06D);}
		this.runRoarEffects(NearbyEntitiesInteractionInfo.REPULSE_NEAR);
	}

	protected void runRoarEffects() {this.runRoarEffects(NearbyEntitiesInteractionInfo.NONE);}

	protected void runRoarEffects(NearbyEntitiesInteractionInfo type)
	{
		if (this.random.nextInt(4) == 0) {this.makeRandomRoofBlockFall(5, 15, 12, 20);}
		float dragOrRepulseFactor = type.noInteraction() ? 0.0F : type.getType().isDrag() ? 64.0F : 5.8F;
		this.dragOrRepulseEntities(type, dragOrRepulseFactor);
		if (this.level().isClientSide()) {this.spawnParticles(ParticleTypes.LAVA, 5, 0.5D);}
	}

	@Override protected boolean canDragOrRepulseEntity(Entity entity)
	{
		return super.canDragOrRepulseEntity(entity) && !EntityHelper.isImmuneToChainedGodDrag(entity);
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
		if (!LoadedConfigParams.DO_BOSS_GRIEFING) {return;}

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

	@Override public boolean doHurtTarget(ServerLevel serverLevel, Entity attackedEntity)
	{
		DamageSource damagesource = this.damageSources().mobAttack(this);
		this.level().broadcastEntityEvent(this, (byte)4);
		float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float amount = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
		boolean flag = attackedEntity.hurtServer(serverLevel, damagesource, amount);
		if (flag)
		{
			((LivingEntity)attackedEntity).knockback(kb * 0.5F, (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
			attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.8F, attackedEntity.getDeltaMovement().z);
			EnchantmentHelper.doPostAttackEffects(serverLevel, attackedEntity, damagesource);
		}

		this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}

	@Override
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
	public void playRoarSound(float volume, float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR.get(), volume, pitch);}
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
	public boolean canStartImploding() {return isActiveAndUnchained() && this.getPhase() == BossPhase.SECOND_PHASE;}
	public boolean isFreelyMoving() {return !isInTransitionOrDyingPhase() && !this.isImploding() && !this.isUnchaining();}
	public boolean isActiveAndUnchained() {return this.isActive() && this.isUnchained();}

	/* Chained God Goals */

	public static class ChainedGodFireballAttackGoal extends ShootProjectileFlurryGoal
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

	public static class ChainedGodRandomFireballAttackGoal extends ShootProjectileGoal
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

	public static class ChainedGodSummonTornSpiritGoal extends SummonEntitiesGoal
	{
		public ChainedGodSummonTornSpiritGoal(ChainedGodEntity entity) {super(entity, 1.1D);}

		public ChainedGodEntity getChainedGodGoalOwner() {return (ChainedGodEntity) this.getGoalOwner();}

		@Override public boolean canUse()
		{
			ChainedGodEntity goalOwner = this.getChainedGodGoalOwner();
			return goalOwner.isInTransitionPhase();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.TORN_SPIRIT.get().create(this.getGoalOwner().level(), EntitySpawnReason.MOB_SUMMONED);
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
}
