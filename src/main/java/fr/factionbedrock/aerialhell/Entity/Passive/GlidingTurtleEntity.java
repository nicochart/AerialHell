package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.GlideGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class GlidingTurtleEntity extends AerialHellAnimalEntity
{
    public static final EntityDataAccessor<Boolean> GLIDING = SynchedEntityData.<Boolean>defineId(GlidingTurtleEntity.class, EntityDataSerializers.BOOLEAN);
    private int ateTimer;

    public GlidingTurtleEntity(EntityType<? extends GlidingTurtleEntity> type, Level worldIn) {super(type, worldIn);}

    public GlidingTurtleEntity(Level worldIn) {this(AerialHellEntities.GLIDING_TURTLE.get(), worldIn);}

    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        if (this.isFood(player.getItemInHand(hand))) {this.ateTimer = 12000;}
        return super.mobInteract(player, hand);
    }

    @Override public void tick()
    {
        if (this.ateTimer > 0) {this.ateTimer--;}
        else if (!this.isAteTimerInBounds()) {this.ateTimer = 0;}
        super.tick();
    }

    private boolean isAteTimerInBounds() {return this.ateTimer >=0 && this.ateTimer <= 6000;}

    @Override protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new GlideGoal(this));
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(GLIDING, false);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.TEMPT_RANGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        return AerialHellEntities.GLIDING_TURTLE.get().create(world, EntitySpawnReason.BREEDING);
    }
    
    public boolean isGliding() {return !this.entityData.get(GLIDING);}
    public void setGliding(boolean flag) {this.entityData.set(GLIDING, !flag);}

    @Override public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}

    @Override public int getAmbientSoundInterval() {return 640;}
    @Override protected float getSoundVolume() {return 0.7F;}
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_DEATH.get();}
    @Override public void playAmbientSound()
    {
        SoundEvent ambientSound = this.getAmbientSound();
        if (ambientSound != null) {this.playSound(ambientSound, this.ateTimer <= 0 ? this.getSoundVolume() : 0.0F, this.getVoicePitch());}
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Glide", this.isGliding());
        compound.putInt("AteTimer", this.ateTimer);
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setGliding(compound.getBoolean("Glide"));
        this.ateTimer = compound.getInt("AteTimer");
    }
}
