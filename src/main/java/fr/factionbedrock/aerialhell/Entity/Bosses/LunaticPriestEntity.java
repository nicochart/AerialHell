package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.EnumSet;
import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LunaticPriestEntity extends AbstractBossEntity
{
	public int attackTimer;
	private int lunaticProjectileTimer;
	
	public static final EntityDataAccessor<Boolean> SECOND_PHASE = SynchedEntityData.defineId(LunaticPriestEntity.class, EntityDataSerializers.BOOLEAN);
	
	public LunaticPriestEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		if (this.getHealth() >= getMaxHealthForPhase2()) {this.updateToPhase1();}
		else {this.updateToPhase2();}
		this.attackTimer = 0;
		this.lunaticProjectileTimer = 80;
		bossInfo.setColor(BossEvent.BossBarColor.YELLOW);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
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
	    this.goalSelector.addGoal(5, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
	    /*Both phases*/
	    this.goalSelector.addGoal(2, new LunaticPriestEntity.LunaticProjectileAttackGoal(this));
	    this.goalSelector.addGoal(3, new ActiveMeleeAttackGoal(this, 1.25D, false));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
	    this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public float getMaxHealthForPhase2() {return this.getMaxHealth() / 2;}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(SECOND_PHASE, false);
	}
	
	@Override
	public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		if (!isActive)
		{
			this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false)));
		}
	}
	
	public boolean isInPhase1() {return !this.entityData.get(SECOND_PHASE);}
	public boolean isInPhase2() {return this.entityData.get(SECOND_PHASE);}
	public boolean shouldUpdateToPhase1() {return this.getHealth() >= getMaxHealthForPhase2() && this.isInPhase2();}
	public boolean shouldUpdateToPhase2() {return this.getHealth() < getMaxHealthForPhase2() && this.isInPhase1();}
	
	private void updateToPhase1()
	{
		this.entityData.set(SECOND_PHASE, false);
		this.moveControl =  new LunaticPriestEntity.PriestMoveHelperController(this);
		this.setDeltaMovement(this.getDeltaMovement().add(0,2,0));
	}
	
	private void updateToPhase2()
	{
		this.entityData.set(SECOND_PHASE, true);
		this.moveControl = new MoveControl(this);
		this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 2, true, false)));
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = super.hurt(source, amount);
		if (flag)
		{
			if (source.getEntity() instanceof LivingEntity)
			{
				if (!(source.getEntity() instanceof Player && ((Player)source.getEntity()).isCreative()))
				{
					this.setTarget((LivingEntity) source.getEntity());
				}
			}
		}
		return flag;
	}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 600.0D)
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.27D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.05D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D);
    }
	
	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override
	public boolean doHurtTarget(Entity attackedEntity)
	{
	      this.level.broadcastEntityEvent(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
	      boolean flag = attackedEntity.hurt(DamageSource.mobAttack(this), f1);
	      if (flag)
	      {
	         attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.4F, attackedEntity.getDeltaMovement().z);
	         this.doEnchantDamageEffects(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleEntityEvent(id);}
	}
	
	@Override public void tick()
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
	
	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		if (this.lunaticProjectileTimer > 0) {this.lunaticProjectileTimer--;} else if (this.lunaticProjectileTimer < 0) {this.lunaticProjectileTimer++;}
		super.aiStep();
    }
	
	@Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
	{
		if (isInPhase1()) {return false;}
		else {return super.causeFallDamage(distance, damageMultiplier, source);}
	}
	
	@Override protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}
	
	/*copied from net.minecraft.entity.FlyingEntity*/
	@Override public void travel(Vec3 travelVector)
	{
		if (isInPhase1())
		{
			if (isActive())
			{
				if (this.isInWater())
			    {
			    	this.moveRelative(0.02F, travelVector);
			        this.move(MoverType.SELF, this.getDeltaMovement());
			        this.setDeltaMovement(this.getDeltaMovement().scale((double)0.8F));
			    }
				else if (this.isInLava())
				{
			        this.moveRelative(0.02F, travelVector);
			        this.move(MoverType.SELF, this.getDeltaMovement());
			        this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			    }
				else
				{
			        BlockPos ground = new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ());
			        float f = 0.91F;
			        if (this.onGround)
			        {
			        	f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
			        }
			        float f1 = 0.16277137F / (f * f * f);
			        f = 0.91F;
			        if (this.onGround)
			        {
			            f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
			        }
		
			        this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, travelVector);
			        this.move(MoverType.SELF, this.getDeltaMovement());
			        this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
				}
				this.calculateEntityAnimation(this, false);
			}
		}
		else {super.travel(travelVector);}
	}
	
	@Override public boolean onClimbable()
	{
		if (isInPhase1() && isActive()) {return false;}
		else {return super.onClimbable();}
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
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override
		public boolean canUse()
		{
			if (!priest.isActive() || priest.isInPhase2())
			{
				return false;
			}
			MoveControl movementcontroller = this.priest.getMoveControl();
			if (!movementcontroller.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getWantedX() - this.priest.getX();
				double d1 = movementcontroller.getWantedY() - this.priest.getY();
				double d2 = movementcontroller.getWantedZ() - this.priest.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0 || d3 > 3600.0;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			Random random = this.priest.getRandom();
			double d0 = this.priest.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.priest.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.priest.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.priest.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
		}
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.MoveHelperController but changed GhastEntity to LunaticPriestEntity */
	static class PriestMoveHelperController extends MoveControl
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
			if (this.operation == MoveControl.Operation.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.priest.getRandom().nextInt(5) + 2;
					Vec3 vec3d = new Vec3(this.wantedX - this.priest.getX(), this.wantedY - this.priest.getY(), this.wantedZ - this.priest.getZ());
					double d0 = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.canReach(vec3d, Mth.ceil(d0)))
					{
						this.priest.setDeltaMovement(this.priest.getDeltaMovement().add(vec3d.scale(0.1)));
					}
					else
					{
						this.operation = MoveControl.Operation.WAIT;
					}
				}
			}
		}

		private boolean canReach(Vec3 pos, int distance) //isNotColliding
		{
			AABB axisalignedbb = this.priest.getBoundingBox();
			for (int i = 1; i < distance; ++i)
			{
				axisalignedbb = axisalignedbb.move(pos);
				if (!this.priest.level.noCollision(this.priest, axisalignedbb)) {return false;}
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
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return priest.isActive() && priest.isInPhase1();
		}

		@Override
		public void tick()
		{				
			if (this.priest.getTarget() == null)
			{
				Vec3 vec = this.priest.getDeltaMovement();
				this.priest.setYRot(-((float)Mth.atan2(vec.x, vec.z)) * (180F / (float)Math.PI));
				this.priest.yBodyRot = this.priest.getYRot();
			}
			else
			{
				LivingEntity livingentity = this.priest.getTarget();
				if (livingentity.distanceToSqr(this.priest) < 64*64)
				{
					double x = livingentity.getX() - this.priest.getX();
					double z = livingentity.getZ() - this.priest.getZ();
					this.priest.setYRot(-((float)Mth.atan2(x, z)) * (180F / (float)Math.PI));
					this.priest.yBodyRot = this.priest.getYRot();
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
	        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean canUse()
	    {
	    	if (!this.priest.isActive()) {return false;}
	    	LivingEntity target = this.priest.getTarget();
	        return target != null && priest.hasLineOfSight(target) && priest.lunaticProjectileTimer == 0 && target.isAlive() && this.priest.canAttack(target);
	    }
	    
	    public void start() {this.projectileCount = 0;}
	    
	    public void stop() {this.projectileCount = 0;}

	    public void tick()
	    {
	    	LivingEntity target = this.priest.getTarget();
	    	
	    	double Xdistance = target.getX() - this.priest.getX();
            double Ydistance = target.getY(0.5D) - this.priest.getY(0.5D);
            double Zdistance = target.getZ() - this.priest.getZ();
            
            float inaccuracy = 0.0f;
            if (priest.isInPhase2()) {inaccuracy = 0.3f;}
            
	        if (projectileCount < 1)
	        {
	        	 ++this.projectileCount;
	        	 LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(this.priest.level, this.priest, Xdistance, Ydistance, Zdistance, 0.7f + this.priest.random.nextFloat(), inaccuracy);
                 lunaticProjectileEntity.setPos(lunaticProjectileEntity.getX(), this.priest.getY(0.5D) + 0.5D, lunaticProjectileEntity.getZ());
                 this.priest.level.addFreshEntity(lunaticProjectileEntity);
	        }
	        if (priest.isInPhase1())
	        {
	        	priest.lunaticProjectileTimer = 35 + (int) (priest.random.nextFloat() * 20);
	        }
	        else
	        {
	        	priest.lunaticProjectileTimer = 30 + (int) (priest.random.nextFloat() * 10);
	        }
	        super.tick();
	    }
	}
	
	public static class PriestLookRandomlyGoal extends ActiveRandomLookAroundGoal
	{		
		public PriestLookRandomlyGoal(LunaticPriestEntity priestIn) {super(priestIn);}
		@Override public boolean canUse() {return ((LunaticPriestEntity) this.activableGoalOwner).isInPhase2() && super.canUse();}
		@Override public boolean canContinueToUse() {return ((LunaticPriestEntity) this.activableGoalOwner).isInPhase2() && super.canContinueToUse();}
	}
	
	public static class PriestWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public PriestWaterAvoidingRandomWalkingGoal(LunaticPriestEntity priestIn, double speedIn) {super(priestIn, speedIn);}
		@Override public boolean canUse() {return ((LunaticPriestEntity) this.activableGoalOwner).isInPhase2() && super.canUse();}
		@Override public boolean canContinueToUse() {return ((LunaticPriestEntity) this.activableGoalOwner).isInPhase2() && super.canContinueToUse();}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LUNATIC_PRIEST_DEATH.get();}
    @Override protected float getSoundVolume() {return 2.5F;}
}
