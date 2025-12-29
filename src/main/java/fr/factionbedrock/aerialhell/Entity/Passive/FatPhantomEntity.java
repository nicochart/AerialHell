package fr.factionbedrock.aerialhell.Entity.Passive;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class FatPhantomEntity extends Phantom implements Enemy
{
   public List<Player> attackingPlayers = Lists.newArrayList();
   private Vec3 orbitOffset = Vec3.ZERO;
   private BlockPos orbitPosition = BlockPos.ZERO;
   private FatPhantomEntity.AttackPhase attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
   public static final EntityDataAccessor<Boolean> DISAPPEARING = SynchedEntityData.defineId(FatPhantomEntity.class, EntityDataSerializers.BOOLEAN);
   private int timeDisappearing;

   public FatPhantomEntity(EntityType<? extends FatPhantomEntity> type, Level worldIn)
   {
      super(type, worldIn);
      this.xpReward = 5;
      this.moveControl = new FatPhantomEntity.MoveHelperController(this);
      this.lookControl = new FatPhantomEntity.LookHelperController(this);
   }
   
   public static boolean canSpawn(EntityType<FatPhantomEntity> type, ServerLevelAccessor worldIn, EntitySpawnReason reason, BlockPos pos, RandomSource randomIn)
   {
	   return worldIn.getDifficulty() != Difficulty.PEACEFUL && worldIn.getLevel().isBrightOutside() && randomIn.nextInt(120) == 0 && checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
   }

   @Override public void refreshDimensions() {}

   @Override
   protected BodyRotationControl createBodyControl()
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
   
   public static AttributeSupplier.Builder registerAttributes()
   {
       return Mob.createMobAttributes()
       		.add(Attributes.MOVEMENT_SPEED, 0.25D)
       		.add(Attributes.MAX_HEALTH, 150.0D)
       		.add(Attributes.ATTACK_DAMAGE, 18.0D)
       		.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
       		.add(Attributes.FOLLOW_RANGE, 64.0D);
   }
   
   @Override
   public boolean doHurtTarget(ServerLevel level, Entity entityIn)
   {
	   if (entityIn instanceof Player && !this.attackingPlayers.isEmpty())
	   {
		   Player playerIn = (Player) entityIn;
		   if (this.attackingPlayers.contains(playerIn)) {this.attackingPlayers.remove(playerIn);}
	   }
	   return super.doHurtTarget(level, entityIn);
   }
   
   @Override
   public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
   {
	   
	   if (!source.is(DamageTypes.MAGIC))
	   {
		   if (source.getDirectEntity() instanceof Player)
		   {
			   Player player = (Player) source.getDirectEntity();
			   attackingPlayers.add(player);
		   }
		   else if (source.getDirectEntity() instanceof Projectile)
		   {
			   List<Player> targetable_players = level.getNearbyPlayers(TargetingConditions.forCombat().range(64.0D), FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
			   if (!targetable_players.isEmpty())
			   {
				   for (Player player : targetable_players)
				   {
					   attackingPlayers.add(player);
				   }
			   }
		   }
	   }
	   return super.hurtServer(level, source, amount);
   }
   
   @Override
   protected void defineSynchedData(SynchedEntityData.Builder builder)
   {
      super.defineSynchedData(builder);
      builder.define(DISAPPEARING, false);
   }
   
   public int getDefaultFatPhantomSize()
   {
	   return 12;
   }

   private void updatePhantomSize()
   {
      this.refreshDimensions();
      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)(Math.max(16 + this.getPhantomSize() - getDefaultFatPhantomSize(), 16)));
   }
   
   @Override
   public void onSyncedDataUpdated(EntityDataAccessor<?> key)
   {
      if (ID_SIZE.equals(key)) {this.updatePhantomSize();}
      //super.onSyncedDataUpdated(key); not needed
   }

   @Override
   public void tick()
   {
      super.tick();
      if (this.level().isClientSide())
      {
         float f = Mth.cos((float)(this.getId() * 3 + this.tickCount) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
         float f1 = Mth.cos((float)(this.getId() * 3 + this.tickCount + 1) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
         if (f > 0.0F && f1 <= 0.0F)
         {
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
         }

         int i = this.getPhantomSize();
         float f2 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f3 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f4 = (0.3F + f * 0.45F) * ((float)i * 0.2F + 1.0F);
         this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
         this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
      }
      
      if (this.isDisappearing())
      {
    	  if (this.timeDisappearing < 190) {this.addFatPhantomParticle(1);}
    	  else if (this.timeDisappearing < 200) {this.addFatPhantomParticle(10);}
    	  else {this.discard();}
    	  this.timeDisappearing++;
      }
   }

   private void addFatPhantomParticle(int number)
   {
   		for (int i=0; i<number; i++)
   		{
   			this.level().addParticle(AerialHellParticleTypes.FAT_PHANTOM_SMOKE.get(), this.getX() + random.nextFloat() - 0.5, this.getY() + 2 * random.nextFloat(), this.getZ() + random.nextFloat() - 0.5, random.nextFloat() - 0.5, random.nextFloat() -0.5, random.nextFloat() - 0.5);
   		}
   }
   
   @Override public boolean fireImmune() {return false;}

   @Override
   public void aiStep()
   {
	   if (!this.level().isBrightOutside())
	   {
		   if (!this.isDisappearing())
		   {
			   this.timeDisappearing = 0;
			   this.setDisappearing(true);
		   }
	   }   
	   super.aiStep();
   }

   public boolean isDisappearing() {return this.entityData.get(DISAPPEARING);}
   
   public void setDisappearing(boolean flag) {this.entityData.set(DISAPPEARING, flag);}
   
   @Override
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
   {
      SpawnGroupData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
      this.orbitPosition = this.blockPosition().above(6);
      this.setPhantomSize(10 + random.nextInt(6));
      if (worldIn.getBlockState(this.blockPosition().above()).getBlock() == Blocks.AIR)
      {
    	  this.snapTo(new Vec3(blockPosition().above().getX(), blockPosition().above().getY(), blockPosition().above().getZ()));
      }
      this.timeDisappearing = 0; this.setDisappearing(false);
      return data;
   }

   @Override protected void readAdditionalSaveData(ValueInput valueInput)
   {
      super.readAdditionalSaveData(valueInput);
      this.orbitPosition = (BlockPos)valueInput.read("anchor_pos", BlockPos.CODEC).orElse((BlockPos) null);
      this.setPhantomSize(valueInput.getIntOr("size", 0));
      this.setDisappearing(valueInput.getBooleanOr("Disappearing", false));
   }

   @Override protected void addAdditionalSaveData(ValueOutput valueOutput)
   {
      super.addAdditionalSaveData(valueOutput);
      valueOutput.storeNullable("anchor_pos", BlockPos.CODEC, this.orbitPosition);
      valueOutput.putInt("size", this.getPhantomSize());
      valueOutput.putBoolean("Disappearing", this.isDisappearing());
   }

   @Override
   public EntityDimensions getDefaultDimensions(Pose poseIn)
   {
      int i = this.getPhantomSize();
      EntityDimensions entitydimensions = super.getDefaultDimensions(poseIn);
      return entitydimensions.scale(1.0F + 0.15F * (float)i);
   }

   boolean canAttack(ServerLevel level, LivingEntity entity, TargetingConditions targetingConditions)
   {
      return targetingConditions.test(level, this, entity);
   }

   static enum AttackPhase
   {
      CIRCLE,
      SWOOP;
   }

   class AttackAttackingPlayerGoal extends Goal
   {
      private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
      private int tickDelay = 20;

      private AttackAttackingPlayerGoal() {}

      public boolean canUse()
      {
         if (this.tickDelay > 0)
         {
            --this.tickDelay;
            return false;
         }
         else
         {
            this.tickDelay = 60;
            ServerLevel serverlevel = getServerLevel(FatPhantomEntity.this.level());
            List<Player> list = serverlevel.getNearbyPlayers(this.attackTargeting, FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
            if (!attackingPlayers.isEmpty() && !list.isEmpty())
            {
               list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

               for(Player player : list)
               {
                  if (FatPhantomEntity.this.canAttack(serverlevel, player, TargetingConditions.DEFAULT) && attackingPlayers.contains(player))
                  {
                     FatPhantomEntity.this.setTarget(player);
                     return true;
                  }
               }
            }
            return false;
         }
      }

      public boolean canContinueToUse()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         return livingentity != null ? FatPhantomEntity.this.canAttack(getServerLevel(FatPhantomEntity.this.level()), livingentity, TargetingConditions.DEFAULT) : false;
      }
   }

   class BodyHelperController extends BodyRotationControl
   {
      public BodyHelperController(Mob mob)
      {
         super(mob);
      }

      @Override public void clientTick()
      {
         FatPhantomEntity.this.yHeadRot = FatPhantomEntity.this.yBodyRot;
         FatPhantomEntity.this.yBodyRot = FatPhantomEntity.this.getYRot();
      }
   }

   class LookHelperController extends LookControl
   {
      public LookHelperController(Mob entityIn) {super(entityIn);}

      public void tick() {}
   }

   abstract class MoveGoal extends Goal
   {
      public MoveGoal()
      {
         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
      }

      protected boolean touchingTarget()
      {
         return FatPhantomEntity.this.orbitOffset.distanceToSqr(FatPhantomEntity.this.getX(), FatPhantomEntity.this.getY(), FatPhantomEntity.this.getZ()) < 4.0D;
      }
   }

   class MoveHelperController extends MoveControl
   {
      private float speedFactor = 0.1F;

      public MoveHelperController(Mob entityIn)
      {
         super(entityIn);
      }

      public void tick()
      {
         if (FatPhantomEntity.this.horizontalCollision)
         {
            FatPhantomEntity.this.setYRot(FatPhantomEntity.this.getYRot() + 180.0F);
            this.speedFactor = 0.1F;
         }

         double d0 = FatPhantomEntity.this.orbitOffset.x - FatPhantomEntity.this.getX();
         double d1 = FatPhantomEntity.this.orbitOffset.y - FatPhantomEntity.this.getY();
         double d2 = FatPhantomEntity.this.orbitOffset.z - FatPhantomEntity.this.getZ();
         double d3 = Math.sqrt(d0 * d0 + d2 * d2);
         if (Math.abs(d3) > (double) 1.0E-5F) {
            double d4 = 1.0D - Math.abs(d1 * (double) 0.7F) / d3;
            d0 *= d4;
            d2 *= d4;
            d3 = Math.sqrt(d0 * d0 + d2 * d2);
            double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
            float f = FatPhantomEntity.this.getYRot();
            float f1 = (float) Mth.atan2(d2, d0);
            float f2 = Mth.wrapDegrees(FatPhantomEntity.this.getYRot() + 90.0F);
            float f3 = Mth.wrapDegrees(f1 * (180F / (float) Math.PI));
            FatPhantomEntity.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
            FatPhantomEntity.this.yBodyRot = FatPhantomEntity.this.getYRot();
            if (Mth.degreesDifferenceAbs(f, FatPhantomEntity.this.getYRot()) < 3.0F) {
               this.speedFactor = Mth.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
            } else {
               this.speedFactor = Mth.approach(this.speedFactor, 0.2F, 0.025F);
            }

            float f4 = (float) (-(Mth.atan2(-d1, d3) * (double) (180F / (float) Math.PI)));
            FatPhantomEntity.this.setXRot(f4);
            float f5 = FatPhantomEntity.this.getYRot() + 90.0F;
            double d6 = (double) (this.speedFactor * Mth.cos(f5 * ((float) Math.PI / 180F))) * Math.abs(d0 / d5);
            double d7 = (double) (this.speedFactor * Mth.sin(f5 * ((float) Math.PI / 180F))) * Math.abs(d2 / d5);
            double d8 = (double) (this.speedFactor * Mth.sin(f4 * ((float) Math.PI / 180F))) * Math.abs(d1 / d5);
            Vec3 vec3 = FatPhantomEntity.this.getDeltaMovement();
            FatPhantomEntity.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
         }
      }
   }

   class OrbitPointGoal extends FatPhantomEntity.MoveGoal
   {
      private float angle;
      private float distance;
      private float height;
      private float clockwise;

      public boolean canUse() {return FatPhantomEntity.this.getTarget() == null || FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE;}

      public void start()
      {
         this.distance = 5.0F + FatPhantomEntity.this.random.nextFloat() * 10.0F;
         this.height = -4.0F + FatPhantomEntity.this.random.nextFloat() * 9.0F;
         this.clockwise = FatPhantomEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
         this.selectNext();
      }

      public void tick()
      {
         if (FatPhantomEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {this.height = -4.0F + FatPhantomEntity.this.random.nextFloat() * 9.0F;}

         if (FatPhantomEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0)
         {
            ++this.distance;
            if (this.distance > 15.0F)
            {
               this.distance = 5.0F;
               this.clockwise = -this.clockwise;
            }
         }

         if (FatPhantomEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0)
         {
            this.angle = FatPhantomEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
            this.selectNext();
         }

         if (this.touchingTarget()) {this.selectNext();}

         if (FatPhantomEntity.this.orbitOffset.y < FatPhantomEntity.this.getY() && !FatPhantomEntity.this.level().isEmptyBlock(FatPhantomEntity.this.blockPosition().below(1)))
         {
            this.height = Math.max(1.0F, this.height);
            this.selectNext();
         }

         if (FatPhantomEntity.this.orbitOffset.y > FatPhantomEntity.this.getY() && !FatPhantomEntity.this.level().isEmptyBlock(FatPhantomEntity.this.blockPosition().above(1)))
         {
            this.height = Math.min(-1.0F, this.height);
            this.selectNext();
         }

      }

      private void selectNext()
      {
         if (BlockPos.ZERO.equals(FatPhantomEntity.this.orbitPosition)) {FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.blockPosition();}

         this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
         FatPhantomEntity.this.orbitOffset = Vec3.atLowerCornerOf(FatPhantomEntity.this.orbitPosition).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
      }
   }

   class PickAttackGoal extends Goal
   {
      private int nextSweepTick;

      public boolean canUse()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         return livingentity != null ? FatPhantomEntity.this.canAttack(getServerLevel(FatPhantomEntity.this.level()), livingentity, TargetingConditions.DEFAULT) : false;
      }

      public void start()
      {
         this.nextSweepTick = this.adjustedTickDelay(10);
         FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         this.setAnchorAboveTarget();
      }

      public void stop() {FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, FatPhantomEntity.this.orbitPosition).above(10 + FatPhantomEntity.this.random.nextInt(20));}

      public void tick()
      {
         if (FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE)
         {
            --this.nextSweepTick;
            if (this.nextSweepTick <= 0)
            {
               FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.SWOOP;
               this.setAnchorAboveTarget();
               this.nextSweepTick = this.adjustedTickDelay((8 + FatPhantomEntity.this.random.nextInt(4)) * 20);
               FatPhantomEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + FatPhantomEntity.this.random.nextFloat() * 0.1F);
            }
         }
      }

      private void setAnchorAboveTarget()
      {
         FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getTarget().blockPosition().above(20 + FatPhantomEntity.this.random.nextInt(20));
         if (FatPhantomEntity.this.orbitPosition.getY() < FatPhantomEntity.this.level().getSeaLevel())
         {
            FatPhantomEntity.this.orbitPosition = new BlockPos(FatPhantomEntity.this.orbitPosition.getX(), FatPhantomEntity.this.level().getSeaLevel() + 1, FatPhantomEntity.this.orbitPosition.getZ());
         }
      }
   }

   class SweepAttackGoal extends FatPhantomEntity.MoveGoal
   {
      private SweepAttackGoal() {}

      public boolean canUse() {return FatPhantomEntity.this.getTarget() != null && FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.SWOOP;}

      public boolean canContinueToUse()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         if (livingentity == null) {return false;}
         else if (!livingentity.isAlive()) {return false;}
         else if (!(livingentity instanceof Player) || !((Player)livingentity).isSpectator() && !((Player)livingentity).isCreative()) {return this.canUse();}
         else {return false;}
      }

      public void start() {}

      public void stop()
      {
         FatPhantomEntity.this.setTarget((LivingEntity)null);
         FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
      }

      public void tick()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         FatPhantomEntity.this.orbitOffset = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
         if (FatPhantomEntity.this.getBoundingBox().inflate(0.2F).intersects(livingentity.getBoundingBox()))
         {
            FatPhantomEntity.this.doHurtTarget(getServerLevel(FatPhantomEntity.this.level()), livingentity);
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
            if (!FatPhantomEntity.this.isSilent())
            {
               FatPhantomEntity.this.level().levelEvent(1039, FatPhantomEntity.this.blockPosition(), 0);
            }
         }
         else if (FatPhantomEntity.this.horizontalCollision || FatPhantomEntity.this.hurtTime > 0)
         {
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         }
      }
   }
}

