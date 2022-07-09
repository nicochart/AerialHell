package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TornSpiritEntity extends MonsterEntity
{
	public TornSpiritEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }
	
	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.addGoal(2, new TornSpiritEntity.FireballAttackGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, 0.55F));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, MudSpectralSoldierEntity.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.33D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 17.0D);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			if (source.getTrueSource() instanceof LivingEntity && !(source.getImmediateSource() instanceof AbstractArrowEntity)) {this.setAttackTarget((LivingEntity) source.getTrueSource());}
		}
		return flag;
	}
	
	@Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
	@Override
    public void tick()
    {
        double random = rand.nextFloat();
        double x = getPosX() + (rand.nextFloat() - 0.5F) * random;
        double y = (this.getBoundingBox().minY + random) + 0.5D;
        double z = getPosZ() + (rand.nextFloat() - 0.5F) * random;
        this.world.addParticle(AerialHellParticleTypes.GOD_FLAME.get(), x, y, z, 0.0D, -0.06D, 0.0D);
        
        super.tick();
    }
	
	@Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
    	return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
    	return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_DEATH.get();
    }
    
	static class FireballAttackGoal extends Goal
	{
		private final TornSpiritEntity tornspirit;
	    private int attackStep;
	    private int attackTime;
	    private int firedRecentlyTimer;

	    public FireballAttackGoal(TornSpiritEntity fireminionIn)
	    {
	    	this.tornspirit = fireminionIn;
	        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean shouldExecute()
	    {
	    	LivingEntity target = this.tornspirit.getAttackTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.tornspirit.getDistance(target);}
	        return target != null && target.isAlive() && this.tornspirit.canAttack(target) && DistanceToTarget > 10;
	    }
	    
	    public void startExecuting() {this.attackStep = 0;}
	    
	    public void resetTask() {this.firedRecentlyTimer = 0;}

	    public void tick()
	    {
	        --this.attackTime;
	        LivingEntity target = this.tornspirit.getAttackTarget();
	        if (target != null)
	        {
	        	boolean canSeeTarget = this.tornspirit.getEntitySenses().canSee(target);
	            if (canSeeTarget)
	            {
	               this.firedRecentlyTimer = 0;
	            }
	            else
	            {
	               ++this.firedRecentlyTimer;
	            }

	            double squaredDistanceToTarget = this.tornspirit.getDistanceSq(target);
	            if (squaredDistanceToTarget < 4.0D)
	            {
	               if (!canSeeTarget)
	               {
	                  return;
	               }

	               if (this.attackTime <= 0)
	               {
	                  this.attackTime = 20;
	                  this.tornspirit.attackEntityAsMob(target);
	               }

	               this.tornspirit.getMoveHelper().setMoveTo(target.getPosX(), target.getPosY(), target.getPosZ(), 2.0D);
	             }
	             else if (squaredDistanceToTarget < this.getFollowDistance() * this.getFollowDistance() && canSeeTarget)
	             {
	               double Xdistance = target.getPosX() - this.tornspirit.getPosX();
	               double Ydistance = target.getPosYHeight(0.5D) - this.tornspirit.getPosYHeight(0.5D);
	               double Zdistance = target.getPosZ() - this.tornspirit.getPosZ();
	               if (this.attackTime <= 0)
	               {
	                  this.attackStep += (int)(Math.random() * 3) + 1; //+random int between 1 and 4
	                  if (this.attackStep == 1)
	                  {
	                     this.attackTime = 60;
	                  }
	                  else if (this.attackStep <= 12)
	                  {
	                     this.attackTime = 6;
	                  }
	                  else
	                  {
	                     this.attackTime = 100;
	                     this.attackStep = 0;
	                  }

	                  if (this.attackStep > 1)
	                  {
	                     float halfDistanceToTarget = MathHelper.sqrt(MathHelper.sqrt(squaredDistanceToTarget)) * 0.5F;
	                     if (!this.tornspirit.isSilent())
	                     {
	                        this.tornspirit.world.playEvent((PlayerEntity)null, 1018, this.tornspirit.getPosition(), 0);
	                     }
	                     
	                     int n = (int)(Math.random() * 2) + 1; //nombre aléatoire entre 1 et 3
	                    		 
	                     for(int i = 0; i < n; ++i)
	                     {
	                        SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.tornspirit.world, this.tornspirit, Xdistance + 0.5 * this.tornspirit.getRNG().nextGaussian() * (double)halfDistanceToTarget, Ydistance, Zdistance + 0.5 * this.tornspirit.getRNG().nextGaussian() * (double)halfDistanceToTarget);
	                        smallfireballentity.setPosition(smallfireballentity.getPosX(), this.tornspirit.getPosYHeight(0.5D) + 0.5D, smallfireballentity.getPosZ());
	                        this.tornspirit.world.addEntity(smallfireballentity);
	                     }
	                  }
	               }
	               this.tornspirit.getLookController().setLookPositionWithEntity(target, 10.0F, 10.0F);
	             }
	             else if (this.firedRecentlyTimer < 5)
	             {
	               this.tornspirit.getMoveHelper().setMoveTo(target.getPosX(), target.getPosY(), target.getPosZ(), 1.0D);
	             }

	            super.tick();
	         }
	     }

	     private double getFollowDistance()
	     {
	         return this.tornspirit.getAttributeValue(Attributes.FOLLOW_RANGE);
	     }
	}
}
