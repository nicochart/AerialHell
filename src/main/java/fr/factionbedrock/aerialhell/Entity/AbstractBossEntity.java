package fr.factionbedrock.aerialhell.Entity;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
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

import java.util.List;

public abstract class AbstractBossEntity extends AbstractActivableEntity
{
	protected final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);
	public static final EntityDataAccessor<Integer> BOSS_DIFFICULTY = SynchedEntityData.defineId(AbstractBossEntity.class, EntityDataSerializers.INT);

	public AbstractBossEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}

	@Override public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = this.bossHurt(source, amount);
		if (flag)
		{
			this.setActive(true);
			this.lastHurtByPlayerTime = 100;
			this.timeWithoutAnyTarget = 0;
		}
		return flag;
	}

	//copy of net.minecraft.world.entity.LivingEntity hurt(DamageSource source, float amount) method, removing everything non-related to my bosses, and calling other methods, allowing customization in my inheriting classes
	public boolean bossHurt(DamageSource source, float amount)
	{
		if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount)) return false;
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

	public boolean tryDying(DamageSource damageSource)
	{
		this.die(damageSource);
		return true;
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
		if (sourceEntity != null && !damageSource.is(DamageTypeTags.IS_EXPLOSION))
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

	@Override protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(BOSS_DIFFICULTY, 0);
	}

	public void setDifficulty(int difficulty) {this.entityData.set(BOSS_DIFFICULTY, difficulty);}
	public int getDifficulty() {return this.entityData.get(BOSS_DIFFICULTY);}

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
	
	@Override public void readAdditionalSaveData(CompoundTag compound)
	{
	      super.readAdditionalSaveData(compound);
	      if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
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
	}

	public void immunizeToEffects()
	{
		this.removeEffect(MobEffects.LEVITATION);
		this.removeEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get());
	}

	@Override public boolean startRiding(Entity entity, boolean p_19967_)
	{
		if (entity instanceof Boat boat)
		{
			//Copy of net.minecraft.world.entity.vehicle.VehicleEntity.destroy(Item item) {..}
			entity.kill();
			if (entity.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
			{
				ItemStack itemstack = new ItemStack(boat.getDropItem());
				if (this.hasCustomName()) {itemstack.setHoverName(this.getCustomName());}
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
