package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MudCycleMageEntity extends AbstractBossEntity
{
	public int timeDying;
	public static int timeToDie = 60;
	private boolean forceSummoningClonesOnce = false;
	private float damageAmountSinceLastSummon;
	public MudCycleMageEntity(EntityType<? extends MudCycleMageEntity> type, World world)
	{
		super(type, world);
		this.damageAmountSinceLastSummon = 0;
		this.timeDying = 0;
	}
	
	@Override
    protected void initGoals()
    {
		this.goalSelector.add(2, new AvoidSunlightGoal(this));
	    this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
	    this.goalSelector.add(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.goalSelector.add(3, new SummonSpectralEntitiesGoal(this));
	    this.goalSelector.add(5, new ActiveWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.add(6, new ActiveLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.add(6, new ActiveRandomLookAroundGoal(this));
	    this.targetSelector.add(1, new RevengeGoal(this));
	    this.targetSelector.add(2, new ActiveNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.targetSelector.add(3, new ActiveTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.add(4, new FleeEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 200.0D)
				.add(EntityAttributes.FOLLOW_RANGE, 32.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.25D)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		boolean flag = super.damage(serverWorld, source, amount);
		if (flag) {this.damageAmountSinceLastSummon += amount;}
		return flag;
	}

	@Override public boolean tryActuallyHurt(ServerWorld serverWorld, DamageSource damageSource, float amount)
	{
		return this.getPhase() != BossPhase.DYING && super.tryActuallyHurt(serverWorld, damageSource, amount);
	}

	@Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
	{
		return !this.isInDeadOrDyingPhase() && super.tryAttack(serverWorld, attackedEntity);
	}

	public boolean isHealthMatchForSecondPhase() {return this.getHealth() * 2 < this.getMaxHealth();}
	public boolean isDamageAmountSinceLastSummonSufficentToTriggerSummon() {return this.damageAmountSinceLastSummon > 85 - 4 * this.getDifficulty();}
	public void resetDamageAmountSinceLastSummon() {this.damageAmountSinceLastSummon = 0;}

	@Override public Item getTrophy() {return AerialHellItems.MUD_CYCLE_MAGE_TROPHY;}

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
		if (this.timeDying > timeToDie) {this.tryDying(this.lastDamageSource == null ? this.getDamageSources().generic() : this.lastDamageSource);}
		if (this.age % 2 == 0) {this.makeRandomRoofBlockFall(2, 8, 2, 13);}
		this.playSummonParticles(2);
	}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.SECOND_PHASE) {this.forceSummoningClonesOnce = true;}
		if (nextPhase == BossPhase.DYING)
		{
			this.playSound(SoundEvents.ENTITY_WITHER_DEATH, 1.0F, 1.9F);
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, timeToDie, 10, true, false));
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
	public void handleStatus(byte id)
	{
		if (id == 5) {playSummonParticles(30);}
		else {super.handleStatus(id);}
	}

	private void playSummonParticles(int number)
	{
		if (this.getEntityWorld().isClient())
		{
			for(int i = 0; i < number; ++i)
			{
				double d0 = random.nextGaussian() * 0.02D; double d1 = random.nextGaussian() * 0.02D; double d2 = random.nextGaussian() * 0.02D;
				this.getEntityWorld().addParticleClient(ParticleTypes.LARGE_SMOKE, this.getParticleX(1.0D) - d0 * 10.0D, this.getRandomBodyY() - d1 * 10.0D, this.getParticleZ(1.0D) - d2 * 10.0D, 0.25 * (random.nextFloat() - 0.5), 0.2D, 0.25 * (random.nextFloat() - 0.5));
			}
		}
	}

	public static class SummonSpectralEntitiesGoal extends SummonThreeEntitiesGoal
	{
		private boolean shouldFinishSummoningClones;
		private boolean isNotSummoningClones;
		public SummonSpectralEntitiesGoal(MudCycleMageEntity entity) {super(entity, 0.0D);}

		public MudCycleMageEntity getMageGoalOwner() {return (MudCycleMageEntity) this.getGoalOwner();}

		@Override public boolean canStart() {return super.canStart() && this.getMageGoalOwner().isActive();}

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
			MudSpectralSoldierEntity entity = AerialHellEntities.MUD_SPECTRAL_SOLDIER.create(this.getGoalOwner().getEntityWorld(), SpawnReason.MOB_SUMMONED);
			entity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			entity.updateAttackType();
			return entity;
		}

		protected MudSpectralGolemEntity createMudSpectralGolem()
		{
			return AerialHellEntities.MUD_SPECTRAL_GOLEM.create(this.getGoalOwner().getEntityWorld(), SpawnReason.MOB_SUMMONED);
		}

		protected MudSpectralCycleMageEntity createClone()
		{
			MudSpectralCycleMageEntity entity = AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE.create(this.getGoalOwner().getEntityWorld(), SpawnReason.MOB_SUMMONED);
			entity.setMaster(this.getMageGoalOwner());
			return entity;
		}

		@Override protected void playEffect()
		{
			this.getGoalOwner().getEntityWorld().sendEntityStatus(this.getGoalOwner(), (byte)5);
			super.playEffect();
		}

		@Override protected int getSummonTimerTargetValue() {return 200;}
		@Override protected void resetTask() {super.resetTask(); this.getMageGoalOwner().resetDamageAmountSinceLastSummon(); this.resetCloningStatus();}
		@Override protected boolean customSummonConditionMet() {return this.getMageGoalOwner().isDamageAmountSinceLastSummonSufficentToTriggerSummon();}

		private void resetCloningStatus() {this.shouldFinishSummoningClones = false; this.isNotSummoningClones = false;}
	}
	
	@Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
}
