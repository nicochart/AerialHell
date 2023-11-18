package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.KodamaRattleGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class KodamaEntity extends AerialHellAnimalEntity
{
    private static final EntityDataAccessor<Integer> FACE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIZE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_RATTLING = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> RATTLING_TILT_ANGLE = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    public int rattleTimerMalus;
    public float rattleHeadRotZAmplitude;

    public KodamaEntity(EntityType<? extends KodamaEntity> type, Level worldIn) {super(type, worldIn); this.setRandomRattleTimerMalusAndHeadRotAmplitude();}

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficulty, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag)
    {
        this.setRandomFaceAndSize();
        this.setRattling(false);
        return super.finalizeSpawn(serverLevelAccessor, difficulty, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new KodamaRattleGoal(this));
        super.registerGoals();
    }

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(FACE_ID, 1);
        this.entityData.define(SIZE_ID, 1);
        this.entityData.define(IS_RATTLING, false);
        this.entityData.define(RATTLING_TILT_ANGLE, 0);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        return AerialHellEntities.KODAMA.get().create(this.level());
    }

    public int getFaceId() {return this.entityData.get(FACE_ID);}
    public void setFaceId(int id) {this.entityData.set(FACE_ID, id);}
    public int getSizeId() {return this.entityData.get(SIZE_ID);}
    public void setSizeId(int id) {this.entityData.set(SIZE_ID, id);}
    public boolean isRattling() {return this.entityData.get(IS_RATTLING);}
    public void setRattling(boolean b) {this.entityData.set(IS_RATTLING, b);}
    public int getRattlingTiltAngle() {return this.entityData.get(RATTLING_TILT_ANGLE);}
    public void setRattlingTiltAngle(int value) {this.entityData.set(RATTLING_TILT_ANGLE, value);}

    public int getMaxRattlingTiltAngle() {return 20;}

    private void setRandomFaceAndSize()
    {
        this.setFaceId(this.random.nextInt(5)+1);
        this.setSizeId(this.random.nextInt(5)+1);
    }

    private void setRandomRattleTimerMalusAndHeadRotAmplitude()
    {
        this.rattleTimerMalus = random.nextInt(300, 600);
        int negative_or_positive = this.random.nextInt(2) == 0 ? -1 : 1;
        this.rattleHeadRotZAmplitude = negative_or_positive * (5 + this.random.nextInt(5)) / 10.0F;
    }

    @Override public void tick()
    {
        if (tickCount % 6000 == 0 && !this.isRattling()) {this.setRandomRattleTimerMalusAndHeadRotAmplitude();}
        super.tick();
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_KODAMA_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_KODAMA_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_KODAMA_DEATH.get();}
    protected SoundEvent getRattleSound() {return AerialHellSoundEvents.ENTITY_KODAMA_RATTLE.get();}

    public void playRattleSound()
    {
        SoundEvent soundevent = this.getRattleSound();
        if (soundevent != null) {this.playSound(soundevent, 0.6F, this.getVoicePitch());}
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putInt("FaceId", this.getFaceId());
        compound.putInt("SizeId", this.getSizeId());
        compound.putBoolean("IsRattling", this.isRattling());
        compound.putInt("TiltAngle", this.getRattlingTiltAngle());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setFaceId(compound.getInt("FaceId"));
        this.setSizeId(compound.getInt("SizeId"));
        this.setRattling(compound.getBoolean("IsRattling"));
        this.setRattlingTiltAngle(compound.getInt("TiltAngle"));
    }
}
