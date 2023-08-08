package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.EnumSet;
import java.util.List;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveLeapAtTargetGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.ActiveWaterAvoidingRandomWalkingGoal;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class ChainedGodEntity extends AbstractBossEntity
{
	public int attackTimer;
	private int fireballTimer;
	
	private static final EntityDataAccessor<Boolean> IMPLODING = SynchedEntityData.defineId(ChainedGodEntity.class, EntityDataSerializers.BOOLEAN);
	private int timeSinceImploding;
	   
	public ChainedGodEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		fireballTimer = 5000;
		attackTimer = 0;
		timeSinceImploding = 0; this.hurtTime = 0;
		bossInfo.setColor(BossEvent.BossBarColor.RED);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.addGoal(3, new ChainedGodMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new ChainedGodWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new ChainedGodLeapAtTargetGoal(this, 0.7F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1400.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 6.0D)
				.add(Attributes.ATTACK_DAMAGE, 25.0D);
    }
	
	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getDirectEntity();
		Entity trueSourceEntity = source.getEntity();
		if (this.isImploding() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (this.getHealth() < this.getMaxHealth() / 3 && immediateSourceEntity instanceof AbstractArrow)
		{
			return false;
		}
		else
		{
			boolean flag = super.hurt(source, amount);
			if (flag)
			{
				if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrow))
				{
					if (!(trueSourceEntity instanceof Player && ((Player)trueSourceEntity).isCreative()))
					{
						this.setTarget((LivingEntity) trueSourceEntity);
					}
				}
			}
			return flag;
		}
	}
	
	@Override
	protected void defineSynchedData()
	{
	    super.defineSynchedData();
	    this.entityData.define(IMPLODING, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		
		compound.putShort("TimeImploding", (short)this.timeSinceImploding);
	    compound.putBoolean("Imploding", this.isImploding());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
	    super.readAdditionalSaveData(compound);
	    if (compound.contains("TimeImploding", 99))
	    {
	    	this.timeSinceImploding = compound.getShort("TimeImploding");
	    }
	    this.setImploding(compound.getBoolean("Imploding"));
	}
	
	public boolean isImploding()
	{
		return this.entityData.get(IMPLODING);
	}

	public void setImploding(boolean isImploding)
	{
	    this.entityData.set(IMPLODING, isImploding);
	}
	
	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}
	
	@Override
    public void tick()
    {		
		fireballTimer -= (int)(Math.random() * 41) + 1; //+random int between 1 and 42
		if (fireballTimer <= 0) {fireballTimer = 2000;}
		
		if (this.isActive() && this.tickCount % 600 == 0 && this.getHealth() < this.getMaxHealth() / 2)
		{
			this.timeSinceImploding = 0;
			this.setImploding(true);
			this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR.get(), 5.0F, 1.0F);
		}
		
		if (this.isImploding())
		{
			//lève les bras, fait des particules bonus, ne bouge plus, cr�er une explosion si timeSince = fuzetime
			if (!level().isClientSide())
			{
				this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10, true, false)));
			}
			this.timeSinceImploding++;
			
	        if (this.timeSinceImploding >= 138)
	        {
	        	this.implode();
	            this.setImploding(false);
		        this.timeSinceImploding = 0;
	        }
	        
	        if (this.timeSinceImploding > 12)
	        {
	        	List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(20), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 15));
				for (Entity entity : nearbyEntities)
		    	{
					boolean creaOrSpecPlayer = (entity instanceof Player && (((Player) entity).isSpectator() || ((Player) entity).isCreative()));
		    		if (entity instanceof LivingEntity && !creaOrSpecPlayer)
		    		{
		    			dragEntity(entity);
		    		}
		    	}
				
				if (this.level().isClientSide())
		        {
		        	for (int i=0; i<5; i++)
		        	{
		        		double rand = random.nextFloat() * 2;
						double x = getX() + (random.nextFloat() - 0.5F) * rand;
						double y = (this.getBoundingBox().minY + rand) + 0.5D;
						double z = getZ() + (random.nextFloat() - 0.5F) * rand;
						double dx = (random.nextFloat() - 0.5F)/10;
						double dz = (random.nextFloat() - 0.5F)/10;
						this.level().addParticle(ParticleTypes.LAVA, x, y, z, dx, 0.5D, dz);
		        	}
		        }
	        }
		}
		
		if (random.nextFloat() > 0.5)
        {
			double rand = random.nextFloat() * 2;
			double x = getX() + (random.nextFloat() - 0.5F) * rand;
			double y = (this.getBoundingBox().minY + rand) + 0.5D;
			double z = getZ() + (random.nextFloat() - 0.5F) * rand;
			double dx = (random.nextFloat() - 0.5F)/10;
			double dz = (random.nextFloat() - 0.5F)/10;
			this.level().addParticle(AerialHellParticleTypes.GOD_FLAME.get(), x, y, z, dx, -0.06D, dz);
        }

		this.destroyObstacles();
		super.tick();
    }

	private void destroyObstacles()
	{
		BlockPos pos = this.blockPosition().above(); int x,y,z; int xzRadius = 3, yRadius = 3;
		for (x=-xzRadius;x<=xzRadius;x++)
		{
			for (y=-yRadius;y<=yRadius;y++)
			{
				for (z=-xzRadius;z<=xzRadius;z++)
				{
					if (level().getBlockState(pos.offset(x, y, z)).is(AerialHellTags.Blocks.CHAINED_GOD_CAN_WALK_DESTROY))
					{
						level().destroyBlock(pos.offset(x, y, z), this.random.nextInt(3) > 1);
					}
				}
			}
		}
	}
	
	@Override
    public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override
	public boolean doHurtTarget(Entity attackedEntity)
	{
	      this.level().broadcastEntityEvent(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float amount = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
	      float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
	      boolean flag = attackedEntity.hurt(this.damageSources().mobAttack(this), amount);
	      if (flag)
	      {
	    	 ((LivingEntity)attackedEntity).knockback(kb * 0.5F, (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
	         attackedEntity.setDeltaMovement(attackedEntity.getDeltaMovement().x, (double)0.8F, attackedEntity.getDeltaMovement().z);
	         this.doEnchantDamageEffects(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}

	@Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) //broadcastEntityEvent
	{
		if (id == 4)
		{
			this.attackTimer = 10;
			this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
		else {super.handleEntityEvent(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_DEATH.get();}
    
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
    	if (!blockIn.liquid())
    	{
        	this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_STEP.get(), 0.5F, 0.8F + 0.5F*random.nextFloat());
        }
    }
	
	private void dragEntity(Entity entityIn)
	{
		double factor = 0.8 / Math.max(5, this.distanceTo(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vec3 toGod = new Vec3(this.getX() - entityIn.getX(), this.getY() - entityIn.getY(), this.getZ() - entityIn.getZ()).multiply(factor, factor, factor);
		entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(toGod.multiply(factor,factor,factor)));
	}
	
	
	private void implode()
	{
		if (!this.level().isClientSide())
	    {

	    	Level.ExplosionInteraction explosionInteraction = ForgeEventFactory.getMobGriefingEvent(this.level(), this) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
	        this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)5, explosionInteraction);
	    }
		spawnImplosionParticle();
	}
	
	public void spawnImplosionParticle()
	{
		if (this.level().isClientSide())
        {
        	for(int i = 0; i < 30; ++i)
            {
            	double d0 = this.random.nextGaussian() * 0.02D;
            	double d1 = this.random.nextGaussian() * 0.02D;
            	double d2 = this.random.nextGaussian() * 0.02D;
            	this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else
        {
           this.level().broadcastEntityEvent(this, (byte)20);
        }
	}
	
	/* Chained God Goals */
	
	static class ChainedGodFireballAttackGoal extends Goal
	{
		private final ChainedGodEntity chainedGod;
	    private int fireballCount;

	    public ChainedGodFireballAttackGoal(ChainedGodEntity chainedGodIn)
	    {
	    	this.chainedGod = chainedGodIn;
	        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean canUse()
	    {
	    	if (!this.chainedGod.isActive() || this.chainedGod.isImploding()) {return false;}
	    	LivingEntity target = this.chainedGod.getTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.chainedGod.distanceTo(target);}
	        return chainedGod.getHealth() < chainedGod.getMaxHealth() / 2 && chainedGod.fireballTimer < 50 && target != null && target.isAlive() && this.chainedGod.canAttack(target) && DistanceToTarget < 16;
	    }
	    
	    public void start() {this.fireballCount = 0;}
	    
	    public void stop() {this.fireballCount = 0;}

	    public void tick()
	    {
	    	LivingEntity target = this.chainedGod.getTarget();
	    	double Xdistance = target.getX() - this.chainedGod.getX();
            double Ydistance = target.getY(0.5D) - this.chainedGod.getY(0.5D);
            double Zdistance = target.getZ() - this.chainedGod.getZ();
            double halfDistanceToTarget = this.chainedGod.distanceTo(target) / 2;
            
	        if (fireballCount < 3)
	        {
	        	 ++this.fireballCount;
	        	 ChainedGodFireballEntity fireballentity = new ChainedGodFireballEntity(this.chainedGod.level(), this.chainedGod, Xdistance + 0.5 * this.chainedGod.random.nextGaussian() * (double)halfDistanceToTarget, Ydistance, Zdistance + 0.5 * this.chainedGod.random.nextGaussian() * (double)halfDistanceToTarget);
                 fireballentity.setPos(fireballentity.getX(), this.chainedGod.getY(0.5D) + 0.5D, fireballentity.getZ());
                 this.chainedGod.level().addFreshEntity(fireballentity);
	        }
	        super.tick();
	     }
	}
	
	public static class ChainedGodLeapAtTargetGoal extends ActiveLeapAtTargetGoal
	{		
		public ChainedGodLeapAtTargetGoal(ChainedGodEntity godIn, float leapMotionYIn) {super(godIn, leapMotionYIn);}
		@Override public boolean canUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canUse();}
		@Override public boolean canContinueToUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canContinueToUse();}
	}
	
	public static class ChainedGodMeleeAttackGoal extends ActiveMeleeAttackGoal
	{
		public ChainedGodMeleeAttackGoal(ChainedGodEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean canUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canUse();}
		@Override public boolean canContinueToUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canContinueToUse();}
	}
	
	public static class ChainedGodWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public ChainedGodWaterAvoidingRandomWalkingGoal(ChainedGodEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean canUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canUse();}
		@Override public boolean canContinueToUse() {return !((ChainedGodEntity) this.activableGoalOwner).isImploding() && super.canContinueToUse();}
	}
}
