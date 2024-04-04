package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionWaterAvoidingRandomStrollGoal;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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

public class SnakeEntity extends Monster
{
    public static int LENGTH = 10;
    private boolean isCut = false;
    @Nullable private SnakeEntity nextBodyPart;
    private static final EntityDataAccessor<Integer> BODY_PART_ID = SynchedEntityData.<Integer>defineId(SnakeEntity.class, EntityDataSerializers.INT);

    public SnakeEntity(EntityType<? extends SnakeEntity> type, Level world) {super(type, world);}

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SnakeMeleeAttackGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new SnakeWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        this.dragNextBodyPart();
    }

    protected void dragNextBodyPart()
    {
        if (this.nextBodyPart != null)
        {
            Vec3 prevDeltaMovement = this.nextBodyPart.getDeltaMovement(); double prevx = prevDeltaMovement.x, prevy = prevDeltaMovement.y, prevz = prevDeltaMovement.z;
            double factor = Math.min(Math.max(0.25, 0.25 * this.distanceTo(this.nextBodyPart)), 0.3F);
            Vec3 defaultDragVector = new Vec3(this.getX() - this.nextBodyPart.getX(), this.getY() - this.nextBodyPart.getY(), this.getZ() - this.nextBodyPart.getZ()).multiply(factor,factor,factor);

            double x = prevx + defaultDragVector.x;
            double y = prevy + defaultDragVector.y;
            double z = prevz + defaultDragVector.z;

            Direction xDirection = defaultDragVector.x > 0 ? Direction.EAST : Direction.WEST;
            Direction zDirection = defaultDragVector.z > 0 ? Direction.SOUTH : Direction.NORTH;
            Direction mainDirection = Math.abs(defaultDragVector.x) > Math.abs(defaultDragVector.z) ? xDirection : zDirection;
            boolean mainDirectionColliding = !this.level().getBlockState(this.nextBodyPart.blockPosition().relative(mainDirection)).isAir();
            boolean yOverride = (this.getY() > this.nextBodyPart.getY() && prevy < 0.9F && mainDirectionColliding);

            this.nextBodyPart.setDeltaMovement(new Vec3(x, yOverride ? 0.9F * factor : y, z).multiply(factor, factor, factor));
        }
    }

    @Override public boolean canCollideWith(Entity entity)
    {
        return !(entity instanceof SnakeEntity) && super.canCollideWith(entity);
    }

    @Override protected void doPush(Entity entity)
    {
        if (!(entity instanceof SnakeEntity)) {super.doPush(entity);}
    }

    @Override @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
    {
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
            nextBodyPart.moveTo(this.getX() + (double) x, this.getY() + 0.5D, this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
            nextBodyPart.setBodyPartId(this.getBodyPartId() + 1);
            this.level().addFreshEntity(nextBodyPart);
        }
        return nextBodyPart;
    }

    @Override public EntityType<SnakeEntity> getType() {return (EntityType<SnakeEntity>) super.getType();}

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(BODY_PART_ID, 0);
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putInt("body_part_id", this.getBodyPartId());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setBodyPartId(compound.getInt("body_part_id"));
    }

    public int getBodyPartId() {return this.entityData.get(BODY_PART_ID);}
    protected void setBodyPartId(int id) {this.entityData.set(BODY_PART_ID, id);}
    public boolean isHead() {return this.entityData.get(BODY_PART_ID) == 0;}

    protected void setCut() {this.isCut = true;}
    protected boolean isCut() {return this.isCut;}

    @Nullable public SnakeEntity getNextBodyPart() {return this.nextBodyPart;}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
        		.add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override public boolean canChangeDimensions() {return false;}
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.SILVERFISH_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.SILVERFISH_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.SILVERFISH_DEATH;}

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
}
