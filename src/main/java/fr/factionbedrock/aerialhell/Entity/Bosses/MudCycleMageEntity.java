package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class MudCycleMageEntity extends AbstractBossEntity
{
	public int timeDying;
	public static int timeToDie = 60;
	private boolean forceSummoningClonesOnce = false;
	private float damageAmountSinceLastSummon;
	public MudCycleMageEntity(EntityType<? extends MudCycleMageEntity> type, Level world)
	{
		super(type, world);
		this.damageAmountSinceLastSummon = 0;
		this.timeDying = 0;
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.goalSelector.addGoal(3, new SummonSpectralEntitiesGoal(this));
	    this.goalSelector.addGoal(5, new ActiveWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
	    this.goalSelector.addGoal(6, new ActiveRandomLookAroundGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 200.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = super.hurt(source, amount);
		if (flag) {this.damageAmountSinceLastSummon += amount;}
		return flag;
	}

	@Override public boolean tryActuallyHurt(DamageSource damageSource, float amount)
	{
		return this.getPhase() != BossPhase.DYING && super.tryActuallyHurt(damageSource, amount);
	}

	@Override public boolean doHurtTarget(Entity attackedEntity)
	{
		return !this.isInDeadOrDyingPhase() && super.doHurtTarget(attackedEntity);
	}

	public boolean isHealthMatchForSecondPhase() {return this.getHealth() * 2 < this.getMaxHealth();}
	public boolean isDamageAmountSinceLastSummonSufficentToTriggerSummon() {return this.damageAmountSinceLastSummon > 85 - 4 * this.getDifficulty();}
	public void resetDamageAmountSinceLastSummon() {this.damageAmountSinceLastSummon = 0;}

	@Override public Item getTrophy() {return AerialHellBlocksAndItems.MUD_CYCLE_MAGE_TROPHY_ITEM.get();}

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return type == BossPhaseTickType.ALL;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}
	@Override public boolean doesPlayFastDeathSoundIfDyingWithTryDyingPhaseUpdate() {return true;}

	@Override public void tick()
	{
		super.tick();
		if (!this.isInDeadOrDyingPhase()) {this.timeDying = 0;}
	}

	@Override public void tickDyingPhase()
	{
		this.timeDying++;
		if (this.timeDying > timeToDie) {this.tryDying(this.lastDamageSource == null ? this.damageSources().generic() : this.lastDamageSource);}
		if (this.tickCount % 2 == 0) {this.makeRandomRoofBlockFall(2, 8, 2, 13);}
		this.playSummonParticles(2);
	}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.SECOND_PHASE) {this.forceSummoningClonesOnce = true;}
		if (nextPhase == BossPhase.DYING)
		{
			this.playSound(SoundEvents.WITHER_DEATH, 1.0F, 1.9F);
			this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, timeToDie, 10, true, false)));
		}
	}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (this.getPhase() == phase) {return false;}
		if (phase == BossPhase.FIRST_PHASE) {return !isHealthMatchForSecondPhase();}
		if (phase == BossPhase.SECOND_PHASE) {return isHealthMatchForSecondPhase() && !this.isInDeadOrDyingPhase();}
		else {return false;}
	}

	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 5) {playSummonParticles(30);}
		else {super.handleEntityEvent(id);}
	}

	private void playSummonParticles(int number)
	{
		if (this.level().isClientSide())
		{
			for(int i = 0; i < number; ++i)
			{
				double d0 = random.nextGaussian() * 0.02D; double d1 = random.nextGaussian() * 0.02D; double d2 = random.nextGaussian() * 0.02D;
				this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 0.25 * (random.nextFloat() - 0.5), 0.2D, 0.25 * (random.nextFloat() - 0.5));
			}
		}
	}

	public static class SummonSpectralEntitiesGoal extends SummonThreeEntitiesGoal
	{
		private boolean shouldFinishSummoningClones;
		private boolean isNotSummoningClones;
		public SummonSpectralEntitiesGoal(MudCycleMageEntity entity) {super(entity, 0.0D);}

		public MudCycleMageEntity getMageGoalOwner() {return (MudCycleMageEntity) this.getGoalOwner();}

		@Override public boolean canUse() {return super.canUse() && this.getMageGoalOwner().isActive();}

		@Override public Entity createEntity()
		{
			if ((!this.isNotSummoningClones && this.getGoalOwner().getRandom().nextInt(this.getMageGoalOwner().getDifficulty() + 1) > 2) || this.shouldFinishSummoningClones || this.getMageGoalOwner().forceSummoningClonesOnce)
			{
				this.shouldFinishSummoningClones = true;
				this.getMageGoalOwner().forceSummoningClonesOnce = false;
				return createClone();
			}
			else {this.isNotSummoningClones = true;}

			if (!(this.getMageGoalOwner().getPhase() == BossPhase.SECOND_PHASE)) {return createMudSpectralSoldier();}
			else
			{
				return (this.getGoalOwner().getRandom().nextInt(2) == 0) ? createMudSpectralSoldier() : createMudSpectralGolem();
			}
		}

		protected MudSpectralSoldierEntity createMudSpectralSoldier()
		{
			MudSpectralSoldierEntity entity = AerialHellEntities.MUD_SPECTRAL_SOLDIER.get().create(this.getGoalOwner().level());
			entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			entity.reassessWeaponGoal();
			return entity;
		}

		protected MudSpectralGolemEntity createMudSpectralGolem()
		{
			return AerialHellEntities.MUD_SPECTRAL_GOLEM.get().create(this.getGoalOwner().level());
		}

		protected MudSpectralCycleMageEntity createClone()
		{
			MudSpectralCycleMageEntity entity = AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE.get().create(this.getGoalOwner().level());
			entity.setMaster(this.getMageGoalOwner());
			return entity;
		}

		@Override protected void playEffect()
		{
			this.getGoalOwner().level().broadcastEntityEvent(this.getGoalOwner(), (byte)5);
			super.playEffect();
		}

		@Override protected int getSummonTimerTargetValue() {return 200;}
		@Override protected void resetTask() {super.resetTask(); this.getMageGoalOwner().resetDamageAmountSinceLastSummon(); this.resetCloningStatus();}
		@Override protected boolean customSummonConditionMet() {return this.getMageGoalOwner().isDamageAmountSinceLastSummonSufficentToTriggerSummon();}

		private void resetCloningStatus() {this.shouldFinishSummoningClones = false; this.isNotSummoningClones = false;}
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH;}
}
