package fr.factionbedrock.aerialhell.Entity.Bosses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LunaticPriestEntity extends MonsterEntity
{
	public int attackTimer;
	
	public static final DataParameter<Boolean> LUNATIC_PRIEST_ACTIVE = EntityDataManager.createKey(LunaticPriestEntity.class, DataSerializers.BOOLEAN);
	
	public LunaticPriestEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		this.attackTimer = 0;
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(3, new PriestMeleeAttackGoal(this, 1.25D, false));
	    this.goalSelector.addGoal(4, new PriestWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new PriestLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(6, new PriestLookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new PriestNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(LUNATIC_PRIEST_ACTIVE, false);
	}
    
    public void setActive(boolean isActive)
	{
		this.dataManager.set(LUNATIC_PRIEST_ACTIVE, isActive);
	}
	
	public boolean isActive()
	{
		return this.dataManager.get(LUNATIC_PRIEST_ACTIVE);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 400.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.27D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.05D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D);
    }
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
	      return false;
	}
	
	@Override
	public boolean isImmuneToFire()
	{
		return true;
	}
	
	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			this.setActive(true);
			this.recentlyHit = 100;
		}
		return flag;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity attackedEntity)
	{
	      this.world.setEntityState(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float f1 = (int)f > 0 ? f / 2.0F + (float)this.rand.nextInt((int)f) : f;
	      boolean flag = attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
	      if (flag)
	      {
	         attackedEntity.setMotion(attackedEntity.getMotion().getX(), (double)0.4F, attackedEntity.getMotion().getZ());
	         this.applyEnchantments(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else
		{
	         super.handleStatusUpdate(id);
	    }
	}
	
	@Override
	public void tick()
	{		
		super.tick();
		if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 8.0, EntityPredicates.CAN_AI_TARGET) != null)
		{
			this.setActive(true);
		}
		else if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 32.0, EntityPredicates.CAN_AI_TARGET) == null)
		{
			if (this.recentlyHit <= 0)
			{
				this.setActive(false);
			}
		}
		
		if (isActive() && (this.ticksExisted % 600 == 0 || (this.ticksExisted % 300 == 0 && rand.nextInt(2) == 0)))
		{
			if (this.getHealth() < this.getMaxHealth() / 2)
			{
				//this.summonSpectralSoldiersAndGolems();
			}
			else
			{
				//this.summonSpectralSoldiers();
			}
			if (this.world.isRemote)
			{
				//this.spawnSmokeParticle();
			}
			this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + rand.nextFloat() * 0.1F);
		}
	}
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
	public static class PriestNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
	{
		private final LunaticPriestEntity priest;
		
		public PriestNearestAttackableTargetGoal(LunaticPriestEntity priestIn, Class<T> targetClassIn, boolean checkSight)
		{
			super(priestIn, targetClassIn, checkSight);
			this.priest = priestIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.priest.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.priest.isActive() && super.shouldContinueExecuting();
		}
		
	}
	
	public static class PriestLookRandomlyGoal extends LookRandomlyGoal
	{
		private final LunaticPriestEntity priest;
		
		public PriestLookRandomlyGoal(LunaticPriestEntity priestIn)
		{
			super(priestIn);
			this.priest = priestIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.priest.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.priest.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class PriestLookAtPlayerGoal extends LookAtGoal
	{
		private final LunaticPriestEntity priest;
		
		public PriestLookAtPlayerGoal(LunaticPriestEntity priestIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
		{
			super(priestIn, watchTargetClass, maxDistance);
			this.priest = priestIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.priest.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.priest.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class PriestWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		private final LunaticPriestEntity priest;
		
		public PriestWaterAvoidingRandomWalkingGoal(LunaticPriestEntity priestIn, double speedIn)
		{
			super(priestIn, speedIn);
			this.priest = priestIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.priest.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.priest.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class PriestMeleeAttackGoal extends MeleeAttackGoal
	{
		private final LunaticPriestEntity priest;
		
		public PriestMeleeAttackGoal(LunaticPriestEntity priestIn, double speedIn, boolean useLongMemory)
		{
			super(priestIn, speedIn, useLongMemory);
			this.priest = priestIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.priest.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.priest.isActive() && super.shouldContinueExecuting();
		}
	}
}
