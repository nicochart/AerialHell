package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public abstract class AbstractMimicEntity extends PathfinderMob
{
	public AbstractMimicEntity(EntityType<? extends AbstractMimicEntity> type, Level world)
	{
		super(type, world);
		this.xpReward = 10;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(2,  new MeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public boolean hurtServer(ServerLevel serverWorld, DamageSource source, float amount)
	{
		for (int i=0; i<12; i++)
		{
			double x = this.getX(),y = this.getY() + this.getEyeHeight() / 1.5,z = this.getZ();
			double dx = random.nextFloat() - 0.5F, dy = random.nextFloat() - 0.5F, dz = random.nextFloat() - 0.5F;
			this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, getMimicBlock().defaultBlockState()), x, y, z, dx, dy, dz);
		}
		boolean flag = super.hurtServer(serverWorld, source, amount);
		if (flag)
		{
			if (source.getDirectEntity() instanceof LivingEntity attacker)
			{
				if (attacker instanceof Player && !((Player)attacker).isCreative()) {this.setTarget(attacker);}
			}
		}
		return flag;
	}

	@Override
	public boolean doHurtTarget(ServerLevel serverWorld, Entity target)
	{
		boolean flag = super.doHurtTarget(serverWorld, target);
		if (flag && target instanceof LivingEntity)
		{
			if (((LivingEntity) target).getHealth() <= 0.0) {this.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);}
			else {this.playSound(SoundEvents.GENERIC_EAT.value(), 1.0F, 1.0F + random.nextFloat());}
		}
		return flag;
	}

	abstract protected Block getMimicBlock();
	@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WOOD_BREAK;}
}
