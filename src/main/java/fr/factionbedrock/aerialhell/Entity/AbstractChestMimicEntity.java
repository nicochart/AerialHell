package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public abstract class AbstractChestMimicEntity extends CreatureEntity
{
	public float mouthOpeningAmplitude;
	public float mouthOpeningFrequencyMalus;
	
	public AbstractChestMimicEntity(EntityType<? extends AbstractChestMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.SetRandomMouthOpeningAmplitudeAndFrequency();
		this.experienceValue = 10;
	}
	
	public void SetRandomMouthOpeningAmplitudeAndFrequency()
	{
		float max_amplitude = 0.7F;
		float min_amplitude = 0.3F;
		this.mouthOpeningAmplitude = min_amplitude + ((float) Math.random() * (max_amplitude - min_amplitude));
		float min_frequency_malus = 5.0F;
		float max_frequency_malus = 11.0F;
		this.mouthOpeningFrequencyMalus = min_frequency_malus + ((float) Math.random() * (max_frequency_malus - min_frequency_malus));
	}

	public AbstractChestMimicEntity(World worldIn)
	{
		super(AerialHellEntities.AERIAL_TREE_MIMIC.get(), worldIn);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(2,  new MeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.BLOCK_WOOD_BREAK;
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.BLOCK_CHEST_CLOSE;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (source.getImmediateSource() instanceof AbstractChestMimicEntity)
		{
			return false;
		}
		if (source.getImmediateSource() instanceof LivingEntity && this.hurtTime == 0)
		{
			if (this.world instanceof ServerWorld)
			{
				((ServerWorld) this.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.CHEST.getDefaultState()), this.getPosX(), this.getPosY() + this.getHeight() / 1.5, this.getPosZ(), 20, this.getWidth() / 4.0, this.getHeight() / 4.0, this.getWidth() / 4.0, 0.05);
			}
			
			LivingEntity attacker = (LivingEntity) source.getImmediateSource();
			if (!(attacker instanceof PlayerEntity) || !((PlayerEntity) attacker).isCreative())
			{
				this.setAttackTarget(attacker);
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean result = super.attackEntityAsMob(entityIn);
		
		if (entityIn instanceof LivingEntity)
		{
			// If the entity died as a result of this attack, then play the burp sound. Otherwise, play the eating sound.
			SoundEvent sound = (((LivingEntity) entityIn).getHealth() <= 0.0)? SoundEvents.ENTITY_PLAYER_BURP : SoundEvents.ENTITY_GENERIC_EAT;
			this.playSound(sound, 1.0F, this.getSoundPitch());
		}
		
		return result;
	}

	@Override
	public void spawnExplosionParticle()
	{
		if (this.world.isRemote)
		{
			for(int i = 0; i < 20; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.POOF, this.getPosXWidth(0.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, d0, d1, d2);
			}
		}
		else
		{
			this.world.setEntityState(this, (byte)20);
		}
	}
}
