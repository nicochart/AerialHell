package fr.factionbedrock.aerialhell.Entity.Passive;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FatPhantomEntity extends FlyingEntity implements IMob
{
   private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(FatPhantomEntity.class, DataSerializers.VARINT);
   public List<PlayerEntity> attackingPlayers = Lists.newArrayList();
   private Vector3d orbitOffset = Vector3d.ZERO;
   private BlockPos orbitPosition = BlockPos.ZERO;
   private FatPhantomEntity.AttackPhase attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
   public static final DataParameter<Boolean> DISAPPEARING = EntityDataManager.<Boolean>createKey(FatPhantomEntity.class, DataSerializers.BOOLEAN);
   private int timeDisappearing;

   public FatPhantomEntity(EntityType<? extends FatPhantomEntity> type, World worldIn)
   {
      super(type, worldIn);
      this.experienceValue = 5;
      this.moveController = new FatPhantomEntity.MoveHelperController(this);
      this.lookController = new FatPhantomEntity.LookHelperController(this);
   }
   
   public static boolean canSpawn(EntityType<FatPhantomEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
   {
	   //return randomIn.nextInt(10) == 0 && worldIn.getWorld().isDaytime(); //probleme : spawn en masse
	   return false;
   }
   
   @Override
   protected BodyController createBodyController()
   {
      return new FatPhantomEntity.BodyHelperController(this);
   }

   @Override
   protected void registerGoals()
   {
      this.goalSelector.addGoal(1, new FatPhantomEntity.PickAttackGoal());
      this.goalSelector.addGoal(2, new FatPhantomEntity.SweepAttackGoal());
      this.goalSelector.addGoal(3, new FatPhantomEntity.OrbitPointGoal());
      this.targetSelector.addGoal(1, new FatPhantomEntity.AttackAttackingPlayerGoal());
   }
   
   public static AttributeModifierMap.MutableAttribute registerAttributes()
   {
       return CreatureEntity.func_233666_p_()
       		.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
       		.createMutableAttribute(Attributes.MAX_HEALTH, 150.0D)
       		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 18.0D)
       		.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
       		.createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D);
   }
   
   @Override
   public boolean attackEntityAsMob(Entity entityIn)
   {
	   if (entityIn instanceof PlayerEntity && !this.attackingPlayers.isEmpty())
	   {
		   PlayerEntity playerIn = (PlayerEntity) entityIn;
		   if (this.attackingPlayers.contains(playerIn)) {this.attackingPlayers.remove(playerIn);}
	   }
	   return super.attackEntityAsMob(entityIn);
   }
   
   @Override
   public boolean attackEntityFrom(DamageSource source, float amount)
   {
	   
	   if (!source.isMagicDamage())
	   {
		   
		   if (source.getImmediateSource() instanceof PlayerEntity)
		   {
			   PlayerEntity player = (PlayerEntity) source.getImmediateSource();
			   attackingPlayers.add(player);
		   }
		   else if (source.getImmediateSource() instanceof ProjectileEntity)
		   {
			   List<PlayerEntity> targetable_players = FatPhantomEntity.this.world.getTargettablePlayersWithinAABB((new EntityPredicate()).setDistance(64.0D), FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
			   if (!targetable_players.isEmpty())
			   {
				   for (PlayerEntity player : targetable_players)
				   {
					   attackingPlayers.add(player);
				   }
			   }
		   }
	   }
	   return super.attackEntityFrom(source, amount);
   }
   
   @Override
   protected void registerData()
   {
      super.registerData();
      this.dataManager.register(SIZE, 0);
      this.dataManager.register(DISAPPEARING, false);
   }
   
   public int getDefaultFatPhantomSize()
   {
	   return 12;
   }
   
   public void setPhantomSize(int sizeIn)
   {
      this.dataManager.set(SIZE, MathHelper.clamp(sizeIn, 0, 64));
   }

   private void updatePhantomSize()
   {
      this.recalculateSize();
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)(Math.max(16 + this.getPhantomSize() - getDefaultFatPhantomSize(), 16)));
   }

   public int getPhantomSize()
   {
      return this.dataManager.get(SIZE);
   }
   
   @Override
   protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
   {
      return sizeIn.height * 0.35F;
   }
   
   @Override
   public void notifyDataManagerChange(DataParameter<?> key)
   {
      if (SIZE.equals(key))
      {
         this.updatePhantomSize();
      }

      super.notifyDataManagerChange(key);
   }

   @Override
   public void tick()
   {
      super.tick();
      if (this.world.isRemote)
      {
         float f = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted) * 0.13F + (float)Math.PI);
         float f1 = MathHelper.cos((float)(this.getEntityId() * 3 + this.ticksExisted + 1) * 0.13F + (float)Math.PI);
         if (f > 0.0F && f1 <= 0.0F)
         {
            this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.rand.nextFloat() * 0.05F, 0.95F + this.rand.nextFloat() * 0.05F, false);
         }

         int i = this.getPhantomSize();
         float f2 = MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f3 = MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f4 = (0.3F + f * 0.45F) * ((float)i * 0.2F + 1.0F);
         this.world.addParticle(ParticleTypes.MYCELIUM, this.getPosX() + (double)f2, this.getPosY() + (double)f4, this.getPosZ() + (double)f3, 0.0D, 0.0D, 0.0D);
         this.world.addParticle(ParticleTypes.MYCELIUM, this.getPosX() - (double)f2, this.getPosY() + (double)f4, this.getPosZ() - (double)f3, 0.0D, 0.0D, 0.0D);
      }
      
      if (this.isDisappearing())
      {
    	  if (this.timeDisappearing < 190)
    	  {
    		  this.addFatPhantomParticle(1);
    	  }
    	  else if (this.timeDisappearing < 200)
    	  {
    		  this.addFatPhantomParticle(10);
    	  }
    	  else
    	  {
    		  this.remove();
    	  }
    	  this.timeDisappearing++;
      }
   }
   
   private void addFatPhantomParticle(int number)
   {
   		for (int i=0; i<number; i++)
   		{
   			this.world.addParticle(AerialHellParticleTypes.FAT_PHANTOM_SMOKE.get(), this.getPosX() + rand.nextFloat() - 0.5, this.getPosY() + 2 * rand.nextFloat(), this.getPosZ() + rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() -0.5, rand.nextFloat() - 0.5);
   		}
   }
   
   @Override
   public boolean isImmuneToFire()
   {
	   return false;
   }
   
   @Override
   public void livingTick()
   {
	   if (!this.world.isDaytime())
	   {
		   if (!this.isDisappearing())
		   {
			   this.timeDisappearing = 0;
			   this.setDisappearing(true);
		   }
	   }   
	   super.livingTick();
   }

   public boolean isDisappearing()
   {
   	return this.dataManager.get(DISAPPEARING);
   }
   
   public void setDisappearing(boolean flag)
   {
   	this.dataManager.set(DISAPPEARING, flag);
   }
   
   @Override
   public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
   {
      this.orbitPosition = this.getPosition().up(6);
      this.setPhantomSize(12);
      this.timeDisappearing = 0; this.setDisappearing(false);
      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
   }

   @Override
   public void readAdditional(CompoundNBT compound)
   {
      super.readAdditional(compound);
      if (compound.contains("AX"))
      {
         this.orbitPosition = new BlockPos(compound.getInt("AX"), compound.getInt("AY"), compound.getInt("AZ"));
      }

      this.setPhantomSize(compound.getInt("Size"));
      this.setDisappearing(compound.getBoolean("Disappearing"));
   }

   @Override
   public void writeAdditional(CompoundNBT compound)
   {
      super.writeAdditional(compound);
      compound.putInt("AX", this.orbitPosition.getX());
      compound.putInt("AY", this.orbitPosition.getY());
      compound.putInt("AZ", this.orbitPosition.getZ());
      compound.putInt("Size", this.getPhantomSize());
      compound.putBoolean("Disappearing", this.isDisappearing());
   }

   @Override
   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance)
   {
      return true;
   }

   @Override
   public SoundCategory getSoundCategory()
   {
      return SoundCategory.HOSTILE;
   }

   @Override
   protected SoundEvent getAmbientSound()
   {
      return SoundEvents.ENTITY_PHANTOM_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn)
   {
      return SoundEvents.ENTITY_PHANTOM_HURT;
   }

   @Override
   protected SoundEvent getDeathSound()
   {
      return SoundEvents.ENTITY_PHANTOM_DEATH;
   }

   @Override
   protected float getSoundVolume()
   {
      return 1.0F;
   }

   @Override
   public boolean canAttack(EntityType<?> typeIn)
   {
      return true;
   }

   @Override
   public EntitySize getSize(Pose poseIn)
   {
      int i = this.getPhantomSize();
      EntitySize entitysize = super.getSize(poseIn);
      float f = (entitysize.width + 0.2F * (float)i) / entitysize.width;
      return entitysize.scale(f);
   }

   static enum AttackPhase
   {
      CIRCLE,
      SWOOP;
   }

   class AttackAttackingPlayerGoal extends Goal
   {
      private final EntityPredicate predicate = (new EntityPredicate()).setDistance(64.0D);
      private int tickDelay = 20;

      private AttackAttackingPlayerGoal() {}

      public boolean shouldExecute()
      {
         if (this.tickDelay > 0)
         {
            --this.tickDelay;
            return false;
         }
         else
         {
            this.tickDelay = 60;
            List<PlayerEntity> list = FatPhantomEntity.this.world.getTargettablePlayersWithinAABB(this.predicate, FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
            if (!attackingPlayers.isEmpty() && !list.isEmpty())
            {
               list.sort(Comparator.<Entity, Double>comparing(Entity::getPosY).reversed());

               for(PlayerEntity player : list)
               {
                  if (FatPhantomEntity.this.canAttack(player, EntityPredicate.DEFAULT) && attackingPlayers.contains(player))
                  {
                     FatPhantomEntity.this.setAttackTarget(player);
                     return true;
                  }
               }
            }
            return false;
         }
      }

      public boolean shouldContinueExecuting()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getAttackTarget();
         return livingentity != null ? FatPhantomEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
      }
   }

   class BodyHelperController extends BodyController
   {
      public BodyHelperController(MobEntity mob)
      {
         super(mob);
      }

      public void updateRenderAngles()
      {
         FatPhantomEntity.this.rotationYawHead = FatPhantomEntity.this.renderYawOffset;
         FatPhantomEntity.this.renderYawOffset = FatPhantomEntity.this.rotationYaw;
      }
   }

   class LookHelperController extends LookController
   {
      public LookHelperController(MobEntity entityIn)
      {
         super(entityIn);
      }

      public void tick() {}
   }

   abstract class MoveGoal extends Goal
   {
      public MoveGoal()
      {
         this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
      }

      protected boolean func_203146_f()
      {
         return FatPhantomEntity.this.orbitOffset.squareDistanceTo(FatPhantomEntity.this.getPosX(), FatPhantomEntity.this.getPosY(), FatPhantomEntity.this.getPosZ()) < 4.0D;
      }
   }

   class MoveHelperController extends MovementController
   {
      private float speedFactor = 0.1F;

      public MoveHelperController(MobEntity entityIn)
      {
         super(entityIn);
      }

      public void tick()
      {
         if (FatPhantomEntity.this.collidedHorizontally)
         {
            FatPhantomEntity.this.rotationYaw += 180.0F;
            this.speedFactor = 0.1F;
         }

         float f = (float)(FatPhantomEntity.this.orbitOffset.x - FatPhantomEntity.this.getPosX());
         float f1 = (float)(FatPhantomEntity.this.orbitOffset.y - FatPhantomEntity.this.getPosY());
         float f2 = (float)(FatPhantomEntity.this.orbitOffset.z - FatPhantomEntity.this.getPosZ());
         double d0 = (double)MathHelper.sqrt(f * f + f2 * f2);
         double d1 = 1.0D - (double)MathHelper.abs(f1 * 0.7F) / d0;
         f = (float)((double)f * d1);
         f2 = (float)((double)f2 * d1);
         d0 = (double)MathHelper.sqrt(f * f + f2 * f2);
         double d2 = (double)MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
         float f3 = FatPhantomEntity.this.rotationYaw;
         float f4 = (float)MathHelper.atan2((double)f2, (double)f);
         float f5 = MathHelper.wrapDegrees(FatPhantomEntity.this.rotationYaw + 90.0F);
         float f6 = MathHelper.wrapDegrees(f4 * (180F / (float)Math.PI));
         FatPhantomEntity.this.rotationYaw = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
         FatPhantomEntity.this.renderYawOffset = FatPhantomEntity.this.rotationYaw;
         if (MathHelper.degreesDifferenceAbs(f3, FatPhantomEntity.this.rotationYaw) < 3.0F)
         {
            this.speedFactor = MathHelper.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
         }
         else
         {
            this.speedFactor = MathHelper.approach(this.speedFactor, 0.2F, 0.025F);
         }

         float f7 = (float)(-(MathHelper.atan2((double)(-f1), d0) * (double)(180F / (float)Math.PI)));
         FatPhantomEntity.this.rotationPitch = f7;
         float f8 = FatPhantomEntity.this.rotationYaw + 90.0F;
         double d3 = (double)(this.speedFactor * MathHelper.cos(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f / d2);
         double d4 = (double)(this.speedFactor * MathHelper.sin(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f2 / d2);
         double d5 = (double)(this.speedFactor * MathHelper.sin(f7 * ((float)Math.PI / 180F))) * Math.abs((double)f1 / d2);
         Vector3d vector3d = FatPhantomEntity.this.getMotion();
         FatPhantomEntity.this.setMotion(vector3d.add((new Vector3d(d3, d5, d4)).subtract(vector3d).scale(0.2D)));
      }
   }

   class OrbitPointGoal extends FatPhantomEntity.MoveGoal
   {
      private float field_203150_c;
      private float field_203151_d;
      private float field_203152_e;
      private float field_203153_f;

      private OrbitPointGoal() {}

      public boolean shouldExecute() 
      {
         return FatPhantomEntity.this.getAttackTarget() == null || FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE;
      }

      public void startExecuting()
      {
         this.field_203151_d = 5.0F + FatPhantomEntity.this.rand.nextFloat() * 10.0F;
         this.field_203152_e = -4.0F + FatPhantomEntity.this.rand.nextFloat() * 9.0F;
         this.field_203153_f = FatPhantomEntity.this.rand.nextBoolean() ? 1.0F : -1.0F;
         this.func_203148_i();
      }

      public void tick()
      {
         if (FatPhantomEntity.this.rand.nextInt(350) == 0)
         {
            this.field_203152_e = -4.0F + FatPhantomEntity.this.rand.nextFloat() * 9.0F;
         }

         if (FatPhantomEntity.this.rand.nextInt(250) == 0)
         {
            ++this.field_203151_d;
            if (this.field_203151_d > 15.0F)
            {
               this.field_203151_d = 5.0F;
               this.field_203153_f = -this.field_203153_f;
            }
         }

         if (FatPhantomEntity.this.rand.nextInt(450) == 0)
         {
            this.field_203150_c = FatPhantomEntity.this.rand.nextFloat() * 2.0F * (float)Math.PI;
            this.func_203148_i();
         }

         if (this.func_203146_f())
         {
            this.func_203148_i();
         }

         if (FatPhantomEntity.this.orbitOffset.y < FatPhantomEntity.this.getPosY() && !FatPhantomEntity.this.world.isAirBlock(FatPhantomEntity.this.getPosition().down(1)))
         {
            this.field_203152_e = Math.max(1.0F, this.field_203152_e);
            this.func_203148_i();
         }

         if (FatPhantomEntity.this.orbitOffset.y > FatPhantomEntity.this.getPosY() && !FatPhantomEntity.this.world.isAirBlock(FatPhantomEntity.this.getPosition().up(1)))
         {
            this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
            this.func_203148_i();
         }
      }

      private void func_203148_i()
      {
         if (BlockPos.ZERO.equals(FatPhantomEntity.this.orbitPosition))
         {
            FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getPosition();
         }

         this.field_203150_c += this.field_203153_f * 15.0F * ((float)Math.PI / 180F);
         FatPhantomEntity.this.orbitOffset = Vector3d.copy(FatPhantomEntity.this.orbitPosition).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
      }
   }

   class PickAttackGoal extends Goal
   {
      private int tickDelay;

      private PickAttackGoal() {}

      public boolean shouldExecute()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getAttackTarget();
         return livingentity != null ? FatPhantomEntity.this.canAttack(FatPhantomEntity.this.getAttackTarget(), EntityPredicate.DEFAULT) : false;
      }

      public void startExecuting()
      {
         this.tickDelay = 10;
         FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         this.func_203143_f();
      }

      public void resetTask()
      {
         FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, FatPhantomEntity.this.orbitPosition).up(10 + FatPhantomEntity.this.rand.nextInt(20));
      }

      public void tick()
      {
         if (FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE)
         {
            --this.tickDelay;
            if (this.tickDelay <= 0)
            {
               FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.SWOOP;
               this.func_203143_f();
               this.tickDelay = (8 + FatPhantomEntity.this.rand.nextInt(4)) * 20;
               FatPhantomEntity.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + FatPhantomEntity.this.rand.nextFloat() * 0.1F);
            }
         }
      }

      private void func_203143_f()
      {
         FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getAttackTarget().getPosition().up(20 + FatPhantomEntity.this.rand.nextInt(20));
         if (FatPhantomEntity.this.orbitPosition.getY() < FatPhantomEntity.this.world.getSeaLevel())
         {
            FatPhantomEntity.this.orbitPosition = new BlockPos(FatPhantomEntity.this.orbitPosition.getX(), FatPhantomEntity.this.world.getSeaLevel() + 1, FatPhantomEntity.this.orbitPosition.getZ());
         }
      }
   }

   class SweepAttackGoal extends FatPhantomEntity.MoveGoal
   {
      private SweepAttackGoal() {}

      public boolean shouldExecute()
      {
         return FatPhantomEntity.this.getAttackTarget() != null && FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.SWOOP;
      }

      public boolean shouldContinueExecuting()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getAttackTarget();
         if (livingentity == null)
         {
            return false;
         }
         else if (!livingentity.isAlive())
         {
            return false;
         }
         else if (!(livingentity instanceof PlayerEntity) || !((PlayerEntity)livingentity).isSpectator() && !((PlayerEntity)livingentity).isCreative())
         {
            if (!this.shouldExecute())
            {
               return false;
            }
            else
            {
               if (FatPhantomEntity.this.ticksExisted % 20 == 0)
               {
                  List<CatEntity> list = FatPhantomEntity.this.world.getEntitiesWithinAABB(CatEntity.class, FatPhantomEntity.this.getBoundingBox().grow(16.0D), EntityPredicates.IS_ALIVE);
                  if (!list.isEmpty())
                  {
                     for(CatEntity catentity : list)
                     {
                        catentity.func_213420_ej();
                     }
                     return false;
                  }
               }
               return true;
            }
         }
         else
         {
            return false;
         }
      }

      public void startExecuting() {}

      public void resetTask()
      {
         FatPhantomEntity.this.setAttackTarget((LivingEntity)null);
         FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
      }

      public void tick()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getAttackTarget();
         FatPhantomEntity.this.orbitOffset = new Vector3d(livingentity.getPosX(), livingentity.getPosYHeight(0.5D), livingentity.getPosZ());
         if (FatPhantomEntity.this.getBoundingBox().grow((double)0.2F).intersects(livingentity.getBoundingBox()))
         {
            FatPhantomEntity.this.attackEntityAsMob(livingentity);
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
            if (!FatPhantomEntity.this.isSilent())
            {
               FatPhantomEntity.this.world.playEvent(1039, FatPhantomEntity.this.getPosition(), 0);
            }
         }
         else if (FatPhantomEntity.this.collidedHorizontally || FatPhantomEntity.this.hurtTime > 0)
         {
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         }
      }
   }
}

