package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GlidingTurtleEntity extends AerialHellAnimalEntity
{
	private int jumpTimer;
    public static final EntityDataAccessor<Boolean> GLIDING = SynchedEntityData.<Boolean>defineId(GlidingTurtleEntity.class, EntityDataSerializers.BOOLEAN);

    public GlidingTurtleEntity(EntityType<? extends GlidingTurtleEntity> type, Level worldIn)
    {
        super(type, worldIn);
        this.jumpTimer = 0;
    }

    public GlidingTurtleEntity(Level worldIn)
    {
        this(AerialHellEntities.GLIDING_TURTLE.get(), worldIn);
        this.jumpTimer = 0;
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(GLIDING, false);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(AerialHellBlocksAndItems.AERIAL_BERRY.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        return AerialHellEntities.GLIDING_TURTLE.get().create(this.level);
    }
    
    public boolean isGliding() {return !this.entityData.get(GLIDING);}
    public void setGliding(boolean flag) {this.entityData.set(GLIDING, !flag);}

    @Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_DEATH.get();}

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Glide", this.isGliding());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setGliding(compound.getBoolean("Glide"));
    }

    @Override public void aiStep()
    {
        super.aiStep();
        if (!this.onGround)
        {
            if (this.isGliding()) {this.setGlidingMotion();}
            else if (this.getDeltaMovement().y < -0.3D) {this.setGliding(true);}
        }

        if (this.jumpTimer > 600 && !this.isGliding() && this.isOnGround())
        {
            this.glideJump();
        }
        if (!this.isGliding()) {this.jumpTimer += 1 + (int) Math.random()*10;}

        if (this.isGliding() && this.isOnGround() && this.getDeltaMovement().y>=-0.1F) {this.setGliding(false);}
    }

    private void setGlidingMotion()
    {
        if (this.getDeltaMovement().y < 0.0D)
        {
            Vec3 forward = Vec3.directionFromRotation(this.getRotationVector()); //TODO : right direction ?
            if (this.hasBlockUnder(this.blockPosition().offset(2.5*forward.x, forward.y, 2.5*forward.z), 20))
            {
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D).add(forward.x/100, 0, forward.z/100));
            }
            else
            {
                if (this.hasBlockUnder(this.blockPosition(), 25))
                {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.9D, 0.6D, 0.9D));
                }
                else
                {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.5D, 1.0D).add(forward.x/70, 0, forward.z/70));
                }
            }
        }
    }

    protected boolean hasBlockUnder(BlockPos pos, int yBlocksDistance)
    {
        for (int dy=0; dy<yBlocksDistance; dy++) {if (!level.isEmptyBlock(pos.below(dy))) {return true;}}
        return false;
    }

    public void glideJump()
    {
        if (this.isBaby())
        {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 1.1D, 0.0D));
        }
        else
        {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 1.5D, 0.0D));
        }
        this.jumpTimer = 0;
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        boolean flag = super.hurt(source, amount);
        if (flag && !this.isGliding()) {this.jumpTimer += 400;}
        return flag;
    }
}
