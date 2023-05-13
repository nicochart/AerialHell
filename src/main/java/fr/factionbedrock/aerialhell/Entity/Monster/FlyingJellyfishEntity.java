package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class FlyingJellyfishEntity extends FlyingMob implements Enemy
{
	public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(FlyingJellyfishEntity.class, EntityDataSerializers.BOOLEAN);

	public FlyingJellyfishEntity(EntityType<? extends FlyingJellyfishEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.moveControl = new FlyingJellyfishEntity.MoveHelperController(this);
	}

	public FlyingJellyfishEntity(Level worldIn)
	{
		this(AerialHellEntities.FLYING_JELLYFISH.get(), worldIn);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(5, new FlyingJellyfishEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new FlyingJellyfishEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new FlyingJellyfishEntity.PoisonballAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
	}

	public static AttributeSupplier.Builder registerAttributes()
	{
		return createMobAttributes()
				.add(Attributes.MAX_HEALTH, 5.0D)
				.add(Attributes.FOLLOW_RANGE, 100.0D);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACKING, false);
	}
	
	public boolean isAttacking()
	{
		return this.entityData.get(ATTACKING);
	}

	public void setAttacking(boolean isAttacking)
	{
		this.entityData.set(ATTACKING, isAttacking);
	}

	@Override protected boolean shouldDespawnInPeaceful() {return true;}

	@Override
	public void aiStep()
	{
		super.aiStep();
		if (this.getY() < 0 || this.getY() > 256)
		{
			this.discard();
		}
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer)
	{
		return true;
	}

	@Override
	protected float getSoundVolume()
	{
		return 1.2F;
	}

	public static boolean canJellyfishSpawn(EntityType<? extends FlyingJellyfishEntity> jellyfish, LevelAccessor worldIn, MobSpawnType reason,	BlockPos pos, Random random)
	{
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(15) == 0 && checkMobSpawnRules(jellyfish, worldIn, reason, pos, random);
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.FireballAttackGoal but changed GhastEntity to FlyingJellyfishEntity */
	static class PoisonballAttackGoal extends Goal
	{
		private final FlyingJellyfishEntity parentEntity;
		public int attackTimer;

		public PoisonballAttackGoal(FlyingJellyfishEntity jellyfish)
		{
			this.parentEntity = jellyfish;
		}

		@Override
		public boolean canUse()
		{
			return parentEntity.getTarget() != null;
		}

		@Override
		public void start()
		{
			this.attackTimer = 0;
		}
		
		@Override
		public void stop()
		{
			this.parentEntity.setAttacking(false);
		}

		@Override
		public void tick()
		{
			LivingEntity target = parentEntity.getTarget();
			if (target.distanceToSqr(this.parentEntity) < 64*64 && this.parentEntity.hasLineOfSight(target))
			{
				Level world = this.parentEntity.level;
				++this.attackTimer;
				if (this.attackTimer == 10)
				{
					this.parentEntity.playSound(this.parentEntity.getAmbientSound(), 3.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);
				}
				else if (this.attackTimer == 20)
				{
					double Xdistance = target.getX() - (this.parentEntity.getX());
					double Ydistance = target.getY(0.5)  -  this.parentEntity.getY(0.5);
					double Zdistance = target.getZ() - (this.parentEntity.getZ());
					this.parentEntity.playSound(SoundEvents.SHULKER_SHOOT, 1.2F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 2.0F);
					PoisonballEntity snowballentity = new PoisonballEntity(world, this.parentEntity, Xdistance, Ydistance, Zdistance);
					snowballentity.setPos(this.parentEntity.getX(), this.parentEntity.getY(0.5), this.parentEntity.getZ());
					world.addFreshEntity(snowballentity);
					this.attackTimer = -40;
				}
			}
			else if (this.attackTimer > 0)
			{
				this.attackTimer--;
			}
			
			this.parentEntity.setAttacking(attackTimer > 12);
		}
	}
	
	/* Same as net.minecraft.entity.monster.GhastEntity.RandomFlyGoal but changed GhastEntity to FlyingJellyfishEntity */
	static class RandomFlyGoal extends Goal
	{
		private final FlyingJellyfishEntity parentEntity;

		public RandomFlyGoal(FlyingJellyfishEntity entity)
		{
			this.parentEntity = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override
		public boolean canUse()
		{
			MoveControl movementcontroller = this.parentEntity.getMoveControl();
			if (!movementcontroller.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
				double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
				double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
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
			Random random = this.parentEntity.getRandom();
			double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
		}
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.MoveHelperController but changed GhastEntity to FlyingJellyfishEntity */
	static class MoveHelperController extends MoveControl
	{
		private final FlyingJellyfishEntity parentEntity;
		private int courseChangeCooldown;

		public MoveHelperController(FlyingJellyfishEntity jellyfish)
		{
			super(jellyfish);
			this.parentEntity = jellyfish;
		}

		@Override
		public void tick() {
			if (this.operation == MoveControl.Operation.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
					Vec3 vec = new Vec3(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
					double d0 = vec.length();
					vec = vec.normalize();
					if (this.canReach(vec, Mth.ceil(d0)))
					{
						this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec.scale(0.1)));
					}
					else
					{
						this.operation = MoveControl.Operation.WAIT;
					}
				}
			}
		}

		private boolean canReach(Vec3 pos, int distance)
		{
			AABB axisalignedbb = this.parentEntity.getBoundingBox();

			for (int i = 1; i < distance; ++i)
			{
				axisalignedbb = axisalignedbb.move(pos);
				if (!this.parentEntity.level.noCollision(this.parentEntity, axisalignedbb)) {return false;}
			}

			return true;
		}

	}

	/* Same as net.minecraft.entity.monster.GhastEntity.LookAroundGoal but changed GhastEntity to FlyingJellyfishEntity */
	static class LookAroundGoal extends Goal
	{
		private final FlyingJellyfishEntity parentEntity;

		public LookAroundGoal(FlyingJellyfishEntity jellyfish)
		{
			this.parentEntity = jellyfish;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return true;
		}

		@Override
		public void tick()
		{				
			if (this.parentEntity.getTarget() == null)
			{
				Vec3 vec = this.parentEntity.getDeltaMovement();
				this.parentEntity.setYRot(-((float)Mth.atan2(vec.x, vec.z)) * (180F / (float)Math.PI));
				this.parentEntity.yBodyRot = this.parentEntity.getYRot();
			}
			else
			{
				LivingEntity livingentity = this.parentEntity.getTarget();
				if (livingentity.distanceToSqr(this.parentEntity) < 64*64)
				{
					double x = livingentity.getX() - this.parentEntity.getX();
					double z = livingentity.getZ() - this.parentEntity.getZ();
					this.parentEntity.setYRot(-((float)Mth.atan2(x, z)) * (180F / (float)Math.PI));
					this.parentEntity.yBodyRot = this.parentEntity.getYRot();
				}
			}
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return AerialHellSoundEvents.ENTITY_FLYING_JELLYFISH_HURT.get();
	}
}