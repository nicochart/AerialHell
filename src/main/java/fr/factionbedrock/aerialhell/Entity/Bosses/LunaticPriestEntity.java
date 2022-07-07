package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.EnumSet;
import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LunaticPriestEntity extends AbstractBossEntity
{
	public int attackTimer;
	private int lunaticProjectileTimer;
	
	public static final DataParameter<Boolean> SECOND_PHASE = EntityDataManager.createKey(LunaticPriestEntity.class, DataSerializers.BOOLEAN);
	
	public LunaticPriestEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		if (this.getHealth() >= getMaxHealthForPhase2()) {this.updateToPhase1();}
		else {this.updateToPhase2();}
		this.attackTimer = 0;
		this.lunaticProjectileTimer = 80;
		bossInfo.setColor(BossInfo.Color.YELLOW);
		bossInfo.setOverlay(BossInfo.Overlay.NOTCHED_6);
	}
	
	@Override
    protected void registerGoals()
    {
		/*Phase 1 only*/
		this.goalSelector.addGoal(5, new LunaticPriestEntity.PriestRandomFlyGoal(this));
		this.goalSelector.addGoal(7, new LunaticPriestEntity.PriestLookAroundGoal(this));
		this.goalSelector.addGoal(6, new PriestLookRandomlyGoal(this));
		/*Phase 2 only*/
	    this.goalSelector.addGoal(4, new PriestWaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(5, new BossLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
	    /*Both phases*/
	    this.goalSelector.addGoal(2, new LunaticPriestEntity.LunaticProjectileAttackGoal(this));
	    this.goalSelector.addGoal(3, new BossMeleeAttackGoal(this, 1.25D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new BossNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public float getMaxHealthForPhase2() {return this.getMaxHealth() / 2;}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(SECOND_PHASE, false);
	}
	
	@Override
	public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		if (!isActive)
		{
			this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOW_FALLING, 120, 2, true, false)));
		}
	}
	
	public boolean isInPhase1() {return !this.dataManager.get(SECOND_PHASE);}
	public boolean isInPhase2() {return this.dataManager.get(SECOND_PHASE);}
	public boolean shouldUpdateToPhase1() {return this.getHealth() >= getMaxHealthForPhase2() && this.isInPhase2();}
	public boolean shouldUpdateToPhase2() {return this.getHealth() < getMaxHealthForPhase2() && this.isInPhase1();}
	
	private void updateToPhase1()
	{
		this.dataManager.set(SECOND_PHASE, false);
		this.moveController =  new LunaticPriestEntity.PriestMoveHelperController(this);
		this.setMotion(this.getMotion().add(0,2,0));
	}
	
	private void updateToPhase2()
	{
		this.dataManager.set(SECOND_PHASE, true);
		this.moveController = new MovementController(this);
		this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOW_FALLING, 120, 2, true, false)));
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			if (source.getTrueSource() instanceof LivingEntity) {this.setAttackTarget((LivingEntity) source.getTrueSource());}
		}
		return flag;
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 600.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.27D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.05D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D);
    }
	
	@Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
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
		if (this.shouldUpdateToPhase1() && this.isActive() && this.timeWithoutAnyTarget == 0)
		{
			this.updateToPhase1();
		}
		if (this.shouldUpdateToPhase2() && this.isActive() && this.timeWithoutAnyTarget == 0)
		{
			this.updateToPhase2();
		}
		
		if (this.timeWithoutAnyTarget > 0 && this.isInPhase1())
		{
			this.updateToPhase2();
		}
	}
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		if (this.lunaticProjectileTimer > 0) {this.lunaticProjectileTimer--;} else if (this.lunaticProjectileTimer < 0) {this.lunaticProjectileTimer++;}
		super.livingTick();
    }
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		if (isInPhase1())
		{
			return false;
		}
		else
		{
			return super.onLivingFall(distance, damageMultiplier);
		}
	}
	
	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}
	
	/*copied from net.minecraft.entity.FlyingEntity*/
	@Override
	public void travel(Vector3d travelVector)
	{
		if (isInPhase1())
		{
			if (isActive())
			{
				if (this.isInWater())
			    {
			    	this.moveRelative(0.02F, travelVector);
			        this.move(MoverType.SELF, this.getMotion());
			        this.setMotion(this.getMotion().scale((double)0.8F));
			    }
				else if (this.isInLava())
				{
			        this.moveRelative(0.02F, travelVector);
			        this.move(MoverType.SELF, this.getMotion());
			        this.setMotion(this.getMotion().scale(0.5D));
			    }
				else
				{
			        BlockPos ground = new BlockPos(this.getPosX(), this.getPosY() - 1.0D, this.getPosZ());
			        float f = 0.91F;
			        if (this.onGround)
			        {
			        	f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
			        }
			        float f1 = 0.16277137F / (f * f * f);
			        f = 0.91F;
			        if (this.onGround)
			        {
			            f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
			        }
		
			        this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, travelVector);
			        this.move(MoverType.SELF, this.getMotion());
			        this.setMotion(this.getMotion().scale((double)f));
				}
				this.func_233629_a_(this, false);
			}
		}
		else
		{
			super.travel(travelVector);
		}
	}
	
	@Override
	public boolean isOnLadder()
	{
		if (isInPhase1() && isActive())
		{
			return false;
		}
		else
		{
			return super.isOnLadder();
		}
	}
	
	/*
	 * Goals
	 */

	/* Same as net.minecraft.entity.monster.GhastEntity.RandomFlyGoal but changed GhastEntity to LunaticPriestEntity */
	static class PriestRandomFlyGoal extends Goal
	{
		private final LunaticPriestEntity priest;

		public PriestRandomFlyGoal(LunaticPriestEntity priestIn)
		{
			this.priest = priestIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override
		public boolean shouldExecute()
		{
			if (!priest.isActive() || priest.isInPhase2())
			{
				return false;
			}
			MovementController movementcontroller = this.priest.getMoveHelper();
			if (!movementcontroller.isUpdating())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getX() - this.priest.getPosX();
				double d1 = movementcontroller.getY() - this.priest.getPosY();
				double d2 = movementcontroller.getZ() - this.priest.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0 || d3 > 3600.0;
			}
		}

		@Override
		public boolean shouldContinueExecuting()
		{
			return false;
		}

		@Override
		public void startExecuting()
		{
			Random random = this.priest.getRNG();
			double d0 = this.priest.getPosX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.priest.getPosY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.priest.getPosZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.priest.getMoveHelper().setMoveTo(d0, d1, d2, 1.0);
		}
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.MoveHelperController but changed GhastEntity to LunaticPriestEntity */
	static class PriestMoveHelperController extends MovementController
	{
		private final LunaticPriestEntity priest;
		private int courseChangeCooldown;

		public PriestMoveHelperController(LunaticPriestEntity priest)
		{
			super(priest);
			this.priest = priest;
		}

		@Override
		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.priest.getRNG().nextInt(5) + 2;
					Vector3d vec3d = new Vector3d(this.posX - this.priest.getPosX(), this.posY - this.priest.getPosY(), this.posZ - this.priest.getPosZ());
					double d0 = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.isNotColliding(vec3d, MathHelper.ceil(d0)))
					{
						this.priest.setMotion(this.priest.getMotion().add(vec3d.scale(0.1)));
					}
					else
					{
						this.action = MovementController.Action.WAIT;
					}
				}
			}
		}

		private boolean isNotColliding(Vector3d pos, int distance)
		{
			AxisAlignedBB axisalignedbb = this.priest.getBoundingBox();
			for (int i = 1; i < distance; ++i)
			{
				axisalignedbb = axisalignedbb.offset(pos);
				if (!this.priest.world.hasNoCollisions(this.priest, axisalignedbb)) {return false;}
			}
			return true;
		}
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.LookAroundGoal but changed GhastEntity to LunaticPriestEntity */
	static class PriestLookAroundGoal extends Goal
	{
		private final LunaticPriestEntity priest;

		public PriestLookAroundGoal(LunaticPriestEntity priest)
		{
			this.priest = priest;
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean shouldExecute()
		{
			return priest.isActive() && priest.isInPhase1();
		}

		@Override
		public void tick()
		{				
			if (this.priest.getAttackTarget() == null)
			{
				Vector3d vec3d = this.priest.getMotion();
				this.priest.rotationYaw = -((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180.0F / (float)Math.PI);
				this.priest.renderYawOffset = this.priest.rotationYaw;
			}
			else
			{
				LivingEntity livingentity = this.priest.getAttackTarget();
				if (livingentity.getDistanceSq(this.priest) < 64*64)
				{
					double x = livingentity.getPosX() - this.priest.getPosX();
					double z = livingentity.getPosZ() - this.priest.getPosZ();
					this.priest.rotationYaw = -((float)MathHelper.atan2(x, z)) * (180.0F / (float)Math.PI);
					this.priest.renderYawOffset = this.priest.rotationYaw;
				}
			}
		}
	}
	
	static class LunaticProjectileAttackGoal extends Goal
	{
		private final LunaticPriestEntity priest;
	    private int projectileCount;

	    public LunaticProjectileAttackGoal(LunaticPriestEntity priestIn)
	    {
	    	this.priest = priestIn;
	        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean shouldExecute()
	    {
	    	if (!this.priest.isActive())
	    	{
	    		return false;
	    	}
	    	LivingEntity target = this.priest.getAttackTarget();
	        return target != null && priest.canEntityBeSeen(target) && priest.lunaticProjectileTimer == 0 && target.isAlive() && this.priest.canAttack(target);
	    }
	    
	    public void startExecuting() {this.projectileCount = 0;}
	    
	    public void resetTask() {this.projectileCount = 0;}

	    public void tick()
	    {
	    	LivingEntity target = this.priest.getAttackTarget();
	    	
	    	double Xdistance = target.getPosX() - this.priest.getPosX();
            double Ydistance = target.getPosYHeight(0.5D) - this.priest.getPosYHeight(0.5D);
            double Zdistance = target.getPosZ() - this.priest.getPosZ();
            
            float inaccuracy = 0.0f;
            if (priest.isInPhase2())
            {
            	inaccuracy = 0.3f;
            }
            
	        if (projectileCount < 1)
	        {
	        	 ++this.projectileCount;
	        	 LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(this.priest.world, this.priest, Xdistance, Ydistance, Zdistance, 0.7f + this.priest.rand.nextFloat(), inaccuracy);
                 lunaticProjectileEntity.setPosition(lunaticProjectileEntity.getPosX(), this.priest.getPosYHeight(0.5D) + 0.5D, lunaticProjectileEntity.getPosZ());
                 this.priest.world.addEntity(lunaticProjectileEntity);
	        }
	        if (priest.isInPhase1())
	        {
	        	priest.lunaticProjectileTimer = 40 + (int) (priest.rand.nextFloat()) * 20;
	        }
	        else
	        {
	        	priest.lunaticProjectileTimer = 30 + (int) (priest.rand.nextFloat()) * 10;
	        }
	        super.tick();
	    }
	}
	
	public static class PriestLookRandomlyGoal extends BossLookRandomlyGoal
	{		
		public PriestLookRandomlyGoal(LunaticPriestEntity priestIn) {super(priestIn);}
		@Override public boolean shouldExecute() {return ((LunaticPriestEntity) this.boss).isInPhase2() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return ((LunaticPriestEntity) this.boss).isInPhase2() && super.shouldContinueExecuting();}
	}
	
	public static class PriestWaterAvoidingRandomWalkingGoal extends BossWaterAvoidingRandomWalkingGoal
	{
		public PriestWaterAvoidingRandomWalkingGoal(LunaticPriestEntity priestIn, double speedIn) {super(priestIn, speedIn);}
		@Override public boolean shouldExecute() {return ((LunaticPriestEntity) this.boss).isInPhase2() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return ((LunaticPriestEntity) this.boss).isInPhase2() && super.shouldContinueExecuting();}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_DEATH.get();}
    @Override protected float getSoundVolume() {return 2.5F;}
}
