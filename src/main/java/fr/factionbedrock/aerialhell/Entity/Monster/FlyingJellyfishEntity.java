package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;

public class FlyingJellyfishEntity extends FlyingEntity implements IMob
{
	public static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(FlyingJellyfishEntity.class, DataSerializers.BOOLEAN);

	public FlyingJellyfishEntity(EntityType<? extends FlyingJellyfishEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.moveController = new FlyingJellyfishEntity.MoveHelperController(this);
	}

	public FlyingJellyfishEntity(World worldIn)
	{
		this(AerialHellEntities.FLYING_JELLYFISH.get(), worldIn);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(5, new FlyingJellyfishEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new FlyingJellyfishEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new FlyingJellyfishEntity.SnowballAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return FlyingEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 5.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 100.0D);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(ATTACKING, false);
	}
	
	public boolean isAttacking()
	{
		return this.dataManager.get(ATTACKING);
	}

	public void setAttacking(boolean isAttacking)
	{
		this.dataManager.set(ATTACKING, isAttacking);
	}

	@Override
	protected boolean isDespawnPeaceful()
	{
		return true;
	}

	@Override
	public void livingTick()
	{
		super.livingTick();
		if (this.getPosY() < 0 || this.getPosY() > 256)
		{
			this.remove();
		}
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
		return true;
	}

	@Override
	protected float getSoundVolume()
	{
		return 1.2F;
	}

	public static boolean canJellyfishSpawn(EntityType<? extends FlyingJellyfishEntity> jellyfish, IWorld worldIn, SpawnReason reason,	BlockPos pos, Random random)
	{
		AxisAlignedBB boundingBox = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4);
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(65) == 0 &&
			   worldIn.getEntitiesWithinAABB(FlyingJellyfishEntity.class, boundingBox).size() == 0 &&
			   worldIn.getBiome(pos).equals(AerialHellBiomes.AERIAL_TREE_FOREST) &&
			   !worldIn.containsAnyLiquid(boundingBox) && worldIn.getLight(pos) > 8 &&
			   canSpawnOn(jellyfish, worldIn, reason, pos, random);
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.FireballAttackGoal but changed GhastEntity to FlyingJellyfishEntity */
	static class SnowballAttackGoal extends Goal
	{
		private final FlyingJellyfishEntity parentEntity;
		public int attackTimer;

		public SnowballAttackGoal(FlyingJellyfishEntity jellyfish)
		{
			this.parentEntity = jellyfish;
		}

		@Override
		public boolean shouldExecute()
		{
			return parentEntity.getAttackTarget() != null;
		}

		@Override
		public void startExecuting()
		{
			this.attackTimer = 0;
		}
		
		@Override
		public void resetTask()
		{
			this.parentEntity.setAttacking(false);
		}

		@Override
		public void tick()
		{
			LivingEntity target = parentEntity.getAttackTarget();
			if (target.getDistanceSq(this.parentEntity) < 64*64 && this.parentEntity.canEntityBeSeen(target))
			{
				World world = this.parentEntity.world;
				++this.attackTimer;
				if (this.attackTimer == 10)
				{
					this.parentEntity.playSound(this.parentEntity.getAmbientSound(), 3.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				}
				else if (this.attackTimer == 20)
				{
					Vector3d look = this.parentEntity.getLook(1.0F);
					double accelX = target.getPosX() - (this.parentEntity.getPosX() + look.x * 4.0);
					double accelY = target.getPosYHeight(0.5)  - (0.5 + this.parentEntity.getPosYHeight(0.5));
					double accelZ = target.getPosZ() - (this.parentEntity.getPosZ() + look.z * 4.0);
					this.parentEntity.playSound(SoundEvents.ENTITY_SHULKER_SHOOT, 1.2F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 2.0F);
					PoisonballEntity snowballentity = new PoisonballEntity(world, this.parentEntity, accelX, accelY, accelZ);
					snowballentity.setPosition(this.parentEntity.getPosX() + look.x * 1.5, this.parentEntity.getPosYHeight(0.5) + 0.1, this.parentEntity.getPosZ() + look.z * 1.5);
					world.addEntity(snowballentity);
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
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override
		public boolean shouldExecute()
		{
			MovementController movementcontroller = this.parentEntity.getMoveHelper();
			if (!movementcontroller.isUpdating())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getX() - this.parentEntity.getPosX();
				double d1 = movementcontroller.getY() - this.parentEntity.getPosY();
				double d2 = movementcontroller.getZ() - this.parentEntity.getPosZ();
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
			Random random = this.parentEntity.getRNG();
			double d0 = this.parentEntity.getPosX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.parentEntity.getPosY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.parentEntity.getPosZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0);
		}
	}

	/* Same as net.minecraft.entity.monster.GhastEntity.MoveHelperController but changed GhastEntity to FlyingJellyfishEntity */
	static class MoveHelperController extends MovementController
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
			if (this.action == MovementController.Action.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					Vector3d vec3d = new Vector3d(this.posX - this.parentEntity.getPosX(), this.posY - this.parentEntity.getPosY(), this.posZ - this.parentEntity.getPosZ());
					double d0 = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.isNotColliding(vec3d, MathHelper.ceil(d0)))
					{
						this.parentEntity.setMotion(this.parentEntity.getMotion().add(vec3d.scale(0.1)));
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
			AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

			for (int i = 1; i < distance; ++i)
			{
				axisalignedbb = axisalignedbb.offset(pos);
				if (!this.parentEntity.world.hasNoCollisions(this.parentEntity, axisalignedbb))
				{
					return false;
				}
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
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean shouldExecute()
		{
			return true;
		}

		@Override
		public void tick()
		{				
			if (this.parentEntity.getAttackTarget() == null)
			{
				Vector3d vec3d = this.parentEntity.getMotion();
				this.parentEntity.rotationYaw = -((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180.0F / (float)Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			}
			else
			{
				LivingEntity livingentity = this.parentEntity.getAttackTarget();
				if (livingentity.getDistanceSq(this.parentEntity) < 64*64)
				{
					double x = livingentity.getPosX() - this.parentEntity.getPosX();
					double z = livingentity.getPosZ() - this.parentEntity.getPosZ();
					this.parentEntity.rotationYaw = -((float)MathHelper.atan2(x, z)) * (180.0F / (float)Math.PI);
					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				}
			}
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_SILVERFISH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_SILVERFISH_DEATH;
	}
}