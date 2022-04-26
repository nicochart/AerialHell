package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AerialHellGolemEntity extends MonsterEntity
{
	public int attackTimer;
	
	public static final DataParameter<Boolean> GOLEM_ACTIVE = EntityDataManager.createKey(AerialHellGolemEntity.class, DataSerializers.BOOLEAN);
	
    public AerialHellGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
        this.attackTimer = 0;
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new GolemMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new GolemWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new GolemLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new GolemLookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    public float getAttackDamage()
    {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
    
    @Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(GOLEM_ACTIVE, false);
	}
    
    public void setActive(boolean isActive)
	{
		this.dataManager.set(GOLEM_ACTIVE, isActive);
	}
	
	public boolean isActive()
	{
		return this.dataManager.get(GOLEM_ACTIVE);
	}
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	float attackDamage = this.getAttackDamage();
    	this.world.setEntityState(this, (byte)4);
        float f1 = (int)attackDamage > 0 ? attackDamage / 2.0F + (float)this.rand.nextInt((int)attackDamage) : attackDamage;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag)
        {
           entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)this.getYMotionOnAttack(), 0.0D)); //projection en hauteur
           this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }
    
    public abstract float getYMotionOnAttack();
	
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
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15F, 0.5F);
    }
    
    public static class GolemMeleeAttackGoal extends MeleeAttackGoal
	{
		private final AerialHellGolemEntity golem;
		
		public GolemMeleeAttackGoal(AerialHellGolemEntity golemIn, double speedIn, boolean useLongMemory)
		{
			super(golemIn, speedIn, useLongMemory);
			this.golem = golemIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.golem.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.golem.isActive() && super.shouldContinueExecuting();
		}
		
	}
	
	public static class GolemLookRandomlyGoal extends LookRandomlyGoal
	{
		private final AerialHellGolemEntity golem;
		
		public GolemLookRandomlyGoal(AerialHellGolemEntity golemIn)
		{
			super(golemIn);
			this.golem = golemIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.golem.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.golem.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class GolemLookAtPlayerGoal extends LookAtGoal
	{
		private final AerialHellGolemEntity golem;
		
		public GolemLookAtPlayerGoal(AerialHellGolemEntity golemIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
		{
			super(golemIn, watchTargetClass, maxDistance);
			this.golem = golemIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.golem.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.golem.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class GolemWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		private final AerialHellGolemEntity golem;
		
		public GolemWaterAvoidingRandomWalkingGoal(AerialHellGolemEntity golemIn, double speedIn)
		{
			super(golemIn, speedIn);
			this.golem = golemIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.golem.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.golem.isActive() && super.shouldContinueExecuting();
		}
	}
}