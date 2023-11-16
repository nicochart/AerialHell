package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
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
    public static final EntityDataAccessor<Integer> FACE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SIZE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);

    public KodamaEntity(EntityType<? extends KodamaEntity> type, Level worldIn) {super(type, worldIn);}

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficulty, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag)
    {
        this.setRandomFaceAndSize();
        return super.finalizeSpawn(serverLevelAccessor, difficulty, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override protected void registerGoals()
    {
        super.registerGoals();
    }

    @Override protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(FACE_ID, 1);
        this.entityData.define(SIZE_ID, 1);
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

    private void setRandomFaceAndSize()
    {
        this.setFaceId(this.random.nextInt(5)+1);
        this.setSizeId(this.random.nextInt(5)+1);
    }

    //@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_KODAMA_AMBIENT.get();} TODO
    //@Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_KODAMA_HURT.get();}
    //@Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_KODAMA_DEATH.get();}

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putInt("FaceId", this.getFaceId());
        compound.putInt("SizeId", this.getSizeId());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setFaceId(compound.getInt("FaceId"));
        this.setSizeId(compound.getInt("SizeId"));
    }
}
