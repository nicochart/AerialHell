package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LunaticPriestEntity extends AbstractBossEntity
{
	public int attackTimer;
	
	public LunaticPriestEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.attackTimer = 0;
		bossInfo.setColor(BossEvent.BossBarColor.YELLOW);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}
	
	@Override
    protected void registerGoals()
    {
		/*Phase 1 only*/
		this.goalSelector.addGoal(5, new LunaticPriestEntity.PriestRandomFlyGoal(this));
		this.goalSelector.addGoal(7, new GhastLikeGoals.LookAroundGoal(this));
		this.goalSelector.addGoal(6, new PriestLookRandomlyGoal(this));
		/*Phase 2 only*/
	    this.goalSelector.addGoal(4, new PriestWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
	    /*Both phases*/
	    this.goalSelector.addGoal(2, new LunaticPriestEntity.LunaticProjectileAttackGoal(this));
	    this.goalSelector.addGoal(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public float getMaxHealthForPhase2() {return this.getMaxHealth() / 2;}
	
	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		if (!isActive)
		{
			this.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false));
		}
		else
		{
			if (this.shouldUpdateToPhase1()) {this.updateToPhase(BossPhase.FIRST_PHASE);}
		}
	}

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return type == BossPhaseTickType.ALL;}
	
	public boolean isInPhase1() {return this.getPhase() == BossPhase.FIRST_PHASE;}
	public boolean isInPhase2() {return this.getPhase() == BossPhase.SECOND_PHASE;}
	public boolean shouldUpdateToPhase1() {return this.getHealth() >= getMaxHealthForPhase2() && this.isInPhase2();}
	public boolean shouldUpdateToPhase2() {return this.getHealth() < getMaxHealthForPhase2() && this.isInPhase1();}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_PHASE)
		{
			this.moveControl = new GhastLikeGoals.MoveHelperController(this);
			this.setDeltaMovement(this.getDeltaMovement().add(0,2,0));
		}
		else if (nextPhase == BossPhase.SECOND_PHASE)
		{
			this.moveControl = new MoveControl(this);
			this.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false));
		}
	}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (this.getPhase() == phase) {return false;}
		if (phase == BossPhase.FIRST_PHASE) {return shouldUpdateToPhase1() && this.isActive();}
		if (phase == BossPhase.SECOND_PHASE) {return shouldUpdateToPhase2() || !this.isActive();}
		else {return false;}
	}
	
	@Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
	{
		boolean flag = super.hurtServer(serverLevel, source, amount);
		if (flag)
		{
			if (source.getEntity() instanceof LivingEntity)
			{
				if (!(source.getEntity() instanceof Player && ((Player)source.getEntity()).isCreative()))
				{
					this.setTarget((LivingEntity) source.getEntity());
				}
			}
		}
		return flag;
	}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 600.0D)
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.27D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.05D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D);
    }
	
	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override public boolean doHurtTarget(ServerLevel serverLevel, Entity attackedEntity)
	{
		DamageSource damagesource = this.damageSources().mobAttack(this);
		this.level().broadcastEntityEvent(this, (byte)4);
		float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		boolean flag = attackedEntity.hurtServer(serverLevel, damagesource, f1);
		if (flag)
		{
			attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.4F, attackedEntity.getDeltaMovement().z);
			EnchantmentHelper.doPostAttackEffects(serverLevel, attackedEntity, damagesource);
		}

		this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}
	
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleEntityEvent(id);}
	}

	@Override public Item getTrophy() {return AerialHellBlocksAndItems.LUNAR_PRIEST_TROPHY_ITEM.get();}

	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
	{
		if (isInPhase1()) {return false;}
		else {return super.causeFallDamage(distance, damageMultiplier, source);}
	}
	
	@Override protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}
	
	/*copied from net.minecraft.entity.FlyingEntity*/
	@Override public void travel(Vec3 travelVector)
	{
		if (isInPhase1())
		{
			if (isActive())
			{
				if (this.isControlledByLocalInstance())
				{
					if (this.isInWater())
					{
						this.moveRelative(0.02F, travelVector);
						this.move(MoverType.SELF, this.getDeltaMovement());
						this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
					}
					else if (this.isInLava())
					{
						this.moveRelative(0.02F, travelVector);
						this.move(MoverType.SELF, this.getDeltaMovement());
						this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
					}
					else
					{
						BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
						float f = 0.91F;
						if (this.onGround()) {f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;}

						float f1 = 0.16277137F / (f * f * f);
						f = 0.91F;
						if (this.onGround()) {f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;}

						this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, travelVector);
						this.move(MoverType.SELF, this.getDeltaMovement());
						this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
					}
				}
				this.calculateEntityAnimation(false);
			}
		}
		else {super.travel(travelVector);}
	}
	
	@Override public boolean onClimbable()
	{
		if (isInPhase1() && isActive()) {return false;}
		else {return super.onClimbable();}
	}

	public float getShootVelocityInaccuracy() {return this.isInPhase1() ? 0.0f : 0.3f;}
	
	/*
	 * Goals
	 */

	static class PriestRandomFlyGoal extends GhastLikeGoals.RandomFlyGoal
	{
		public PriestRandomFlyGoal(LunaticPriestEntity priestIn) {super(priestIn);}
		
		@Override public boolean canUse()
		{
			LunaticPriestEntity priest = (LunaticPriestEntity) this.getParentEntity();
			if (!priest.isActive() || priest.isInPhase2()) {return false;}
			else {return super.canUse();}
		}
	}

	public static class LunaticProjectileAttackGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public LunaticProjectileAttackGoal(LunaticPriestEntity entity) {super(entity);}

		@Override public boolean canUse()
		{
			LunaticPriestEntity priest = (LunaticPriestEntity)this.getParentEntity();
			if (!priest.isActive()) {return false;}
			LivingEntity target = priest.getTarget();
			return super.canUse() && priest.hasLineOfSight(target) && target.isAlive() && priest.canAttack(target);
		}

		@Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
		{
			return new LunaticProjectileEntity(level, shooter, accX, accY, accZ, 0.7f + shooter.getRandom().nextFloat(), ((LunaticPriestEntity)this.getParentEntity()).getShootVelocityInaccuracy());
		}

		@Override public int getShootTimeInterval()
		{
			LunaticPriestEntity priest = ((LunaticPriestEntity)this.getParentEntity());
			int difficulty = priest.getDifficulty(); if (difficulty == 0) {difficulty = 1;}
			return priest.isInPhase1() ? 19 / difficulty + (int) (priest.random.nextFloat() * 15) : 14 / difficulty + (int) (priest.random.nextFloat() * 7);
		}

		@Override public int getShootDelay() {return 0;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
		@Override public double getYProjectileOffset() {return 0.5D;}
		@Override protected void setAttacking(boolean bool) {}
		@Override public SoundEvent getShootSound() {return null;}
	}
	
	public static class PriestLookRandomlyGoal extends ActiveRandomLookAroundGoal
	{		
		public PriestLookRandomlyGoal(LunaticPriestEntity priestIn) {super(priestIn);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && ((LunaticPriestEntity) this.activableGoalOwner).isInPhase2();}
	}
	
	public static class PriestWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public PriestWaterAvoidingRandomWalkingGoal(LunaticPriestEntity priestIn, double speedIn) {super(priestIn, speedIn);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && ((LunaticPriestEntity) this.getGoalOwner()).isInPhase2();}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_DEATH.get();}
    @Override protected float getSoundVolume() {return 2.5F;}
}
