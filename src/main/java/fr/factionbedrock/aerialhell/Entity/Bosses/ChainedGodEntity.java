package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileFlurryGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.ImplodingEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Entity.StagedActivableEntity;
import fr.factionbedrock.aerialhell.Entity.Util.ImplodingEntityInfo;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ChainedGodEntity extends AbstractBossEntity implements ImplodingEntity, StagedActivableEntity, GoalConditionEntity.PhaseAwareGoalConditionEntity
{
	public static final int OTHER_GOALS = 0, FREELY_MOVING_GOALS = 1, IMPLODE_GOAL = 2;
	public int attackTimer;
	public int timeDying;

	/* --- Imploding Entity fields --- */
	private static final TrackedData<Boolean> IMPLODING = DataTracker.registerData(ChainedGodEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private final ImplodingEntityInfo IMPLODING_INFO = new ImplodingEntityInfo(IMPLODING, 126, 600, new ImplodingEntityInfo.ImplodingSoundHelper(this::playRoarSound, 5.0F, 1.0F, -12));
	/* ------------------------------- */
	/* --- StagedActivableEntity fields --- */
	private static final TrackedData<Boolean> UNCHAINING = DataTracker.registerData(ChainedGodEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	StagedActivableEntityInfo.ActivatingPhaseParameters CHAINED_GOD_UNCHAINING_PARAMETERS = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(76).activatingStartSoundHelper(new PlaySoundHelper(this::playRoarSound, 5.0F, 1.6F));
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.ACTIVABLE_INFO, UNCHAINING, CHAINED_GOD_UNCHAINING_PARAMETERS);
	/* -------------------------------------- */

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

	public ChainedGodEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
		this.attackTimer = 0; this.hurtTime = 0; this.timeDying = 0;
		bossInfo.setColor(BossBar.Color.RED);
		bossInfo.setStyle(BossBar.Style.NOTCHED_6);
	}

	@Override protected void initGoals()
	{
		this.targetSelector.add(2, new ConditionalGoal(this, OTHER_GOALS, new ActiveTargetGoal<>(this, PlayerEntity.class, true)));
		this.targetSelector.add(1, new RevengeGoal(this));
		this.goalSelector.add(0, new ChainedGodEntity.ChainedGodRandomFireballAttackGoal(this));
		this.goalSelector.add(1, new ConditionalGoal(this, IMPLODE_GOAL, new ImplodeGoal(this)));
		this.goalSelector.add(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.add(2, new ChainedGodSummonTornSpiritGoal(this));
		this.goalSelector.add(3, new ConditionalGoal(this, FREELY_MOVING_GOALS, new MeleeAttackGoal(this, 1.25D, false)));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(5, new ConditionalGoal(this, FREELY_MOVING_GOALS, new WanderAroundFarGoal(this, 0.6D)));
		this.goalSelector.add(6, new ConditionalGoal(this, FREELY_MOVING_GOALS, new PounceAtTargetGoal(this, 0.7F)));
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
	}

	@Override protected void writeCustomData(WriteView view)
	{
		super.writeCustomData(view);
		/* ------- ImplodingEntity : Delegating to interface ------- */
		this.implodingWriteCustomData(view);
		/* --------------------------------------------------------- */
	}

	@Override protected void readCustomData(ReadView view)
	{
		super.readCustomData(view);
		/* ------- ImplodingEntity : Delegating to interface ------- */
		this.implodingReadCustomData(view);
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

	@Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}

	@Override public boolean handleFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}
	
	@Override public void tick()
    {
		/* -- ImplodingEntity : tick --*/
		this.implodingTick();
		/* ----------------------------*/
		if (random.nextFloat() > 0.5 && this.getEntityWorld().isClient()) {spawnParticles(AerialHellParticleTypes.GOD_FLAME, 1, -0.06D);}

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
			if (this.lastDamageSource != null && lastDamageSource.getAttacker() instanceof ServerPlayerEntity player)
			{
				Criteria.PLAYER_KILLED_ENTITY.trigger(player, this, lastDamageSource);
			}
			this.tryDying(this.lastDamageSource == null ? this.getDamageSources().generic() : this.lastDamageSource);
		}
	}

	@Override public void tickDeadPhase() {this.tickDyingPhase();}

	@Override public Item getTrophy() {return AerialHellItems.CHAINED_GOD_TROPHY;}

	protected void runTransitionEffect()
	{
		if (this.getEntityWorld().isClient()) {this.spawnParticles(ParticleTypes.SMALL_FLAME, 5, -0.06D);}
		this.runRoarEffects(NearbyEntitiesInteractionInfo.REPULSE_NEAR);
	}

	protected void runRoarEffects() {this.runRoarEffects(NearbyEntitiesInteractionInfo.NONE);}

	protected void runRoarEffects(NearbyEntitiesInteractionInfo type)
	{
		if (this.random.nextInt(4) == 0) {this.makeRandomRoofBlockFall(5, 15, 12, 20);}
		float dragOrRepulseFactor = type.noInteraction() ? 0.0F : type.getType().isDrag() ? 64.0F : 5.8F;
		this.dragOrRepulseEntities(type, dragOrRepulseFactor);
		if (this.getEntityWorld().isClient()) {this.spawnParticles(ParticleTypes.LAVA, 5, 0.5D);}
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
			this.getEntityWorld().addParticleClient(type, x, y, z, dx, dy, dz);
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
					if (getEntityWorld().getBlockState(pos.add(x, y, z)).isIn(AerialHellTags.Blocks.CHAINED_GOD_CAN_WALK_DESTROY))
					{
						getEntityWorld().breakBlock(pos.add(x, y, z), this.random.nextInt(3) > 1);
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
		this.getEntityWorld().sendEntityStatus(this, (byte)4);
		float f = (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
		float amount = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		float kb = (float)this.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK);
		boolean flag = attackedEntity.damage(serverWorld, damagesource, amount);
		if (flag)
		{
			((LivingEntity)attackedEntity).takeKnockback(kb * 0.5F, (double) MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
			attackedEntity.setVelocity(attackedEntity.getVelocity().x, (double)0.8F, attackedEntity.getVelocity().z);
			if (getEntityWorld() instanceof ServerWorld svWorld) {EnchantmentHelper.onTargetDamaged(svWorld, attackedEntity, damagesource);}
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
	public void playRoarSound(float volume, float pitch) {this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR, volume, pitch);}
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
	public boolean canStartImploding() {return isActiveAndUnchained() && this.getPhase() == BossPhase.SECOND_PHASE;}
	public boolean isFreelyMoving() {return !isInTransitionOrDyingPhase() && !this.isImploding() && !this.isUnchaining();}
	public boolean isActiveAndUnchained() {return this.isActive() && this.isUnchained();}

	/* Chained God Goals */
	public static class ChainedGodFireballAttackGoal extends ShootProjectileFlurryGoal
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

	public static class ChainedGodRandomFireballAttackGoal extends ShootProjectileGoal
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

	public static class ChainedGodSummonTornSpiritGoal extends SummonEntitiesGoal
	{
		public ChainedGodSummonTornSpiritGoal(ChainedGodEntity entity) {super(entity, 1.1D);}

		public ChainedGodEntity getChainedGodGoalOwner() {return (ChainedGodEntity) this.getGoalOwner();}

		@Override public boolean canStart()
		{
			ChainedGodEntity goalOwner = this.getChainedGodGoalOwner();
			return goalOwner.isInTransitionPhase();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.TORN_SPIRIT.create(this.getGoalOwner().getEntityWorld(), SpawnReason.MOB_SUMMONED);
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
