package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractCustomHurtMonsterEntity;
import fr.factionbedrock.aerialhell.Entity.Util.CustomHurtInfo;
import fr.factionbedrock.aerialhell.Entity.Util.SnakeCustomHurtInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
    private static final TrackedData<Integer> BODY_PART_ID = DataTracker.<Integer>registerData(AbstractSnakeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> IS_CUT = DataTracker.<Boolean>registerData(AbstractSnakeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected boolean reverseDrag;
    protected int timeInInvalidSituation;
    private static final int MAX_TIME_IN_INVALID_SITUATION = 20; //in tick

    public AbstractSnakeEntity(EntityType<? extends AbstractSnakeEntity> type, World world)
    {
        super(type, world);
        this.head = null;
        this.previousBodyPart = null;
        this.nextBodyPart = null;
        this.reverseDrag = false;
        this.bodyPartDeathReaction = this.getBodyPartDeathReaction();
        this.length = this.getLength().get(this.getRandom());
        this.timeInInvalidSituation = 0;
    }

    protected abstract BodyPartDeathReaction getBodyPartDeathReaction();
    protected abstract UniformIntProvider getLength();
    protected abstract int getMinLength(); //if the actual length of the snake is below minLength, the snake will die

    public int getBodyPartId() {return this.getDataTracker().get(BODY_PART_ID);}
    protected void setBodyPartId(int id) {this.getDataTracker().set(BODY_PART_ID, id);}
    public boolean isHead() {return this.getDataTracker().get(BODY_PART_ID) == 0;}

    protected void setCut() {this.getDataTracker().set(IS_CUT, true);}
    protected boolean isCut() {return this.getDataTracker().get(IS_CUT);}

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
        List<AbstractSnakeEntity> nearbyEntities = this.getEntityWorld().getEntitiesByClass(AbstractSnakeEntity.class, this.getBoundingBox().expand(5), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (AbstractSnakeEntity entity : nearbyEntities)
        {
            if (entity.getUuidAsString().equals(stringUUID)) {return entity;}
        }
        return null;
    }

    public int countNextBodyParts() {return this.countNextBodyParts(0);}

    public int countNextBodyParts(int previousCount)
    {
        return (this.nextBodyPart != null) ? this.nextBodyPart.countNextBodyParts(previousCount + 1) : previousCount;
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
        int fallingCount = head.isOnGround() ? 1 : 0, count = 1;
        AbstractSnakeEntity nextBodyPart = head.nextBodyPart;
        while (nextBodyPart != null)
        {
            count++;
            if (nextBodyPart.isOnGround() || nextBodyPart.isInFluid()) {fallingCount = 0;}
            else {fallingCount++;}
            nextBodyPart = nextBodyPart.nextBodyPart;
        }
        return fallingCount >= 0.60F * count;
    }

    @Override protected void initGoals()
    {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SnakeGoals.SnakeMeleeAttackGoal(this, 1.25D));
        this.goalSelector.add(3, new SnakeGoals.SnakeWaterAvoidingRandomWalkingGoal(this, 0.9D));
        this.goalSelector.add(4, new SnakeGoals.SnakeLookAtPlayerGoal(this));
        this.goalSelector.add(4, new SnakeGoals.SnakeRandomLookAroundGoal(this));
        this.goalSelector.add(4, new SnakeGoals.AlignSnakeBodyPartGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public boolean isPersistent() {return !this.isHead();}

    @Override protected void onRemoval(ServerWorld serverWorld, RemovalReason reason)
    {
        //super.onRemoval is called in sendRemove
        AbstractSnakeEntity head = this.isHead() ? this : this.getHead(); //head = this (should be) because other body parts are persistent
        if (head != null)
        {
            head.sendRemove(serverWorld, reason, this, SendDirection.BACKWARD);
        }
        super.onRemoval(serverWorld, reason);
    }

    @Override public void tick()
    {
        super.tick();
        if (!this.reverseDrag) {this.dragNextBodyPart();}
        else {this.dragPreviousBodyPart();}

        if (this.head == null) {this.head = this.getHeadBodyPart();} //called after reloading the world
        if (!this.isCut() && this.nextBodyPart == null) {this.tryToFindBackNextBodyPart();} //called after reloading the world

        if (this.nextBodyPart != null && !this.nextBodyPart.isAlive()) {this.setCut(); this.nextBodyPart = null;}

        if (this.isHead() && this.age % 5 == 0)
        {
            boolean shouldReverseDrag = this.shouldReverseDrag();
            if (this.reverseDrag != shouldReverseDrag)
            {
                this.sendDragDirection(shouldReverseDrag ? SendDirection.FORWARD : SendDirection.BACKWARD, SendDirection.BACKWARD, this);
            }
        }

        if (!this.isHead() && (this.previousBodyPart == null || this.distanceTo(this.previousBodyPart) > 2.0F)) {this.timeInInvalidSituation++;}
        else if (this.isHead() && this.countNextBodyParts() + 1 < this.getMinLength()) {this.timeInInvalidSituation++;}
        else {this.timeInInvalidSituation = 0;}

        if (this.timeInInvalidSituation > MAX_TIME_IN_INVALID_SITUATION)
        {
            this.serverDamage(this.getDamageSources().outOfWorld(), this.getMaxHealth());
        }

        if (!this.isOnGround())
        {
            AbstractSnakeEntity tail = this.getTailBodyPart(), head = this.getHeadBodyPart();
            if (tail.isOnGround() || head != null && head.isOnGround())
            {
                Vec3d deltaMovement = this.getVelocity();
                if (deltaMovement.y < 0) //slow falling if tail/head is not falling
                {
                    this.setVelocity(deltaMovement.multiply(1.0F, 0.8F, 1.0F));
                }
            }
        }
    }

    @Override public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        //debug
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.isOf(Items.STRUCTURE_VOID))
        {
            EntityHelper.debugSnakeEntity(this, player);
            return ActionResult.SUCCESS;
        }
        else {return super.interactMob(player, hand);}
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
        boolean mayFall = source.getY() < dragged.getY();
        float distanceToNextBodyPart = source.distanceTo(dragged);
        if (distanceToNextBodyPart > 2 && mayJump) {dragged.sendJump(0.42F, 0.0F, source, SendDirection.BACKWARD);}
        if (distanceToNextBodyPart < 0.7F) {return;}
        Vec3d prevDeltaMovement = dragged.getVelocity(); double prevx = prevDeltaMovement.x, prevy = prevDeltaMovement.y, prevz = prevDeltaMovement.z;
        double factor = Math.min(Math.max(0.4, 0.4 * distanceToNextBodyPart), 0.5F);
        Vec3d defaultDragVector = new Vec3d(source.getX() - dragged.getX(), source.getY() - dragged.getY(), source.getZ() - dragged.getZ()).multiply(factor,factor,factor);

        double x = prevx + defaultDragVector.x;
        double y = prevy + defaultDragVector.y;
        double z = prevz + defaultDragVector.z;

        Direction xDirection = defaultDragVector.x > 0 ? Direction.EAST : Direction.WEST;
        Direction zDirection = defaultDragVector.z > 0 ? Direction.SOUTH : Direction.NORTH;
        Direction mainDirection = Math.abs(defaultDragVector.x) > Math.abs(defaultDragVector.z) ? xDirection : zDirection;
        boolean mainDirectionColliding = !source.getEntityWorld().getBlockState(dragged.getBlockPos().offset(mainDirection)).isAir();

        if (mayJump) //trying to avoid dragged (body parts) getting stuck below thin-block-surface
        {
            BlockPos abovePos = dragged.getBlockPos().up();
            if (dragged.mayBeColliding(abovePos.offset(Direction.EAST))) {x -= 0.1F;}
            if (dragged.mayBeColliding(abovePos.offset(Direction.WEST))) {x += 0.1F;}
            if (dragged.mayBeColliding(abovePos.offset(Direction.SOUTH))) {z -= 0.1F;}
            if (dragged.mayBeColliding(abovePos.offset(Direction.NORTH))) {z += 0.1F;}
        }

        if (mayFall) //trying to avoid dragged (body parts) getting stuck by block collision while falling
        {
            BlockPos belowPos = dragged.getBlockPos().down();
            if (dragged.mayBeColliding(belowPos.offset(Direction.EAST))) {x -= 0.1F;}
            if (dragged.mayBeColliding(belowPos.offset(Direction.WEST))) {x += 0.1F;}
            if (dragged.mayBeColliding(belowPos.offset(Direction.SOUTH))) {z -= 0.1F;}
            if (dragged.mayBeColliding(belowPos.offset(Direction.NORTH))) {z += 0.1F;}
        }

        boolean yOverride = (mayJump && prevy < 0.9F && mainDirectionColliding);

        dragged.setVelocity(new Vec3d(x, yOverride ? 0.9F * factor : y, z).multiply(factor, factor, factor));
    }

    public boolean mayBeColliding(BlockPos pos)
    {
        BlockState state = this.getEntityWorld().getBlockState(pos);
        VoxelShape blockShape = state.getCollisionShape(this.getEntityWorld(), pos, ShapeContext.of(this)).offset(pos.getX(), pos.getY(), pos.getZ());
        return VoxelShapes.matchesAnywhere(blockShape, VoxelShapes.cuboid(this.getBoundingBox().expand(0.1F)), BooleanBiFunction.AND);
    }

    @Override public boolean collidesWith(Entity entity) {return !(entity instanceof AbstractSnakeEntity) && super.collidesWith(entity);}
    @Override protected void pushAway(Entity entity)
    {
        if (entity instanceof AbstractSnakeEntity snakeEntity && snakeEntity.getType() == this.getType())
        {
            boolean sameSnake = snakeEntity.getHead() != null && snakeEntity.getHead() == this.getHead();
            boolean collisionDueToSmallDistance = this.distanceTo(snakeEntity) <= 0.2 || (entity.getVelocity().x == 0 && entity.getVelocity().z == 0 && this.distanceTo(entity) < 0.4F);
            boolean noCollide = sameSnake && !collisionDueToSmallDistance;
            if (!noCollide) {super.pushAway(entity);}
        }
        else {super.pushAway(entity);}
    }

    @Override @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficultyIn, SpawnReason reason, @Nullable EntityData spawnDataIn)
    {
        if (this.isHead()) {this.head = this;}
        if (this.getBodyPartId() < this.length && !this.isCut() && this.getNextBodyPart() == null)
        {
            this.nextBodyPart = this.summonNextBodyPart();
            if (this.nextBodyPart != null)
            {
                this.nextBodyPart.initialize(world, difficultyIn, reason, spawnDataIn);
            }
        }
        return spawnDataIn;
    }

    private AbstractSnakeEntity summonNextBodyPart()
    {
        float x = 0.0F;
        float z = 0.0F;
        AbstractSnakeEntity nextBodyPart = this.getType().create(this.getEntityWorld(), SpawnReason.NATURAL);
        if (nextBodyPart != null)
        {
            if (this.isPersistent()) {nextBodyPart.setPersistent();}
            nextBodyPart.setCustomName(this.getCustomName());
            nextBodyPart.setAiDisabled(this.isAiDisabled());
            nextBodyPart.setInvulnerable(this.isInvulnerable());
            nextBodyPart.refreshPositionAndAngles(this.getX() + (double) x, this.getY(), this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
            nextBodyPart.setBodyPartId(this.getBodyPartId() + 1);
            nextBodyPart.setPreviousBodyPart(this);
            nextBodyPart.head = this.getHead();
            this.getEntityWorld().spawnEntity(nextBodyPart);
        }
        return nextBodyPart;
    }

    @Override protected CustomHurtInfo getDefaultCustomHurtInfo(float amount)
    {
        return new SnakeCustomHurtInfo(amount, this.defaultKbStrength(), this.shouldPlayHurtOrDeathSoundOnHurt(), this.shouldApplyKbOnHurt(), true);
    }

    @Override public boolean customHurt(ServerWorld serverWorld, DamageSource damageSource, CustomHurtInfo info)
    {
        boolean flag = super.customHurt(serverWorld, damageSource, info);
        if (flag && info instanceof SnakeCustomHurtInfo snakeHurtInfo && snakeHurtInfo.shouldSendToOthers())
        {
            float amount = info.amount();
            float amountReduction = 2.0F;
            float kbStrengthReduction = this.isHead() ? 0.01F : 0.05F;
            float minimumAmount = Math.min(amount, 0.5F);
            if (this.nextBodyPart != null) {this.nextBodyPart.sendHurt(serverWorld, damageSource, info, amountReduction, kbStrengthReduction, minimumAmount, 0.0F, this, AbstractSnakeEntity.SendDirection.BACKWARD);}
            if (this.previousBodyPart != null) {this.previousBodyPart.sendHurt(serverWorld, damageSource, info, amountReduction, kbStrengthReduction, minimumAmount, 0.0F, this, AbstractSnakeEntity.SendDirection.FORWARD);}
        }
        if (this.isDead()) {this.runDeathReaction();}
        return flag;
    }

    public boolean shouldReverseDrag()
    {
        AbstractSnakeEntity head = this.getHead();
        if (head != null && head.countNextBodyParts() + 1 < 6) {return false;}
        return this.isTailFalling() && this.isOnGround() || (this.reverseDrag && !this.isOnGround());
    }

    public void sendDragDirection(SendDirection dragDirection, SendDirection sendDirection, AbstractSnakeEntity sender)
    {
        this.reverseDrag = dragDirection == SendDirection.FORWARD;
        AbstractSnakeEntity torchbearer = sendDirection == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) torchbearer.sendDragDirection(dragDirection, sendDirection, sender);
    }

    public void sendHurt(ServerWorld serverWorld, DamageSource damageSource, CustomHurtInfo previousInfo, float amountReduction, float kbStrengthReduction, AbstractSnakeEntity sender, SendDirection direction)
    {
        this.sendHurt(serverWorld, damageSource, previousInfo, amountReduction, kbStrengthReduction, 0.0F, 0.0F, sender, direction);
    }

    public void sendHurt(ServerWorld serverWorld, DamageSource damageSource, CustomHurtInfo previousInfo, float amountReduction, float kbStrengthReduction, float minimumAmount, float minimumKbStrength, AbstractSnakeEntity sender, SendDirection direction)
    {
        float newAmount = Math.max(previousInfo.amount() - amountReduction, minimumAmount);
        float newKbStrength = Math.max(previousInfo.kbStrength() - kbStrengthReduction, minimumKbStrength);
        boolean shouldApplyKb = this.shouldApplyKbOnHurt() && newKbStrength > 0.0F;
        SnakeCustomHurtInfo newInfo = new SnakeCustomHurtInfo(newAmount, newKbStrength, this.shouldPlayHurtOrDeathSoundOnHurt(), shouldApplyKb, false);
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null && newAmount > 0) {torchbearer.sendHurt(serverWorld, damageSource, newInfo, amountReduction, kbStrengthReduction, minimumAmount, minimumKbStrength, sender, direction);}
        this.customHurt(serverWorld, damageSource, newInfo);
    }

    public void sendHeadUpdate(AbstractSnakeEntity newHead, AbstractSnakeEntity sender, SendDirection direction) //overrides head in this and all nexts body parts
    {
        this.head = newHead;
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) {torchbearer.sendHeadUpdate(newHead, sender, direction);}
    }

    public void sendDie(DamageSource damageSource, AbstractSnakeEntity sender, SendDirection direction)
    {
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) {torchbearer.sendDie(damageSource, sender, direction);}
        this.setHealth(0.0F);
        this.customOnDeath(damageSource, this.shouldPlayHurtOrDeathSoundOnHurt());
    }

    public void sendRemove(ServerWorld serverWorld, RemovalReason reason, AbstractSnakeEntity sender, SendDirection direction)
    {
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        if (torchbearer != null) {torchbearer.sendRemove(serverWorld, reason, sender, direction);}
        if (!this.isRemoved())
        {
            this.setRemoved(reason);
            super.onRemoval(serverWorld, reason); //do not call this.onRemoval here. Will infinite loop : this.onRemoval -> sendRemove -> this.onRemoval -> sendRemove -> ...
        }
    }

    public void sendJump(float yMovement, float yMovementReduction, AbstractSnakeEntity sender, SendDirection direction)
    {
        Vec3d deltamovement = this.getVelocity();
        AbstractSnakeEntity torchbearer = direction == SendDirection.BACKWARD ? this.nextBodyPart : this.previousBodyPart; //next one to receive and send the message
        float newYMovement = yMovement - yMovementReduction;
        if (torchbearer != null && newYMovement > 0) {torchbearer.sendJump(newYMovement, yMovementReduction, sender, direction);}
        this.setVelocity(deltamovement.x, yMovement, deltamovement.z);
    }

    @Override public void customOnDeath(DamageSource damageSource, boolean playSound)
    {
        if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_DIE)
        {
            AbstractSnakeEntity head = this.getHead();
            if (head != null && !head.isDead())
            {
                head.setHealth(0.0F);
                head.customOnDeath(damageSource, head.shouldPlayHurtOrDeathSoundOnHurt()); //mélange entre die et customDie
            }
            if (this.nextBodyPart != null && !this.nextBodyPart.isDead())
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.customOnDeath(damageSource, this.nextBodyPart.shouldPlayHurtOrDeathSoundOnHurt());
            }
        }
        else if (this.nextBodyPart != null)
        {
            if (this.bodyPartDeathReaction == BodyPartDeathReaction.LOOSE_TAIL)
            {
                this.nextBodyPart.setHealth(0.0F);
                this.nextBodyPart.customOnDeath(damageSource, this.nextBodyPart.shouldPlayHurtOrDeathSoundOnHurt());
            }
            else if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_SPLIT) {}
            else if (this.bodyPartDeathReaction == BodyPartDeathReaction.SPLIT_IF_NOT_HEAD)
            {
                if (this.isHead())
                {
                    this.nextBodyPart.sendDie(damageSource, this, SendDirection.BACKWARD);
                }
            }
        }
        //(this.)runDeathReaction(..) now done in (this.)customHurt(..), because can't sendHurt after detach / split.
        super.customOnDeath(damageSource, playSound);
    }

    protected void runDeathReaction()
    {
        if (this.bodyPartDeathReaction == BodyPartDeathReaction.ALWAYS_SPLIT || this.bodyPartDeathReaction == BodyPartDeathReaction.SPLIT_IF_NOT_HEAD && !this.isHead()) {this.split();}
        else {this.detach_body_part();}
    }

    protected void detach_body_part()
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

    protected void split()
    {
        AbstractSnakeEntity nextHead = this.findNextHead();
        if (nextHead != null)
        {
            nextHead.setBodyPartId(0);
            nextHead.sendHeadUpdate(nextHead, this, SendDirection.BACKWARD);
        }
        this.detach_body_part();
    }

    @Nullable private AbstractSnakeEntity findNextHead()
    {
        AbstractSnakeEntity next = this.nextBodyPart;
        while (next != null && next.isDead())
        {
            next = next.nextBodyPart;
        }
        return next;
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(BODY_PART_ID, 0);
        builder.add(IS_CUT, false);
    }

    @Override protected void writeCustomData(WriteView view)
    {
        super.writeCustomData(view);
        view.putInt("body_part_id", this.getBodyPartId());
        view.putBoolean("is_cut", this.isCut());
        if (this.nextBodyPart != null)
        {
            view.putString("next_body_part_uuid", this.nextBodyPart.getUuidAsString());
        }
    }

    @Override protected void readCustomData(ReadView view)
    {
        super.readCustomData(view);
        if (view.getOptionalInt("body_part_id").isPresent()) {this.setBodyPartId(view.getOptionalInt("body_part_id").get());}
        if (view.getBoolean("is_cut", false)) {this.setCut();}
        else {this.getDataTracker().set(IS_CUT, false);}
        if (view.getOptionalString("next_body_part_uuid").isPresent())
        {
            this.nextBodyPartStringUUID = view.getOptionalString("next_body_part_uuid").get();
        }
    }

    @Override public boolean canTeleportBetween(World source, World dest) {return false;}
    @Override protected void dropExperience(ServerWorld serverWorld, Entity entity) {if (this.isHead()) {super.dropExperience(serverWorld, entity);}}
    @Override public EntityType<AbstractSnakeEntity> getType() {return (EntityType<AbstractSnakeEntity>) super.getType();}
    @Override public boolean handleFallDamage(double distance, float damageMultiplier, DamageSource source)
    {
        if (!this.isHead()) {return false;}
        return super.handleFallDamage(distance, damageMultiplier, source);
    }

    protected float defaultKbStrength() {return 0.4F;}
    protected boolean shouldPlayHurtOrDeathSoundOnHurt() {return this.isHead();}
    protected boolean shouldPlayAmbientSound() {return this.isHead();}
    protected boolean shouldApplyKbOnHurt() {return true;}

    @Override public void playAmbientSound() {if (this.shouldPlayAmbientSound()) {super.playAmbientSound();}}
    @Override protected void playStepSound(BlockPos pos, BlockState state)
    {
        if (this.isHead()) {super.playStepSound(pos, state);}
        else if (this.random.nextFloat() < 0.2F) //if is not head : 20% chance to play sound anyway, but with 15x lower volume
        {
            BlockSoundGroup blockSoundGroup = state.getSoundGroup();
            this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.1F, blockSoundGroup.getPitch());
        }
    }

    @Nullable @Override protected SoundEvent getAmbientSound(){return AerialHellSoundEvents.ENTITY_SNAKE_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SNAKE_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SNAKE_DEATH;}
}
