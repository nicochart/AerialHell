package fr.factionbedrock.aerialhell.Entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import java.util.List;

public class AbstractBossEntity extends MonsterEntity
{
	protected final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS));
	protected int timeWithoutAnyTarget;
	
	public static final DataParameter<Boolean> BOSS_ACTIVE = EntityDataManager.createKey(AbstractBossEntity.class, DataSerializers.BOOLEAN);
	
	public AbstractBossEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		this.timeWithoutAnyTarget = 0;
	}
	
	/* Add the given player to the list of players tracking this entity. For instance, a player may track a boss in order to view its associated boss bar. */
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player)
	{
    	super.addTrackingPlayer(player);
    	this.bossInfo.addPlayer(player);
    }

    /* Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for more information on tracking. */
	@Override
    public void removeTrackingPlayer(ServerPlayerEntity player)
    {
    	super.removeTrackingPlayer(player);
    	this.bossInfo.removePlayer(player);
    }
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	      super.readAdditional(compound);
	      if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
	}
	
	@Override
	public void setCustomName(@Nullable ITextComponent name)
	{
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	}
	
	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(BOSS_ACTIVE, false);
	}

	public void adaptBossDifficulty()
	{
		List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(30), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 15));
		int difficulty = -1;
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof PlayerEntity) {difficulty += 1;}
		}
		if (this.isPotionActive(Effects.RESISTANCE)) {this.removePotionEffect(Effects.RESISTANCE);}
		if (this.isPotionActive(Effects.STRENGTH)) {this.removePotionEffect(Effects.STRENGTH);}
		if (difficulty > 0) //is 0 if there is only one player, +1 per additional player
		{
			this.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 54000, Math.min(4,difficulty * 2), false, false));
			this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 54000, Math.min(4, difficulty - 1), false, false));
		}
	}
    
    public void setActive(boolean isActive) {this.dataManager.set(BOSS_ACTIVE, isActive);}
	
	public boolean isActive() {return this.dataManager.get(BOSS_ACTIVE);}
	
	@Override public boolean canDespawn(double distanceToClosestPlayer) {return false;}
	@Override public boolean isNonBoss() {return false;}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			this.setActive(true);
			this.adaptBossDifficulty();
			this.recentlyHit = 100;
		}
		return flag;
	}
	
	@Override
	public void tick()
	{		
		super.tick();		
		if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 8.0, EntityPredicates.CAN_AI_TARGET) != null)
		{
			this.setActive(true);
			this.adaptBossDifficulty();
			this.timeWithoutAnyTarget = 0;
		}
		else if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 48.0, EntityPredicates.CAN_AI_TARGET) == null)
		{			
			if (timeWithoutAnyTarget < 120) {timeWithoutAnyTarget++;}
			else if (this.recentlyHit <= 0 && timeWithoutAnyTarget == 120) {this.setActive(false);}
		}

		if (this.isActive() && this.ticksExisted % 900 == 0) {this.adaptBossDifficulty();}
	}
	
	/*
	 * Boss Goals
	 */

	public static class BossNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
	{
		protected final AbstractBossEntity boss;
		
		public BossNearestAttackableTargetGoal(AbstractBossEntity bossIn, Class<T> targetClassIn, boolean checkSight)
		{
			super(bossIn, targetClassIn, checkSight);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class BossLookRandomlyGoal extends LookRandomlyGoal
	{
		protected final AbstractBossEntity boss;
		
		public BossLookRandomlyGoal(AbstractBossEntity bossIn)
		{
			super(bossIn);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class BossLookAtPlayerGoal extends LookAtGoal
	{
		protected final AbstractBossEntity boss;
		
		public BossLookAtPlayerGoal(AbstractBossEntity bossIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
		{
			super(bossIn, watchTargetClass, maxDistance);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class BossWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		protected final AbstractBossEntity boss;
		
		public BossWaterAvoidingRandomWalkingGoal(AbstractBossEntity bossIn, double speedIn)
		{
			super(bossIn, speedIn);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class BossMeleeAttackGoal extends MeleeAttackGoal
	{
		protected final AbstractBossEntity boss;
		
		public BossMeleeAttackGoal(AbstractBossEntity bossIn, double speedIn, boolean useLongMemory)
		{
			super(bossIn, speedIn, useLongMemory);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class BossLeapAtTargetGoal extends LeapAtTargetGoal
	{
		protected final AbstractBossEntity boss;
		
		public BossLeapAtTargetGoal(AbstractBossEntity bossIn, float leapMotionYIn)
		{
			super(bossIn, leapMotionYIn);
			this.boss = bossIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.boss.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.boss.isActive() && super.shouldContinueExecuting();
		}
	}
}
