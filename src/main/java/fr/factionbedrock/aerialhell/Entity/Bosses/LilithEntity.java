package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LilithEntity extends AbstractBossEntity
{
	public int attackTimer;
	
	private static final DataParameter<Boolean> IS_TRANSFORMING = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TRANSFORMED = EntityDataManager.createKey(CreeperEntity.class, DataSerializers.BOOLEAN);
	private int timeSinceTransforming;
	   
	public LilithEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		attackTimer = 0;
		timeSinceTransforming = 0; this.hurtTime = 0;
		bossInfo.setColor(BossInfo.Color.PURPLE);
		bossInfo.setOverlay(BossInfo.Overlay.NOTCHED_6);
	}

	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new BossNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new LilithMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LilithWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 600.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getImmediateSource();
		Entity trueSourceEntity = source.getTrueSource();
		if (this.isTransforming() && !source.isCreativePlayer()) {return false;}
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrowEntity))
			{
				if (!(trueSourceEntity instanceof PlayerEntity && ((PlayerEntity)trueSourceEntity).isCreative()))
				{
					this.setAttackTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
	
	@Override
	protected void registerData()
	{
	    super.registerData();
	    this.dataManager.register(IS_TRANSFORMING, false);
	    this.dataManager.register(IS_TRANSFORMED, false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putShort("timeTransforming", (short)this.timeSinceTransforming);
		compound.putBoolean("isTransforming", this.isTransforming());
		compound.putBoolean("isTransformed", this.isTransformed());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	    super.readAdditional(compound);
	    if (compound.contains("timeTransforming", 99))
	    {
	    	this.timeSinceTransforming = compound.getShort("timeTransforming");
	    }
	    this.setTransforming(compound.getBoolean("isTransforming"));
	    this.setTransforming(compound.getBoolean("isTransformed"));
	}
	
	public boolean isTransforming() {return this.dataManager.get(IS_TRANSFORMING);}
	public void setTransforming(boolean isTransforming) {this.dataManager.set(IS_TRANSFORMING, isTransforming);}
	
	public boolean isTransformed() {return this.dataManager.get(IS_TRANSFORMED);}
	public void setTransformed(boolean transformed) {this.dataManager.set(IS_TRANSFORMED, transformed);}
	
	@Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {return false;}
	
	@Override
    public void tick()
    {		
		if (this.isActive() && !this.isTransformed() && !this.isTransforming())
		{
			this.timeSinceTransforming = 0;
			this.setTransforming(true);
			//this.playSound(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMING.get(), 5.0F, 1.0F);
		}
		
		if (this.isTransforming())
		{
			if (!world.isRemote)
			{
				this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 20, 10, true, false)));
				this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.RESISTANCE, 1, 10, true, false)));
			}
			this.timeSinceTransforming++;
			
	        if (this.timeSinceTransforming >= 138)
	        {
	        	this.transform();
		        this.timeSinceTransforming = 0;
	        }
	        
	        if (this.timeSinceTransforming > 12)
	        {
	        	List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(20), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 15));
				for (Entity entity : nearbyEntities)
		    	{
					boolean creaOrSpecPlayer = (entity instanceof PlayerEntity && (((PlayerEntity) entity).isSpectator() || ((PlayerEntity) entity).isCreative()));
		    		if (entity instanceof LivingEntity && !creaOrSpecPlayer) {dragEntity(entity);}
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
						this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), x, y, z, dx, 0.0D, dz);
		        	}
		        }
	        }
		}		
		super.tick();
    }
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
	@Override public boolean canBePushed() {return false;}
	
	@Override
	public boolean attackEntityAsMob(Entity attackedEntity)
	{
	      this.world.setEntityState(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float amount = (int)f > 0 ? f / 2.0F + (float)this.rand.nextInt((int)f) : f;
	      float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
	      boolean flag = attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), amount);
	      if (flag)
	      {
	    	 ((LivingEntity)attackedEntity).applyKnockback(kb * 0.5F, (double)MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F))));
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
	
	private void dragEntity(Entity entityIn)
	{
		double factor = 0.2 / Math.max(5, this.getDistance(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vector3d toGod = new Vector3d(this.getPosX() - entityIn.getPosX(), this.getPosY() - entityIn.getPosY(), this.getPosZ() - entityIn.getPosZ()).mul(factor, factor, factor);
		entityIn.setMotion(entityIn.getMotion().add(toGod.mul(factor,factor,factor)));
	}
	
	private void transform()
	{
		this.setTransforming(false);
		this.setTransformed(true);
		spawnTransformationParticle();
	}
	
	public void spawnTransformationParticle()
	{
		if (this.world.isRemote)
        {
        	for(int i = 0; i < 30; ++i)
            {
            	double d0 = this.rand.nextGaussian() * 0.02D;
            	double d1 = this.rand.nextGaussian() * 0.02D;
            	double d2 = this.rand.nextGaussian() * 0.02D;
            	this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getPosXWidth(1.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else
        {
           this.world.setEntityState(this, (byte)20);
        }
	}
	
	/* Lilith Goals */
	
	public static class LilithMeleeAttackGoal extends BossMeleeAttackGoal
	{
		public LilithMeleeAttackGoal(LilithEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean shouldExecute() {return !((LilithEntity) this.boss).isTransforming() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((LilithEntity) this.boss).isTransforming() && super.shouldContinueExecuting();}
	}
	
	public static class LilithWaterAvoidingRandomWalkingGoal extends BossWaterAvoidingRandomWalkingGoal
	{
		public LilithWaterAvoidingRandomWalkingGoal(LilithEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean shouldExecute() {return !((LilithEntity) this.boss).isTransforming() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((LilithEntity) this.boss).isTransforming() && super.shouldContinueExecuting();}
	}
}
