package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MudGolemEntity extends AerialHellHostileEntity
{
	public static final DataParameter<Boolean> MUD_GOLEM_ACTIVE = EntityDataManager.createKey(CrystalSlimeEntity.class, DataSerializers.BOOLEAN);
	private float timeClosePlayer = 0.0F;
	
    public MudGolemEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(1, new GolemMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(2, new GolemWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new GolemLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new GolemLookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
    }

    private float getAttackDamage()
    {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
    
    @Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(MUD_GOLEM_ACTIVE, false);
	}
    
    public void setActive(boolean isAwake)
	{
		this.dataManager.set(MUD_GOLEM_ACTIVE, isAwake);
	}
	
	public boolean isActive()
	{
		return this.dataManager.get(MUD_GOLEM_ACTIVE);
	}
	
	@Override
	public void tick()
	{
		if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), 16.0, EntityPredicates.CAN_AI_TARGET) != null)
		{
			if (!this.isActive() && this.timeClosePlayer >= 50)
			{
				this.setActive(true);
			}
			this.timeClosePlayer++;
		}
		else
		{
			if (timeClosePlayer == 0)
			{
				this.setActive(false);
			}
			else
			{
				this.timeClosePlayer--;
			}
		}
		super.tick();
	}
	
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	float attackDamage = this.getAttackDamage();
        float f1 = (int)attackDamage > 0 ? attackDamage / 2.0F + (float)this.rand.nextInt((int)attackDamage) : attackDamage;
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
        if (flag)
        {
           entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D)); //projection en hauteur
           this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
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
		private final MudGolemEntity golem;
		
		public GolemMeleeAttackGoal(MudGolemEntity golemIn, double speedIn, boolean useLongMemory)
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
		private final MudGolemEntity golem;
		
		public GolemLookRandomlyGoal(MudGolemEntity golemIn)
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
		private final MudGolemEntity golem;
		
		public GolemLookAtPlayerGoal(MudGolemEntity golemIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
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
		private final MudGolemEntity golem;
		
		public GolemWaterAvoidingRandomWalkingGoal(MudGolemEntity golemIn, double speedIn)
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