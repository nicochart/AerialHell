package fr.factionbedrock.aerialhell.Entity.Passive;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import org.jetbrains.annotations.Nullable;

public class FatPhantomEntity extends PhantomEntity implements Monster
{
   public List<PlayerEntity> attackingPlayers = Lists.newArrayList();
   private Vec3d orbitOffset = Vec3d.ZERO;
   private BlockPos orbitPosition = BlockPos.ORIGIN;
   private FatPhantomEntity.AttackPhase attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
   public static final TrackedData<Boolean> DISAPPEARING = DataTracker.registerData(FatPhantomEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
   private int timeDisappearing;

   public FatPhantomEntity(EntityType<? extends FatPhantomEntity> type, World world)
   {
      super(type, world);
      this.experiencePoints = 5;
      this.moveControl = new FatPhantomEntity.MoveHelperController(this);
      this.lookControl = new FatPhantomEntity.LookHelperController(this);
   }
   
   public static boolean canSpawn(EntityType<FatPhantomEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random)
   {
	   return world.getDifficulty() != Difficulty.PEACEFUL && world.toServerWorld().isDay() && random.nextInt(120) == 0 && canMobSpawn(type, world, reason, pos, random);
   }

   @Override public void calculateDimensions() {}

   @Override protected BodyControl createBodyControl() {return new FatPhantomEntity.BodyHelperController(this);}

   @Override
   protected void initGoals()
   {
      this.goalSelector.add(1, new FatPhantomEntity.PickAttackGoal());
      this.goalSelector.add(2, new FatPhantomEntity.SweepAttackGoal());
      this.goalSelector.add(3, new FatPhantomEntity.OrbitPointGoal());
      this.targetSelector.add(1, new FatPhantomEntity.AttackAttackingPlayerGoal());
   }

   @Override protected boolean isDisallowedInPeaceful() {return false;}
   
   public static DefaultAttributeContainer.Builder registerAttributes()
   {
       return MobEntity.createMobAttributes()
       		.add(EntityAttributes.MOVEMENT_SPEED, 0.25D)
       		.add(EntityAttributes.MAX_HEALTH, 150.0D)
       		.add(EntityAttributes.ATTACK_DAMAGE, 18.0D)
       		.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0D)
       		.add(EntityAttributes.FOLLOW_RANGE, 64.0D);
   }
   
   @Override
   public boolean tryAttack(ServerWorld serverWorld, Entity entity)
   {
	   if (entity instanceof PlayerEntity playerEntity && !this.attackingPlayers.isEmpty())
	   {
		   if (this.attackingPlayers.contains(playerEntity)) {this.attackingPlayers.remove(playerEntity);}
	   }
	   return super.tryAttack(serverWorld, entity);
   }
   
   @Override
   public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
   {
	   
	   if (!source.isOf(DamageTypes.MAGIC))
	   {
		   if (source.getSource() instanceof PlayerEntity playerEntity)
		   {
			   attackingPlayers.add(playerEntity);
		   }
		   else if (source.getSource() instanceof ProjectileEntity)
		   {
			   List<PlayerEntity> targetable_players = serverWorld.getPlayers(TargetPredicate.createAttackable().setBaseMaxDistance(64.0D), FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().expand(16.0D, 64.0D, 16.0D));
			   if (!targetable_players.isEmpty())
			   {
				   for (PlayerEntity player : targetable_players)
				   {
					   attackingPlayers.add(player);
				   }
			   }
		   }
	   }
	   return super.damage(serverWorld, source, amount);
   }
   
   @Override
   protected void initDataTracker(DataTracker.Builder builder)
   {
      super.initDataTracker(builder);
      builder.add(DISAPPEARING, false);
   }
   
   public int getDefaultFatPhantomSize()
   {
	   return 12;
   }

   private void updatePhantomSize()
   {
      this.calculateDimensions();
      this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue((double)(Math.max(16 + this.getPhantomSize() - getDefaultFatPhantomSize(), 16)));
   }
   
   @Override
   public void onTrackedDataSet(TrackedData<?> data)
   {
      if (SIZE.equals(data)) {this.updatePhantomSize();}
      //super.onTrackedDataSet(key); not needed
   }

   @Override
   public void tick()
   {
      super.tick();
      if (this.getWorld().isClient())
      {
         float f = MathHelper.cos((float)(this.getId() * 3 + this.age) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
         float f1 = MathHelper.cos((float)(this.getId() * 3 + this.age + 1) * 7.448451F * ((float)Math.PI / 180F) + (float)Math.PI);
         if (f > 0.0F && f1 <= 0.0F)
         {
            this.getWorld().playSoundClient(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
         }

         int i = this.getPhantomSize();
         float f2 = MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f3 = MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)) * (1.3F + 0.21F * (float)i);
         float f4 = (0.3F + f * 0.45F) * ((float)i * 0.2F + 1.0F);
         this.getWorld().addParticleClient(ParticleTypes.MYCELIUM, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
         this.getWorld().addParticleClient(ParticleTypes.MYCELIUM, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
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
   			this.getWorld().addParticleClient(AerialHellParticleTypes.FAT_PHANTOM_SMOKE, this.getX() + random.nextFloat() - 0.5, this.getY() + 2 * random.nextFloat(), this.getZ() + random.nextFloat() - 0.5, random.nextFloat() - 0.5, random.nextFloat() -0.5, random.nextFloat() - 0.5);
   		}
   }
   
   @Override public boolean isFireImmune() {return false;}
   @Override protected boolean isAffectedByDaylight() {return false;}

   @Override
   public void tickMovement()
   {
	   if (!this.getWorld().isDay())
	   {
		   if (!this.isDisappearing())
		   {
			   this.timeDisappearing = 0;
			   this.setDisappearing(true);
		   }
	   }   
	   super.tickMovement();
   }

   public boolean isDisappearing() {return this.getDataTracker().get(DISAPPEARING);}
   
   public void setDisappearing(boolean flag) {this.getDataTracker().set(DISAPPEARING, flag);}
   
   @Override
   public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficultyIn, SpawnReason reason, @Nullable EntityData spawnDataIn)
   {
      EntityData data = super.initialize(world, difficultyIn, reason, spawnDataIn);
      this.orbitPosition = this.getBlockPos().up(6);
      this.setPhantomSize(10 + random.nextInt(6));
      if (world.getBlockState(this.getBlockPos().up()).getBlock() == Blocks.AIR)
      {
    	  this.setPosition(new Vec3d(this.getBlockPos().up().getX(), this.getBlockPos().up().getY(), this.getBlockPos().up().getZ()));
      }
      this.timeDisappearing = 0; this.setDisappearing(false);
      return data;
   }

   @Override protected void readCustomData(ReadView view)
   {
      super.readCustomData(view);
      this.orbitPosition = view.read("anchor_pos", BlockPos.CODEC).orElse(null);
      this.setPhantomSize(view.getInt("size", 0));
      this.setDisappearing(view.getBoolean("Disappearing", false));
   }

   @Override protected void writeCustomData(WriteView view)
   {
      super.writeCustomData(view);
      view.putNullable("anchor_pos", BlockPos.CODEC, this.orbitPosition);
      view.putInt("size", this.getPhantomSize());
      view.putBoolean("Disappearing", this.isDisappearing());
   }

   @Override
   public EntityDimensions getBaseDimensions(EntityPose pose)
   {
      int i = this.getPhantomSize();
      EntityDimensions entitydimensions = super.getBaseDimensions(pose);
      return entitydimensions.scaled(1.0F + 0.15F * (float)i);
   }

   boolean testTargetPredicate(ServerWorld world, LivingEntity target, TargetPredicate predicate) {return predicate.test(world, this, target);}

   static enum AttackPhase
   {
      CIRCLE,
      SWOOP;
   }

   class AttackAttackingPlayerGoal extends Goal
   {
      private final TargetPredicate attackTargeting = TargetPredicate.createAttackable().setBaseMaxDistance(64.0D);
      private int tickDelay = 20;

      private AttackAttackingPlayerGoal() {}

      public boolean canStart()
      {
         if (this.tickDelay > 0)
         {
            --this.tickDelay;
            return false;
         }
         else
         {
            this.tickDelay = 60;
            ServerWorld serverWorld = castToServerWorld(FatPhantomEntity.this.getWorld());
            List<PlayerEntity> list = serverWorld.getPlayers(this.attackTargeting, FatPhantomEntity.this, FatPhantomEntity.this.getBoundingBox().expand(16.0D, 64.0D, 16.0D));
            if (!attackingPlayers.isEmpty() && !list.isEmpty())
            {
               list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

               for(PlayerEntity player : list)
               {
                  if (FatPhantomEntity.this.testTargetPredicate(serverWorld, player, TargetPredicate.DEFAULT) && attackingPlayers.contains(player))
                  {
                     FatPhantomEntity.this.setTarget(player);
                     return true;
                  }
               }
            }
            return false;
         }
      }

      public boolean shouldContinue()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         return livingentity != null ? FatPhantomEntity.this.testTargetPredicate(castToServerWorld(FatPhantomEntity.this.getWorld()), livingentity, TargetPredicate.DEFAULT) : false;
      }
   }

   class BodyHelperController extends BodyControl
   {
      public BodyHelperController(MobEntity mob)
      {
         super(mob);
      }

      @Override public void tick()
      {
         FatPhantomEntity.this.headYaw = FatPhantomEntity.this.bodyYaw;
         FatPhantomEntity.this.bodyYaw = FatPhantomEntity.this.getYaw();
      }
   }

   class LookHelperController extends LookControl
   {
      public LookHelperController(MobEntity entityIn) {super(entityIn);}

      public void tick() {}
   }

   abstract class MoveGoal extends Goal
   {
      public MoveGoal()
      {
         this.setControls(EnumSet.of(Goal.Control.MOVE));
      }

      protected boolean touchingTarget()
      {
         return FatPhantomEntity.this.orbitOffset.squaredDistanceTo(FatPhantomEntity.this.getX(), FatPhantomEntity.this.getY(), FatPhantomEntity.this.getZ()) < 4.0D;
      }
   }

   class MoveHelperController extends MoveControl
   {
      private float speedFactor = 0.1F;

      public MoveHelperController(MobEntity entityIn)
      {
         super(entityIn);
      }

      public void tick()
      {
         if (FatPhantomEntity.this.horizontalCollision)
         {
            FatPhantomEntity.this.setYaw(FatPhantomEntity.this.getYaw() + 180.0F);
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
            float f = FatPhantomEntity.this.getYaw();
            float f1 = (float) MathHelper.atan2(d2, d0);
            float f2 = MathHelper.wrapDegrees(FatPhantomEntity.this.getYaw() + 90.0F);
            float f3 = MathHelper.wrapDegrees(f1 * (180F / (float) Math.PI));
            FatPhantomEntity.this.setYaw(MathHelper.stepUnwrappedAngleTowards(f2, f3, 4.0F) - 90.0F);
            FatPhantomEntity.this.bodyYaw = FatPhantomEntity.this.getYaw();
            if (MathHelper.angleBetween(f, FatPhantomEntity.this.getYaw()) < 3.0F) {
               this.speedFactor = MathHelper.stepTowards(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
            } else {
               this.speedFactor = MathHelper.stepTowards(this.speedFactor, 0.2F, 0.025F);
            }

            float f4 = (float) (-(MathHelper.atan2(-d1, d3) * (double) (180F / (float) Math.PI)));
            FatPhantomEntity.this.setPitch(f4);
            float f5 = FatPhantomEntity.this.getYaw() + 90.0F;
            double d6 = (double) (this.speedFactor * MathHelper.cos(f5 * ((float) Math.PI / 180F))) * Math.abs(d0 / d5);
            double d7 = (double) (this.speedFactor * MathHelper.sin(f5 * ((float) Math.PI / 180F))) * Math.abs(d2 / d5);
            double d8 = (double) (this.speedFactor * MathHelper.sin(f4 * ((float) Math.PI / 180F))) * Math.abs(d1 / d5);
            Vec3d vec3 = FatPhantomEntity.this.getVelocity();
            FatPhantomEntity.this.setVelocity(vec3.add((new Vec3d(d6, d8, d7)).subtract(vec3).multiply(0.2D)));
         }
      }
   }

   class OrbitPointGoal extends FatPhantomEntity.MoveGoal
   {
      private float angle;
      private float distance;
      private float height;
      private float clockwise;

      public boolean canStart() {return FatPhantomEntity.this.getTarget() == null || FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE;}

      public void start()
      {
         this.distance = 5.0F + FatPhantomEntity.this.random.nextFloat() * 10.0F;
         this.height = -4.0F + FatPhantomEntity.this.random.nextFloat() * 9.0F;
         this.clockwise = FatPhantomEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
         this.selectNext();
      }

      public void tick()
      {
         if (FatPhantomEntity.this.random.nextInt(this.getTickCount(350)) == 0) {this.height = -4.0F + FatPhantomEntity.this.random.nextFloat() * 9.0F;}

         if (FatPhantomEntity.this.random.nextInt(this.getTickCount(250)) == 0)
         {
            ++this.distance;
            if (this.distance > 15.0F)
            {
               this.distance = 5.0F;
               this.clockwise = -this.clockwise;
            }
         }

         if (FatPhantomEntity.this.random.nextInt(this.getTickCount(450)) == 0)
         {
            this.angle = FatPhantomEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
            this.selectNext();
         }

         if (this.touchingTarget()) {this.selectNext();}

         if (FatPhantomEntity.this.orbitOffset.y < FatPhantomEntity.this.getY() && !FatPhantomEntity.this.getWorld().isAir(FatPhantomEntity.this.getBlockPos().down(1)))
         {
            this.height = Math.max(1.0F, this.height);
            this.selectNext();
         }

         if (FatPhantomEntity.this.orbitOffset.y > FatPhantomEntity.this.getY() && !FatPhantomEntity.this.getWorld().isAir(FatPhantomEntity.this.getBlockPos().up(1)))
         {
            this.height = Math.min(-1.0F, this.height);
            this.selectNext();
         }

      }

      private void selectNext()
      {
         if (BlockPos.ZERO.equals(FatPhantomEntity.this.orbitPosition)) {FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getBlockPos();}

         this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
         FatPhantomEntity.this.orbitOffset = Vec3d.of(FatPhantomEntity.this.orbitPosition).add((double)(this.distance * MathHelper.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * MathHelper.sin(this.angle)));
      }
   }

   class PickAttackGoal extends Goal
   {
      private int nextSweepTick;

      public boolean canStart()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         return livingentity != null ? FatPhantomEntity.this.testTargetPredicate(castToServerWorld(FatPhantomEntity.this.getWorld()), livingentity, TargetPredicate.DEFAULT) : false;
      }

      public void start()
      {
         this.nextSweepTick = this.getTickCount(10);
         FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         this.setAnchorAboveTarget();
      }

      public void stop() {FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING, FatPhantomEntity.this.orbitPosition).up(10 + FatPhantomEntity.this.random.nextInt(20));}

      public void tick()
      {
         if (FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.CIRCLE)
         {
            --this.nextSweepTick;
            if (this.nextSweepTick <= 0)
            {
               FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.SWOOP;
               this.setAnchorAboveTarget();
               this.nextSweepTick = this.getTickCount((8 + FatPhantomEntity.this.random.nextInt(4)) * 20);
               FatPhantomEntity.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + FatPhantomEntity.this.random.nextFloat() * 0.1F);
            }
         }
      }

      private void setAnchorAboveTarget()
      {
         FatPhantomEntity.this.orbitPosition = FatPhantomEntity.this.getTarget().getBlockPos().up(20 + FatPhantomEntity.this.random.nextInt(20));
         if (FatPhantomEntity.this.orbitPosition.getY() < FatPhantomEntity.this.getWorld().getSeaLevel())
         {
            FatPhantomEntity.this.orbitPosition = new BlockPos(FatPhantomEntity.this.orbitPosition.getX(), FatPhantomEntity.this.getWorld().getSeaLevel() + 1, FatPhantomEntity.this.orbitPosition.getZ());
         }
      }
   }

   class SweepAttackGoal extends FatPhantomEntity.MoveGoal
   {
      private SweepAttackGoal() {}

      public boolean canStart() {return FatPhantomEntity.this.getTarget() != null && FatPhantomEntity.this.attackPhase == FatPhantomEntity.AttackPhase.SWOOP;}

      public boolean shouldContinue()
      {
         LivingEntity livingentity = FatPhantomEntity.this.getTarget();
         if (livingentity == null) {return false;}
         else if (!livingentity.isAlive()) {return false;}
         else if (!EntityHelper.isCreaOrSpecPlayer(livingentity)) {return this.canStart();}
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
         FatPhantomEntity.this.orbitOffset = new Vec3d(livingentity.getX(), livingentity.getBodyY(0.5D), livingentity.getZ());
         if (FatPhantomEntity.this.getBoundingBox().expand(0.2F).intersects(livingentity.getBoundingBox()))
         {
            FatPhantomEntity.this.tryAttack(castToServerWorld(FatPhantomEntity.this.getWorld()), livingentity);
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
            if (!FatPhantomEntity.this.isSilent())
            {
               FatPhantomEntity.this.getWorld().syncWorldEvent(WorldEvents.PHANTOM_BITES, FatPhantomEntity.this.getBlockPos(), 0);
            }
         }
         else if (FatPhantomEntity.this.horizontalCollision || FatPhantomEntity.this.hurtTime > 0)
         {
            FatPhantomEntity.this.attackPhase = FatPhantomEntity.AttackPhase.CIRCLE;
         }
      }
   }
}

