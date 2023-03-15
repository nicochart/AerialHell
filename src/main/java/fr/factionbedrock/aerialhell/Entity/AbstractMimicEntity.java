package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractMimicEntity extends CreatureEntity
{
	public AbstractMimicEntity(EntityType<? extends AbstractMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.experienceValue = 10;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2,  new MeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		for (int i=0; i<12; i++)
		{
			double x = this.getPosX(),y = this.getPosY() + this.getHeight() / 1.5,z = this.getPosZ();
			double dx = rand.nextFloat() - 0.5F, dy = rand.nextFloat() - 0.5F, dz = rand.nextFloat() - 0.5F;
			world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, getMimicBlock().getDefaultState()), x, y, z, dx, dy, dz);
		}
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			if (source.getImmediateSource() instanceof LivingEntity)
			{
				LivingEntity attacker = (LivingEntity) source.getImmediateSource();
				if (attacker instanceof PlayerEntity && !((PlayerEntity)attacker).isCreative()) {this.setAttackTarget(attacker);}
			}
		}
		return flag;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = super.attackEntityAsMob(entityIn);
		if (flag && entityIn instanceof LivingEntity)
		{
			if (((LivingEntity) entityIn).getHealth() <= 0.0) {this.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 1.0F);}
			else {this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F + rand.nextFloat());}
		}
		return flag;
	}

	abstract protected Block getMimicBlock();
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.BLOCK_WOOD_BREAK;}
}
