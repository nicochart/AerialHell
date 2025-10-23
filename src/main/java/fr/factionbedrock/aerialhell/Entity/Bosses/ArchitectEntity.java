package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ArchitectEntity extends AbstractBossEntity
{
	public int timeDying;

	public ArchitectEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.timeDying = 0;
		this.hurtTime = 0;
		bossInfo.setColor(BossEvent.BossBarColor.BLUE);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }

	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1400.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 5.0D)
				.add(Attributes.ATTACK_DAMAGE, 25.0D);
    }

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return false;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION) {this.playTransitionSound();}
		if (nextPhase == BossPhase.SECOND_PHASE) {this.playTransitionSound();}
		if (nextPhase == BossPhase.DYING) {this.playDeathSound();}
	}

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}

	@Override public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public void tick()
	{
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
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_FAR, 10.0F);
		this.timeDying++;
		if (this.timeDying > 140) {this.tryDying(this.lastDamageSource == null ? this.damageSources().generic() : this.lastDamageSource);}
	}

	@Override public void tickDeadPhase() {this.tickDyingPhase();}

	@Override public Item getTrophy() {return AerialHellItems.VOLUCITE_ORE.get();}

	protected void runTransitionEffect()
	{
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_NEAR, 64.0F);
	}
	
	@Override public boolean isPushable() {return false;}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}
    protected SoundEvent getFastDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}

	public void playDeathSound() {this.playSound(this.getDeathSound(), 5.0F, 1.0F);}
	@Override public void playFastDeathSound() {this.playSound(getFastDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
	public void playTransitionSound() {this.playSound(AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_ACTIVATION.get(), 5.0F, 1.0F);}

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
}
