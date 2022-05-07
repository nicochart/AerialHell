package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.EnumSet;
import java.util.List;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChainedGodEntity extends AbstractBossEntity
{
	public int attackTimer;
	private int fireballTimer;
	
	private static final DataParameter<Boolean> IMPLODING = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.BOOLEAN);
	private int timeSinceImploding;
	   
	public ChainedGodEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		fireballTimer = 5000;
		attackTimer = 0;
		timeSinceImploding = 0; this.hurtTime = 0;
		bossInfo.setColor(BossInfo.Color.RED);
		bossInfo.setOverlay(BossInfo.Overlay.NOTCHED_6);
	}

	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new BossNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodEntity.ChainedGodFireballAttackGoal(this));
		this.goalSelector.addGoal(3, new ChainedGodMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new ChainedGodWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new ChainedGodLeapAtTargetGoal(this, 0.7F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 700.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 6.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 14.0D);
    }
	
	@Override
	protected void registerData()
	{
	    super.registerData();
	    this.dataManager.register(IMPLODING, false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putShort("TimeImploding", (short)this.timeSinceImploding);
	    compound.putBoolean("Imploding", this.isImploding());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	    super.readAdditional(compound);
	    if (compound.contains("TimeImploding", 99))
	    {
	    	this.timeSinceImploding = compound.getShort("TimeImploding");
	    }
	    this.setImploding(compound.getBoolean("Imploding"));
	}
	
	public boolean isImploding()
	{
		return this.dataManager.get(IMPLODING);
	}

	public void setImploding(boolean isImploding)
	{
	    this.dataManager.set(IMPLODING, isImploding);
	}
	
	@Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		return false;
	}
	
	@Override
    public void tick()
    {		
		fireballTimer -= (int)(Math.random() * 41) + 1; //+random int between 1 and 42
		if (fireballTimer <= 0) {fireballTimer = 2000;}
		
		if (this.isActive() && this.ticksExisted % 600 == 0 && this.getHealth() < this.getMaxHealth() / 2)
		{
			this.timeSinceImploding = 0;
			this.setImploding(true);
			this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_ROAR.get(), 5.0F, 1.0F);
		}
		
		if (this.isImploding())
		{
			//lève les bras, fait des particules bonus, ne bouge plus, créer une explosion si timeSince = fuzetime
			this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 20, 10, true, false)));
			this.timeSinceImploding++;
			
	        if (this.timeSinceImploding >= 138)
	        {
	        	this.implode();
	            this.setImploding(false);
		        this.timeSinceImploding = 0;
	        }
	        
	        if (this.timeSinceImploding > 12)
	        {
	        	List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(20), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 15));
				for (Entity entity : nearbyEntities)
		    	{
					boolean creaOrSpecPlayer = (entity instanceof PlayerEntity && (((PlayerEntity) entity).isSpectator() || ((PlayerEntity) entity).isCreative()));
		    		if (entity instanceof LivingEntity && !creaOrSpecPlayer)
		    		{
		    			dragEntity(entity);
		    		}
		    	}
				
				if (this.world.isRemote)
		        {
		        	for (int i=0; i<5; i++)
		        	{
		        		double random = rand.nextFloat() * 2;
						double x = getPosX() + (rand.nextFloat() - 0.5F) * random;
						double y = (this.getBoundingBox().minY + random) + 0.5D;
						double z = getPosZ() + (rand.nextFloat() - 0.5F) * random;
						double dx = (rand.nextFloat() - 0.5F)/10;
						double dz = (rand.nextFloat() - 0.5F)/10;
						this.world.addParticle(ParticleTypes.LAVA, x, y, z, dx, 0.5D, dz);
		        	}
		        }
	        }
		}
		
		if (rand.nextFloat() > 0.5)
        {
			double random = rand.nextFloat() * 2;
			double x = getPosX() + (rand.nextFloat() - 0.5F) * random;
			double y = (this.getBoundingBox().minY + random) + 0.5D;
			double z = getPosZ() + (rand.nextFloat() - 0.5F) * random;
			double dx = (rand.nextFloat() - 0.5F)/10;
			double dz = (rand.nextFloat() - 0.5F)/10;
			this.world.addParticle(AerialHellParticleTypes.GOD_FLAME.get(), x, y, z, dx, -0.06D, dz);
        }
		
		super.tick();
    }
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
	@Override
	public boolean canBePushed()
	{
		return false;
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
	         attackedEntity.setMotion(attackedEntity.getMotion().getX(), (double)0.8F, attackedEntity.getMotion().getZ());
	         this.applyEnchantments(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}
	
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
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_CHAINED_GOD_DEATH.get();}
    
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
    	if (!blockIn.getMaterial().isLiquid())
    	{
        	this.playSound(AerialHellSoundEvents.ENTITY_CHAINED_GOD_STEP.get(), 0.5F, 0.8F + 0.5F*rand.nextFloat());
        }
    }
	
	private void dragEntity(Entity entityIn)
	{
		double factor = 0.8 / Math.max(5, this.getDistance(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vector3d toGod = new Vector3d(this.getPosX() - entityIn.getPosX(), this.getPosY() - entityIn.getPosY(), this.getPosZ() - entityIn.getPosZ()).mul(factor, factor, factor);
		entityIn.setMotion(entityIn.getMotion().add(toGod.mul(factor,factor,factor)));
	}
	
	
	private void implode()
	{
		if (!this.world.isRemote)
	    {
	    	Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
	        this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)5, explosion$mode);
	    }
		spawnImplosionParticle();
	}
	
	public void spawnImplosionParticle()
	{
		if (this.world.isRemote)
        {
        	for(int i = 0; i < 30; ++i)
            {
            	double d0 = this.rand.nextGaussian() * 0.02D;
            	double d1 = this.rand.nextGaussian() * 0.02D;
            	double d2 = this.rand.nextGaussian() * 0.02D;
            	this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXWidth(1.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else
        {
           this.world.setEntityState(this, (byte)20);
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
	        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean shouldExecute()
	    {
	    	if (!this.chainedGod.isActive() || this.chainedGod.isImploding())
	    	{
	    		return false;
	    	}
	    	LivingEntity target = this.chainedGod.getAttackTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.chainedGod.getDistance(target);}
	        return chainedGod.getHealth() < chainedGod.getMaxHealth() / 2 && chainedGod.fireballTimer < 50 && target != null && target.isAlive() && this.chainedGod.canAttack(target) && DistanceToTarget < 16;
	    }
	    
	    public void startExecuting() {this.fireballCount = 0;}
	    
	    public void resetTask() {this.fireballCount = 0;}

	    public void tick()
	    {
	    	LivingEntity target = this.chainedGod.getAttackTarget();
	    	double Xdistance = target.getPosX() - this.chainedGod.getPosX();
            double Ydistance = target.getPosYHeight(0.5D) - this.chainedGod.getPosYHeight(0.5D);
            double Zdistance = target.getPosZ() - this.chainedGod.getPosZ();
            double halfDistanceToTarget = this.chainedGod.getDistance(target) / 2;
            
	        if (fireballCount < 3)
	        {
	        	 ++this.fireballCount;
	        	 ChainedGodFireballEntity fireballentity = new ChainedGodFireballEntity(this.chainedGod.world, this.chainedGod, Xdistance + 0.5 * this.chainedGod.getRNG().nextGaussian() * (double)halfDistanceToTarget, Ydistance, Zdistance + 0.5 * this.chainedGod.getRNG().nextGaussian() * (double)halfDistanceToTarget);
                 fireballentity.setPosition(fireballentity.getPosX(), this.chainedGod.getPosYHeight(0.5D) + 0.5D, fireballentity.getPosZ());
                 this.chainedGod.world.addEntity(fireballentity);
	        }
	        super.tick();
	     }
	}
	
	public static class ChainedGodLeapAtTargetGoal extends BossLeapAtTargetGoal
	{		
		public ChainedGodLeapAtTargetGoal(ChainedGodEntity godIn, float leapMotionYIn) {super(godIn, leapMotionYIn);}
		@Override public boolean shouldExecute() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldContinueExecuting();}
	}
	
	public static class ChainedGodMeleeAttackGoal extends BossMeleeAttackGoal
	{
		public ChainedGodMeleeAttackGoal(ChainedGodEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean shouldExecute() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldContinueExecuting();}
	}
	
	public static class ChainedGodWaterAvoidingRandomWalkingGoal extends BossWaterAvoidingRandomWalkingGoal
	{
		public ChainedGodWaterAvoidingRandomWalkingGoal(ChainedGodEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean shouldExecute() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((ChainedGodEntity) this.boss).isImploding() && super.shouldContinueExecuting();}
	}
}
