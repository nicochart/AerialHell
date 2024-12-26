package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractBossEntity extends AbstractActivableEntity
{
	protected final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS);
	private static final TrackedData<Integer> BOSS_DIFFICULTY = DataTracker.registerData(AbstractBossEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Integer> PHASE = DataTracker.registerData(AbstractBossEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public AbstractBossEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}

	@Override public boolean damage(DamageSource source, float amount)
	{
		boolean flag = this.bossHurt(source, amount);
		if (flag)
		{
			if (source.isOf(DamageTypes.GENERIC_KILL) || source.isOf(DamageTypes.OUT_OF_WORLD)) {}
			else {this.setActive(true);}
			this.playerHitTimer = 100;
			this.timeWithoutAnyTarget = 0;
		}
		return flag;
	}

	//copy of net.minecraft.world.entity.LivingEntity damage(DamageSource source, float amount) method, removing everything non-related to my bosses, and calling other methods, allowing customization in my inheriting classes
	public boolean bossHurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source) || this.getWorld().isClient || this.isDead()) {return false;}
		else if (source.isIn(DamageTypeTags.IS_FIRE) && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {return false;}
		else
		{
			this.despawnCounter = 0;

			if (source.isIn(DamageTypeTags.IS_FREEZING) && this.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {amount *= 5.0F;}
			this.limbAnimator.setSpeed(1.5F);

			boolean wasOnHurtCooldown = (float)this.timeUntilRegen > 10.0F && !source.isIn(DamageTypeTags.BYPASSES_COOLDOWN);
			boolean actuallyGotHurt = tryActuallyHurt(source, amount);

			if (!actuallyGotHurt) {return false;}
			//we know this got hurt
			setLastHurtBy(source);

			if (!wasOnHurtCooldown)
			{
				this.getWorld().sendEntityDamage(this, source);
				if (!source.isIn(DamageTypeTags.NO_IMPACT)) {this.scheduleVelocityUpdate();}

				tryApplyingKnockback(source);
			}

			boolean died = false;
			if (this.isDead()) {died = tryDying(source);}

			if (!wasOnHurtCooldown) {playHurtSound(source, died);}

			this.lastDamageSource = source;
			this.lastDamageTime = this.getWorld().getTime();

			if (source.getAttacker() instanceof ServerPlayerEntity serverPlayerSource)
			{
				Criteria.PLAYER_HURT_ENTITY.trigger(serverPlayerSource, this, source, amount, amount, false);
			}
			return true;
		}
	}

	public boolean tryActuallyHurt(DamageSource damageSource, float amount) //returns true if the entity is actually hurt
	{
		boolean isOnHurtCooldown = (float)this.timeUntilRegen > 10.0F;
		boolean shouldDamageBeReducedDueToHurtCooldown = isOnHurtCooldown && !damageSource.isIn(DamageTypeTags.BYPASSES_COOLDOWN);

		if (shouldDamageBeReducedDueToHurtCooldown)
		{
			//the difference in damage amount is dealt if the amount of new "hurt" is greater than last one
			float reducedAmount = amount - this.lastDamageTaken;
			if (reducedAmount <= 0) {return false;}

			this.applyDamage(damageSource, reducedAmount);
			this.lastDamageTaken = amount;
			return true;
		}
		else
		{
			this.lastDamageTaken = amount;
			this.timeUntilRegen = 20;
			this.applyDamage(damageSource, amount);
			this.maxHurtTime = 10;
			this.hurtTime = this.maxHurtTime;
			return true;
		}
	}

	public enum NotDeadReason{START_NORMAL_PHASE, START_TRANSITION_PHASE}

	public boolean tryDying(DamageSource damageSource)
	{
		if (!this.enableTryDyingPhaseUpdate()) {this.onDeath(damageSource); return true;}
		else
		{
			if (damageSource.isOf(DamageTypes.GENERIC_KILL))
			{
				this.setHealth(0.0F);
				this.playFastDeathSound();
				this.onDeath(damageSource);
				return true;
			}

			if (this.isInNormalPhase())
			{
				BossPhase nextPhase = this.updateToNextPhase();
				this.applyAfterTriedDyingPhaseUpdateEffect(nextPhase, NotDeadReason.START_TRANSITION_PHASE);
				return false;
			}
			else if (this.isInTransitionPhase())
			{
				BossPhase nextPhase = this.updateToNextPhase();
				this.applyAfterTriedDyingPhaseUpdateEffect(nextPhase, NotDeadReason.START_NORMAL_PHASE);
				return false;
			}
			else //if (this.isInDeadPhase())
			{
				this.updateToNextPhase();
				this.setHealth(0.0F);
				if (doesPlayFastDeathSoundIfDyingWithTryDyingPhaseUpdate()) {this.playFastDeathSound();}
				this.onDeath(damageSource);
				return true;
			}
		}
	}

	public void playFastDeathSound()
	{
		if (this.getDeathSound() != null)
		{
			this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getPitch());
		}
	}

	protected void playHurtSound(DamageSource damageSource, boolean died)
	{
		if (died)
		{
			SoundEvent soundevent = this.getDeathSound();
			if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getPitch());}
		}
		else {this.playHurtSound(damageSource);}
	}

	public boolean tryApplyingKnockback(DamageSource damageSource)
	{
		Entity sourceEntity = damageSource.getAttacker();
		if (sourceEntity != null && !damageSource.isIn(DamageTypeTags.NO_KNOCKBACK))
		{
			double xKb = sourceEntity.getX() - this.getX();

			double zKb;
			for(zKb = sourceEntity.getZ() - this.getZ(); xKb * xKb + zKb * zKb < 1.0E-4D; zKb = (Math.random() - Math.random()) * 0.01D) {
				xKb = (Math.random() - Math.random()) * 0.01D;
			}

			this.takeKnockback((double)0.4F, xKb, zKb);
			this.tiltScreen(xKb, zKb);
			return true;
		}
		return false;
	}

	public void setLastHurtBy(DamageSource damageSource)
	{
		Entity sourceEntity = damageSource.getAttacker();
		if (sourceEntity != null)
		{
			if (sourceEntity instanceof LivingEntity sourceLivingEntity)
			{
				if (!damageSource.isIn(DamageTypeTags.NO_ANGER)) {this.setAttacker(sourceLivingEntity);}
			}

			if (sourceEntity instanceof PlayerEntity sourcePlayerEntity)
			{
				this.playerHitTimer = 100;
				this.attackingPlayer = sourcePlayerEntity;
			}
			else if (sourceEntity instanceof WolfEntity worfEntity)
			{
				if (worfEntity.isTamed())
				{
					this.playerHitTimer = 100;
					LivingEntity tamableEntityOwner = worfEntity.getOwner();
					if (tamableEntityOwner instanceof PlayerEntity playerOwner) {this.attackingPlayer = playerOwner;}
					else {this.attackingPlayer = null;}
				}
			}
		}
	}

	@Override protected void initDataTracker(DataTracker.Builder builder)
	{
		super.initDataTracker(builder);
		builder.add(BOSS_DIFFICULTY, 0);
		builder.add(PHASE, 0);
	}

	public void setDifficulty(int difficulty) {this.getDataTracker().set(BOSS_DIFFICULTY, difficulty);}
	public int getDifficulty() {return this.getDataTracker().get(BOSS_DIFFICULTY);}

	public BossPhase getPhase() {return getPhaseAfterNSteps(0);}
	public BossPhase getNextPhase() {return getPhaseAfterNSteps(1);}

	public BossPhase getPhaseAfterNSteps(int n)
	{
		int phase = this.getDataTracker().get(PHASE) + n;
		if (this.isDyingPhaseId(phase)) {return BossPhase.DYING;}
		else if (phase > BossPhase.DYING.getPhaseId()) {return BossPhase.DEAD;}
		return switch (phase)
		{
			default -> BossPhase.FIRST_PHASE;
			case 1 -> BossPhase.FIRST_TO_SECOND_TRANSITION;
			case 2 -> BossPhase.SECOND_PHASE;
			case 3 -> BossPhase.SECOND_TO_THIRD_TRANSITION;
			case 4 -> BossPhase.THIRD_PHASE;
			case 5 -> BossPhase.THIRD_TO_FOURTH_TRANSITION;
			case 6 -> BossPhase.FOURTH_PHASE;
		};
	}

	public BossPhase setPhase(int phase) {this.getDataTracker().set(PHASE, phase); return this.getPhase();}
	public void setPhase(BossPhase phase) {this.setPhase(phase.getPhaseId());}

	public boolean isDyingPhaseId(int phaseId) {return phaseId >= this.getPhaseIdToSkipToDyingPhase() && phaseId <= BossPhase.DYING.getPhaseId();}
	public boolean isInDyingPhase() {return this.getPhase() == BossPhase.DYING;}
	public boolean isInDeadPhase() {return this.getPhase() == BossPhase.DEAD || this.getPhase().getPhaseId() > BossPhase.DEAD.getPhaseId();}
	public boolean isInDeadOrDyingPhase() {return this.isInDyingPhase() || this.isInDeadPhase();}

	public boolean isInTransitionPhase() {return !this.isInDeadOrDyingPhase() && this.getPhase().getPhaseId() % 2 == 1;}
	public boolean isInTransitionOrDyingPhase() {return this.isInDyingPhase() || this.isInTransitionPhase();}
	public boolean isInNormalPhase() {return !isInDeadOrDyingPhase() && this.getPhase().getPhaseId() % 2 == 0;}

	public void applyPhaseUpdateEffect(BossPhase nextPhase) {} //called by updateToNextPhase()
	public void applyAfterTriedDyingPhaseUpdateEffect(BossPhase nextPhase, NotDeadReason reason) {this.setHealth(1.0F);} //called by tryDying() if the boss do not die
	public void tickTransitionPhase() {}
	public void tickDyingPhase() {}
	public void tickDeadPhase() {}
	public boolean shouldUpdateToPhase(BossPhase phase) {return false;}
	public boolean shouldUpdateToNextPhase() {return shouldUpdateToPhase(this.getNextPhase());}

	public BossPhase updateToPhase(BossPhase phase) //returns next phase
	{
		BossPhase actualPhase = phase;
		if (phase != BossPhase.DYING && isDyingPhaseId(phase.getPhaseId())) {actualPhase = BossPhase.DYING;}
		this.setPhase(actualPhase);
		this.applyPhaseUpdateEffect(actualPhase);
		return actualPhase;
	}

	public BossPhase updateToNextPhase() {return updateToPhase(this.getNextPhase());}

	public void tickPhaseEffects()
	{
		if (this.isInTransitionPhase()) {this.tickTransitionPhase();}
		if (this.isInDyingPhase()) {this.tickDyingPhase();}
		if (this.isInDeadPhase()) {this.tickDeadPhase();}
	}

	public enum BossPhaseTickType{ALL, NEXT}
	public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return type == BossPhaseTickType.NEXT;}
	public boolean enableTryDyingPhaseUpdate() {return false;}
	public boolean doesPlayFastDeathSoundIfDyingWithTryDyingPhaseUpdate() {return false;}

	public void tickBossPhases()
	{
		this.tickPhaseEffects();
		if (this.enableTickPhaseUpdate(BossPhaseTickType.NEXT))
		{
			if (this.shouldUpdateToNextPhase()) {this.updateToNextPhase(); return;}
		}
		if (this.enableTickPhaseUpdate(BossPhaseTickType.ALL))
		{
			for (BossPhase phase : BossPhase.PHASE_LIST)
			{
				if (this.shouldUpdateToPhase(phase)) {this.updateToPhase(phase); return;}
			}
		}
	}

	public abstract int getPhaseIdToSkipToDyingPhase(); //phase in which the boss skip to BossPhase.DYING phase

	/* Add the given player to the list of players tracking this entity. For instance, a player may track a boss in order to view its associated boss bar. */
	@Override public void onStartedTrackingBy(ServerPlayerEntity player)
	{
    	super.onStartedTrackingBy(player);
    	this.bossInfo.addPlayer(player);
    }

    /* Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for more information on tracking. */
	@Override public void onStoppedTrackingBy(ServerPlayerEntity player)
    {
    	super.onStoppedTrackingBy(player);
    	this.bossInfo.removePlayer(player);
    }

	@Override public void writeCustomDataToNbt(NbtCompound nbt)
	{
		super.writeCustomDataToNbt(nbt);
		nbt.putInt("Phase", this.getPhase().getPhaseId());
	}

	@Override public void readCustomDataFromNbt(NbtCompound nbt)
	{
		super.readCustomDataFromNbt(nbt);
		if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
		this.setPhase(nbt.getInt("Phase"));
	}
	
	@Override public void setCustomName(@Nullable Text name)
	{
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	}

	@Override protected void mobTick()
	{
		super.mobTick();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override public void tick()
	{
		super.tick();
		if (this.isActive() && this.age % 900 == 0) {this.updateBossDifficulty(); this.adaptBossDifficulty();}
		this.bossInfo.setVisible(this.isActive());
		this.immunizeToEffects();
		this.tickBossPhases();
	}

	public void immunizeToEffects()
	{
		this.removeStatusEffect(StatusEffects.LEVITATION);
		this.removeStatusEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS);
	}

	@Override public boolean startRiding(Entity entity, boolean p_19967_)
	{
		if (entity instanceof BoatEntity boat)
		{
			//Copy of net.minecraft.entity.vehicle.VehicleEntity.killAndDropItem(Item item) {..}
			entity.kill();
			if (this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
			{
				ItemStack itemstack = new ItemStack(boat.asItem());
				itemstack.set(DataComponentTypes.CUSTOM_NAME, this.getCustomName());
				this.dropStack(itemstack);
			}
		}
		return false;
	}

	@Override protected boolean canStartRiding(Entity entity) {return false;}

	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		this.updateBossDifficulty(); this.adaptBossDifficulty();
	}

	private void updateBossDifficulty()
	{
		List<Entity> nearbyEntities = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(30), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 15));
		int playerCount = 0;
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof PlayerEntity && !EntityHelper.isCreaOrSpecPlayer(entity)) {playerCount += 1;}
		}
		this.setDifficulty(Math.min(playerCount, 6)); //difficulty will be 0 if there is no player nearby, and will grow by 1 with each nearby player. capped at 6
	}

	protected void adaptBossDifficulty()
	{
		if (this.hasStatusEffect(StatusEffects.RESISTANCE)) {this.removeStatusEffect(StatusEffects.RESISTANCE);}
		if (this.hasStatusEffect(StatusEffects.STRENGTH)) {this.removeStatusEffect(StatusEffects.STRENGTH);}
		int amplifier = this.getDifficulty() - 1; //amplifier = 0 if there is one player, +1 per additional player
		if (amplifier > 0)
		{
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 54000, Math.min(3, (int) Math.ceil(amplifier / 2.0F)), false, false));
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 54000, Math.min(3, amplifier - 1), false, false));
		}
	}

	protected void makeRandomRoofBlockFall(int yBaseOffset, int maxXZOffset, int minYOffset, int maxYOffset)
	{
		BlockPos basePos = this.getBlockPos().up(yBaseOffset);
		BlockPos fallPos = basePos.add(this.random.nextBetweenExclusive(-maxXZOffset, maxXZOffset), this.random.nextBetweenExclusive(minYOffset, maxYOffset), this.random.nextBetweenExclusive(-maxXZOffset, maxXZOffset));
		while (this.getWorld().getBlockState(fallPos).isAir() && fallPos.getY() < basePos.getY() + 25) {fallPos = fallPos.up();}
		while (!FallingBlock.canFallThrough(this.getWorld().getBlockState(fallPos.down())) && fallPos.getY() > basePos.getY()) {fallPos = fallPos.down();}
		BlockState fallState = this.getWorld().getBlockState(fallPos);
		if (FallingBlock.canFallThrough(this.getWorld().getBlockState(fallPos.down())) && fallPos.getY() >= this.getWorld().getBottomY())
		{
			if (fallState.getBlock() instanceof CoreProtectedBlock block)
			{
				fallState = block.getCrackedVariant().getDefaultState();
			}
			FallingBlockEntity.spawnFromBlock(this.getWorld(), fallPos, fallState);
		}
		else {
			System.out.println("Wanted to make block fall but can't");
		}
	}

	@Override protected void dropEquipment(ServerWorld world, DamageSource damageSource, boolean p_33576_)
	{
		if (this.getTrophy() != null)
		{
			if (this.getRandom().nextInt(10) == 0) {this.dropItem(this.getTrophy());}
		}
	}

	@Nullable public abstract Item getTrophy();

	@Override public int getMinTimeToActivate() {return 5;}
	@Override public double getMinDistanceToActivate() {return 8;}
	@Override public double getMinDistanceToDeactivate() {return 48;}
	@Override public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {return false;}
	@Override public boolean canTeleportBetween(World source, World dest) {return false;}
}
