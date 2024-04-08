package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractCustomHurtMonsterEntity;
import fr.factionbedrock.aerialhell.Entity.Util.CustomHurtInfo;
import fr.factionbedrock.aerialhell.Entity.Util.SnakeCustomHurtInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractSnakeEntity extends AbstractCustomHurtMonsterEntity
{
    protected enum BodyPartDeathReaction{ALWAYS_SPLIT, SPLIT_IF_NOT_HEAD, LOOSE_TAIL, ALWAYS_DIE}
    protected enum SendDirection{FORWARD, BACKWARD}
    private final int length;
    public final BodyPartDeathReaction bodyPartDeathReaction;
    @Nullable private AbstractSnakeEntity head;
    @Nullable private AbstractSnakeEntity previousBodyPart;
    @Nullable private AbstractSnakeEntity nextBodyPart;
    @Nullable private String nextBodyPartStringUUID;
    private static final EntityDataAccessor<Integer> BODY_PART_ID = SynchedEntityData.<Integer>defineId(AbstractSnakeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_CUT = SynchedEntityData.<Boolean>defineId(AbstractSnakeEntity.class, EntityDataSerializers.BOOLEAN);
    protected boolean reverseDrag;

    public AbstractSnakeEntity(EntityType<? extends AbstractSnakeEntity> type, Level world)
    {
        super(type, world);
        this.head = null;
        this.previousBodyPart = null;
        this.nextBodyPart = null;
        this.reverseDrag = false;
        this.bodyPartDeathReaction = this.getBodyPartDeathReaction();
        this.length = this.getLength().sample(this.getRandom());
    }

    protected abstract BodyPartDeathReaction getBodyPartDeathReaction();
    protected abstract IntProvider getLength();

    public int getBodyPartId() {return this.entityData.get(BODY_PART_ID);}
    protected void setBodyPartId(int id) {this.entityData.set(BODY_PART_ID, id);}
    public boolean isHead() {return this.entityData.get(BODY_PART_ID) == 0;}

    protected void setCut() {this.entityData.set(IS_CUT, true);}
    protected boolean isCut() {return this.entityData.get(IS_CUT);}

    public boolean setPreviousBodyPart(AbstractSnakeEntity previousBodyPart)
    {
        boolean canSet = this.previousBodyPart == null;
        if (canSet) {this.previousBodyPart = previousBodyPart;}
        return canSet;
    }

    @Nullable public AbstractSnakeEntity getHead() {return this.head;}
    @Nullable public AbstractSnakeEntity getPreviousBodyPart() {return this.previousBodyPart;}
    @Nullable public AbstractSnakeEntity getNextBodyPart() {return this.nextBodyPart;}

    @Nullable public AbstractSnakeEntity getNextBodyPartByUUID(String stringUUID)
    {
        List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(5), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (Entity entity : nearbyEntities)
        {
            if (entity.getStringUUID().equals(stringUUID)) {return (AbstractSnakeEntity) entity;}
        }
        return null;
    }

    public AbstractSnakeEntity getTailBodyPart()
    {
        return (this.nextBodyPart != null) ? this.nextBodyPart.getTailBodyPart() : this;
    }

    @Nullable public AbstractSnakeEntity getHeadBodyPart()
    {
        return (this.previousBodyPart != null) ? this.previousBodyPart.getHeadBodyPart() : this.isHead() ? this : null;
    }

    public boolean isTailFalling()
    {
        //tail is falling if 60% of the body parts, starting from tail, are all not on ground
        if (!this.isHead() && this.getHead() == null) {return false;} //can't execute
        AbstractSnakeEntity head = this.isHead() ? this : this.getHead();
        int fallingCount = head.onGround() ? 1 : 0, count = 1;
        AbstractSnakeEntity nextBodyPart = head.nextBodyPart;
        while (nextBodyPart != null)
        {
            count++;
            if (nextBodyPart.onGround()) {fallingCount = 0;}
            else {fallingCount++;}
            nextBodyPart = nextBodyPart.nextBodyPart;
        }
        return fallingCount >= 0.60F * count;
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SnakeGoals.SnakeMeleeAttackGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new SnakeGoals.SnakeWaterAvoidingRandomWalkingGoal(this, 0.9D));
        this.goalSelector.addGoal(4, new SnakeGoals.SnakeLookAtPlayerGoal(this));
        this.goalSelector.addGoal(4, new SnakeGoals.SnakeRandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new SnakeGoals.AlignSnakeBodyPartGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        if (!this.reverseDrag) {this.dragNextBodyPart();}
        else {this.dragPreviousBodyPart();}

        if (this.head == null) {this.head = this.getHeadBodyPart();} //called after reloading the world
        if (!this.isCut() && this.nextBodyPart == null) {this.tryToFindBackNextBodyPart();} //called after reloading the world

        if (this.nextBodyPart != null && this.nextBodyPart.isDeadOrDying()) {this.setCut(); this.nextBodyPart = null;}

        if (this.isHead() && this.tickCount % 5 == 0)
        {
            boolean shouldReverseDrag = this.isTailFalling() && this.onGround() || (this.reverseDrag && !this.onGround());
            if (this.reverseDrag != shouldReverseDrag)
            {
                this.sendDragDirection(shouldReverseDrag ? SendDirection.FORWARD : SendDirection.BACKWARD, SendDirection.BACKWARD, this);
            }
        }

        if (!this.onGround())
        {
            AbstractSnakeEntity tail = this.getTailBodyPart(), head = this.getHeadBodyPart();
            if (tail.onGround() || head != null && head.onGround())
            {
                Vec3 deltaMovement = this.getDeltaMovement();
                if (deltaMovement.y < 0) //slow falling if tail/head is not falling
                {
                    this.setDeltaMovement(deltaMovement.multiply(1.0F, 0.8F, 1.0F));
                }
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
            dragBodyPartToAnother(this.nextBodyPart, this);
        }
    }

    protected void dragPreviousBodyPart()
    {
        if (this.previousBodyPart != null)
        {
            dragBodyPartToAnother(this.previousBodyPart, this);
        }
    }

    protected static void dragBodyPartToAnother(AbstractSnakeEntity dragged, AbstractSnakeEntity source)
    {
        boolean mayJump = source.getY() > dragged.getY();
        float distanceToNextBodyPart = source.distanceTo(dragged);
        if (distanceToNextBodyPart > 2 && mayJump) {dragged.sendJump(0.42F, 0.0F, source, SendDirection.BACKWARD);}
        if (distanceToNextBodyPart < 0.7F) {return;}
        Vec3 prevDeltaMovement = dragged.getDeltaMovement(); double prevx = prevDeltaMovement.x, prevy = prevDeltaMovement.y, prevz = prevDeltaMovement.z;
        double factor = Math.min(Math.max(0.4, 0.4 * distanceToNextBodyPart), 0.5F);
        Vec3 defaultDragVector = new Vec3(source.getX() - dragged.getX(), source.getY() - dragged.getY(), source.getZ() - dragged.getZ()).multiply(factor,factor,factor);

        double x = prevx + defaultDragVector.x;
        double y = prevy + defaultDragVector.y;
        double z = prevz + defaultDragVector.z;

        Direction xDirection = defaultDragVector.x > 0 ? Direction.EAST : Direction.WEST;
        Direction zDirection = defaultDragVector.z > 0 ? Direction.SOUTH : Direction.NORTH;
        Direction mainDirection = Math.abs(defaultDragVector.x) > Math.abs(defaultDragVector.z) ? xDirection : zDirection;
        boolean mainDirectionColliding = !source.level().getBlockState(dragged.blockPosition().relative(mainDirection)).isAir();

        if (mayJump) //trying to avoid "tail" (body parts) getting stuck below thin-block-surface
        {
            BlockPos abovePos = dragged.blockPosition().above();
            if (dragged.mayBeColliding(abovePos.relative(Direction.EAST))) {x -= 0.1F;}
            if (dragged.mayBeColliding(abovePos.relative(Direction.WEST))) {x += 0.1F;}
            if (dragged.mayBeColliding(abovePos.relative(Direction.SOUTH))) {z -= 0.1F;}
            if (dragged.mayBeColliding(abovePos.relative(Direction.NORTH))) {z += 0.1F;}
        }

        boolean yOverride = (mayJump && prevy < 0.9F && mainDirectionColliding);

        dragged.setDeltaMovement(new Vec3(x, yOverride ? 0.9F * factor : y, z).multiply(factor, factor, factor));
    }

    public boolean mayBeColliding(BlockPos pos)
    {
        BlockState state = this.level().getBlockState(pos);
        VoxelShape blockShape = state.getCollisionShape(this.level(), pos, CollisionContext.of(this)).move(pos.getX(), pos.getY(), pos.getZ());
        return Shapes.joinIsNotEmpty(blockShape, Shapes.create(this.getBoundingBox().inflate(0.1F)), BooleanOp.AND);
    }

    @Override public boolean canCollideWith(Entity entity) {return !(entity instanceof AbstractSnakeEntity) && super.canCollideWith(entity);}
    @Override protected void doPush(Entity entity)
    {
        if (entity instanceof AbstractSnakeEntity snakeEntity && snakeEntity.getType() == this.getType())
        {
            boolean sameSnake = snakeEntity.getHead() != null && snakeEntity.getHead() == this.getHead();
            boolean collisionDueToSmallDistance = this.distanceTo(snakeEntity) <= 0.2 || (entity.getDeltaMovement().x == 0 && entity.getDeltaMovement().z == 0 && this.distanceTo(entity) < 0.4F);
            boolean noCollide = sameSnake && !collisionDueToSmallDistance;
            if (!noCollide) {super.doPush(entity);}
        }
        else {super.doPush(entity);}
    }

    @Override @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
    {
        if (this.isHead()) {this.head = this;}
        if (this.getBodyPartId() < this.length && !this.isCut() && this.getNextBodyPart() == null)
        {
            this.nextBodyPart = this.summonNextBodyPart();
            if (this.nextBodyPart != null)
            {
                this.nextBodyPart.finalizeSpawn(level, difficultyIn, reason, spawnDataIn, dataTag);
            }
        }
        return spawnDataIn;
    }

    private AbstractSnakeEntity summonNextBodyPart()
    {
        float x = 0.0F;
        float z = 0.0F;
        AbstractSnakeEntity nextBodyPart = this.getType().create(this.level());
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

    @Override protected CustomHurtInfo getDefaultCustomHurtInfo(float amount)
    {
        return new SnakeCustomHurtInfo(amount, this.defaultKbStrength(), this.shouldPlayHurtOrDeathSoundOnHurt(), this.shouldApplyKbOnHurt(), true);
    }

    @Override public boolean customHurt(DamageSource damageSource, CustomHurtInfo info)
    {
        boolean flag = super.customHurt(damageSource, info);
        if (flag && info instanceof SnakeCustomHurtInfo snakeHurtInfo && snakeHurtInfo.shouldSendToOthers())
        {
            float amount = info.amount();
            float amountReduction = 2.0F;
            float kbStrengthReduction = this.isHead() ? 0.01F : 0.05F;
            float minimumAmount = Math.min(amount, 0.5F);
            if (this.nextBodyPart != null) {this.nextBodyPart.sendHurt(damageSource, info, amountReduction, kbStrengthReduction, minimumAmount, 0.0F, this, AbstractSnakeEntity.SendDirection.BACKWARD);}
            if (this.previousBodyPart != null) {this.previousBodyPart.sendHurt(damageSource, info, amountReduction, kbStrengthReduction, minimumAmount, 0.0F, this, AbstractSnakeEntity.SendDirection.FORWARD);}
        }
        if (this.isDeadOrDying()) {this.detach();}
        return flag;
    }

    public void sendDragDirection(SendDirection dragDirection, SendDirection sendDirection, AbstractSnakeEntity sender)
    {
        this.reverseDrag = dragDirection == SendDirection.FORWARD;
        AbstractSnakeEntity torchbearer = sendDirection == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) torchbearer.sendDragDirection(dragDirection, sendDirection, sender);
    }

    public void sendHurt(DamageSource damageSource, CustomHurtInfo previousInfo, float amountReduction, float kbStrengthReduction, AbstractSnakeEntity sender, SendDirection direction)
    {
        this.sendHurt(damageSource, previousInfo, amountReduction, kbStrengthReduction, 0.0F, 0.0F, sender, direction);
    }

    public void sendHurt(DamageSource damageSource, CustomHurtInfo previousInfo, float amountReduction, float kbStrengthReduction, float minimumAmount, float minimumKbStrength, AbstractSnakeEntity sender, SendDirection direction)
    {
        float newAmount = Math.max(previousInfo.amount() - amountReduction, minimumAmount);
        float newKbStrength = Math.max(previousInfo.kbStrength() - kbStrengthReduction, minimumKbStrength);
        boolean shouldApplyKb = this.shouldApplyKbOnHurt() && newKbStrength > 0.0F;
        SnakeCustomHurtInfo newInfo = new SnakeCustomHurtInfo(newAmount, newKbStrength, this.shouldPlayHurtOrDeathSoundOnHurt(), shouldApplyKb, false);
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null && newAmount > 0) {torchbearer.sendHurt(damageSource, newInfo, amountReduction, kbStrengthReduction, minimumAmount, minimumKbStrength, sender, direction);}
        this.customHurt(damageSource, newInfo);
    }

    public void sendHeadUpdate(AbstractSnakeEntity newHead, AbstractSnakeEntity sender, SendDirection direction) //overrides head in this and all nexts body parts
    {
        this.head = newHead;
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) {torchbearer.sendHeadUpdate(newHead, sender, direction);}
    }

    public void sendJump(float yMovement, float yMovementReduction, AbstractSnakeEntity sender, SendDirection direction)
    {
        Vec3 deltamovement = this.getDeltaMovement();
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        float newYMovement = yMovement - yMovementReduction;
        if (torchbearer != null && newYMovement > 0) {torchbearer.sendJump(newYMovement, yMovementReduction, sender, direction);}
        this.setDeltaMovement(deltamovement.x, yMovement, deltamovement.z);
    }

    @Override public void customDie(DamageSource damageSource, boolean playSound)
    {
        if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_DIE)
        {
            AbstractSnakeEntity head = this.getHead();
            if (head != null && !head.isDeadOrDying())
            {
                head.setHealth(0.0F);
                head.customDie(damageSource, head.shouldPlayHurtOrDeathSoundOnHurt()); //mélange entre die et customDie
            }
            if (this.nextBodyPart != null && !this.nextBodyPart.isDeadOrDying())
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.customDie(damageSource, this.nextBodyPart.shouldPlayHurtOrDeathSoundOnHurt());
            }
        }

        if (this.nextBodyPart != null)
        {
            if (this.bodyPartDeathReaction == BodyPartDeathReaction.LOOSE_TAIL)
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.customDie(damageSource, this.nextBodyPart.shouldPlayHurtOrDeathSoundOnHurt());
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
                        this.nextBodyPart.customDie(damageSource, this.nextBodyPart.shouldPlayHurtOrDeathSoundOnHurt());
                    }
                }
            }
        }
        //this.detach(); now done in (this.)customHurt(..), because can't sendHurt after detach.
        super.customDie(damageSource, playSound);
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

    @Override public boolean canChangeDimensions() {return false;}
    @Override protected void dropExperience() {if (this.isHead()) {super.dropExperience();}}
    @Override public EntityType<AbstractSnakeEntity> getType() {return (EntityType<AbstractSnakeEntity>) super.getType();}
    @Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
    {
        if (!this.isHead()) {return false;}
        return super.causeFallDamage(distance, damageMultiplier, source);
    }

    protected float defaultKbStrength() {return 0.4F;}
    protected boolean shouldPlayHurtOrDeathSoundOnHurt() {return this.isHead();}
    protected boolean shouldApplyKbOnHurt() {return true;}

    @Nullable @Override protected SoundEvent getAmbientSound(){return this.isHead() ? AerialHellSoundEvents.ENTITY_SNAKE_AMBIENT.get() : null;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SNAKE_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SNAKE_DEATH.get();}
}
