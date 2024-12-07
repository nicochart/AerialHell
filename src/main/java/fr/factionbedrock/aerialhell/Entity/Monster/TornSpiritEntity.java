package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class TornSpiritEntity extends HostileEntity
{
	public TornSpiritEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
    }
	
	@Override protected void initGoals()
    {
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.add(2, new TornSpiritEntity.FireballAttackGoal(this));
		this.goalSelector.add(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(6, new PounceAtTargetGoal(this, 0.55F));
        this.targetSelector.add(7, new ActiveTargetGoal<>(this, MudSoldierEntity.class, true));
        this.targetSelector.add(8, new ActiveTargetGoal<>(this, MudSpectralSoldierEntity.class, true));
        this.targetSelector.add(9, new ActiveTargetGoal<>(this, MudCycleMageEntity.class, true));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, ChainedGodEntity.class, 6.0F, 1.0D, 1.2D));
    }
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.33D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 17.0D);
    }
	
	@Override public boolean damage(DamageSource source, float amount)
	{
		boolean flag = super.damage(source, amount);
		if (flag)
		{
			if (source.getAttacker() instanceof LivingEntity  livingEntity && !(source.getSource() instanceof PersistentProjectileEntity))
			{
				if (!(EntityHelper.isCreativePlayer(livingEntity)))
				{
					this.setTarget(livingEntity);
				}
			}
		}
		return flag;
	}
	
	@Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}
	
	@Override public void tick()
    {
		if (this.getTarget() instanceof ChainedGodEntity) {this.setTarget(null);}
        double rand = random.nextFloat();
        double x = getX() + (random.nextFloat() - 0.5F) * rand;
        double y = (this.getBoundingBox().minY + rand) + 0.5D;
        double z = getZ() + (random.nextFloat() - 0.5F) * rand;
        this.getWorld().addParticle(AerialHellParticleTypes.GOD_FLAME, x, y, z, 0.0D, -0.06D, 0.0D);
        
        super.tick();
    }
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_HURT;}
	@Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_TORN_SPIRIT_DEATH;}
    
	static class FireballAttackGoal extends Goal
	{
		private final TornSpiritEntity tornspirit;
	    private int attackStep;
	    private int attackTime;
	    private int firedRecentlyTimer;

	    public FireballAttackGoal(TornSpiritEntity fireminionIn)
	    {
	    	this.tornspirit = fireminionIn;
			this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
	    }

	    public boolean canStart()
	    {
	    	LivingEntity target = this.tornspirit.getTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.tornspirit.distanceTo(target);}
	        return target != null && target.isAlive() && this.tornspirit.canTarget(target) && DistanceToTarget > 10;
	    }
	    
	    public void start() {this.attackStep = 0;}
	    
	    public void stop() {this.firedRecentlyTimer = 0;}

	    public void tick()
	    {
	        --this.attackTime;
	        LivingEntity target = this.tornspirit.getTarget();
	        if (target != null)
	        {
	        	boolean canSeeTarget = this.tornspirit.getVisibilityCache().canSee(target);
	            if (canSeeTarget)
	            {
	               this.firedRecentlyTimer = 0;
	            }
	            else
	            {
	               ++this.firedRecentlyTimer;
	            }

	            double squaredDistanceToTarget = this.tornspirit.squaredDistanceTo(target);
	            if (squaredDistanceToTarget < 4.0D)
	            {
	               if (!canSeeTarget)
	               {
	                  return;
	               }

	               if (this.attackTime <= 0)
	               {
	                  this.attackTime = 20;
	                  this.tornspirit.tryAttack(target);
	               }

	               this.tornspirit.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 2.0D);
	             }
	             else if (squaredDistanceToTarget < this.getFollowDistance() * this.getFollowDistance() && canSeeTarget)
	             {
	               double Xdistance = target.getX() - this.tornspirit.getX();
	               double Ydistance = target.getBodyY(0.5D) - this.tornspirit.getBodyY(0.5D);
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
	                     float halfDistanceToTarget = MathHelper.sqrt(MathHelper.sqrt((float) squaredDistanceToTarget)) * 0.5F;
	                     if (!this.tornspirit.isSilent())
	                     {
	                        this.tornspirit.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, this.tornspirit.getBlockPos(), 0);
	                     }
	                     
	                     int n = (int)(Math.random() * 2) + 1; //nombre al�atoire entre 1 et 3
	                    		 
	                     for(int i = 0; i < n; ++i)
	                     {
							 Vec3d vec3 = new Vec3d(Xdistance + 0.5 * this.tornspirit.getRandom().nextGaussian() * (double)halfDistanceToTarget, Ydistance, Zdistance + 0.5 * this.tornspirit.getRandom().nextGaussian() * (double)halfDistanceToTarget);
							 SmallFireballEntity smallfireball = new SmallFireballEntity(this.tornspirit.getWorld(), this.tornspirit, vec3.normalize());
							 smallfireball.setPos(smallfireball.getX(), this.tornspirit.getBodyY(0.5D) + 0.5D, smallfireball.getZ());
	                        this.tornspirit.getWorld().spawnEntity(smallfireball);
	                     }
	                  }
	               }
	               this.tornspirit.getLookControl().lookAt(target, 10.0F, 10.0F);
	             }
	             else if (this.firedRecentlyTimer < 5)
	             {
	               this.tornspirit.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.0D);
	             }

	            super.tick();
	         }
	     }

	     private double getFollowDistance()
	     {
	         return this.tornspirit.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
	     }
	}
}
