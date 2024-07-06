package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TornSpiritEntity extends Monster
{
	public TornSpiritEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
    }
	
	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.goalSelector.addGoal(2, new TornSpiritEntity.FireballAttackGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, 0.55F));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, MudSpectralSoldierEntity.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.FOLLOW_RANGE, 24.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.33D)
				.add(Attributes.ATTACK_DAMAGE, 17.0D);
    }
	
	@Override public boolean hurt(DamageSource source, float amount)
	{
		boolean flag = super.hurt(source, amount);
		if (flag)
		{
			if (source.getEntity() instanceof LivingEntity && !(source.getDirectEntity() instanceof AbstractArrow))
			{
				if (!(source.getEntity() instanceof Player && ((Player)source.getEntity()).isCreative()))
				{
					this.setTarget((LivingEntity) source.getEntity());
				}
			}
		}
		return flag;
	}
	
	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override public void tick()
    {
		if (this.getTarget() instanceof ChainedGodEntity) {this.setTarget(null);}
        double rand = random.nextFloat();
        double x = getX() + (random.nextFloat() - 0.5F) * rand;
        double y = (this.getBoundingBox().minY + rand) + 0.5D;
        double z = getZ() + (random.nextFloat() - 0.5F) * rand;
        this.level().addParticle(AerialHellParticleTypes.GOD_FLAME.get(), x, y, z, 0.0D, -0.06D, 0.0D);
        
        super.tick();
    }
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_HURT.get();}
	@Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_DEATH.get();}
    
	static class FireballAttackGoal extends Goal
	{
		private final TornSpiritEntity tornspirit;
	    private int attackStep;
	    private int attackTime;
	    private int firedRecentlyTimer;

	    public FireballAttackGoal(TornSpiritEntity fireminionIn)
	    {
	    	this.tornspirit = fireminionIn;
	        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean canUse()
	    {
	    	LivingEntity target = this.tornspirit.getTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.tornspirit.distanceTo(target);}
	        return target != null && target.isAlive() && this.tornspirit.canAttack(target) && DistanceToTarget > 10;
	    }
	    
	    public void start() {this.attackStep = 0;}
	    
	    public void stop() {this.firedRecentlyTimer = 0;}

	    public void tick()
	    {
	        --this.attackTime;
	        LivingEntity target = this.tornspirit.getTarget();
	        if (target != null)
	        {
	        	boolean canSeeTarget = this.tornspirit.getSensing().hasLineOfSight(target);
	            if (canSeeTarget)
	            {
	               this.firedRecentlyTimer = 0;
	            }
	            else
	            {
	               ++this.firedRecentlyTimer;
	            }

	            double squaredDistanceToTarget = this.tornspirit.distanceToSqr(target);
	            if (squaredDistanceToTarget < 4.0D)
	            {
	               if (!canSeeTarget)
	               {
	                  return;
	               }

	               if (this.attackTime <= 0)
	               {
	                  this.attackTime = 20;
	                  this.tornspirit.doHurtTarget(target);
	               }

	               this.tornspirit.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 2.0D);
	             }
	             else if (squaredDistanceToTarget < this.getFollowDistance() * this.getFollowDistance() && canSeeTarget)
	             {
	               double Xdistance = target.getX() - this.tornspirit.getX();
	               double Ydistance = target.getY(0.5D) - this.tornspirit.getY(0.5D);
	               double Zdistance = target.getZ() - this.tornspirit.getZ();
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
	                     float halfDistanceToTarget = Mth.sqrt(Mth.sqrt((float) squaredDistanceToTarget)) * 0.5F;
	                     if (!this.tornspirit.isSilent())
	                     {
	                        this.tornspirit.level().levelEvent((Player)null, 1018, this.tornspirit.blockPosition(), 0);
	                     }
	                     
	                     int n = (int)(Math.random() * 2) + 1; //nombre al�atoire entre 1 et 3
	                    		 
	                     for(int i = 0; i < n; ++i)
	                     {
							 Vec3 vec3 = new Vec3(Xdistance + 0.5 * this.tornspirit.getRandom().nextGaussian() * (double)halfDistanceToTarget, Ydistance, Zdistance + 0.5 * this.tornspirit.getRandom().nextGaussian() * (double)halfDistanceToTarget);
							 SmallFireball smallfireball = new SmallFireball(this.tornspirit.level(), this.tornspirit, vec3.normalize());
							 smallfireball.setPos(smallfireball.getX(), this.tornspirit.getY(0.5D) + 0.5D, smallfireball.getZ());
	                        this.tornspirit.level().addFreshEntity(smallfireball);
	                     }
	                  }
	               }
	               this.tornspirit.getLookControl().setLookAt(target, 10.0F, 10.0F);
	             }
	             else if (this.firedRecentlyTimer < 5)
	             {
	               this.tornspirit.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
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
