package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LunaticPriestEntity extends AbstractBossEntity
{
	public int attackTimer;
	
	public LunaticPriestEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
		this.attackTimer = 0;
		bossInfo.setColor(BossBar.Color.YELLOW);
		bossInfo.setStyle(BossBar.Style.NOTCHED_6);
	}
	
	@Override
    protected void initGoals()
    {
		/*Phase 1 only*/
		this.goalSelector.add(5, new LunaticPriestEntity.PriestRandomFlyGoal(this));
		this.goalSelector.add(7, new GhastLikeGoals.LookAroundGoal(this));
		this.goalSelector.add(6, new PriestLookRandomlyGoal(this));
		/*Phase 2 only*/
	    this.goalSelector.add(4, new PriestWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.add(5, new ActiveLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
	    /*Both phases*/
	    this.goalSelector.add(2, new LunaticPriestEntity.LunaticProjectileAttackGoal(this));
	    this.goalSelector.add(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.targetSelector.add(1, new RevengeGoal(this));
	    this.targetSelector.add(2, new ActiveNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.goalSelector.add(4, new FleeEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public float getMaxHealthForPhase2() {return this.getMaxHealth() / 2;}
	
	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		if (!isActive)
		{
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 120, 2, true, false));
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
			this.setVelocity(this.getVelocity().add(0,2,0));
		}
		else if (nextPhase == BossPhase.SECOND_PHASE)
		{
			this.moveControl = new MoveControl(this);
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 120, 2, true, false));
		}
	}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (this.getPhase() == phase) {return false;}
		if (phase == BossPhase.FIRST_PHASE) {return shouldUpdateToPhase1() && this.isActive();}
		if (phase == BossPhase.SECOND_PHASE) {return shouldUpdateToPhase2() || !this.isActive();}
		else {return false;}
	}
	
	@Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		boolean flag = super.damage(serverWorld, source, amount);
		if (flag)
		{
			if (source.getAttacker() instanceof LivingEntity)
			{
				if (!EntityHelper.isCreativePlayer(source.getAttacker()))
				{
					this.setTarget((LivingEntity) source.getAttacker());
				}
			}
		}
		return flag;
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 600.0D)
				.add(EntityAttributes.FOLLOW_RANGE, 48.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.27D)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.05D)
				.add(EntityAttributes.ATTACK_KNOCKBACK, 1.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 7.0D);
    }
	
	@Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}
	
	@Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
	{
		DamageSource damagesource = this.getDamageSources().mobAttack(this);
		this.getEntityWorld().sendEntityStatus(this, (byte)4);
		float f = (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
		float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
		boolean flag = attackedEntity.damage(serverWorld, damagesource, f1);
		if (flag)
		{
			attackedEntity.setVelocity(attackedEntity.getVelocity().x, (double)0.4F, attackedEntity.getVelocity().z);
			EnchantmentHelper.onTargetDamaged(serverWorld, attackedEntity, damagesource);
		}

		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}
	
	@Override
	public void handleStatus(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleStatus(id);}
	}

	@Override public Item getTrophy() {return AerialHellItems.LUNAR_PRIEST_TROPHY;}

	@Override public void tickMovement()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.tickMovement();
    }
	
	@Override public boolean handleFallDamage(double distance, float damageMultiplier, DamageSource source)
	{
		if (isInPhase1()) {return false;}
		else {return super.handleFallDamage(distance, damageMultiplier, source);}
	}
	
	@Override protected void fall(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}
	
	/*copied from net.minecraft.entity.FlyingEntity*/
	@Override public void travel(Vec3d travelVector)
	{
		if (isInPhase1())
		{
			if (isActive())
			{
				if (this.isLogicalSideForUpdatingMovement())
				{
					if (this.isTouchingWater())
					{
						this.updateVelocity(0.02F, travelVector);
						this.move(MovementType.SELF, this.getVelocity());
						this.setVelocity(this.getVelocity().multiply(0.8F));
					}
					else if (this.isInLava())
					{
						this.updateVelocity(0.02F, travelVector);
						this.move(MovementType.SELF, this.getVelocity());
						this.setVelocity(this.getVelocity().multiply(0.5));
					}
					else
					{
						float f = 0.91F;
						if (this.isOnGround()) {f = this.getEntityWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91F;}

						float f1 = 0.16277137F / (f * f * f);
						f = 0.91F;
						if (this.isOnGround()) {f = this.getEntityWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91F;}

						this.updateVelocity(this.isOnGround() ? 0.1F * f1 : 0.02F, travelVector);
						this.move(MovementType.SELF, this.getVelocity());
						this.setVelocity(this.getVelocity().multiply((double)f));
					}
				}
				this.updateLimbs(false);
			}
		}
		else {super.travel(travelVector);}
	}
	
	@Override public boolean isClimbing()
	{
		if (isInPhase1() && isActive()) {return false;}
		else {return super.isClimbing();}
	}

	public float getShootVelocityInaccuracy() {return this.isInPhase1() ? 0.0f : 0.3f;}
	
	/*
	 * Goals
	 */

	static class PriestRandomFlyGoal extends GhastLikeGoals.RandomFlyGoal
	{
		public PriestRandomFlyGoal(LunaticPriestEntity priestIn) {super(priestIn);}
		
		@Override public boolean canStart()
		{
			LunaticPriestEntity priest = (LunaticPriestEntity) this.getParentEntity();
			if (!priest.isActive() || priest.isInPhase2()) {return false;}
			else {return super.canStart();}
		}
	}

	public static class LunaticProjectileAttackGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public LunaticProjectileAttackGoal(LunaticPriestEntity entity) {super(entity);}

		@Override public boolean canStart()
		{
			LunaticPriestEntity priest = (LunaticPriestEntity)this.getParentEntity();
			if (!priest.isActive()) {return false;}
			LivingEntity target = priest.getTarget();
			return super.canStart() && priest.canSee(target) && target.isAlive() && priest.canTarget(target);
		}

		@Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
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
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_DEATH;}
    @Override protected float getSoundVolume() {return 2.5F;}
}
