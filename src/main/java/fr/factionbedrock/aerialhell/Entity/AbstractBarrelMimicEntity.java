package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractBarrelMimicEntity extends CreatureEntity
{
	public AbstractBarrelMimicEntity(EntityType<? extends AbstractBarrelMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.experienceValue = 10;
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
	
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.BLOCK_WOOD_BREAK;}
	@Override protected SoundEvent getDeathSound() {return SoundEvents.BLOCK_BARREL_CLOSE;}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			LivingEntity attacker = (LivingEntity) source.getImmediateSource();
			if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).isCreative()) {this.setAttackTarget(attacker);}
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void spawnExplosionParticle()
	{
		if (this.world.isRemote)
		{
			for(int i = 0; i < 15; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.POOF, this.getPosXWidth(0.0D) - d0 * 9.0D, this.getPosYRandom() - d1 * 9.0D, this.getPosZRandom(1.0D) - d2 * 9.0D, d0, d1, d2);
			}
		}
		else {this.world.setEntityState(this, (byte)20);}
	}
}
