package fr.factionbedrock.aerialhell.Entity.Bosses;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Entity.AbstractActivableEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public abstract class AbstractBossEntity extends AbstractActivableEntity
{
	protected final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);
	private static final EntityDataAccessor<Integer> BOSS_DIFFICULTY = SynchedEntityData.defineId(AbstractBossEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(AbstractBossEntity.class, EntityDataSerializers.INT);

	public AbstractBossEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}

	@Override public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = this.bossHurt(source, amount);
		if (flag)
		{
			if (source.is(DamageTypes.GENERIC_KILL) || source.is(DamageTypes.FELL_OUT_OF_WORLD)) {}
			else {this.setActive(true);}
			this.lastHurtByPlayerTime = 100;
			this.timeWithoutAnyTarget = 0;
		}
		return flag;
	}

	//copy of net.minecraft.world.entity.LivingEntity hurt(DamageSource source, float amount) method, removing everything non-related to my bosses, and calling other methods, allowing customization in my inheriting classes
	public boolean bossHurt(DamageSource source, float amount)
	{
		if (!net.neoforged.neoforge.common.CommonHooks.onLivingAttack(this, source, amount)) return false;
		if (this.isInvulnerableTo(source) || this.level().isClientSide || this.isDeadOrDying()) {return false;}
		else if (source.is(DamageTypeTags.IS_FIRE) && this.hasEffect(MobEffects.FIRE_RESISTANCE)) {return false;}
		else
		{
			this.noActionTime = 0;

			if (source.is(DamageTypeTags.IS_FREEZING) && this.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {amount *= 5.0F;}
			this.walkAnimation.setSpeed(1.5F);

			boolean wasOnHurtCooldown = (float)this.invulnerableTime > 10.0F && !source.is(DamageTypeTags.BYPASSES_COOLDOWN);
			boolean actuallyGotHurt = tryActuallyHurt(source, amount);

			if (!actuallyGotHurt) {return false;}
			//we know this got hurt
			setLastHurtBy(source);

			if (!wasOnHurtCooldown)
			{
				this.level().broadcastDamageEvent(this, source);
				if (!source.is(DamageTypeTags.NO_IMPACT)) {this.markHurt();}

				tryApplyingKnockback(source);
			}

			boolean died = false;
			if (this.isDeadOrDying()) {died = tryDying(source);}

			if (!wasOnHurtCooldown) {playHurtSound(source, died);}

			this.lastDamageSource = source;
			this.lastDamageStamp = this.level().getGameTime();

			if (source.getEntity() instanceof ServerPlayer serverPlayerSource)
			{
				CriteriaTriggers.PLAYER_HURT_ENTITY.trigger(serverPlayerSource, this, source, amount, amount, false);
			}

			return true;
		}
	}

	public boolean tryActuallyHurt(DamageSource damageSource, float amount) //returns true if the entity is actually hurt
	{
		boolean isOnHurtCooldown = (float)this.invulnerableTime > 10.0F;
		boolean shouldDamageBeReducedDueToHurtCooldown = isOnHurtCooldown && !damageSource.is(DamageTypeTags.BYPASSES_COOLDOWN);

		if (shouldDamageBeReducedDueToHurtCooldown)
		{
			//the difference in damage amount is dealt if the amount of new "hurt" is greater than last one
			float reducedAmount = amount - this.lastHurt;
			if (reducedAmount <= 0) {return false;}

			this.actuallyHurt(damageSource, reducedAmount);
			this.lastHurt = amount;
			return true;
		}
		else
		{
			this.lastHurt = amount;
			this.invulnerableTime = 20;
			this.actuallyHurt(damageSource, amount);
			this.hurtDuration = 10;
			this.hurtTime = this.hurtDuration;
			return true;
		}
	}

	public enum NotDeadReason{START_NORMAL_PHASE, START_TRANSITION_PHASE}

	public boolean tryDying(DamageSource damageSource)
	{
		if (!this.enableTryDyingPhaseUpdate()) {this.die(damageSource); return true;}
		else
		{
			if (damageSource.is(DamageTypes.GENERIC_KILL))
			{
				this.setHealth(0.0F);
				this.playFastDeathSound();
				this.die(damageSource);
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
				this.die(damageSource);
				return true;
			}
		}
	}

	public void playFastDeathSound()
	{
		if (this.getDeathSound() != null)
		{
			this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getVoicePitch());
		}
	}

	protected void playHurtSound(DamageSource damageSource, boolean died)
	{
		if (died)
		{
			SoundEvent soundevent = this.getDeathSound();
			if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
		}
		else {this.playHurtSound(damageSource);}
	}

	public boolean tryApplyingKnockback(DamageSource damageSource)
	{
		Entity sourceEntity = damageSource.getEntity();
		if (sourceEntity != null && !damageSource.is(DamageTypeTags.NO_KNOCKBACK))
		{
			double xKb = sourceEntity.getX() - this.getX();

			double zKb;
			for(zKb = sourceEntity.getZ() - this.getZ(); xKb * xKb + zKb * zKb < 1.0E-4D; zKb = (Math.random() - Math.random()) * 0.01D) {
				xKb = (Math.random() - Math.random()) * 0.01D;
			}

			this.knockback((double)0.4F, xKb, zKb);
			this.indicateDamage(xKb, zKb);
			return true;
		}
		return false;
	}

	public void setLastHurtBy(DamageSource damageSource)
	{
		Entity sourceEntity = damageSource.getEntity();
		if (sourceEntity != null)
		{
			if (sourceEntity instanceof LivingEntity sourceLivingEntity)
			{
				if (!damageSource.is(DamageTypeTags.NO_ANGER)) {this.setLastHurtByMob(sourceLivingEntity);}
			}

			if (sourceEntity instanceof Player sourcePlayerEntity)
			{
				this.lastHurtByPlayerTime = 100;
				this.lastHurtByPlayer = sourcePlayerEntity;
			}
			else if (sourceEntity instanceof TamableAnimal tamableEntity)
			{
				if (tamableEntity.isTame())
				{
					this.lastHurtByPlayerTime = 100;
					LivingEntity tamableEntityOwner = tamableEntity.getOwner();
					if (tamableEntityOwner instanceof Player playerOwner) {this.lastHurtByPlayer = playerOwner;}
					else {this.lastHurtByPlayer = null;}
				}
			}
		}
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);
		builder.define(BOSS_DIFFICULTY, 0);
		builder.define(PHASE, 0);
	}

	public void setDifficulty(int difficulty) {this.entityData.set(BOSS_DIFFICULTY, difficulty);}
	public int getDifficulty() {return this.entityData.get(BOSS_DIFFICULTY);}

	public BossPhase getPhase() {return getPhaseAfterNSteps(0);}
	public BossPhase getNextPhase() {return getPhaseAfterNSteps(1);}

	public BossPhase getPhaseAfterNSteps(int n)
	{
		int phase = this.entityData.get(PHASE) + n;
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

	public BossPhase setPhase(int phase) {this.entityData.set(PHASE, phase); return this.getPhase();}
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
	@Override public void startSeenByPlayer(ServerPlayer player)
	{
    	super.startSeenByPlayer(player);
    	this.bossInfo.addPlayer(player);
    }

    /* Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for more information on tracking. */
	@Override public void stopSeenByPlayer(ServerPlayer player)
    {
    	super.stopSeenByPlayer(player);
    	this.bossInfo.removePlayer(player);
    }

	@Override public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Phase", this.getPhase().getPhaseId());
	}

	@Override public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
		this.setPhase(compound.getInt("Phase"));
	}
	
	@Override public void setCustomName(@Nullable Component name)
	{
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	}
	
	@Override protected void customServerAiStep()
	{
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	@Override public void tick()
	{
		super.tick();
		if (this.isActive() && this.tickCount % 900 == 0) {this.updateBossDifficulty(); this.adaptBossDifficulty();}
		this.bossInfo.setVisible(this.isActive());
		this.immunizeToEffects();
		this.tickBossPhases();
	}

	public void immunizeToEffects()
	{
		this.removeEffect(MobEffects.LEVITATION);
		this.removeEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate());
	}

	@Override public boolean startRiding(Entity entity, boolean p_19967_)
	{
		if (entity instanceof Boat boat)
		{
			//Copy of net.minecraft.world.entity.vehicle.VehicleEntity.destroy(Item item) {..}
			entity.kill();
			if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
			{
				ItemStack itemstack = new ItemStack(boat.getDropItem());
				itemstack.set(DataComponents.CUSTOM_NAME, this.getCustomName());
				this.spawnAtLocation(itemstack);
			}
		}
		return false;
	}

	@Override protected boolean canRide(Entity p_20339_) {return false;}

	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		this.updateBossDifficulty(); this.adaptBossDifficulty();
	}

	private void updateBossDifficulty()
	{
		List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(30), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 15));
		int playerCount = 0;
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof Player)
			{
				Player player = (Player) entity;
				if (!(player.isCreative() || player.isSpectator())) {playerCount += 1;}
			}
		}
		this.setDifficulty(Math.min(playerCount, 6)); //difficulty will be 0 if there is no player nearby, and will grow by 1 with each nearby player. capped at 6
	}

	protected void adaptBossDifficulty()
	{
		if (this.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {this.removeEffect(MobEffects.DAMAGE_RESISTANCE);}
		if (this.hasEffect(MobEffects.DAMAGE_BOOST)) {this.removeEffect(MobEffects.DAMAGE_BOOST);}
		int amplifier = this.getDifficulty() - 1; //amplifier = 0 if there is one player, +1 per additional player
		if (amplifier > 0)
		{
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 54000, Math.min(3, (int) Math.ceil(amplifier / 2.0F)), false, false));
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 54000, Math.min(3, amplifier - 1), false, false));
		}
	}

	protected void makeRandomRoofBlockFall(int yBaseOffset, int maxXZOffset, int minYOffset, int maxYOffset)
	{
		BlockPos basePos = this.blockPosition().above(yBaseOffset);
		BlockPos fallPos = basePos.offset(this.random.nextInt(-maxXZOffset, maxXZOffset), this.random.nextInt(minYOffset, maxYOffset), this.random.nextInt(-maxXZOffset, maxXZOffset));
		while (this.level().getBlockState(fallPos).isAir() && fallPos.getY() < basePos.getY() + 25) {fallPos = fallPos.above();}
		while (!FallingBlock.isFree(level().getBlockState(fallPos.below())) && fallPos.getY() > basePos.getY()) {fallPos = fallPos.below();}
		BlockState fallState = this.level().getBlockState(fallPos);
		if (FallingBlock.isFree(level().getBlockState(fallPos.below())) && fallPos.getY() >= level().getMinBuildHeight())
		{
			if (fallState.getBlock() instanceof CoreProtectedBlock block)
			{
				fallState = block.getCrackedVariant().defaultBlockState();
			}
			FallingBlockEntity.fall(level(), fallPos, fallState);
		}
	}

	@Override protected void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_)
	{
		if (this.getTrophy() != null)
		{
			if (this.getRandom().nextInt(10) == 0) {this.spawnAtLocation(this.getTrophy());}
		}
	}

	@Nullable public abstract Item getTrophy();

	@Override public int getMinTimeToActivate() {return 5;}
	@Override public double getMinDistanceToActivate() {return 8;}
	@Override public double getMinDistanceToDeactivate() {return 48;}
	@Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean canChangeDimensions() {return false;}
}
