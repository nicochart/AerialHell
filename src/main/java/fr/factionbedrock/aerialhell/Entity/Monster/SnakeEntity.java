package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionRandomLookAroundGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionWaterAvoidingRandomStrollGoal;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class SnakeEntity extends Monster
{
    private enum BodyPartDeathReaction{ALWAYS_SPLIT, SPLIT_IF_NOT_HEAD, LOOSE_TAIL, ALWAYS_DIE}
    private enum SendDirection{FORWARD, BACKWARD}
    public static int LENGTH = 16;
    public final BodyPartDeathReaction bodyPartDeathReaction;
    @Nullable private SnakeEntity head;
    @Nullable private SnakeEntity previousBodyPart;
    @Nullable private SnakeEntity nextBodyPart;
    @Nullable private String nextBodyPartStringUUID;
    private static final EntityDataAccessor<Integer> BODY_PART_ID = SynchedEntityData.<Integer>defineId(SnakeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_CUT = SynchedEntityData.<Boolean>defineId(SnakeEntity.class, EntityDataSerializers.BOOLEAN);

    public SnakeEntity(EntityType<? extends SnakeEntity> type, Level world)
    {
        super(type, world);
        this.head = null;
        this.previousBodyPart = null;
        this.nextBodyPart = null;
        this.bodyPartDeathReaction = BodyPartDeathReaction.LOOSE_TAIL;
    }


    public int getBodyPartId() {return this.entityData.get(BODY_PART_ID);}
    protected void setBodyPartId(int id) {this.entityData.set(BODY_PART_ID, id);}
    public boolean isHead() {return this.entityData.get(BODY_PART_ID) == 0;}

    protected void setCut() {this.entityData.set(IS_CUT, true);}
    protected boolean isCut() {return this.entityData.get(IS_CUT);}

    public boolean setPreviousBodyPart(SnakeEntity previousBodyPart)
    {
        boolean canSet = this.previousBodyPart == null;
        if (canSet) {this.previousBodyPart = previousBodyPart;}
        return canSet;
    }

    @Nullable public SnakeEntity getHead() {return this.head;}
    @Nullable public SnakeEntity getPreviousBodyPart() {return this.previousBodyPart;}
    @Nullable public SnakeEntity getNextBodyPart() {return this.nextBodyPart;}

    @Nullable public SnakeEntity getNextBodyPartByUUID(String stringUUID)
    {
        List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(5), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (Entity entity : nearbyEntities)
        {
            if (entity.getStringUUID().equals(stringUUID)) {return (SnakeEntity) entity;}
        }
        return null;
    }

    public SnakeEntity getTailBodyPart()
    {
        return (this.nextBodyPart != null) ? this.nextBodyPart.getTailBodyPart() : this;
    }

    public SnakeEntity getHeadBodyPart()
    {
        return (this.previousBodyPart != null) ? this.previousBodyPart.getHeadBodyPart() : this.isHead() ? this : null;
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SnakeMeleeAttackGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new SnakeWaterAvoidingRandomWalkingGoal(this, 0.9D));
        this.goalSelector.addGoal(4, new SnakeLookAtPlayerGoal(this));
        this.goalSelector.addGoal(4, new SnakeRandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new AlignSnakeBodyPartGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        this.dragNextBodyPart();

        if (this.head == null) {this.head = this.getHeadBodyPart();} //called after reloading the world
        if (!this.isCut() && this.nextBodyPart == null) {this.tryToFindBackNextBodyPart();} //called after reloading the world

        if (this.nextBodyPart != null && this.nextBodyPart.isDeadOrDying()) {this.setCut(); this.nextBodyPart = null;}

        if (!this.onGround() && this.getTailBodyPart().onGround())
        {
            Vec3 deltaMovement = this.getDeltaMovement();
            if (deltaMovement.y < 0) //slow falling if tail is not falling
            {
                this.setDeltaMovement(deltaMovement.multiply(1.0F, 0.8F, 1.0F));
            }
        }
    }

    private void tryToFindBackNextBodyPart()
    {
        this.nextBodyPart = this.getNextBodyPartByUUID(this.nextBodyPartStringUUID);
        if (this.nextBodyPart == null) {this.setCut();}
        else {this.nextBodyPart.setPreviousBodyPart(this);}
    }

    protected void dragNextBodyPart()
    {
        if (this.nextBodyPart != null)
        {
            boolean mayJump = this.getY() > this.nextBodyPart.getY();
            float distanceToNextBodyPart = this.distanceTo(this.nextBodyPart);
            if (distanceToNextBodyPart > 2 && mayJump) {this.nextBodyPart.sendJump(0.42F, 0.0F, this, SendDirection.BACKWARD);}
            if (distanceToNextBodyPart < 0.7F) {return;}
            Vec3 prevDeltaMovement = this.nextBodyPart.getDeltaMovement(); double prevx = prevDeltaMovement.x, prevy = prevDeltaMovement.y, prevz = prevDeltaMovement.z;
            double factor = Math.min(Math.max(0.4, 0.4 * distanceToNextBodyPart), 0.5F);
            Vec3 defaultDragVector = new Vec3(this.getX() - this.nextBodyPart.getX(), this.getY() - this.nextBodyPart.getY(), this.getZ() - this.nextBodyPart.getZ()).multiply(factor,factor,factor);

            double x = prevx + defaultDragVector.x;
            double y = prevy + defaultDragVector.y;
            double z = prevz + defaultDragVector.z;

            Direction xDirection = defaultDragVector.x > 0 ? Direction.EAST : Direction.WEST;
            Direction zDirection = defaultDragVector.z > 0 ? Direction.SOUTH : Direction.NORTH;
            Direction mainDirection = Math.abs(defaultDragVector.x) > Math.abs(defaultDragVector.z) ? xDirection : zDirection;
            boolean mainDirectionColliding = !this.level().getBlockState(this.nextBodyPart.blockPosition().relative(mainDirection)).isAir();
            boolean yOverride = (mayJump && prevy < 0.9F && mainDirectionColliding);

            this.nextBodyPart.setDeltaMovement(new Vec3(x, yOverride ? 0.9F * factor : y, z).multiply(factor, factor, factor));
        }
    }

    @Override public boolean canCollideWith(Entity entity) {return !(entity instanceof SnakeEntity) && super.canCollideWith(entity);}
    @Override protected void doPush(Entity entity)
    {
        boolean noCollide = entity instanceof SnakeEntity snakeEntity && snakeEntity.getHead() != null && snakeEntity.getHead() == this.getHead() && this.distanceTo(snakeEntity) > 0.2F;
        if (!noCollide) {super.doPush(entity);}
    }

    @Override @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
    {
        if (this.isHead()) {this.head = this;}
        if (this.getBodyPartId() < LENGTH && !this.isCut() && this.getNextBodyPart() == null)
        {
            this.nextBodyPart = this.summonNextBodyPart();
            if (this.nextBodyPart != null)
            {
                this.nextBodyPart.finalizeSpawn(level, difficultyIn, reason, spawnDataIn, dataTag);
            }
        }
        return spawnDataIn;
    }

    private SnakeEntity summonNextBodyPart()
    {
        float x = 0.0F;
        float z = 0.0F;
        SnakeEntity nextBodyPart = this.getType().create(this.level());
        if (nextBodyPart != null)
        {
            if (this.isPersistenceRequired()) {nextBodyPart.setPersistenceRequired();}
            nextBodyPart.setCustomName(this.getCustomName());
            nextBodyPart.setNoAi(this.isNoAi());
            nextBodyPart.setInvulnerable(this.isInvulnerable());
            nextBodyPart.moveTo(this.getX() + (double) x, this.getY(), this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
            nextBodyPart.setBodyPartId(this.getBodyPartId() + 1);
            nextBodyPart.setPreviousBodyPart(this);
            nextBodyPart.head = this.getHead();
            this.level().addFreshEntity(nextBodyPart);
        }
        return nextBodyPart;
    }

    @Override public boolean hurt(DamageSource damageSource, float amount)
    {
        boolean flag = this.snakeHurt(damageSource, amount, true, true);
        if (flag)
        {
            float amountReduction = 2.0F;
            float newAmount = amount < 0.5F ? amount : Math.max(amount - amountReduction, 0.5F);
            if (this.nextBodyPart != null && newAmount > 0) {this.nextBodyPart.sendHurt(damageSource, newAmount, amountReduction, 0.5F, this, SendDirection.BACKWARD);}
            if (this.previousBodyPart != null && newAmount > 0) {this.previousBodyPart.sendHurt(damageSource, newAmount, amountReduction, 0.5F, this, SendDirection.FORWARD);}
        }
        return flag;
    }

    //copy of net.minecraft.world.entity.LivingEntity hurt(DamageSource source, float amount) method, removing everything non-related to my snakes, and calling other methods, allowing customization in my inheriting classes
    public boolean snakeHurt(DamageSource source, float amount, boolean playSound, boolean applyKb)
    {
        if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount)) return false;
        if (this.isInvulnerableTo(source) || this.level().isClientSide || this.isDeadOrDying()) {return false;}
        else if (source.is(DamageTypeTags.IS_FIRE) && this.hasEffect(MobEffects.FIRE_RESISTANCE)) {return false;}
        else
        {
            this.noActionTime = 0;

            if (source.is(DamageTypeTags.IS_FREEZING) && this.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {amount *= 5.0F;}
            this.walkAnimation.setSpeed(1.5F);

            boolean wasOnHurtCooldown = (float)this.invulnerableTime > 10.0F && !source.is(DamageTypeTags.BYPASSES_COOLDOWN);
            boolean actuallyGotHurt = tryActuallyHurt(source, amount);

            if (!actuallyGotHurt) {return false;}
            //we know this got hurt
            setLastHurtBy(source);

            if (!wasOnHurtCooldown)
            {
                this.level().broadcastDamageEvent(this, source);
                if (!source.is(DamageTypeTags.NO_IMPACT)) {this.markHurt();}

                if (applyKb) {tryApplyingKnockback(source);}
            }

            boolean died = false;
            if (this.isDeadOrDying()) {this.die(source); died = true;}

            if (!wasOnHurtCooldown && playSound)
            {
                if (died) {this.playDeathSound(source);}
                else {this.playHurtSound(source);}
            }

            this.lastDamageSource = source;
            this.lastDamageStamp = this.level().getGameTime();

            if (source.getEntity() instanceof ServerPlayer serverPlayerSource)
            {
                CriteriaTriggers.PLAYER_HURT_ENTITY.trigger(serverPlayerSource, this, source, amount, amount, false);
            }

            return true;
        }
    }

    public boolean tryActuallyHurt(DamageSource damageSource, float amount) //returns true if the entity is actually hurt
    {
        boolean isOnHurtCooldown = (float)this.invulnerableTime > 10.0F;
        boolean shouldDamageBeReducedDueToHurtCooldown = isOnHurtCooldown && !damageSource.is(DamageTypeTags.BYPASSES_COOLDOWN);

        if (shouldDamageBeReducedDueToHurtCooldown)
        {
            //the difference in damage amount is dealt if the amount of new "hurt" is greater than last one
            float reducedAmount = amount - this.lastHurt;
            if (reducedAmount <= 0) {return false;}

            this.actuallyHurt(damageSource, reducedAmount);
            this.lastHurt = amount;
            return true;
        }
        else
        {
            this.lastHurt = amount;
            this.invulnerableTime = 20;
            this.actuallyHurt(damageSource, amount);
            this.hurtDuration = 10;
            this.hurtTime = this.hurtDuration;
            return true;
        }
    }

    public void setLastHurtBy(DamageSource damageSource)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (sourceEntity != null)
        {
            if (sourceEntity instanceof LivingEntity sourceLivingEntity)
            {
                if (!damageSource.is(DamageTypeTags.NO_ANGER)) {this.setLastHurtByMob(sourceLivingEntity);}
            }

            if (sourceEntity instanceof Player sourcePlayerEntity)
            {
                this.lastHurtByPlayerTime = 100;
                this.lastHurtByPlayer = sourcePlayerEntity;
            }
            else if (sourceEntity instanceof TamableAnimal tamableEntity)
            {
                if (tamableEntity.isTame())
                {
                    this.lastHurtByPlayerTime = 100;
                    LivingEntity tamableEntityOwner = tamableEntity.getOwner();
                    if (tamableEntityOwner instanceof Player playerOwner) {this.lastHurtByPlayer = playerOwner;}
                    else {this.lastHurtByPlayer = null;}
                }
            }
        }
    }

    public boolean tryApplyingKnockback(DamageSource damageSource)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (sourceEntity != null && !damageSource.is(DamageTypeTags.NO_KNOCKBACK))
        {
            double xKb = sourceEntity.getX() - this.getX();
            double zKb;
            for(zKb = sourceEntity.getZ() - this.getZ(); xKb * xKb + zKb * zKb < 1.0E-4D; zKb = (Math.random() - Math.random()) * 0.01D) {xKb = (Math.random() - Math.random()) * 0.01D;}

            this.knockback((double)0.4F, xKb, zKb);
            this.indicateDamage(xKb, zKb);
            return true;
        }
        return false;
    }

    @Override public void knockback(double strength, double ratioX, double ratioZ)
    {
        super.knockback(strength, ratioX, ratioZ);
        double strengthReduction = this.isHead() ? 0.01D : 0.05D;
        double newStrength = strength - strengthReduction;
        if (this.nextBodyPart != null && newStrength > 0) {this.nextBodyPart.sendKnockback(newStrength, strengthReduction, ratioX, ratioZ, this, SendDirection.BACKWARD);}
        if (this.previousBodyPart != null && newStrength > 0) {this.previousBodyPart.sendKnockback(newStrength, strengthReduction, ratioX, ratioZ, this, SendDirection.FORWARD);}
    }

    public void sendHurt(DamageSource damageSource, float amount, float amountReduction, SnakeEntity sender, SendDirection direction)
    {
        this.sendHurt(damageSource, amount, amountReduction, 0.0F, sender, direction);
    }

    public void sendHurt(DamageSource damageSource, float amount, float amountReduction, float minimumAmount, SnakeEntity sender, SendDirection direction)
    {
        this.snakeHurt(damageSource, amount, false, false); //kb is managed by a sendKnockback, generated by this.hurt calling this.tryApplyingKb calling this.knockback
        SnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        float newAmount = Math.max(amount - amountReduction, minimumAmount);
        if (torchbearer != null && newAmount > 0) {torchbearer.sendHurt(damageSource, newAmount, amountReduction, minimumAmount, sender, direction);}
    }

    public void sendKnockback(double strength, double strengthReduction, double ratioX, double ratioZ, SnakeEntity sender, SendDirection direction)
    {
        super.knockback(strength, ratioX, ratioZ);
        SnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        double newStrength = strength - strengthReduction;
        if (torchbearer != null && newStrength > 0) {torchbearer.sendKnockback(newStrength, strengthReduction, ratioX, ratioZ, sender, direction);}
    }

    public void sendHeadUpdate(SnakeEntity newHead, SnakeEntity sender, SendDirection direction) //overrides head in this and all nexts body parts
    {
        this.head = newHead;
        SnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) {torchbearer.sendHeadUpdate(newHead, sender, direction);}
    }

    public void sendJump(float yMovement, float yMovementReduction, SnakeEntity sender, SendDirection direction)
    {
        Vec3 deltamovement = this.getDeltaMovement();
        this.setDeltaMovement(deltamovement.x, yMovement, deltamovement.z);
        SnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        float newYMovement = yMovement - yMovementReduction;
        if (torchbearer != null && newYMovement > 0) {torchbearer.sendJump(newYMovement, yMovementReduction, sender, direction);}
    }

    @Override public void die(DamageSource damageSource)
    {
        if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_DIE)
        {
            SnakeEntity head = this.getHead();
            if (head != null && !head.isDeadOrDying())
            {
                head.setHealth(0.0F);
                head.die(damageSource);
            }
            if (this.nextBodyPart != null && !this.nextBodyPart.isDeadOrDying())
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.die(damageSource);
            }
        }

        if (this.nextBodyPart != null)
        {
            if (this.bodyPartDeathReaction == BodyPartDeathReaction.LOOSE_TAIL)
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.die(damageSource);
            }
            else
            {
                if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_SPLIT)
                {
                    this.nextBodyPart.setBodyPartId(0);
                    this.nextBodyPart.sendHeadUpdate(this.nextBodyPart, this, SendDirection.BACKWARD);
                }
                else if (this.bodyPartDeathReaction == BodyPartDeathReaction.SPLIT_IF_NOT_HEAD)
                {
                    this.nextBodyPart.setBodyPartId(0);
                    this.nextBodyPart.sendHeadUpdate(this.nextBodyPart, this, SendDirection.BACKWARD);
                    if (this.isHead())
                    {
                        this.nextBodyPart.setHealth(0.0F);
                        this.nextBodyPart.die(damageSource);
                    }
                }
            }
        }
        this.detach();
        super.die(damageSource);
    }

    public void detach()
    {
        this.head = null;
        if (this.nextBodyPart != null)
        {
            this.nextBodyPart.previousBodyPart = null;
            this.nextBodyPart = null;
        }
        if (this.previousBodyPart != null)
        {
            this.previousBodyPart.setCut();
            this.previousBodyPart.nextBodyPart = null;
            this.previousBodyPart = null;
        }
    }

    @Override public EntityType<SnakeEntity> getType() {return (EntityType<SnakeEntity>) super.getType();}

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(BODY_PART_ID, 0);
        this.entityData.define(IS_CUT, false);
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putInt("body_part_id", this.getBodyPartId());
        compound.putBoolean("is_cut", this.isCut());
        if (this.nextBodyPart != null)
        {
            compound.putString("next_body_part_uuid", this.nextBodyPart.getStringUUID());
        }
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setBodyPartId(compound.getInt("body_part_id"));
        if (compound.getBoolean("is_cut")) {this.setCut();}
        else {this.entityData.set(IS_CUT, false);}
        if (compound.contains("next_body_part_uuid"))
        {
            this.nextBodyPartStringUUID = compound.getString("next_body_part_uuid");
        }
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
        		.add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override public boolean canChangeDimensions() {return false;}

    @Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
    {
        if (!this.isHead()) {return false;}
        return super.causeFallDamage(distance, damageMultiplier, source);
    }
    
    @Nullable @Override protected SoundEvent getAmbientSound(){return this.isHead() ? SoundEvents.SILVERFISH_AMBIENT : null;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.SILVERFISH_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.SILVERFISH_DEATH;}

    protected void playDeathSound(DamageSource damageSource)
    {
        SoundEvent soundevent = this.getDeathSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
    }

    public static class SnakeWaterAvoidingRandomWalkingGoal extends AdditionalConditionWaterAvoidingRandomStrollGoal
    {
        public SnakeWaterAvoidingRandomWalkingGoal(SnakeEntity entity, double speedIn) {super(entity, speedIn);}
        @Override public boolean additionalConditionMet() {return ((SnakeEntity)this.mob).isHead();}
    }

    public static class SnakeMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public SnakeMeleeAttackGoal(SnakeEntity entity, double speedIn) {super(entity, speedIn, false);}
        @Override public boolean additionalConditionMet() {return ((SnakeEntity)this.mob).isHead();}
    }

    public static class SnakeLookAtPlayerGoal extends AdditionalConditionLookAtPlayerGoal
    {
        protected SnakeEntity snakeGoalOwner;
        public SnakeLookAtPlayerGoal(SnakeEntity entity) {super(entity, Player.class, 8.0F); this.snakeGoalOwner = entity;}
        @Override public boolean additionalConditionMet() {return this.snakeGoalOwner.isHead();}
    }

    public static class SnakeRandomLookAroundGoal extends AdditionalConditionRandomLookAroundGoal
    {
        protected SnakeEntity snakeGoalOwner;
        public SnakeRandomLookAroundGoal(SnakeEntity entity) {super(entity); this.snakeGoalOwner = entity;}
        @Override public boolean additionalConditionMet() {return this.snakeGoalOwner.isHead();}
    }

    public static class AlignSnakeBodyPartGoal extends Goal
    {
        protected SnakeEntity snakeGoalOwner;

        public AlignSnakeBodyPartGoal(SnakeEntity entity)
        {
            this.snakeGoalOwner = entity;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override public boolean canUse() {return !this.snakeGoalOwner.isHead();}
        @Override public boolean canContinueToUse() {return !this.snakeGoalOwner.isHead();}

        @Override public void tick()
        {
            if (this.snakeGoalOwner.getPreviousBodyPart() != null)
            {
                this.snakeGoalOwner.lookAt(this.snakeGoalOwner.getPreviousBodyPart(), 30.0F, 30.0F);
            }
        }

        @Override public boolean requiresUpdateEveryTick() {return true;}
    }
}
