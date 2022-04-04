package fr.factionbedrock.aerialhell.Entity.Bosses;

import fr.factionbedrock.aerialhell.Entity.Monster.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MudCycleMageEntity extends MudSoldierEntity
{
	public static final DataParameter<Boolean> MUD_CYCLE_MAGE_ACTIVE = EntityDataManager.createKey(MudCycleMageEntity.class, DataSerializers.BOOLEAN);
	
	public MudCycleMageEntity(EntityType<? extends MudSoldierEntity> type, World world)
	{
		super(type, world);
	}
	
	@Override
    protected void registerGoals()
    {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	    this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new MageWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(6, new MageLookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new MageNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TornSpiritEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(MUD_CYCLE_MAGE_ACTIVE, false);
	}
    
    public void setActive(boolean isActive)
	{
		this.dataManager.set(MUD_CYCLE_MAGE_ACTIVE, isActive);
	}
	
	public boolean isActive()
	{
		return this.dataManager.get(MUD_CYCLE_MAGE_ACTIVE);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 200.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
	      return false;
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
			this.setActive(false);
		}
		
		if (isActive() && (this.ticksExisted % 600 == 0 || (this.ticksExisted % 300 == 0 && rand.nextInt(2) == 0)))
		{
			if (this.getHealth() < this.getMaxHealth() / 2)
			{
				this.summonSpectralSoldiersAndGolems();
			}
			else
			{
				this.summonSpectralSoldiers();
			}
		}
	}
	
	private void summonSpectralSoldiers()
	{		
		double x,y,z;
		x = this.getPosX(); y = this.getPosY(); z = this.getPosZ();
		
		MudSpectralSoldierEntity spectralSoldier1 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		MudSpectralSoldierEntity spectralSoldier2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		MudSpectralSoldierEntity spectralSoldier3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		spectralSoldier1.setPosition(x, y, z);
		spectralSoldier2.setPosition(x, y, z);
		spectralSoldier3.setPosition(x, y, z);
		spectralSoldier1.setMotion(0.5, 0, 0);//(1, 0, 0);
		spectralSoldier2.setMotion(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralSoldier3.setMotion(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		spectralSoldier1.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier2.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier3.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		spectralSoldier1.setCombatTask();
		spectralSoldier2.setCombatTask();
		spectralSoldier3.setCombatTask();
		
		for (int i=0; i<30; i++) this.world.addParticle(ParticleTypes.LARGE_SMOKE, x + rand.nextFloat() - 0.5, y + 2 * rand.nextFloat(), z + rand.nextFloat(), 0.25 * (rand.nextFloat()) - 0.5, 0.3D, 0.25 * (rand.nextFloat() - 0.5));
		this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + rand.nextFloat() * 0.1F);
		this.world.addEntity(spectralSoldier1);
		this.world.addEntity(spectralSoldier2);
		this.world.addEntity(spectralSoldier3);
	}
	
	private void summonSpectralSoldiersAndGolems()
	{		
		double x,y,z;
		x = this.getPosX(); y = this.getPosY(); z = this.getPosZ();
		
		LivingEntity spectralEntity1;
		LivingEntity spectralEntity2;
		LivingEntity spectralEntity3;
		
		if (rand.nextInt(2) == 0)
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		}
		else
		{
			spectralEntity1 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity2 = AerialHellEntities.MUD_SPECTRAL_GOLEM_TYPE.create(this.world);
			spectralEntity3 = AerialHellEntities.MUD_SPECTRAL_SOLDIER_TYPE.create(this.world);
		}
		spectralEntity1.setPosition(x, y, z);
		spectralEntity2.setPosition(x, y, z);
		spectralEntity3.setPosition(x, y, z);
		spectralEntity1.setMotion(0.5, 0, 0);//(1, 0, 0);
		spectralEntity2.setMotion(-0.250000112583355, 0, 0.4333882291756956);//(-0.50000022516671, 0, 0.8667764583513912);
		spectralEntity3.setMotion(-0.250000112583355, 0, -0.4333882291756956);//(-0.50000022516671, 0, -0.8667764583513912);
		
		for (int i=0; i<30; i++) this.world.addParticle(ParticleTypes.LARGE_SMOKE, x + rand.nextFloat() - 0.5, y + 2 * rand.nextFloat(), z + rand.nextFloat(), 0.25 * (rand.nextFloat()) - 0.5, 0.3D, 0.25 * (rand.nextFloat() - 0.5));
		this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + rand.nextFloat() * 0.1F);
		this.world.addEntity(spectralEntity1);
		this.world.addEntity(spectralEntity2);
		this.world.addEntity(spectralEntity3);
	}
	
	public static class MageNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
	{
		private final MudCycleMageEntity mage;
		
		public MageNearestAttackableTargetGoal(MudCycleMageEntity mageIn, Class<T> targetClassIn, boolean checkSight)
		{
			super(mageIn, targetClassIn, checkSight);
			this.mage = mageIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.mage.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.mage.isActive() && super.shouldContinueExecuting();
		}
		
	}
	
	public static class MageLookRandomlyGoal extends LookRandomlyGoal
	{
		private final MudCycleMageEntity mage;
		
		public MageLookRandomlyGoal(MudCycleMageEntity mage)
		{
			super(mage);
			this.mage = mage;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.mage.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.mage.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class MageLookAtPlayerGoal extends LookAtGoal
	{
		private final MudCycleMageEntity mage;
		
		public MageLookAtPlayerGoal(MudCycleMageEntity mageIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance)
		{
			super(mageIn, watchTargetClass, maxDistance);
			this.mage = mageIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.mage.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.mage.isActive() && super.shouldContinueExecuting();
		}
	}
	
	public static class MageWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		private final MudCycleMageEntity mage;
		
		public MageWaterAvoidingRandomWalkingGoal(MudCycleMageEntity mageIn, double speedIn)
		{
			super(mageIn, speedIn);
			this.mage = mageIn;
		}
		
		//Returns whether the EntityAIBase should begin execution.
		@Override
		public boolean shouldExecute()
		{
			return this.mage.isActive() && super.shouldExecute();
		}
		
		//Returns whether an in-progress EntityAIBase should continue executing
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.mage.isActive() && super.shouldContinueExecuting();
		}
	}
}
