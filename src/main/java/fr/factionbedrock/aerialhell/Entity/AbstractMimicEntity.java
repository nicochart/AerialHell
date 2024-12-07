package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractMimicEntity extends PathAwareEntity
{
	public AbstractMimicEntity(EntityType<? extends AbstractMimicEntity> type, World world)
	{
		super(type, world);
		this.experiencePoints = 10;
	}

	@Override
	protected void initGoals()
	{
		this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(5, new LookAroundGoal(this));
		this.goalSelector.add(2,  new MeleeAttackGoal(this, 1.0, false));
		this.targetSelector.add(1, new RevengeGoal(this));
		this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public boolean damage(DamageSource source, float amount)
	{
		for (int i=0; i<12; i++)
		{
			double x = this.getX(),y = this.getY() + this.getStandingEyeHeight() / 1.5,z = this.getZ();
			double dx = random.nextFloat() - 0.5F, dy = random.nextFloat() - 0.5F, dz = random.nextFloat() - 0.5F;
			this.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, getMimicBlock().getDefaultState()), x, y, z, dx, dy, dz);
		}
		boolean flag = super.damage(source, amount);
		if (flag)
		{
			if (source.getSource() instanceof LivingEntity attacker)
			{
				if (attacker instanceof PlayerEntity && !((PlayerEntity)attacker).isCreative()) {this.setTarget(attacker);}
			}
		}
		return flag;
	}

	@Override
	public boolean tryAttack(Entity target)
	{
		boolean flag = super.tryAttack(target);
		if (flag && target instanceof LivingEntity)
		{
			if (((LivingEntity) target).getHealth() <= 0.0) {this.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 1.0F);}
			else {this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F + random.nextFloat());}
		}
		return flag;
	}

	abstract protected Block getMimicBlock();
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.BLOCK_WOOD_BREAK;}
}
