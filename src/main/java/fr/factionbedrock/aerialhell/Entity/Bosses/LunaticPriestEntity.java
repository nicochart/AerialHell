package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.FlyMoveHelperController;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.FlyingLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.RandomFlyGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class LunaticPriestEntity extends AbstractBossEntity implements GoalConditionEntity.PhaseAwareGoalConditionEntity
{
	public static final int BOTH_PHASES_GOALS = 0, PHASE_1_GOALS = 1, PHASE_2_GOALS = 2;
	public int attackTimer;
	
	public LunaticPriestEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.attackTimer = 0;
		bossInfo.setColor(BossEvent.BossBarColor.YELLOW);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	/* ----- GoalConditionEntity.PhaseAwareGoalConditionEntity : Interface method implementation ----- */
	@Override public boolean checkGoalCondition(int conditionIndex) {return this.canUseGoalsAdditionalCondition(conditionIndex);} //need to override checkGoalCondition because priest implements both GoalSimpleConditionEntity and PhaseAwareGoalConditionEntity

	@Override public boolean canUseGoalsAdditionalCondition(int goalIndex)
	{
		if (!super.canUseGoalsAdditionalCondition()) {return false;}
		else
		{
			if (goalIndex == BOTH_PHASES_GOALS) {return true;}
			else if (goalIndex == PHASE_1_GOALS)
			{
				return this.isInPhase1();
			}
			else if (goalIndex == PHASE_2_GOALS)
			{
				return this.isInPhase2();
			}
			return false;
		}
	}
	/* ----------------------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		/*Phase 1 only*/
		this.goalSelector.addGoal(5, new ConditionalGoal(this, PHASE_1_GOALS, new RandomFlyGoal(this)));
		this.goalSelector.addGoal(7, new ConditionalGoal(this, PHASE_1_GOALS, new FlyingLookAroundGoal(this)));
		/*Phase 2 only*/
		this.goalSelector.addGoal(6, new ConditionalGoal(this, PHASE_2_GOALS, new RandomLookAroundGoal(this)));
		this.goalSelector.addGoal(4, new ConditionalGoal(this, PHASE_2_GOALS, new WaterAvoidingRandomStrollGoal(this, 1.0D)));
		/*Both phases*/
		this.goalSelector.addGoal(5, new ConditionalGoal(this, BOTH_PHASES_GOALS, new LookAtPlayerGoal(this, Player.class, 8.0F)));
		this.goalSelector.addGoal(2, new ConditionalGoal(this, BOTH_PHASES_GOALS, new LunaticProjectileAttackGoal(this)));
		this.goalSelector.addGoal(3, new ConditionalGoal(this, BOTH_PHASES_GOALS, new MeleeAttackGoal(this, 1.25D, false)));
		this.targetSelector.addGoal(2, new ConditionalGoal(this, BOTH_PHASES_GOALS, new NearestAttackableTargetGoal<>(this, Player.class, true)));
		/*Independant of phases*/
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
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
			this.moveControl = new FlyMoveHelperController(this);
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
	
	@Override public boolean hurtServer(ServerLevel serverWorld, DamageSource source, float amount)
	{
		boolean flag = super.hurtServer(serverWorld, source, amount);
		if (flag)
		{
			if (source.getEntity() instanceof LivingEntity)
			{
				if (!EntityHelper.isCreativePlayer(source.getEntity()))
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
	
	@Override public boolean doHurtTarget(ServerLevel serverWorld, Entity attackedEntity)
	{
		DamageSource damagesource = this.damageSources().mobAttack(this);
		this.level().broadcastEntityEvent(this, (byte)4);
		float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		boolean flag = attackedEntity.hurtServer(serverWorld, damagesource, f1);
		if (flag)
		{
			attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.4F, attackedEntity.getDeltaMovement().z);
			EnchantmentHelper.doPostAttackEffects(serverWorld, attackedEntity, damagesource);
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

	@Override public Item getTrophy() {return AerialHellItems.LUNAR_PRIEST_TROPHY;}

	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source)
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
				if (this.isLocalInstanceAuthoritative())
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
						float f = 0.91F;
						if (this.onGround()) {f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.91F;}

						float f1 = 0.16277137F / (f * f * f);
						f = 0.91F;
						if (this.onGround()) {f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.91F;}

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

	public static class LunaticProjectileAttackGoal extends ShootProjectileGoal
	{
		public LunaticProjectileAttackGoal(LunaticPriestEntity entity) {super(entity);}

		@Override public boolean isValidTarget(@Nullable LivingEntity target)
		{
			return super.isValidTarget(target) && this.getParentEntity().hasLineOfSight(target);
		}

		@Override public Projectile createProjectile(Level world, LivingEntity shooter, double accX, double accY, double accZ)
		{
			return new LunaticProjectileEntity(world, shooter, accX, accY, accZ, 0.7f + shooter.getRandom().nextFloat(), ((LunaticPriestEntity)this.getParentEntity()).getShootVelocityInaccuracy());
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
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_DEATH;}
    @Override protected float getSoundVolume() {return 2.5F;}
}
