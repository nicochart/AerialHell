package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.GlideGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GlidingTurtleEntity extends AerialHellAnimalEntity
{
    public static final TrackedData<Boolean> GLIDING = DataTracker.<Boolean>registerData(GlidingTurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int ateTimer;

    public GlidingTurtleEntity(EntityType<? extends GlidingTurtleEntity> type, World world) {super(type, world);}

    public GlidingTurtleEntity(World world) {this(AerialHellEntities.GLIDING_TURTLE, world);}

    public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        if (this.isBreedingItem(player.getStackInHand(hand))) {this.ateTimer = 12000;}
        return super.interactMob(player, hand);
    }

    @Override public void tick()
    {
        if (this.ateTimer > 0) {this.ateTimer--;}
        else if (!this.isAteTimerInBounds()) {this.ateTimer = 0;}
        super.tick();
    }

    private boolean isAteTimerInBounds() {return this.ateTimer >=0 && this.ateTimer <= 6000;}

    @Override protected void initGoals()
    {
        super.initGoals();
        this.goalSelector.add(0, new GlideGoal(this));
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(GLIDING, false);
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity mob)
    {
        return AerialHellEntities.GLIDING_TURTLE.create(this.getWorld());
    }
    
    public boolean isGliding() {return !this.getDataTracker().get(GLIDING);}
    public void setGliding(boolean flag) {this.getDataTracker().set(GLIDING, !flag);}

    @Override public boolean handleFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}

    @Override public int getMinAmbientSoundDelay() {return 640;}
    @Override protected float getSoundVolume() {return 0.7F;}
    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_GLIDING_TURTLE_DEATH;}
    @Override public void playAmbientSound()
    {
        SoundEvent ambientSound = this.getAmbientSound();
        float volume = this.ateTimer <= 0 ? this.getSoundVolume() : 0.0F;
        float pitch = this.getSoundPitch();
        if (ambientSound != null) {this.playSound(ambientSound, volume, pitch);}
    }

    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Glide", this.isGliding());
        nbt.putInt("AteTimer", this.ateTimer);
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        this.setGliding(nbt.getBoolean("Glide"));
        this.ateTimer = nbt.getInt("AteTimer");
    }
}
