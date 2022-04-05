package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.EnumSet;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Projectile.ChainedGodFireballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChainedGodEntity extends MonsterEntity
{
	public int attackTimer;
	private static int fireballTimer;
	
	public ChainedGodEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		fireballTimer = 5000;
		attackTimer = 0;
	}

	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new ChainedGodEntity.FireballAttackGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, 1.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 700.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 3.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 14.0D);
    }
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
	      return false;
	}
	
	@Override
	public boolean isImmuneToFire()
	{
		return true;
	}
	
	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}
	
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
	public boolean attackEntityAsMob(Entity attackedEntity)
	{
	      this.world.setEntityState(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float f1 = (int)f > 0 ? f / 2.0F + (float)this.rand.nextInt((int)f) : f;
	      boolean flag = attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
	      if (flag)
	      {
	         attackedEntity.setMotion(0.0D, (double)0.8F, 0.0D);
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
		private final ChainedGodEntity chainedGod;
	    private int fireballCount;

	    public FireballAttackGoal(ChainedGodEntity chainedGodIn)
	    {
	    	this.chainedGod = chainedGodIn;
	        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	    }

	    public boolean shouldExecute()
	    {
	    	LivingEntity target = this.chainedGod.getAttackTarget();
	    	double DistanceToTarget = 0;
	    	if (target != null)	{DistanceToTarget = this.chainedGod.getDistance(target);}
	        return chainedGod.getHealth() < chainedGod.getMaxHealth() / 2 && fireballTimer < 50 && target != null && target.isAlive() && this.chainedGod.canAttack(target) && DistanceToTarget < 16;
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
}
